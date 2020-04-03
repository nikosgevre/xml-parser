import java.util.*;
import java.lang.*;
import java.io.*; 
import java.net.*; 
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*My also need these imports*/
// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStream;
// import java.io.InputStreamReader;
// import java.io.OutputStream;
// import java.io.OutputStreamWriter;
// import java.io.Reader;
// import java.io.Writer;
// import java.net.HttpURLConnection;
// import java.net.ProtocolException;
// import java.net.URL;
// import java.net.URLConnection;

public class DocumentBuilder{
	public Node [] pateres = new Node[0];
	
	/*-----------------CONSTRUCTORS-----------------*/
	
	public DocumentBuilder(){
		//dhmiourgei ena keno DocumentBuilder
	}
	
	/*-----------------METHODS-----------------*/
	
	public String getDocumentAsString(String location){
		//diavazei ena text file kai to gurnaei se string(h topothesia tou arxeiou borei na einai url/filesystem). To string periexei olo to periexomeno tou arxeiou xml.
		
		if(location.contains("http://")) {
			try {
				URL mhtsos = new URL(location);
				BufferedReader in = new BufferedReader(
				new InputStreamReader(mhtsos.openStream()));
				
				String xml = null;
				String inputLine;
				while ((inputLine = in.readLine()) != null){
					xml += inputLine;
				}
				
				in.close();
				return(xml);
			}
			catch(MalformedURLException ex) {
				System.out.println("This isn't a specified url "+ location);
				return (null);
			}
			catch(IOException ex) {
				System.out.println("IOException occured while reading from file "+location);
				return (null);
			}    
		}
		else {
			try {
				File file = new File (location);
				FileReader fReader = new FileReader(file);
				BufferedReader in = new BufferedReader(fReader);
				String inputLine;
				StringBuffer strDocument = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					strDocument.append(inputLine);
				}
				fReader.close();
				return (strDocument.toString());
			}
			catch(FileNotFoundException ex) {
				System.out.println("The specified file was not found at "+ location);
				return (null);
			}   
			catch(IOException ex) {
				System.out.println("IOException occured while reading from file "+location);
			}    
			return "Nothing to return..";
		}
	}
	
	public Document getDocument(String location) {
		//diavazei ena arxeio 'h url kai epistrefei Document(to kanoniko XML Document)
		String XMLstr = getDocumentAsString(location);
		Document doc = parseDocument(XMLstr);
		return(doc);
	}
	
	public Document parseDocument(String documentStr) {
		int i = 0, j = 0, k = 0;
		int position = 0;
		int count_xmlns = 0;
		int flag_space;
		int count = 0,shmaia = 0;
		int index = 0;
		String name_namespace;
		String nmspc,namespace;
		String txt = " ";
		String subdocStr = documentStr;
		Document doc = new Document();
		
		Pattern nodeP = Pattern.compile("<(/?)([a-zA-Z_]+)([a-zA-Z_0-9:]*)([^>]*)(/?)>");
		Matcher nodeM = nodeP.matcher(documentStr);
		while(nodeM.find() ) {
			Node nd = new Node();

			//*****************TEXT**************************
			index = subdocStr.indexOf(nodeM.group());
			subdocStr = subdocStr.substring(index+nodeM.group().length(),subdocStr.length());
			if(subdocStr.length()>0){
				if(Character.isLetter(subdocStr.charAt(0)) || Character.isDigit(subdocStr.charAt(0))) {
					txt = " ";
					k = 0;
					shmaia = 1;
					while(!txt.contains("</")) {
						k++;
						txt = subdocStr.substring(0,k);
					}
					txt = txt.substring(0,txt.length()-2);
				}
			}
			
			//****************NODES**************************
			flag_space = 0;
			if(nodeM.group(2)!= null && !nodeM.group(1).contains("/")) {
				
				
				//*********NAMESPACES***********************
				if(nodeM.group(4).contains("url=")) {
					if(doc.namespacePrefixExists(nodeM.group(2))) {
						flag_space = 1;
					}
					else {
						System.out.println("Error, this prefix doesn't exist!");
						return(null);
					}
				}
					
				if(flag_space == 1) {
					name_namespace = nodeM.group(3).substring(1, (nodeM.group(3).length()));
				}
				else {
					name_namespace =nodeM.group(2);
				}
				nd.setName(name_namespace);
				count++;
				if(count == 1){
					doc.setRootNode(nd);
				}

				//*******TEXT****************
				if(shmaia == 1) {
					nd.setText(txt);
					shmaia = 0;
				}
				else {
					nd.setText("null");
				}
				
				if(i == 0) {
					pateres = Arrays.copyOf(pateres, pateres.length + 1);
					pateres[i] = nd;
					nd.setParent(null);
					i++;
				}
				else{
					pateres = Arrays.copyOf(pateres, pateres.length + 1);
					pateres[i] = nd;
					nd.setParent(pateres[i-1]);
					pateres[i-1].addChild(pateres[i]);
					i++;
				}
				int len = nodeM.group(4).length();
				if(len != 0) {
					//*****************NAMESPACES**********************
					namespace = nodeM.group(4);
					
					if(namespace.contains("xmlns:")) {
						position = namespace.indexOf("=");
						nmspc = namespace.substring(7, (namespace.length()-1));
						Namespace geller = new Namespace(namespace.substring(7,(position)),namespace.substring((position+2),(namespace.length()-1)));
						if(doc.namespacePrefixExists(geller.getPrefix())) {
							System.out.println("Error, you can't use the same prefix twise!");
							return(null);
						}
						doc.addNamespace(geller);
						nd.setNamespace(geller);
						count_xmlns++;
					}
					String c = Character.toString(nodeM.group(4).charAt(len-1));
					if(c.contains("/")) {
						pateres = Arrays.copyOf(pateres, pateres.length - 1);
						if(i!=0) {
							i--;
						}
						
					}
				}
			}
			
			if(nodeM.group(1).contains("/")) {
				
				pateres = Arrays.copyOf(pateres, pateres.length - 1);
				if(i!=0) {
					i--;
				}
				
			}

			//**********ATTRIBUTES********************
			Pattern attrP = Pattern.compile(" ([a-zA-z_]+)=\"([\\p{Alnum}\\p{Punct}]+)\"");
			Matcher attrM = attrP.matcher(nodeM.group(4));
			
			int found = 0,counter = 0; 
			while(attrM.find() ) {
				counter++;
				found = 0;
				while(nd.getAttributes().size()>found) {
					if(nd.getAttribute(found).getName().equals(attrM.group(1))){
						System.out.println("This attribute already exists!");
						return(null);
					}
					found++;
				}
				Attribute attr = new Attribute(attrM.group(1),attrM.group(2));
				nd.addAttribute(attr);
			}
			
			if(counter > 0) {
				if(flag_space == 1) {
					nd.getAttribute(counter-1).setNamespace(doc.getNamespaces().get(j));
				}
			}
		}
		return(doc);
	}
}

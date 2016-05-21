import java.util.*;
import java.lang.*;

public class Document{
	private Node root;
	private List<Namespace> nameSpace;
	
	/*-----------------CONSTRUCTORS-----------------*/
	
	public Document() {
		//dhmiourgei ena keno Document
		nameSpace = new LinkedList<Namespace>();
	}
	public Document(Node rootNode) {
		//dhmiourgei ena kainourio Document me root node=rootNode
		nameSpace = new LinkedList<Namespace>();
		root = rootNode;
	} 
	
	/*-----------------METHODS-----------------*/
	
	public Node getRootNode(){
		//gurnaei to root node tou Document
		return(root);
	}
	
	protected void setRootNode(Node rootNode) {
		//arxikopoiei to root node tou Document
		root = rootNode;
	}
	
	protected void addNamespace(Namespace namespace) {
		nameSpace.add(namespace);
	}
	
	public boolean namespacePrefixExists(String prefix) {
		int i = 0;
		boolean flag = false;
		
		while(nameSpace.size() > i) {
			if(nameSpace.get(i).getPrefix().equals(prefix)) {
				flag = true;
				break;
			}
			i++;
		}
		
		return(flag);
	}
	
	public List<Namespace> getNamespaces() {
		
		return(nameSpace);
	}
	
	public Namespace getNamespace(String prefix) {
		int i = 0;
		Namespace flag = null;
		
		while(nameSpace.size() > i) {
			if(nameSpace.get(i).getPrefix().equals(prefix)) {
				flag = nameSpace.get(i);
				break;
			}
			i++;
		}
		
		return(flag);
	}
	
	public String toXMLString() {
		
		return(getRootNode().toXMLString());
	}
	
	public String toXMLString(String identStr) {
		
		return(getRootNode().toXMLString(0,identStr));
	}

}


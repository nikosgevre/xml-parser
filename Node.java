package gr.uth.inf.ce325.xml_parser;

import java.util.*;
import java.lang.*;

public class Node {
	private String name;
	private String text;
	private Node parent;
	private Document doc;
	private List<Node> child;
	private List<Attribute> attr;
	private Namespace nm;
	
	public int noch, noat;
	
	/*-----------------CONSTRUCTORS-----------------*/
	
	public Node() {
		child = new LinkedList<Node>();
		attr = new LinkedList<Attribute>();
		text = "null";
	}
	
	public Node(String name) {
		child = new LinkedList<Node>();
		attr = new LinkedList<Attribute>();
		this.name = name;
		text = "null";
	}
	
	public Node(String name, String text) {
		child = new LinkedList<Node>();
		attr = new LinkedList<Attribute>();
		this.name = name;
		this.text = text;
	}
	
	public Node(Document doc, String name, String text) {
		child = new LinkedList<Node>();
		attr = new LinkedList<Attribute>();
		this.name = name;
		this.text = text;
		this.doc = doc;
	}
	
	public Node(String name, String text, Node parent) {
		child = new LinkedList<Node>();
		attr = new LinkedList<Attribute>();
		this.name = name;
		this.text = text;
		this.doc = doc;
		this.parent = parent;
	}
	
	public Node(Document doc, String name, String text, Node parent) {
		child = new LinkedList<Node>();
		attr = new LinkedList<Attribute>();
		this.name = name;
		this.text = text;
		this.doc = doc;
		this.parent = parent;
	}
	
	public Node(String name, String text, Node parent, List<Attribute> attr) {
		child = new LinkedList<Node>();
		this.attr = attr;
		this.name = name;
		this.text = text;
		this.doc = doc;
		this.parent = parent;
	}
	
	public Node(Document doc, String name, String text, Node parent, List<Attribute> attr) {
		child = new LinkedList<Node>();
		this.attr = attr;
		this.name = name;
		this.text = text;
		this.doc = doc;
		this.parent = parent;
	}
	
	public Node(String name, String text, Node parent, List<Attribute> attr, Namespace nm) {
		child = new LinkedList<Node>();
		this.attr = attr;
		this.name = name;
		this.text = text;
		this.doc = doc;
		this.parent = parent;
		this.nm = nm;
	}
	
	public Node(Document doc, String name, String text, Node parent, List<Attribute> attr, Namespace nm) {
		child = new LinkedList<Node>();
		this.attr = attr;
		this.name = name;
		this.text = text;
		this.doc = doc;
		this.parent = parent;
		this.nm = nm;
	}
	
	public Node(String name, String text, List<Attribute> attr, Namespace nm) {
		child = new LinkedList<Node>();
		this.attr = attr;
		this.name = name;
		this.text = text;
		this.doc = doc;
		this.nm = nm;
	}
	
	public Node(Document doc, String name, String text, List<Attribute> attr, Namespace nm) {
		child = new LinkedList<Node>();
		this.attr = attr;
		this.name = name;
		this.text = text;
		this.doc = doc;
		this.nm = nm;
	}
	
/*-----------------METHODS-----------------*/
	
	public String getName() {
		//gurnaei to onoma tou Node
		return(name);
	}
	
	public String getText() {
		//gurnaei to text tou node an uparxei, an oxi gurnaei adeio string
		return(text);
	}
	
	public void setName(String name) {
		//orizei onoma komvou
		this.name = name;
	}
	
	public void setText(String text) {
		//vazei to text tou komvou
		this.text = text;
	}
	
	public Node getParent() {
		//epistrefei ton komvo tou patera
		return(parent);
	}
	
	public void setParent(Node parent) {
		//vazei ton komvo tou patera
		this.parent = parent;
	}
	
	public void addChild(Node child) {
		//prosthetei ton komvo tou paidiou sth lista apo ta child Nodes tou node
		this.child.add(child);
	}
	
	public void addChild(int index, Node child) {
		//prosthetei ton komvo tou paidiou sth thesh index ths listas sta paidia tou komvou tou Node
		this.child.add(index, child);
	}
	
	public Node getFirstChild() {
		//gurnaei ton komvo tou prwtou paidiou apo th lista twn paidiwn
		noch = 1;
		return(child.get(0)); 
	}
	
	public Node getNextChild() {
		//gurnaei to epomeno paidi apo th lista
		noch++;
		return(child.get(noch-1));
	}
	
	public Node getChild(int index) {
		//gurnaei ton komvo tou paidiou sth thesh index apo th lista child Nodes
		noch = index+1;
		return(child.get(index));
	}
	
	public List<Node> getChildren() {
		//gurnaei th lista twn paidiwn tou komvou
		return(child);
	}
	
	public void addAttribute(Attribute attr) {
		//prosthetei Attribute sth lista twn attributes tou komvou
		this.attr.add(attr);
	}
	
	public void addAttribute(int index, Attribute attr) {
		//prosthetei Attribute sth lista twn attributes tou komvou sth thesh index
		this.attr.add(index, attr);
	}
	
	public Attribute getFirstAttribute() {
		//gurnaei to prwto Attribute apo th lista twn attributes tou komvou
		noat = 1;
		return(attr.get(0));
	}
	
	public Attribute getNextAttribute() {
		//gurnaei to epomeno attribute apo th lista twn attributes tou komvou
		noat++;
		return(attr.get(noat-1));
	}
	
	public Attribute getAttribute(int index) {
		//gurnaei to attribute sth thesh index apo th lista twn attributes tou komvou
		noat = index + 1;
		return(attr.get(index));
	}
	
	public List<Attribute> getAttributes() {
		//gurnaei th lista twn attributes tou komvou
		return(attr);
	}
	
	public Namespace getNamespace(){
		//Return the Namespace the Node belongs to, if any exists
		return(nm); 
	}
	
	public void setNamespace(Namespace nm){
		//Set the Namespace for the Node
		this.nm = nm;
	}

	public String toXMLString(int depth, String identStr) {
		int i = 0, o = 0, d = 0;
		Node nd = new Node();
		String xmlString = "";
		String idStr = "";
		
		if(depth!=0) {
			xmlString += "\r\n";
		}
		while(i<depth) {
			idStr +=identStr;
			i++;
		}
		xmlString += idStr + "<";
		i = 0;
		if(attr.size()!=0) {
			o = 0;
			while(attr.size()>o){
				
				if(getAttribute(o).toAttrString().contains(":") &&getAttribute(o).toAttrString().contains("url")) {
					xmlString += getAttribute(o).toAttrString().substring(0,getAttribute(o).toAttrString().indexOf(":")+1) + getName() + getAttribute(o).toAttrString().substring(getAttribute(o).toAttrString().indexOf(":")+1,getAttribute(o).toAttrString().length());
				}
				else {
					if(o == 0) {
						xmlString +=getName();
					}
						xmlString += " "+  getAttribute(o).toAttrString();
				}
				o++;
			}
		}
		
		if(nm!=null) {
			xmlString += getName() + " xmlns:" + nm.getPrefix() + "=" + "\"" + nm.getURI() + "\"";
		}
		if(child.size() != 0) {
			xmlString +=getName();
			xmlString += ">";
			i = 0;
			while(child.size() > i) {
				xmlString += getChild(i).toXMLString(depth+1,identStr);
				i++;
			}
			xmlString += "\r\n" + idStr + "</" + getName() + ">";
		}
		else {
			if(!text.equals("null")) {
				if (o == 0) {
					xmlString += getName() + ">" + text + "</" + getName() + ">";
				}
				else {
					xmlString += ">" + text + "</" + getName() + ">";
				}
			}
			else {
				xmlString += "/>";
			}
		}
		
		
		return(xmlString);
	}
	
	public String toXMLString() {
		String xmlString = "<";
		int i = 0, o = 0;

		
		if(attr.size()!=0) {
			o = 0;
			while(attr.size()>o){
				
				if(getAttribute(o).toAttrString().contains(":") &&getAttribute(o).toAttrString().contains("url")) {
					xmlString += getAttribute(o).toAttrString().substring(0,getAttribute(o).toAttrString().indexOf(":")+1) + getName() + getAttribute(o).toAttrString().substring(getAttribute(o).toAttrString().indexOf(":")+1,getAttribute(o).toAttrString().length());
				}
				else {
					if(o == 0) {
						xmlString +=getName();
					}
						xmlString += " "+  getAttribute(o).toAttrString();
				}
				o++;
			}
		}
		
		if(nm!=null) {
			xmlString += getName() + " xmlns:" + nm.getPrefix() + "=" + "\"" + nm.getURI() + "\"";
		}
		if(child.size() != 0) {
			xmlString +=getName();
			xmlString += ">";
			while(child.size() > i) {
				xmlString += getChild(i).toXMLString();
				i++;
			}
			xmlString += "</" + getName() + ">";
		}
		else {
			if(text != null) {
				if (o == 0) {
					xmlString += getName() + ">" + text + "</" + getName() + ">";
				}
				else {
					xmlString += ">" + text + "</" + getName() + ">";
				}
			}
			else {
				xmlString += "/>";
			}
		}
		return(xmlString);
	}
}


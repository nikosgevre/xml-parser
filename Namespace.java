package gr.uth.inf.ce325.xml_parser;

import java.util.*;
import java.lang.*;

public class Namespace{
	private String prefix;
	private String uri;
	
	/*-----------------CONSTRUCTORS-----------------*/
	
	public Namespace(String prefix,String uri) {
		this.prefix = prefix;
		this.uri = uri;
				
	}

	/*-----------------METHODS-----------------*/
	
	public String getPrefix(){
		
		return(prefix);
	}

	public String getURI(){
		
		return(uri);
	}

	public String toXMLString(){
		return("xmlns:"+ getPrefix() + "= " +"\""+ getURI() + "\"");
	}
	
}
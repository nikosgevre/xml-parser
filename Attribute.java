import java.util.*;
import java.lang.*;

public class Attribute{
	private String name;
	private String value;
	private Namespace nm;
	private Document doc;
	
	/*-----------------CONSTRUCTORS-----------------*/
	
	public Attribute(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	public Attribute(String name, String value, Document doc) {
		this.name = name;
		this.value = value;
		this.doc = doc;
	}
	
	public Attribute(String name, String value, Namespace nm) {
		this.name = name;
		this.value = value;
		this.nm = nm;
	}
	
	public Attribute(String name, String value, Document doc, Namespace nm) {
		this.name = name;
		this.value = value;
		this.doc = doc;
		this.nm = nm;
	}
	
	/*-----------------METHODS-----------------*/
	
	public void setName(String name) {
		//arxikopoiei to onoma tou Attribute
		this.name = name;
	}
	
	public void setValue(String value) {
		//arxikopoiei thn timh tou attribute
		this.value = value;
	}
	
	public String getName() {
		//gurnaei to onoma tou attribute
		return(name);
	}
	
	public String getValue() {
		//gurnaei thn timh tou attribute
		return(value);
	}
	
	public Namespace getNamespace(){
		//gurnaei th timh tou Namespace
		return(nm);
	}
	
	public void setNamespace(Namespace nm) {
		//arxikopoiei th timh tou Namespace
		this.nm = nm;
	}
	
	public String toAttrString() {
		//gurnaei ena string pou einai h anaparastash tou attribute
		if(nm!= null) {
			return(nm.getPrefix() + ":" +getName() + "=" + "\"" + getValue() + "\"");
		}
		return(getName() + "=" + "\"" + getValue() + "\"");
	}
}

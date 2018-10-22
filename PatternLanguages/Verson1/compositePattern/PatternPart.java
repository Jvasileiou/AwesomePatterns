package compositePattern;

import java.io.OutputStream;
import java.io.PrintWriter;

public class PatternPart extends PatternComponent{

	private String contents;
	
	public PatternPart(String name, String contents) {
		super(name);
		
		this.setContents(contents);
	}
	
	public String getContents(){
		return contents;
	}
	
	public void setContents(String contents){
		this.contents=contents;
	} 
	
	public void saveContents(OutputStream outputStream) {
		setOutputWriter(new PrintWriter(outputStream));
		
		getOutputWriter().println("\t\t" + getName()); 		
		getOutputWriter().println("\t\t\t" + contents);		
		
		getOutputWriter().flush();
	}
	
	public String toString() {	
		return "		" + getName() + "-Part :  " + contents + "\n" ;
	}
	
}
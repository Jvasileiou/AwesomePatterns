package compositePattern;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

//implements Cloneable
public class PatternComponent {

	private static PrintWriter outputWriter;
	private String name;
	
	public PatternComponent(String name) {	
		this.name = name ;
	}
	
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	
	public PrintWriter getOutputWriter() {
		return outputWriter;
	}
	
	public static void setOutputWriter(PrintWriter outputWriter) {
		PatternComponent.outputWriter = outputWriter;
	}
	
	public void saveName(OutputStream outputStream) {
		setOutputWriter(new PrintWriter(outputStream));
		
		
		if(!(getName().equals(null) || getName().equals(""))) 
		{
		
			if(this.getClass() == Pattern.class) 
			{
				getOutputWriter().println("\tPattern Name"); 		// 1 tab 
				getOutputWriter().println("\t   " + getName());		// 1 tab + 2 spaces
			}else
			{
				getOutputWriter().println("PatternLanguage Name");	
				getOutputWriter().println("  " + getName());		// 2 spaces
			}
		}	
				
		getOutputWriter().flush();
	}

	
	public ArrayList<PatternComponent> getComponentsList() {
		throw new UnsupportedOperationException();	}
	
	public String getContents() {
		throw new UnsupportedOperationException();
	}
	
	public void setContents(String contents) {
		throw new UnsupportedOperationException();
	}
	
	public void setContents(String contents, int index) {
		throw new UnsupportedOperationException();
	}

	public void saveContents(OutputStream fileName) {
		throw new UnsupportedOperationException();
	}
	
	public void add(PatternComponent patternComponent) {	
		throw new UnsupportedOperationException();
	}
	
	public void set(int i,PatternComponent patternComponent) {
		throw new UnsupportedOperationException();
	}
	
	public void remove(String patternComponentTitle) {	
		throw new UnsupportedOperationException(); 		
	}
	
	public FileInputStream getInputStream() {
		throw new UnsupportedOperationException();
	}
	
	public FileOutputStream getOutputStream() {
		throw new UnsupportedOperationException();
	}
	
	public int getSizeArrayList() {
		throw new UnsupportedOperationException();
	}
	
	public PatternComponent getChild(int i) {
		throw new UnsupportedOperationException();
	}
	
	public void createPathToWrite(String path) {
		throw new UnsupportedOperationException();
	}
	
	public void createPathToRead(String path) {
		throw new UnsupportedOperationException();
	}
	
	public boolean loadPatternLanguage(InputStream inputStream) {
		throw new UnsupportedOperationException();
	}
}


























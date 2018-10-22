package compositePattern;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class PatternComposite extends PatternComponent{

	private DecoratorAbstractFactory tempDecorator; 
	
	private ArrayList<PatternComponent> componentsList;
	private FileOutputStream outputStream = null;
	private FileInputStream inputStream = null;
	private Scanner inputReader;
	private String path = "" ;
	
	public PatternComposite(String name) {
		super(name);
		
		setComponentsList(new ArrayList<>());	
	}
	
	public void createPathToWrite(String path) {
		
		this.setPath(path) ;
		
		try
		{
			// Write
			setOutputStream(new FileOutputStream(path));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error opening the file.");
			System.exit(0);
		}
	}
	
	public void createPathToRead(String path) {	
		try
		{
			// Read
			setInputStream(new FileInputStream(path));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error opening the file.");
			System.exit(0);
		}
	}
	
	public void add(PatternComponent patternComponent) {
		componentsList.add(patternComponent);
	}
	
	public void set(int i,PatternComponent patternComponent) {
		componentsList.set(i,patternComponent);
	}

	// or remove(){	 notify("");  }
	public void remove(Pattern patternRemoved) {	
		if(patternRemoved == null)
		{
			Play.notifyRemovedPattern();
			return ;
		}
		componentsList.remove(patternRemoved);
	}
		
	public PatternComponent getChild(int index) {
		if(getSizeArrayList() > index)
			return componentsList.get(index);
		
		return null;
	}
		
	public void setContents(String contents, int index) {
		componentsList.get(index).setContents(contents);
	}
	
	public void saveContents(OutputStream outputStream) {
		setOutputWriter(new PrintWriter(outputStream));
		 
		saveName(outputStream);
		
		Iterator<PatternComponent> patternIterator = componentsList.iterator();
		while(patternIterator.hasNext()) {
			PatternComponent patternDescription = (PatternComponent) patternIterator.next();
			patternDescription.saveContents(outputStream);
		}

		getOutputWriter().flush();
	}
	
	public void decorateComponents(DecoratorAbstractFactory decoratorFactory){
		// The first pattern of the list
		int pos = 0;
		
		tempDecorator = decoratorFactory;
		
		// Iterator the componentsList
		Iterator<PatternComponent> patternIterator = componentsList.iterator();
		while(patternIterator.hasNext()) {
			
			// Take a pattern element
			PatternComponent pattern = (PatternComponent) patternIterator.next();
			
			// Call decorateComponents method
			((Pattern) pattern).decorateComponents(tempDecorator);
			
			// Create the new decorated pattern
			PatternComponent patternDecorated = tempDecorator.createPatternDecorator( pattern );
			
			// Replace pattern with Decorated pattern
			set(pos++ , patternDecorated) ;
		}
			
	}
		
	public String toString() {	
		String res = "";
		
		if(!(getName().equals(null) || getName().equals(""))) 
		{
			if(this.getClass() == Pattern.class) 
			{
				res += "	Pattern :  " + getName() + "\n" ;
			}else
			{
				res += "PatternLanguage :  " + getName() + "\n" ;
			}	
		}
		
		Iterator<PatternComponent> patternIterator = componentsList.iterator();
		while(patternIterator.hasNext()) {
			PatternComponent patternDescription = (PatternComponent) patternIterator.next();
			 res += patternDescription.toString();
		}
		
		return res;
	}
	
	public boolean loadPatternLanguage(InputStream inputStream) {
		// Common
		boolean isPatternLanguage = false;
		boolean isPattern = false;
		boolean isTemplate = false;
		boolean isRightStructure = false;
		// For Micro 
		boolean isProblemMi = false;
		boolean isSolutionMi = false;
		// For Inductive
		boolean isContextIn = false;
		boolean isForcesIn = false;
		boolean isSolutionIn = false;
		// For Deductive
		boolean isProblemDe = false;
		boolean isSolutionDe = false;
		boolean isBenefitsDe = false;
		boolean isConsequencesDe = false;
		// For Gang of Four Pattern
		boolean isPatternClassificationGa = false;
		boolean isIntentGa = false;
		boolean isAlsoKnownAsGa = false;
		boolean isMotivationGa = false;
		boolean isApplicabilityGa = false;
		boolean isStructureGa = false;
		boolean isParticipantsGa = false;
		boolean isCollaborationsGa = false;
		boolean isConsequencesGa = false;
		boolean isImplementationGa = false;
		boolean isSampleCodeGa = false;
		boolean isKnownUsesGa = false;
		boolean isRelatedPatternsGa = false;
		// For System of Patterns
		boolean isAlsoKnownAsSy = false;
		boolean isExampleSy = false;
		boolean isContextSy = false;
		boolean isProblemSy = false;
		boolean isSolutionSy = false;
		boolean isStructureSy = false;
		boolean isDynamicsSy = false;
		boolean isImplementationSy = false;
		boolean isExampleResolvedSy = false;
		boolean isVariantsSy = false;
		boolean isKnownUsesSy = false;
		boolean isConsequencesSy = false;
			
		int counter = 0; // Read pattern language name
	
		String namePatternLanguage = getName();
		
		PatternComponent currentPattern = null;
		
		setInputReader(new Scanner(inputStream));
		
		while(getInputReader().hasNextLine()) {
			isRightStructure = false;
			
			// remove the "white characters" from the start and the ending of line
			String line =  getInputReader().nextLine();
			line = line.trim(); 

			if(line.equals(null)) 
				return false;
			
			if(counter == 0) { // Read pattern language name
				if(isPatternLanguage) {
					
					if(line.equals(namePatternLanguage)) { 
						counter++;
					}else {
						return false;
					}
					isPatternLanguage = false;
				}
				
				if(line.equals("PatternLanguage Name"))
					isPatternLanguage = true;
			
			}else if(counter == 1){ // Read pattern name
				if(isPattern) {
					String namePattern = line;
					currentPattern = new Pattern(namePattern);
					
					//Add it in to array list
					add(currentPattern);
					
					counter++;
				}
				
				if(line.equals("Pattern Name")) 
					isPattern = true;
			}else if(counter == 2) { // Read template
				
				if(currentPattern == null) {
					return false;
				}
					
				
				if(isTemplate) {
					String template = line;
					if(template.equals("Micro Pattern"))
					{
						currentPattern.add(new PatternPart("Template",	"Micro Pattern"));
						counter = 3 ; // means Micro Pattern
					}
					else if(template.equals("Inductive Mini Pattern"))
					{
						currentPattern.add(new PatternPart("Template",	"Inductive Mini Pattern"));
						counter = 5 ; // means Inductive Pattern
					}
					else if(template.equals("Deductive Mini Pattern"))
					{
						currentPattern.add(new PatternPart("Template",	"Deductive Mini Pattern"));
						counter = 8 ; // means Deductive Pattern
					}
					else if(template.equals("Gang of Four Pattern"))
					{
						currentPattern.add(new PatternPart("Template",	"Gang of Four Pattern"));
						counter = 12 ; // means Gang of Four Pattern
					}
					else if(template.equals("System of Patterns"))
					{
						currentPattern.add(new PatternPart("Template",	"System of Patterns"));
						counter = 25 ; // means System of Patterns
					}
					else 
					{
						return false;
					}
				}		
				
				if(line.equals("Template"))
					isTemplate = true;
			} // Micro Pattern #######################################
			else if(counter == 3) { // Problem
				if(isProblemMi) {
					currentPattern.add(new PatternPart("Problem", line ));
					counter++;
				}
				
				if(line.equals("Problem"))
					isProblemMi = true;
			}else if(counter == 4) { // Solution
				if(isSolutionMi) {
					currentPattern.add(new PatternPart("Solution", line ));
					// fix the variables for looping
					counter    = 1 ;
					isPattern  = false;
					
					isTemplate = false;
					isProblemMi  = false;
					isSolutionMi = false;
					isRightStructure = true;
				}
				
				if(line.equals("Solution"))
					isSolutionMi = true;
			} // Inductive Pattern ##################################
			else if(counter == 5){
				if(isContextIn) {
					currentPattern.add(new PatternPart("Context", line ));
					counter++;
				}
				
				if(line.equals("Context"))
					isContextIn = true;
			}else if(counter == 6) {
				if(isForcesIn) {
					currentPattern.add(new PatternPart("Forces", line ));
					counter++;
				}
				
				if(line.equals("Forces"))
					isForcesIn = true;
			}else if(counter == 7) {
				if(isSolutionIn) {
					currentPattern.add(new PatternPart("Solution", line ));
					// fix the variables for looping
					counter = 1;
					isPattern = false;
					
					isTemplate = false;
					isContextIn = false;
					isForcesIn = false;
					isSolutionIn = false;
					isRightStructure = true;			
				}
				
				if(line.equals("Solution")) 					
					isSolutionIn = true;					
			} // Deductive Pattern ##################################
			else if(counter == 8) {
				if(isProblemDe) {
					currentPattern.add(new PatternPart("Problem", line ));
					counter++;
				}
				
				if(line.equals("Problem"))
					isProblemDe = true;
			}else if(counter == 9) {
				if(isSolutionDe) {
					currentPattern.add(new PatternPart("Solution", line ));
					counter++;
				}
				
				if(line.equals("Solution"))
					isSolutionDe = true;
			}else if(counter == 10) {
				if(isBenefitsDe) {
					currentPattern.add(new PatternPart("Benefits", line ));
					counter++;
				}
				
				if(line.equals("Benefits"))
					isBenefitsDe = true;
			}else if(counter == 11) {
				if(isConsequencesDe) {
					currentPattern.add(new PatternPart("Consequences", line ));
					// fix the variables for looping
					counter = 1;
					isPattern = false;
					
					isTemplate = false;
					isProblemDe = false;
					isSolutionDe = false;
					isBenefitsDe = false;
					isConsequencesDe = false;
					isRightStructure = true;	
				}
				
				if(line.equals("Consequences"))
					isConsequencesDe = true;
			} // Gang of Four Pattern ###################################
			else if(counter == 12) {
				if(isPatternClassificationGa) {
					currentPattern.add(new PatternPart("Pattern Classification", line ));
					counter++;
				}
				
				if(line.equals("Pattern Classification"))
					isPatternClassificationGa = true;
			}else if(counter == 13) {
				if(isIntentGa) {
					currentPattern.add(new PatternPart("Intent", line ));
					counter++;
				}
				
				if(line.equals("Intent"))
					isIntentGa = true;
			}else if(counter == 14) {
				if(isAlsoKnownAsGa) {
					currentPattern.add(new PatternPart("Also Known As", line ));
					counter++;
				}
				
				if(line.equals("Also Known As"))
					isAlsoKnownAsGa = true;
			}else if(counter == 15) {
				if(isMotivationGa) {
					currentPattern.add(new PatternPart("Motivation", line ));
					counter++;
				}
				
				if(line.equals("Motivation"))
					isMotivationGa = true;
			}else if(counter == 16) {
				if(isApplicabilityGa) {
					currentPattern.add(new PatternPart("Applicability", line ));
					counter++;
				}
				
				if(line.equals("Applicability"))
					isApplicabilityGa = true;
			}else if(counter == 17) {
				if(isStructureGa) {
					currentPattern.add(new PatternPart("Structure", line ));
					counter++;
				}
				
				if(line.equals("Structure"))
					isStructureGa = true;
			}else if(counter == 18) {
				if(isParticipantsGa) {
					currentPattern.add(new PatternPart("Participants", line ));
					counter++;
				}
				
				if(line.equals("Participants"))
					isParticipantsGa = true;
			}else if(counter == 19) {
				if(isCollaborationsGa) {
					currentPattern.add(new PatternPart("Collaborations", line ));
					counter++;
				}
				
				if(line.equals("Collaborations"))
					isCollaborationsGa = true;
			}else if(counter == 20) {
				if(isConsequencesGa) {
					currentPattern.add(new PatternPart("Consequences", line ));
					counter++;
				}
				
				if(line.equals("Consequences"))
					isConsequencesGa = true;
			}else if(counter == 21) {
				if(isImplementationGa) {
					currentPattern.add(new PatternPart("Implementation", line ));
					counter++;
				}
				
				if(line.equals("Implementation"))
					isImplementationGa = true;
			}else if(counter == 22) {
				if(isSampleCodeGa) {
					currentPattern.add(new PatternPart("Sample Code", line ));
					counter++;
				}
				
				if(line.equals("Sample Code"))
					isSampleCodeGa = true;
			}else if(counter == 23) {
				if(isKnownUsesGa) {
					currentPattern.add(new PatternPart("Known Uses", line ));
					counter++;
				}
				
				if(line.equals("Known Uses"))
					isKnownUsesGa = true;
			}else if(counter == 24) {
				if(isRelatedPatternsGa) {
					currentPattern.add(new PatternPart("Related Patterns", line ));
					// fix the variables for looping
					counter = 1;
					isPattern = false;
					
					isTemplate = false;
					isPatternClassificationGa = false;
					isIntentGa = false;
					isAlsoKnownAsGa = false;
					isMotivationGa = false;
					isApplicabilityGa = false;
					isStructureGa = false;
					isParticipantsGa = false;
					isCollaborationsGa = false;
					isConsequencesGa = false;
					isImplementationGa = false;
					isSampleCodeGa = false;
					isKnownUsesGa = false;
					isRelatedPatternsGa = false;
					isRightStructure = true;
				}
				
				if(line.equals("Related Patterns"))
					isRelatedPatternsGa = true;
			}// System of Patterns ############################################
			else if(counter == 25) {
				if(isAlsoKnownAsSy) {
					currentPattern.add(new PatternPart("Also Known As", line ));
					counter++;
				}
				
				if(line.equals("Also Known As"))
					isAlsoKnownAsSy = true;
			}else if(counter == 26) {
				if(isExampleSy) {
					currentPattern.add(new PatternPart("Example", line ));
					counter++;
				}
				
				if(line.equals("Example"))
					isExampleSy = true;
			}else if(counter == 27) {
				if(isContextSy) {
					currentPattern.add(new PatternPart("Context", line ));
					counter++;
				}
				
				if(line.equals("Context"))
					isContextSy = true;
			}else if(counter == 28) {
				if(isProblemSy) {
					currentPattern.add(new PatternPart("Problem", line ));
					counter++;
				}
				
				if(line.equals("Problem"))
					isProblemSy = true;
			}else if(counter == 29) {
				if(isSolutionSy) {
					currentPattern.add(new PatternPart("Solution", line ));
					counter++;
				}
				
				if(line.equals("Solution"))
					isSolutionSy = true;
			}else if(counter == 30) {
				if(isStructureSy) {
					currentPattern.add(new PatternPart("Structure", line ));
					counter++;
				}
				
				if(line.equals("Structure"))
					isStructureSy = true;
			}else if(counter == 31) {
				if(isDynamicsSy) {
					currentPattern.add(new PatternPart("Dynamics", line ));
					counter++;
				}
				
				if(line.equals("Dynamics"))
					isDynamicsSy = true;
			}else if(counter == 32) {
				if(isImplementationSy) {
					currentPattern.add(new PatternPart("Implementation", line ));
					counter++;
				}
				
				if(line.equals("Implementation"))
					isImplementationSy = true;
			}else if(counter == 33) {
				if(isExampleResolvedSy) {
					currentPattern.add(new PatternPart("Example Resolved", line ));
					counter++;
				}
				
				if(line.equals("Example Resolved"))
					isExampleResolvedSy = true;
			}else if(counter == 34) {
				if(isVariantsSy) {
					currentPattern.add(new PatternPart("Variants", line ));
					counter++;
				}
				
				if(line.equals("Variants"))
					isVariantsSy = true;
			}else if(counter == 35) {
				if(isKnownUsesSy) {
					currentPattern.add(new PatternPart("Known Uses", line ));
					counter++;
				}
				
				if(line.equals("Known Uses"))
					isKnownUsesSy = true;
			}else if(counter == 36) {
				if(isConsequencesSy) {
					currentPattern.add(new PatternPart("Consequences", line ));
					// fix the variables for looping
					counter = 1;
					isPattern = false;
					
					isTemplate = false;
					isAlsoKnownAsSy = false;
					isExampleSy = false;
					isContextSy = false;
					isProblemSy = false;
					isSolutionSy = false;
					isStructureSy = false;
					isDynamicsSy = false;
					isImplementationSy = false;
					isExampleResolvedSy = false;
					isVariantsSy = false;
					isKnownUsesSy = false;
					isConsequencesSy = false;
					isRightStructure = true;
				}
				
				if(line.equals("Consequences"))
					isConsequencesSy = true;
			}
		}
	
		if(isRightStructure)
			return true;
		
		return false;
	}
	
	public int getSizeArrayList() { 
		return componentsList.size() ;
	}
	
	// ----------------------------------------------------------------
	// Getter and Setter
	public ArrayList<PatternComponent> getComponentsList() {		return componentsList; 	}
	
	public void setComponentsList(ArrayList<PatternComponent> componentsList) {		this.componentsList = componentsList;	}
	
	public FileOutputStream getOutputStream() {		return outputStream;	}

	public void setOutputStream(FileOutputStream outputStream) {		this.outputStream = outputStream;	}

	public FileInputStream getInputStream() {		return inputStream; 	}

	public void setInputStream(FileInputStream inputStream) {		this.inputStream = inputStream;	}

	public Scanner getInputReader() {		return inputReader;		}
	
	public void setInputReader(Scanner inputReader) {		this.inputReader = inputReader;	}

	public String getPath() {		return path;	}

	public void setPath(String path) {		this.path = path;	}
	
}
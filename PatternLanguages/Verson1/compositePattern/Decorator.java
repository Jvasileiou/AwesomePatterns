package compositePattern;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Decorator extends PatternComposite{
		
	private String beginTag = "";
	private String endTag 	= "";
	private String contents = null ;
			
	public Decorator(String name, String beginTag ,String endTag ) {
		super(name);
	
		this.setBeginTag(beginTag);
		this.setEndTag(endTag);
	}
	
	public Decorator(String name, String contents ,  String beginTag ,  String endTag ) {
		super(name);
		
		this.contents = contents;
		this.setBeginTag(beginTag);
		this.setEndTag(endTag);
	}
	
	public void saveName(OutputStream outputStream) {
		setOutputWriter(new PrintWriter(outputStream));
		
		if(!(getName().equals(null) || getName().equals(""))) 
		{ 	
			getOutputWriter().println( getBeginTag() + getName() + getEndTag() );		
			getOutputWriter().println("");
			
			if(contents != null) 
			{
				// It is Decorated PatternPart
				getOutputWriter().println("\t\t" + getContents() );	
				getOutputWriter().println("");
			}
		}	
				
		getOutputWriter().flush();
	}

	public void saveContents(OutputStream outputStream) {
		setOutputWriter(new PrintWriter(outputStream));
		 
		// Save name and contents of Decorated PatternLanguage,Pattern,name and contents of Decorated PatternPart
		saveName(outputStream);
		
		// It is not PatternPart
		if(getSizeArrayList() != 0) 
		{
			// List of PatternLanguage
			Iterator<PatternComponent> patternIterator = getComponentsList().iterator();
			while(patternIterator.hasNext()) {
				//////////////////////////////////////////////////////////////////////////////////////////////////////
				// Decorated Pattern and patternPart
				PatternComponent decoratedElement = patternIterator.next();
				//////////////////////////////////////////////////////////////////////////////// remove casting
				decoratedElement.saveContents(outputStream);
			}
		}
		getOutputWriter().flush();
	}
	
	public boolean loadPatternLanguage(InputStream inputStream) 
	{
		// Common
		String namePattern = "";
		String template = "";
		// For Micro Pattern
		String problemMi = 	"";
		String solutionMi = "";
		// For Inductive Pattern
		String contextIn = "";
		String forcesIn = "";
		String solutionIn = "";
		// For Deductive  Pattern
		String problemDe = "";
		String solutionDe = "";
		String benefitsDe = "";
		String consequencesDe = "";
		// For Gang of 4 Pattern
		String patternClassificationGa = "";
		String intentGa = "";
		String alsoKnownAsGa = "";
		String motivationGa = "";
		String applicabilityGa = "";
		String structureGa = "";
		String participantsGa = "";
		String collaborationsGa = "";
		String consequencesGa = "";
		String implementationGa = "";
		String sampleCodeGa = "";
		String knownUsesGa = "";
		String relatedPatternsGa = "";
		// For System of Patterns
		String alsoKnownAsSy = "";
		String exampleSy = "";
		String contextSy = "";	
		String problemSy = "";
		String solutionSy = "";
		String structureSy = "";
		String dynamicsSy = "";
		String implementationSy = "";
		String exampleResolvedSy = "";	
		String variantsSy = "";
		String knownUsesSy = "";
		String consequencesSy = "";
		
		
		boolean isRightStructure = false;
	
		int counter = 1 ; // count lines
		int numberOfPatterns = 0;
		
		PatternComponent currentPattern = null;
		String namePatternLanguage = getName();
		String line = "";
		
		setInputReader(new Scanner(inputStream));
	
		
		while(getInputReader().hasNextLine()) {
			
			line =  getInputReader().nextLine().trim();
			
			////// End ###################################
			if(line.toString().equals("\\end{document}")) {
				if(isRightStructure) {
					return true;
				}else {
					return false;
				}
			}
								
			isRightStructure = false ;
			
			if(counter < 7) {
				// Do nothing
			}else if(counter == 7)
			{
				line =  line.trim().toString();
				
				// Split meta character
				String[] partsOne = line.split("\\{");
				String[] partsTwo = partsOne[1].split("}");
						
				String name = partsTwo[0];
				if(name == null || !(namePatternLanguage.equals(name))) {
					return false;
				}
				
			}else {
			
				String[] partsOne = line.split("\\{");
				
				// Pattern ####################################################
				if(partsOne[0].equals("\\section")) {
					
					String[] partsTwo = partsOne[1].split("}");
					
					namePattern = partsTwo[0];
					currentPattern = new Pattern(namePattern);
					
					//Add it in to array list
					add(currentPattern);
					
					line =  getInputReader().nextLine().trim();
					line =  getInputReader().nextLine().trim();
					
					String[] parts  = line.split("\\{")  ;
					String[] parts2 = parts[1].split("}"); 
					template = parts2[0]; 
					
					// Template ##################################################
					if(template.equals("Template")) {
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
						
						if(line.equals("Micro Pattern")) 
						{
							currentPattern.add(new PatternPart("Template",	"Micro Pattern"));
							numberOfPatterns = 1 ; // means Micro Pattern
						}
						else if(line.equals("Inductive Mini Pattern"))
						{
							currentPattern.add(new PatternPart("Template",	"Inductive Mini Pattern"));
							numberOfPatterns = 2 ; 
						}
						else if(line.equals("Deductive Mini Pattern"))
						{
							currentPattern.add(new PatternPart("Template",	"Deductive Mini Pattern"));
							numberOfPatterns = 3 ; 
						}
						else if(line.equals("Gang of Four Pattern"))
						{
							currentPattern.add(new PatternPart("Template",	"Gang of Four Pattern"));
							numberOfPatterns = 4 ; 
						}
						else if(line.equals("System of Patterns"))
						{
							currentPattern.add(new PatternPart("Template",	"System of Patterns"));
							numberOfPatterns = 5 ;
						}
					}	
					line =  getInputReader().nextLine().trim();
					line =  getInputReader().nextLine().trim();
				}
				partsOne[0] = "";			
				
				// Micro Pattern Template #############################################
				if(numberOfPatterns == 1) 
				{
					int i = 0;
					String[] parts  = line.split("\\{")  ;
					String[] parts2 = parts[1].split("}");
					problemMi = parts2[0];
					
					String[] partsOfMicro = { "Problem" , "Solution" } ;
							
					if(problemMi.equals(partsOfMicro[i])) {
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
						
						currentPattern.add(new PatternPart("Problem", line ));
						
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
					}else {
						return false;
					}
					
					i++;
					parts  = line.split("\\{") ;
					parts2 = parts[1].split("}");
					solutionMi = parts2[0];
					
					if(solutionMi.equals(partsOfMicro[i])) {
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
						
						currentPattern.add(new PatternPart("Solution", line ));
						
						line =  getInputReader().nextLine().trim();
							
						isRightStructure = true;
						numberOfPatterns = 0 ;
					}else {
						return false;
					}
				}
				
				
				// Inductive Pattern Template #############################################
				if(numberOfPatterns == 2) 
				{
					int i = 0;
					String[] parts  = line.split("\\{")  ;
					String[] parts2 = parts[1].split("}");
					contextIn = parts2[0];
					
					String[] partsOfInductive = { "Context" , "Forces", "Solution" } ;
							
					if(contextIn.equals(partsOfInductive[i])) {
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
						
						currentPattern.add(new PatternPart("Context", line ));
						
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
					}else {
						return false;
					}
					
					i++;
					parts  = line.split("\\{") ;
					parts2 = parts[1].split("}");
					forcesIn = parts2[0];
										
					if(forcesIn.equals(partsOfInductive[i])) {
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
						
						currentPattern.add(new PatternPart("Forces", line ));
						
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
					}else {
						return false;
					}
					
					i++;
					parts  = line.split("\\{") ;
					parts2 = parts[1].split("}");
					solutionIn = parts2[0];
					
					if(solutionIn.equals(partsOfInductive[i])) {
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
						
						currentPattern.add(new PatternPart("Solution", line ));
						
						line =  getInputReader().nextLine().trim();
							
						isRightStructure = true;
						numberOfPatterns = 0 ;
					}else {
						return false;
					}
				}
				
				
				// Deductive Pattern Template #############################################
				if(numberOfPatterns == 3) 
				{
					int i = 0;
					String[] parts  = line.split("\\{")  ;
					String[] parts2 = parts[1].split("}");
					problemDe = parts2[0];
					
					String[] partsOfDeductive = { "Problem" , "Solution", "Benefits" , "Consequences"} ;
							
					if(problemDe.equals(partsOfDeductive[i])) {
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
						
						currentPattern.add(new PatternPart("Problem", line ));
						
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
					}else {
						return false;
					}
					
					i++;
					parts  = line.split("\\{") ;
					parts2 = parts[1].split("}");
					solutionDe = parts2[0];
										
					if(solutionDe.equals(partsOfDeductive[i])) {
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
						
						currentPattern.add(new PatternPart("Solution", line ));
						
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
					}else {
						return false;
					}
					
					i++;
					parts  = line.split("\\{") ;
					parts2 = parts[1].split("}");
					benefitsDe = parts2[0];
										
					if(benefitsDe.equals(partsOfDeductive[i])) {
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
						
						currentPattern.add(new PatternPart("Benefits", line ));
						
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
					}else {
						return false;
					}
					
					i++;
					parts  = line.split("\\{") ;
					parts2 = parts[1].split("}");
					consequencesDe = parts2[0];
					
					if(consequencesDe.equals(partsOfDeductive[i])) {
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
						
						currentPattern.add(new PatternPart("Consequences", line ));
						
						line =  getInputReader().nextLine().trim();
							
						isRightStructure = true;
						numberOfPatterns = 0 ;
					}else {
						return false;
					}
				}
				
				
				
				// Gang of 4 Pattern Template #############################################
				if(numberOfPatterns == 4) 
				{
					int i = 0;
					String[] parts  = line.split("\\{")  ;
					String[] parts2 = parts[1].split("}");
					patternClassificationGa = parts2[0];
					
					String[] partsOfGang = 
						{ 
							"Pattern Classification" , "Intent"
							,"Also Known As" , "Motivation"
							,"Applicability" , "Structure" , "Participants"
							,"Collaborations" , "Consequences" , "Implementation"
							, "Sample Code" , "Known Uses" , "Related Patterns" 
						} ;
							
					if(patternClassificationGa.equals(partsOfGang[i])) {
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
						
						currentPattern.add(new PatternPart("Pattern Classification", line ));
						
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
					}else {
						return false;
					}
					
					i++;
					parts  = line.split("\\{") ;
					parts2 = parts[1].split("}");
					intentGa = parts2[0];
										
					if(intentGa.equals(partsOfGang[i])) {
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
						
						currentPattern.add(new PatternPart("Intent", line ));
						
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
					}else {
						return false;
					}
					
					i++;
					parts  = line.split("\\{") ;
					parts2 = parts[1].split("}");
					alsoKnownAsGa = parts2[0];
										
					if(alsoKnownAsGa.equals(partsOfGang[i])) {
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
						
						currentPattern.add(new PatternPart("Also Known As", line ));
						
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
					}else {
						return false;
					}
					
					i++;
					parts  = line.split("\\{") ;
					parts2 = parts[1].split("}");
					motivationGa = parts2[0];
										
					if(motivationGa.equals(partsOfGang[i])) {
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
						
						currentPattern.add(new PatternPart("Motivation", line ));
						
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
					}else {
						return false;
					}
					
					i++;
					parts  = line.split("\\{") ;
					parts2 = parts[1].split("}");
					applicabilityGa = parts2[0];
										
					if(applicabilityGa.equals(partsOfGang[i])) {
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
						
						currentPattern.add(new PatternPart("Applicability", line ));
						
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
					}else {
						return false;
					}
					
					i++;
					parts  = line.split("\\{") ;
					parts2 = parts[1].split("}");
					structureGa = parts2[0];
										
					if(structureGa.equals(partsOfGang[i])) {
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
						
						currentPattern.add(new PatternPart("Structure", line ));
						
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
					}else {
						return false;
					}
					
					
					i++;
					parts  = line.split("\\{") ;
					parts2 = parts[1].split("}");
					participantsGa = parts2[0];
										
					if(participantsGa.equals(partsOfGang[i])) {
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
						
						currentPattern.add(new PatternPart("Participants", line ));
						
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
					}else {
						return false;
					}
					
					i++;
					parts  = line.split("\\{") ;
					parts2 = parts[1].split("}");
					collaborationsGa = parts2[0];
										
					if(collaborationsGa.equals(partsOfGang[i])) {
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
						
						currentPattern.add(new PatternPart("Collaborations", line ));
						
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
					}else {
						return false;
					}
					
					i++;
					parts  = line.split("\\{") ;
					parts2 = parts[1].split("}");
					consequencesGa = parts2[0];
										
					if(consequencesGa.equals(partsOfGang[i])) {
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
						
						currentPattern.add(new PatternPart("Consequences", line ));
						
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
					}else {
						return false;
					}
					
					i++;
					parts  = line.split("\\{") ;
					parts2 = parts[1].split("}");
					implementationGa = parts2[0];
										
					if(implementationGa.equals(partsOfGang[i])) {
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
						
						currentPattern.add(new PatternPart("Implementation", line ));
						
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
					}else {
						return false;
					}
					
					i++;
					parts  = line.split("\\{") ;
					parts2 = parts[1].split("}");
					sampleCodeGa = parts2[0];
										
					if(sampleCodeGa.equals(partsOfGang[i])) {
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
						
						currentPattern.add(new PatternPart("Sample Code", line ));
						
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
					}else {
						return false;
					}
								
					i++;
					parts  = line.split("\\{") ;
					parts2 = parts[1].split("}");
					knownUsesGa = parts2[0];
										
					if(knownUsesGa.equals(partsOfGang[i])) {
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
						
						currentPattern.add(new PatternPart("Known Uses", line ));
						
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
					}else {
						return false;
					}
					
					/// Last ----------------------------------------------
					i++;
					parts  = line.split("\\{") ;
					parts2 = parts[1].split("}");
					relatedPatternsGa = parts2[0];
					
					if(relatedPatternsGa.equals(partsOfGang[i])) {
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
						
						currentPattern.add(new PatternPart("Related Patterns", line ));
						
						line =  getInputReader().nextLine().trim();
							
						isRightStructure = true;
						numberOfPatterns = 0 ;
					}else {
						return false;
					}
				}
				
				// System of Patterns Template #############################################
				if(numberOfPatterns == 4) 
				{
					int i = 0;
					String[] parts  = line.split("\\{")  ;
					String[] parts2 = parts[1].split("}");
					alsoKnownAsSy = parts2[0];
					
					String[] partsOfSystem = 
						{ 
							"Also Known As" , "Example"
							,"Context" , "Problem"
							,"Solution" , "Structure" , "Dynamics"
							,"Implementation" , "Example Resolved" , "Variants"
							, "Known Uses" , "Consequences" 
						} ;
							
					if(alsoKnownAsSy.equals(partsOfSystem[i])) {
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
						
						currentPattern.add(new PatternPart("Also Known As", line ));
						
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
					}else {
						return false;
					}
					
					i++;
					parts  = line.split("\\{") ;
					parts2 = parts[1].split("}");
					exampleSy = parts2[0];
										
					if(exampleSy.equals(partsOfSystem[i])) {
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
						
						currentPattern.add(new PatternPart("Example", line ));
						
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
					}else {
						return false;
					}
					
					i++;
					parts  = line.split("\\{") ;
					parts2 = parts[1].split("}");
					contextSy = parts2[0];
										
					if(contextSy.equals(partsOfSystem[i])) {
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
						
						currentPattern.add(new PatternPart("Context", line ));
						
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
					}else {
						return false;
					}
					
					i++;
					parts  = line.split("\\{") ;
					parts2 = parts[1].split("}");
					problemSy = parts2[0];
										
					if(problemSy.equals(partsOfSystem[i])) {
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
						
						currentPattern.add(new PatternPart("Problem", line ));
						
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
					}else {
						return false;
					}
					
					i++;
					parts  = line.split("\\{") ;
					parts2 = parts[1].split("}");
					solutionSy = parts2[0];
										
					if(solutionSy.equals(partsOfSystem[i])) {
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
						
						currentPattern.add(new PatternPart("Solution", line ));
						
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
					}else {
						return false;
					}
					
					i++;
					parts  = line.split("\\{") ;
					parts2 = parts[1].split("}");
					structureSy = parts2[0];
										
					if(structureSy.equals(partsOfSystem[i])) {
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
						
						currentPattern.add(new PatternPart("Structure", line ));
						
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
					}else {
						return false;
					}
					
					
					i++;
					parts  = line.split("\\{") ;
					parts2 = parts[1].split("}");
					dynamicsSy = parts2[0];
										
					if(dynamicsSy.equals(partsOfSystem[i])) {
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
						
						currentPattern.add(new PatternPart("Dynamics", line ));
						
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
					}else {
						return false;
					}
					
					i++;
					parts  = line.split("\\{") ;
					parts2 = parts[1].split("}");
					implementationSy = parts2[0];
										
					if(implementationSy.equals(partsOfSystem[i])) {
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
						
						currentPattern.add(new PatternPart("Implementation", line ));
						
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
					}else {
						return false;
					}
					
					i++;
					parts  = line.split("\\{") ;
					parts2 = parts[1].split("}");
					exampleResolvedSy = parts2[0];
										
					if(exampleResolvedSy.equals(partsOfSystem[i])) {
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
						
						currentPattern.add(new PatternPart("Example Resolved", line ));
						
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
					}else {
						return false;
					}
					
					i++;
					parts  = line.split("\\{") ;
					parts2 = parts[1].split("}");
					variantsSy = parts2[0];
										
					if(variantsSy.equals(partsOfSystem[i])) {
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
						
						currentPattern.add(new PatternPart("Variants", line ));
						
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
					}else {
						return false;
					}
					
					i++;
					parts  = line.split("\\{") ;
					parts2 = parts[1].split("}");
					knownUsesSy = parts2[0];
										
					if(knownUsesSy.equals(partsOfSystem[i])) {
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
						
						currentPattern.add(new PatternPart("Known Uses", line ));
						
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
					}else {
						return false;
					}
					
					/// Last ----------------------------------------------
					i++;
					parts  = line.split("\\{") ;
					parts2 = parts[1].split("}");
					consequencesSy = parts2[0];
					
					if(consequencesSy.equals(partsOfSystem[i])) {
						line =  getInputReader().nextLine().trim();
						line =  getInputReader().nextLine().trim();
						
						currentPattern.add(new PatternPart("Consequences", line ));
						
						line =  getInputReader().nextLine().trim();
							
						isRightStructure = true;
						numberOfPatterns = 0 ;
					}else {
						return false;
					}	
				}
				
	/////////////////////////////////////////////////			
			}
			counter++;
		}
		
		return false;
	}
	
	public void copyArraylist(PatternComponent object) {
		
		ArrayList<PatternComponent> arrayList = getComponentsList();
		
		Iterator<PatternComponent> iterator = object.getComponentsList().iterator();
		while( iterator.hasNext()) 
		{
			PatternComponent decoratedElement =  iterator.next();
			
			arrayList.add(decoratedElement);
		}
		
	}

	public String getContents() {
		return contents; 
	}
	
	public String getEndTag() {
		return endTag;
	}

	public void setEndTag(String endTag) {
		this.endTag = endTag;
	}

	public String getBeginTag() {
		return beginTag;
	}

	public void setBeginTag(String beginTag) {
		this.beginTag = beginTag;
	}

	// @Override
	// public void decorateComponents(DecoratorAbstractFactory decoratorFactory) {}
	
}










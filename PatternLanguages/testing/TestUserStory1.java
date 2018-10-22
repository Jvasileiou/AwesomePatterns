package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import compositePattern.*;

class TestUserStory1 {

	private static Play objectMain = new Play();
	
	@BeforeAll
	public static void initialize() 
	{		
		PatternComponent patternLanguageOne;
		PatternComponent patternLanguageTwo;
		PatternComponent patternLanguageThree;
		
		String namePLOne 	= "Car";			// Micro 		Pattern 	Template
		String namePLTwo 	= "";	    		// Inductive 	Pattern 	Template
		String namePLThree 	= "";			    // Deductive 	Pattern 	Template
		
		patternLanguageOne   = objectMain.createPatternLanguage(namePLOne);
		patternLanguageTwo   = objectMain.createPatternLanguage(namePLTwo);
		patternLanguageThree = objectMain.createPatternLanguage( namePLThree );
				
		PatternComponent patternOne = objectMain.createPattern("MicroPattern");
		PatternComponent patternTwo = objectMain.createPattern("InductiveMiniPattern");
		PatternComponent patternThree = objectMain.createPattern("DeductiveMiniPattern");
				
		patternOne.setName("Windows");
		patternTwo.setName("Wheels");
		patternThree.setName("Name of a Pattern");
				
		patternLanguageOne.add(patternOne) ;	
		patternLanguageTwo.add(patternTwo) ;
		patternLanguageThree.add(patternThree) ;
	}
	
	@Test
	void testNamesOfPatternLanguages()
	{
		PatternComponent patternLanguage = objectMain.getPatternLanguages().get("Car") ;
		assertEquals("Car" , patternLanguage.getName() );
		
		patternLanguage = objectMain.getPatternLanguages().get("PatternLanguage(0)") ;
		assertEquals("PatternLanguage(0)" , patternLanguage.getName() );
		
		patternLanguage = objectMain.getPatternLanguages().get("PatternLanguage(1)") ;
		assertEquals("PatternLanguage(1)" , patternLanguage.getName() );
	}
	
	@Test
	void testConstructionMicro() 
	{
		PatternComponent patternLanguage = objectMain.getPatternLanguages().get("Car");
		Iterator<PatternComponent> patternIterator = patternLanguage.getComponentsList().iterator();
		while(patternIterator.hasNext())
		{
			PatternComponent pattern = (PatternComponent) patternIterator.next();
			assertEquals("Windows" 	, pattern.getName());
			assertEquals("Template" , pattern.getComponentsList().get(0).getName());
			assertEquals("Problem" 	, pattern.getComponentsList().get(1).getName());
			assertEquals("Solution" , pattern.getComponentsList().get(2).getName());
		}
		
	}
	
	@Test
	void testConstructionInductive() {
		PatternComponent patternLanguage = objectMain.getPatternLanguages().get("PatternLanguage(0)");
		Iterator<PatternComponent> patternIterator = patternLanguage.getComponentsList().iterator();
		while(patternIterator.hasNext())
		{
			PatternComponent pattern = (PatternComponent) patternIterator.next();
			assertEquals("Wheels" 	, pattern.getName());
			assertEquals("Template" , pattern.getComponentsList().get(0).getName());
			assertEquals("Context" 	, pattern.getComponentsList().get(1).getName());
			assertEquals("Forces"   , pattern.getComponentsList().get(2).getName());
			assertEquals("Solution" , pattern.getComponentsList().get(3).getName());
		}
	}
	
	@Test
	void testConstructionDeductive() {
		PatternComponent patternLanguage = objectMain.getPatternLanguages().get("PatternLanguage(1)");
		Iterator<PatternComponent> patternIterator = patternLanguage.getComponentsList().iterator();
		while(patternIterator.hasNext())
		{
			PatternComponent pattern = (PatternComponent) patternIterator.next();
			assertEquals("Name of a Pattern" 	, pattern.getName());
			assertEquals("Template" , pattern.getComponentsList().get(0).getName());
			assertEquals("Problem" 	, pattern.getComponentsList().get(1).getName());
			assertEquals("Solution" , pattern.getComponentsList().get(2).getName());
			assertEquals("Benefits" , pattern.getComponentsList().get(3).getName());
			assertEquals("Consequences" , pattern.getComponentsList().get(4).getName());
		}
	}
	

	
}

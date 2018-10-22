package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import compositePattern.PatternComponent;
import compositePattern.Play;
import compositePattern.TemplateFactory;

class TestUserStory2 {

	private static Play objectMain = new Play();
	private static TemplateFactory templateFactory = new TemplateFactory();
	private static PatternComponent patternLanguage;
	private static PatternComponent patternOne;
	private static PatternComponent patternTwo;
	private static PatternComponent patternThree;

	
	@BeforeAll
	public static void initialize() 
	{
		String namePLOne 	= "Car";			
		
		patternLanguage   = objectMain.createPatternLanguage(namePLOne);
			
		
		// #### createTemplate() ####
		patternOne   = templateFactory.createTemplate( "" );  		// Default is Micro Template
		patternTwo   = templateFactory.createTemplate("GangOfFourPattern");
		patternThree = templateFactory.createTemplate("SystemOfPatterns");
				
		patternOne.setName("Windows");
		patternTwo.setName("Wheels");
		patternThree.setName("Helmet");
				
		// #### ADD() NETHOD ######
		patternLanguage.add(patternOne) ;
		patternLanguage.add(patternTwo) ;
	}
	
	// TEST ADD() method
	
	@Test
	void testNumberOfPatterns() {
	
		assertEquals(2 , patternLanguage.getSizeArrayList() ) ;
		
		patternLanguage.add(patternThree) ;
		assertEquals(3 , patternLanguage.getSizeArrayList() ) ;
	}

	@Test
	void testPatterns() {
		Iterator<PatternComponent> patternIterator = patternLanguage.getComponentsList().iterator();
		while(patternIterator.hasNext())
		{
			
			PatternComponent pattern = (PatternComponent) patternIterator.next();
			if(pattern.getName().equals("Windows")) {
				assertEquals("Template" , pattern.getComponentsList().get(0).getName());
				assertEquals("Problem" 	, pattern.getComponentsList().get(1).getName());
				assertEquals("Solution" , pattern.getComponentsList().get(2).getName());
			}
			else if (pattern.getName().equals("Wheels"))
			{
				assertEquals("Template" , pattern.getComponentsList().get(0).getName());
				assertEquals("Pattern Classification" 	, pattern.getComponentsList().get(1).getName());
				assertEquals("Intent" , pattern.getComponentsList().get(2).getName());
				assertEquals("Also Known As" , pattern.getComponentsList().get(3).getName());
				assertEquals("Motivation" , pattern.getComponentsList().get(4).getName());
				assertEquals("Applicability" , pattern.getComponentsList().get(5).getName());
				assertEquals("Structure" , pattern.getComponentsList().get(6).getName());
				assertEquals("Participants" , pattern.getComponentsList().get(7).getName());
				assertEquals("Collaborations" , pattern.getComponentsList().get(8).getName());
				assertEquals("Consequences" , pattern.getComponentsList().get(9).getName());
				assertEquals("Implementation" , pattern.getComponentsList().get(10).getName());
				assertEquals("Sample Code" , pattern.getComponentsList().get(11).getName());
				assertEquals("Known Uses" , pattern.getComponentsList().get(12).getName());
				assertEquals("Related Patterns" , pattern.getComponentsList().get(13).getName());
			}		
		}
		
	}
	
}

package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import compositePattern.PatternComponent;
import compositePattern.Play;
import compositePattern.TemplateFactory;

class TestUserStory4 {

	private static Play objectMain = new Play();
	private static TemplateFactory templateFactory = new TemplateFactory();
	private static PatternComponent patternLanguage;
	private static PatternComponent patternOne;
	private static PatternComponent patternTwo;

	
	@BeforeAll
	public static void initialize() 
	{
		String namePLOne 	= "Car";			
		
		patternLanguage   = objectMain.createPatternLanguage(namePLOne);
				
		// #### createTemplate() ####
		patternOne   = templateFactory.createTemplate("");  	
		patternTwo   = templateFactory.createTemplate("DeductiveMiniPattern");
				
		patternOne.setName("Windows");
		patternTwo.setName("Wheels");
				
		// #### ADD() NETHOD ######
		patternLanguage.add(patternOne) ;
		patternLanguage.add(patternTwo) ;	
		
		objectMain.getPatternLanguages().put(namePLOne, patternLanguage);
	}
	
	// TEST contents() method
	
	@Test
	void testContents() {

		PatternComponent patternLanguage = objectMain.getPatternLanguages().get("Car");
		
		PatternComponent pattern = patternLanguage.getChild(0);
		
		// Micro Pattern Template
		pattern.setContents("Micro Pattern" , 0);
		pattern.setContents("first word"  	, 1);
		pattern.setContents("second word" 	, 2);

		assertEquals("Micro Pattern", pattern.getComponentsList().get(0).getContents());
		assertEquals("first word" 	, pattern.getComponentsList().get(1).getContents());
		assertEquals("second word" 	, pattern.getComponentsList().get(2).getContents());
		
		pattern = patternLanguage.getChild(1);
		
		// Deductive Mini Pattern Template
		pattern.setContents("Micro Pattern" , 0);
		pattern.setContents("first word"  	, 1);
		pattern.setContents("second word" 	, 2);
		pattern.setContents("3rd word" 	    , 3);
		pattern.setContents("4th word" 	    , 4);
		
		assertEquals("Micro Pattern", pattern.getComponentsList().get(0).getContents());
		assertEquals("first word" 	, pattern.getComponentsList().get(1).getContents());
		assertEquals("second word" 	, pattern.getComponentsList().get(2).getContents());
		assertEquals("3rd word"		, pattern.getComponentsList().get(3).getContents());
		assertEquals("4th word" 	, pattern.getComponentsList().get(4).getContents());
		
	}

}

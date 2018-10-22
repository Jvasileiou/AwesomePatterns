package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import compositePattern.PatternComponent;
import compositePattern.PatternComposite;
import compositePattern.Pattern;
import compositePattern.Play;
import compositePattern.TemplateFactory;

class TestUserStory3 {

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
		patternLanguage.add(patternThree) ;
		
		objectMain.getPatternLanguages().put(namePLOne, patternLanguage);
	}
	
	// TEST REMOVE() METHOD
	@Test
	void testNumberOfPatterns()
	{	
		assertEquals(3 , patternLanguage.getSizeArrayList() ) ;
		
		objectMain.removePattern((PatternComposite) patternLanguage, (Pattern) patternOne);
		assertEquals(2 , patternLanguage.getSizeArrayList() ) ;
		
		objectMain.removePattern((PatternComposite) patternLanguage, (Pattern) patternTwo);
		assertEquals(1 , patternLanguage.getSizeArrayList() ) ;
		
		objectMain.removePattern((PatternComposite) patternLanguage, (Pattern) patternThree);
		assertEquals(0 , patternLanguage.getSizeArrayList() ) ;
	}
	
}

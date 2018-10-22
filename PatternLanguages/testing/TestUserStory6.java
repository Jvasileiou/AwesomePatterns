package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import compositePattern.PatternComponent;
import compositePattern.PatternComposite;
import compositePattern.Play;
import compositePattern.TemplateFactory;

class TestUserStory6 {

	private static Play objectMain = new Play();
	private static TemplateFactory templateFactory = new TemplateFactory();
	private static PatternComponent patternLanguage;
	private static PatternComponent newPatternLanguage;
	private static PatternComponent patternOne;
	private static PatternComponent patternTwo;
	private static String namePLOne = "Motorbike";	
	
	@BeforeAll
	public static void initialize() 
	{		
		patternLanguage   = objectMain.createPatternLanguage(namePLOne);
		
		// #### createTemplate() ####
		patternOne   = templateFactory.createTemplate( "" );  		// Default is Micro Template
		patternTwo   = templateFactory.createTemplate("DeductiveMiniPattern");
				
		patternOne.setName("Windows");
		patternOne.setContents("Micro Pattern"  , 0);
		patternOne.setContents("first word"  	, 1);
		patternOne.setContents("second word" 	, 2);
		
		patternTwo.setName("Wheels");
		patternTwo.setContents("Deductive Mini Pattern" , 0);
		patternTwo.setContents("third word"  	, 1);
		patternTwo.setContents("fourth word" 	, 2);
		patternTwo.setContents("fifth word" 	, 3);
		patternTwo.setContents("sixth word" 	, 4);
				
		patternLanguage.add(patternOne) ;
		patternLanguage.add(patternTwo) ;

		objectMain.getPatternLanguages().put(namePLOne, patternLanguage);
				
		patternLanguage.createPathToWrite("user.dir" + namePLOne + ".txt");
		
		objectMain.savePatternLanguage(patternLanguage);
		
		// Remove pattern language from the hash map
		objectMain.getPatternLanguages().remove(namePLOne);
				
		newPatternLanguage = new PatternComposite(namePLOne);

		newPatternLanguage.createPathToRead("user.dir" + namePLOne + ".txt");
		
		if(objectMain.loadContentsOfPatternLanguage(newPatternLanguage)) {	

		}
			
	}
	
	// After load method called, hash_map has the pattern language we expected
	@Test
	void testHasHashMapThePatternLang() 
	{
		assertEquals("Motorbike" , newPatternLanguage.getName());
	}
	
	@Test
	void testNumberOfPatterns() 
	{
		assertEquals(2 , newPatternLanguage.getSizeArrayList());
	}
	
	@Test
	void testPatterns() 
	{
		assertEquals("Windows" , newPatternLanguage.getChild(0).getName() );
		assertEquals("Wheels"  , newPatternLanguage.getChild(1).getName() );
	}
	
	@Test
	void testStructureOfFirstPattern() 
	{
		PatternComponent pattern = newPatternLanguage.getChild(0) ;	
		assertEquals("Micro Pattern", pattern.getComponentsList().get(0).getContents());
		assertEquals("first word" 	, pattern.getComponentsList().get(1).getContents());
		assertEquals("second word" 	, pattern.getComponentsList().get(2).getContents());
	}
	
	@Test
	void testStructureOfSecondPattern() 
	{
		PatternComponent pattern = newPatternLanguage.getChild(1) ;	
		assertEquals("Deductive Mini Pattern", pattern.getComponentsList().get(0).getContents());
		assertEquals("third word" 	, pattern.getComponentsList().get(1).getContents());
		assertEquals("fourth word" 	, pattern.getComponentsList().get(2).getContents());
		assertEquals("fifth word" 	, pattern.getComponentsList().get(3).getContents());
		assertEquals("sixth word" 	, pattern.getComponentsList().get(4).getContents());
	}

}

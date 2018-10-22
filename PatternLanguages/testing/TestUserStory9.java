package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import compositePattern.Decorator;
import compositePattern.Pattern;
import compositePattern.PatternComponent;
import compositePattern.PatternComposite;
import compositePattern.Play;
import compositePattern.TemplateFactory;

class TestUserStory9 {

	private static Play objectMain = new Play();
	private static TemplateFactory templateFactory = new TemplateFactory();
	private static PatternComponent patternLanguage;
	private static PatternComponent patternLanguageDecorated;
	private static PatternComponent newPatternLanguage;	
	private static PatternComponent patternOne;
	private static String namePLOne = "Truck";
	private static String beginTagPL = "\\documentclass{article}\r\n" + "\\begin{document}\r\n" + "\\title{";
	private static String endTagPL = "\\maketitle";
	
	@BeforeAll
	public static void initialize() 
	{		
		patternLanguage   = objectMain.createPatternLanguage(namePLOne);
		
		// #### createTemplate() ####
		patternOne   = templateFactory.createTemplate("InductiveMiniPattern");  
				
		patternOne.setName("Windows");
		patternOne.setContents("Inductive Mini Pattern" , 0);
		patternOne.setContents("first word"  			, 1);
		patternOne.setContents("second word" 			, 2);
		patternOne.setContents("3rd word" 				, 3);
			
		// Add 
		patternLanguage.add(patternOne) ;
		
		objectMain.getPatternLanguages().put(namePLOne, patternLanguage);
		
		// Decorate a pattern language as latex
		objectMain.decoratePatternLanguage( patternLanguage );
				
		// Take the decorated pattern language
		patternLanguageDecorated = objectMain.getPatternLanguages().get(namePLOne) ;
		
		patternLanguageDecorated.createPathToWrite("user.dir" + namePLOne + ".tex");
		objectMain.savePatternLanguage(patternLanguageDecorated);
		
		// Remove pattern language from the hash map
		objectMain.getPatternLanguages().remove(namePLOne);
				
		newPatternLanguage = new Decorator(namePLOne , beginTagPL , endTagPL );

		newPatternLanguage.createPathToRead("user.dir" + namePLOne + ".tex");
		if(objectMain.loadContentsOfPatternLanguage(newPatternLanguage)) {}
	}
	
	@Test
	void testClassOfPatternLanguage() 
	{	
		patternLanguageDecorated = objectMain.getPatternLanguages().get(namePLOne) ;
		assertEquals(Decorator.class , patternLanguageDecorated.getClass() );
		
		assertEquals(Pattern.class , patternLanguageDecorated.getChild(0).getClass() );
	}
	
	// After load method called, hash_map has the pattern language we expected
	@Test
	void testHasHashMapTheDecoratedPatternLang() 
	{
		assertEquals("Truck" 		, newPatternLanguage.getName());
		assertEquals(beginTagPL     , ((Decorator) newPatternLanguage).getBeginTag());
		assertEquals(endTagPL    	, ((Decorator) newPatternLanguage).getEndTag());
	}
	
	@Test
	void testNumberOfPattern() 
	{
		assertEquals(1 , newPatternLanguage.getSizeArrayList());
	}	
	
	@Test
	void testPattern() 
	{
		assertEquals("1st Windows"  	,  newPatternLanguage.getChild(0).getName() );
	}
	
	@Test
	void testPatternParts() 
	{
		String beginTagPatternPart = "\t\t\\subsection{" ;
		String endTagPatternPart = "}" ;
		assertEquals("Template"  				,  newPatternLanguage.getChild(0).getChild(0).getName() );
		assertEquals("Inductive Mini Pattern"  	,  newPatternLanguage.getChild(0).getChild(0).getContents());
		assertEquals("Context"  				,  newPatternLanguage.getChild(0).getChild(1).getName());
		assertEquals("first word"  				,  newPatternLanguage.getChild(0).getChild(1).getContents());
		assertEquals("Forces"  					,  newPatternLanguage.getChild(0).getChild(2).getName() );
		assertEquals("second word"  			,  newPatternLanguage.getChild(0).getChild(2).getContents());
		assertEquals("Solution"  				,  newPatternLanguage.getChild(0).getChild(3).getName());
		assertEquals("3rd word"  				,  newPatternLanguage.getChild(0).getChild(3).getContents());
	}
}

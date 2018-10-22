package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import compositePattern.Decorator;
import compositePattern.PatternComponent;
import compositePattern.PatternComposite;
import compositePattern.Play;
import compositePattern.TemplateFactory;

class TestUserStory7 {

	private static Play objectMain = new Play();
	private static TemplateFactory templateFactory = new TemplateFactory();
	private static PatternComponent patternLanguage;
	private static PatternComponent patternLanguageDecorated;
	private static PatternComponent patternOne;
	private static PatternComponent patternTwo;
	private static String namePLOne = "Motorbike";	
	
	@BeforeAll
	public static void initialize() 
	{		
		patternLanguage   = objectMain.createPatternLanguage(namePLOne);
		
		// #### createTemplate() ####
		patternOne   = templateFactory.createTemplate("MicroPattern" );  
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
			
		patternLanguageDecorated = objectMain.getPatternLanguages().get(namePLOne);
		
		// Decorate a pattern language as latex
		objectMain.decoratePatternLanguage( patternLanguageDecorated );
	}
	
	
	//  "Down Casting"
	@Test
	void testClassOfPatternLanguage() 
	{	
		patternLanguageDecorated = objectMain.getPatternLanguages().get(namePLOne) ;
		assertEquals(Decorator.class , patternLanguageDecorated.getClass() );
		
		PatternComponent pattern = patternLanguageDecorated.getChild(0);
		assertEquals(Decorator.class , pattern.getClass() );
		
		pattern = patternLanguageDecorated.getChild(1);
		assertEquals(Decorator.class , pattern.getClass() );	
	}
	
	// Contents 
	@Test
	void testContents() 
	{
		String beginTagOfPatternLAng = "\\documentclass{article}\n\n\n\n" +
						 "\\begin{document}\n\n" + "\\title{" ;
		String endTagOfPatternLAng  = "}\n\n\\maketitle" ;
		
		patternLanguageDecorated = objectMain.getPatternLanguages().get(namePLOne) ;
		String beginTagPL =	((Decorator) patternLanguageDecorated).getBeginTag();
		String endTagPL =	((Decorator) patternLanguageDecorated).getEndTag();
		
		assertEquals( beginTagOfPatternLAng ,  beginTagPL );	
		assertEquals( endTagOfPatternLAng 	,  endTagPL );
		
		Decorator pattern = (Decorator) patternLanguageDecorated.getChild(0);
		Decorator pattern2 = (Decorator) patternLanguageDecorated.getChild(1);
		
		assertEquals("Windows" , pattern.getName() );
		assertEquals("Wheels"  , pattern2.getName() );

		
		String beginTagPattern = pattern.getBeginTag() ;
		String endTagPattern   = pattern.getEndTag()	;
		
		String beginTagPattern2 = pattern2.getBeginTag() ;
		String endTagPattern2   = pattern2.getEndTag()	;
		
		assertEquals( "\\section{1st " 	, beginTagPattern );	
		assertEquals( "}" 				, endTagPattern );
		assertEquals( "\\section{2nd " 	, beginTagPattern2 );	
		assertEquals( "}" 				, endTagPattern2 );
		
		// Template
		Decorator patternPart = (Decorator) pattern.getChild(0);
		assertEquals( "Template" 		  ,patternPart.getName() 	);
		assertEquals( "Micro Pattern" 	  ,patternPart.getContents());
		assertEquals( "\t\t\\subsection{" ,patternPart.getBeginTag());
		assertEquals( "}" 				  ,patternPart.getEndTag() 	);
		
		// Problem
		patternPart = (Decorator) pattern.getChild(1);
		assertEquals( "Problem" 		 	,patternPart.getName() 	  );
		assertEquals( "first word"  		,patternPart.getContents());
		assertEquals( "\t\t\\subsection{"	,patternPart.getBeginTag());
		assertEquals( "}" 				 	,patternPart.getEndTag()  );
		
		// Solution
		patternPart = (Decorator) pattern.getChild(2);
		assertEquals( "Solution" 		 	,patternPart.getName() 	);
		assertEquals( "second word"  		,patternPart.getContents());
		assertEquals( "\t\t\\subsection{"	,patternPart.getBeginTag());
		assertEquals( "}" 				 	,patternPart.getEndTag() 	);
		
		// ######### Pattern Parts of 2nd Pattern  #############
		
		// Template
		Decorator patternPart2 = (Decorator) pattern2.getChild(0);
		assertEquals( "Template" 		  	   ,patternPart2.getName() 	);
		assertEquals( "Deductive Mini Pattern" ,patternPart2.getContents());
		assertEquals( "\t\t\\subsection{"      ,patternPart2.getBeginTag());
		assertEquals( "}" 				  	   ,patternPart2.getEndTag() 	);
		
		// Problem
		patternPart2 = (Decorator) pattern2.getChild(1);
		assertEquals( "Problem" 		 	,patternPart2.getName() 	  );
		assertEquals( "third word"  		,patternPart2.getContents());
		assertEquals( "\t\t\\subsection{"	,patternPart2.getBeginTag());
		assertEquals( "}" 				 	,patternPart2.getEndTag()  );
		
		// Solution
		patternPart2 = (Decorator) pattern2.getChild(2);
		assertEquals( "Solution" 		 	,patternPart2.getName() 	);
		assertEquals( "fourth word"  		,patternPart2.getContents());
		assertEquals( "\t\t\\subsection{"	,patternPart2.getBeginTag());
		assertEquals( "}" 				 	,patternPart2.getEndTag() 	);
		
		// Benefits
		patternPart2 = (Decorator) pattern2.getChild(3);
		assertEquals( "Benefits" 		 	,patternPart2.getName() 	  );
		assertEquals( "fifth word"  		,patternPart2.getContents());
		assertEquals( "\t\t\\subsection{"	,patternPart2.getBeginTag());
		assertEquals( "}" 				 	,patternPart2.getEndTag()  );
		
		// Consequences
		patternPart2 = (Decorator) pattern2.getChild(4);
		assertEquals( "Consequences" 		,patternPart2.getName() 	);
		assertEquals( "sixth word"  		,patternPart2.getContents());
		assertEquals( "\t\t\\subsection{"	,patternPart2.getBeginTag());
		assertEquals( "}" 				 	,patternPart2.getEndTag() 	);
		
	}

}

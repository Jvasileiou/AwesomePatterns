package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import compositePattern.PatternComponent;
import compositePattern.Play;
import compositePattern.TemplateFactory;

class TestUserStory8 {

	private static Play objectMain = new Play();
	private static TemplateFactory templateFactory = new TemplateFactory();
	private static PatternComponent patternLanguage;
	private static PatternComponent patternOne;
	private static String namePLOne = "Car";	
	
	@BeforeAll
	public static void initialize() 
	{		
		patternLanguage   = objectMain.createPatternLanguage(namePLOne);
		
		// #### createTemplate() ####
		patternOne   = templateFactory.createTemplate("DeductiveMiniPattern");  
		
		patternOne.setName("Windows");
		patternOne.setContents("Deductive Mini Pattern"  , 0);
		patternOne.setContents("first word"  	, 1);
		patternOne.setContents("second word" 	, 2);
		patternOne.setContents("3 word"  		, 3);
		patternOne.setContents("4 word" 		, 4);

		// #### ADD() NETHOD ######
		patternLanguage.add(patternOne) ;
		
		objectMain.getPatternLanguages().put(namePLOne, patternLanguage);
		
		objectMain.decoratePatternLanguage(patternLanguage);
		
		// Take the decorated pattern language
		PatternComponent patternLangDecorated = objectMain.getPatternLanguages().get(namePLOne);
		
		patternLangDecorated.createPathToWrite("user.dir" + namePLOne + ".tex");
				
		objectMain.savePatternLanguage(patternLangDecorated);
			
	}
	
	@Test
	void testSaveDecoratedPatternLanguage() 
	{ 	
		Scanner inputReader = null;
		 try
		 {
			 inputReader =
			  new Scanner(new FileInputStream("C:\\Users\\jvvas\\Desktop\\" + namePLOne + ".tex" ));
		 }
		 catch(FileNotFoundException e)
		 {
			 System.exit(0);
		 }
		 
		// 1st Line
		String line = inputReader.nextLine().toString().trim();
		assertEquals("\\documentclass{article}" , line );
		
		line = inputReader.nextLine();
		line = inputReader.nextLine();
		line = inputReader.nextLine();
		
		// 5th Line
		line = inputReader.nextLine().toString().trim();
		assertEquals("\\begin{document}" , line );
		
		line = inputReader.nextLine();
		
		line = inputReader.nextLine().toString().trim();
		assertEquals("\\title{Car}" , line );

		line = inputReader.nextLine();
		
		line = inputReader.nextLine().toString().trim();
		assertEquals("\\maketitle" , line );
		
		line = inputReader.nextLine();
		
		line = inputReader.nextLine().toString().trim();
		assertEquals("\\section{1st Windows}" , line );

		line = inputReader.nextLine();
		
		line = inputReader.nextLine().toString().trim();
		assertEquals("\\subsection{Template}" , line );
		
		line = inputReader.nextLine();
		
		line = inputReader.nextLine().toString().trim();
		assertEquals("Deductive Mini Pattern" , line );
			
		line = inputReader.nextLine();

		line = inputReader.nextLine().toString().trim();
		assertEquals("\\subsection{Problem}" , line );

		line = inputReader.nextLine();
		
		line = inputReader.nextLine().toString().trim();
		assertEquals("first word" , line );
		
		line = inputReader.nextLine();
		
		line = inputReader.nextLine().toString().trim();
		assertEquals("\\subsection{Solution}" , line );
		
		line = inputReader.nextLine();
		
		line = inputReader.nextLine().toString().trim();
		assertEquals("second word" , line );
			
		line = inputReader.nextLine();

		line = inputReader.nextLine().toString().trim();
		assertEquals("\\subsection{Benefits}" , line );
		
		line = inputReader.nextLine();
		
		line = inputReader.nextLine().toString().trim();
		assertEquals("3 word" , line );
		
		line = inputReader.nextLine();
		
		line = inputReader.nextLine().toString().trim();
		assertEquals("\\subsection{Consequences}" , line );
		
		line = inputReader.nextLine();
		
		line = inputReader.nextLine().toString().trim();
		assertEquals("4 word" , line );
		
		line = inputReader.nextLine();
		
		line = inputReader.nextLine().toString().trim();
		assertEquals("\\end{document}" , line );
	
	}

}

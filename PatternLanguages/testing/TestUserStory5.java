package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import compositePattern.PatternComponent;
import compositePattern.PatternComposite;
import compositePattern.Play;
import compositePattern.TemplateFactory;

class TestUserStory5 {

	private static Play objectMain = new Play();
	private static TemplateFactory templateFactory = new TemplateFactory();
	private static PatternComponent patternLanguage;
	private static PatternComponent patternOne;
	private static PatternComponent patternTwo;
	private static String namePLOne = "Car";	
	
	final String dir = System.getProperty("user.dir");

	
	@BeforeAll
	public static void initialize() 
	{		
		patternLanguage   = objectMain.createPatternLanguage(namePLOne);
		
		// #### createTemplate() ####
		patternOne   = templateFactory.createTemplate( "" );  		// Default is Micro Template
		patternTwo   = templateFactory.createTemplate("InductiveMiniPattern");
				
		patternOne.setName("Windows");
		patternOne.setContents("Micro Pattern"  , 0);
		patternOne.setContents("first word"  	, 1);
		patternOne.setContents("second word" 	, 2);
		
		patternTwo.setName("Wheels");
		patternTwo.setContents("Inductive Mini Pattern" , 0);
		patternTwo.setContents("third word"  	, 1);
		patternTwo.setContents("fourth word" 	, 2);
		patternTwo.setContents("fifth word" 	, 3);
				
		// #### ADD() NETHOD ######
		patternLanguage.add(patternOne) ;
		patternLanguage.add(patternTwo) ;
		
		objectMain.getPatternLanguages().put(namePLOne, patternLanguage);
		
		patternLanguage.createPathToWrite("user.dir" + namePLOne + ".txt");
		
		objectMain.savePatternLanguage(patternLanguage);
	}
	
	@Test
	void testSavePatternLanguage() 
	{ 	
		Scanner inputReader = null;
		 try
		 {
			 inputReader =
			  new Scanner(new FileInputStream("C:\\Users\\jvvas\\Desktop\\" + namePLOne + ".txt" ));
		 }
		 catch(FileNotFoundException e)
		 {
			 System.exit(0);
		 }
		 
		// 1st Line
		String line = inputReader.nextLine().toString().trim();
		assertEquals("PatternLanguage Name" , line );
		
		// 2nd Line - name of pattern Language
		line = inputReader.nextLine().toString().trim();
		assertEquals("Car" , line );
		
		// 3rd Line - name of pattern Language
		line = inputReader.nextLine().toString().trim();
		assertEquals("Pattern Name" , line );

		// 4th Line - name of 1st pattern
		line = inputReader.nextLine().toString().trim();
		assertEquals("Windows" , line );
		
		// 5th Line - template pattern
		
		/// CONTENTS ############
		line = inputReader.nextLine().toString().trim();
		assertEquals("Template" , line );
		
		line = inputReader.nextLine().toString().trim();
		assertEquals("Micro Pattern" , line );
		
		line = inputReader.nextLine().toString().trim();
		assertEquals("Problem" , line );
				
		line = inputReader.nextLine().toString().trim();
		assertEquals("first word" , line );

		line = inputReader.nextLine().toString().trim();
		assertEquals("Solution" , line );
		
		line = inputReader.nextLine().toString().trim();
		assertEquals("second word" , line );
			
		// 2nd Pattern 
		line = inputReader.nextLine().toString().trim();
		assertEquals("Pattern Name" , line );
		
		line = inputReader.nextLine().toString().trim();
		assertEquals("Wheels" , line );
		
		// CONTENTS #########
		line = inputReader.nextLine().toString().trim();
		assertEquals("Template" , line );
		
		line = inputReader.nextLine().toString().trim();
		assertEquals("Inductive Mini Pattern" , line );
		
		line = inputReader.nextLine().toString().trim();
		assertEquals("Context" , line );
		
		line = inputReader.nextLine().toString().trim();
		assertEquals("third word" , line );
		
		line = inputReader.nextLine().toString().trim();
		assertEquals("Forces" , line );
		
		line = inputReader.nextLine().toString().trim();
		assertEquals("fourth word" , line );
		
		line = inputReader.nextLine().toString().trim();
		assertEquals("Solution" , line );
		
		line = inputReader.nextLine().toString().trim();
		assertEquals("fifth word" , line );
	}

}

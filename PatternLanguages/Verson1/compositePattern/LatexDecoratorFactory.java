package compositePattern;

public class LatexDecoratorFactory implements DecoratorAbstractFactory{
	
	private static int counterPatterns = 1 ;
	
	@Override
	public PatternComponent createPatternLanguageDecorator(PatternComponent patternLanguage) {
		
		String oldPatternLanguage = patternLanguage.getName();
		String beginTag = "";
		String endTag = "";
		
		// Add the commands 
		beginTag = "\\documentclass{article}\n\n\n\n" + "\\begin{document}\n\n" + "\\title{";
		endTag = "}\n\n\\maketitle";
		
		Decorator patternLangDecorated = new Decorator( oldPatternLanguage , beginTag , endTag ) ;

		// Copy decorated elements of Array list
		patternLangDecorated.copyArraylist( patternLanguage );
		
		return patternLangDecorated;
	}

	@Override
	public PatternComponent createPatternDecorator(PatternComponent pattern) {
		
		String oldNamePattern = pattern.getName();
		String beginTag = "";
		String endTag = "";
		
		// Add the commands 
		endTag   = "}" ;
		
		if(counterPatterns == 1 ) {
			beginTag = "\\section{" + counterPatterns + "st "  ;
		}else if(counterPatterns == 2 ) {
			beginTag = "\\section{" + counterPatterns + "nd "  ;
		}else if(counterPatterns == 3 ) {
			beginTag = "\\section{" + counterPatterns + "rd "  ;
		}else {
			beginTag = "\\section{" + counterPatterns + "th "  ;
		}
		
		Decorator patternDecorated = new Decorator( oldNamePattern , beginTag , endTag ) ;
				
		// Copy decorated elements of Array list
		patternDecorated.copyArraylist(pattern);
				
		counterPatterns++;
		
		return patternDecorated;
	}

	@Override
	public PatternComponent createPatternPartDecorator( PatternComponent patternPart ) {
		
		String namePatternPart 		 = patternPart.getName();
		String contentsOfPatternPart = patternPart.getContents();
		String beginTag = "";
		String endTag = "";
		
		// Add the commands
		beginTag = "\t\t\\subsection{" ;
		endTag   = "}" ;
		
		Decorator patternPartDecorated = new Decorator( namePatternPart , contentsOfPatternPart , beginTag , endTag) ;

		return patternPartDecorated;
	}

}

package compositePattern;

public interface DecoratorAbstractFactory {

	public PatternComponent createPatternLanguageDecorator( PatternComponent patternLanguage ) ;
	
	public PatternComponent createPatternDecorator( PatternComponent pattern );
	
	public PatternComponent createPatternPartDecorator( PatternComponent patternPart );
	
}

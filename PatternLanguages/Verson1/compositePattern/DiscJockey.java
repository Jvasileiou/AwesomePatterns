package compositePattern;

public class DiscJockey {

	private PatternComponent patternObject;
	
	public DiscJockey(PatternComponent patternObject) {
		this.patternObject = patternObject;
	}
	
	
	public String displayInfoOfPatterns() {
		String result = "";
		
		result += patternObject.toString();
	
		//System.out.println(result);
		
		return result;
	}
}
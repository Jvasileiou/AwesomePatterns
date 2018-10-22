package compositePattern;

import java.util.ArrayList;
import java.util.Iterator;

public class Pattern extends PatternComposite{
	 
	public Pattern(String name) {
		super(name);	
	}

	public Pattern clone() {
		//clonePattern = (Pattern) super.clone();
		//Pattern clonePattern = new Pattern(getName());
			
		Pattern clonePattern = new Pattern("");
		
		ArrayList<PatternComponent> list = getComponentsList();
		
		for (int i = 0; i < list.size(); i++) 
			clonePattern.add(new PatternPart(list.get(i).getName(), list.get(i).getContents()));
			
		return clonePattern ;
	}
	
	public void setContents(int index, String contents){
		getComponentsList().get(index).setContents(contents);
	}
	
	@Override
	public void decorateComponents(DecoratorAbstractFactory decoratorFactory) {
		PatternComponent patternPartDecorated = null;
		int pos = 0;	
		
		// Iterator the componentsList (PatternParts)
		Iterator<PatternComponent> patternIterator = getComponentsList().iterator();
		while(patternIterator.hasNext()) {
			
			// Take a PatternPart element
			PatternComponent patternPart = (PatternComponent) patternIterator.next();
			
			// Create the new decorated patternPart
			patternPartDecorated = (Decorator) decoratorFactory.createPatternPartDecorator( patternPart );
					
			//Replace patternParts
			set(pos++ , patternPartDecorated );
		}		
	}
}






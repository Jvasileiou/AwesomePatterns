package compositePattern;

import java.util.HashMap;

public class TemplateFactory {

	private HashMap<String,Pattern> templatesMap = new HashMap<>();
	public  HashMap<String,Pattern> getTemplates(){		return templatesMap;	} 
	
	public TemplateFactory() {
		
		Pattern micro = new Pattern("MicroPattern");
		micro.add(new PatternPart("Template",	""));
		micro.add(new PatternPart("Problem",	""));
		micro.add(new PatternPart("Solution",	""));
		templatesMap.put("MicroPattern", micro);
		
		Pattern inductiveMini = new Pattern("InductiveMiniPattern");
		inductiveMini.add(new PatternPart("Template",	""));
		inductiveMini.add(new PatternPart("Context",	""));
		inductiveMini.add(new PatternPart("Forces",		""));
		inductiveMini.add(new PatternPart("Solution",	""));
		templatesMap.put("InductiveMiniPattern", inductiveMini);
		
		Pattern deductiveMini = new Pattern("DeductiveMiniPattern");
		deductiveMini.add(new PatternPart("Template",	""));
		deductiveMini.add(new PatternPart("Problem",	""));
		deductiveMini.add(new PatternPart("Solution",	""));
		deductiveMini.add(new PatternPart("Benefits",	""));
		deductiveMini.add(new PatternPart("Consequences",""));
		templatesMap.put("DeductiveMiniPattern", deductiveMini);
		
		Pattern gangOfFourPattern = new Pattern("GangOfFourPattern");
		gangOfFourPattern.add(new PatternPart("Template",		""));
		gangOfFourPattern.add(new PatternPart("Pattern Classification",	""));
		gangOfFourPattern.add(new PatternPart("Intent",			""));
		gangOfFourPattern.add(new PatternPart("Also Known As",	""));
		gangOfFourPattern.add(new PatternPart("Motivation",		""));
		gangOfFourPattern.add(new PatternPart("Applicability",	""));
		gangOfFourPattern.add(new PatternPart("Structure",		""));
		gangOfFourPattern.add(new PatternPart("Participants",	""));
		gangOfFourPattern.add(new PatternPart("Collaborations",	""));
		gangOfFourPattern.add(new PatternPart("Consequences",	""));
		gangOfFourPattern.add(new PatternPart("Implementation",	""));
		gangOfFourPattern.add(new PatternPart("Sample Code",	""));
		gangOfFourPattern.add(new PatternPart("Known Uses",		""));
		gangOfFourPattern.add(new PatternPart("Related Patterns",""));
		templatesMap.put("GangOfFourPattern", gangOfFourPattern);
		
		Pattern systemOfPatterns = new Pattern("SystemOfPatterns");
		systemOfPatterns.add(new PatternPart("Template",	""));
		systemOfPatterns.add(new PatternPart("Also Known As",""));
		systemOfPatterns.add(new PatternPart("Example",		""));
		systemOfPatterns.add(new PatternPart("Context",		""));
		systemOfPatterns.add(new PatternPart("Problem",		""));
		systemOfPatterns.add(new PatternPart("Solution",	""));
		systemOfPatterns.add(new PatternPart("Structure",	""));
		systemOfPatterns.add(new PatternPart("Dynamics",	""));
		systemOfPatterns.add(new PatternPart("Implementation",""));
		systemOfPatterns.add(new PatternPart("Example Resolved",""));
		systemOfPatterns.add(new PatternPart("Variants",	""));
		systemOfPatterns.add(new PatternPart("Known Uses",	""));
		systemOfPatterns.add(new PatternPart("Consequences",""));
		templatesMap.put("SystemOfPatterns", systemOfPatterns);

	}
	
	// [US2]
	public Pattern createTemplate(String templateName) {
		
		if(templateName.equals(null) || templateName.equals("")) 
		{
			Play. notifyDefaultTemplate();
			return templatesMap.get("MicroPattern");
		}	
			
		return templatesMap.get(templateName);
	}
	
}
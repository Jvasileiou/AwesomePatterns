package compositePattern;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;

public class Play 
{
	private static int  counter = 0 ;
	private static DecoratorAbstractFactory decoratorFactory  = new  LatexDecoratorFactory() ;
	private PatternComponent patternLangForSaveEndTag;
	private HashMap<String,PatternComponent> patternLanguages = new HashMap<>();
	private TemplateFactory templates = new TemplateFactory();
	
	public Play() {}
	
	public TemplateFactory getTemplates() {
		return templates;
	}
	
	public HashMap<String,PatternComponent> getPatternLanguages(){
		return patternLanguages;
	}	
	
	// [US1]
	public PatternComposite createPatternLanguage(String name)
	{
		PatternComponent patternLanguage = null;
		
		if(name.equals("")){
			name = "PatternLanguage(" + counter + ")" ;
			counter++;
		}
		
		patternLanguage = new PatternComposite(name);
			
		// add the new pattern language 
		patternLanguages.put(name,patternLanguage);
		
		return (PatternComposite) patternLanguage;
	}
	
	// [US2]
	public Pattern createPattern(String nameTemplate) {
	
		Pattern newPattern = templates.createTemplate(nameTemplate).clone();
		
		//Pattern newPattern = templates.createTempalte(nameTemplate) ;
		
		return newPattern;
	}
	
	// [US3]
	public void removePattern(PatternComposite patternLang, Pattern patternRemoved) {
		
		patternLang.remove(patternRemoved);
	}
			
	// [US5]
	public void savePatternLanguage(PatternComponent patternLanguage) {
	
		patternLangForSaveEndTag = patternLanguage ; 
		
		patternLanguage.saveContents(patternLanguage.getOutputStream());
		
		if(patternLanguage.getClass() == Decorator.class) 
			saveEndTag(patternLanguage.getOutputStream());	
	}
	
	// [US6]
	public boolean loadContentsOfPatternLanguage(PatternComponent patternLanguage) {
	
		String name = patternLanguage.getName();
		
		// There is with the same name
		if((patternLanguages.containsKey(name))) 
			return false;
				
		boolean isThere = patternLanguage.loadPatternLanguage(patternLanguage.getInputStream());
		
		if(isThere) {
			patternLanguages.put(name, patternLanguage);
			return true;
		}
		return false;
	}

	// [US7]
	public void decoratePatternLanguage(PatternComponent patternLanguage )
	{	
		((PatternComposite) patternLanguage).decorateComponents(decoratorFactory);
		
		String oldName = patternLanguage.getName();	
		
		// Change the PatternLanguage name as Decorated
		PatternComponent patternLangDecorated = (Decorator) decoratorFactory.createPatternLanguageDecorator(patternLanguage) ; 	

		// Remove patternLanguage
		patternLanguages.remove(oldName);
		
		// Add decorated patternLanguage
		patternLanguages.put( patternLangDecorated.getName() , patternLangDecorated);
		
	}

	public void saveEndTag(OutputStream outputStream) {
		patternLangForSaveEndTag.setOutputWriter(new PrintWriter(outputStream));

		patternLangForSaveEndTag.getOutputWriter().println("\\end{document}");		

		patternLangForSaveEndTag.getOutputWriter().flush();
	}
	
	public void showHashMap() {
		for(String name: patternLanguages.keySet()){
			
			System.out.println(name + ":"+ patternLanguages.get(name));
		}
	}
	
	// [US2]
	public static void notifyDefaultTemplate()
	{
		notify("Notification Message\n\n\n The default template of Pattern is Micro Pattern Template.");
	}
	
	// [US3]
	public static void notifyRemovedPattern()
	{
		notify("Notification Message\n\n\n Choose the Pattern you want to remove.");
	}
/*
	// [US5]
	public void checkPatternLangToSave(PatternComponent patternComponent) {
		PatternComponent currentPattern = null;
		boolean thereIs = false; // there is pattern
		
		for(int i = 0 ; i < patternComponent.getSizeArrayList() ; i++)
		{
			currentPattern = patternComponent.getChild(i);
			
			if(currentPattern.getClass() == Pattern.class) {
				thereIs = true;
				break;
			}
		}
		
		if(!thereIs)
			notify("Notification Message\n\n\nThe Pattern Language contains no pattern.");			
		
		patternComponent.saveContents(outputStream);
	}
*/	
	// Alert Box
	public static void notify(String message) {
		
	
	}

	public static DecoratorAbstractFactory getTempDecorator() {
		return decoratorFactory;
	}

	public static void setTempDecorator(DecoratorAbstractFactory decoratorFactory) {
		Play.decoratorFactory = decoratorFactory;
	}
	
	/*
	public static void main(String[] args){
		//-------------------------------
		
		// Create the file in which the data will be stored
		String path = "C://Users/jvvas/Desktop/fileName.txt";
		
		try
		{
			outputStream = new FileOutputStream(path,true);
			inputStream = new FileInputStream(path);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error opening the file.");
			System.exit(0);
		}
		
		///-------------------------------
		// Create PatternLanguages - PL-----------
		
		
		//PatternLanguages - PL----------
		PatternComponent car = new PatternComposite("Car"); 
		
		PatternComponent motorBike = new PatternComposite("MotorBike");
		
		PatternComponent truck = new PatternComposite("Truck");		
			
		// add patternLanguages
		patternLanguages.put("Car",car);
		patternLanguages.put("MotorBike", motorBike);
		patternLanguages.put("Truck", truck);
	
		
		
		PatternComponent wheelsMicro = new Pattern("");
		car.add(wheelsMicro);
		wheelsMicro.add(  templates.createTempalte("").clone()   );
				
		PatternComponent windowsInductive= new Pattern("Windows");
		car.add(windowsInductive);
		windowsInductive.add(templates.createTempalte("InductiveMiniPattern").clone());


		
		
		
		PatternComponent helmetDeductive = new Pattern("Helmet");
		motorBike.add(helmetDeductive);
		helmetDeductive.add(templates.createTempalte("DeductiveMiniPattern").clone());
		
		
		//Mia fora .getChild(0) dinei to name
		PatternComponent changedContents = motorBike.getChild(0).getChild(0);

		int size = changedContents.getSizeArrayList();
		for(int i = 1; i < size; i++) {
			changedContents.setContents("OKKKKK",i);
		}
		
	
		
		PatternComponent numberOfWheelsMicro = new Pattern("Number of Wheels");
		truck.add(numberOfWheelsMicro);
		numberOfWheelsMicro.add( templates.createTempalte("MicroPattern").clone() );
		
		PatternComponent numberOfWindowsSystem = new Pattern("Number of Windows");
		truck.add(numberOfWindowsSystem);
		numberOfWindowsSystem.add( templates.createTempalte("SystemOfPatterns").clone() );
		
		PatternComponent typeOfcargoMicro = new Pattern("Type of Cargo");
		truck.add(typeOfcargoMicro);
		typeOfcargoMicro.add(templates.createTempalte("MicroPattern").clone());
		
		
	
		car.saveContents(outputStream);
		motorBike.saveContents(outputStream);
		truck.saveContents(outputStream);
		
		//String y = loadPatternLanguage(inputStream, motorBike);
		
		
		
		
		//------------------------
	}
*/
	
		
}
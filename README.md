# PatternLanguages

Desktop App

### Purpose 

A software development pattern defines a general reusable solution to a commonly occurring software
development problem within a particular context. Patterns constitute a significant asset of the software
engineering community. Amongst the very first approaches we have the GoF design patterns catalog that
concerns best OO development practices. Then, there are also regular conferences (e.g. PLoP, EuroPLoP) that
take place for more than 20 years and whose main topic is the identification of new patterns and pattern
languages (the term pattern language is typically used to refer to a set of related patterns). Patterns are
formally specified in terms of pattern templates. So far, several pattern templates have been proposed in the
literature.
The main goal of this project is to develop a PatternsEditor, an application that makes pattern writting easier,
especially for young inexperienced pattern writers. At a glance, PatternsEditor shall allow a patterns writer to
prepare a new pattern based on well known templates change the structure of an existing pattern by switching
between these templates, and generate actual pattern documents in well known formats (simple text, Latex),
and so on.

### Testing 

#### Tests for User Story <1>
Test ID <TestUserStory1>
Class <Play ->PatternComposite.Class>
Test Class <PatternComponent.class>
Test Method <Play.createPatternLanguage()>
Description <The test shows how a pattern language is created >
#### Tests for User Story <2>
Test ID <TestUserStory2>
Class <Play ->PatternComposite.Class>&<Play ->TemplateFactory.class>
Test Class <PatternComponent.class>
Test Method <Play.createPattern()>
Description <The test shows how a pattern language add a pattern>
#### Tests for User Story <3>
Test ID <TestUserStory3>
Class <Play ->PatternComposite.Class>
Test Class <PatternComponent.class>
Test Method <Play.removePattern()>
Description <The test shows how a pattern language remove a pattern>
#### Tests for User Story <4>
Test ID <TestUserStory4>
Class <CompositePattern.class>
Test Class <PatternComponent.class>
Test Method <Play.setContents()>
Description <The test shows how a pattern update its contents>
#### Tests for User Story <5>
Test ID <TestUserStory5>
Class <Play ->CompositePattern.class>
Test Class <PatternComponent.class>
Test Method <Play.savePatternLanguage()>
Description <The user save a pattern language in a file with txt format>
#### Tests for User Story <6>
Test ID <TestUserStory6>
Class <Play ->CompositePattern.class>
Test Class <PatternComponent.class>
Test Method <Play.loadPatternLanguage()>
Description <The user load a pattern language from the disk>
#### Tests for User Story <7>
Test ID <TestUserStory7>
Class <Play ->CompositePattern.class>
Test Class <PatternComponent.class>
Test Method <Play.decoratePatternLanguage()>
Description <The user decorate a pattern language as Latex>
#### Tests for User Story <8>
Test ID <TestUserStory8>
Class <Play ->CompositePattern.class>
Test Class <PatternComponent.class>
Test Method <Play.saveatternLanguage()>
Description <The user save a pattern language in a file with tex format>
#### Tests for User Story <9>
Test ID <TestUserStory9>
Class <Play ->CompositePattern.class>
Test Class <PatternComponent.class>
Test Method <Play.saveatternLanguage()>
Description <The user load a pattern language from a disk>

### Author 

+ John Vasileiou

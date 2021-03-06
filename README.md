# FXSamples
Examples how to build apps using the JavaFX & TestFX APIs.
This project has exploratory :warning: character.
This project requires Java 1.8 JDK to compile (with test), package and run.

The idea for this project was born at the JavaFX for Business Applications workshop with Hendrik Ebbers. There is great software available, great frameworks for JavaFX but somehow often there is a lack of good examples.

**Goals:**
 * explore JavaFX concepts
 * discover and try out pattern to decouple GUI from business logic
 * explore concepts for automated testing of GUI functionality
 * implement everything in a Maven build
 * have everything runnable in headless mode to enable continuous builds
 * Later this project will be migrated to Java 9 
 
## Pocket Calculator
A typical business application consisting of multiple views organized in tabs.
The goal is here, to demonstrate how to decouple GUI logic from business logic. 
Furthermore TestFX capabilities for testing JavaFX controls are explored.

![Screenshot](pages/PocketCalculator.png)

Run to test, to build, to package:

On bash:

```cmd
	./mvnw test
	./mvnw jfx:jar
	./mvnw jfx:native
```

On Windows command line:

```cmd
	mvnw test
	mvnw jfx:jar
	mvnw jfx:native
```
 
 
## Resources 
 
**Good sources for JavaFX**
 * Hendrik Ebbers GUI Garage: http://www.guigarage.com
 * Code Makery (1): http://code.makery.ch/library/javafx-8-tutorial/
 * Code Makery (2): http://code.makery.ch/blog/javafx-dialogs-official/
 * ControlsFX: http://fxexperience.com/controlsfx/
 * FormsFX: https://www.slideshare.net/DirkLemmermann/introduction-to-formsfx
 * FXExperience: http://fxexperience.com
 * Comprehensive GUI testing for Vocabhunter: https://vocabhunter.github.io/2016/07/27/TestFX.html

**APIs being used:**
 * JavaFX: https://docs.oracle.com/javase/9/docs/api/overview-summary.html#JavaFX
 * JavaSE 8: Client: http://docs.oracle.com/javase/8/javase-clienttechnologies.htm
 * JavaSE 9: http://docs.oracle.com/javase/9/index.html
 * JavaFX CSS: http://docs.oracle.com/javase/8/javafx/api/javafx/scene/doc-files/cssref.html
 * TestFX: https://github.com/TestFX/TestFX
 * Apache Maven: https://github.com/apache/maven

 
## Other considerable points ##

 **Resources for Java 9 migration**
  * Oracle JDK9 Migration Guide: https://docs.oracle.com/javase/9/migrate/toc.htm
  * Open JDK Quick Start on Modularization: http://openjdk.java.net/projects/jigsaw/quick-start
  * How to configure Eclipse for Java9: https://wiki.eclipse.org/Configure_Eclipse_for_Java_9
  * DZone Guide on creating runtime images: https://dzone.com/articles/jdk9-howto-create-a-java-run-time-image-with-maven
  * **Blogs:**
    * Real World Java 9: https://trishagee.github.io/presentation/real_world_java_9/
    * https://blog.codecentric.de/en/2015/11/first-steps-with-java9-jigsaw-part-1/
    * https://blog.codefx.org/java/java-9-migration-guide/
    * http://www.baeldung.com/project-jigsaw-java-modularity
    * https://de.paperblog.com/java-9-erste-modul-generierung-modul-infojava-mit-eclipse-beta-for-oxygen-1320418/
    * https://www.sitepoint.com/ultimate-guide-to-java-9/
  * **Examples:**
  	 * JavaDeveloperZone: http://javadeveloperzone.com/java-9/java-9-module-example-using-maven/
     * Multi-Module-Maven: https://github.com/cfdobber/maven-java9-jigsaw 
  
 **Dependency Injection using @Inject**
  * http://www.javamagazine.mozaicreader.com/SeptOct2017/Twitter#&pageSet=54&page=0
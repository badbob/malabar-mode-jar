package com.software_ninja.malabar.lang;

import org.junit.Test;
import static org.junit.Assert.*; 

import com.software_ninja.malabar.lang.JavaParser;
import com.software_ninja.malabar.project.MavenProjectHandler;
 
public class TestJavaParser {

  String simple = 'src/test/resources/projects/simple/';
  String scriptIn = simple + '/src/test/java/com/software_ninja/test/project/AppTest.java';
  String pm = simple + "pom.xml";
  String repo = "~/.m2/repository";
  
  def mavenProjectHandler = new MavenProjectHandler([cache:[:]]);
  def cacheEntry = mavenProjectHandler.lookInCache( pm, { mavenProjectHandler.fecthProjectInfo(repo, pm)});
  
  
  def javaParser = cacheEntry['parsers']['java'];
 
  @Test
  public void testFileParser() throws Exception {
  

    assertEquals([], javaParser.parse(new File(scriptIn))['errors']);
    assertEquals("com.software_ninja.test.project.AppTest", javaParser.parse(new File(scriptIn))['class'].getName());
  }

}
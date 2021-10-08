# run-python-from-java

## exec-maven-plugin-test

Running Python file from Java with exec-maven-plugin, 
referencing [here](https://www.bswen.com/2018/04/maven-Execute-python-scripts-in-maven-projects.html).

### pom.xml

Add exec-maven-plugin into pom.xml plugins.

````
<plugin>
  <groupId>org.codehaus.mojo</groupId>
  <artifactId>exec-maven-plugin</artifactId>
  <version>1.6.0</version>
  <executions>
    <execution>
      <configuration>
        <executable>python</executable>
        <workingDirectory> ... </workingDirectory>
        <arguments>
          <argument> ... </argument>
        </arguments>
      </configuration>
      <id>python_build</id>
      <phase>generate-resources</phase>
      <goals>
        <goal>exec</goal>
      </goals>
    </execution>
  </executions>
</plugin>
````

Set `workingDirectory` to where you keep the Python files.
Then, set `argument` to the Python file name you want to run. 
Only one Python file can run at compile.
Finally, delete `pluginManagement` as it will prevent exec-maven-plugin to run.

### Run

Run the Python file with `mvn compile` from Maven tab.

Note : The result of `print()` can be seen at Run tab.
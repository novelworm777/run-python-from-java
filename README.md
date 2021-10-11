# run-python-from-java

## process-builder-test

Running Python file from Java with ProcessBuilder API,
referencing [here](https://www.baeldung.com/java-working-with-python).

### AppTest.java

This function is to get Python file path inside project.
The first ellipsis below can be replaced with root directory path or
the path to folders containing Python files.

````
private String resolvePythonScriptPath(String filename) {
    File file = new File( ... + filename);
    return file.getAbsolutePath();
}
````

This function is to read Python output(s) as a list.
Every output of Python no matter what the original data type is,
will be converted into String.

````
private List<String> readProcessOutput(InputStream inputStream) throws IOException {
    try (BufferedReader output = new BufferedReader(new InputStreamReader(inputStream))) {
        return output.lines()
            .collect(Collectors.toList());
    }
}
````

The first ellipsis bellow can be replaced with the name of Python file.
While the second ellipsis bellow can be replaced with the expected output of Python file as String.

````
@Test
public void givenPythonScript_whenPythonProcessInvoked_thenSuccess() throws Exception {
    ProcessBuilder processBuilder = new ProcessBuilder("python", resolvePythonScriptPath( ... ));
    processBuilder.redirectErrorStream(true);

    Process process = processBuilder.start();
    List<String> results = readProcessOutput(process.getInputStream());

    assertThat("Results should not be empty", results, is(not(empty())));
    assertThat("Results should contain output of script: ", results, hasItem(containsString( ... )));

    int exitCode = process.waitFor();
    assertEquals("No errors should be detected", 0, exitCode);
}
````

Add `System.out.println(results);` into the function to see the list of Python output(s).
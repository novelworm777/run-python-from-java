package org.test;

import org.junit.Test;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Optional.empty;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class AppTest 
{
    private String resolvePythonScriptPath(String filename) {
        File file = new File("src/test/resources/" + filename);
        return file.getAbsolutePath();
    }

    private List<String> readProcessOutput(InputStream inputStream) throws IOException {
        try (BufferedReader output = new BufferedReader(new InputStreamReader(inputStream))) {
            return output.lines().collect(Collectors.toList());
        }
    }

    @Test
    public void givenPythonScript_whenPythonProcessInvoked_thenSuccess() throws Exception {
        ProcessBuilder processBuilder = new ProcessBuilder("python", resolvePythonScriptPath("hello.py"));
        processBuilder.redirectErrorStream(true);

        Process process = processBuilder.start();
        List<String> results = readProcessOutput(process.getInputStream());

        assertThat("Results should not be empty", results, is(not(empty())));
        assertThat("Results should contain output of script: ", results, hasItem(containsString("Hello World!")));

        int exitCode = process.waitFor();
        assertEquals("No errors should be detected", 0, exitCode);
    }

    @Test
    public void testPrintNumber() throws Exception {
        ProcessBuilder processBuilder = new ProcessBuilder("python", resolvePythonScriptPath("number.py"));
        processBuilder.redirectErrorStream(true);

        Process process = processBuilder.start();
        List<String> results = readProcessOutput(process.getInputStream());

        assertThat("Results should not be empty", results, is(not(empty())));
        assertThat("Results should contain output of script: ", results, hasItem(containsString("3.33")));

        int exitCode = process.waitFor();
        assertEquals("No errors should be detected", 0, exitCode);
    }
}

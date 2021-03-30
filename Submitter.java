import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Peter Li
 * @version 12/16/2020
 */
public class Submitter {
    
    private final static String SUBMITTER_NAME = "Submitter.java";
    private final static String SERVER = "http://139.147.9.180/submit/";
    
    private static class Submission {
        private final String name;
        private final String password;
        private final ProblemSubmission[] submissions;
        
        public Submission(String name, String password, ProblemSubmission[] submissions) {
            this.name = name;
            this.password = password;
            this.submissions = submissions;
        }
        
        @Override
        public String toString() {
            return String.format(
                "{\"name\": \"%s\", \"password\": \"%s\", \"submissions\": %s}",
                this.name,
                this.password,
                Arrays.toString(submissions));
        }
    }
    
    private static class ProblemSubmission {
        private final String name;
        private final String code;
        
        public ProblemSubmission(String name, String code) {
            this.name = name;
            this.code = code;
        }
        
        @Override
        public String toString() {
            return String.format(
                "{\"name\": \"%s\", \"code\": \"%s\"}",
                this.name,
                escape(this.code));
        }
    }
    
    private static ProblemSubmission readProblem(File file) throws IllegalArgumentException {
        String name = file.getName();
        name = name.substring(0, name.indexOf(".java"));
        String code;
        try {
            code = new Scanner(file).useDelimiter("\\A").next();
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException();
        }
        return new ProblemSubmission(name, code);
    }
    
    private static ProblemSubmission[] readPrograms(File[] programs, int choice) {
        if (choice != programs.length) {
            return new ProblemSubmission[]{readProblem(programs[choice])};
        } else {
            return Arrays
                .stream(programs)
                .map(Submitter::readProblem)
                .toArray(ProblemSubmission[]::new);
        }
    }
    
    private static String escape(String raw) {
        String escaped = raw;
        escaped = escaped.replace("\\", "\\\\");
        escaped = escaped.replace("\"", "\\\"");
        escaped = escaped.replace("\b", "\\b");
        escaped = escaped.replace("\f", "\\f");
        escaped = escaped.replace("\n", "\\n");
        escaped = escaped.replace("\r", "\\r");
        escaped = escaped.replace("\t", "\\t");
        return escaped;
    }
    
    private static String submitProgram(String submission) throws IOException {
        URL url = new URL(SERVER);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
    
        try(OutputStream requestBody = connection.getOutputStream()) {
            byte[] input = submission.getBytes(StandardCharsets.UTF_8);
            requestBody.write(input, 0, input.length);
        }
    
        InputStream responseStream = connection.getInputStream();

        Scanner sc = new Scanner(responseStream).useDelimiter("\\A");

        return sc.next();
    }
    
    private static String getSubmissionResult(String name) throws IOException {
        URL url = new URL(SERVER + name);
    
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("GET");
    
        InputStream responseStream = connection.getInputStream();
    
        Scanner sc = new Scanner(responseStream).useDelimiter("\\A");
    
        return sc.hasNext() ? sc.next() : "";
    }
    
    private static String getPassword() {
        try {
            return new Scanner(new File("password.txt")).useDelimiter("\\A").next().trim();
        } catch (IOException e) {
            System.out.println("Cannot find password file.");
            return "password";
        }
    }
    
    public static void main(String[] args) throws IOException {
        File currentDirectory = new File(".");
        String absoluteDirectory = System.getProperty("user.dir").replace("\\", "/");
        String participantName = absoluteDirectory.substring(absoluteDirectory.lastIndexOf('/') + 1);
        System.out.println("Team name: " + participantName);
        File[] programs = null;
        if (currentDirectory.exists() && currentDirectory.isDirectory()) {
            programs = currentDirectory.listFiles((dir, name) -> name.contains(".java") && !name.equals(SUBMITTER_NAME));
        }
        if (programs == null || programs.length == 0) {
            System.out.println("No file is available for submission");
            return;
        }
        
        String password = getPassword();
        
        System.out.println("Which problem would you like to submit?");
        for (int i = 0; i < programs.length; i++) {
            System.out.println(i + ". " + programs[i].getName());
        }
        System.out.println(programs.length + ". All programs");
        
        Scanner sc = new Scanner(System.in);
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(sc.nextLine());
                if (0 <= choice && choice <= programs.length) {
                    break;
                }
            } catch (Throwable ignored) { }
            System.out.println("Please input a valid number");
        }
        
        ProblemSubmission[] submissions = readPrograms(programs, choice);
        String submission = new Submission(participantName, password, submissions).toString();
        
        System.out.println(submitProgram(submission));
        
        String response = getSubmissionResult(participantName);
        
        if (!response.trim().isEmpty()) {
            System.out.println(response);
        }
    }
}

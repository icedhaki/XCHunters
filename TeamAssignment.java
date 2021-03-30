import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class TeamAssignment {
    public static int solution(int n, int k, int[] skill) {
        // your solution goes here
        return 0;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        String[] line = br.readLine().split(" ");
        int n = parseInt(line[0]), k = parseInt(line[1]);
        int[] input = Arrays
            .stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();
        
        System.out.println(solution(n, k, input));
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class StoringWater {
    public static int solution(int n, int[] height) {
        // your solution goes here
        return 0;
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        int[] input = Arrays
            .stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();
        System.out.println(solution(input.length, input));
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class StoringWater {
    public static int solution(int n, int[] height) {
        // your solution goes here
        int max = 0;
        for(int i = 0; i < n - 1; i++){
            for(int j = i+1; j < n; j++){
                int volume = Math.min(height[i],height[j])*(j-i);
                //System.out.println(volume);
                if(volume > max){
                    max = volume;
                }
            }
        }
        
        return max;
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

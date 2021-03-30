import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ServerStructure {
    
    public static long solution(int n, String[] directories) {
        return 0;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] lines = br.lines().limit(n).toArray(String[]::new);
        System.out.println(solution(n, lines));
    }
}

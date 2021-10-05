import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main9095_123더하기 {
    static int[] dp = new int[11];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for(int i = 4; i < dp.length; i++){
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
        }

        for(int i = 0; i < n; i++){
            System.out.println(dp[Integer.parseInt(br.readLine())]);
        }
    }
}

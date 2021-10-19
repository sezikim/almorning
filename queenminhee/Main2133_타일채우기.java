package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2133_타일채우기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];

        if(N % 2 != 0){//홀수인 경우 만들수가 없음
            System.out.println(0);
            return;
        }
        dp[0] = 1;
        dp[2] = 3;
        for(int i = 4; i <= N; i+=2){
            dp[i] = dp[i-2] * dp[2];
            for(int j = i - 4; j >= 0; j -= 2){
                dp[i] += dp[j] * 2;
            }
        }
        System.out.println(dp[N]);
    }
}

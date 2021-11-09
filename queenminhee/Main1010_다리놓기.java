package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1010_다리놓기 {
    static int[][] dp = new int[30][30];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            System.out.println(Comb(m, n));
        }
    }

    private static int Comb(int n, int r) {

        if(dp[n][r] > 1) return dp[n][r];

        if(r == 0 || n == r){
            return dp[n][r] = 1;
        }

        return dp[n][r] = Comb(n-1, r-1) + Comb(n-1, r);
    }
}

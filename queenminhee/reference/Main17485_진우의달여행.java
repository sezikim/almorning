package boj;

import java.io.*;
import java.util.StringTokenizer;

public class Main17485_진우의달여행 {
    public static int N, M;
    public static int answer = Integer.MAX_VALUE;
    public static int[][] arr;
    public static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        dp = new int[3][N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //init
        for (int i = 0; i < M; i++) {
            dp[0][0][i] = arr[0][i];
            dp[1][0][i] = arr[0][i];
            dp[2][0][i] = arr[0][i];
        }

        //불가능한 곳 INF
        for (int i = 0; i < N; i++) {
            dp[0][i][0] = Integer.MAX_VALUE;
            dp[2][i][M - 1] = Integer.MAX_VALUE;
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (innerRange(j - 1) && innerRange(j + 1)) { //왼, 오 대각선 모두 올수있는경우
                    dp[0][i][j] = Math.min(dp[1][i-1][j-1], dp[2][i-1][j-1]) + arr[i][j];
                    dp[1][i][j] = Math.min(dp[0][i-1][j], dp[2][i-1][j]) + arr[i][j];
                    dp[2][i][j] = Math.min(dp[0][i-1][j+1], dp[1][i-1][j+1]) + arr[i][j];
                }else if (!innerRange(j - 1) && innerRange(j + 1)) { // 왼쪽에서는 못옴
                    dp[1][i][j] = Math.min(dp[0][i-1][j], dp[2][i-1][j]) + arr[i][j];
                    dp[2][i][j] = Math.min(dp[0][i-1][j+1], dp[1][i-1][j+1]) + arr[i][j];
                }else if (innerRange(j - 1) && !innerRange(j + 1)) {
                    dp[0][i][j] = Math.min(dp[1][i-1][j-1], dp[2][i-1][j-1]) + arr[i][j];
                    dp[1][i][j] = Math.min(dp[0][i-1][j], dp[2][i-1][j]) + arr[i][j];
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for(int i = 0; i < M ; i++){
            for(int j = 0; j < 3; j++){
                min = Math.min(min, dp[j][N-1][i]);
            }
        }
        System.out.println(min);
    }

    private static boolean innerRange(int y) {
        return 0 <= y && y < M;
    }

}

/*
 1. 첫번째 도전: 메모리 초과; (처음에 BFS 로 탐색)
 2. 두번째 도전: dfs 로; (시간초과 ㅠㅠ)
 3. DP를 접근한다 해도 어떻게 구현해야할지 막막해서 참조했음;;
    3-1. DP[num][x][y]:
      - num: 0: 왼, 1: 가운데, 2: 오른 대각선
      - x, y: 이전에 온 방향
 */

package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main2253_점프 {
    public static int INF = Integer.MAX_VALUE / 2;
    public static int N, M;
    public static int[][] dp;
    public static Set<Integer> set = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new int[N + 1][150];

        for(int i = 0; i < N + 1 ; i++ ){
            Arrays.fill(dp[i], INF);
        }

        dp[1][0] = 0;

        for(int i = 0; i < M; i++){
            int n = Integer.parseInt(br.readLine());
            set.add(n);
        }

        for(int i = 2; i < N + 1; i++){
            if(set.contains(i)) continue;
            for(int v = 1; v < (int) Math.sqrt(2 * i) + 1; v++){ // v * (v + 1) / 2 = i 로 v (속도)의 최대 길이는 2 * i 에 제곱근 까지 (포함)
                dp[i][v] = min(dp[i-v][v-1], dp[i-v][v], dp[i-v][v+1]) + 1;
            }
        }

        int answer = INF;
        for(int i = 0; i < 150; i++){
            answer = min(answer, dp[N][i]);
        }
        System.out.println(answer == INF ? -1 : answer);
    }
    public static int min(int ...x){
        int ret = Integer.MAX_VALUE;
        for(int i = 0; i < x.length; i++){
            ret = Math.min(ret, x[i]);
        }
        return ret;
    }
}


/*
- 이번에도 dfs 로 접근을 했다.
- dp 점화식을 세우는 방법이 감이 잘 안잡히는 듯하다.(자주 접했던 문제들은 기억이 나지만..새로운문제는...흠)

- 참조한 설명은: https://velog.io/@stkang9409/%EB%B0%B1%EC%A4%80-%EC%A0%90%ED%94%84-%EC%89%BD%EA%B2%8C-%EB%82%98%EC%98%A8-%ED%92%80%EC%9D%B4
- jcu011 님 코드참조: https://github.com/sezikim/almorning/blob/main/jcu011/028.%20%EC%A0%90%ED%94%84.cpp

[요약 정리]
- dp[i][v]: i 번째 돌에 v의 속도로 들어왔을 경우에 최소값
    - dp[i][v] 로 갈 수 있는 경우는 i-v 번째 돌에서 v의 속도로 뛰는 경우 (3가지)
        - dp[i-v][v-1] 에서 가속
        - dp[i-v][v] 에서 유지
        - dp[i-v][v+1]에서 감속
- 최대 건널 수 있는 돌의 개수는 140개 (주어진 돌의 최대 개수: 10,000) (141 * 142/ 2 = 10011)
- velog 참조에 따르면 (v * (v + 1) )/ 2 = i 로, v가 될 수 있는 최대 경우를 sqrt(i)로 보고 계산을 한 듯하다..

- Java 코드로 다른 언어와 차이 있었던 부분
    - Math.min 은 2개의 밸류만 학인해서 여러개 값을 확인할 수 있는 min method를 별도로 만들었음
    - INF 값을 지정할 때 Integer.MAX_VALUE 를 하면 연산하면서 예외가 생김
    -
*/
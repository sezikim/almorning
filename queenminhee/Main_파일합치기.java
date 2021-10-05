import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main11066_파일합치기{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int t = 0; t < T; t++){
            int K = Integer.parseInt(br.readLine());
            int[] arr = new int[K];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < K; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }
            process(K, arr);
        }

    }

    private static void process(int k, int[] arr) {
        int[] S = new int[k+1];                     //누적합
        int[][] dp = new int[k+1][k+1];             //dp: i~j 까지 합하는 최소 비용

        S[1] = arr[1];                              //누적합 구하기
        for(int i = 1; i < k+1; i++){
            S[i] = arr[i-1] + S[i-1];
        }

        for(int i = 1; i < k + 1; i++) {            //파일 길이: 1 ~ k 까지 (i = 1, s = 1, e = 2 => 길이 1)
            for(int s = 1; s + i < k + 1; s++){     //시작점
                int e = s + i;                      //끝점
                dp[s][e] = Integer.MAX_VALUE;
                for(int d = s; d < e; d++){         //나누는 위치
                    dp[s][e] = Math.min(dp[s][e], dp[s][d] + dp[d + 1][e] + (S[e] - S[s - 1])); //두 파일 합한것 + 누적합 과 기존의 값중 작은 값으로 업뎃
                }
            }
        }

        System.out.println(dp[1][k]);
    }
}


/*
참조 풀이
https://data-make.tistory.com/402
https://guy-who-writes-sourcecode.tistory.com/43

혼자서는 구현 못했고 참조하면서 왜 이렇게 되는지 이해하는데도 꽤 오래 걸렸다;
*/
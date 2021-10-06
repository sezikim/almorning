import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2015 {
    static int N;
    static int K;
    static int[] arr;
    static int[][] subSumArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        String[] firstLine = br.readLine().split(" ");
        N = Integer.parseInt(firstLine[0]);
        K = Integer.parseInt(firstLine[1]);

        arr = new int[N];
        subSumArr = new int[N][N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            subSumArr[i][0] = arr[i];
        }

        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                if (j == i) {
                    subSumArr[i][j] = arr[j];
                } else {
                    subSumArr[i][j] = subSumArr[i][j - 1] + arr[j];
                }
                if (subSumArr[i][j] == K) {
                    result++;
                }
            }
        }

        bw.write(String.valueOf(result));
        bw.flush();
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1025_제곱수찾기 {
    public static int N, M, ans;
    public static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        ans = -1;

        for (int i = 0; i < N; i++) {
            String row = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = row.charAt(j) - '0';
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) { //배열을 돌것
                for (int di = -N; di < N; di++) {
                    for (int dj = -M; dj < M; dj++) { // 공차 (common diff)
                        if (di == 0 && dj == 0) continue; //등차 아니니 확인할 필요 없음
                        int value = 0;
                        int nx = i;
                        int ny = j;
                        while (!isOut(nx, ny)) {
                            value = value * 10 + (arr[nx][ny]);
                            System.out.println(value +" "+ nx +" "+ ny);
                            if (isSquareNum(value)) {
                                ans = Math.max(ans, value);
                            }
                            nx += di;
                            ny += dj;
                        }
                    }
                }
            }
        }

        System.out.println(ans);
    }

    private static boolean isSquareNum(int value) {
        int num = (int) Math.sqrt(value);
        return num * num == value;
    }

    private static boolean isOut(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= M;
    }
}

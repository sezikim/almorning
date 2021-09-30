import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, r, c, d, answer;
    static int[][] arr;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void solve(int r, int c, int d) {
        boolean flag = false;

        if (!visited[r][c]) {
            visited[r][c] = true;
            answer += 1;
        }

        for (int i = 0; i < 4; i++) {
            d = rotate(d);
            int nextR = r + dx[d];
            int nextC = c + dy[d];

            if (!visited[nextR][nextC] && arr[nextR][nextC] == 0) {
                solve(nextR, nextC, d);
                flag = true;
                break;
            }
        }

        if (!flag) {
            int nextR = r - dx[d];
            int nextC = c - dy[d];
            if (arr[nextR][nextC] == 0) {
                solve(nextR, nextC, d);
            }
        }
    }

    public static int rotate(int d) {
        if (d == 0)
            return 3; // 북쪽에서 서쪽
        else if (d == 1)
            return 0; // 동쪽에서 남쪽
        else if (d == 2)
            return 1; // 남쪽에서 동쪽
        else
            return 2; // 서쪽에서 북쪽
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        answer = 0;
        arr = new int[N][M];
        visited = new boolean[N][M];

        st = new StringTokenizer(br.readLine(), " ");
        r = Integer.parseInt(st.nextToken()); // 로봇 r좌표
        c = Integer.parseInt(st.nextToken()); // 로봇 c좌표
        d = Integer.parseInt(st.nextToken()); // 로봇 방향

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        solve(r, c, d);

        System.out.println(answer);
    }
}

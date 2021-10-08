import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main10026_적록색약 {
    public static int N, generalCnt, readGreenCnt;
    public static char[][] arrG, arrRG;
    public static boolean[][] visited;
    public static int[] dx = {0, 1, 0, -1};
    public static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arrRG = new char[N][N];
        arrG = new char[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                char c = str.charAt(j);
                arrG[i][j] = c;
                arrRG[i][j] = c == 'R' ? 'G' : c;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    bfs(i, j, true);
                    generalCnt++;
                }
            }
        }

        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    bfs(i, j, false);
                    readGreenCnt++;
                }
            }
        }

        System.out.println(generalCnt + " " + readGreenCnt);
    }


    private static void bfs(int x, int y, boolean ck) {//ck true: 일반 , false : 적록
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();

            int cx = curr[0];
            int cy = curr[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (!check(nx, ny)) continue;
                if (visited[nx][ny]) continue;

                if (ck && arrG[cx][cy] == arrG[nx][ny]) {
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                } else if (!ck && arrRG[cx][cy] == arrRG[nx][ny]) {
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                }
            }
        }
    }

    private static boolean check(int nx, int ny) {
        return 0 <= nx && nx < N && 0 <= ny && ny < N ;
    }
}

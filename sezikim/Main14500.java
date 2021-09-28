import java.util.*;

public class Main14500 {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int area = Integer.MIN_VALUE;
    static boolean[][] visited;
    static int[][] arr;
    static int n;
    static int m;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        arr = new int[n][m];

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                arr[i][j] = sc.nextInt();
            }
        }

        visited = new boolean[n][m];

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (i - 1 >= 0 && j - 1 >= 0 && j + 1 < m) {
                    area = Math.max(area, arr[i][j] + arr[i - 1][j] + arr[i][j - 1] + arr[i][j + 1]);
                }
                if (i + 1 < n && j - 1 >= 0 && j + 1 < m) {
                    area = Math.max(area, arr[i][j] + arr[i + 1][j] + arr[i][j - 1] + arr[i][j + 1]);
                }
                if (j - 1 >= 0 && i - 1 >= 0 && i + 1 < n) {
                    area = Math.max(area, arr[i][j] + arr[i][j - 1] + arr[i - 1][j] + arr[i + 1][j]);
                }
                if (j + 1 < m && i - 1 >= 0 && i + 1 < n) {
                    area = Math.max(area, arr[i][j] + arr[i][j + 1] + arr[i - 1][j] + arr[i + 1][j]);
                }
                visited[i][j] = true;
                dfs(0, i, j, arr[i][j]);
                visited[i][j] = false;
            }
        }

        System.out.println(area);
    }

    static void dfs(int depth, int y, int x, int sum) {
        if (depth == 3) {
            area = Math.max(area, sum);
            return;
        }

        for (int i = 0; i < 4; ++i) {
            int nY = y + dy[i];
            int nX = x + dx[i];
            if (nY >= 0 && nY < n && nX >= 0 && nX < m && !visited[nY][nX]) {
                visited[nY][nX] = true;
                dfs(depth + 1, nY, nX, sum + arr[nY][nX]);
                visited[nY][nX] = false;
            }
        }
    }
}

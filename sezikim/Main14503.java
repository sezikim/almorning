import java.util.*;

public class Main14503 {
    static boolean[][] visited;
    static int[][] arr;
    static int[] dx = {0,-1,0,1};
    static int[] dy = {-1,0,1,0};
    static int d;
    static int cleanArea;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        visited = new boolean[n][m];
        arr = new int[n][m];

        int sY = sc.nextInt();
        int sX = sc.nextInt();
        d = sc.nextInt();

        if (d == 1) d = 3;
        else if (d == 3) d = 1;

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                arr[i][j] = sc.nextInt();
            }
        }

        visited[sY][sX] = true;
        cleanArea = 1;
        clean(sY, sX);

        System.out.println(cleanArea);
    }

    static void clean(int y, int x) {
        d++;
        if (d == 4) d = 0;

        if (arr[y+dy[d]][x+dx[d]] == 0 && !visited[y+dy[d]][x+dx[d]]) {
            visited[y+dy[d]][x+dx[d]] = true;
            cleanArea++;
            clean(y+dy[d], x+dx[d]);
        } else {
            d++;
            if (d == 4) d = 0;

            if (arr[y+dy[d]][x+dx[d]] == 0 && !visited[y+dy[d]][x+dx[d]]) {
                visited[y+dy[d]][x+dx[d]] = true;
                cleanArea++;
                clean(y+dy[d], x+dx[d]);
            } else {
                d++;
                if (d == 4) d = 0;

                if (arr[y+dy[d]][x+dx[d]] == 0 && !visited[y+dy[d]][x+dx[d]]) {
                    visited[y+dy[d]][x+dx[d]] = true;
                    cleanArea++;
                    clean(y+dy[d], x+dx[d]);
                } else {
                    d++;
                    if (d == 4) d = 0;

                    if (arr[y+dy[d]][x+dx[d]] == 0 && !visited[y+dy[d]][x+dx[d]]) {
                        visited[y+dy[d]][x+dx[d]] = true;
                        cleanArea++;
                        clean(y+dy[d], x+dx[d]);
                    } else {
                        d++;
                        if (d == 4) d = 0;
                        d++;
                        if (d == 4) d = 0;

                        int nY = y+dy[d];
                        int nX = x+dx[d];
                        if (arr[nY][nX] == 0) {
                            d--;
                            if (d == -1) d = 3;
                            d--;
                            if (d == -1) d = 3;
                            clean(nY, nX);
                        }else if (arr[nY][nX] == 1) {
                            return;
                        }
                    }
                }
            }
        }
    }
}

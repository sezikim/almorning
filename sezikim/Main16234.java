import java.util.*;

public class Main16234 {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int l = sc.nextInt();
        int r = sc.nextInt();

        int[][] arr = new int[n][n];
        boolean[][] visited = new boolean[n][n];

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                arr[i][j] = sc.nextInt();
            }
        }

        int day = 0;

        while (true) {
            boolean check = false;
            for (int y = 0; y < n; ++y) {
                for (int x= 0 ; x < n; ++x) {
                    visited[y][x] = false;
                }
            }

            for (int y = 0; y < n; ++y) {
                for (int x = 0; x < n; ++x) {
                    if (!visited[y][x]) {
                        HashSet<Country> union = new HashSet<>();
                        Queue<Country> q = new LinkedList<>();
                        Country start = new Country(y, x, arr[y][x]);
                        q.add(start);
                        union.add(start);
                        visited[y][x] = true;

                        while (!q.isEmpty()) {
                            Country cur = q.poll();

                            for (int i = 0; i < 4; ++i) {
                                int nY = cur.y + dy[i];
                                int nX = cur.x + dx[i];
                                if (nY >= 0
                                        && nY < n
                                        && nX >= 0
                                        && nX < n
                                        && !visited[nY][nX]
                                        && Math.abs(arr[nY][nX] - arr[cur.y][cur.x]) >= l
                                        && Math.abs(arr[nY][nX] - arr[cur.y][cur.x]) <= r) {
                                    check = true;
                                    visited[nY][nX] = true;
                                    Country next = new Country(nY, nX, arr[nY][nX]);
                                    q.add(next);
                                    union.add(next);
                                }
                            }
                        }

                        int sum = 0;
                        for (Country c : union) {
                            sum += c.value;
                        }

                        int mean = sum/union.size();
                        for (Country c : union) {
                            arr[c.y][c.x] = mean;
                        }
                    }
                }
            }
            if (!check) break;
            day++;
        }

        System.out.println(day);
    }

    static class Country {
        int y;
        int x;
        int value;

        public Country(int y, int x, int value) {
            this.y = y;
            this.x = x;
            this.value = value;
        }
    }
}

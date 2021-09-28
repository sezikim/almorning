import java.util.*;

public class Main21610 {
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] arr = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                arr[i][j] = sc.nextInt();
            }
        }

        int[][] commands = new int[m][2];
        for (int i = 0; i < m; ++i) {
            commands[i][0] = sc.nextInt();
            commands[i][1] = sc.nextInt();
        }

        HashSet<Cloud> clouds = new HashSet<>();
        clouds.add(new Cloud(n - 1, 0));
        clouds.add(new Cloud(n - 1, 1));
        clouds.add(new Cloud(n - 2, 0));
        clouds.add(new Cloud(n - 2, 1));

        for (int[] command : commands) {
            int d = command[0];
            int s = command[1];

            //1, 2
            HashSet<Cloud> deleteSet = new HashSet<>();
            for (Cloud cloud : clouds) {
                int nY = cloud.y + (dy[d-1] * s) % n;
                int nX = cloud.x + (dx[d-1] * s) % n;

                if (nY < 0) nY = n + nY;
                if (nX < 0) nX = n + nX;
                if (nY >= n) nY = nY%n;
                if (nX >= n) nX = nX%n;

                Cloud cur = new Cloud(nY, nX);

                arr[cur.y][cur.x]++;
                deleteSet.add(cur);
            }

            //3
            clouds.removeAll(clouds);

            //4
            for (Cloud cloud : deleteSet) {
                int y = cloud.y;
                int x = cloud.x;
                int count = 0;

                if (y + 1 < n && x + 1 < n && arr[y + 1][x + 1] >= 1) count++;
                if (y - 1 >= 0 && x + 1 < n && arr[y - 1][x + 1] >= 1) count++;
                if (y - 1 >= 0 && x - 1 >= 0 && arr[y - 1][x - 1] >= 1) count++;
                if (y + 1 < n && x - 1 >= 0 && arr[y + 1][x - 1] >= 1) count++;

                arr[y][x] += count;
            }

            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (arr[i][j] >= 2 && !deleteSet.contains(new Cloud(i, j))) {
                        Cloud tmpCloud = new Cloud(i, j);
                        clouds.add(tmpCloud);
                        arr[i][j] -= 2;
                    }
                }
            }
        }

        int sum = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                sum += arr[i][j];
            }
        }

        System.out.println(sum);
    }

    static class Cloud {
        int y;
        int x;

        public Cloud(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cloud cloud = (Cloud) o;
            return y == cloud.y && x == cloud.x;
        }

        @Override
        public int hashCode() {
            return Objects.hash(y, x);
        }
    }
}

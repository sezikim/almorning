import java.util.*;

public class Main14502 {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static HashSet<String> set = new HashSet<>();
    static int m;
    static int n;
    static boolean[] visited;
    static int[][] arr;
    static List<Integer> virusList;
    static int maxSafeArea = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        virusList = new ArrayList<>();
        visited = new boolean[n * m];
        arr = new int[n][m];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                arr[i][j] = scanner.nextInt();
                if (arr[i][j] != 0) visited[i * m + j] = true;
                if (arr[i][j] == 2) virusList.add(i * m + j);
            }
        }

        int[][] initArr = new int[n][m];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                initArr[i][j] = arr[i][j];
            }
        }
        boolean[] initVisited = new boolean[n * m];
        for (int i = 0; i < n * m; ++i) {
            initVisited[i] = visited[i];
        }

        dfs(0, 0, "");

        int count = 0;
        Queue<Integer> q;
        for (String cur : set) {
            count = 0;
            q = new LinkedList<>();
            String[] curArr = cur.split(" ");
            for (String idx : curArr) {
                int i = Integer.parseInt(idx);
                arr[i / m][i % m] = 1;
                visited[i] = true;
            }

            q.addAll(virusList);
            while (!q.isEmpty()) {
                int curIdx = q.poll();
                int nextY;
                int nextX;
                for (int i = 0; i < 4; ++i) {
                    nextY = curIdx / m + dy[i];
                    nextX = curIdx % m + dx[i];
                    if (nextY < n
                            && nextY >= 0
                            && nextX < m
                            && nextX >= 0
                            && !visited[nextY * m + nextX]
                            && arr[nextY][nextX] == 0) {
                        visited[nextY * m + nextX] = true;
                        arr[nextY][nextX] = 2;
                        q.add(nextY * m + nextX);
                    }
                }
            }

            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < m; ++j) {
                    if (arr[i][j] == 0) count++;
                }
            }

            maxSafeArea = Math.max(count, maxSafeArea);

            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < m; ++j) {
                    arr[i][j] = initArr[i][j];
                }
            }

            for (int i = 0; i < m * n; ++i) {
                visited[i] = initVisited[i];
            }
        }

        System.out.println(maxSafeArea);
    }

    static void dfs(int depth, int idx, String cur) {
        if (depth == 3) {
            set.add(cur);
            return;
        }

        for (int i = idx; i < m * n; ++i) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(depth + 1, i, cur + i + " ");
                visited[i] = false;
            }
        }
    }
}

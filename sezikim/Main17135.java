import java.util.*;

public class Main17135 {
    static int n;
    static int m;
    static int d;
    static int[][] arr;
    static boolean[] visited;
    static HashSet<String> position;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        d = sc.nextInt();

        arr = new int[n][m];
        position = new HashSet<>();

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                arr[i][j] = sc.nextInt();
            }
        }

        visited = new boolean[m];
        dfs(0, "", 0);

        int[][] tmp = new int[n][m];
        for (String cur : position) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < m; ++j) {
                    tmp[i][j] = arr[i][j];
                }
            }
            String[] arrows = cur.split(",");
            int count = 0;
            for (int y = 0; y < n; ++y) {
                HashSet<String> attackPoint = new HashSet<>();
                for (String arrow : arrows) {
                    int c = Integer.parseInt(arrow);
                    int r = n;
                    int ac = 0;
                    int ar = 0;
                    int minD = Integer.MAX_VALUE;
                    boolean check = false;
                    for (int i = 0; i < n; ++i) {
                        if (Math.abs(r-i) > d) continue;
                        for (int j = 0; j < m; ++j) {
                            if (Math.abs(r-i) + Math.abs(c-j) > d) continue;
                            if (tmp[i][j] == 1) {
                                int distance = Math.abs(j - c) + Math.abs(i - r);
                                if (minD > distance) {
                                    minD = distance;
                                    ac = j;
                                    ar = i;
                                    check = true;
                                } else if (distance == minD) {
                                    if (j < ac) {
                                        ac = j;
                                        ar = i;
                                        check = true;
                                    }
                                }
                            }
                        }
                    }
                    if (check) attackPoint.add(ar + "," + ac);
                }

                for (String attack : attackPoint) {
                    String[] atkP = attack.split(",");
                    tmp[Integer.parseInt(atkP[0])][Integer.parseInt(atkP[1])] = 0;
                }

                count += attackPoint.size();

                for (int i = n-1; i >= 1; --i) {
                    for (int x = 0; x < m; ++x) {
                        tmp[i][x] = tmp[i - 1][x];
                    }
                }
                for (int x = 0; x < m; ++x) {
                    tmp[0][x] = 0;
                }
            }

            max = Math.max(count, max);
        }

        System.out.println(max);
    }

    static void dfs(int depth, String cur, int size) {
        if (size == 3) {
            position.add(cur);
            return;
        }

        if (depth == m) {
            return;
        }

        visited[depth] = true;
        String next = cur + depth + ",";
        dfs(depth + 1, next, size + 1);
        visited[depth] = false;
        dfs(depth + 1, cur, size);
    }
}

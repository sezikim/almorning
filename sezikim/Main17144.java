import java.util.*;

public class Main17144 {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        int c = sc.nextInt();
        int t = sc.nextInt();
        int[][] arr = new int[r][c];
        List<Integer> fresher = new ArrayList<>();

        for (int i = 0; i < r; ++i) {
            for (int j = 0; j < c; ++j) {
                arr[i][j] = sc.nextInt();
                if (arr[i][j] == -1) {
                    fresher.add(i);
                }
            }
        }
        for (int s = 0; s < t; ++s) {
            // 미세먼지 확산양 구하기
            // 미세먼지 줄은양 빼주기
            int[][] plus = new int[r][c];
            int[][] minus = new int[r][c];
            for (int i = 0; i < r; ++i) {
                for (int j = 0; j < c; ++j) {
                    int count = 0;
                    for (int d = 0; d < 4; ++d) {
                        if (i + dy[d] >= 0
                                && i + dy[d] < r
                                && j + dx[d] >= 0
                                && j + dx[d] < c
                                && arr[i + dy[d]][j + dx[d]] != -1) {
                            plus[i+dy[d]][j+dx[d]] += arr[i][j] / 5;
                            count++;
                        }
                    }
                    minus[i][j] -= (arr[i][j]/5) * count;
                }
            }

            // 미세먼저 확산양 더하기
            for (int i = 0; i < r; ++i) {
                for (int j = 0; j < c; ++j) {
                    if (arr[i][j] != -1) arr[i][j] += plus[i][j] + minus[i][j];
                }
            }

            // 공기청정기 바람으로 이동하는 것 구하기
            for (int i = fresher.get(0) - 1; i > 0; --i) {
                arr[i][0] = arr[i-1][0];
            }
            for (int i = 0; i < c - 1; ++i) {
                arr[0][i] = arr[0][i+1];
            }
            for (int i = 0; i < fresher.get(0); ++i) {
                arr[i][c-1] = arr[i+1][c-1];
            }
            for (int i = c-1; i > 0; --i) {
                arr[fresher.get(0)][i] = arr[fresher.get(0)][i-1];
            }
            arr[fresher.get(0)][1] = 0;

            for (int i = fresher.get(1) + 1; i < r - 1; ++i) {
                arr[i][0] = arr[i+1][0];
            }
            for (int i = 0; i < c - 1; ++i) {
                arr[r-1][i] = arr[r-1][i+1];
            }
            for (int i = r-1; i > fresher.get(1); --i) {
                arr[i][c-1] = arr[i-1][c-1];
            }
            for (int i = c-1; i > 0; --i) {
                arr[fresher.get(1)][i] = arr[fresher.get(1)][i-1];
            }
            arr[fresher.get(1)][1] = 0;

        }

        int dust = 0;
        for (int i = 0; i < r; ++i) {
            for (int j = 0; j < c; ++j) {
                if (arr[i][j] != -1) dust += arr[i][j];
            }
        }

        System.out.println(dust);
    }
}

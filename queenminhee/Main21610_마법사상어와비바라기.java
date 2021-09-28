import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main21610_마법사상어비바라기 {
    public static int N, M;
    public static int[][] A;
    public static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    public static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static List<Pointer> clouds = new ArrayList<>();
    static boolean[][] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        A = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                A[i][j] = sc.nextInt();
            }
        }

        initCloud();


        for (int i = 0; i < M; i++) {
            visited = new boolean[N + 1][N + 1];
            int d = sc.nextInt() - 1;
            int s = sc.nextInt();

            moveCloud(d, s);
            rainCloud();
            clouds = new ArrayList<>();
            findCloud();
        }

        int answer = 0;
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                answer += A[i][j];
            }
        }
        System.out.println(answer);

    }

    private static void findCloud() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (!visited[i][j] && A[i][j] >= 2) {
                    clouds.add(new Pointer(i, j));
                    A[i][j] -= 2;
                }
            }
        }
    }

    private static void rainCloud() {
        for (int i = 0; i < clouds.size(); i++) {

            int[] crossX = {-1, -1, 1, 1};
            int[] crossY = {-1, 1, 1, -1};
            int cnt = 0;
            for (int j = 0; j < 4; j++) {
                int nx = clouds.get(i).x + crossX[j];
                int ny = clouds.get(i).y + crossY[j];

                if (isRange(nx, ny) && A[nx][ny] > 0) {
                    cnt++;
                }
            }
            A[clouds.get(i).x][clouds.get(i).y] += cnt;
        }

    }

    private static boolean isRange(int nx, int ny) {
        return nx > 0 && ny > 0 && nx <= N && ny <= N;
    }

    private static void moveCloud(int d, int s) {
        for (int i = 0; i < clouds.size(); i++) {
            int nx = rangeCheck(clouds.get(i).x, dx[d] * s);
            int ny = rangeCheck(clouds.get(i).y, dy[d] * s);

            clouds.get(i).x = nx;
            clouds.get(i).y = ny;

            visited[nx][ny] = true;
            A[nx][ny]++;
        }
    }

    private static int rangeCheck(int num, int offset) {
        int ret = (num + offset) % N;
        return ret > 0 ? ret : ret + N;
    }

    private static void initCloud() {
        clouds.add(new Pointer(N - 1, 1));
        clouds.add(new Pointer(N - 1, 2));
        clouds.add(new Pointer(N, 1));
        clouds.add(new Pointer(N, 2));
    }
}

class Pointer {
    int x;
    int y;

    public Pointer(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
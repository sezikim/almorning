import java.io.*;
import java.util.StringTokenizer;

// 마법사 상어와 토네이도
public class BOJ_20057 {

    public static int N;
    public static int direction = -1;
    public static int[] dx = {0, 1, 0, -1};
    public static int[] dy = {-1, 0, 1, 0};
    static int[][] sandX = {{-1, 1, -2, -1, 1, 2, -1, 1, 0}, {-1, -1, 0, 0, 0, 0, 1, 1, 2},    //모래가 퍼지는 x방향
            {1, -1, 2, 1, -1, -2, 1, -1, 0}, {1, 1, 0, 0, 0, 0, -1, -1, -2}};
    static int[][] sandY = {{1, 1, 0, 0, 0, 0, -1, -1, -2}, {-1, 1, -2, -1, 1, 2, -1, 1, 0},    //모래가 퍼지는 y방향
            {-1, -1, 0, 0, 0, 0, 1, 1, 2}, {1, -1, 2, 1, -1, -2, 1, -1, 0}};
    static int[] sandRatio = {1, 1, 2, 7, 7, 2, 10, 10, 5};
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        // 1. 입력
        N = Integer.parseInt(br.readLine().trim());
        map = new int[N][N];
        int center = N / 2;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int result = calculateSummaryOfSand(center);
        bw.write(String.valueOf(result));
        bw.flush();
    }

    public static int calculateSummaryOfSand(int center) {
        int totalSand = 0;
        int x = center;
        int y = center;
        int distance = 0;
        while (true) {
            distance++;
            for (int j = 0; j < 2; j++) {
                direction = (direction + 1) % 4;
                for (int k = 0; k < distance; k++) {
                    int nx = x + dx[direction];
                    int ny = y + dy[direction];
                    if (nx >= N || nx < 0 || ny >= N || ny < 0) {
                        return totalSand;
                    }
                    totalSand += move_sand(nx, ny);
                    x = nx;
                    y = ny;
                }
            }
        }
    }

    public static int move_sand(int x, int y) {
        int sand = map[x][y];
        int totalSandOut = 0;
        int totalSpreadOut = 0;
        for (int i = 0; i < 9; i++) {
            int nx = x + sandX[direction][i];
            int ny = y + sandY[direction][i];
            int spreadSandAMount = (sand * sandRatio[i]) / 100;

            if (nx >= N || nx < 0 || ny >= N || ny < 0) {
                totalSandOut += spreadSandAMount;
            }
            else {
                map[nx][ny] += spreadSandAMount;
            }
            totalSpreadOut += spreadSandAMount;
        }

        int ax = x + dx[direction];
        int ay = y + dy[direction];
        int alphaAmount = sand - totalSpreadOut;

        if(ax >= N || ax < 0 || ay >= N || ay < 0) {
            totalSandOut += alphaAmount;
        }
        else {
            map[ax][ay] += alphaAmount;
        }
        return totalSandOut;
    }
}

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main5547_일루미네이션 {
    public static final int BLANK = 0;
    public static final int BUILDING = 1;
    public static int answer;
    public static int W;
    public static int H;
    public static int[][] arr;
    public static boolean[][] isOut;

//    public static int[][] oddDir = {{0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 0}};
//    public static int[][] evenDir = {{-1, -1}, {0, -1}, {1, 0}, {0, 1}, {-1, 1}, {-1, 0}};

    //(w, h)로 읽고 있기 때문에 (x, y) -> (y, x)로
    static int[][] oddDir = {{-1, 0}, {0, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};
    static int[][] evenDir = {{-1, -1}, {0, -1}, {1, -1}, {1, 0}, {0, 1}, {-1 ,0}};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        W = sc.nextInt();
        H = sc.nextInt();

        arr = new int[H + 2][W + 2];
        isOut = new boolean[H + 2][W + 2];

        for (int i = 1; i < H + 1; i++) {
            for (int j = 1; j < W + 1; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        checkOutWall();

        for (int i = 1; i < H + 1; i++) {
            for (int j = 1; j < W + 1; j++) {
                if(arr[i][j] == BUILDING){
                    countLines(i, j);
                }
            }
        }

        System.out.println(answer);
    }

    private static void checkOutWall() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});

        while (!q.isEmpty()) {
            int[] xy = q.poll();
            int cx = xy[0];
            int cy = xy[1];

            for (int i = 0; i < 6; i++) {
                int nx, ny;
                if (cx % 2 == 0) {
                    nx = cx + evenDir[i][0];
                    ny = cy + evenDir[i][1];
                } else {
                    nx = cx + oddDir[i][0];
                    ny = cy + oddDir[i][1];
                }

                if (nx < 0 || nx >= H + 2 || ny < 0 || ny >= W + 2) continue;
                if(!isOut[nx][ny] && arr[nx][ny] == BLANK){
                    isOut[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                }
            }
        }
    }

    private static void countLines(int cx, int cy) {
        for(int i = 0; i < 6; i++){
            int nx, ny;
            if(cx % 2 == 0){
                nx = cx + evenDir[i][0];
                ny = cy + evenDir[i][1];
            }else{
                nx = cx + oddDir[i][0];
                ny = cy + oddDir[i][1];
            }

            if(isOut[nx][ny]) answer++;
        }
    }
}


/*
[로직]
1. 건물의 외벽을 찾는다. checkOutWall()
    1.1 기존에 주어진 배열에 패딩이 입혀진 크기를 전부 돌면서 외벽인 경우 isOut 배열에 true 로 체크했다.
    1.2 이 때 y값이 홀, 짝에 따라 다르다. (주의사항 여기서 배열을 읽을때 W, H로 읽기 때문에 cx가 짝수였는지 홀수였는지로 체크해야한다.)
2. 배열을 돌면서 건물인 경우에 외벽이 맞는지에 따라 카운트를 한다.

[어려웠던 것]
1. 정육각형을 어떻게 하라는 건지 감을 잡지 못했다.
2. 로직 자체를 생각하지 못함;
3. (x, y)를 읽을때 여기서 (y,x)로 읽기 때문에 이부분을 유의해서 풀어야했다. (이런 문제가 나오면 늘 생각이 꼬인다;ㅠ)
*/
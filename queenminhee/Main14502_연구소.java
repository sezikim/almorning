import java.util.Scanner;

public class Main14502_연구소 {
    public static final int BLANK = 0;
    public static final int WALL = 1;
    public static final int VIRUS = 2;

    public static int N;
    public static int M;
    public static int answer = Integer.MIN_VALUE;

    public static int[] dx = {0, 1, 0, -1};
    public static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        int[][] arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        search(arr, 3);

        System.out.println(answer);
    }

    private static void search(int[][] arr, int cnt) {
        if (cnt < 0) return;
        if (cnt == 0) {
            int[][] temp = new int[N][M];
            for (int i = 0; i < N; i++) {
                if (M >= 0) System.arraycopy(arr[i], 0, temp[i], 0, M);
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (temp[i][j] == VIRUS) {
                        spreadVirus(temp, i, j);
                    }
                }
            }

            answer = Math.max(answer, countBlank(temp));
            return;
        }

        // 3개의 벽을 임의로 세운다.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == BLANK) {
                    arr[i][j] = WALL;
                    cnt -= 1;
                    search(arr, cnt);
                    cnt += 1;
                    arr[i][j] = BLANK;
                }
            }
        }
    }

    private static void spreadVirus(int[][] arr, int cx, int cy) {
        for (int i = 0; i < 4; i++) {
            int nx = cx + dx[i];
            int ny = cy + dy[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= M || arr[nx][ny] == WALL) continue;

            if (arr[nx][ny] == BLANK) {
                arr[nx][ny] = VIRUS;
                spreadVirus(arr, nx, ny);
            }
        }
    }

    private static int countBlank(int[][] temp) {
        int ret = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (temp[i][j] == BLANK) ret++;
            }
        }
        return ret;
    }
}


/*
[전체 로직]
1. 벽을 3곳을 세워본다.
2. 3곳을 세운 후에 바이러스를 퍼뜨린다. (이때 배열을 복사해서 사용 (바이러스 퍼트릴 용도))
3. 빈 칸을 카운트해서 기존에 카운트했던 값과 비교해서 큰값으로 업데이트 한다.

[시간이 걸린 부분]
- cnt 를 3으로 시작했으니 카운트를 빼야하는데 더해서 계산했었음;;ㅎㅎ 순서 바꿈;; (디버깅으로 해결)
- 최대값을 구해야하는데, Math.min 으로 구해서 계속 초기값이 나왔었음 (디버깅으로 해결)

[배운점 및 아쉬운점 기타]
- 한 번 풀었어서 로직을 금방 떠올릴 수 있었다.
- IDE 없이, 디버깅툴 없이 구현하는건 여전히 손에 잘 안잡힌다.
- 여전히 Scanner 를 벗어나지 못하겠다; ㅠ3ㅠ
- 나는 cnt 를 빼고 더했는데 그렇게 하지 말고 매개변수로 cnt-1 를 넘겨줘서 처리했어도 된다.

*/
package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main16236_아기상어 {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] arr;
    static boolean[][] visited;
    static int N, cx, cy, answer, eatCnt;
    static int size = 2;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 9) {//상어위치는 위치정보만 저장
                    cx = i;
                    cy = j;
                    continue;
                }
                arr[i][j] = num;
            }
        }

        bfs();

        System.out.println(answer);
    }

    private static void bfs() {

        while (true) {
            Queue<int[]> q = new PriorityQueue<>((a, b)-> (a[2] != b[2])? Integer.compare(a[2], b[2]):
                    ((a[0] != b[0])? Integer.compare(a[0], b[0]): Integer.compare(a[1], b[1])));
            q.add(new int[]{cx, cy, 0});
            visited = new boolean[N][N];
            visited[cx][cy] = true;
            boolean flag = false;

            while (!q.isEmpty()) {
                int[] curr = q.poll();
                cx = curr[0];
                cy = curr[1];
                if(arr[cx][cy] != 0 && arr[cx][cy] < size){
                    eatCnt++;
                    arr[cx][cy] = 0;
                    answer += curr[2];
                    flag = true;
                    break;
                }

                for (int i = 0; i < 4; i++) {
                    int nx = cx + dx[i];
                    int ny = cy + dy[i];

                    if (isOutRange(nx, ny) || visited[nx][ny] || size < arr[nx][ny]) continue;
                    q.add(new int[]{nx, ny, curr[2] + 1});
                    visited[nx][ny] = true;
                }
            }

            if(!flag){
                break;
            }

            if(size == eatCnt){
                eatCnt = 0;
                size++;
            }
        }
    }

    private static boolean isOutRange(int nx, int ny) {
        return nx < 0 || nx >= N || ny < 0 || ny >= N;
    }
}

/*
우선순위를 어떻게 둘지 계속 고민하다가 처음에는 객체로 저장해서 계속 정렬하는 방식으로 갔었는데
찾아보다보니 PQ를 활용하니 그부분을 아주 간략하게 구현해낼 수 있었다.
(정렬을 했을 때 안돌아간거는 .. 로직상에 문제가 있었던 것 같다 (백업안해둬서 파일 날아감...)

https://girawhale.tistory.com/39


*
* */
package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main15684_사다리조작 {
    public static int N, M, H, answer;
    public static boolean flag = false;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        arr = new int[H + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr[a][b] = RIGHT;
            arr[a][b + 1] = LEFT;
        }

        for (int i = 0; i <= 3; i++) {
            answer = i;
            dfs(0, 0);
            if(flag) break;
        }

        System.out.println(flag ? answer : -1);
    }

    private static void dfs(int x, int cnt) {
        if(flag) return;
        if (cnt == answer) {
            if(check()){
                flag = true;
            }
            return;
        }

        for (int i = x; i < H + 1; i++) {
            for (int j = 1; j < N ; j++) {
                if (arr[i][j] == 0 && arr[i][j + 1] == 0) {
                    arr[i][j] = RIGHT;
                    arr[i][j + 1] = LEFT;
                    dfs(i, cnt+ 1);
                    arr[i][j] = arr[i][j+1] = 0;
                }
            }
        }
    }

    private static boolean check() {
        for(int i = 1; i <= N; i++){
            int x = 1, y = i;
            for(int j = 0; j < H; j++){
                if(arr[x][y] == RIGHT) {
                    y ++;
                }else if(arr[x][y] == LEFT){
                    y --;
                }
                x++;
            }
            if(y != i) return false;
        }
        return true;
    }
}

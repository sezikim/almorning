import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main17144_미세먼지안녕 {
    public static int R, C;
    public static int[][] arr;
    public static int[][] temp;
    public static List<Integer> vacuum = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        R = sc.nextInt();
        C = sc.nextInt();
        int T = sc.nextInt();
        arr = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                arr[i][j] = sc.nextInt();
                if(arr[i][j] == -1) vacuum.add(i);
            }
        }

        while (T-- > 0) {
            temp = new int[R][C];

            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (arr[i][j] > 0) {
                        spreadDust(i, j);
                    }
                }
            }
            updateTempToArr();
            airRefresh();
        }
        print();
    }

    private static void print() {
        int answer = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (arr[i][j] > 0) answer += arr[i][j];
            }
        }
        System.out.println(answer);
    }

    private static void airRefresh() {
        upperSide();
        lowerSide();
    }

    private static void lowerSide() {
        int i = vacuum.get(1) + 1, j = 0;
        while(i < R - 1){
            arr[i][j] = arr[i+1][j];
            i++;
        }
        while(j < C - 1){
            arr[i][j] = arr[i][j+1];
            j++;
        }
        while(i > vacuum.get(1)){
            arr[i][j] = arr[i-1][j];
            i--;
        }
        while(j > 0){
            arr[i][j] = arr[i][j-1];
            j--;
        }
        arr[vacuum.get(1)][1] = 0;
    }

    private static void upperSide() {
        int i = vacuum.get(0) - 1, j = 0;
        while(i > 0){
            arr[i][j] = arr[i-1][j];
            i--;
        }
        while(j < C - 1){
            arr[i][j] = arr[i][j+1];
            j++;
        }
        while(i < vacuum.get(0)){
            arr[i][j] = arr[i+1][j];
            i++;
        }
        while(j > 1){
            arr[i][j] = arr[i][j-1];
            j--;
        }
        arr[vacuum.get(0)][1] = 0;
    }

    private static void updateTempToArr() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(arr[i][j] == -1) continue;
                arr[i][j] = temp[i][j];
            }
        }
    }

    private static void spreadDust(int cx, int cy) {
        int[] dx = new int[]{0, 1, 0, -1};
        int[] dy = new int[]{1, 0, -1, 0};
        temp[cx][cy] += arr[cx][cy];

        for (int i = 0; i < 4; i++) {
            int nx = cx + dx[i];
            int ny = cy + dy[i];

            if (nx < 0 || nx >= R || ny < 0 || ny >= C || arr[nx][ny] == -1) continue;
            temp[nx][ny] += arr[cx][cy] / 5;
            temp[cx][cy] -= arr[cx][cy] / 5;
        }
    }
}


/*
[막폈던 부분]
- 나선형으로 숫자를 업데이트 하는것.
- 달팽이 채우기, 나선형문제가 헷갈리네요ㅠㅠㅎㅎ;;
-
* */
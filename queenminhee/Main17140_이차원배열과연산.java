package boj;

import java.io.*;
import java.util.*;

public class Main17140_이차원배열과연산 {
    public static int r, c, k;
    public static int N = 3; //N * M 배열 (초기 3 * 3)
    public static int M = 3;
    public static int[][] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        A = new int[101][101];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(operating());

    }

    private static int operating() {
        for (int time = 0; time <= 100; time++) {
            if (A[r][c] == k) {
                return time;
            }

            if (N >= M) {
                for (int i = 1; i <= N; i++) {
                    R(i);
                }
            } else {
                for (int i = 1; i <= M; i++) {
                    C(i);
                }
            }
        }
        return -1;
    }

    private static void C(int i) {
        Queue<Sol17140> pq = new PriorityQueue<>();
        Map<Integer, Integer> map = new HashMap<>();

        for (int j = 1; j <= N; j++) {
            if (A[j][i] == 0) continue;
            map.put(A[j][i], map.getOrDefault(A[j][i], 0) + 1);
        }

        map.forEach((k, v)-> pq.add(new Sol17140(k, v)));

        int idx = 1;
        while(!pq.isEmpty()){
            Sol17140 p = pq.poll();
            A[idx++][i] = p.ele;
            A[idx++][i] = p.count;
        }

        N = Math.max(N, idx);

        //처음에 이부분을 이렇게 하나하나 채워주지 않고, method를 시작할때 A배열을 모두 0으로 초기화해서 덮어씌워서
        //결과가 조금 이상하게 나왔었다.
        while(idx <= 99){
            A[idx++][i] = 0;
            A[idx++][i] = 0;
        }
    }

    private static void R(int i) {
        Queue<Sol17140> pq = new PriorityQueue<>();
        Map<Integer, Integer> map = new HashMap<>();

        for (int j = 1; j <= M; j++) {
            if (A[i][j] == 0) continue;
            map.put(A[i][j], map.getOrDefault(A[i][j], 0) + 1);
        }

        map.forEach((k, v)-> pq.add(new Sol17140(k, v)));

        int idx = 1;
        while(!pq.isEmpty()){
            Sol17140 p = pq.poll();
            A[i][idx++] = p.ele;
            A[i][idx++] = p.count;
        }

        M = Math.max(M, idx);

        while(idx <= 99){
            A[i][idx++] = 0;
            A[i][idx++] = 0;
        }
    }

}

class Sol17140 implements Comparable<Sol17140> {
    int ele;
    int count;

    public Sol17140(int ele, int count) {
        this.ele = ele;
        this.count = count;
    }

    @Override
    public int compareTo(Sol17140 o) {
        if (o.count != this.count) {
            return Integer.compare(this.count, o.count);
        } else {
            return Integer.compare(this.ele, o.ele);
        }
    }
}


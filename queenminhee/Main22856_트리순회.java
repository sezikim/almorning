import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main22856_트리순회 {
    public static int N, cnt, endPoint;
    public static int[][] tree;
    public static int[] parent;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        tree = new int[N + 1][2];
        visited = new boolean[N + 1];
        parent = new int[N + 1];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int left = tree[idx][0] = Integer.parseInt(st.nextToken()); //left node
            int right = tree[idx][1] = Integer.parseInt(st.nextToken()); //right node
            if (left != -1) {
                parent[left] = idx;                          //부모노드 체크
            }
            if (right != -1) {
                parent[right] = idx;                          //부모노드 체크
            }
        }

        order(1);

        int curNode = 1;
        while (true) {
            if (!visited[curNode]) {
                visited[curNode] = true;
            }
            if (tree[curNode][0] != -1 && !visited[tree[curNode][0]]) {
                curNode = tree[curNode][0];
            } else if (tree[curNode][1] != -1 && !visited[tree[curNode][1]]) {
                curNode = tree[curNode][1];
            } else if (curNode == endPoint) {
                break;
            } else {
                curNode = parent[curNode];
            }
            cnt++;
        }

        System.out.println(cnt);

    }

    private static void order(int node) {
        if (tree[node][0] != -1) { //왼쪽 자식노드 있는 경우
            order(tree[node][0]);
        }
        endPoint = node;           //종료지점
        if (tree[node][1] != -1) { //오른쪽 자식노드 있는경우
            order(tree[node][1]);
        }
    }
}

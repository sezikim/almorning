import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main15681_트리와쿼리 {
    public static LinkedList<Integer>[] oriList;
    public static LinkedList<Integer>[] subList;
    public static int[] parent;
    public static int[] size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        oriList = new LinkedList[N + 1];
        subList = new LinkedList[N + 1];
        parent = new int[N + 1];
        size = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            oriList[i] = new LinkedList<>();
            subList[i] = new LinkedList<>();
        }

        for (int i = 0; i < N - 1; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            oriList[a].add(b);
            oriList[b].add(a);
        }

        makeTree(R , -1);
        countSubtreeNodes(R);

        for(int i = 0; i < Q; i++){
            int q = Integer.parseInt(br.readLine());

            System.out.println(size[q]);
        }
    }

    private static void countSubtreeNodes(int currentNode) {
        size[currentNode] = 1;
        for(Integer nextNode : subList[currentNode]){
            countSubtreeNodes(nextNode);
            size[currentNode] += size[nextNode];
        }
    }

    private static void makeTree(int currenNode, int p) {
        for(Integer node : oriList[currenNode]){
            if(node != p){
                subList[currenNode].add(node);
                parent[node] = currenNode;
                makeTree(node, currenNode);
            }
        }
    }
}

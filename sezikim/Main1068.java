import java.util.*;

public class Main1068 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        HashMap<Integer, List<Integer>> treeMap = new HashMap<>();

        for (int i = 0; i < n; ++i) {
            treeMap.put(i, new ArrayList<>());
        }

        int root = 0;
        for (int i = 0; i < n; ++i) {
            int cur = sc.nextInt();
            if (cur == -1) root = i;
            else treeMap.get(cur).add(i);
        }

        int d = sc.nextInt();

        if (root == d) {
            System.out.println(0);
            return;
        }

        HashSet<Integer> deleteSet = new HashSet<>();

        Queue<Integer> dq = new LinkedList<>();
        deleteSet.add(d);
        dq.add(d);
        while (!dq.isEmpty()) {
            int cur = dq.poll();
            List<Integer> nextList = treeMap.get(cur);
            for (int next : nextList) {
                deleteSet.add(next);
                dq.add(next);
            }
        }

        int leaf = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(root);

        int cur;
        while (!q.isEmpty()) {
            cur = q.poll();
            if (treeMap.get(cur).size() == 0 || (treeMap.get(cur).size() == 1 && deleteSet.contains(treeMap.get(cur).get(0))))
                leaf++;

            for (int next : treeMap.get(cur)) {
                if (!deleteSet.contains(next)) {
                    q.add(next);
                }
            }
        }

        System.out.println(leaf);
    }
}

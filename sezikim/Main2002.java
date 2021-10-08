import java.util.*;

public class Main2002 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        List<String> in = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            in.add(sc.next());
        }

        List<String> out = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            out.add(sc.next());
        }

        HashSet<String> set = new HashSet<>();
        int count = 0;
        while (!out.isEmpty()) {
            if (in.get(0).equals(out.get(0))) {
                in.remove(0);
                out.remove(0);
                continue;
            }

            if (set.contains(in.get(0))) {
                in.remove(0);
            } else {
                count++;
                set.add(out.get(0));
                out.remove(0);
            }
        }

        System.out.println(count);
    }
}

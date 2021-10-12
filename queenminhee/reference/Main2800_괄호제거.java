import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main2800_괄호제거 {
    public static String str;
    public static int len;
    public static int[] pair;
    public static boolean[] visited;
    public static Set<String> set;
    public static Stack<Integer> stack;
    public static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        str = br.readLine();
        len = str.length();
        pair = new int[len];
        visited = new boolean[len];
        stack = new Stack<>();
        sb = new StringBuilder();
        set = new HashSet<>();

        for(int i = 0; i < len; i++){
            char c = str.charAt(i);
            if(c =='('){
                stack.add(i);
            }else if(c == ')'){
                int idx = stack.pop();
                pair[idx] = i;
                pair[i] = idx;
            }
        }

        dfs(0);

        set.remove(str);                            //괄호 하나도 안지운 경우
        List<String> list = new ArrayList<>(set);
        Collections.sort(list);
        sb = new StringBuilder();
        for (String s : list) {
            sb.append(s).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void dfs(int depth) {
        if(depth == len){
            set.add(sb.toString());
            return;
        }

        if(str.charAt(depth) == '('){
            visited[depth] = true;
            dfs(depth+1);
            visited[depth] = false;
        }

        if(str.charAt(depth) ==')' && visited[pair[depth]]){ //')' 의 짝꿍 '(' 를 제거한 경우
            visited[depth] = true;
            dfs(depth + 1);
            visited[depth] = false;
        }else{ // 숫자였거나 제거 대상이 아닌 경우
            sb.append(str.charAt(depth));
            dfs(depth + 1);
            sb.deleteCharAt(sb.length() - 1);
        }

    }
}

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main1068_트리 {
    public static int deleteNode, root, answer;
    public static  List<Integer> childs[];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        childs = new ArrayList[N];
        for(int i = 0; i < N; i++){
            childs[i] = new ArrayList<>();
        }

        for(int i = 0; i < N; i++){
            int x = sc.nextInt();
            if(x == -1) root = i;
            else childs[x].add(i);
        }

        deleteNode = sc.nextInt();

        dfs(root);
        System.out.println(answer);
    }

    private static void dfs(int v) {
        if(v == deleteNode){
            return;
        }

        if(childs[v].size() == 0){
            answer++;
            return;
        }

        for(int i = 0; i < childs[v].size(); i++){
            if(childs[v].get(i) == deleteNode && childs[v].size() == 1){
                answer++;
                return;
            }
            dfs(childs[v].get(i));
        }
    }
}

/*
[배운점 및 아쉬운점]
1. 트리는 직접 연습을 한적이 거의 없어서 그런지 문제를 읽기만 하면 당황;
2. 혼자힘으로 풀지 못함. 다른 코드들을 여러가지 참조하다가 가장 이해하기 쉬었던 코드로 따라서 구현함.
*/
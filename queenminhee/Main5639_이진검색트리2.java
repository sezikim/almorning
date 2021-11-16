package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main5639_이진검색트리2 {
    static List<Integer> tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tree = new ArrayList<>();
        while(true){
            String nxtNode = br.readLine();
            if(nxtNode == null) break;
            tree.add(Integer.parseInt(nxtNode));
        }

        postOrder(0, tree.size()-1);
    }

    public static void postOrder(int start, int end){
        if(start > end) return;

        int root = tree.get(start);

        int right = start + 1;
        for(int i = start + 1; i <= end; i++){
            if(root < tree.get(i)){
                right = i;
                break;
            }
        }

        postOrder(start + 1, right -1);
        postOrder(right, end);
        System.out.println(root);
    }
}

//이 코드를 이해하는데 참조해야했던 post 들
//https://penglog.tistory.com/96
//https://velog.io/@jodawooooon/BOJ-5639-S1-%EC%9D%B4%EC%A7%84-%EA%B2%80%EC%83%89-%ED%8A%B8%EB%A6%AC

//다만 직접 노드를 만들어주는 편이 탐색속도나 메모리 측면에서는 좀 더 좋은거같다.
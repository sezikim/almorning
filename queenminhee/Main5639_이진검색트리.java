package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main5639_이진검색트리 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Node root = new Node(Integer.parseInt(br.readLine()));

        while(true){
            String nxtNode = br.readLine();
            if(nxtNode == null || nxtNode.equals("")) break;
            root.add(Integer.parseInt(nxtNode));
        }

        postOrder(root);
    }

    private static void postOrder(Node node) {
        if(node == null) return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.num);
    }
}
class Node{
    int num;
    Node left;
    Node right;

    Node(int num){
        this.num = num;
    }

    void add(int num){
        if(num < this.num){//현재노드보다 작은 경우 왼쪽 탐색
            if(this.left == null){ //단 비어있으면 새로 만들어주고 종료
                this.left = new Node(num);
            }else{
                this.left.add(num);
            }
        }else{
            if(this.right == null){
                this.right = new Node(num);
            }else{
                this.right.add(num);
            }
        }
    }
}

// 1. 왼쪽은 항상 부모, 오른쪽형제보다 작다. => Node add 함수를 만들면서 작을땐 왼쪽노드 탐색, 클땐 오른쪽 노드를 탐색하면서 null 인 위치에 바인딩
// 2. runtime 에러 났었던것 => 입출력 마지막에 없는것 처리를 isEmpty()로만 처리해서 계속 에러가 났었다. (null 인 경우도 고려해야한다.)
// 2-1. isEmpty() || null 도 안됬다. (isBlank()도 마찬가지.. null check 를 먼저 하고 => 그다음에 공백문자열인지 확인하는 순서가 되야했다.
// 2-2 없는값에 대한 체크를 먼저 진행해야한다 !!!!!!!!!
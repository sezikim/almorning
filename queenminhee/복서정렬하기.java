import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class 복서정렬하기 {
    public static void main(String[] args) {

    }
}

class Solution {
    int n;
    List<Person> list = new ArrayList<>();
    public int[] solution(int[] weights, String[] head2head) {
        n = weights.length;
        int[] answer = new int[n];

        for(int num = 0; num < n; num++){
            char[] wln = head2head[num].toCharArray();
            double winCnt = 0;
            int winAndBigger = 0;
            double total = 0;

            for(int i = 0; i < n; i++){
                if(wln[i] == 'W'){
                    winCnt++;
                    if(weights[num] < weights[i]){
                        winAndBigger++;
                    }
                }
                if(wln[i] != 'N'){
                    total++;
                }
            }

            Person person;
            if(total == 0){
                person = new Person(num+1, weights[num], winAndBigger, 0.0);
            }else{
                person = new Person(num+1, weights[num], winAndBigger, winCnt/total);//나눈후에 double
            }
            list.add(person);
        }

        Collections.sort(list);

        for(int i = 0; i < list.size(); i++){
            answer[i] = list.get(i).num;
        }
        System.out.println(Arrays.toString(answer));
        return answer;
    }
}

class Person implements Comparable<Person>{
    int num;
    int weight;
    int winCnt;
    double winPercent;

    Person(int num, int weight, int winCnt, double winPercent){
        this.num = num;
        this.weight = weight;
        this.winCnt = winCnt;
        this.winPercent = winPercent;
    }

    @Override
    public int compareTo(Person o){
        if(this.winPercent != o.winPercent){
            return Double.compare(o.winPercent, this.winPercent);
        }else if(this.winCnt != o.winCnt){
            return Integer.compare(o.winCnt, this.winCnt);
        }else if(this.weight != o.weight){
            return Integer.compare(o.weight, this.weight);
        }else {
            return Integer.compare(this.num, o.num);
        }
    }
}

/*
푸는 시간을 오래 잡아먹은 이유
1. double 타입으로 처음에 Percent 를 하지 않고, float 타입으로 계산을 했었음. -> 5~ 테케 X
2. N(게임하지 않은 값)을 카운트할 때 조건문을 잘못씀 ('W'가 아닌값들 중 'N'가 아닌값 으로 카운트하는바람에 승률계산이 잘못됬었음) => 1~4 테케 x
3. IDE 없이 연습을 하려다보니 오타를 찾는것도 꽤나 오래걸림...

다른 사람들 코드를 보면서 배운 점.
1. Java Sort 가 여러가지 방법이 있지만 그중 Stream 을 활용하는게 가장 간단해 보인다; 조만간 이 방법에 대해서 한번 다시 리뷰를..!
* */
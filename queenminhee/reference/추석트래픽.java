package programmers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class 추석트래픽 {
    public int solution(String[] lines) throws ParseException {
        int answer = 0;
        int[] count = new int[lines.length];

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SSS");
        for(int i = 0; i < lines.length; i++){
            String[] pivot = lines[i].split(" ");
            Date pivotDate = format.parse(pivot[1]);
            long preEnd = pivotDate.getTime();

            for(int j = i; j < lines.length; j++){
                String[] nxt = lines[j].split(" ");
                Date nxtDate = format.parse(nxt[1]);
                long nxtEnd = nxtDate.getTime();
                double nxtS = Double.parseDouble(nxt[2].substring(0, nxt[2].length() - 1));
                long nxtStart = (long) (nxtEnd - nxtS * 1000 + 1);

                if(nxtStart < preEnd + 1000){
                    count[i]++;
                    answer = Math.max(answer, count[i]);
                }
            }
        }

        return answer;
    }
}

/*
[배운것 정리]
1. SimpleDate -> DateFormat 방법중 한가지 더 알게됨; (이런방법이...!)
2. 시작지점과 끝지점을 관리해야함은 알고있었는데, 어떻게 기준을 잡아야할지 (ms까지 어떻게 관리하지..?했음) 고민이 많이 되서 참조
3. 핵심은 앞에 작업의 끝나는시각 + 1 초(1000ms) 보다 다음 작업의 시작점이 작으면 겹치는 공간이니 count 해주는 것..!

* */
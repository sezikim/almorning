import java.awt.geom.QuadCurve2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main2002_추월 {
    public static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        List<String> in = new ArrayList<>();
        List<String> out = new ArrayList<>();
        Set<String> temp = new HashSet<>();
        int cnt = 0;

        for(int i = 0; i < N; i++){
            in.add(br.readLine());
        }
        for(int i = 0; i < N; i++){
            out.add(br.readLine());
        }

        while(!out.isEmpty()){
            String inner = in.get(0);
            String outer = out.get(0);
            if(inner.equals(outer)){
                in.remove(inner);
                out.remove(outer);
            }else {
                if(temp.contains(inner)){
                    in.remove(inner);
                }else{
                    cnt++;
                    temp.add(outer);
                    out.remove(outer);
                }
            }
        }

        System.out.println(cnt);
    }
}

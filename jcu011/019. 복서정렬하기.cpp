#include<bits/stdc++.h>
using namespace std;
const double EPSILON = 1e-10;

struct Info{ // 승률, 이긴수, 몸무게, 번호
    double rate;
    int win, weight, num;
    bool operator < (const Info &t) const {
        if(fabs(rate-t.rate) < EPSILON) { // 실수형의 == 비교는 오차로 인해 오류가 생길 수 있으므로 예외처리를 해야함
            if(win==t.win){
                if(weight == t.weight) return num < t.num;
                return weight > t.weight;
            }
            return win > t.win;
        }
        return rate > t.rate;
    }
    Info(double r=0, int wi=0, int we=0, int n=0)
        :rate(r), win(wi), weight(we), num(n){}
};

vector<Info> v; 

vector<int> solution(vector<int> w, vector<string> h2h) {
    for(int i=0; i<h2h.size(); i++){
        double win=0, lose=0, rate=0;
        int heavy=0, weight=w[i], num=i+1;
        
        bool isN = true;
        for(int j=0; j<h2h[i].size(); j++){
            if(i==j) continue;
            if(h2h[i][j]!='N') isN = false;
            if(h2h[i][j]=='W') {
                if(w[i] < w[j]) heavy++;
                win++;
            } 
            else if(h2h[i][j]=='L') lose++;
        }
        if(!isN) {
            rate = win/(win+lose);
        }
        
        v.push_back(Info(rate, heavy, weight, num));
    }
    
    sort(v.begin(), v.end());
    vector<int> ans;
    for(auto val : v) ans.push_back(val.num);
    return ans;
}
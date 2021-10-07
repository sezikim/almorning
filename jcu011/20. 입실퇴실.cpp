#include<bits/stdc++.h>
using namespace std;

set<int> s;
vector<int> solution(vector<int> et, vector<int> lv) {
    for(int i=0; i<et.size(); i++) {
        et[i]--;
        lv[i]--;
    }
    vector<int> ans(et.size());
    int now=0;
    for(int i=0; i<lv.size(); i++){
        if(s.count(lv[i])==0){
            while(1){
                s.insert(et[now]);
                if(lv[i]==et[now++]) break;
            }    
        }
        ans[lv[i]] += (s.size()-1);
        s.erase(lv[i]);
        for(auto val : s){
            ans[val]++;
        }
    }
    
    return ans;
}
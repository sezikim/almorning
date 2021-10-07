#include<bits/stdc++.h>
#define xx first
#define yy second
using namespace std;
using pss = pair<string,string>;

pss divideBalancedString(string p){
    int cnt = 0;
    int sz = p.size();
    for(int i=0; i<sz; i++){
        if(p[i]=='(') cnt++;
        else cnt--;
        if(cnt==0) return {p.substr(0,i+1), p.substr(i+1, sz-i-1)};
    }
}

bool chkRightString(string p){
    // 반드시 균형잡힌 문자열이 입력되어야만 한다.
    int cnt = 0;
    for(int i=0; i<p.size(); i++){
        if(p[i]=='(') cnt++;
        else cnt--;
        if(cnt<0) return false;
    }
    return true;
}

string func(string p){
    if(p=="") return p; // 1
    pss t = divideBalancedString(p); // 2
    string u = t.xx;
    string v = t.yy;
    
    if(chkRightString(u)){ // 3
        return u + func(v);
    } else { // 4
        string res = '(' + func(v) + ')';
        string tmp = u.substr(1,u.size()-2);
        for(int i=0; i<tmp.size(); i++){
            if(tmp[i]=='(') tmp[i] = ')';
            else tmp[i] = '(';
        }
        return res + tmp;
    }
}

string solution(string p) {
    return func(p);
}
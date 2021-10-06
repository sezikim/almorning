#include<bits/stdc++.h>
using namespace std;

string S,T;

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>S>>T;
    deque<char> dq;
    for(int i=0; i<T.size(); i++) dq.push_back(T[i]);

    bool isrvs = false;
    while(dq.size()!=S.size()){
        if(!isrvs) {
            if(dq.back()=='B') isrvs = !isrvs;
            dq.pop_back();
        } else {
            if(dq.front()=='B') isrvs = !isrvs;
            dq.pop_front();
        }

    }
    
    int idx = 0;
    bool chk = true;
    while(!dq.empty()){
        if(!isrvs){
            if(S[idx++]!=dq.front()) {
                chk = false;
                break;
            }
            dq.pop_front();
        } else {
            if(S[idx++]!=dq.back()) {
                chk = false;
                break;
            }
            dq.pop_back();
        }
    }
    cout<<chk;
}

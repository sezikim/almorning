#include<bits/stdc++.h>
using namespace std;

int N;
map<string, int> m;

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N;
    string str;
    int n = 1;
    for(int i=0; i<N; i++){
        cin>>str;
        m[str] = n++;
    }

    vector<int> v;
    for(int i=0; i<N; i++){
        cin>>str;
        v.push_back(m[str]);
    }

    int ans = 0;
    for(int i=0; i<N; i++){
        for(int j=i+1; j<N; j++){
            if(v[i]>v[j]) {
                ans++;
                break;
            }
        }
    }

    cout<<ans;
}
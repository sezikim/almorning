#include<bits/stdc++.h>
#define xx first
#define yy second
using namespace std;
using pii = pair<int,int>;
using tpi = tuple<int,int,int>;

const int INF = 0x3f3f3f3f;

int N,D;
vector<int> adj[52]{};
int vi[52]{};

int bfs(int st){
    int ret =0;
    queue<int> q;
    if(!vi[st]) q.push(st);
    vi[st] = true;

    while(!q.empty()){
        int cn = q.front();
        q.pop();

        int cnt = 0;
        for(auto next : adj[cn]){
            if(vi[next]) continue;
            q.push(next);
            vi[next] = true;
            cnt++;
        }

        if(!cnt) ret++;
    }

    return ret;
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N;
    int k, st;
    for(int i=0; i<N; i++){
        cin>>k;
        if(k==-1) st = i;
        adj[k].push_back(i);
    }
    cin>>D;
    vi[D] = true;

    cout<<bfs(st);
}

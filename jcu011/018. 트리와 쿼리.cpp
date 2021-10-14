#include<bits/stdc++.h>
using namespace std;

int N,root,Q;
vector<int> adj[100001]{};
bool vi[100001]{};
int dp[100001]{};

int makeTree(int par){
    vi[par] = true;
    for(auto next : adj[par]){
        if(vi[next]) continue;
        dp[par] += makeTree(next);
    }
    return dp[par]+1;
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N>>root>>Q;
    int a,b;
    for(int i=0; i<N-1; i++){
        cin>>a>>b;
        adj[a].push_back(b);
        adj[b].push_back(a);
    }

    makeTree(root);

    int u;
    while(Q--){
        cin>>u;
        cout<<dp[u]+1<<'\n';
    }
}
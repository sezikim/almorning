#include<bits/stdc++.h>
using namespace std;
const int INF = 0x3f3f3f3f;

int N,M,H;
bool vi[31][11]{}, flag;
int ans=INF;

bool chkSelfAll(){
    for(int i=1; i<=N; i++){
        int cur = i;
        for(int j=1; j<=H; j++){
            if(cur>1 && vi[j][cur-1]) cur--;    
            else if(cur<N && vi[j][cur]) cur++;
        }
        if(cur!=i) return false;
    }
    return true;
}

void dfs(int dpt, int limit, int idx){
    if(flag) return;
    if(dpt==limit) {
        if(chkSelfAll()) flag = true;
        return;
    }

    for(int k=idx+1; k<N*H; k++){
        if(k%N==0) continue;
        int i = k/N+1;
        int j = k%N;
        if(vi[i][j] || vi[i][j-1] || vi[i][j+1]) continue;
        vi[i][j] = true;
        dfs(dpt + 1, limit, k);
        vi[i][j] = false;
    }

}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N>>M>>H;
    int a,b;
    for(int i=0; i<M; i++){
        cin>>a>>b;
        vi[a][b] = true;
    }

    for(int i=0; i<4; i++){
        dfs(0,i,0);
        if(flag) {
            ans = i;
            break;
        }
    }
    
    if(ans==INF) cout<<-1;
    else cout<<ans;
}
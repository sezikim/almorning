#include<bits/stdc++.h>
using namespace std;
const int INF = 0x3f3f3f3f;

int N,M;
int dp[11001][150]{}; // dp[p][x] : x만큼 점프하여 p에 도착했을때 점프횟수의 최솟값
bool vi[11001]{};

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N>>M;
    int t;
    for(int i=0; i<M; i++){
        cin>>t;
        vi[t] = true;
    }
    
    memset(dp,0x3f,sizeof(dp));
    dp[1][0] = 0;
    dp[2][1] = 1;
    for(int i=3; i<=N; i++){
        if(vi[i]) continue;
        int j = i-141; // 1부터 141까지의 합은 10011 이므로 최대로 건널 수 있는 돌의 개수는 140개
        if(j<2) j = 2;
        for(; j<i; j++){
            dp[i][i-j] = min({dp[i][i-j], dp[j][i-j-1]+1, dp[j][i-j]+1, dp[j][i-j+1]+1});
        }
    }

    int ans = INF;
    for(int i=0; i<150; i++){
        ans = min(ans, dp[N][i]);
    }
    if(ans>=INF) cout<<-1;
    else cout<<ans;
}
// 크누스 최적화 : 솔직히 기억안남

#include<bits/stdc++.h>
using namespace std;
const int INF = 1e9;

int T,N;
int cost[501]{};
int ps[501]{};
int dp[501][501]{};
int opt[501][501]{};

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0); cout.tie(0);
    cin>>T;
    while(T--){
        cin>>N;
        for(int i=1; i<=N; i++){
            cin>>cost[i];
            ps[i] = ps[i-1] + cost[i];
            opt[i][i] = i;
        }

        for(int d=1; d<N; d++){
            for(int x=1; x+d<=N; x++){
                int y = x+d;
                dp[x][y] = INF;
                for(int k=opt[x][y-1]; k<=opt[x+1][y]; k++){
                    int t = dp[x][k]+dp[k+1][y]+ps[y]-ps[x-1];
                    if(dp[x][y] > t){
                        dp[x][y] = t;
                        opt[x][y] = k;
                    }
                }
            }
        }

        cout<<dp[1][N]<<'\n';
    }
}
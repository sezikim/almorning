#include<bits/stdc++.h>
using namespace std;
const int INF = 0x3f3f3f3f;

int N,M;
int m[1010][1010]{};
int dp[1010][1010][3]{};
int dx[3] = {-1,0,1};

int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	memset(m, 0x3f, sizeof(m));
	memset(dp, 0x3f, sizeof(dp));

	cin>>N>>M;
	for(int i=1; i<=N; i++){
		for(int j=1; j<=M; j++) 
			cin>>m[i][j];
	}

	for(int j=1; j<=M; j++){
		dp[1][j][0] = m[1][j];
		dp[1][j][1] = m[1][j];
		dp[1][j][2] = m[1][j];
	}

	for(int y=2; y<=N; y++){
		for(int x=1; x<=M; x++){
			dp[y][x][0] = min(dp[y-1][x+1][1], dp[y-1][x+1][2]) + m[y][x];
			dp[y][x][1] = min(dp[y-1][x][0], dp[y-1][x][2]) + m[y][x];
			dp[y][x][2] = min(dp[y-1][x-1][0], dp[y-1][x-1][1]) + m[y][x];
		}
	}
    
	int min_ = INF;
	for(int j=1; j<=M; j++){
		min_ = min(min_, dp[N][j][0]);
		min_ = min(min_, dp[N][j][1]);
		min_ = min(min_, dp[N][j][2]);
	}

	cout<<min_;
}
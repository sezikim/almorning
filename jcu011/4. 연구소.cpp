#include<bits/stdc++.h>
#define xx first
#define yy second
using namespace std;
typedef pair<int,int> pii;

int N,M,wall=0, maxArea = 0;
int m[9][9]{};
bool vi[9][9]{};
int dy[4] = {-1,1,0,0};
int dx[4] = {0,0,-1,1};
vector<pii> stk;

int bfs(){
    memset(vi,0,sizeof(vi));
    queue<pii> q;
    int cnt = stk.size();
    for(auto val : stk){
        q.push(val);
        vi[val.xx][val.yy] = true;
    }

    while(!q.empty()){
        int cy = q.front().xx;
        int cx = q.front().yy;
        q.pop();

        for(int i=0; i<4; i++){
            int ny = cy + dy[i];
            int nx = cx + dx[i];
            if(ny<0 || nx<0 || ny>=N || nx>=M || vi[ny][nx]) continue;
            if(m[ny][nx]==1) continue;
            q.push({ny,nx});
            vi[ny][nx] = true;
            cnt++;
        }
    }
    
    return cnt;
}

void dfs(int dpt, int idx){
    if(dpt==3) {
        int area = wall + bfs();
        maxArea = max(maxArea, N*M-area);
        return;
    }

    for(int i=idx+1; i<N*M; i++){
        int y = i/M;
        int x = i%M;
        if(m[y][x]!=0) continue;
        m[y][x] = 1;
        dfs(dpt+1, i);
        m[y][x] = 0;
    }
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N>>M;
    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++){
            cin>>m[i][j];
            if(m[i][j]==1) wall++;
            if(m[i][j]==2) stk.push_back({i,j});
        }
    }
    wall+=3;
    dfs(0,-1);
    cout<<maxArea;
}
#include<bits/stdc++.h>
#define xx first
#define yy second
using namespace std;
typedef pair<int,int> pii;

int N,Q;
int m[65][65]{};
int mm[65][65]{};
bool vi[65][65]{};
int dy[4] = {-1,1,0,0};
int dx[4] = {0,0,-1,1};

int bfs(int sty, int stx){
    int ret = 1;
    queue<pii> q;
    q.push({sty,stx});
    vi[sty][stx] = true;

    while(!q.empty()){
        int cy = q.front().xx;
        int cx = q.front().yy;
        q.pop();

        for(int i=0; i<4; i++){
            int ny = cy + dy[i];
            int nx = cx + dx[i];
            if(ny<0 || nx<0 || ny>=N || nx>=N) continue;
            if(vi[ny][nx] || !m[ny][nx]) continue;
            q.push({ny,nx});
            vi[ny][nx] = true;
            ret++;
        }
    }
    return ret;
}

void updateIce(){
    queue<pii> q;
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            if(m[i][j]==0) continue;
            int adjIce = 0;
            for(int k=0; k<4; k++){
                int ny = i + dy[k];
                int nx = j + dx[k];
                if(ny<0 || nx<0 || ny>=N || nx>=N || m[ny][nx]==0) continue;
                adjIce++;
            }
            if(adjIce < 3) q.push({i,j});
        }
    }

    while(!q.empty()){
        m[q.front().xx][q.front().yy]--;
        q.pop();
    }
}

void rotate2(int y, int x, int n){
    for(int j=x,ii=0; j<x+n; j++,ii++){
        for(int i=y+n-1,jj=0; i>=y; i--,jj++){
            mm[ii][jj] = m[i][j];
        }
    }

    for(int i=y, ii=0; i<y+n; i++, ii++){
        for(int j=x, jj=0; j<x+n; j++, jj++){
            m[i][j] = mm[ii][jj];
        }
    }
}

void rotate(int L){
    int n = 1<<L;
    for(int i=0; i<N; i+=n){
        for(int j=0; j<N; j+=n){
            rotate2(i,j,n);
        }
    }
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N>>Q;
    N = 1<<N;
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            cin>>m[i][j];
        }
    }
    int l;
    while(Q--){
        cin>>l;
        if(l) rotate(l);
        updateIce();
    }

    int remains=0;
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            remains += m[i][j];
        }
    }
    cout<<remains<<'\n';
    
    int maxIce = 0;
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            if(vi[i][j] || !m[i][j]) continue;
            maxIce = max(maxIce, bfs(i,j));
        }
    }
    if(maxIce<2) cout<<0;
    else cout<<maxIce;
}
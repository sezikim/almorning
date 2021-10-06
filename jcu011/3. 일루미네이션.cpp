#include<bits/stdc++.h>
#define xx first
#define yy second
using namespace std;
using ll = long long;
using pii = pair<int,int>;
using pll = pair<ll,ll>;
using tpi = tuple<int,int,int>;
using tpl = tuple<ll,ll,ll>;
using dpi = pair<pii,pii>;
using dpl = pair<pll,pll>;
using pis = pair<int,string>;
using psi = pair<string,int>;

const int INF = 0x3f3f3f3f;
const ll INF64 = 0x3f3f3f3f3f3f3f3f;

int N,M;
int m[105][105]{};
bool vi[105][105]{};
int dy[6] = {-1,1,0,0,-1,1};
int dx[6] = {0,0,-1,1,0,0};

int cnt(int y, int x){
    int res = 6;
    int tmp = y%2==1 ? 1 : -1;
    for(int i=0; i<6; i++){
        int ny = y + dy[i];
        int nx = x + dx[i];
        if(i>3) nx += tmp;
        if(ny<1 || nx<1 || ny>N || nx>M) continue;
        if(m[ny][nx]) res--;
    }
    return res;
}

void bfs(){
    queue<pii> q;
    q.push({0,0});
    vi[0][0] = true;

    while(!q.empty()){
        int cy = q.front().xx;
        int cx = q.front().yy;
        q.pop();
        int tmp = cy%2==1 ? 1 : -1;

        for(int i=0; i<6; i++){
            int ny = cy + dy[i];
            int nx = cx + dx[i];
            if(i>3) nx += tmp;
            if(ny<0 || nx<0 || ny>N+1 || nx>M+1 || vi[ny][nx]) continue;
            q.push({ny,nx});
            vi[ny][nx] = true;
        }
    }
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>M>>N;

    for(int i=1; i<=N; i++){
        for(int j=1; j<=M; j++){
            cin>>m[i][j];
            if(m[i][j]) vi[i][j] = true;
        }
    }

    bfs();

    int sum = 0;
    for(int i=1; i<=N; i++){
        for(int j=1; j<=M; j++){
            if(m[i][j]) {
                sum += cnt(i,j);
            } else if(!vi[i][j]) {
                sum -= (6-cnt(i,j));
            }
        }
    }
    cout<<sum;
}


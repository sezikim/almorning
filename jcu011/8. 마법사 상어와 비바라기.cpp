// 5:38
#include<bits/stdc++.h>
#define xx first
#define yy second
using namespace std;
typedef pair<int,int> pii;

int N,M;
int m[51][51]{};
vector<pii> cl;
vector<pii> bug;
bool vi[51][51]{};
int dy[8] = {0,-1,-1,-1,0,1,1,1};
int dx[8] = {-1,-1,0,1,1,1,0,-1};

void makeCloud(){
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            if(m[i][j]>=2 && !vi[i][j]) {
                cl.push_back({i,j});
                m[i][j] -= 2;
            }
        }
    }
}

int da[4] = {-1,-1,1,1};
int db[4] = {-1,1,1,-1};
void copyWater(){
    vector<int> t;
    for(auto pos : bug){
        int ca = pos.xx;
        int cb = pos.yy;
        int cnt = 0;
        for(int i=0; i<4; i++){
            int na = ca + da[i];
            int nb = cb + db[i];
            if(na<0 || nb<0 || na>=N || nb>=N) continue;
            if(m[na][nb]) cnt++;
        }
        t.push_back(cnt);
    }
    for(int i=0; i<bug.size(); i++) {
        m[bug[i].xx][bug[i].yy] += t[i];
    }
}

void move(int d, int s){
    memset(vi,0,sizeof(vi));
    bug.clear();
    for(auto pos : cl){
        int ny = (50*N + pos.xx + s*dy[d])%N;
        int nx = (50*N + pos.yy + s*dx[d])%N;
        m[ny][nx]++;
        vi[ny][nx] = true;
        bug.push_back({ny,nx});
    }
    cl.clear();
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N>>M;
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            cin>>m[i][j];
        }
    }
    cl.push_back({N-1,0});
    cl.push_back({N-1,1});
    cl.push_back({N-2,0});
    cl.push_back({N-2,1});
    int d,s; // 방향, 거리
    for(int i=0; i<M; i++) {
        cin>>d>>s; d--;
        move(d,s);
        copyWater();
        makeCloud();
    }
    
    int sum=0;
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            sum += m[i][j];
        }
    }
    cout<<sum;
}

#include<bits/stdc++.h>
#define xx first
#define yy second
using namespace std;
typedef pair<int,int> pii;
typedef pair<int,pii> piii;

struct Info{
    int n,a,b,c,d;
    Info(int n=0, int a=0, int b=0, int c=0, int d=0):
    n(n), a(a), b(b), c(c), d(d) {}
};

int N;
vector<Info> v;
int m[21][21]{};
int dy[4] = {-1,1,0,0};
int dx[4] = {0,0,-1,1};
pii pos[410]{};
int vi[21][21]{};
int vi2[21][21]{};
vector<piii> cand;

void chkEmpty(int maxvi, int idx){
    memset(vi2,0,sizeof(vi2));
    cand.clear();
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            if(vi[i][j]==maxvi && m[i][j]==0){
                for(int k=0; k<4; k++){
                    int ny = i + dy[k];
                    int nx = j + dx[k];
                    if(ny<0 || nx<0 || ny>=N || nx>=N || m[ny][nx]!=0) continue;
                    vi2[i][j]++;
                }
                cand.push_back({vi2[i][j],{i,j}});
            }
        }
    }

    sort(cand.begin(), cand.end(), [&](piii a, piii b) -> bool {
        if(a.xx==b.xx) {
            if(a.yy.xx == b.yy.xx) return a.yy.yy < b.yy.yy;
            else return a.yy.xx < b.yy.xx;
        }
        return a.xx > b.xx;
    });
    
    int y = cand[0].yy.xx;
    int x = cand[0].yy.yy;
    m[y][x] = v[idx].n;
    pos[v[idx].n] = {y,x};
}

int chkStudent(int idx){
    memset(vi,0,sizeof(vi));
    int nd = v[idx].n;
    int chk[4] = {v[idx].a, v[idx].b, v[idx].c, v[idx].d};
    int maxvi = 0;
    for(int k=0; k<4; k++){
        int cn = chk[k];
        if(pos[cn].xx == -1) continue;
        int cy = pos[cn].xx;
        int cx = pos[cn].yy;
        for(int i=0; i<4; i++){
            int ny = cy + dy[i];
            int nx = cx + dx[i];
            if(ny<0 || nx<0 || ny>=N || nx>=N || m[ny][nx]!=0) continue;
            vi[ny][nx]++;
            maxvi = max(maxvi, vi[ny][nx]);
        }
    }
    return maxvi;
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N;
    int n,a,b,c,d;
    for(int i=0; i<N*N; i++){
        cin>>n>>a>>b>>c>>d;
        v.push_back(Info(n,a,b,c,d));
    }
    memset(pos,-1,sizeof(pos));
    for(int i=0; i<N*N; i++){
        int maxvi = chkStudent(i);
        chkEmpty(maxvi, i);
    }

    int sum=0;
    for(auto nd : v){
        int cn = nd.n;
        int cy = pos[cn].xx;
        int cx = pos[cn].yy;
        int cnt = 0;
        for(int i=0; i<4; i++){
            int ny = cy + dy[i];
            int nx = cx + dx[i];
            if(ny<0 || nx<0 || ny>=N || nx>=N) continue;
            int nm = m[ny][nx];
            if(nm==nd.a || nm==nd.b || nm==nd.c || nm==nd.d) cnt++;
        }
        if(cnt==0) continue;
        else if(cnt==1) sum+=1;
        else if(cnt==2) sum+=10;
        else if(cnt==3) sum+=100;
        else if(cnt==4) sum+=1000;
    }
    
    cout<<sum;
}
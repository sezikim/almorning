#include<bits/stdc++.h>
using namespace std;
typedef pair<int,int> pii;
#define xx first
#define yy second

int N;
int m[2][101][101]{};
bool vi[2][101][101]{};
int dy[4] = {-1,1,0,0};
int dx[4] = {0,0,-1,1};

void bfs(int t, int y, int x){
    memset(vi,0,sizeof(vi));
    queue<pii> q;
    q.push({y,x});
    vi[t][y][x] = true;
    int cl = m[t][y][x];

    while(!q.empty()){
        int cpy = q.front().xx;
        int cpx = q.front().yy;
        q.pop();

        for(int i=0; i<4; i++){
            int npy = cpy + dy[i];
            int npx = cpx + dx[i];

            if(npy<0 || npx<0 || npy>=N || npx>=N || vi[t][npy][npx]) continue;
            if(m[t][npy][npx]!=cl) continue;
            m[t][npy][npx] = -1;
            q.push({npy,npx});
            vi[t][npy][npx] = true;
        }
    }
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N;
    string str;
    for(int i=0; i<N; i++){
        cin>>str;
        for(int j=0; j<N; j++){
            if(str[j]=='R') {m[0][i][j]=0; m[1][i][j]=0;}
            else if(str[j]=='G') {m[0][i][j]=1; m[1][i][j]=0;}
            else {m[0][i][j]=2; m[1][i][j]=1;} // 'B'
        }
    }
    int c1=0,c2=0;
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            if(m[0][i][j]!=-1) {
                bfs(0,i,j);
                c1++;
            }
            if(m[1][i][j]!=-1){
                bfs(1,i,j);
                c2++;
            }
        }
    }
    cout<<c1<<' '<<c2;
}
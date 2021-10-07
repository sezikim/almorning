#include<bits/stdc++.h>
using namespace std;

int N,sum=0;
int m[500][500]{};
int dy[4] = {0,1,0,-1};
int dx[4] = {-1,0,1,0};
int mask[5][5][4]{};
int tmask[5][5]={
    {0,0,2,0,0},
    {0,10,7,1,0},
    {5,0,0,0,0},
    {0,10,7,1,0},
    {0,0,2,0,0}
};

void move(int cy, int cx, int d){
    int y=cy-2, x=cx-2;
    int remains = m[cy][cx];
    for(int i=0; i<5; i++){
        for(int j=0; j<5; j++){
            int ny = y + i;
            int nx = x + j;
            int ns = m[cy][cx] * mask[i][j][d] / 100;
            remains -= ns;
            if(ny<0 || nx<0 || ny>=N || nx>=N) {
                sum += ns;
                continue;
            }
            m[ny][nx] += ns;
        }
    }

    int ny = cy + dy[d];
    int nx = cx + dx[d];
    if(ny<0 || nx<0 || ny>=N || nx>=N) {
        sum += remains;
        return;
    }
    m[ny][nx] += remains;
}

void tornado(){
    int cnt=1, idx = 1, dir=0, cy=N/2, cx=N/2;
    while(cnt < N*N){
        for(int i=0; i<2; i++){
            for(int k=0; k<idx; k++){
                int ny = cy + dy[dir];
                int nx = cx + dx[dir];
                move(ny, nx, dir);
                m[ny][nx] = cnt++;
                if(cnt>=N*N) return;
                cy = ny;
                cx = nx;
            }
            dir = (dir+1)%4;
        }
        idx++;
    }
}

void initMask(int K){
    for(int i=0; i<K; i++){
        for(int j=0; j<K; j++){
            mask[i][j][0] = tmask[i][j];
        }
    }
    for(int j=K-1,ii=0; j>=0,ii<K; j--,ii++){
        for(int i=0,jj=0; i<K,jj<K; i++,jj++){
            mask[ii][jj][1] = tmask[i][j];
        }
    }
    for(int i=K-1,ii=0; i>=0,ii<K; i--,ii++){
        for(int j=K-1,jj=0; j>=0,jj<K; j--,jj++){
            mask[ii][jj][2] = tmask[i][j];
        }
    }
    for(int j=0,ii=0; j<K,ii<K; j++,ii++){
        for(int i=K-1,jj=0; i>=0,jj<K; i--,jj++){
            mask[ii][jj][3] = tmask[i][j];
        }
    }
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N;
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            cin>>m[i][j];
        }
    }
    initMask(5);
    tornado();
    cout<<sum;
}
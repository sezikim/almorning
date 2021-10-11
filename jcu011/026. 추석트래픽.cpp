#include <string>
#include <vector>
#include <algorithm>
#define xx first
#define yy second
using namespace std;
typedef pair<int,int> pii;

int solution(vector<string> lines) {
    vector<pii> times;
    for(auto &line : lines){
        char buf[25];
        line.pop_back();
        int h,m,s,d;
        double _s, _d;
        sscanf(line.c_str(), "%s %d:%d:%lf %lf", buf,&h,&m,&_s,&_d);
        h *= 3600000;
        m *= 60000;
        s = (int)(_s*1000.0 + 0.5);
        d = (int)(_d*1000.0 + 0.5);
        int ed = h + m + s;
        int st = ed - d + 1;
        times.push_back({st,1});
        times.push_back({ed,-1});
    }
    sort(times.begin(), times.end());
    int st,ed=0, ans=0, cnt=0;
    for(st=0; st<times.size(); st++){
        while(ed<times.size()){
            if(times[ed].xx - times[st].xx >= 1000) break;
            if(times[ed].yy == 1) cnt++;
            ed++;
        }
        
        ans = max(ans, cnt);
        if(times[st].yy==-1) cnt--;
    }
    
    return ans;
}
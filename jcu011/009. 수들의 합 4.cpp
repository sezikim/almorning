#include<bits/stdc++.h>
using namespace std;
using ll = long long;

ll N;
ll K;
ll ps[200002]{};
map<ll, ll> m;

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N;
    cin>>K;
    ll res = 0, t;
    m[0] = 1;
    for(ll i=1; i<=N; i++){
        cin>>t;
        ps[i] = ps[i-1] + t;

        ll tmp = ps[i] - K;
        res += m[tmp];

        m[ps[i]]++;
    }
    
    cout<<res;
}

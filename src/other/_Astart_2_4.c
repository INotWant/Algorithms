#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
const int mod=1e9+7;
const int N=1e5+5;
int a[N<<1];
ll num[N<<1];
ll dp[N<<1];

//*
ll ksm(ll a,ll b){
    ll s=1;
    while(b){
        if(b&1){
            a%=mod;
            s%=mod;
            s*=a;
        }
        a%=mod;
        a*=a;
        b>>=1;
    }
    return s%mod;
}
ll C(ll n,ll m){
    if(n==m||m==0) return 1;
    ll up=1,down=1;
    if(m>n-m) m=n-m;
    for(int i=0;i<m;i++){
        up=up*(n-i)%mod;
        down=down*(i+1)%mod;
    }
    return up*ksm(down,mod-2)%mod;
}
*/

// TLE
/*
const int MAXN = 1e5;
bool arr[MAXN+1] = {false};
vector<int> produce_prim_number(){
    vector<int> prim;
    prim.push_back(2);
    int i,j;
    for(i=3; i*i<=MAXN; i+=2){
        if(!arr[i]){
            prim.push_back(i);
            for(j=i*i; j<=MAXN; j+=i)
                arr[j] = true;
        }
    }
    while(i<=MAXN){
        if(!arr[i])
            prim.push_back(i);
        i+=2;
    }
    return prim;
}

//计算n!中素因子p的指数
int Cal(int x, int p){
    int ans = 0;
    long long rec = p;
    while(x>=rec){
        ans += x/rec;
        rec *= p;
    }
    return ans;
}

//计算n的k次方对M取模
int Pow(long long n, int k, int M){
    long long ans = 1;
    while(k){
        if(k&1){
            ans = (ans * n) % M;
        }
        n = (n * n) % M;
        k >>= 1;
    }
    return ans;
}

//计算C(n,m)
int C(int n, int m, vector<int> prim){
    long long ans = 1;
    int num;
    for(int i=0; i<prim.size() && prim[i]<=n; ++i){
        num = Cal(n, prim[i]) - Cal(m, prim[i]) - Cal(n-m, prim[i]);
        ans = (ans * Pow(prim[i], num, mod)) % mod;
    }
    return ans;
}
*/

int main(){
//    vector<int> prim = produce_prim_number();
    int T;
    scanf("%d",&T);
    while(T--){
        int n;
        scanf("%d",&n);
        int up=n<<1;
        for(int i=1;i<=up;i++){
            scanf("%d",&a[i]);
            num[i]=0;
        }
        for(int i=1;i<=up;i++) num[a[i]]++;
        dp[0]=1;
        ll sum=0;
        for(int i=1;i<=up;i++){
            if((sum&1)==0&&(num[i]&1)==0){ //偶偶
                dp[i]=dp[i-1]*C(num[i],num[i]>>1,prim)%mod;
            }
            else if((sum&1)==0&&(num[i]&1)==1){//偶奇
                dp[i]=dp[i-1]*C(num[i],num[i]>>1,prim)*2%mod;
            }
            else if((sum&1)==1&&(num[i]&1)==1){//奇奇
                dp[i]=dp[i-1]*C(num[i],num[i]>>1,prim)%mod;
            }
            else { //奇偶
                dp[i]=dp[i-1]*C(num[i],num[i]>>1,prim)%mod;
                if(num[i]){//不为0的偶数(即>=2)
                    dp[i]+=dp[i-1]*C(num[i],(num[i]-2)>>1,prim)%mod;
                    dp[i]%=mod;
                }
            }
            sum+=num[i];
        }
        printf("%lld\n",dp[up]);
    }
    return 0;
}

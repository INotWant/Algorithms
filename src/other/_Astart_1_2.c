#include<bits/stdc++.h>
using namespace std;
const int maxn=1010;
struct node{
	int l;
	int r;
}a[maxn],b[maxn];
int main()
{
	int n,t,cnt,id,len,ans;
	scanf("%d",&t);
	while(t--)
	{
		scanf("%d",&n);
		for(int i=1;i<=n;i++) scanf("%d%d",&a[i].l,&a[i].r);
		b[1]=a[1];cnt=1;ans=0;
		for(int i=2;i<=n;i++)
		{
			if(b[cnt].r<a[i].l||b[cnt].l>a[i].r) b[++cnt]=a[i];
			else {b[cnt].l=max(b[cnt].l,a[i].l);b[cnt].r=min(b[cnt].r,a[i].r);}
		}
		if(b[2].r<b[1].l)
		    id=b[1].l;
		else
		    id=b[1].r;
		for(int i=2;i<=cnt;i++)
		{
			if(b[i].r<b[i-1].l)
			{
				len=id-b[i].r;id=b[i].r;
				if(len&1&&b[i].l<id&&i+1<=cnt&&b[i+1].r<b[i].l)
				{
					len++;id--;
				}
			}
			else
			{
				len=b[i].l-id;id=b[i].l;
				if(len&1&&b[i].r>id&&i+1<=cnt&&b[i+1].l>b[i].r)
				{
					len++;id++;
				}
			}
			ans+=len/2;
			if(len&1) ans++;
		}
		printf("%d\n",ans);
	}
	return 0;
}
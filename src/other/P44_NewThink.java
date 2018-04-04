package other;

public class P44_NewThink {

    public boolean isMatch(String s, String p) {
        int i = 0,j = 0,is=-1,ip=-1;
        char[] ss = s.toCharArray();
        char[] pp = p.toCharArray();
        while (i<ss.length){
            if (j<pp.length&&(ss[i]==pp[j]||pp[j]=='?')){
                i++;j++;
            }else if (j<pp.length&&pp[j]=='*'){
                ip = j++;       // 存储,以用以回溯
                is = i;
            }else if (ip!=-1){  // 回溯
                j = ip+1;
                i = ++is;
            }else return false;
        }
        while (j<pp.length&&pp[j]=='*') j++;

        return j==p.length();
    }

}

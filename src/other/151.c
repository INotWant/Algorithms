#include "stdio.h"

void invert(char *, int);
char *reverseWords(char *);

void invert(char *s, int len){
    int i, j = len;
    char c;
    for(i = 0; i < j; ++i, --j){
            c = s[i];
            s[i] = s[j];
            s[j] = c;
    }
}

char* reverseWords(char* s) {
    if(s == NULL || s[0] == '\0')
        return s;
    int i = 0,j = 0;
    while(s[++j] != '\0')
        ;
    --j;
    for(; i <= j; ++i){
        if(s[i] == ' ' ){
            int k = i+1;
            while(k <= j && s[k] == ' '){
                ++k;
            }
	    if (k == j+1){
		j = i-1;
		s[j+1] = '\0';
		break;
	    }
            if (k - i > 1 || i == 0){
                int t = i == 0 ? 0 : i + 1;
                int temp = i == 0 ? k - i : k - i - 1;
                for(; k <= j; ++k, ++t){
                    s[t] = s[k];
                }
                j -= temp;
                s[j+1] = '\0';
            }
        }
    }
    invert(s, j);
    printf("%s\n", s);
    char *p = s;
    int len = 0;
    for(i = 0; i <= j; i++){
	int t_i = i;
        while(p[i] != ' ' && i <= j){
            ++i;
        }
        invert(p+t_i, i-t_i-1);
    }
    return s;
}


int main(void){
	// char *p = "ting";	/* 注意 p 指向的是常量字符串，故不可通过 p 改变字符串的内容 */
	char s[] = " the  sky  is blue ";
	reverseWords(s);
	printf("%s\n", s);
}

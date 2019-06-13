#include<stdio.h>
#include<stdlib.h>
#include<string.h>

int Atoint(char *s,int radix)    //s是给定的radix进制字符串
{
    int ans=0,i;
    for(i=0;i<strlen(s);i++)
    {
        char t=s[i];
        if(t>='0'&&t<='9') ans=ans*radix+t-'0';
        else ans=ans*radix+t-'a'+10;
    }
        return ans;
}

int main(){
	char s[100];
    int r;
	int num ;
	char str[30];
    printf("*******进制转换器*******\n");
    printf("请输入一个数，并指定它是几进制数：\n");
    scanf("%s",s);
    scanf("%d",&r);
    num = Atoint(s,r);
    printf("二进制：");
    itoa(num,str,2);//2即是代表转换为2进制
    printf("%s\n",str);
    printf("八进制：%3o\n",num);    //按八进制格式输出，保留5位高位补零
    printf("十进制：%3d\n",num);    //按十进制格式输出，保留3位高位补零
    printf("十六进制：%3x\n",num);    //按十六进制格式输出，保留5位高位补零
    return 0;
}

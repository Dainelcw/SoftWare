#include<stdio.h>
#include<stdlib.h>
#include<string.h>

int Atoint(char *s,int radix)    //s�Ǹ�����radix�����ַ���
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
    printf("*******����ת����*******\n");
    printf("������һ��������ָ�����Ǽ���������\n");
    scanf("%s",s);
    scanf("%d",&r);
    num = Atoint(s,r);
    printf("�����ƣ�");
    itoa(num,str,2);//2���Ǵ���ת��Ϊ2����
    printf("%s\n",str);
    printf("�˽��ƣ�%3o\n",num);    //���˽��Ƹ�ʽ���������5λ��λ����
    printf("ʮ���ƣ�%3d\n",num);    //��ʮ���Ƹ�ʽ���������3λ��λ����
    printf("ʮ�����ƣ�%3x\n",num);    //��ʮ�����Ƹ�ʽ���������5λ��λ����
    return 0;
}

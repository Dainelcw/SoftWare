/*���һ������ת��������
���������ơ��˽��ơ�ʮ���ơ�ʮ������������ת����
ͨ���˿��⣬���������ַ�������ʽ��������ƻ���ĸ��ֲ�����
����Ҫ��
��1������������ơ��˽��ơ�ʮ���ơ�ʮ����������
��2�������������ת����������Ƶ�����
��3����������������档
*/
#include<stdio.h>
#include<iostream>
#include<string>
#include <bitset>

using namespace std;

int Atoint(string s,int radix)    //s�Ǹ�����radix�����ַ���
{
    int ans=0;
    for(int i=0;i<s.size();i++)
    {
        char t=s[i];
        if(t>='0'&&t<='9') ans=ans*radix+t-'0';
        else ans=ans*radix+t-'a'+10;
    }
        return ans;
}

int main(){
    cout<<"*******����ת����*******"<<endl;
    cout<<"������һ��������ָ�����Ǽ���������"<<endl;
    string s;
    int r;
    cin>>s;
    cin>>r;
    int num = Atoint(s,r);
    cout<<"�����ƣ�";
    cout<<bitset<sizeof(num)*2>(num)<<endl;
    printf("�˽��ƣ�%3o\n",num);    //���˽��Ƹ�ʽ���������5λ��λ����
    printf("ʮ���ƣ�%3d\n",num);    //��ʮ���Ƹ�ʽ���������3λ��λ����
    printf("ʮ�����ƣ�%3x\n",num);    //��ʮ�����Ƹ�ʽ���������5λ��λ����
    return 0;
}

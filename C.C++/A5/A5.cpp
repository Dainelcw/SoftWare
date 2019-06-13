/*设计一个进制转换器程序。
包括二进制、八进制、十进制、十六进制数互相转换。
通过此课题，熟练掌握字符串、格式输出、进制换算的各种操作。
课题要求：
（1）可输入二进制、八进制、十进制、十六进制数；
（2）将已输入的数转换成其余进制的数；
（3）具有输入输出界面。
*/
#include<stdio.h>
#include<iostream>
#include<string>
#include <bitset>

using namespace std;

int Atoint(string s,int radix)    //s是给定的radix进制字符串
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
    cout<<"*******进制转换器*******"<<endl;
    cout<<"请输入一个数，并指定它是几进制数："<<endl;
    string s;
    int r;
    cin>>s;
    cin>>r;
    int num = Atoint(s,r);
    cout<<"二进制：";
    cout<<bitset<sizeof(num)*2>(num)<<endl;
    printf("八进制：%3o\n",num);    //按八进制格式输出，保留5位高位补零
    printf("十进制：%3d\n",num);    //按十进制格式输出，保留3位高位补零
    printf("十六进制：%3x\n",num);    //按十六进制格式输出，保留5位高位补零
    return 0;
}

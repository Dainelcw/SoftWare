/*设计一个加密程序。包括明文与密钥的转换。
通过此课题，熟练掌握数组、格式输出、字符串处理、类型转换等。
课题要求：
（1）输入任意一段明文M，以及密钥K;
（2）根据以下公式将其转换为密文C。
     Ci  =  mi  +  K  ,其中i = 0,1,……n-1 , K 为密钥；
（3）具有输入输出界面。
*/
#include <stdio.h>
#include <stdlib.h>
#include <string>
#include <iostream>

using namespace std;
int main(){
    string M;
    int K;
    cout<<"请输入明文M和密钥K："<<endl;
    cin>>M;
    cin>>K;
    char C[100];
    strcpy(C,M.c_str());
    for(int i=0;i<M.length();i++){
        C[i] =  C[i]+K;
    }
    cout<<"加密后的密文是："<<endl;
    cout<<C<<endl;
    return 0;
}

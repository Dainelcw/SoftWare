/*���һ�����ܳ��򡣰�����������Կ��ת����
ͨ���˿��⣬�����������顢��ʽ������ַ�����������ת���ȡ�
����Ҫ��
��1����������һ������M���Լ���ԿK;
��2���������¹�ʽ����ת��Ϊ����C��
     Ci  =  mi  +  K  ,����i = 0,1,����n-1 , K Ϊ��Կ��
��3����������������档
*/
#include <stdio.h>
#include <stdlib.h>
#include <string>
#include <iostream>

using namespace std;
int main(){
    string M;
    int K;
    cout<<"����������M����ԿK��"<<endl;
    cin>>M;
    cin>>K;
    char C[100];
    strcpy(C,M.c_str());
    for(int i=0;i<M.length();i++){
        C[i] =  C[i]+K;
    }
    cout<<"���ܺ�������ǣ�"<<endl;
    cout<<C<<endl;
    return 0;
}

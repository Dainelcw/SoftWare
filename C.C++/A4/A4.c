/*设计一个加密程序。包括明文与密钥的转换。
通过此课题，熟练掌握数组、格式输出、字符串处理、类型转换等。
课题要求：
（1）输入任意一段明文M，以及密钥K;
（2）根据以下公式将其转换为密文C。
     Ci  =  mi  +  K  ,其中i = 0,1,……n-1 , K 为密钥；
（3）具有输入输出界面。
*/
#include<stdio.h>
#include<string.h>

void jiemi(){
		char M[100],C[100];
	int flag=1,K,i;
	while(flag){
		printf("请输入密文M和密钥K：\n");
		scanf("%s",M);
		scanf("%d",&K);
		strcpy(C,M);
		for(i=0;i<strlen(M);i++){
			C[i] =  C[i]-K;
			if((C[i]>='a'&&C[i]<='z')||(C[i]>='A'&&C[i]<='Z'))
				continue;
			else
			{
				printf("请输入有效密文和密钥。\n");
				break;
			}
			if(i==strlen(M))
				flag==0;
		}	
	}
	
	printf("解密后的明文是：%s\n",C);
}

void jiami(){
	char M[100],C[100];
	int flag=1,K,i;
	while(flag){
		printf("请输入明文M和密钥K：\n");
		scanf("%s",M);
		scanf("%d",&K);
		strcpy(C,M);
		for(i=0;i<strlen(C);i++){
			C[i] =  C[i]+K;
			if((C[i]>='a'&&C[i]<='z')||(C[i]>='A'&&C[i]<='Z'))
				flag=1;
			else
			{
				printf("请输入有效明文和密钥。\n");
				break;
			}
			if(i==strlen(M))
				flag=0;
		}	
	}
	
	printf("加密后的密文是：%s\n",C);
}

int main(){
    int type_m,flag1=1;
	while(flag1){
		printf("请输入您要选择的功能:\n");
		printf("1、加密\n");
		printf("2、解密\n");
		printf("0、退出\n");
		scanf("%d",&type_m);
		switch(type_m){
		case 1:
			jiami();
			break;
		case 2:
			jiemi();
			break;
		case 0:
			flag1=0;
			break;
		}
	}
	
    
    
    return 0;
}

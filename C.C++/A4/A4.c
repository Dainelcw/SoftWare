/*���һ�����ܳ��򡣰�����������Կ��ת����
ͨ���˿��⣬�����������顢��ʽ������ַ�����������ת���ȡ�
����Ҫ��
��1����������һ������M���Լ���ԿK;
��2���������¹�ʽ����ת��Ϊ����C��
     Ci  =  mi  +  K  ,����i = 0,1,����n-1 , K Ϊ��Կ��
��3����������������档
*/
#include<stdio.h>
#include<string.h>

void jiemi(){
		char M[100],C[100];
	int flag=1,K,i;
	while(flag){
		printf("����������M����ԿK��\n");
		scanf("%s",M);
		scanf("%d",&K);
		strcpy(C,M);
		for(i=0;i<strlen(M);i++){
			C[i] =  C[i]-K;
			if((C[i]>='a'&&C[i]<='z')||(C[i]>='A'&&C[i]<='Z'))
				continue;
			else
			{
				printf("��������Ч���ĺ���Կ��\n");
				break;
			}
			if(i==strlen(M))
				flag==0;
		}	
	}
	
	printf("���ܺ�������ǣ�%s\n",C);
}

void jiami(){
	char M[100],C[100];
	int flag=1,K,i;
	while(flag){
		printf("����������M����ԿK��\n");
		scanf("%s",M);
		scanf("%d",&K);
		strcpy(C,M);
		for(i=0;i<strlen(C);i++){
			C[i] =  C[i]+K;
			if((C[i]>='a'&&C[i]<='z')||(C[i]>='A'&&C[i]<='Z'))
				flag=1;
			else
			{
				printf("��������Ч���ĺ���Կ��\n");
				break;
			}
			if(i==strlen(M))
				flag=0;
		}	
	}
	
	printf("���ܺ�������ǣ�%s\n",C);
}

int main(){
    int type_m,flag1=1;
	while(flag1){
		printf("��������Ҫѡ��Ĺ���:\n");
		printf("1������\n");
		printf("2������\n");
		printf("0���˳�\n");
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

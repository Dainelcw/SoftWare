/*
�������ݣ�
���һ�����ֳ��򡣰�����������ַ������Լ��ַ����ȽϺ�ͳ�ơ�
ͨ���˿��⣬�����������顢��ʽ������ַ�������ȡ�
����Ҫ��
��1���������һ�ַ�����ÿ�β������ַ������ݡ����ȶ���ͬ��
��2�����ݣ�1���Ľ���������ַ������ж������Ƿ���ȷ�������ȷ�ʣ�
��3����������������档
*/

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>
 

char *randstr(char *str)
{
	int i,len;
	srand(time(NULL));
	len = rand()%10+2;
	for (i = 0; i < len; ++i)
	{
		switch ((rand() % 3))
		{
		case 1:
			str[i] = 'A' + rand() % 26;
			break;
		case 2:
			str[i] = 'a' + rand() % 26;
			break;
		default:
			str[i] = '0' + rand() % 10;
			break;
		}
	}
	str[i] = '\0';
	return str;
}

int main(){
    char name[20],inputstr[20];
    int i=0,error = 0,j=5;
	float gailv;
    while(j!=0){
		printf("%s\n",randstr(name));
		scanf("%s",&inputstr);
        while(name[i]!='\0'){
            if(name[i]!= inputstr[i]){
                error++;
            }
            i++;
        }
        if(error == 0){
            printf("������ȷ����ȷ�ʣ�100%\n");
        }else{
            gailv = (1-error/(float)strlen(name))*100;
            printf("���������ȷ�ʣ�");
            printf("%.2f",gailv);
            printf("%%\n");
        }
        j--;
        error=0;
        i=0;
    }

    return 0;
}

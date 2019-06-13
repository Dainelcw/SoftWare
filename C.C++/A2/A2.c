/*
课题内容：
设计一个打字程序。包括随机产生字符串，以及字符串比较和统计。
通过此课题，熟练掌握数组、格式输出、字符串处理等。
课题要求：
（1）随机产生一字符串，每次产生的字符串内容、长度都不同；
（2）根据（1）的结果，输入字符串，判断输入是否正确，输出正确率；
（3）具有输入输出界面。
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
            printf("输入正确，正确率：100%\n");
        }else{
            gailv = (1-error/(float)strlen(name))*100;
            printf("输入错误，正确率：");
            printf("%.2f",gailv);
            printf("%%\n");
        }
        j--;
        error=0;
        i=0;
    }

    return 0;
}

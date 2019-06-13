/*
课题内容:
    设计一个分数统计程序。包括学生信息的输入输出以及排序。
课题要求：
    （1）输入某班级学生的姓名、分数；
    （2）对（1）的分数进行降幂排列并输出；
    （3）具有输入输出界面。
*/
#include<stdio.h>
#include<string.h>

void swap(char *p,char *q)
{
char a[20];
strcpy(a,p);
strcpy(p,q);
strcpy(q,a);
}

int main(){
    int score[5],i,j,score_temp;
    char name[5][10],name_temp[10] = "";
    printf("********学生成绩排序系统**********\n");
    printf("请输入学生姓名成绩：\n");
    for(i=0;i<5;i++){
        scanf("%s %d",&name[i],&score[i]);
    }
    for(i=0;i<5;i++){
        for(j=i+1;j<5;j++){
            if(score[i]<score[j]){
                score_temp = score[i];
                score[i] = score[j];
                score[j] = score_temp;
                swap(name[i],name[j]);
            }
        }
    }
    printf("学生成绩排名如下：\n");
    for(i=0;i<5;i++){
        printf("%s %d\n",name[i],score[i]);
    }
    return 0;
}

/*
one 90
two 80
three 92
four 99
five 86
*/

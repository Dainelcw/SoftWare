/*
��������:
    ���һ������ͳ�Ƴ��򡣰���ѧ����Ϣ����������Լ�����
����Ҫ��
    ��1������ĳ�༶ѧ����������������
    ��2���ԣ�1���ķ������н������в������
    ��3����������������档
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
    printf("********ѧ���ɼ�����ϵͳ**********\n");
    printf("������ѧ�������ɼ���\n");
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
    printf("ѧ���ɼ��������£�\n");
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

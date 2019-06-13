#include<stdio.h>
#include<conio.h>
#include<string.h>
#include<stdlib.h>

 struct stu{
     char code[10];
     float s1,s2,s3;
     float score;
     char grade;
 }temp;

 void set_choice(char *choice){
     printf(">>>>>>>>>     1�����ļ���ȡ����        <<<<<<<\n");
     printf(">>>>>>>>>     2�����㲢��ʾ�ɼ�         <<<<<<<\n");
     printf(">>>>>>>>>     3�����ȼ���ѯ              <<<<<<<\n");
     printf(">>>>>>>>>     4���������ɼ�����         <<<<<<<\n");
     printf(">>>>>>>>>     5����ѧ�Ų�ѯ             <<<<<<<\n");
     printf(">>>>>>>>>     6����ѯ���еȼ�           <<<<<<<\n");
     printf(">>>>>>>>>     7�����浽�ļ�            <<<<<<<\n");
     printf(">>>>>>>>>     0���˳�ϵͳ               <<<<<<<\n");
     printf("��ѡ��������Ҫ�����Ĺ��ܣ��������֣���");
     *choice = getchar();
 }

 void dispinfo(){
    system("cls");
    //printf("\n\n");
    printf("*****************ѧ���ɼ�����ϵͳ*****************\n");
 }

 int readfile(struct stu *list){
    int n;
    int i = 0;
    FILE *fp;
    fp = fopen("note.dat","r");
    fscanf(fp,"%d",&n);
    for(i=0;i<n;i++){
        fscanf(fp,"%s%f%f%f\n",list[i].code,&list[i].s1,&list[i].s2,&list[i].s3);
    }
    printf("\tѧ��\t\tƽʱ�ɼ� ���гɼ� ��ĩ�ɼ�\n");
    for(i=0;i<n;i++){
        printf("\t%s\t%5.1lf\t%5.1lf\t%5.1lf\n",list[i].code,list[i].s1,list[i].s2,list[i].s3);
    }
    printf("***********��ȡ�ļ��ɹ�!�������������**************\n");
    //scanf("%d",i);
    getch();
    fclose(fp);
    return n;
 }

 void view(struct stu *list,int amount){
    int i=0;
    float average=0;
    printf("\tѧ��\t\t����\t�ȼ�\n");
    for(i=0;i<amount;i++){
        average+=list[i].score;
        printf("\t%10s\t%6.1f\t%c\n",list[i].code,list[i].score,list[i].grade);
    }
    average/=amount;
    printf("   ����������%d  ����ƽ���ɼ�%.1f\n",amount,average);
    printf("\n******�������������********\n");
    getch();
 }

 void discharge(struct stu *list,int amount){
    float temp;
    char temp1;
    char temp2[10];
    int i,j;
    for(i=0;i<amount;i++){      //�Է�������
        for(j=0;j<amount;j++){
            if(list[j].score>list[i].score){            //�ȽϷ���
                temp = list[j].score;
                list[j].score = list[i].score;
                list[i].score = temp;

                strcpy(temp2,list[j].code);
                strcpy(list[j].code,list[i].code);
                strcpy(list[i].code,temp2);

                temp = list[j].s1;
                list[j].s1 = list[i].s1;
                list[i].s1 = temp;

                temp = list[j].s2;
                list[j].s2 = list[i].s2;
                list[i].s2 = temp;

                temp = list[j].s3;
                list[j].s3 = list[i].s3;
                list[i].s3 = temp;

                temp1 = list[j].grade;
                list[j].grade = list[i].grade;
                list[i].grade = temp1;
            }
        }
    }
    view(list,amount);
 }

 void calculate(struct stu *list,int amount){
    int i=0;
    for(i=0;i<amount;i++){
        list[i].score=list[i].s1*0.3+list[i].s2*0.3+list[i].s3*0.4;
        if(list[i].score>=90)
            list[i].grade = 'A';
        else if(list[i].score>=80)
            list[i].grade = 'B';
        else if(list[i].score>=70)
            list[i].grade = 'C';
        else if(list[i].score>=60)
            list[i].grade = 'D';
        else
            list[i].grade = 'E';
    }
    dispinfo();
    view(list,amount);
 }

 void findgrade(struct stu *list,int amount){
    int i=0,j=0;
    char g;
    dispinfo();
    printf("\n��ѡ������Ҫ��ѯ�ĵȼ��������дA/B/C/D/E��:\n");
    g = getch();
    printf("\t\tѧ��\t\t����\t�ȼ�\n");
    for(i=0;i<amount;i++){
        if(list[i].grade==g){
            j++;
            printf("\t\t%10s\t%6.1f\t%c\n",list[i].code,list[i].score,list[i].grade);
        }
    }
    printf("\n\n    �ȼ�Ϊ%c�ܹ���%d�ˣ�������%d��ռ��������%.2lf\n",g,j,amount,(double)j/amount);
    printf("\n******�������������********\n");
    getch();
 }

 void gradelist(struct stu *list,int amount){
    int i=0;
    int j=0;
    printf("\t\t�ȼ�\t����\t�ٷֱ�\n");
    for(i=0;i<amount;i++){
        if(list[i].grade=='A')
            j++;
    }
    printf("\t\tA\t%d\t%6.3lf\n",j,(double)j/amount);

    for(i=0;i<amount;i++){
        if(list[i].grade=='B')
            j++;
    }
    printf("\t\tB\t%d\t%6.3lf\n",j,(double)j/amount);

    for(i=0;i<amount;i++){
        if(list[i].grade=='C')
            j++;
    }
    printf("\t\tC\t%d\t%6.3lf\n",j,(double)j/amount);

    for(i=0;i<amount;i++){
        if(list[i].grade=='D')
            j++;
    }
    printf("\t\tD\t%d\t%6.3lf\n",j,(double)j/amount);

    for(i=0;i<amount;i++){
        if(list[i].grade=='E')
            j++;
    }
    printf("\t\tE\t%d\t%6.3lf\n",j,(double)j/amount);

    printf("\n******�������������********\n");
    getch();
 }

void find(struct stu *list,int amount){
    int i=0;
    int flag=1;
    char temp[10];
    int flag2;
    dispinfo();
    do{
        flag2 = 1;
        printf("    ������ѧ�ţ�");
        scanf("%s",temp);
        for(i=0;i<amount;i++){
            flag = strcmp(temp,list[i].code);
            if(flag==0){
                flag2 = 0;
                break;
            }
        }
        if(flag2==1){
            printf("    ���������ѧ�Ų����ڣ�����������!\n");
        }
    }while(flag2);

    printf("\t\tѧ��\t����\t�ȼ�\n");
    printf("\t\t%10s\t%6.1f\t%6c\n",list[i].code,list[i].score,list[i].grade);
    printf("\n******�������������********\n");
    getch();
}

void save(struct stu *list,int amount){
    int i=0;
    FILE *fp;
    fp = fopen("out.dat","w");
    fprintf(fp,"\t\tѧ��\t����\t�ȼ�\n");
    for(i=0;i<amount;i++){
        printf("\t\t%10s\t%6.1f\t%6c\n",list[i].code,list[i].score,list[i].grade);
    }
    printf("\n\n****����ɹ�����鿴�������������*******\n");
    fclose(fp);
    getch();
}

int main(){
    //system("colorF9");
    struct stu list[100];
    char choice;
    int amount;
    while(1){
        dispinfo();
        set_choice(&choice);
        switch(choice){
        case '1':
            amount = readfile(list);
            break;
        case '2':
            calculate(list,amount);
            break;
        case '3':
            findgrade(list,amount);
            break;
        case '4':
            discharge(list,amount);
            break;
        case '5':
            find(list,amount);
            break;
        case '6':
            gradelist(list,amount);
            break;
        case '7':
            save(list,amount);
            break;
        case '0':
            system("cls");
            printf("*******��ӭ�ٴι���**********\n");
            getch();
            break;
        }
    }
    return 0;
}
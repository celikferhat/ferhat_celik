int theAge(int day,int month, int year, int today, int this_month, int this_year)
{
    int sum;
    sum = ((this_year*365 + this_month*30 + today)-(year*365+month*30+day))/365;
    return sum;
}

int daysLeft(int day,int month, int today, int this_month)
{
    int days;
    days = (((month*30+day)-(this_month*30 + today))+365)%365;
    printf("%d\n",days);
    return days;
}



#define PI 3.14
float diameter(float circumference)
{
    float dia;
    dia = circumference / PI;
    printf("%.2f\n",dia);
    return dia;
}

float ageOfTree(float diameter , float growth_factor)
{
    float age;
    age = diameter*growth_factor;
    return age;
}


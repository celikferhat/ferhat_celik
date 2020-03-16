
%{
#include "my.h"

%}






%%
[ \t\n]     ;
";;".*          return COMMENT;
"and"       return KW_AND;
"or"        return KW_OR;
"not"       return KW_NOT;
"equal"     return KW_EQUAL;
"less"      return KW_LESS;
"nil"       return KW_NIL;
"list"      return KW_LIST;
"append"    return KW_APPEND;
"concat"    return KW_CONCAT;
"set"       return KW_SET;
"deffun"    return KW_DEFFUN;
"for"       return KW_FOR;
"if"        return KW_IF;
"exit"      return KW_EXIT;
"load"      return KW_LOAD;
"disp"      return KW_DISP;
"true"      return KW_TRUE;
"false"     return KW_FALSE;

"+"         return OP_PLUS;
"-"         return OP_MINUS;
"/"         return OP_DIV;
"*"         return OP_MULT;
"("         return OP_OP;
")"         return OP_CP;
"**"        return OP_DBLMULT;
"\""         return OP_OC;
"'"         return OP_CC;
","         return OP_COMMA;

[a-zA-Z][_a-zA-z0-9]*  return IDENTIFIER;
[0-9]+     return VALUE;

.           {  printf("SYNTAX_ERROR %s cannot be tokenized",yytext); }

%%


int yywrap(){}
int main(){


 FILE *fp;
 char filename[50];
 printf("Enter the filename:");
 scanf("%s",filename);

 fp = fopen(filename,"r");
 yyin = fp;

 int ntoken;
 char *names[] = {NULL ,
    "KW_AND"     ,
    "KW_OR"      ,
    "KW_NOT"     ,
    "KW_EQUAL"   ,
    "KW_LESS"    ,
    "KW_NIL"     ,
    "KW_LIST"    ,
    "KW_APPEND"  ,
    "KW_CONCAT"  ,
    "KW_SET"     ,
    "KW_DEFFUN"  ,
    "KW_FOR"     ,
    "KW_IF"      ,
    "KW_EXIT"    ,
    "KW_LOAD"    ,
    "KW_DISP"    ,
    "KW_TRUE"    ,
    "KW_FALSE"   ,

    "OP_PLUS"    ,
    "OP_MINUS"   ,
    "OP_DIV"     ,
    "OP_MULT"    ,
    "OP_OP"      ,
    "OP_CP"      ,
    "OP_DBLMULT" ,
    "OP_OC"      ,
    "OP_CC"      ,
    "OP_COMMA"   ,

    "COMMENT"    ,
    "VALUE"      ,
    "IDENTIFIER"

 };
while(ntoken = yylex() ){
  printf("%s\n",names[ntoken]);
}


return 0;
}

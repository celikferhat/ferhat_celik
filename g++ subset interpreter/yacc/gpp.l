%{
#include "y.tab.h"
void yyerror (char *s);
int yylex();
%}
%%
[ \t\n]     ;
";;".*      return COMMENT;
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
"true"      {yylval.num = 1;  return KW_TRUE;}   
"false"     {yylval.num = 0; return KW_FALSE;}



"+"         return OP_PLUS;
"-"         return OP_MINUS;
"/"         return OP_DIV;
"*"         return OP_MULT;
"("         return OP_OP;
")"         return OP_CP;
"**"        return OP_DBLMULT;
"\""        return OP_OC;
"'"         return OP_CC;
","         return OP_COMMA;

[a-zA-Z][_a-zA-z0-9]*  return IDENTIFIER;
[0-9]+     {yylval.num = atoi(yytext);return VALUE;}
.                      {ECHO; yyerror ("unexpected character");}

%%
int yywrap (void) {return 1;}


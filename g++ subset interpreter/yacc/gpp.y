%{
void yyerror (char *s);
int yylex();
#include <stdio.h>     /* C declarations used in actions */
#include <stdlib.h>
#include <ctype.h>

%}

%union {int num; char id;}         /* Yacc definitions */
%start input

%token KW_AND
%token KW_OR
%token KW_NOT
%token KW_EQUAL
%token KW_LESS
%token KW_NIL
%token KW_LIST
%token KW_APPEND
%token KW_CONCAT
%token KW_SET
%token KW_DEFFUN
%token KW_FOR
%token KW_IF
%token KW_EXIT
%token KW_LOAD
%token KW_DISP
%token OP_PLUS
%token OP_MINUS
%token OP_DIV
%token OP_MULT
%token OP_OP
%token OP_CP
%token OP_DBLMULT
%token OP_OC
%token OP_CC
%token OP_COMMA
%token COMMENT



%token <id> IDENTIFIER
%token <num> VALUE KW_TRUE KW_FALSE
%type <num> line exp expl expb Bool


%%

/* descriptions of expected inputs     corresponding actions (in C) */


input :
        line input | line
      ;


line    :
		    exp 			{printf("Result: %d\n",$1);}
        | OP_OP  KW_LIST expl OP_CP  {printf("\n");}
        | expb				{ if($1) printf("Result: True\n" ); else printf("Result: False\n" );}
        | OP_OP KW_EXIT OP_CP 	{exit(EXIT_SUCCESS);}
        ;



exp    	:
        VALUE                          {$$ = $1;}
        | OP_OP OP_PLUS exp exp  OP_CP       {$$ = $3 + $4;}
        | OP_OP OP_MINUS exp exp OP_CP       {$$ = $3 - $4;}
        | OP_OP OP_MULT exp exp OP_CP       {$$ = $3 * $4;}
        | OP_OP OP_DIV  exp exp OP_CP       {$$ = $3 / $4;}
       	;

expb   :
	     Bool								{$$ = $1;}
       | OP_OP KW_AND expb expb OP_CP 		{ $$ = $3 && $4; 	}
       | OP_OP KW_OR expb expb OP_CP		{ $$ = $3 || $4;	} 
       | OP_OP KW_NOT expb OP_CP 			{ $$ = !($3); 		}
       | OP_OP KW_EQUAL expb expb OP_CP 	{ $$ =  ( ((!($3) || $4) && ($3 || !($4) ))  );	}
       ;

Bool : KW_TRUE {$$ = $1;}
     | KW_FALSE {$$ = $1;}
	 ;

expl   :
       VALUE          { $$=$1;printf("%d ",$1); }
       | expl VALUE   { printf("%d ",$2); }
       ;




%%                     /* C code */




int main (void) {


	return yyparse ( );
}

void yyerror (char *s) {fprintf (stderr, "%s\n", s);}

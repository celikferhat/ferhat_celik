;Ferhat ÇELİK 151044014
(defvar KW_AND     1)   ;v
(defvar KW_OR      2)   ;v
(defvar KW_NOT     3)   ;n
(defvar KW_EQUAL   4)   ;q
(defvar KW_LESS    5)
(defvar KW_NIL     6)
(defvar KW_LIST    7)   ;m
(defvar KW_APPEND  8)   ;k
(defvar KW_CONCAT  9)   ;k
(defvar KW_SET     10)  ;g
(defvar KW_DEFFUN  11)  ;f
(defvar KW_FOR     12)
(defvar KW_IF      13)  ;h
(defvar KW_EXIT    14)
(defvar KW_LOAD    15)
(defvar KW_DISP    16)
(defvar KW_TRUE    17)    ;b
(defvar KW_FALSE   18)    ;b
(defvar KW_WHILE   19)    ;w

(defvar OP_PLUS    20)    ;o
(defvar OP_MINUS   21)    ;o
(defvar OP_DIV     22)    ;o
(defvar OP_MULT    23)    ;o
(defvar OP_OP      24)    ;a
(defvar OP_CP      25)    ;d
(defvar OP_DBLMULT 26)
(defvar OP_OC      27)
(defvar OP_CC      28)    ;t
(defvar OP_COMMA   29)    ;c

(defvar COMMENT    30)
(defvar VALUE      31)    ;e
(defvar IDENTIFIER 32)    ;i
(defvar SYNTAX_ERROR 33)  ;s






(defvar Keywords (list "and" "or" "not" "equal" "less" "nil" "list" "append" "concat" "set" "deffun" "for" "if" "exit" "load" "disp" "true" "false" "while") )
(defvar Operators (list "+" "-" "*" "/" "(" ")" "**" "\"" ","  "'" ) )
(defvar Comments (list ";;") )


(defun delimiterp (c) (position c " "))

(defun my-split (string &key (delimiterp #'delimiterp))
  (loop :for beg = (position-if-not delimiterp string)
    :then (position-if-not delimiterp string :start (1+ end))
    :for end = (and beg (position-if delimiterp string :start beg))
    :when beg :collect (subseq string beg end)
    :while end))


(defun replace-all (string part replacement &key (test #'char=))
    "Returns a new string in which all the occurences of the part
    is replaced with replacement."
      (with-output-to-string (out)
        (loop with part-length = (length part)
              for old-pos = 0 then (+ pos part-length)
              for pos = (search part string
                                :start2 old-pos
                                :test test)
              do (write-string string out
                               :start old-pos
                               :end (or pos (length string)))
              when pos do (write-string replacement out)
              while pos)))


(defun numeric-string-p (string)
  (let ((*read-eval* nil))
    (ignore-errors (numberp (read-from-string string)))))

(defun string-include (string1 string2)
      (cond
       ((zerop (length string1)) nil) ; string1 is empty (no need to test it every time)
       ((> (length string1) (length string2)) nil) ; string1 is longer than string2
       ((string= string1 (subseq string2 0 (length string1))) string1) ; string2 starts with string1
       (t (string-include string1 (subseq string2 1)))))

(defun int_to_token (token)

(cond
  ((string= token "and")      KW_AND)
  ((string= token "or")       KW_OR)
  ((string= token "not")      KW_NOT)
  ((string= token "equal")    KW_EQUAL)
  ((string= token "less")     KW_LESS)
  ((string= token "nil")      KW_NIL)
  ((string= token "list")     KW_LIST)
  ((string= token "append")   KW_APPEND)
  ((string= token "concat")   KW_CONCAT)
  ((string= token "set")      KW_SET)
  ((string= token "deffun")   KW_DEFFUN)
  ((string= token "for")      KW_FOR)
  ((string= token "while")    KW_WHILE)
  ((string= token "if")       KW_IF)
  ((string= token "exit")     KW_EXIT)
  ((string= token "load")     KW_LOAD)
  ((string= token "disp")     KW_DISP)
  ((string= token "true")     KW_TRUE)
  ((string= token "false")    KW_FALSE)
  ((string= token "+")        OP_PLUS)
  ((string= token "-")        OP_MINUS)
  ((string= token "/")        OP_DIV)
  ((string= token "*")        OP_MULT)
  ((string= token "(")        OP_OP)
  ((string= token ")")        OP_CP)
  ((string= token "**")       OP_DBLMULT)
  ((string= token "\"")       OP_OC)
  ((string= token "'")        OP_CC)
  ((string= token ",")        OP_COMMA)




  )

)

(defun tokenize (line)

(setq local_token_list '() )

(loop for token in line

do
(cond ( (find token Comments :test #'equal)

      (push COMMENT local_token_list)
      (return)
 )
 ( (find token Keywords :test #'equal)

       (push (int_to_token token) local_token_list)
  )
  ( (find token Operators :test #'equal)

        (push (int_to_token token) local_token_list)
   )
   ( (numeric-string-p token)

         (push VALUE local_token_list)
    )
    ( (every #'alpha-char-p token)

          (push IDENTIFIER local_token_list)
     )
     (t (push SYNTAX_ERROR local_token_list) (format t "~S can not be tokenized" token) (terpri) )



)

)

(reverse local_token_list)

)


(defun read_file (filename)


(setq all_tokens '())
  (let ((in (open filename :if-does-not-exist nil)))
    (when in
      (loop for line = (read-line in nil)


  		while line do
          (setq fpart (replace-all line "(" " ( "))
          (setq spart (replace-all fpart ")" " ) "))
  			(setq all_tokens  (append all_tokens (tokenize (my-split spart) )) )



  		)
      (close in)))

    (if  (input_check (input_to_reg all_tokens ) )  (print_tokens  all_tokens ) (write-line "SYNTAX_ERROR")  )

  )



(defun input_check(input)

(setq result_expi (expi_check input))


  (if   (and (= 1 (length result_expi)) (string= result_expi "e")) t
      (progn
          (setq result_expb (expb_check result_expi))
          (if  (and (= 1 (length result_expb)) (string= result_expb "b")) t

            (progn
                (setq result_explisti (explisti_check result_expb))
                (if  (and (= 1 (length result_explisti)) (or (string= result_explisti "l") (string= result_explisti "e") )) t

                )

              )

           )

        )
  )





)
(defun expi_check (input)

(cond (
      (= 1 (length input))
      (if (string= input "e") input)
      )
      (t  (if (string-include "aoiid" input) (progn (setq input (replace-all input "aoiid" "e")) (expi_check input) )
          (progn
            (if (string-include "aoied" input) (progn (setq input (replace-all input "aoied" "e")) (expi_check input) )

            (progn
              (if (string-include "aoeid" input) (progn (setq input (replace-all input "aoeid" "e")) (expi_check input) )

              (progn
                (if (string-include "aoeed" input) (progn (setq input (replace-all input "aoeed" "e")) (expi_check input) )

                    (if (string-include "ahbd" input) (progn (setq input (replace-all input "ahbd" "e")) (expi_check input) )
                    (if (string-include "ahbe" input) (progn (setq input (replace-all input "ahbe" "ahb")) (expi_check input) )
                    (if (string-include "agied" input) (progn (setq input (replace-all input "agied" "e")) (expi_check input) )
                        (if (string-include "agild" input) (progn (setq input (replace-all input "agild" "e")) (expi_check input) )
                          (if (string-include "awbd" input) (progn (setq input (replace-all input "awbd" "e")) (expi_check input) )
                            (if (string-include "awbe" input) (progn (setq input (replace-all input "awbe" "awb")) (expi_check input) )
                                (if (string-include "afiaided" input) (progn (setq input (replace-all input "afiaided" "e")) (expi_check input) )
                                    (if (string-include "afiaii" input) (progn (setq input (replace-all input "afiaii" "afiai")) (expi_check input) )
                                        (if (string-include "afiaidee" input) (progn (setq input (replace-all input "afiaidee" "afiaide")) (expi_check input) )
                                          (if (string-include "aied" input) (progn (setq input (replace-all input "aied" "e")) (expi_check input) )
                                            (if (string-include "aiee" input) (progn (setq input (replace-all input "aiee" "aie")) (expi_check input) ) input )
                                           )
                                         )
                                     )
                                 )
                             )
                           )
                         )
                     )
                     )
                     )

                 )



                )

               )
              )

             )

            )
          )

         )

)


)

(defun expb_check(input)

(if
      (= 1 (length input))
      (if (string= input "b") input)

      (progn  (if (string-include "avbbd" input) (progn (setq input (replace-all input "avbbd" "b")) (expb_check input) )

                  (if (string-include "anbd" input) (progn (setq input (replace-all input "anbd" "b")) (expb_check input) )
                      (if (string-include "aqeed" input) (progn (setq input (replace-all input "aqeed" "b")) (expb_check input) )
                        (if (string-include "aqbbd" input) (progn (setq input (replace-all input "aqbbd" "b")) (expb_check input) )
                          (if (string-include "aqied" input) (progn (setq input (replace-all input "aqied" "b")) (expb_check input) )
                            (if (string-include "aqeid" input) (progn (setq input (replace-all input "aqeid" "b")) (expb_check input) )
                              (if (string-include "aqiid" input) (progn (setq input (replace-all input "aqiid" "b")) (expb_check input) )
                                (if (string-include "ahb" input) (expi_check input)
                                    (if (string-include "awb" input) (expi_check input)
                                        (if (string-include "afiaid" input) (expi_check input)
                                          (if (string-include "aied" input) (expi_check input) input )
                                         )
                                     )
                                 )
                               )
                             )
                           )
                         )
                       )
                  )


              )




         )

)
)


(defun explisti_check (input)

(if
      (= 1 (length input))
      (if (or (string= input "l")  (string= input "e")  )  input)

      (progn  (if (string-include "aklld" input) (progn  (setq input (replace-all input "aklld" "l")) (explisti_check input) )

                  (if (string-include "akeld" input) (progn (setq input (replace-all input "akeld" "l")) (explisti_check input) )
                      (if (string-include "tad" input) (progn  (setq input (replace-all input "tad" "l")) (explisti_check input) )
                        (if (string-include "tae" input) (progn  (setq input (replace-all input "tae" "ta")) (explisti_check input) )
                          (if (string-include "amed" input)  (progn (setq input (replace-all input "amed" "l")) (explisti_check input) )
                            (if (string-include "amee" input)  (progn (setq input (replace-all input "amee" "ame")) (explisti_check input) )
                            (if (string-include "ahb" input)  (expi_check input)
                              (if (string-include "awb" input)  (expi_check input)
                                  (if (string-include "afiaid" input)  (expi_check input)
                                    (if (string-include "aied" input)  (expi_check input)
                                          (if (string-include "ag" input)  (expi_check input)  nil)
                                        )
                                      )
                                    )
                                   )
                               )
                             )
                         )
                       )
                  )


              )




         )

)

)






(defun input_to_reg (input)  ; Token to regular expression     abeed
(setq regular_type "")

(loop for token in input
  do

  (cond
    (  (=  token OP_OP)
      (setq regular_type (concatenate 'string regular_type "a")))
    (  (=  token OP_CP)
      (setq regular_type (concatenate 'string regular_type "d")))
    ( (or  (= token OP_PLUS) (= token OP_MINUS) (= token OP_MULT) (= token OP_DIV)  )
      (setq regular_type (concatenate 'string regular_type "o")))
    (  (= token VALUE)
      (setq regular_type (concatenate 'string regular_type "e")))
    (  (=  token IDENTIFIER)
      (setq regular_type (concatenate 'string regular_type "i")))
    (  (=  token KW_CONCAT)
      (setq regular_type (concatenate 'string regular_type "k")))
    (  (=  token KW_APPEND)
      (setq regular_type (concatenate 'string regular_type "k")))
    (  (=  token OP_CC)
      (setq regular_type (concatenate 'string regular_type "t")))
    (  (=  token SYNTAX_ERROR)
      (setq regular_type (concatenate 'string regular_type "s")))
    (  (or (= token KW_AND) (= token KW_OR) )
      (setq regular_type (concatenate 'string regular_type "v")))
    (  (=  token KW_NOT)
      (setq regular_type (concatenate 'string regular_type "n")))
    (  (=  token KW_EQUAL)
      (setq regular_type (concatenate 'string regular_type "q")))
    (  (or (= token KW_TRUE) (= token KW_FALSE) )
      (setq regular_type (concatenate 'string regular_type "b")))
    (  (=  token KW_SET)
      (setq regular_type (concatenate 'string regular_type "g")))
    (  (=  token KW_IF)
      (setq regular_type (concatenate 'string regular_type "h")))
    (  (=  token KW_WHILE)
      (setq regular_type (concatenate 'string regular_type "w")))
    (  (=  token KW_DEFFUN)
      (setq regular_type (concatenate 'string regular_type "f")))
    (  (=  token KW_LIST)
      (setq regular_type (concatenate 'string regular_type "m")))





    )



  )


regular_type
)

(defun print_tokens(arg)
(loop for j in arg
  do
    (if (= j KW_AND)(print "KW_AND"))
    (if (= j KW_OR )(print   "KW_OR"))   ;v
    (if (= j KW_NOT) (print "KW_NOT"))   ;n
    (if (= j KW_EQUAL)(print "KW_EQUAL"))   ;q
    (if (= j KW_LESS )(print  "KW_LESS"))
    (if (= j KW_NIL  )(print "KW_NIL"))
    (if (= j KW_LIST )(print "KW_LIST"))   ;m
    (if (= j KW_APPEND) (print "KW_APPEND"))   ;k
    (if (= j KW_CONCAT) (print "KW_CONCAT"))   ;k
    (if (= j KW_SET)    (print "KW_SET"))  ;g
    (if (= j KW_DEFFUN) (print "KW_DEFFUN"))  ;f
    (if (= j KW_FOR)    (print "KW_FOR"))
    (if (= j KW_IF)     (print "KW_IF"))  ;h
    (if (= j KW_EXIT)   (print "KW_EXIT"))
    (if (= j KW_LOAD)   (print "KW_LOAD"))
    (if (= j KW_DISP)   (print "KW_DISP"))
    (if (= j KW_TRUE)   (print "KW_TRUE"))    ;b
    (if (= j KW_FALSE)  (print "KW_FALSE"))    ;b
    (if (= j KW_WHILE ) (print "KW_WHILE"))    ;w

    (if (= j OP_PLUS)  (print  "OP_PLUS"))    ;o
    (if (= j OP_MINUS) (print  "OP_MINUS"))    ;o
    (if (= j OP_DIV )  (print  "OP_DIV"))    ;o
    (if (= j OP_MULT )  (print "OP_MULT"))    ;o
    (if (= j OP_OP  )  (print  "OP_OP"))    ;a
    (if (= j OP_CP )    (print "OP_CP"))    ;d
    (if (= j OP_DBLMULT) (print "OP_DBLMULT"))
    (if (= j OP_OC )     (print "OP_OC"))
    (if (= j OP_CC  )    (print "OP_CC"))    ;t
    (if (= j OP_COMMA)   (print "OP_COMMA"))    ;c

    (if (= j COMMENT)    (print "COMMENT"))
    (if (= j VALUE )     (print "VALUE"))    ;e
    (if (= j IDENTIFIER) (print "IDENTIFIER"))    ;i
    (if (= j SYNTAX_ERROR) (print "SYNTAX_ERROR"))

  )

)

(defun gppinterpreter ()


(setq user_input "")
(write-line "[1] INTERPRETER")
(write-line "[2] INPUT FROM FILE")
(setq selection (read))
(if (= selection 1)
(progn
  (print "Type |exit| for terminete the program")

( loop
      (print "g++ >")
      (setq user_input (read-line))
      (if (string= user_input "exit") (return))


    (setq fpart (replace-all user_input "(" " ( "))
    (setq spart (replace-all fpart ")" " ) "))
    (if (input_check (input_to_reg (tokenize (my-split spart) )) ) (print_tokens (tokenize (my-split spart) ) ) (print "SYNTAX_ERROR"))


 ))
(progn
(print "Enter filename:")
(setq filename (read-line))
(read_file filename))
 )


  )

(gppinterpreter)
  ;(read_file "deneme.txt")

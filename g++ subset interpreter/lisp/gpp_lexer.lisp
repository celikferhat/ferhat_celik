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




(setq identifier_list '())
(setq identifier_list_value '())






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
  		(print line)
  		(terpri)
          (setq fpart (replace-all line "(" " ( "))
          (setq spart (replace-all fpart ")" " ) "))
  			
  			    (if (input_check (input_to_reg (tokenize (my-split spart) )) )
    				(get_result (my-split spart) (tokenize(my-split spart)) )
    				(print "SYNTAX_ERROR")
    			)
    			(terpri)


  		)
      (close in)))

    

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

(defun replace_sublist (liste index eleman last_index) 
    (setq new_list '())
    (setq i 0)
    
    (loop for token in liste
        do
        (if (= i index)
        (push eleman new_list)
        )
        (if (or(< i index) (> i (+ index last_index) )) 
        
        (push (nth i liste) new_list)
        )
        
        (setq  i (+ i 1))
    
    
    
    
    )
    (reverse new_list)




)

(defun remove_sublist (liste index last_index) 
    (setq new_list '())
    (setq i 0)
    
    (loop for token in liste
        do
        
        (if (or(< i index) (> i (+ index last_index) )) 
        
        (push (nth i liste) new_list)
        )
        
        (setq  i (+ i 1))
    
    
    
    
    )
    (reverse new_list)




)

(defun remove-nth (n list)
  (declare
    (type (integer 0) n)
    (type list list))
  (if (or (zerop n) (null list))
    (cdr list)
    (cons (car list) (remove-nth (1- n) (cdr list)))))

(defun get_result(input tokens)
  (setq arti (list 24 20 31 31 25))
  (setq eksi (list 24 21 31 31 25))
  (setq carpi (list 24 23 31 31 25))
  (setq bolu (list 24 22 31 31 25))
  (setq liste_sequance (list 24 7 31) )
  (setq append_sequance (list 24 8 31 24 7 25 25))
  (setq concat_sequance (list 24 9 24 7 25 24 7 25 25))


  (if  (search arti tokens)
    (progn
      (setq index (search arti tokens))
      (setq toplam (+  (parse-integer  (nth (+ index 2) input ))   (parse-integer  (nth (+ index 3) input) ) ) )
      (setq tokens (replace_sublist tokens index 31 4) )
      (setq input  (replace_sublist input index (write-to-string toplam) 4) )
	        


    )
  )
  (if  (search eksi tokens)
    (progn
      (setq index (search eksi tokens))
      (setq toplam (-  (parse-integer  (nth (+ index 2) input ))   (parse-integer  (nth (+ index 3) input) ) ) )
      (setq tokens (replace_sublist tokens index 31 4) )
      (setq input  (replace_sublist input index (write-to-string toplam) 4) )
	        


    )
  )
    (if  (search carpi tokens)
    (progn
      (setq index (search carpi tokens))
      (setq toplam (*  (parse-integer  (nth (+ index 2) input ))   (parse-integer  (nth (+ index 3) input) ) ) )
      (setq tokens (replace_sublist tokens index 31 4) )
      (setq input  (replace_sublist input index (write-to-string toplam) 4) )
	        


    )
  )
    (if  (search bolu tokens)
    (progn
      (setq index (search bolu tokens))
      (setq toplam (/  (parse-integer  (nth (+ index 2) input ))   (parse-integer  (nth (+ index 3) input) ) ) )
      (setq tokens (replace_sublist tokens index 31 4) )
      (setq input  (replace_sublist input index (write-to-string toplam) 4) )
	       


    )
  )  
    (if (search liste_sequance tokens)
    	(progn 
    		(setq index (search liste_sequance tokens))
    		(princ (nth (+ index 2) input))
    		(princ " ")
    		(setq input  (remove-nth (+ index 2) input))
    		(setq tokens (remove-nth (+ index 2) tokens))
    		
    				


    		)
    )
    ;(if (search (list 24 7 25) tokens) 
    ;	(progn 

    ;		(setq input (remove_sublist input (search (list 24 7 25) tokens) 2 ) )
    ;		(setq tokens(remove_sublist tokens (search (list 24 7 25) tokens) 2 ) )

   ; 	)

  ;  )

    (if (search append_sequance tokens)
    	(progn 

    		(setq index (search append_sequance tokens) )
    		(princ (nth (+ index 2) input))
    		(princ " ")
    		(setq input (remove-nth index input))
    		(setq tokens (remove-nth index tokens))
    		(setq input (remove-nth index input))
    		(setq tokens (remove-nth index tokens))
    		(setq input (remove-nth index input))
    		(setq tokens (remove-nth index tokens))
    		(setq input (remove-nth (+ index 3) input))
    		(setq tokens (remove-nth (+ index 3) tokens))
    		)
    )
    (if (search concat_sequance tokens)

    	(progn

    		(setq index (search concat_sequance tokens))

    		(setq input (remove-nth index input))
    		(setq tokens (remove-nth index tokens))
    		(setq input (remove-nth index input))
    		(setq tokens (remove-nth index tokens))
    		(setq input (remove-nth (+ index 3) input))
    		(setq tokens (remove-nth (+ index 3) tokens))
    		(setq input (remove-nth (+ index 3) input))
    		(setq tokens (remove-nth (+ index 3) tokens))
    		(setq input (remove-nth (+ index 3) input))
    		(setq tokens (remove-nth (+ index 3) tokens))
    		(setq input (remove-nth (+ index 3) input))
    		(setq tokens (remove-nth (+ index 3) tokens))
    		
    		

    		)

    )
    (if (or (search (list 24 1 17 17 25) tokens) (search (list 24 1 17 18 25) tokens) (search (list 24 1 18 17 25) tokens) (search (list 24 1 18 18 25) tokens))	;and 

    	(progn

    		(cond ((search (list 24 1 17 17 25) tokens) (setq index (search (list 24 1 17 17 25) tokens)) ) 
    				((search (list 24 1 17 18 25) tokens) (setq index (search (list 24 1 17 18 25) tokens)) )
    				((search (list 24 1 18 17 25) tokens) (setq index (search (list 24 1 18 17 25) tokens)) )
    				((search (list 24 1 18 18 25) tokens) (setq index (search (list 24 1 18 18 25) tokens)) )


    			)

    		

    			(setq ilk_ifade  (* (- (nth (+ index 2) tokens) 18 ) -1 ) )
    			(setq ikinci_ifade (* (- (nth (+ index 3) tokens) 18 ) -1 ) )
    	
    			(if (= (logand ilk_ifade ikinci_ifade) 1) 
    				(progn (setq input (replace_sublist input index "true" 4)) (setq tokens (replace_sublist tokens index 17 4)) ) 
    				(progn (setq input (replace_sublist input index "false" 4)) (setq tokens (replace_sublist tokens index 18 4)))
    				)
    		)
    )
        (if (or (search (list 24 2 17 17 25) tokens) (search (list 24 2 17 18 25) tokens) (search (list 24 2 18 17 25) tokens) (search (list 24 2 18 18 25) tokens))  ; or

    	(progn

    		(cond ((search (list 24 2 17 17 25) tokens) (setq index (search (list 24 2 17 17 25) tokens)) ) 
    				((search (list 24 2 17 18 25) tokens) (setq index (search (list 24 2 17 18 25) tokens)) )
    				((search (list 24 2 18 17 25) tokens) (setq index (search (list 24 2 18 17 25) tokens)) )
    				((search (list 24 2 18 18 25) tokens) (setq index (search (list 24 2 18 18 25) tokens)) )


    			)

    		

    			(setq ilk_ifade  (* (- (nth (+ index 2) tokens) 18 ) -1 ) )
    			(setq ikinci_ifade (* (- (nth (+ index 3) tokens) 18 ) -1 ) )
    	
    			(if (= (logior ilk_ifade ikinci_ifade) 1) 
    				(progn (setq input (replace_sublist input index "true" 4)) (setq tokens (replace_sublist tokens index 17 4)) ) 
    				(progn (setq input (replace_sublist input index "false" 4)) (setq tokens (replace_sublist tokens index 18 4)))
    				)
    		)
    )

    (if (or (search (list 24 3 17 25) tokens) (search (list 24 3 18 25) tokens) ) 															; not
    	(progn 
    		(cond ((search (list 24 3 17 25) tokens) (setq index (search (list 24 3 17 25) tokens)) ) 
    			  ((search (list 24 3 18 25) tokens) (setq index (search (list 24 3 18 25) tokens)) )
    		)
    		(setq ilk_ifade  (* (- (nth (+ index 2) tokens) 18 ) -1 ) )
    		(if (= ilk_ifade 1)
    				(progn (setq input (replace_sublist input index "false" 3)) (setq tokens (replace_sublist tokens index 18 3))) 
    				(progn (setq input (replace_sublist input index "true" 3)) (setq tokens (replace_sublist tokens index 17 3)) ) 
    				
    		)



    	)


    )
    (if (or (search (list 24 4 17 17 25) tokens) (search (list 24 4 17 18 25) tokens) (search (list 24 4 18 17 25) tokens) (search (list 24 4 18 18 25) tokens))  ; equal expb expb

    	(progn

    		(cond ((search (list 24 4 17 17 25) tokens) (setq index (search (list 24 4 17 17 25) tokens)) ) 
    				((search (list 24 4 17 18 25) tokens) (setq index (search (list 24 4 17 18 25) tokens)) )
    				((search (list 24 4 18 17 25) tokens) (setq index (search (list 24 4 18 17 25) tokens)) )
    				((search (list 24 4 18 18 25) tokens) (setq index (search (list 24 4 18 18 25) tokens)) )


    			)

    		

    			(setq ilk_ifade  (* (- (nth (+ index 2) tokens) 18 ) -1 ) )
    			(setq ikinci_ifade (* (- (nth (+ index 3) tokens) 18 ) -1 ) )
    	
    			(if (=  ilk_ifade ikinci_ifade) 
    				(progn (setq input (replace_sublist input index "true" 4)) (setq tokens (replace_sublist tokens index 17 4)) ) 
    				(progn (setq input (replace_sublist input index "false" 4)) (setq tokens (replace_sublist tokens index 18 4)))
    				)
    		)
    )    
    (if (or (search (list 24 4 31 31 25) tokens) (search (list 24 4 31 31 25) tokens) (search (list 24 4 31 31 25) tokens) (search (list 24 4 31 31 25) tokens))   ; equal expi expi

    	(progn

    		(cond ((search (list 24 4 31 31 25) tokens) (setq index (search (list 24 4 31 31 25) tokens)) ) 
    		)

    		

    			(setq ilk_ifade    (nth (+ index 2) input)  )
    			(setq ikinci_ifade (nth (+ index 3) input)  )
    			
    			(if (=  (parse-integer ilk_ifade) (parse-integer ikinci_ifade)) 
    				(progn (setq input (replace_sublist input index "true" 4)) (setq tokens (replace_sublist tokens index 17 4)) ) 
    				(progn (setq input (replace_sublist input index "false" 4)) (setq tokens (replace_sublist tokens index 18 4)))
    				)
    		)
    ) 

    (if (search (list 24 10 32 31 25) tokens)							; set identifier value
    	(progn 

    		(setq index (search (list 24 10 32 31 25) tokens))

    		(if (position (nth (+ index 2) input) identifier_list :test #'equal)
    			(progn
    			
    			(setq idf_index (position (nth (+ index 2) input) identifier_list :test #'equal))
    			(setf (nth idf_index identifier_list_value) (parse-integer (nth (+ index 3) input) )  )
    			
    			(setq input (remove_sublist input index 4))
    			(setq tokens(remove_sublist tokens index 4))

    			)
    			(progn
    			
    			(push  (nth (+ index 2) input) identifier_list )
    			(push  (parse-integer (nth (+ index 3) input)) identifier_list_value)

    			(setq input (remove_sublist input index 4))
    			(setq tokens(remove_sublist tokens index 4))

    			)
    		)


    		
    		


    	) 
    )

    (if  (search (list 32) tokens)										;identifier to value
    	(if (not (= 10  (nth (- (search (list 32) tokens) 1) tokens) )  )
    		(progn 

    			(if (not (position (nth  (search (list 32) tokens) input) identifier_list :test #'equal)) (progn (princ "|")(princ (nth  (search (list 32) tokens) input))(princ "|")(princ " identifier not previously defined") (quit) )  )
    			
    			(setq temp (position (nth  (search (list 32) tokens) input) identifier_list :test #'equal)  )

    			
    		(setq input  (replace_sublist input (search (list 32) tokens) (write-to-string (nth temp identifier_list_value)) 0))
    		(setq tokens (replace_sublist tokens (search (list 32) tokens) 31 0 ))
    		
    		)
    	)
	)


	(if (or (search (list 24 13 17 31 31 25) tokens) (search (list 24 13 18 31 31 25) tokens))    ; if
		(progn

			(if (search (list 24 13 17 31 31 25) tokens) 
				(progn
					(setq input  (replace_sublist input  (search (list 24 13 17 31 31 25) tokens) (nth (+ 3 (search (list 24 13 17 31 31 25) tokens)) input  ) 5 ))
					(setq tokens (replace_sublist tokens (search (list 24 13 17 31 31 25) tokens) 31  5 ))

				)
			)
			(if (search (list 24 13 18 31 31 25) tokens) 
				(progn
					(setq input  (replace_sublist input  (search (list 24 13 18 31 31 25) tokens) (nth (+ 4 (search (list 24 13 18 31 31 25) tokens)) input  ) 5 ))
					(setq tokens (replace_sublist tokens (search (list 24 13 18 31 31 25) tokens) 31  5 ))

				)
			)
		)
		
	)

	



    
    (cond
    	((= 1 (list-length tokens)) (print (car input)))
    	((< 3 (list-length tokens)) (get_result input tokens))
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
    (if (input_check (input_to_reg (tokenize (my-split spart) )) )
    (get_result (my-split spart) (tokenize(my-split spart)) )
    (print "SYNTAX_ERROR")
    )


 ))
(progn
(print "Enter filename:")
(setq filename (read-line))
(read_file filename))
 )


  )

(gppinterpreter)
  ;(read_file "deneme.txt")

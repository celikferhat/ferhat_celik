# Ferhat ÇELÝK 151044014
.data
	uArray: .word 1,2,3,4,5,6,7,8,9,10,11,12,13
	
	
	realSArray: .word 1,2,-1,-1,-1,-1,-1,-1                     # Array size 8 boþ býrakýlacak elemanlarýn yerine -1 yazýlmalý.Ýstenildiði kadar sub-array eklenebilir S4-S5-S6...
		    .word 2,3,4,5,-1,-1,-1,-1
		    .word 6,7,8,9,10,11,12,13                      # S2 array ini 6,6,6,6,6,6,6,6 ile doldurursak S3 ve S4 ü seçer  S2 nin elemanlarý ayný olduðu için counter'ý 1 olur
		    .word 1,3,5,7,9,11,13,-1
		    .word 2,4,6,8,10,12,13,-1
		    
	
	realSRowSize: .word 5
	realSColSize: .word 8
	
	.eqv DATA_SIZE 4
	
	
	string: .asciiz      "S"
	newline : .asciiz "\n"
	
	uSize: .word 13	
	
	
.text
.globl main

main: 
	la $a1  , realSArray

       jal myrec
       
    #  li $v0 , 1
    #  li $a0 , 0
    #  move $a0 , $v0
    #  syscall
      	
      	li $v0 , 10
      	syscall


count:
	addi   $s0,$zero,0       		 #s0 ==> i
	addi   $t0 , $zero , 0
	addi   $a1 , $zero , -1
	li $s1,0
	lw $t1 ,uSize    		 #a1 ==> -1
	
loop:	
	beq    $s1,$t1,loopend
	lw     $t5 ,uArray($t0) 
	beq    $a1,$t5,inc_count	 #a0 ==> array
	addi   $t0 , $t0 , 4
	addi   $s1,$s1,1	
	b loop

inc_count:
	addi   $s0,$s0,1
	addi   $t0 , $t0 , 4
	addi   $s1,$s1,1
	b loop
		
loopend: jr $ra	 # <<< Sonuç S0 da >>>



equal:
	
	addi $s4 , $zero , 0 # counter
	addi $s5 , $zero , 0 #i
	addi $s6 , $zero , 0 #j
	lw $t1 , uSize  #8 
	lw $t7 , realSColSize  #10
	addi $s7 , $zero , 0  # For uArray index
	addi $t4 , $zero , 0  # For sArray index
	
	li  $s2 , -1
	

	ifor_equal:
	
	beq $s5 , $t1 , iforend
	addi $s6 , $zero , 0
	
	mul $s7 , $s5 , 4
	lw $t5 , uArray($s7)
	addi $s5 , $s5 , 1
	
	li $a3 , 0
	
jfor_equal:
	beq  $s6 , $t7 , ifor_equal		    
	mul  $t4 , $s6 , 4
	li   $t8 , 0
	add $t8 , $t4 , $a1
	lw   $t6 ,($t8)	
	beq  $t6 , $a3 , skipincrease
	beq  $t6 , $s2  , skipincrease
	beq  $t5 , $t6 , increasecounter					
	addi $s6 , $s6 , 1
	b jfor_equal
	
	
iforend:
	jr $ra
	# <<<   Sonuç S4 te >>>
	

	

increasecounter:
	li $a3,0
	add $a3,$a3,$t6
	
	addi $s6 , $s6 , 1
	addi $s4 , $s4 , 1		
	b jfor_equal

skipincrease:
	addi $s6 , $s6 , 1
	b jfor_equal	
	
	

	
myrec:
	sw $ra, ($sp)
	jal count
	lw $ra, ($sp)
	
	lw $t1 ,uSize 
	beq $s0 , $t1 , end_of_rec

	li $s0 , 0  #max equal counter
	li $s1 , 0  #max index
	lw $t2 , realSRowSize
	li $t0 , 0 # index
	
	lw $t3 , realSColSize
	la $a1 , realSArray
	li $s3 , 1
recloop1:

	
	sw $ra , ($sp)
	jal equal
	lw $ra, ($sp)

	slt $s2 , $s0 , $s4
	
	
	beq $s2 , $s3 , setmaxindex
	
donus:	
	lw $t3 , realSColSize
	mul $t9 , $t3 , DATA_SIZE
	add $a1 , $a1 , $t9
	addi $t0 , $t0 , 1
	lw $t2 , realSRowSize
	bne  $t0 , $t2 , recloop1 	
	
	
	

udoldur: 
	li $t0 , 0 # index
	lw $t3 , realSColSize   #8
	
	mul $t9 , $t3 , DATA_SIZE  #32
	
	mul $t9 , $s1 ,$t9         #
	
	
	li $t1 , 0 # uarray adres index
	li $t2 , 0 # sarray adres index
	la $a2 , uArray
	
	lw $s3 ,uSize 
	loop1:
		li $t8 , 0
		li $t2 , 0
		loop2:
			lw $t4 , uArray($t1)
			li $s0 , 0
			add $s0 , $t2 , $t9
			lw $t5 , realSArray($s0)

			beq $t4 , $t5 ,	birkoy
	loop2donus : 	
			
			addi $t8,$t8,1
			addi $t2,$t2,4
			bne  $t8,$t3,loop2		
									
		addi $t0 , $t0 , 1
		addi $t1 , $t1 , 4
		bne $t0  , $s3 , loop1
	

	
		
			
sdoldur:
	#s1 max index
	lw  $t3 , realSColSize
	mul $t9 , $t3 , DATA_SIZE
	mul $t9 , $t9 , $s1								
	li $s0 , 0 #loop index   
	li $s2 , 0
	
	sloop:
		li  $s6 , -1
		la $a3 , realSArray
		add $s5 , $a3 , $t9
		add $s5 , $s5 , $s2
		
		sw $s6 , 0($s5)
		
		addi $s0 , $s0 , 1
		addi $s2 , $s2 , 4							
		bne  $s0 , $t3 , sloop				
							
	
	li $v0 	, 4
	la $a0 , string
	syscall
							
																						
	li $v0 1
	move $a0 , $s1
	syscall
	
	li $v0 ,4
	la $a0 ,newline
	syscall
																																					
	b myrec
	
	li $v0 , 10
	syscall
	
birkoy:
	li $s7 , 0
	add $s7 , $s7 , $a2
	add $s7 , $s7 , $t1
	li  $s6 , -1
	sw $s6 , 0($s7)	
	j loop2donus
		
	
	
	
	
	
end_of_rec:
	



	li $v0 , 10
      	syscall		
setmaxindex:
	move $s0 , $s4
	move $s1 , $t0
	j donus		
			
				
						
	
   
				
	
 

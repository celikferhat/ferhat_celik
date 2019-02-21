/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect;

/**
 *
 * @author acer
 */
/*
0 = empty
1 = player 1
2 = player 2

*/

import java.util.Random;
public class control {/*thiss class set game rules and control game win*/

    public int[][] array;/*my game's array*/



    public boolean win;/*if win set true*/
    public boolean fullBoard;/*if any player can not win and board full it set true*/
    public static boolean playerwin=false;/*if player play agains conputer this flags work*/
    public static boolean computerwin = false;/*if player play agains conputer this flags work*/


    public control(){/*constructer create empty array*/

        array= new int[Connect.getmySize()][Connect.getmySize()];

        for(int row=0;row<Connect.getmySize();row++){
            for(int col=0;col<Connect.getmySize();col++){
                array[row][col]=0;
            }

        }

    }
    public void zero()/*zero the board.*/
    {
        for(int row=0;row<Connect.getmySize();row++){
            for(int col=0;col<Connect.getmySize();col++){
                array[row][col]=0;
            }

        }
    }

    public boolean isColumnfull(int index){ /*Returns true if the column is full.*/
        int a=0;
        for(int i = 0 ; Connect.getmySize() > i ; i++ ){
            if(array[i][index] != 0)//Checks whether the column is full.
            {
                a++;
            }

        }
        if(a == Connect.getmySize())
            return true;
        return false;
    }






    private boolean winner(int row,int column)
    {
        int i,a=0,b=0,c=0, /*We detect the winner in this function.We only check where X or O is thcolumnn.*/
                tmpy=0,     /*The variable used to control the line.*/
                tmpd=0,     /*The variable used to control the row.*/
                tmpc=0,     /*The variable used to control the cross.*/

                tmpx=0;     /*The variable used to control the cross.*/


        tmpy= array[row][0];    //I set the variable at the beginning of the line.
        for(i=0;Connect.getmySize()>i;i++) {
            for(i=0;Connect.getmySize()>i;i++)
            {

                if ((tmpy == array[row][i] && array[row][i] != 0)) {//if you have the same 4 values on the line side by side exept 0, return true.
                    a++;

                    if (a == 4) return true;
                } else {
                    tmpy = array[row][i];
                    a = 1;
                }


            }
        }



        a=0;
        tmpd= array[0][column];
        for(i=0;Connect.getmySize()>i;i++) {
            if (array[i][column] != 7) {
                if ((tmpd == array[i][column] && array[i][column] !=
                        0)) {//if the row has the same 4 values over the top exept 0,return true.
                    a++;

                    if (a == 4)return true;
                } else {
                    tmpd = array[i][column];
                    a = 1;
                }
            }
        }


        b=0;
        tmpc=array[row][column];
        for(i=0;5>i;i++)
        {if(row-i>=0  && column+i<Connect.getmySize()){//The place where the value is placed is checked on 4 upper right and 4 lower left.
            if(array[row-i][column+i] == tmpc)
                b++;}
            if(row+i<Connect.getmySize() && column-i>=0){
                if(array[row+i][column-i] == tmpc)
                    b++;}//If the sum of upper right and lower left numbers is 5, finish.

            if(b>=5){return true;}
        }

        tmpx=array[row][column];
        for(i=0;5>i;i++)
        {if(row+i<Connect.getmySize()  && column+i<Connect.getmySize()){////The place where the value is placed is checked on 4 upper left and 4 lower right.
            if(array[row+i][column+i] == tmpx)
                c++;}
            if(row-i>=0 && column-i>=0){
                if(array[row-i][column-i] == tmpc)
                    c++;}

            if(c>=5){return true;}////If the sum of upper left and lower right numbers is 5, finish.
        }




        return false;
    }



    public boolean isBoardFull(){

        int zero=0;

        for(int i=0;Connect.getmySize() > i ; i++)
        {
            for(int j=0;Connect.getmySize()>j;j++)
            {
                if(array[i][j] == 0)
                    zero++;
            }
        }

        if(zero == 0)
            return true;
        else return false;

    }








    public void ata(int index,int who)  /*set the cell in array*/
    {
        int tempsize=Connect.getmySize();/*for player*/

        int tempsize2=Connect.getmySize();/*for computer*/
        while (tempsize != 0 && array[tempsize - 1][index] != 0) {//same previous homework
            tempsize--;

        }

        if(Connect.computer == false)/*if playing player versus player*/
        array[tempsize - 1][index]= who;


        if(Connect.computer == true){/*if playing player versus computer*/
            Random rand = new Random();


            int random = rand.nextInt(Connect.getmySize());/*not smart computer*/

            array[tempsize - 1][index]= 1;/*player*/

            if(!isColumnfull(random)){

            while (tempsize2 != 0 && array[tempsize2 - 1][random] != 0) {
                tempsize2--;

            }


            array[tempsize2 - 1][random]= 2;/*computer*/
                if(winner(tempsize2-1,random))
                {
                    win = true;
                    computerwin = true;
                    zero();


                }

        }
        }

        if(isBoardFull())
        {
            fullBoard = true;
            zero();
        }
        if(winner(tempsize-1,index))
        {
            win = true;
            playerwin = true;
            zero();


        }

    }




}



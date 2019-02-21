package connect4.connect4;

/**
 * Created by acer on 27.01.2018.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Random;
public class rules {/*thiss class set game rules and rules game win*/

    public int[][] array;/*my game's array*/



    public boolean win;/*if win set true*/
    public static boolean fullBoard=false;/*if any player can not win and board full it set true*/
    public static boolean playerwin=false;/*if player play agains conputer this flags work*/
    public static boolean computerwin = false;/*if player play agains conputer this flags work*/
    public int size;


    public void zero()/*zero the board.*/


    {


        array= new int[size][size];
        for(int row=0;row<size;row++){
            for(int col=0;col<size;col++){
                if( array[row][col] != 1 && array[row][col] != 2 )
                    array[row][col] =0;
            }

        }
    }

    public boolean isColumnfull(int index){ /*Returns true if the index is full.*/
        int a=0;
        for(int i = 0 ; size > i ; i++ ){
            if(array[i][index] != 0)//Checks whether the index is full.
            {
                a++;
            }

        }
        if(a == size)
            return true;
        return false;
    }






    private boolean winner(int row,int index)
    {
        int i,a=0,b=0,c=0, /*We detect the winner in this function.We only check where X or O is thcolumnn.*/
                tmpy=0,     /*The variable used to rules the line.*/
                tmpd=0,     /*The variable used to rules the row.*/
                tmpc=0,     /*The variable used to rules the cross.*/

                tmpx=0;     /*The variable used to rules the cross.*/


        tmpy= array[row][0];    //I set the variable at the beginning of the line.
        for(i=0;size>i;i++) {
            for(i=0;size>i;i++)
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
        tmpd= array[0][index];
        for(i=0;size>i;i++) {
            if (array[i][index] != 7) {
                if ((tmpd == array[i][index] && array[i][index] !=
                        0)) {//if the row has the same 4 values over the top exept 0,return true.
                    a++;

                    if (a == 4){

                        return true;}
                } else {
                    tmpd = array[i][index];
                    a = 1;
                }
            }
        }


        b=0;
        tmpc=array[row][index];
        if(row-1>=0 && index+1<size){
            if(array[row-1][index+1] == tmpc){
                b++;}
        }
        if(row-2>=0 && index+2<size){
            if(array[row-2][index+2] == tmpc){
                b++;}
        }
        if(row+1<size && index-1>=0){
        if(array[row+1][index-1] == tmpc)
            b++;}

         if(b == 3){
             return true;}


        b=0;
        tmpc=array[row][index];
        if(row-1>=0 && index+1<size){
            if(array[row-1][index+1] == tmpc){
                b++;}
        }

        if(row+1<size && index-1>=0){
            if(array[row+1][index-1] == tmpc)
                b++;}
        if(row+2<size && index-2>=0){
            if(array[row+2][index-2] == tmpc)
                b++;}

        if(b == 3){return true;}











        b=0;

        if(row+1<size && index-1>=0){
            if(array[row+1][index-1] == 1)
                b++;}
        if(row+2<size && index-2>=0){
            if(array[row+2][index-2] == 1)
                b++;}
        if(row+3<size && index-3>=0){
            if(array[row+3][index-3] == 1)
                b++;}
        if(row<size && index>=0){
            if(array[row][index] == 1)
                b++;}
        if(b==4)return true;


        b=0;

        if(row+1<size && index-1>=0){
            if(array[row+1][index-1] == 2)
                b++;}
        if(row+2<size && index-2>=0){
            if(array[row+2][index-2] == 2)
                b++;}
        if(row+3<size && index-3>=0){
            if(array[row+3][index-3] == 2)
                b++;}
        if(row<size && index>=0){
            if(array[row][index] == 2)
                b++;}
        if(b==4)return true;






        b=0;
        tmpc=array[row][index];
        for(i=0;4>i;i++)
        {
            if(row-i>=0 && index+i<size){
                if(array[row-i][index+i] == tmpc)
                    b++;}

            if(b>=4){return true;}


        }





        Integer f=0;


        if(row+1 < size && index+1<size)
        {
         if(array[row+1][index+1] == 1)
             f++;
        }
        if(row+2 < size && index+2<size)
        {
            if(array[row+2][index+2] == 1)
                f++;
        }
        if(row+3 < size && index+3<size)
        {
            if(array[row+3][index+3] == 1)


                f++;
        }
        if(row < size && index<size)
        {
            if(array[row][index] == 1)


                f++;
        }

        if(f == 4)
        {
            return true;
        }


        f=0;
        if(row+1 < size && index+1<size)
        {
            if(array[row+1][index+1] == 2)
                f++;
        }
        if(row+2 < size && index+2<size)
        {
            if(array[row+2][index+2] == 2)
                f++;
        }
        if(row+3 < size && index+3<size)
        {
            if(array[row+3][index+3] == 2)


                f++;
        }
        if(row < size && index<size)
        {
            if(array[row][index] == 2)


                f++;
        }

        if(f == 4)
        {
            return true;
        }




        c=0;
        if(row-1>=0 && index-1>=0){
            if(array[row-1][index-1] == 1)
                c++;}
        if(row-2>=0 && index-2>=0){
            if(array[row-2][index-2] == 1)
                c++;}
        if(row-3>=0 && index-3>=0){
            if(array[row-3][index-3] == 1)
                c++;}
        if(row>=0 && index>=0){
            if(array[row][index] == 1)
                c++;}


        if(c==4)return true;







        c=0;
        if(row-1>=0 && index-1>=0){
            if(array[row-1][index-1] == 2)
                c++;}
        if(row-2>=0 && index-2>=0){
            if(array[row-2][index-2] == 2)
                c++;}
        if(row-3>=0 && index-3>=0){
            if(array[row-3][index-3] == 2)
                c++;}

        if(row>=0 && index>=0){
            if(array[row][index] == 2)
                c++;}
        if(c==4)return true;


        c=0;
        tmpx=array[row][index];
        if(row-1>=0 && index-1>=0){
            if(array[row-1][index-1] == tmpx)
                c++;}

        if(row-2>=0 && index-2>=0){
            if(array[row-2][index-2] == tmpx)
                c++;}

        if(row+1<size  && index+1<size){////The place where the value is placed is checked on 4 upper left and 4 lower right.
            if(array[row+1][index+1] == tmpx)
                c++;}

        if(c==3)return true;


        c=0;
        tmpx=array[row][index];

        if(row+1<size  && index+1<size){////The place where the value is placed is checked on 4 upper left and 4 lower right.
            if(array[row+1][index+1] == tmpx)
                c++;}
        if(row+2<size  && index+2<size){////The place where the value is placed is checked on 4 upper left and 4 lower right.
            if(array[row+2][index+2] == tmpx)
                c++;}


        if(row-1>=0 && index-1>=0){
            if(array[row-1][index-1] == tmpx)
                c++;}

         if(c == 3)return true;






        return false;
    }



    public boolean isBoardFull(){

        int zero=0;

        for(int i=0;size > i ; i++)
        {
            for(int j=0;size>j;j++)
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
        playerwin = false;
        computerwin = false;
        int tempsize=size;/*for player*/
        int tempsize2=size;/*for computer*/

        while (tempsize != 0 && array[tempsize - 1][index] != 0)
        {
            tempsize--;

        }

        if(MainActivity.computer == false)
                                                            {/*if playing player versus player*/
            array[tempsize - 1][index]= who;

            if(winner(tempsize-1,index))
            {
                win = true;
                playerwin = true;
                zero();


            }
            if(isBoardFull())
            {
            fullBoard = true;
            zero();
            }




        }








        if(MainActivity.computer == true)
        {                                       /*if playing player versus computer*/
            MainActivity.draw.color=2;

            array[tempsize - 1][index]= 1;/*player*/

            if(winner(tempsize-1,index))
            {
                win = true;
                playerwin = true;
                zero();


            }


            if(computer(tempsize-1,index)){}

            else{
            Random rand = new Random();

            int random = rand.nextInt(size);

            if(!isColumnfull(random)){

                while (tempsize2 != 0 && array[tempsize2 - 1][random] != 0) {
                    tempsize2--;

                }


                array[tempsize2 - 1][random]= 2;/*computer*/
                if(winner(tempsize2-1,random))
                {
                    win = true;
                    playerwin=false;
                    computerwin = true;

                    zero();


                }



            }
                if(isBoardFull())
                {
                    fullBoard = true;
                    zero();
                }





            }
        }




    }


    private boolean computer(int row,int column) {

        Integer vertical = 0, where;
        where = array[0][0];

        for(int i = 0 ; size > i ; i++)
        {
            for(int j = 0 ; size > j ; j++)
            {
                if(array[i][j] == where && where !=0)
                {
                    vertical++;
                }
                else
                {
                 where=array[i][j];
                }

                if(vertical == 1)
                {
                    if(j+1<size)
                    {
                        if(i+1<size){
                            if(array[i+1][j+1] != 0)
                            {
                                if(array[i][j+1]==0){
                                    array[i][j+1]=2;
                                    if(winner(i,j+1))
                                    {
                                        win = true;
                                        computerwin = true;
                                        zero();


                                    }
                                    return true;
                                }
                            }
                        }
                        if(i+1==size){
                            if(array[i][j+1] == 0){
                                array[i][j+1]=2;
                                if(winner(i,j+1))
                                {
                                    win = true;
                                    computerwin = true;
                                    zero();


                                }
                                return true;
                            }
                        }
                    }
                    if(j-2>=0){
                        if(i+1<size){
                            if(array[i+1][j-2] != 0)
                            {
                                if(array[i][j-2]==0){
                                    array[i][j-2]=2;
                                    if(winner(i,j-2))
                                    {
                                        win = true;
                                        computerwin = true;
                                        zero();


                                    }
                                    return true;
                                }
                            }
                        }
                        if(i+1==size){
                            if(array[i][j-2] == 0){
                                array[i][j-2]=2;
                                if(winner(i,j-2))
                                {
                                    win = true;
                                    computerwin = true;
                                    zero();


                                }
                                return true;
                            }
                        }

                    }



                }



                if(j+2<size)
                {

                    if(array[i][j+2] == array[i][j] && array[i][j] != 0)
                    {
                        if(i+1<size)
                        {
                            if(array[i+1][j+1] != 0)
                            {
                                if(array[i][j+1] == 0)
                                {
                                array[i][j+1]=2;
                                    if(winner(i,j+1))
                                    {
                                        win = true;
                                        computerwin = true;
                                        zero();


                                    }
                                return true;
                                }
                            }
                        }
                        if(i+1 == size)
                        {
                            if(array[i][j+1] == 0){
                                array[i][j+1] = 2;
                                if(winner(i,j+1))
                                {
                                    win = true;
                                    computerwin = true;
                                    zero();


                                }
                                return true;
                            }
                        }

                    }

                }


            }
        }














        return false;

    }




}



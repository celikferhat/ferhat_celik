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
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

public class mainboard extends JPanel implements MouseListener,MouseMotionListener {

    private int circleWidth =39;/*my cell size*/
    private int circleHeight =39;
    public int black=0; /*win numbers*/
    public int white =0;
    private int p = 1; /*flag for changing white and black player*/

    public control ctrl = new control();     /*control game*/

    public mainboard() throws InterruptedException {
        this.setFocusable(true);
        this.requestFocus();
        addMouseListener(this); /*mouse listener*/
        addMouseMotionListener(this);
        circleWidth = 40;
        circleHeight = 40;


        this.setSize(1000,1000);
        this.setVisible(true);

    }

    public void paint(Graphics g) {

        g.setColor(Color.gray);/*paint background gray*/
        g.fillRect(0, 0, 1000, 1000);





int x=0,y=0;/*x and y cordinates*/
        for(int i = 0 ; Connect.getmySize() > i ; i++,y+=50){

            for(int j = 0 ; Connect.getmySize() > j ; j++,x+=50)
            {
                if(ctrl.array[i][j] == 0){/*empty cells paint green*/
                    g.setColor(Color.GREEN);
                    g.fillOval(13+x, 80+y, circleWidth, circleHeight);/*there are 50 distances between the cells.*/
                }
                if(ctrl.array[i][j] == 1){   /*player 1 paint white*/
                    g.setColor(Color.white);
                    g.fillOval(13+x, 80+y, circleWidth, circleHeight);
                }
                if(ctrl.array[i][j] == 2){
                    g.setColor(Color.BLACK);    /*player 2 paint black*/
                    g.fillOval(13+x, 80+y, circleWidth, circleHeight);
                }


            }
            x=0;
        }








        g.setColor(Color.white);
        g.fillRect(15, 20, 100, 50);
        g.setFont(new Font("Arial",Font.BOLD,18));
        g.setColor(Color.BLACK);
        g.drawString(" Exit Game ", 15, 50);/*exit button as graphics*/
        BufferedImage img = null;
        try {
            img = ImageIO.read(mainboard.class.getResource("black.png") );/*black and white players images*/
        } catch (IOException e) {
        }
        g.drawImage(img,116,20,30,50,null);
        try {
            img = ImageIO.read(mainboard.class.getResource("white.png"));
        } catch (IOException e) {
        }
        g.drawImage(img,165,20,30,50,null);

        g.drawString(String.valueOf(black),150,50);/*their's win numbers*/
        g.drawString(String.valueOf(white),200,50);


    }

    @Override
    public void mousePressed(MouseEvent e) { /*when pressed mouse*/
        if(e.getX() > 15 && e.getX() <115 && e.getY() > 20 && e.getY() <70) /*if press exit close windows*/
            Connect.board.dispose();

        Graphics g1 = this.getGraphics();


        if(e.getY() > 80)
        {
            for(int i = 1;Connect.getmySize()>i;i++)
            {
               if(e.getX() > 13 && e.getX()<63 && i==1)/*zero column*/
                {   if(!ctrl.isColumnfull(0)){
                    ctrl.ata(0,p);

                    paint(g1);

                    p = (p % 2)+1;}
                    if(ctrl.win == true){
                       if(!Connect.computer) {/*if not playing against computer*/
                           if (p == 2) {
                               JOptionPane.showMessageDialog(null, "White player won.Game will reset", "InfoBox: " + "Win !!!", JOptionPane.INFORMATION_MESSAGE);
                               white++;
                           }
                           if (p == 1)
                               if (p == 1) {
                                   JOptionPane.showMessageDialog(null, "Black player won.Game will reset", "InfoBox: " + "Win !!!", JOptionPane.INFORMATION_MESSAGE);
                                   black++;
                               }
                       }
                       else{/*if playing with computer*/
                           if(ctrl.playerwin){/*if player win*/
                               JOptionPane.showMessageDialog(null, "White player won.Game will reset", "InfoBox: " + "Win !!!", JOptionPane.INFORMATION_MESSAGE);
                               white++;
                               ctrl.playerwin=false;
                           }
                           if(ctrl.computerwin){  /*if computer win*/
                               JOptionPane.showMessageDialog(null, "Black player won.Game will reset", "InfoBox: " + "Win !!!", JOptionPane.INFORMATION_MESSAGE);
                               black++;
                               ctrl.computerwin=false;
                           }
                       }
                                ctrl.win=false;

                    }
                    if(!ctrl.win && ctrl.fullBoard == true) /*if board full*/
                    {
                        JOptionPane.showMessageDialog(null, "There are not empty field anymore.Draw game", "InfoBox: " + "Draw !!!", JOptionPane.INFORMATION_MESSAGE);
                        ctrl.fullBoard = false;
                    }
                }

                if(  e.getX() > (i-1)*50+63 && e.getX() < i*50+63 ) /*other column same above */
                {
                    if(!ctrl.isColumnfull(i)){
                    ctrl.ata(i,p);
                    paint(g1);
                    p= (p % 2)+1;}
                    if(ctrl.win == true){

                        if(!Connect.computer) {
                            if (p == 2) {
                                JOptionPane.showMessageDialog(null, "White player won.Game will reset", "InfoBox: " + "Win !!!", JOptionPane.INFORMATION_MESSAGE);
                                white++;
                            }
                            if (p == 1)
                                if (p == 1) {
                                    JOptionPane.showMessageDialog(null, "Black player won.Game will reset", "InfoBox: " + "Win !!!", JOptionPane.INFORMATION_MESSAGE);
                                    black++;
                                }
                        }

                        else{
                            if(ctrl.playerwin){
                                JOptionPane.showMessageDialog(null, "White player won.Game will reset", "InfoBox: " + "Win !!!", JOptionPane.INFORMATION_MESSAGE);
                                white++;
                                ctrl.playerwin=false;
                            }
                            if(ctrl.computerwin){
                                JOptionPane.showMessageDialog(null, "Black player won.Game will reset", "InfoBox: " + "Win !!!", JOptionPane.INFORMATION_MESSAGE);
                                black++;
                                ctrl.computerwin=false;
                            }
                        }

                           ctrl.win=false;

                    }
                   if(!ctrl.win && ctrl.fullBoard == true)
                    {
                        JOptionPane.showMessageDialog(null, "There are not empty field anymore.Draw game", "InfoBox: " + "Draw !!!", JOptionPane.INFORMATION_MESSAGE);
                        ctrl.fullBoard = false;
                    }
                }
            }





        }


    }
  @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseDragged(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }
  @Override
    public void mouseMoved(MouseEvent em) {
    }


}




























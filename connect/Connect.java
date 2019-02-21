package connect;

import connect.mainboard;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.InterruptedException;

/*Itis my main class.I create Jframes and buttons here*/
public class Connect {

    public static int size=0;       /*board size*/

    static JFrame board ;           /*my game's main jframe*/
    public static boolean computer=false;   /*game type flag*/



    public Connect() throws InterruptedException {

        JFrame popup = new JFrame();        /*seleck game type this jframe*/
        popup.setTitle("Select Game Type");
        popup.setLocation(450, 250);
        popup.setSize(400, 200);


        popup.getContentPane().setBackground(Color.BLACK);

        JLabel label = new JLabel();
        label.setBackground(Color.BLACK);
        label.setSize(250, 250);

        ImageIcon resim1 = new ImageIcon(getClass().getResource("person.png"));    /*button images*/
        JButton comp = new JButton(resim1);                             /*game type buttons*/
        comp.setSize(140, 120);
        comp.setLocation(50, 15);
        /*------------------------------------------------------------------------------*/
        comp.addActionListener(new ActionListener() {   /*when click button*/

                                   @Override
                                   public void actionPerformed(ActionEvent e) {

                                       JFrame boyut = new JFrame(); /* we have to select size so create new jframe*/
                                       boyut.setSize(150,100);
                                       boyut.setLocation(570,250);
                                       JPanel panel = new JPanel();
                                       panel.setSize(200,200);

                                       JLabel label = new JLabel();
                                       label.setText("Enter board size");
                                       panel.add(label);

                                       JTextField text = new JTextField(5); /*new text field for entering size*/
                                       text.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));/*textfield border*/

                                       text.setBounds(200,40,200,40);



                                       panel.add(text);/*add text label to panel*/

                                       JButton enter = new JButton("enter");/*size submit button*/

                                       enter.addActionListener(new ActionListener() {
                                           @Override
                                           public void actionPerformed(ActionEvent e) {

                                               if(Integer.valueOf(text.getText()) < 3)/*size have to bigger than 3*/
                                                   JOptionPane.showMessageDialog(null, "Size can not be negative volue.Please re-enter", "InfoBox: " + "Invalid Size", JOptionPane.INFORMATION_MESSAGE);

                                              else{
                                               size = Integer.valueOf(text.getText());/*assigment size*/


                                               board = new JFrame();
                                               board.setTitle("ConnectFour");
                                               board.setSize(size*60, size*80);
                                               board.setLocation(500, 100);

                                               board.setVisible(true);
                                               try {
                                                   board.add(new mainboard() );/*my game board label all works in it*/
                                                   throw new InterruptedException();
                                               }
                                               catch (InterruptedException a)
                                               {

                                               }
                                               board.getContentPane().setBackground(Color.lightGray);
                                               board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                               board.setVisible(true);
                                               board.setFocusable(true);
                                               board.setResizable(false);



                                               boyut.dispose();/*close*/
                                               popup.dispose();/*close*/






                                           }}
                                       });

                                       /*-------------*/
                                       panel.add(enter);
                                       boyut.setContentPane(panel);

                                       boyut.setVisible(true);
                                       boyut.setResizable(false);

                                   }
                               }
        );
        /*------------------------------------------------------------------------------*/







        ImageIcon resim2 = new ImageIcon( getClass().getResource("comp.png") );/*all process same in above*/
        JButton player = new JButton(resim2);
        player.setSize(140, 120);
        player.setLocation(220, 15);
        player.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame boyut = new JFrame();
                boyut.setSize(150,100);
                boyut.setLocation(570,250);
                JPanel panel = new JPanel();
                panel.setSize(200,200);

                JLabel label = new JLabel();
                label.setText("Enter board size");
                panel.add(label);

                JTextField text = new JTextField(5);
                text.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

                text.setBounds(200,40,200,40);



                panel.add(text);

                JButton enter = new JButton("enter");

                enter.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if(Integer.valueOf(text.getText()) < 0)
                            JOptionPane.showMessageDialog(null, "Size can not be negative volue.Please re-enter", "InfoBox: " + "Invalid Size", JOptionPane.INFORMATION_MESSAGE);


                        else{
                            computer=true;
                        size = Integer.valueOf(text.getText());

                            board = new JFrame();
                            board.setTitle("ConnectFour");
                            board.setSize(size*60, size*80);
                            board.setLocation(500, 100);

                            board.setVisible(true);
                            try {
                                board.add(new mainboard());
                            }
                            catch (InterruptedException a)
                            {

                            }
                            board.getContentPane().setBackground(Color.lightGray);
                            board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            board.setVisible(true);
                            board.setFocusable(true);
                            board.setResizable(false);




                        boyut.dispose();
                        popup.dispose();




                    }}
                });

                /*-------------*/
                panel.add(enter);
                boyut.setContentPane(panel);

                boyut.setVisible(true);
                boyut.setResizable(false);






            }
        });

        label.add(comp);
        label.add(player);


        popup.setContentPane(label);


        popup.setResizable(false);
        popup.setVisible(true);
        popup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


/************************************************/










    }


public static int getmySize(){/*size getter*/

        return size;
}





}









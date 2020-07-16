using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO.Ports;
using System.Windows.Forms;
using Utilities.BunifuCheckBox.Transitions;

namespace notrgb
{
    public partial class Form1 : Form
    {
        private bool[] _keyHeld = new bool[14];
        Songs song_form;
        
        public Form1()
        {
            InitializeComponent();

            //Control.CheckForIllegalCrossThreadCalls = false;

            this.KeyPreview = true;
            string[] ports = SerialPort.GetPortNames();
            port_select.Items.AddRange(ports);
            port_select.SelectedIndex = 0;

            for (int i = 0; 14 > i; i++)
                this._keyHeld[i] = false;


            string[] speeds = {"0.5" , "1.0" , "1.5" , "2.0" };
            speed_select.Items.AddRange(speeds);
            speed_select.SelectedIndex = 1;
            song_form = new Songs();
        }

        public void set_serialport(SerialPort port)
        {
            serialPort1 = port;
        }

        private void OpenChildForm(Form childform , object sender) {

            
            
            childform.TopLevel = false;
            childform.FormBorderStyle = FormBorderStyle.None;
            childform.Dock = DockStyle.Fill;
            this.DesktopPanel.Controls.Add(childform);
            childform.BringToFront();
            childform.Show();

        }

        private void close_button_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void minimize_button_Click(object sender, EventArgs e)
        {
            this.WindowState = FormWindowState.Minimized;
        }

        private void home_button_Click(object sender, EventArgs e)
        {
            indicator.Top = home_button.Top;
            // this.DesktopPanel.Show();
            //OpenChildForm(this, sender);

            song_form.Hide();

        }

        private void play_button_Click(object sender, EventArgs e)
        {
            indicator.Top = play_button.Top;
           // Songs s1 = new Songs();
            song_form.set_serialport(serialPort1);
            OpenChildForm(song_form,sender);

        }

        private void bunifuImageButton1_MouseDown(object sender, MouseEventArgs e)
        {
            this.bunifuImageButton1.Image = Properties.Resources.sag_kesik_s;
            this.bunifuLabel2.Text = "C4";
            try
            {
                if (serialPort1.IsOpen)
                {
                    serialPort1.Write("0262\r\n");

                }


            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Message", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void bunifuImageButton1_MouseLeave(object sender, EventArgs e)
        {
            this.bunifuImageButton1.Image = Properties.Resources.sag_kesik_b;
            this.bunifuLabel2.Text = "";
            try
            {
                if (serialPort1.IsOpen)
                {
                    serialPort1.Write("-262\r\n");

                }


            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Message", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void bunifuImageButton2_MouseDown(object sender, MouseEventArgs e)
        {
            this.bunifuImageButton2.Image = Properties.Resources.sag_sol_kesik_s;
            this.bunifuLabel2.Text = "D4";
            try
            {
                if (serialPort1.IsOpen)
                {
                    serialPort1.Write("0294\r\n");

                }


            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Message", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void bunifuImageButton2_MouseLeave(object sender, EventArgs e)
        {
            this.bunifuImageButton2.Image = Properties.Resources.sag_sol_kesik_b;
            this.bunifuLabel2.Text = "";
            try
            {
                if (serialPort1.IsOpen)
                {
                    serialPort1.Write("-294\r\n");

                }


            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Message", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void bunifuImageButton3_MouseDown(object sender, MouseEventArgs e)
        {
            this.bunifuImageButton3.Image = Properties.Resources.sol_kesik_s;
            this.bunifuLabel2.Text = "E4";
            try
            {
                if (serialPort1.IsOpen)
                {
                    serialPort1.Write("0330\r\n");

                }


            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Message", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void bunifuImageButton3_MouseLeave(object sender, EventArgs e)
        {
            this.bunifuImageButton3.Image = Properties.Resources.sol_kesik_b;
            this.bunifuLabel2.Text = "";
            try
            {
                if (serialPort1.IsOpen)
                {
                   
                    serialPort1.Write("-330\r\n");

                }


            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Message", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void bunifuImageButton4_MouseDown(object sender, MouseEventArgs e)
        {
            this.bunifuImageButton4.Image = Properties.Resources.sag_kesik_s;
            this.bunifuLabel2.Text = "F4";
            try
            {
                if (serialPort1.IsOpen)
                {
                    serialPort1.Write("0349\r\n");

                }


            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Message", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void bunifuImageButton4_MouseLeave(object sender, EventArgs e)
        {
            this.bunifuImageButton4.Image = Properties.Resources.sag_kesik_b;
            this.bunifuLabel2.Text = "";
            try
            {
                if (serialPort1.IsOpen)
                {
                    serialPort1.Write("-349\r\n");

                }


            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Message", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void bunifuImageButton5_MouseDown(object sender, MouseEventArgs e)
        {
            this.bunifuImageButton5.Image = Properties.Resources.sag_sol_kesik_s;
            this.bunifuLabel2.Text = "G4";
            try
            {
                if (serialPort1.IsOpen)
                {
                    serialPort1.Write("0392\r\n");

                }


            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Message", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void bunifuImageButton5_MouseLeave(object sender, EventArgs e)
        {
            this.bunifuImageButton5.Image = Properties.Resources.sag_sol_kesik_b;
            this.bunifuLabel2.Text = "";
            try
            {
                if (serialPort1.IsOpen)
                {
                    serialPort1.Write("-392\r\n");

                }


            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Message", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void bunifuImageButton6_MouseDown(object sender, MouseEventArgs e)
        {
            this.bunifuImageButton6.Image = Properties.Resources.sag_sol_kesik_s;
            this.bunifuLabel2.Text = "A4";
            try
            {
                if (serialPort1.IsOpen)
                {
                    serialPort1.Write("0440\r\n");

                }


            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Message", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void bunifuImageButton6_MouseLeave(object sender, EventArgs e)
        {
            this.bunifuImageButton6.Image = Properties.Resources.sag_sol_kesik_b;
            this.bunifuLabel2.Text = "";
            try
            {
                if (serialPort1.IsOpen)
                {
                    serialPort1.Write("-440\r\n");

                }


            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Message", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void bunifuImageButton7_MouseDown(object sender, MouseEventArgs e)
        {
            this.bunifuImageButton7.Image = Properties.Resources.sol_kesik_s;
            this.bunifuLabel2.Text = "B4";
            try
            {
                if (serialPort1.IsOpen)
                {
                    serialPort1.Write("0494\r\n");

                }


            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Message", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void bunifuImageButton7_MouseLeave(object sender, EventArgs e)
        {
            this.bunifuImageButton7.Image = Properties.Resources.sol_kesik_b;
            this.bunifuLabel2.Text = "";
            try
            {
                if (serialPort1.IsOpen)
                {
                    serialPort1.Write("-494\r\n");

                }


            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Message", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void bunifuImageButton8_MouseDown(object sender, MouseEventArgs e)
        {
            this.bunifuImageButton8.Image = Properties.Resources.sag_kesik_s;
            this.bunifuLabel2.Text = "C5";
            try
            {
                if (serialPort1.IsOpen)
                {
                    serialPort1.Write("0523\r\n");

                }


            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Message", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void bunifuImageButton8_MouseLeave(object sender, EventArgs e)
        {
            this.bunifuImageButton8.Image = Properties.Resources.sag_kesik_b;
            this.bunifuLabel2.Text = "";
            try
            {
                if (serialPort1.IsOpen)
                {
                    serialPort1.Write("-523\r\n");

                }


            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Message", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void bunifuImageButton9_MouseDown(object sender, MouseEventArgs e)
        {
            this.bunifuImageButton9.Image = Properties.Resources.sag_sol_kesik_s;
            this.bunifuLabel2.Text = "D5";
            try
            {
                if (serialPort1.IsOpen)
                {
                    serialPort1.Write("0587\r\n");

                }


            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Message", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void bunifuImageButton9_MouseLeave(object sender, EventArgs e)
        {
            this.bunifuImageButton9.Image = Properties.Resources.sag_sol_kesik_b;
            this.bunifuLabel2.Text = "";
            try
            {
                if (serialPort1.IsOpen)
                {
                    serialPort1.Write("-587\r\n");

                }


            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Message", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void bunifuImageButton10_MouseDown(object sender, MouseEventArgs e)
        {
            this.bunifuImageButton10.Image = Properties.Resources.sol_kesik_s;
            this.bunifuLabel2.Text = "E5";
            try
            {
                if (serialPort1.IsOpen)
                {
                    serialPort1.Write("0659\r\n");

                }


            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Message", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void bunifuImageButton10_MouseLeave(object sender, EventArgs e)
        {
            this.bunifuImageButton10.Image = Properties.Resources.sol_kesik_b;
            this.bunifuLabel2.Text = "";
            try
            {
                if (serialPort1.IsOpen)
                {
                    serialPort1.Write("-659\r\n");

                }


            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Message", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void bunifuImageButton11_MouseDown(object sender, MouseEventArgs e)
        {
            this.bunifuImageButton11.Image = Properties.Resources.sag_kesik_s;
            this.bunifuLabel2.Text = "F5";
            try
            {
                if (serialPort1.IsOpen)
                {
                    serialPort1.Write("0698\r\n");

                }


            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Message", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void bunifuImageButton11_MouseLeave(object sender, EventArgs e)
        {
            this.bunifuImageButton11.Image = Properties.Resources.sag_kesik_b;
            this.bunifuLabel2.Text = "";
            try
            {
                if (serialPort1.IsOpen)
                {
                    serialPort1.Write("-698\r\n");

                }


            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Message", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void bunifuImageButton12_MouseDown(object sender, MouseEventArgs e)
        {
            this.bunifuImageButton12.Image = Properties.Resources.sag_sol_kesik_s;
            this.bunifuLabel2.Text = "G5";
            try
            {
                if (serialPort1.IsOpen)
                {
                    serialPort1.Write("0784\r\n");

                }


            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Message", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void bunifuImageButton12_MouseLeave(object sender, EventArgs e)
        {
            this.bunifuImageButton12.Image = Properties.Resources.sag_sol_kesik_b;
            this.bunifuLabel2.Text = "";
            try
            {
                if (serialPort1.IsOpen)
                {
                    serialPort1.Write("-784\r\n");

                }


            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Message", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void bunifuImageButton13_MouseDown(object sender, MouseEventArgs e)
        {
            this.bunifuImageButton13.Image = Properties.Resources.sag_sol_kesik_s;
            this.bunifuLabel2.Text = "A5";
            try
            {
                if (serialPort1.IsOpen)
                {
                    serialPort1.Write("0880\r\n");

                }


            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Message", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void bunifuImageButton13_MouseLeave(object sender, EventArgs e)
        {
            this.bunifuImageButton13.Image = Properties.Resources.sag_sol_kesik_b;
            this.bunifuLabel2.Text = "";
            try
            {
                if (serialPort1.IsOpen)
                {
                    serialPort1.Write("-880\r\n");

                }


            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Message", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void bunifuImageButton14_MouseDown(object sender, MouseEventArgs e)
        {
            this.bunifuImageButton14.Image = Properties.Resources.sol_kesik_s;
            this.bunifuLabel2.Text = "B5";
            try
            {
                if (serialPort1.IsOpen)
                {
                    serialPort1.Write("0988\r\n");

                }


            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Message", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void bunifuImageButton14_MouseLeave(object sender, EventArgs e)
        {
            this.bunifuImageButton14.Image = Properties.Resources.sol_kesik_b;
            this.bunifuLabel2.Text = "";
            try
            {
                if (serialPort1.IsOpen)
                {
                    serialPort1.Write("-988\r\n");

                }


            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Message", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void port_select_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        private void Form1_KeyDown(object sender, KeyEventArgs e)
        {
            switch (e.KeyCode)
            {
                case Keys.Q:
                    e.Handled = true;
                    if (!this._keyHeld[0])
                    {
                        bunifuImageButton1_MouseDown(null, null);
                        this._keyHeld[0] = true;
                    }
                    break;
                case Keys.W:
                    e.Handled = true;
                    if (!this._keyHeld[1])
                    {
                        bunifuImageButton2_MouseDown(null, null);
                        this._keyHeld[1] = true;
                    }
                    break;
                case Keys.E:
                    e.Handled = true;
                    if (!this._keyHeld[2])
                    {
                        bunifuImageButton3_MouseDown(null, null);
                        this._keyHeld[2] = true;
                    }
                    break;
                case Keys.R:
                    e.Handled = true;
                    if (!this._keyHeld[3])
                    {
                        bunifuImageButton4_MouseDown(null, null);
                        this._keyHeld[3] = true;
                    }
                    break;
                case Keys.T:
                    e.Handled = true;
                    if (!this._keyHeld[4])
                    {
                        bunifuImageButton5_MouseDown(null, null);
                        this._keyHeld[4] = true;
                    }
                    break;
                case Keys.Y:
                    e.Handled = true;
                    if (!this._keyHeld[5])
                    {
                        bunifuImageButton6_MouseDown(null, null);
                        this._keyHeld[5] = true;
                    }
                    break;
                case Keys.U:
                    e.Handled = true;
                    if (!this._keyHeld[6])
                    {
                        bunifuImageButton7_MouseDown(null, null);
                        this._keyHeld[6] = true;
                    }
                    break;
                case Keys.I:
                    e.Handled = true;
                    if (!this._keyHeld[7])
                    {
                        bunifuImageButton8_MouseDown(null, null);
                        this._keyHeld[7] = true;
                    }
                    break;
                case Keys.O:
                    e.Handled = true;
                    if (!this._keyHeld[8])
                    {
                        bunifuImageButton9_MouseDown(null, null);
                        this._keyHeld[8] = true;
                    }
                    break;
                case Keys.P:
                    e.Handled = true;
                    if (!this._keyHeld[9])
                    {
                        bunifuImageButton10_MouseDown(null, null);
                        this._keyHeld[9] = true;
                    }
                    break;
                case Keys.H:
                    e.Handled = true;
                    if (!this._keyHeld[10])
                    {
                        bunifuImageButton11_MouseDown(null, null);
                        this._keyHeld[10] = true;
                    }
                    break;
                case Keys.J:
                    e.Handled = true;
                    if (!this._keyHeld[11])
                    {
                        bunifuImageButton12_MouseDown(null, null);
                        this._keyHeld[11] = true;
                    }
                    break;
                case Keys.K:
                    e.Handled = true;
                    if (!this._keyHeld[12])
                    {
                        bunifuImageButton13_MouseDown(null, null);
                        this._keyHeld[12] = true;
                    }
                    break;
                case Keys.L:
                    e.Handled = true;
                    if (!this._keyHeld[13])
                    {
                        bunifuImageButton14_MouseDown(null, null);
                        this._keyHeld[13] = true;
                    }
                    break;

            }
        }

        private void Form1_KeyUp(object sender, KeyEventArgs e)
        {
            switch (e.KeyCode)
            {
                case Keys.Q:
                    e.Handled = true;
                    bunifuImageButton1_MouseLeave(null, null);
                    this._keyHeld[0] = false;
                    break;
                case Keys.W:
                    e.Handled = true;
                    bunifuImageButton2_MouseLeave(null, null);
                    this._keyHeld[1] = false;
                    break;
                case Keys.E:
                    e.Handled = true;
                    bunifuImageButton3_MouseLeave(null, null);
                    this._keyHeld[2] = false;
                    break;
                case Keys.R:
                    e.Handled = true;
                    bunifuImageButton4_MouseLeave(null, null);
                    this._keyHeld[3] = false;
                    break;
                case Keys.T:
                    e.Handled = true;
                    bunifuImageButton5_MouseLeave(null, null);
                    this._keyHeld[4] = false;
                    break;
                case Keys.Y:
                    e.Handled = true;
                    bunifuImageButton6_MouseLeave(null, null);
                    this._keyHeld[5] = false;
                    break;
                case Keys.U:
                    e.Handled = true;
                    bunifuImageButton7_MouseLeave(null, null);
                    this._keyHeld[6] = false;
                    break;
                case Keys.I:
                    e.Handled = true;
                    bunifuImageButton8_MouseLeave(null, null);
                    this._keyHeld[7] = false;
                    break;
                case Keys.O:
                    e.Handled = true;
                    bunifuImageButton9_MouseLeave(null, null);
                    this._keyHeld[8] = false;
                    break;
                case Keys.P:
                    e.Handled = true;
                    bunifuImageButton10_MouseLeave(null, null);
                    this._keyHeld[9] = false;
                    break;
                case Keys.H:
                    e.Handled = true;
                    bunifuImageButton11_MouseLeave(null, null);
                    this._keyHeld[10] = false;
                    break;
                case Keys.J:
                    e.Handled = true;
                    bunifuImageButton12_MouseLeave(null, null);
                    this._keyHeld[11] = false;
                    break;
                case Keys.K:
                    e.Handled = true;
                    bunifuImageButton13_MouseLeave(null, null);
                    this._keyHeld[12] = false;
                    break;
                case Keys.L:
                    e.Handled = true;
                    bunifuImageButton14_MouseLeave(null, null);
                    this._keyHeld[13] = false;
                    break;

            }
        }

        private void record_OnValuechange(object sender, EventArgs e)
        {
            if (record.Value)
            {

                try
                {
                    if (serialPort1.IsOpen)
                    {
                        serialPort1.Write("0271\r\n"); // r c r d => 72 + 63 + 72 + 64

                    }


                }
                catch (Exception ex)
                {
                    MessageBox.Show(ex.Message, "Message", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }

            }
            else {

                try
                {
                    if (serialPort1.IsOpen)
                    {
                        serialPort1.Write("-271\r\n");

                    }


                }
                catch (Exception ex)
                {
                    MessageBox.Show(ex.Message, "Message", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            }



        }

        private void Play_record_Click(object sender, EventArgs e)
        {
            try
            {
                if (serialPort1.IsOpen)
                {
                    if (speed_select.Text == "0.5")
                        serialPort1.Write("0272\r\n");
                    if (speed_select.Text == "1.0")
                        serialPort1.Write("0273\r\n");
                    if (speed_select.Text == "1.5")
                        serialPort1.Write("0274\r\n");
                    if (speed_select.Text == "2.0")
                        serialPort1.Write("0275\r\n");






                }


            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Message", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void bunifuButton1_Click(object sender, EventArgs e)
        {
            try
            {
                if (serialPort1.IsOpen)
                {
                    serialPort1.Close();

                }


                serialPort1.PortName = port_select.Text;
                serialPort1.Open();
                serialPort1.BaudRate = 9600;

            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Message Port Select", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }
    }
}

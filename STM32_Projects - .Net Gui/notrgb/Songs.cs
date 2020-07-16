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

namespace notrgb
{
    public partial class Songs : Form
    {
        public Songs()
        {
            InitializeComponent();
        }

        public void set_serialport(SerialPort port) {
            myserial = port;
        }

        private void bunifuImageButton1_Click(object sender, EventArgs e)
        {
            try
            {
                if (myserial.IsOpen)
                {
                    myserial.Write("0100\r\n"); 

                }


            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Message", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void bunifuImageButton4_Click(object sender, EventArgs e)
        {
            try
            {
                if (myserial.IsOpen)
                {
                    myserial.Write("0104\r\n");

                }


            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Message", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }
    }
}

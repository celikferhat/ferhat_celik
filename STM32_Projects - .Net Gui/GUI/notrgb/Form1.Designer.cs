namespace notrgb
{
    partial class Form1
    {
        /// <summary>
        ///Gerekli tasarımcı değişkeni.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///Kullanılan tüm kaynakları temizleyin.
        /// </summary>
        ///<param name="disposing">yönetilen kaynaklar dispose edilmeliyse doğru; aksi halde yanlış.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer üretilen kod

        /// <summary>
        /// Tasarımcı desteği için gerekli metot - bu metodun 
        ///içeriğini kod düzenleyici ile değiştirmeyin.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Form1));
            Bunifu.UI.WinForms.BunifuButton.BunifuButton.StateProperties stateProperties1 = new Bunifu.UI.WinForms.BunifuButton.BunifuButton.StateProperties();
            Bunifu.ToggleSwitch.ToggleState toggleState1 = new Bunifu.ToggleSwitch.ToggleState();
            Bunifu.ToggleSwitch.ToggleState toggleState2 = new Bunifu.ToggleSwitch.ToggleState();
            Bunifu.ToggleSwitch.ToggleState toggleState3 = new Bunifu.ToggleSwitch.ToggleState();
            Bunifu.UI.WinForms.BunifuButton.BunifuButton.StateProperties stateProperties2 = new Bunifu.UI.WinForms.BunifuButton.BunifuButton.StateProperties();
            this.bunifuElipse1 = new Bunifu.Framework.UI.BunifuElipse(this.components);
            this.header = new System.Windows.Forms.Panel();
            this.bunifuImageButton15 = new Bunifu.UI.WinForms.BunifuImageButton();
            this.minimize_button = new Bunifu.UI.WinForms.BunifuImageButton();
            this.close_button = new Bunifu.UI.WinForms.BunifuImageButton();
            this.pictureBox1 = new System.Windows.Forms.PictureBox();
            this.navbar = new System.Windows.Forms.Panel();
            this.indicator = new System.Windows.Forms.PictureBox();
            this.play_button = new Bunifu.UI.WinForms.BunifuImageButton();
            this.home_button = new Bunifu.UI.WinForms.BunifuImageButton();
            this.bunifuDragControl1 = new Bunifu.Framework.UI.BunifuDragControl(this.components);
            this.serialPort1 = new System.IO.Ports.SerialPort(this.components);
            this.bunifuLabel2 = new Bunifu.UI.WinForms.BunifuLabel();
            this.port_select = new Bunifu.UI.WinForms.BunifuDropdown();
            this.bunifuLabel1 = new Bunifu.UI.WinForms.BunifuLabel();
            this.Record_label = new Bunifu.UI.WinForms.BunifuLabel();
            this.speed_select = new Bunifu.UI.WinForms.BunifuDropdown();
            this.song_speed = new System.Windows.Forms.Label();
            this.DesktopPanel = new System.Windows.Forms.Panel();
            this.bunifuButton1 = new Bunifu.UI.WinForms.BunifuButton.BunifuButton();
            this.bunifuImageButton14 = new Bunifu.UI.WinForms.BunifuImageButton();
            this.bunifuImageButton13 = new Bunifu.UI.WinForms.BunifuImageButton();
            this.bunifuImageButton12 = new Bunifu.UI.WinForms.BunifuImageButton();
            this.record = new Bunifu.ToggleSwitch.BunifuToggleSwitch();
            this.bunifuImageButton11 = new Bunifu.UI.WinForms.BunifuImageButton();
            this.bunifuImageButton10 = new Bunifu.UI.WinForms.BunifuImageButton();
            this.bunifuImageButton9 = new Bunifu.UI.WinForms.BunifuImageButton();
            this.bunifuImageButton8 = new Bunifu.UI.WinForms.BunifuImageButton();
            this.bunifuImageButton7 = new Bunifu.UI.WinForms.BunifuImageButton();
            this.Play_record = new Bunifu.UI.WinForms.BunifuButton.BunifuButton();
            this.bunifuImageButton6 = new Bunifu.UI.WinForms.BunifuImageButton();
            this.bunifuImageButton1 = new Bunifu.UI.WinForms.BunifuImageButton();
            this.bunifuImageButton5 = new Bunifu.UI.WinForms.BunifuImageButton();
            this.bunifuImageButton2 = new Bunifu.UI.WinForms.BunifuImageButton();
            this.bunifuImageButton4 = new Bunifu.UI.WinForms.BunifuImageButton();
            this.bunifuImageButton3 = new Bunifu.UI.WinForms.BunifuImageButton();
            this.header.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).BeginInit();
            this.navbar.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.indicator)).BeginInit();
            this.DesktopPanel.SuspendLayout();
            this.SuspendLayout();
            // 
            // bunifuElipse1
            // 
            this.bunifuElipse1.ElipseRadius = 5;
            this.bunifuElipse1.TargetControl = this;
            // 
            // header
            // 
            this.header.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(102)))), ((int)(((byte)(204)))));
            this.header.Controls.Add(this.bunifuImageButton15);
            this.header.Controls.Add(this.minimize_button);
            this.header.Controls.Add(this.close_button);
            this.header.Controls.Add(this.pictureBox1);
            this.header.Dock = System.Windows.Forms.DockStyle.Top;
            this.header.Location = new System.Drawing.Point(0, 0);
            this.header.Name = "header";
            this.header.Size = new System.Drawing.Size(920, 54);
            this.header.TabIndex = 0;
            // 
            // bunifuImageButton15
            // 
            this.bunifuImageButton15.ActiveImage = null;
            this.bunifuImageButton15.AllowAnimations = true;
            this.bunifuImageButton15.AllowZooming = true;
            this.bunifuImageButton15.BackColor = System.Drawing.Color.Transparent;
            this.bunifuImageButton15.ErrorImage = ((System.Drawing.Image)(resources.GetObject("bunifuImageButton15.ErrorImage")));
            this.bunifuImageButton15.FadeWhenInactive = false;
            this.bunifuImageButton15.Flip = Bunifu.UI.WinForms.BunifuImageButton.FlipOrientation.Normal;
            this.bunifuImageButton15.Image = global::notrgb.Properties.Resources.green;
            this.bunifuImageButton15.ImageActive = null;
            this.bunifuImageButton15.ImageLocation = null;
            this.bunifuImageButton15.ImageMargin = 10;
            this.bunifuImageButton15.ImageSize = new System.Drawing.Size(20, 20);
            this.bunifuImageButton15.ImageZoomSize = new System.Drawing.Size(30, 30);
            this.bunifuImageButton15.InitialImage = ((System.Drawing.Image)(resources.GetObject("bunifuImageButton15.InitialImage")));
            this.bunifuImageButton15.Location = new System.Drawing.Point(833, 12);
            this.bunifuImageButton15.Name = "bunifuImageButton15";
            this.bunifuImageButton15.Rotation = 0;
            this.bunifuImageButton15.ShowActiveImage = true;
            this.bunifuImageButton15.ShowCursorChanges = true;
            this.bunifuImageButton15.ShowImageBorders = true;
            this.bunifuImageButton15.ShowSizeMarkers = false;
            this.bunifuImageButton15.Size = new System.Drawing.Size(30, 30);
            this.bunifuImageButton15.TabIndex = 3;
            this.bunifuImageButton15.ToolTipText = "";
            this.bunifuImageButton15.WaitOnLoad = false;
            this.bunifuImageButton15.Zoom = 10;
            this.bunifuImageButton15.ZoomSpeed = 10;
            // 
            // minimize_button
            // 
            this.minimize_button.ActiveImage = null;
            this.minimize_button.AllowAnimations = true;
            this.minimize_button.AllowZooming = true;
            this.minimize_button.BackColor = System.Drawing.Color.Transparent;
            this.minimize_button.ErrorImage = ((System.Drawing.Image)(resources.GetObject("minimize_button.ErrorImage")));
            this.minimize_button.FadeWhenInactive = false;
            this.minimize_button.Flip = Bunifu.UI.WinForms.BunifuImageButton.FlipOrientation.Normal;
            this.minimize_button.Image = global::notrgb.Properties.Resources.yellow;
            this.minimize_button.ImageActive = null;
            this.minimize_button.ImageLocation = null;
            this.minimize_button.ImageMargin = 10;
            this.minimize_button.ImageSize = new System.Drawing.Size(20, 20);
            this.minimize_button.ImageZoomSize = new System.Drawing.Size(30, 30);
            this.minimize_button.InitialImage = ((System.Drawing.Image)(resources.GetObject("minimize_button.InitialImage")));
            this.minimize_button.Location = new System.Drawing.Point(797, 12);
            this.minimize_button.Name = "minimize_button";
            this.minimize_button.Rotation = 0;
            this.minimize_button.ShowActiveImage = true;
            this.minimize_button.ShowCursorChanges = true;
            this.minimize_button.ShowImageBorders = true;
            this.minimize_button.ShowSizeMarkers = false;
            this.minimize_button.Size = new System.Drawing.Size(30, 30);
            this.minimize_button.TabIndex = 2;
            this.minimize_button.ToolTipText = "";
            this.minimize_button.WaitOnLoad = false;
            this.minimize_button.Zoom = 10;
            this.minimize_button.ZoomSpeed = 10;
            this.minimize_button.Click += new System.EventHandler(this.minimize_button_Click);
            // 
            // close_button
            // 
            this.close_button.ActiveImage = null;
            this.close_button.AllowAnimations = true;
            this.close_button.AllowZooming = true;
            this.close_button.BackColor = System.Drawing.Color.Transparent;
            this.close_button.ErrorImage = ((System.Drawing.Image)(resources.GetObject("close_button.ErrorImage")));
            this.close_button.FadeWhenInactive = false;
            this.close_button.Flip = Bunifu.UI.WinForms.BunifuImageButton.FlipOrientation.Normal;
            this.close_button.Image = global::notrgb.Properties.Resources.red;
            this.close_button.ImageActive = null;
            this.close_button.ImageLocation = null;
            this.close_button.ImageMargin = 10;
            this.close_button.ImageSize = new System.Drawing.Size(20, 20);
            this.close_button.ImageZoomSize = new System.Drawing.Size(30, 30);
            this.close_button.InitialImage = ((System.Drawing.Image)(resources.GetObject("close_button.InitialImage")));
            this.close_button.Location = new System.Drawing.Point(869, 12);
            this.close_button.Name = "close_button";
            this.close_button.Rotation = 0;
            this.close_button.ShowActiveImage = true;
            this.close_button.ShowCursorChanges = true;
            this.close_button.ShowImageBorders = true;
            this.close_button.ShowSizeMarkers = false;
            this.close_button.Size = new System.Drawing.Size(30, 30);
            this.close_button.TabIndex = 1;
            this.close_button.ToolTipText = "";
            this.close_button.WaitOnLoad = false;
            this.close_button.Zoom = 10;
            this.close_button.ZoomSpeed = 10;
            this.close_button.Click += new System.EventHandler(this.close_button_Click);
            // 
            // pictureBox1
            // 
            this.pictureBox1.Image = global::notrgb.Properties.Resources.song1;
            this.pictureBox1.Location = new System.Drawing.Point(3, 4);
            this.pictureBox1.Name = "pictureBox1";
            this.pictureBox1.Size = new System.Drawing.Size(62, 47);
            this.pictureBox1.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.pictureBox1.TabIndex = 0;
            this.pictureBox1.TabStop = false;
            // 
            // navbar
            // 
            this.navbar.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(26)))), ((int)(((byte)(32)))), ((int)(((byte)(40)))));
            this.navbar.Controls.Add(this.indicator);
            this.navbar.Controls.Add(this.play_button);
            this.navbar.Controls.Add(this.home_button);
            this.navbar.Dock = System.Windows.Forms.DockStyle.Left;
            this.navbar.Location = new System.Drawing.Point(0, 54);
            this.navbar.Name = "navbar";
            this.navbar.Size = new System.Drawing.Size(99, 481);
            this.navbar.TabIndex = 1;
            // 
            // indicator
            // 
            this.indicator.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(102)))), ((int)(((byte)(204)))));
            this.indicator.Location = new System.Drawing.Point(0, 33);
            this.indicator.Name = "indicator";
            this.indicator.Size = new System.Drawing.Size(5, 75);
            this.indicator.TabIndex = 3;
            this.indicator.TabStop = false;
            // 
            // play_button
            // 
            this.play_button.ActiveImage = global::notrgb.Properties.Resources.active_play;
            this.play_button.AllowAnimations = true;
            this.play_button.AllowZooming = true;
            this.play_button.BackColor = System.Drawing.Color.Transparent;
            this.play_button.ErrorImage = ((System.Drawing.Image)(resources.GetObject("play_button.ErrorImage")));
            this.play_button.FadeWhenInactive = false;
            this.play_button.Flip = Bunifu.UI.WinForms.BunifuImageButton.FlipOrientation.Normal;
            this.play_button.Image = global::notrgb.Properties.Resources.play;
            this.play_button.ImageActive = global::notrgb.Properties.Resources.active_play;
            this.play_button.ImageLocation = null;
            this.play_button.ImageMargin = 40;
            this.play_button.ImageSize = new System.Drawing.Size(50, 50);
            this.play_button.ImageZoomSize = new System.Drawing.Size(90, 90);
            this.play_button.InitialImage = ((System.Drawing.Image)(resources.GetObject("play_button.InitialImage")));
            this.play_button.Location = new System.Drawing.Point(6, 129);
            this.play_button.Name = "play_button";
            this.play_button.Rotation = 0;
            this.play_button.ShowActiveImage = true;
            this.play_button.ShowCursorChanges = true;
            this.play_button.ShowImageBorders = true;
            this.play_button.ShowSizeMarkers = false;
            this.play_button.Size = new System.Drawing.Size(90, 90);
            this.play_button.TabIndex = 2;
            this.play_button.ToolTipText = "";
            this.play_button.WaitOnLoad = false;
            this.play_button.Zoom = 40;
            this.play_button.ZoomSpeed = 10;
            this.play_button.Click += new System.EventHandler(this.play_button_Click);
            // 
            // home_button
            // 
            this.home_button.ActiveImage = global::notrgb.Properties.Resources.home_mavi;
            this.home_button.AllowAnimations = true;
            this.home_button.AllowZooming = true;
            this.home_button.BackColor = System.Drawing.Color.Transparent;
            this.home_button.ErrorImage = ((System.Drawing.Image)(resources.GetObject("home_button.ErrorImage")));
            this.home_button.FadeWhenInactive = false;
            this.home_button.Flip = Bunifu.UI.WinForms.BunifuImageButton.FlipOrientation.Normal;
            this.home_button.Image = global::notrgb.Properties.Resources.home_beyaz;
            this.home_button.ImageActive = global::notrgb.Properties.Resources.home_mavi;
            this.home_button.ImageLocation = null;
            this.home_button.ImageMargin = 40;
            this.home_button.ImageSize = new System.Drawing.Size(50, 50);
            this.home_button.ImageZoomSize = new System.Drawing.Size(90, 90);
            this.home_button.InitialImage = ((System.Drawing.Image)(resources.GetObject("home_button.InitialImage")));
            this.home_button.Location = new System.Drawing.Point(6, 33);
            this.home_button.Name = "home_button";
            this.home_button.Rotation = 0;
            this.home_button.ShowActiveImage = true;
            this.home_button.ShowCursorChanges = true;
            this.home_button.ShowImageBorders = true;
            this.home_button.ShowSizeMarkers = false;
            this.home_button.Size = new System.Drawing.Size(90, 90);
            this.home_button.TabIndex = 1;
            this.home_button.ToolTipText = "";
            this.home_button.WaitOnLoad = false;
            this.home_button.Zoom = 40;
            this.home_button.ZoomSpeed = 10;
            this.home_button.Click += new System.EventHandler(this.home_button_Click);
            // 
            // bunifuDragControl1
            // 
            this.bunifuDragControl1.Fixed = true;
            this.bunifuDragControl1.Horizontal = true;
            this.bunifuDragControl1.TargetControl = this.header;
            this.bunifuDragControl1.Vertical = true;
            // 
            // bunifuLabel2
            // 
            this.bunifuLabel2.AutoEllipsis = false;
            this.bunifuLabel2.CursorType = null;
            this.bunifuLabel2.Font = new System.Drawing.Font("Open Sans Light", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(162)));
            this.bunifuLabel2.ForeColor = System.Drawing.Color.White;
            this.bunifuLabel2.Location = new System.Drawing.Point(480, 122);
            this.bunifuLabel2.Name = "bunifuLabel2";
            this.bunifuLabel2.RightToLeft = System.Windows.Forms.RightToLeft.No;
            this.bunifuLabel2.Size = new System.Drawing.Size(0, 0);
            this.bunifuLabel2.TabIndex = 16;
            this.bunifuLabel2.Text = null;
            this.bunifuLabel2.TextAlignment = System.Drawing.ContentAlignment.TopLeft;
            this.bunifuLabel2.TextFormat = Bunifu.UI.WinForms.BunifuLabel.TextFormattingOptions.Default;
            // 
            // port_select
            // 
            this.port_select.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(37)))), ((int)(((byte)(46)))), ((int)(((byte)(59)))));
            this.port_select.BorderRadius = 1;
            this.port_select.Color = System.Drawing.Color.White;
            this.port_select.Direction = Bunifu.UI.WinForms.BunifuDropdown.Directions.Down;
            this.port_select.DisabledColor = System.Drawing.Color.Gray;
            this.port_select.DrawMode = System.Windows.Forms.DrawMode.OwnerDrawFixed;
            this.port_select.DropdownBorderThickness = Bunifu.UI.WinForms.BunifuDropdown.BorderThickness.Thick;
            this.port_select.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.port_select.DropDownTextAlign = Bunifu.UI.WinForms.BunifuDropdown.TextAlign.Left;
            this.port_select.FillDropDown = false;
            this.port_select.FillIndicator = false;
            this.port_select.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.port_select.Font = new System.Drawing.Font("Open Sans", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(162)));
            this.port_select.ForeColor = System.Drawing.Color.White;
            this.port_select.FormattingEnabled = true;
            this.port_select.Icon = null;
            this.port_select.IndicatorColor = System.Drawing.Color.White;
            this.port_select.IndicatorLocation = Bunifu.UI.WinForms.BunifuDropdown.Indicator.Right;
            this.port_select.ItemBackColor = System.Drawing.Color.White;
            this.port_select.ItemBorderColor = System.Drawing.Color.White;
            this.port_select.ItemForeColor = System.Drawing.Color.Black;
            this.port_select.ItemHeight = 26;
            this.port_select.ItemHighLightColor = System.Drawing.Color.CornflowerBlue;
            this.port_select.Location = new System.Drawing.Point(793, 80);
            this.port_select.Name = "port_select";
            this.port_select.Size = new System.Drawing.Size(115, 32);
            this.port_select.TabIndex = 17;
            this.port_select.Text = null;
            this.port_select.SelectedIndexChanged += new System.EventHandler(this.port_select_SelectedIndexChanged);
            // 
            // bunifuLabel1
            // 
            this.bunifuLabel1.AutoEllipsis = false;
            this.bunifuLabel1.CursorType = null;
            this.bunifuLabel1.Font = new System.Drawing.Font("Open Sans Light", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(162)));
            this.bunifuLabel1.ForeColor = System.Drawing.Color.White;
            this.bunifuLabel1.Location = new System.Drawing.Point(715, 68);
            this.bunifuLabel1.Name = "bunifuLabel1";
            this.bunifuLabel1.RightToLeft = System.Windows.Forms.RightToLeft.No;
            this.bunifuLabel1.Size = new System.Drawing.Size(65, 47);
            this.bunifuLabel1.TabIndex = 18;
            this.bunifuLabel1.Text = "Port";
            this.bunifuLabel1.TextAlignment = System.Drawing.ContentAlignment.TopLeft;
            this.bunifuLabel1.TextFormat = Bunifu.UI.WinForms.BunifuLabel.TextFormattingOptions.Default;
            // 
            // Record_label
            // 
            this.Record_label.AutoEllipsis = false;
            this.Record_label.CursorType = null;
            this.Record_label.Font = new System.Drawing.Font("Open Sans", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(162)));
            this.Record_label.ForeColor = System.Drawing.Color.White;
            this.Record_label.Location = new System.Drawing.Point(105, 68);
            this.Record_label.Name = "Record_label";
            this.Record_label.RightToLeft = System.Windows.Forms.RightToLeft.No;
            this.Record_label.Size = new System.Drawing.Size(111, 47);
            this.Record_label.TabIndex = 20;
            this.Record_label.Text = "Record";
            this.Record_label.TextAlignment = System.Drawing.ContentAlignment.TopLeft;
            this.Record_label.TextFormat = Bunifu.UI.WinForms.BunifuLabel.TextFormattingOptions.Default;
            // 
            // speed_select
            // 
            this.speed_select.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(37)))), ((int)(((byte)(46)))), ((int)(((byte)(59)))));
            this.speed_select.BorderRadius = 13;
            this.speed_select.Color = System.Drawing.Color.White;
            this.speed_select.Direction = Bunifu.UI.WinForms.BunifuDropdown.Directions.Down;
            this.speed_select.DisabledColor = System.Drawing.Color.Gray;
            this.speed_select.DrawMode = System.Windows.Forms.DrawMode.OwnerDrawFixed;
            this.speed_select.DropdownBorderThickness = Bunifu.UI.WinForms.BunifuDropdown.BorderThickness.Thick;
            this.speed_select.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.speed_select.DropDownTextAlign = Bunifu.UI.WinForms.BunifuDropdown.TextAlign.Left;
            this.speed_select.FillDropDown = false;
            this.speed_select.FillIndicator = false;
            this.speed_select.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.speed_select.Font = new System.Drawing.Font("Open Sans", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(162)));
            this.speed_select.ForeColor = System.Drawing.Color.White;
            this.speed_select.FormattingEnabled = true;
            this.speed_select.Icon = null;
            this.speed_select.IndicatorColor = System.Drawing.Color.White;
            this.speed_select.IndicatorLocation = Bunifu.UI.WinForms.BunifuDropdown.Indicator.Right;
            this.speed_select.ItemBackColor = System.Drawing.Color.White;
            this.speed_select.ItemBorderColor = System.Drawing.Color.White;
            this.speed_select.ItemForeColor = System.Drawing.Color.Black;
            this.speed_select.ItemHeight = 26;
            this.speed_select.ItemHighLightColor = System.Drawing.Color.White;
            this.speed_select.Location = new System.Drawing.Point(218, 130);
            this.speed_select.Name = "speed_select";
            this.speed_select.Size = new System.Drawing.Size(67, 32);
            this.speed_select.TabIndex = 22;
            this.speed_select.Text = null;
            // 
            // song_speed
            // 
            this.song_speed.AutoSize = true;
            this.song_speed.Font = new System.Drawing.Font("Open Sans Light", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(162)));
            this.song_speed.ForeColor = System.Drawing.Color.White;
            this.song_speed.Location = new System.Drawing.Point(219, 112);
            this.song_speed.Name = "song_speed";
            this.song_speed.Size = new System.Drawing.Size(66, 15);
            this.song_speed.TabIndex = 23;
            this.song_speed.Text = "Song Speed";
            // 
            // DesktopPanel
            // 
            this.DesktopPanel.Controls.Add(this.bunifuButton1);
            this.DesktopPanel.Controls.Add(this.bunifuImageButton14);
            this.DesktopPanel.Controls.Add(this.bunifuLabel2);
            this.DesktopPanel.Controls.Add(this.bunifuImageButton13);
            this.DesktopPanel.Controls.Add(this.port_select);
            this.DesktopPanel.Controls.Add(this.bunifuImageButton12);
            this.DesktopPanel.Controls.Add(this.record);
            this.DesktopPanel.Controls.Add(this.bunifuImageButton11);
            this.DesktopPanel.Controls.Add(this.speed_select);
            this.DesktopPanel.Controls.Add(this.bunifuImageButton10);
            this.DesktopPanel.Controls.Add(this.song_speed);
            this.DesktopPanel.Controls.Add(this.bunifuImageButton9);
            this.DesktopPanel.Controls.Add(this.bunifuLabel1);
            this.DesktopPanel.Controls.Add(this.bunifuImageButton8);
            this.DesktopPanel.Controls.Add(this.Record_label);
            this.DesktopPanel.Controls.Add(this.bunifuImageButton7);
            this.DesktopPanel.Controls.Add(this.Play_record);
            this.DesktopPanel.Controls.Add(this.bunifuImageButton6);
            this.DesktopPanel.Controls.Add(this.bunifuImageButton1);
            this.DesktopPanel.Controls.Add(this.bunifuImageButton5);
            this.DesktopPanel.Controls.Add(this.bunifuImageButton2);
            this.DesktopPanel.Controls.Add(this.bunifuImageButton4);
            this.DesktopPanel.Controls.Add(this.bunifuImageButton3);
            this.DesktopPanel.Dock = System.Windows.Forms.DockStyle.Fill;
            this.DesktopPanel.Location = new System.Drawing.Point(0, 0);
            this.DesktopPanel.Name = "DesktopPanel";
            this.DesktopPanel.Size = new System.Drawing.Size(920, 535);
            this.DesktopPanel.TabIndex = 24;
            // 
            // bunifuButton1
            // 
            this.bunifuButton1.BackColor = System.Drawing.Color.Transparent;
            this.bunifuButton1.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("bunifuButton1.BackgroundImage")));
            this.bunifuButton1.ButtonText = "Connect";
            this.bunifuButton1.ButtonTextMarginLeft = 0;
            this.bunifuButton1.DisabledBorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(102)))), ((int)(((byte)(204)))));
            this.bunifuButton1.DisabledFillColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(102)))), ((int)(((byte)(204)))));
            this.bunifuButton1.DisabledForecolor = System.Drawing.Color.White;
            this.bunifuButton1.Font = new System.Drawing.Font("Open Sans", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(162)));
            this.bunifuButton1.ForeColor = System.Drawing.Color.White;
            this.bunifuButton1.IconLeftCursor = System.Windows.Forms.Cursors.Default;
            this.bunifuButton1.IconPadding = 10;
            this.bunifuButton1.IconRightCursor = System.Windows.Forms.Cursors.Default;
            this.bunifuButton1.IdleBorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(102)))), ((int)(((byte)(204)))));
            this.bunifuButton1.IdleBorderRadius = 30;
            this.bunifuButton1.IdleBorderThickness = 0;
            this.bunifuButton1.IdleFillColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(102)))), ((int)(((byte)(204)))));
            this.bunifuButton1.IdleIconLeftImage = null;
            this.bunifuButton1.IdleIconRightImage = null;
            this.bunifuButton1.Location = new System.Drawing.Point(793, 122);
            this.bunifuButton1.Name = "bunifuButton1";
            stateProperties1.BorderColor = System.Drawing.Color.DodgerBlue;
            stateProperties1.BorderRadius = 30;
            stateProperties1.BorderThickness = 1;
            stateProperties1.FillColor = System.Drawing.Color.DodgerBlue;
            stateProperties1.IconLeftImage = null;
            stateProperties1.IconRightImage = null;
            this.bunifuButton1.onHoverState = stateProperties1;
            this.bunifuButton1.Size = new System.Drawing.Size(115, 40);
            this.bunifuButton1.TabIndex = 24;
            this.bunifuButton1.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            this.bunifuButton1.Click += new System.EventHandler(this.bunifuButton1_Click);
            // 
            // bunifuImageButton14
            // 
            this.bunifuImageButton14.ActiveImage = global::notrgb.Properties.Resources.sol_kesik;
            this.bunifuImageButton14.AllowAnimations = true;
            this.bunifuImageButton14.AllowZooming = false;
            this.bunifuImageButton14.BackColor = System.Drawing.Color.Transparent;
            this.bunifuImageButton14.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.bunifuImageButton14.ErrorImage = ((System.Drawing.Image)(resources.GetObject("bunifuImageButton14.ErrorImage")));
            this.bunifuImageButton14.FadeWhenInactive = false;
            this.bunifuImageButton14.Flip = Bunifu.UI.WinForms.BunifuImageButton.FlipOrientation.Normal;
            this.bunifuImageButton14.Image = global::notrgb.Properties.Resources.sol_kesik_b;
            this.bunifuImageButton14.ImageActive = global::notrgb.Properties.Resources.sol_kesik;
            this.bunifuImageButton14.ImageLocation = null;
            this.bunifuImageButton14.ImageMargin = 0;
            this.bunifuImageButton14.ImageSize = new System.Drawing.Size(55, 250);
            this.bunifuImageButton14.ImageZoomSize = new System.Drawing.Size(55, 250);
            this.bunifuImageButton14.InitialImage = ((System.Drawing.Image)(resources.GetObject("bunifuImageButton14.InitialImage")));
            this.bunifuImageButton14.Location = new System.Drawing.Point(830, 245);
            this.bunifuImageButton14.Name = "bunifuImageButton14";
            this.bunifuImageButton14.Rotation = 0;
            this.bunifuImageButton14.ShowActiveImage = true;
            this.bunifuImageButton14.ShowCursorChanges = true;
            this.bunifuImageButton14.ShowImageBorders = true;
            this.bunifuImageButton14.ShowSizeMarkers = false;
            this.bunifuImageButton14.Size = new System.Drawing.Size(55, 250);
            this.bunifuImageButton14.TabIndex = 15;
            this.bunifuImageButton14.ToolTipText = "";
            this.bunifuImageButton14.WaitOnLoad = false;
            this.bunifuImageButton14.Zoom = 0;
            this.bunifuImageButton14.ZoomSpeed = 10;
            this.bunifuImageButton14.MouseDown += new System.Windows.Forms.MouseEventHandler(this.bunifuImageButton14_MouseDown);
            this.bunifuImageButton14.MouseLeave += new System.EventHandler(this.bunifuImageButton14_MouseLeave);
            // 
            // bunifuImageButton13
            // 
            this.bunifuImageButton13.ActiveImage = global::notrgb.Properties.Resources.sag_sol_kesik;
            this.bunifuImageButton13.AllowAnimations = true;
            this.bunifuImageButton13.AllowZooming = false;
            this.bunifuImageButton13.BackColor = System.Drawing.Color.Transparent;
            this.bunifuImageButton13.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.bunifuImageButton13.ErrorImage = ((System.Drawing.Image)(resources.GetObject("bunifuImageButton13.ErrorImage")));
            this.bunifuImageButton13.FadeWhenInactive = false;
            this.bunifuImageButton13.Flip = Bunifu.UI.WinForms.BunifuImageButton.FlipOrientation.Normal;
            this.bunifuImageButton13.Image = global::notrgb.Properties.Resources.sag_sol_kesik_b;
            this.bunifuImageButton13.ImageActive = global::notrgb.Properties.Resources.sag_sol_kesik;
            this.bunifuImageButton13.ImageLocation = null;
            this.bunifuImageButton13.ImageMargin = 0;
            this.bunifuImageButton13.ImageSize = new System.Drawing.Size(55, 250);
            this.bunifuImageButton13.ImageZoomSize = new System.Drawing.Size(55, 250);
            this.bunifuImageButton13.InitialImage = ((System.Drawing.Image)(resources.GetObject("bunifuImageButton13.InitialImage")));
            this.bunifuImageButton13.Location = new System.Drawing.Point(775, 245);
            this.bunifuImageButton13.Name = "bunifuImageButton13";
            this.bunifuImageButton13.Rotation = 0;
            this.bunifuImageButton13.ShowActiveImage = true;
            this.bunifuImageButton13.ShowCursorChanges = true;
            this.bunifuImageButton13.ShowImageBorders = true;
            this.bunifuImageButton13.ShowSizeMarkers = false;
            this.bunifuImageButton13.Size = new System.Drawing.Size(55, 250);
            this.bunifuImageButton13.TabIndex = 14;
            this.bunifuImageButton13.ToolTipText = "";
            this.bunifuImageButton13.WaitOnLoad = false;
            this.bunifuImageButton13.Zoom = 0;
            this.bunifuImageButton13.ZoomSpeed = 10;
            this.bunifuImageButton13.MouseDown += new System.Windows.Forms.MouseEventHandler(this.bunifuImageButton13_MouseDown);
            this.bunifuImageButton13.MouseLeave += new System.EventHandler(this.bunifuImageButton13_MouseLeave);
            // 
            // bunifuImageButton12
            // 
            this.bunifuImageButton12.ActiveImage = global::notrgb.Properties.Resources.sag_sol_kesik;
            this.bunifuImageButton12.AllowAnimations = true;
            this.bunifuImageButton12.AllowZooming = false;
            this.bunifuImageButton12.BackColor = System.Drawing.Color.Transparent;
            this.bunifuImageButton12.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.bunifuImageButton12.ErrorImage = ((System.Drawing.Image)(resources.GetObject("bunifuImageButton12.ErrorImage")));
            this.bunifuImageButton12.FadeWhenInactive = false;
            this.bunifuImageButton12.Flip = Bunifu.UI.WinForms.BunifuImageButton.FlipOrientation.Normal;
            this.bunifuImageButton12.Image = global::notrgb.Properties.Resources.sag_sol_kesik_b;
            this.bunifuImageButton12.ImageActive = global::notrgb.Properties.Resources.sag_sol_kesik;
            this.bunifuImageButton12.ImageLocation = null;
            this.bunifuImageButton12.ImageMargin = 0;
            this.bunifuImageButton12.ImageSize = new System.Drawing.Size(55, 250);
            this.bunifuImageButton12.ImageZoomSize = new System.Drawing.Size(55, 250);
            this.bunifuImageButton12.InitialImage = ((System.Drawing.Image)(resources.GetObject("bunifuImageButton12.InitialImage")));
            this.bunifuImageButton12.Location = new System.Drawing.Point(720, 245);
            this.bunifuImageButton12.Name = "bunifuImageButton12";
            this.bunifuImageButton12.Rotation = 0;
            this.bunifuImageButton12.ShowActiveImage = true;
            this.bunifuImageButton12.ShowCursorChanges = true;
            this.bunifuImageButton12.ShowImageBorders = true;
            this.bunifuImageButton12.ShowSizeMarkers = false;
            this.bunifuImageButton12.Size = new System.Drawing.Size(55, 250);
            this.bunifuImageButton12.TabIndex = 13;
            this.bunifuImageButton12.ToolTipText = "";
            this.bunifuImageButton12.WaitOnLoad = false;
            this.bunifuImageButton12.Zoom = 0;
            this.bunifuImageButton12.ZoomSpeed = 10;
            this.bunifuImageButton12.MouseDown += new System.Windows.Forms.MouseEventHandler(this.bunifuImageButton12_MouseDown);
            this.bunifuImageButton12.MouseLeave += new System.EventHandler(this.bunifuImageButton12_MouseLeave);
            // 
            // record
            // 
            this.record.Animation = 5;
            this.record.BackColor = System.Drawing.Color.Transparent;
            this.record.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("record.BackgroundImage")));
            this.record.Cursor = System.Windows.Forms.Cursors.Hand;
            this.record.ForeColor = System.Drawing.SystemColors.ControlText;
            this.record.Location = new System.Drawing.Point(222, 83);
            this.record.Name = "record";
            this.record.Size = new System.Drawing.Size(40, 20);
            this.record.TabIndex = 19;
            toggleState1.BackColor = System.Drawing.Color.Empty;
            toggleState1.BackColorInner = System.Drawing.Color.Empty;
            toggleState1.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(236)))), ((int)(((byte)(0)))), ((int)(((byte)(140)))));
            toggleState1.BorderColorInner = System.Drawing.Color.Empty;
            toggleState1.BorderRadius = 1;
            toggleState1.BorderRadiusInner = 1;
            toggleState1.BorderThickness = 1;
            toggleState1.BorderThicknessInner = 1;
            this.record.ToggleStateDisabled = toggleState1;
            toggleState2.BackColor = System.Drawing.Color.Gray;
            toggleState2.BackColorInner = System.Drawing.Color.White;
            toggleState2.BorderColor = System.Drawing.Color.Gray;
            toggleState2.BorderColorInner = System.Drawing.Color.White;
            toggleState2.BorderRadius = 17;
            toggleState2.BorderRadiusInner = 15;
            toggleState2.BorderThickness = 1;
            toggleState2.BorderThicknessInner = 1;
            this.record.ToggleStateOff = toggleState2;
            toggleState3.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(102)))), ((int)(((byte)(204)))));
            toggleState3.BackColorInner = System.Drawing.Color.White;
            toggleState3.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(102)))), ((int)(((byte)(204)))));
            toggleState3.BorderColorInner = System.Drawing.Color.White;
            toggleState3.BorderRadius = 17;
            toggleState3.BorderRadiusInner = 15;
            toggleState3.BorderThickness = 1;
            toggleState3.BorderThicknessInner = 1;
            this.record.ToggleStateOn = toggleState3;
            this.record.Value = false;
            this.record.OnValuechange += new System.EventHandler(this.record_OnValuechange);
            // 
            // bunifuImageButton11
            // 
            this.bunifuImageButton11.ActiveImage = global::notrgb.Properties.Resources.sag_kesik;
            this.bunifuImageButton11.AllowAnimations = true;
            this.bunifuImageButton11.AllowZooming = false;
            this.bunifuImageButton11.BackColor = System.Drawing.Color.Transparent;
            this.bunifuImageButton11.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.bunifuImageButton11.ErrorImage = ((System.Drawing.Image)(resources.GetObject("bunifuImageButton11.ErrorImage")));
            this.bunifuImageButton11.FadeWhenInactive = false;
            this.bunifuImageButton11.Flip = Bunifu.UI.WinForms.BunifuImageButton.FlipOrientation.Normal;
            this.bunifuImageButton11.Image = global::notrgb.Properties.Resources.sag_kesik_b;
            this.bunifuImageButton11.ImageActive = global::notrgb.Properties.Resources.sag_kesik;
            this.bunifuImageButton11.ImageLocation = null;
            this.bunifuImageButton11.ImageMargin = 0;
            this.bunifuImageButton11.ImageSize = new System.Drawing.Size(55, 250);
            this.bunifuImageButton11.ImageZoomSize = new System.Drawing.Size(55, 250);
            this.bunifuImageButton11.InitialImage = ((System.Drawing.Image)(resources.GetObject("bunifuImageButton11.InitialImage")));
            this.bunifuImageButton11.Location = new System.Drawing.Point(665, 245);
            this.bunifuImageButton11.Name = "bunifuImageButton11";
            this.bunifuImageButton11.Rotation = 0;
            this.bunifuImageButton11.ShowActiveImage = true;
            this.bunifuImageButton11.ShowCursorChanges = true;
            this.bunifuImageButton11.ShowImageBorders = true;
            this.bunifuImageButton11.ShowSizeMarkers = false;
            this.bunifuImageButton11.Size = new System.Drawing.Size(55, 250);
            this.bunifuImageButton11.TabIndex = 12;
            this.bunifuImageButton11.ToolTipText = "";
            this.bunifuImageButton11.WaitOnLoad = false;
            this.bunifuImageButton11.Zoom = 0;
            this.bunifuImageButton11.ZoomSpeed = 10;
            this.bunifuImageButton11.MouseDown += new System.Windows.Forms.MouseEventHandler(this.bunifuImageButton11_MouseDown);
            this.bunifuImageButton11.MouseLeave += new System.EventHandler(this.bunifuImageButton11_MouseLeave);
            // 
            // bunifuImageButton10
            // 
            this.bunifuImageButton10.ActiveImage = global::notrgb.Properties.Resources.sol_kesik;
            this.bunifuImageButton10.AllowAnimations = true;
            this.bunifuImageButton10.AllowZooming = false;
            this.bunifuImageButton10.BackColor = System.Drawing.Color.Transparent;
            this.bunifuImageButton10.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.bunifuImageButton10.ErrorImage = ((System.Drawing.Image)(resources.GetObject("bunifuImageButton10.ErrorImage")));
            this.bunifuImageButton10.FadeWhenInactive = false;
            this.bunifuImageButton10.Flip = Bunifu.UI.WinForms.BunifuImageButton.FlipOrientation.Normal;
            this.bunifuImageButton10.Image = global::notrgb.Properties.Resources.sol_kesik_b;
            this.bunifuImageButton10.ImageActive = global::notrgb.Properties.Resources.sol_kesik;
            this.bunifuImageButton10.ImageLocation = null;
            this.bunifuImageButton10.ImageMargin = 0;
            this.bunifuImageButton10.ImageSize = new System.Drawing.Size(55, 250);
            this.bunifuImageButton10.ImageZoomSize = new System.Drawing.Size(55, 250);
            this.bunifuImageButton10.InitialImage = ((System.Drawing.Image)(resources.GetObject("bunifuImageButton10.InitialImage")));
            this.bunifuImageButton10.Location = new System.Drawing.Point(610, 245);
            this.bunifuImageButton10.Name = "bunifuImageButton10";
            this.bunifuImageButton10.Rotation = 0;
            this.bunifuImageButton10.ShowActiveImage = true;
            this.bunifuImageButton10.ShowCursorChanges = true;
            this.bunifuImageButton10.ShowImageBorders = true;
            this.bunifuImageButton10.ShowSizeMarkers = false;
            this.bunifuImageButton10.Size = new System.Drawing.Size(55, 250);
            this.bunifuImageButton10.TabIndex = 11;
            this.bunifuImageButton10.ToolTipText = "";
            this.bunifuImageButton10.WaitOnLoad = false;
            this.bunifuImageButton10.Zoom = 0;
            this.bunifuImageButton10.ZoomSpeed = 10;
            this.bunifuImageButton10.MouseDown += new System.Windows.Forms.MouseEventHandler(this.bunifuImageButton10_MouseDown);
            this.bunifuImageButton10.MouseLeave += new System.EventHandler(this.bunifuImageButton10_MouseLeave);
            // 
            // bunifuImageButton9
            // 
            this.bunifuImageButton9.ActiveImage = global::notrgb.Properties.Resources.sag_sol_kesik;
            this.bunifuImageButton9.AllowAnimations = true;
            this.bunifuImageButton9.AllowZooming = false;
            this.bunifuImageButton9.BackColor = System.Drawing.Color.Transparent;
            this.bunifuImageButton9.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.bunifuImageButton9.ErrorImage = ((System.Drawing.Image)(resources.GetObject("bunifuImageButton9.ErrorImage")));
            this.bunifuImageButton9.FadeWhenInactive = false;
            this.bunifuImageButton9.Flip = Bunifu.UI.WinForms.BunifuImageButton.FlipOrientation.Normal;
            this.bunifuImageButton9.Image = global::notrgb.Properties.Resources.sag_sol_kesik_b;
            this.bunifuImageButton9.ImageActive = global::notrgb.Properties.Resources.sag_sol_kesik;
            this.bunifuImageButton9.ImageLocation = null;
            this.bunifuImageButton9.ImageMargin = 0;
            this.bunifuImageButton9.ImageSize = new System.Drawing.Size(55, 250);
            this.bunifuImageButton9.ImageZoomSize = new System.Drawing.Size(55, 250);
            this.bunifuImageButton9.InitialImage = ((System.Drawing.Image)(resources.GetObject("bunifuImageButton9.InitialImage")));
            this.bunifuImageButton9.Location = new System.Drawing.Point(555, 245);
            this.bunifuImageButton9.Name = "bunifuImageButton9";
            this.bunifuImageButton9.Rotation = 0;
            this.bunifuImageButton9.ShowActiveImage = true;
            this.bunifuImageButton9.ShowCursorChanges = true;
            this.bunifuImageButton9.ShowImageBorders = true;
            this.bunifuImageButton9.ShowSizeMarkers = false;
            this.bunifuImageButton9.Size = new System.Drawing.Size(55, 250);
            this.bunifuImageButton9.TabIndex = 10;
            this.bunifuImageButton9.ToolTipText = "";
            this.bunifuImageButton9.WaitOnLoad = false;
            this.bunifuImageButton9.Zoom = 0;
            this.bunifuImageButton9.ZoomSpeed = 10;
            this.bunifuImageButton9.MouseDown += new System.Windows.Forms.MouseEventHandler(this.bunifuImageButton9_MouseDown);
            this.bunifuImageButton9.MouseLeave += new System.EventHandler(this.bunifuImageButton9_MouseLeave);
            // 
            // bunifuImageButton8
            // 
            this.bunifuImageButton8.ActiveImage = global::notrgb.Properties.Resources.sag_kesik;
            this.bunifuImageButton8.AllowAnimations = true;
            this.bunifuImageButton8.AllowZooming = false;
            this.bunifuImageButton8.BackColor = System.Drawing.Color.Transparent;
            this.bunifuImageButton8.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.bunifuImageButton8.ErrorImage = ((System.Drawing.Image)(resources.GetObject("bunifuImageButton8.ErrorImage")));
            this.bunifuImageButton8.FadeWhenInactive = false;
            this.bunifuImageButton8.Flip = Bunifu.UI.WinForms.BunifuImageButton.FlipOrientation.Normal;
            this.bunifuImageButton8.Image = global::notrgb.Properties.Resources.sag_kesik_b;
            this.bunifuImageButton8.ImageActive = global::notrgb.Properties.Resources.sag_kesik;
            this.bunifuImageButton8.ImageLocation = null;
            this.bunifuImageButton8.ImageMargin = 0;
            this.bunifuImageButton8.ImageSize = new System.Drawing.Size(55, 250);
            this.bunifuImageButton8.ImageZoomSize = new System.Drawing.Size(55, 250);
            this.bunifuImageButton8.InitialImage = ((System.Drawing.Image)(resources.GetObject("bunifuImageButton8.InitialImage")));
            this.bunifuImageButton8.Location = new System.Drawing.Point(500, 245);
            this.bunifuImageButton8.Name = "bunifuImageButton8";
            this.bunifuImageButton8.Rotation = 0;
            this.bunifuImageButton8.ShowActiveImage = true;
            this.bunifuImageButton8.ShowCursorChanges = true;
            this.bunifuImageButton8.ShowImageBorders = true;
            this.bunifuImageButton8.ShowSizeMarkers = false;
            this.bunifuImageButton8.Size = new System.Drawing.Size(55, 250);
            this.bunifuImageButton8.TabIndex = 9;
            this.bunifuImageButton8.ToolTipText = "";
            this.bunifuImageButton8.WaitOnLoad = false;
            this.bunifuImageButton8.Zoom = 0;
            this.bunifuImageButton8.ZoomSpeed = 10;
            this.bunifuImageButton8.MouseDown += new System.Windows.Forms.MouseEventHandler(this.bunifuImageButton8_MouseDown);
            this.bunifuImageButton8.MouseLeave += new System.EventHandler(this.bunifuImageButton8_MouseLeave);
            // 
            // bunifuImageButton7
            // 
            this.bunifuImageButton7.ActiveImage = global::notrgb.Properties.Resources.sol_kesik;
            this.bunifuImageButton7.AllowAnimations = true;
            this.bunifuImageButton7.AllowZooming = false;
            this.bunifuImageButton7.BackColor = System.Drawing.Color.Transparent;
            this.bunifuImageButton7.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.bunifuImageButton7.ErrorImage = ((System.Drawing.Image)(resources.GetObject("bunifuImageButton7.ErrorImage")));
            this.bunifuImageButton7.FadeWhenInactive = false;
            this.bunifuImageButton7.Flip = Bunifu.UI.WinForms.BunifuImageButton.FlipOrientation.Normal;
            this.bunifuImageButton7.Image = global::notrgb.Properties.Resources.sol_kesik_b;
            this.bunifuImageButton7.ImageActive = global::notrgb.Properties.Resources.sol_kesik;
            this.bunifuImageButton7.ImageLocation = null;
            this.bunifuImageButton7.ImageMargin = 0;
            this.bunifuImageButton7.ImageSize = new System.Drawing.Size(55, 250);
            this.bunifuImageButton7.ImageZoomSize = new System.Drawing.Size(55, 250);
            this.bunifuImageButton7.InitialImage = ((System.Drawing.Image)(resources.GetObject("bunifuImageButton7.InitialImage")));
            this.bunifuImageButton7.Location = new System.Drawing.Point(445, 245);
            this.bunifuImageButton7.Name = "bunifuImageButton7";
            this.bunifuImageButton7.Rotation = 0;
            this.bunifuImageButton7.ShowActiveImage = true;
            this.bunifuImageButton7.ShowCursorChanges = true;
            this.bunifuImageButton7.ShowImageBorders = true;
            this.bunifuImageButton7.ShowSizeMarkers = false;
            this.bunifuImageButton7.Size = new System.Drawing.Size(55, 250);
            this.bunifuImageButton7.TabIndex = 8;
            this.bunifuImageButton7.ToolTipText = "";
            this.bunifuImageButton7.WaitOnLoad = false;
            this.bunifuImageButton7.Zoom = 0;
            this.bunifuImageButton7.ZoomSpeed = 10;
            this.bunifuImageButton7.MouseDown += new System.Windows.Forms.MouseEventHandler(this.bunifuImageButton7_MouseDown);
            this.bunifuImageButton7.MouseLeave += new System.EventHandler(this.bunifuImageButton7_MouseLeave);
            // 
            // Play_record
            // 
            this.Play_record.BackColor = System.Drawing.Color.Transparent;
            this.Play_record.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("Play_record.BackgroundImage")));
            this.Play_record.ButtonText = "Play";
            this.Play_record.ButtonTextMarginLeft = 0;
            this.Play_record.DisabledBorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(161)))), ((int)(((byte)(161)))), ((int)(((byte)(161)))));
            this.Play_record.DisabledFillColor = System.Drawing.Color.Gray;
            this.Play_record.DisabledForecolor = System.Drawing.Color.White;
            this.Play_record.Font = new System.Drawing.Font("Open Sans", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.Play_record.ForeColor = System.Drawing.Color.White;
            this.Play_record.IconLeftCursor = System.Windows.Forms.Cursors.Default;
            this.Play_record.IconPadding = 10;
            this.Play_record.IconRightCursor = System.Windows.Forms.Cursors.Default;
            this.Play_record.IdleBorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(102)))), ((int)(((byte)(204)))));
            this.Play_record.IdleBorderRadius = 30;
            this.Play_record.IdleBorderThickness = 3;
            this.Play_record.IdleFillColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(102)))), ((int)(((byte)(204)))));
            this.Play_record.IdleIconLeftImage = null;
            this.Play_record.IdleIconRightImage = null;
            this.Play_record.ImeMode = System.Windows.Forms.ImeMode.NoControl;
            this.Play_record.Location = new System.Drawing.Point(105, 122);
            this.Play_record.Name = "Play_record";
            stateProperties2.BorderColor = System.Drawing.Color.DodgerBlue;
            stateProperties2.BorderRadius = 30;
            stateProperties2.BorderThickness = 3;
            stateProperties2.FillColor = System.Drawing.Color.DodgerBlue;
            stateProperties2.IconLeftImage = null;
            stateProperties2.IconRightImage = null;
            this.Play_record.onHoverState = stateProperties2;
            this.Play_record.Size = new System.Drawing.Size(109, 45);
            this.Play_record.TabIndex = 21;
            this.Play_record.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            this.Play_record.Click += new System.EventHandler(this.Play_record_Click);
            // 
            // bunifuImageButton6
            // 
            this.bunifuImageButton6.ActiveImage = global::notrgb.Properties.Resources.sag_sol_kesik;
            this.bunifuImageButton6.AllowAnimations = true;
            this.bunifuImageButton6.AllowZooming = false;
            this.bunifuImageButton6.BackColor = System.Drawing.Color.Transparent;
            this.bunifuImageButton6.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.bunifuImageButton6.ErrorImage = ((System.Drawing.Image)(resources.GetObject("bunifuImageButton6.ErrorImage")));
            this.bunifuImageButton6.FadeWhenInactive = false;
            this.bunifuImageButton6.Flip = Bunifu.UI.WinForms.BunifuImageButton.FlipOrientation.Normal;
            this.bunifuImageButton6.Image = global::notrgb.Properties.Resources.sag_sol_kesik_b;
            this.bunifuImageButton6.ImageActive = global::notrgb.Properties.Resources.sag_sol_kesik;
            this.bunifuImageButton6.ImageLocation = null;
            this.bunifuImageButton6.ImageMargin = 0;
            this.bunifuImageButton6.ImageSize = new System.Drawing.Size(55, 250);
            this.bunifuImageButton6.ImageZoomSize = new System.Drawing.Size(55, 250);
            this.bunifuImageButton6.InitialImage = ((System.Drawing.Image)(resources.GetObject("bunifuImageButton6.InitialImage")));
            this.bunifuImageButton6.Location = new System.Drawing.Point(390, 245);
            this.bunifuImageButton6.Name = "bunifuImageButton6";
            this.bunifuImageButton6.Rotation = 0;
            this.bunifuImageButton6.ShowActiveImage = true;
            this.bunifuImageButton6.ShowCursorChanges = true;
            this.bunifuImageButton6.ShowImageBorders = true;
            this.bunifuImageButton6.ShowSizeMarkers = false;
            this.bunifuImageButton6.Size = new System.Drawing.Size(55, 250);
            this.bunifuImageButton6.TabIndex = 7;
            this.bunifuImageButton6.ToolTipText = "";
            this.bunifuImageButton6.WaitOnLoad = false;
            this.bunifuImageButton6.Zoom = 0;
            this.bunifuImageButton6.ZoomSpeed = 10;
            this.bunifuImageButton6.MouseDown += new System.Windows.Forms.MouseEventHandler(this.bunifuImageButton6_MouseDown);
            this.bunifuImageButton6.MouseLeave += new System.EventHandler(this.bunifuImageButton6_MouseLeave);
            // 
            // bunifuImageButton1
            // 
            this.bunifuImageButton1.ActiveImage = global::notrgb.Properties.Resources.sag_kesik;
            this.bunifuImageButton1.AllowAnimations = true;
            this.bunifuImageButton1.AllowZooming = false;
            this.bunifuImageButton1.BackColor = System.Drawing.Color.Transparent;
            this.bunifuImageButton1.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.bunifuImageButton1.ErrorImage = ((System.Drawing.Image)(resources.GetObject("bunifuImageButton1.ErrorImage")));
            this.bunifuImageButton1.FadeWhenInactive = false;
            this.bunifuImageButton1.Flip = Bunifu.UI.WinForms.BunifuImageButton.FlipOrientation.Normal;
            this.bunifuImageButton1.Image = global::notrgb.Properties.Resources.sag_kesik_b;
            this.bunifuImageButton1.ImageActive = global::notrgb.Properties.Resources.sag_kesik;
            this.bunifuImageButton1.ImageLocation = null;
            this.bunifuImageButton1.ImageMargin = 0;
            this.bunifuImageButton1.ImageSize = new System.Drawing.Size(55, 250);
            this.bunifuImageButton1.ImageZoomSize = new System.Drawing.Size(55, 250);
            this.bunifuImageButton1.InitialImage = ((System.Drawing.Image)(resources.GetObject("bunifuImageButton1.InitialImage")));
            this.bunifuImageButton1.Location = new System.Drawing.Point(115, 245);
            this.bunifuImageButton1.Name = "bunifuImageButton1";
            this.bunifuImageButton1.Rotation = 0;
            this.bunifuImageButton1.ShowActiveImage = true;
            this.bunifuImageButton1.ShowCursorChanges = false;
            this.bunifuImageButton1.ShowImageBorders = true;
            this.bunifuImageButton1.ShowSizeMarkers = false;
            this.bunifuImageButton1.Size = new System.Drawing.Size(55, 250);
            this.bunifuImageButton1.TabIndex = 2;
            this.bunifuImageButton1.ToolTipText = "";
            this.bunifuImageButton1.WaitOnLoad = false;
            this.bunifuImageButton1.Zoom = 0;
            this.bunifuImageButton1.ZoomSpeed = 10;
            this.bunifuImageButton1.MouseDown += new System.Windows.Forms.MouseEventHandler(this.bunifuImageButton1_MouseDown);
            this.bunifuImageButton1.MouseLeave += new System.EventHandler(this.bunifuImageButton1_MouseLeave);
            // 
            // bunifuImageButton5
            // 
            this.bunifuImageButton5.ActiveImage = global::notrgb.Properties.Resources.sag_sol_kesik;
            this.bunifuImageButton5.AllowAnimations = true;
            this.bunifuImageButton5.AllowZooming = false;
            this.bunifuImageButton5.BackColor = System.Drawing.Color.Transparent;
            this.bunifuImageButton5.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.bunifuImageButton5.ErrorImage = ((System.Drawing.Image)(resources.GetObject("bunifuImageButton5.ErrorImage")));
            this.bunifuImageButton5.FadeWhenInactive = false;
            this.bunifuImageButton5.Flip = Bunifu.UI.WinForms.BunifuImageButton.FlipOrientation.Normal;
            this.bunifuImageButton5.Image = global::notrgb.Properties.Resources.sag_sol_kesik_b;
            this.bunifuImageButton5.ImageActive = global::notrgb.Properties.Resources.sag_sol_kesik;
            this.bunifuImageButton5.ImageLocation = null;
            this.bunifuImageButton5.ImageMargin = 0;
            this.bunifuImageButton5.ImageSize = new System.Drawing.Size(55, 250);
            this.bunifuImageButton5.ImageZoomSize = new System.Drawing.Size(55, 250);
            this.bunifuImageButton5.InitialImage = ((System.Drawing.Image)(resources.GetObject("bunifuImageButton5.InitialImage")));
            this.bunifuImageButton5.Location = new System.Drawing.Point(335, 245);
            this.bunifuImageButton5.Name = "bunifuImageButton5";
            this.bunifuImageButton5.Rotation = 0;
            this.bunifuImageButton5.ShowActiveImage = true;
            this.bunifuImageButton5.ShowCursorChanges = true;
            this.bunifuImageButton5.ShowImageBorders = true;
            this.bunifuImageButton5.ShowSizeMarkers = false;
            this.bunifuImageButton5.Size = new System.Drawing.Size(55, 250);
            this.bunifuImageButton5.TabIndex = 6;
            this.bunifuImageButton5.ToolTipText = "";
            this.bunifuImageButton5.WaitOnLoad = false;
            this.bunifuImageButton5.Zoom = 0;
            this.bunifuImageButton5.ZoomSpeed = 10;
            this.bunifuImageButton5.MouseDown += new System.Windows.Forms.MouseEventHandler(this.bunifuImageButton5_MouseDown);
            this.bunifuImageButton5.MouseLeave += new System.EventHandler(this.bunifuImageButton5_MouseLeave);
            // 
            // bunifuImageButton2
            // 
            this.bunifuImageButton2.ActiveImage = global::notrgb.Properties.Resources.sag_sol_kesik;
            this.bunifuImageButton2.AllowAnimations = true;
            this.bunifuImageButton2.AllowZooming = false;
            this.bunifuImageButton2.BackColor = System.Drawing.Color.Transparent;
            this.bunifuImageButton2.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.bunifuImageButton2.ErrorImage = ((System.Drawing.Image)(resources.GetObject("bunifuImageButton2.ErrorImage")));
            this.bunifuImageButton2.FadeWhenInactive = false;
            this.bunifuImageButton2.Flip = Bunifu.UI.WinForms.BunifuImageButton.FlipOrientation.Normal;
            this.bunifuImageButton2.Image = global::notrgb.Properties.Resources.sag_sol_kesik_b;
            this.bunifuImageButton2.ImageActive = global::notrgb.Properties.Resources.sag_sol_kesik;
            this.bunifuImageButton2.ImageLocation = null;
            this.bunifuImageButton2.ImageMargin = 0;
            this.bunifuImageButton2.ImageSize = new System.Drawing.Size(55, 250);
            this.bunifuImageButton2.ImageZoomSize = new System.Drawing.Size(55, 250);
            this.bunifuImageButton2.InitialImage = ((System.Drawing.Image)(resources.GetObject("bunifuImageButton2.InitialImage")));
            this.bunifuImageButton2.Location = new System.Drawing.Point(170, 245);
            this.bunifuImageButton2.Name = "bunifuImageButton2";
            this.bunifuImageButton2.Rotation = 0;
            this.bunifuImageButton2.ShowActiveImage = true;
            this.bunifuImageButton2.ShowCursorChanges = true;
            this.bunifuImageButton2.ShowImageBorders = true;
            this.bunifuImageButton2.ShowSizeMarkers = false;
            this.bunifuImageButton2.Size = new System.Drawing.Size(55, 250);
            this.bunifuImageButton2.TabIndex = 3;
            this.bunifuImageButton2.ToolTipText = "";
            this.bunifuImageButton2.WaitOnLoad = false;
            this.bunifuImageButton2.Zoom = 0;
            this.bunifuImageButton2.ZoomSpeed = 10;
            this.bunifuImageButton2.MouseDown += new System.Windows.Forms.MouseEventHandler(this.bunifuImageButton2_MouseDown);
            this.bunifuImageButton2.MouseLeave += new System.EventHandler(this.bunifuImageButton2_MouseLeave);
            // 
            // bunifuImageButton4
            // 
            this.bunifuImageButton4.ActiveImage = global::notrgb.Properties.Resources.sag_kesik;
            this.bunifuImageButton4.AllowAnimations = true;
            this.bunifuImageButton4.AllowZooming = false;
            this.bunifuImageButton4.BackColor = System.Drawing.Color.Transparent;
            this.bunifuImageButton4.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.bunifuImageButton4.ErrorImage = ((System.Drawing.Image)(resources.GetObject("bunifuImageButton4.ErrorImage")));
            this.bunifuImageButton4.FadeWhenInactive = false;
            this.bunifuImageButton4.Flip = Bunifu.UI.WinForms.BunifuImageButton.FlipOrientation.Normal;
            this.bunifuImageButton4.Image = global::notrgb.Properties.Resources.sag_kesik_b;
            this.bunifuImageButton4.ImageActive = global::notrgb.Properties.Resources.sag_kesik;
            this.bunifuImageButton4.ImageLocation = null;
            this.bunifuImageButton4.ImageMargin = 0;
            this.bunifuImageButton4.ImageSize = new System.Drawing.Size(55, 250);
            this.bunifuImageButton4.ImageZoomSize = new System.Drawing.Size(55, 250);
            this.bunifuImageButton4.InitialImage = ((System.Drawing.Image)(resources.GetObject("bunifuImageButton4.InitialImage")));
            this.bunifuImageButton4.Location = new System.Drawing.Point(280, 245);
            this.bunifuImageButton4.Name = "bunifuImageButton4";
            this.bunifuImageButton4.Rotation = 0;
            this.bunifuImageButton4.ShowActiveImage = true;
            this.bunifuImageButton4.ShowCursorChanges = true;
            this.bunifuImageButton4.ShowImageBorders = true;
            this.bunifuImageButton4.ShowSizeMarkers = false;
            this.bunifuImageButton4.Size = new System.Drawing.Size(55, 250);
            this.bunifuImageButton4.TabIndex = 5;
            this.bunifuImageButton4.ToolTipText = "";
            this.bunifuImageButton4.WaitOnLoad = false;
            this.bunifuImageButton4.Zoom = 0;
            this.bunifuImageButton4.ZoomSpeed = 10;
            this.bunifuImageButton4.MouseDown += new System.Windows.Forms.MouseEventHandler(this.bunifuImageButton4_MouseDown);
            this.bunifuImageButton4.MouseLeave += new System.EventHandler(this.bunifuImageButton4_MouseLeave);
            // 
            // bunifuImageButton3
            // 
            this.bunifuImageButton3.ActiveImage = global::notrgb.Properties.Resources.sol_kesik;
            this.bunifuImageButton3.AllowAnimations = true;
            this.bunifuImageButton3.AllowZooming = false;
            this.bunifuImageButton3.BackColor = System.Drawing.Color.Transparent;
            this.bunifuImageButton3.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.bunifuImageButton3.ErrorImage = ((System.Drawing.Image)(resources.GetObject("bunifuImageButton3.ErrorImage")));
            this.bunifuImageButton3.FadeWhenInactive = false;
            this.bunifuImageButton3.Flip = Bunifu.UI.WinForms.BunifuImageButton.FlipOrientation.Normal;
            this.bunifuImageButton3.Image = global::notrgb.Properties.Resources.sol_kesik_b;
            this.bunifuImageButton3.ImageActive = global::notrgb.Properties.Resources.sol_kesik;
            this.bunifuImageButton3.ImageLocation = null;
            this.bunifuImageButton3.ImageMargin = 0;
            this.bunifuImageButton3.ImageSize = new System.Drawing.Size(55, 250);
            this.bunifuImageButton3.ImageZoomSize = new System.Drawing.Size(55, 250);
            this.bunifuImageButton3.InitialImage = ((System.Drawing.Image)(resources.GetObject("bunifuImageButton3.InitialImage")));
            this.bunifuImageButton3.Location = new System.Drawing.Point(225, 245);
            this.bunifuImageButton3.Name = "bunifuImageButton3";
            this.bunifuImageButton3.Rotation = 0;
            this.bunifuImageButton3.ShowActiveImage = true;
            this.bunifuImageButton3.ShowCursorChanges = true;
            this.bunifuImageButton3.ShowImageBorders = true;
            this.bunifuImageButton3.ShowSizeMarkers = false;
            this.bunifuImageButton3.Size = new System.Drawing.Size(55, 250);
            this.bunifuImageButton3.TabIndex = 4;
            this.bunifuImageButton3.ToolTipText = "";
            this.bunifuImageButton3.WaitOnLoad = false;
            this.bunifuImageButton3.Zoom = 0;
            this.bunifuImageButton3.ZoomSpeed = 10;
            this.bunifuImageButton3.MouseDown += new System.Windows.Forms.MouseEventHandler(this.bunifuImageButton3_MouseDown);
            this.bunifuImageButton3.MouseLeave += new System.EventHandler(this.bunifuImageButton3_MouseLeave);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(37)))), ((int)(((byte)(46)))), ((int)(((byte)(59)))));
            this.ClientSize = new System.Drawing.Size(920, 535);
            this.Controls.Add(this.navbar);
            this.Controls.Add(this.header);
            this.Controls.Add(this.DesktopPanel);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.None;
            this.Name = "Form1";
            this.Text = "Form1";
            this.KeyDown += new System.Windows.Forms.KeyEventHandler(this.Form1_KeyDown);
            this.KeyUp += new System.Windows.Forms.KeyEventHandler(this.Form1_KeyUp);
            this.header.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).EndInit();
            this.navbar.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.indicator)).EndInit();
            this.DesktopPanel.ResumeLayout(false);
            this.DesktopPanel.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private Bunifu.Framework.UI.BunifuElipse bunifuElipse1;
        private System.Windows.Forms.Panel navbar;
        private System.Windows.Forms.Panel header;
        private Bunifu.Framework.UI.BunifuDragControl bunifuDragControl1;
        private Bunifu.UI.WinForms.BunifuImageButton play_button;
        private Bunifu.UI.WinForms.BunifuImageButton home_button;
        private System.Windows.Forms.PictureBox pictureBox1;
        private Bunifu.UI.WinForms.BunifuImageButton close_button;
        private Bunifu.UI.WinForms.BunifuImageButton minimize_button;
        private System.Windows.Forms.PictureBox indicator;
        private Bunifu.UI.WinForms.BunifuImageButton bunifuImageButton12;
        private Bunifu.UI.WinForms.BunifuImageButton bunifuImageButton11;
        private Bunifu.UI.WinForms.BunifuImageButton bunifuImageButton10;
        private Bunifu.UI.WinForms.BunifuImageButton bunifuImageButton9;
        private Bunifu.UI.WinForms.BunifuImageButton bunifuImageButton8;
        private Bunifu.UI.WinForms.BunifuImageButton bunifuImageButton7;
        private Bunifu.UI.WinForms.BunifuImageButton bunifuImageButton6;
        private Bunifu.UI.WinForms.BunifuImageButton bunifuImageButton5;
        private Bunifu.UI.WinForms.BunifuImageButton bunifuImageButton4;
        private Bunifu.UI.WinForms.BunifuImageButton bunifuImageButton3;
        private Bunifu.UI.WinForms.BunifuImageButton bunifuImageButton2;
        private Bunifu.UI.WinForms.BunifuImageButton bunifuImageButton1;
        private Bunifu.UI.WinForms.BunifuImageButton bunifuImageButton14;
        private Bunifu.UI.WinForms.BunifuImageButton bunifuImageButton13;
        private System.IO.Ports.SerialPort serialPort1;
        private Bunifu.UI.WinForms.BunifuLabel bunifuLabel2;
        private Bunifu.UI.WinForms.BunifuLabel bunifuLabel1;
        private Bunifu.UI.WinForms.BunifuDropdown port_select;
        private Bunifu.ToggleSwitch.BunifuToggleSwitch record;
        private Bunifu.UI.WinForms.BunifuLabel Record_label;
        private Bunifu.UI.WinForms.BunifuButton.BunifuButton Play_record;
        private System.Windows.Forms.Label song_speed;
        private Bunifu.UI.WinForms.BunifuDropdown speed_select;
        private System.Windows.Forms.Panel DesktopPanel;
        private Bunifu.UI.WinForms.BunifuImageButton bunifuImageButton15;
        private Bunifu.UI.WinForms.BunifuButton.BunifuButton bunifuButton1;
    }
}


using System;
using System.Collections.Generic;
using System.IO;
using Xamarin.Forms;

namespace SArticle.Views
{
    public partial class History : ContentPage
    {
        public History()
        {
            InitializeComponent();
            list_history();
        }

        void list_history()
        {
            string path = Environment.GetFolderPath(Environment.SpecialFolder.Personal);
            StackLayout stackLayout = new StackLayout();
            if (!File.Exists(path + "/history.his"))
                return;
            var lines = File.ReadAllLines(path+"/history.his");
             
            foreach (var file in lines)
            {

                Frame frame = new Frame();
                frame.HasShadow = false;
                Grid layout = new Grid();
                RowDefinition rr = new RowDefinition();
                rr.Height = 50;
                layout.RowDefinitions.Add(rr);

                Label label = new Label();
                label.LineBreakMode = LineBreakMode.NoWrap;
                label.Text = file;
                label.TextColor = Color.Black;
                label.FontSize = 16;
                label.Margin = 10;
                layout.Children.Add(label, 0, 0);
                Grid.SetColumnSpan(label, 4);
                ImageButton button = new ImageButton();
                button.BindingContext = file;
                button.Clicked += delete_history;
                button.Source = "delete.png"; button.BackgroundColor = Color.Transparent;
                button.Margin = 10;
                layout.Children.Add(button, 4, 0);
                layout.BackgroundColor = Color.Transparent;
                frame.Content = layout;
                frame.CornerRadius = 10;
                frame.BackgroundColor = Color.White;
                frame.Padding = 0;
                stackLayout.Children.Add(frame);
                Vapolia.Lib.Ui.Gesture.SetTapCommand(frame, go_history(file));

            }
            hsscroll.Content = stackLayout;


        }
        void delete_history(object sender, EventArgs e)
        {
            string fname = ((ImageButton)sender).BindingContext as string;
            string path = Environment.GetFolderPath(Environment.SpecialFolder.Personal);
            var lines = File.ReadAllLines(path + "/history.his");
            using (System.IO.StreamWriter file = new System.IO.StreamWriter(path + "/history.his", false)) {
                var del = false;
                foreach (var f in lines)
                {   

                    if (f.Equals(fname) && !del)
                    {
                        del = true;
                        continue;
                    }
                    else
                    {
                        file.WriteLine(f);

                    }
                }
            }

         

                list_history();

        }
        public Command go_history(String filename) => new Command(() =>
        {
            Navigation.PushAsync(new FirstWeb(filename));


        });
    }
}

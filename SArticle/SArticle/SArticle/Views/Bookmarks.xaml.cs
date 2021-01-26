using System;
using System.IO;
using Xamarin.Forms;
using Android.Webkit;
using System.Threading.Tasks;
using Xamarin.Essentials;

namespace SArticle.Views
{
    public partial class Bookmarks : ContentPage
    {





        public Bookmarks()
        {
            InitializeComponent();
            list_bookmarks();

        }


        void list_bookmarks()
        {
            string path = Environment.GetFolderPath(Environment.SpecialFolder.Personal);
            string[] files = Directory.GetFiles(path);
            StackLayout stackLayout = new StackLayout();
            foreach (var file in files) {
                if (file.Contains("history.his"))
                    continue;
                Frame frame = new Frame();
                frame.HasShadow = false;
                Grid layout = new Grid();
                RowDefinition rr = new RowDefinition();
                rr.Height = 50;
                layout.RowDefinitions.Add(rr);
                Label label = new Label();
                label.LineBreakMode = LineBreakMode.NoWrap;
                label.Text = Path.GetFileName(file);
                label.TextColor = Color.Black;
                label.FontSize = 16;
                label.Margin = 10;
                layout.Children.Add(label,0,0);
                Grid.SetColumnSpan(label, 4);
                ImageButton button = new ImageButton();
                button.BindingContext = file;
                button.Clicked += delete_article;
                button.Source = "delete.png";button.BackgroundColor = Color.Transparent;
                button.Margin = 10;
                layout.Children.Add(button,4,0);
                layout.BackgroundColor = Color.Transparent;

                frame.Content = layout;
                frame.CornerRadius = 10;
                frame.BackgroundColor = Color.White;
                frame.Padding = 0;
                stackLayout.Children.Add(frame);
                Vapolia.Lib.Ui.Gesture.SetTapCommand(frame, go_saved(file));
            }
            bkscroll.Content = stackLayout;


        }


        void delete_article(object sender, EventArgs e)
        {
            string fname = ((ImageButton)sender).BindingContext as string;
            bool doesTypeOneExist = File.Exists(fname);
            if (doesTypeOneExist)
            {
                File.Delete(fname);

            }
            list_bookmarks();

        }
     

        public Command go_saved(String filename) => new Command(() =>
        {
            string type = Path.GetExtension(filename);
           

        if (type != null && type.Contains("pdf")) {
                Navigation.PopAsync(false);
                Navigation.PopAsync(false);
                Navigation.PushAsync(new PdfView(filename));

            }
            else {
                  string text = File.ReadAllText(filename);
                  Navigation.PopAsync(false);
                  Navigation.PopAsync(false);
                  Navigation.PushAsync(new Page2(text, Path.GetFileName(filename)));

            }


        });

        public static void CopyStream(Stream input, Stream output)
        {
            byte[] buffer = new byte[8 * 1024];
            int len;
            while ((len = input.Read(buffer, 0, buffer.Length)) > 0)
            {
                output.Write(buffer, 0, len);
            }
        }
        public async  void Import(object sender, EventArgs e)
        {
            var picres = await FilePicker.PickAsync(new PickOptions { 
            PickerTitle = "Select pdf file",
            FileTypes = FilePickerFileType.Pdf
            });

            if (picres != null)
            {
                string path = Environment.GetFolderPath(Environment.SpecialFolder.Personal);
                string fullname = path + "/" + picres.FileName;
                var stream = await picres.OpenReadAsync();
                if (File.Exists(fullname))
                {
                    File.Delete(fullname);
                }
                using (Stream file = File.Create(fullname))
                {
                    CopyStream(stream, file);
                }
            }


            list_bookmarks();
        }
    }
}

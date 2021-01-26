using System;
using System.IO;
using System.Net;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using Xamarin.Essentials;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace SArticle.Views
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class FirstWeb : ContentPage
    {

        String base_url;
     
            public FirstWeb(String url)
        {
            InitializeComponent();
            fweb.Navigated += Webview_Navigating;           

            fweb.VerticalOptions = LayoutOptions.FillAndExpand;
            fweb.HorizontalOptions = LayoutOptions.FillAndExpand;
            fweb.Margin = 0;
            fweb.Source = url;
            base_url = url;



        }
        async private void Webview_Navigating(object sender, WebNavigatedEventArgs e)
        {
           
            var url = e.Url;
            string path = Environment.GetFolderPath(Environment.SpecialFolder.Personal);
            string filename = Path.Combine(path, "history.his");
            bool doesTypeOneExist = File.Exists(filename);
            using (System.IO.StreamWriter file = new System.IO.StreamWriter(filename, true))
            {
                file.WriteLine(url);

            }

            string tit = await fweb.EvaluateJavaScriptAsync("document.title");
            title.Text = tit;
            base_url = e.Url;

        }

        void clean_page(object sender, EventArgs args)
        {

            Navigation.PushAsync(new Page2(base_url));
        }

        public void Handle_Clicked(object sender, EventArgs e)
        {
            Navigation.PopAsync();
        }
        public void back(object sender, EventArgs e)
        {
            fweb.GoBack();
        }
    }
}

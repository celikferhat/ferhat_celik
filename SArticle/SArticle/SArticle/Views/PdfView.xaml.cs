using System;
using System.Collections.Generic;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace SArticle.Views
{
    public partial class PdfView : ContentPage
    {
        private string file_path;
        public PdfView(string file_path)
        {
            InitializeComponent();
            this.file_path = file_path;
            
           
            if (Device.RuntimePlatform == Device.Android)
                pdfweb.Source = $"file:///android_asset/pdfjs/web/viewer.html?file={"file:///" + WebUtility.UrlEncode(file_path)}";

            else
                pdfweb.Source = file_path;


        }
    }
}

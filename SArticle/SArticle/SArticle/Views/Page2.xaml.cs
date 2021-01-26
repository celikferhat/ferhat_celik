using HtmlAgilityPack;
using System;
using System.Collections.Generic;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace SArticle.Views
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class Page2 : ContentPage
    {

        public TransparentWebView sayfa;
        Frame setting_frame;
        Grid _settings;
        AbsoluteLayout base_layout;
        String web_address;
        Label f_yuzde;
        bool control;
        bool bkmrk = false;
        String article_content;  // soundda lazım
        HtmlWebViewSource htmlSource;
        String ArticleName;
        ImageButton bookmark;
        bool new_or_saved;
        Frame search_frame;
        Grid top_settings;
        String raw_data;
        CustomEntry search_entry;
        public Page2(string url_string)
        {
            InitializeComponent();
            new_or_saved = true;
            web_address = url_string;
            control = false;
      

        }

        public Page2(string content,string name)
        {
            InitializeComponent();
            new_or_saved = false;
            control = false;
            raw_data = content;
            ArticleName = name;
            var doc = new HtmlDocument();
            doc.LoadHtml(raw_data);
             article_content = doc.GetElementbyId("readability-page-1").InnerText;



        }

      async protected override void OnDisappearing()
        {
            if (Device.RuntimePlatform == Device.Android)
            {
                bookmark_check();
                if (bkmrk)
                    await save_file();


            }
         

        }
        async void back_clicked(object sender, EventArgs e)
        {
           
            if (Device.RuntimePlatform == Device.iOS)
            {
                bookmark_check();
                if (bkmrk)
                    await save_file();
                

            }

        

            await Navigation.PopAsync();

        }


        protected async override void OnAppearing()
        {
            base.OnAppearing();



            if (!control)
            {
                await WaitAndExecute(1000, () =>
                {
                    try
                    {

                        Load_Content();

                    }
                    catch (Exception e) {
                        DisplayAlert("Connection Error","Site may be protected\n " + e.Message,"ok");
                        Navigation.PopAsync();
                    }
                });


                control = true;
            }
            if(control)
            {
                bookmark_check();
            }

        }



        protected async Task WaitAndExecute(int milisec, Action actionToExecute)
        {
            await Task.Delay(milisec);
            actionToExecute();
        }


        String filter_content() {
            SmartReader.Reader sr = new SmartReader.Reader(web_address);
            sr.CharThreshold = 300;
            sr.ContinueIfNotReadable = true;
            SmartReader.Article article = sr.GetArticle();
            article_content = article.TextContent;
            ArticleName = article.Title;


            var doc = new HtmlDocument();
            var meta = "<meta charset=\"utf-8\">  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0   maximum-scale=1.0, user-scalable=no  \">  ";

            var rangy = "<script type=\"text/javascript\" src=\"rangy-core.js\"></script>\n<script type=\"text/javascript\" src=\"rangy-classapplier.js\"></script>\n<script type=\"text/javascript\" src=\"rangy-highlighter.js\"></script> <script  src=\"https://code.jquery.com/jquery-3.5.1.min.js\"  integrity=\"sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=\"  crossorigin=\"anonymous\"></script> ";



            var mig_content = meta + article.Content + rangy + "<style type=\"text/css\">    .highlight { background-color: yellow; }   </style> <script>  var serializedHighlights = decodeURIComponent(window.location.search.slice(window.location.search.indexOf(\"=\") + 1));\n        var highlighter;\n\n        var initialDoc;\n\n               window.onload = function() {\n            rangy.init();\n\n           highlighter = rangy.createHighlighter();\n\n            highlighter.addClassApplier(rangy.createClassApplier(\"highlight\", {\n                ignoreWhiteSpace: true,\n                tagNames: [\"span\", \"a\"]\n            }));\n\n              if (serializedHighlights) {\n                highlighter.deserialize(serializedHighlights);\n            }\n        };\n\n\n        function highlightSelectedText() {\n            highlighter.highlightSelection(\"highlight\");\n      highlighter = rangy.createHighlighter();\n            \n            highlighter.addClassApplier(rangy.createClassApplier(\"highlight\", {\n                                                                 ignoreWhiteSpace: true,\n                                                                 tagNames: [\"span\", \"a\"]\n                                                                 }));   };       function unhighlight() {\n        var parentEl = null, sel;\n        if (window.getSelection) {\n            sel = window.getSelection();\n            if (sel.rangeCount) {\n                parentEl = sel.getRangeAt(0).commonAncestorContainer;\n                if (parentEl.nodeType != 1) {\n                    parentEl = parentEl.parentNode;\n                }\n            }\n        } else if ( (sel = document.selection) && sel.type != \"Control\") {\n            parentEl = sel.createRange().parentElement();\n        }\n        if(parentEl.tagName ==\"SPAN\" && parentEl.className == \"highlight\"){\n            var jelm = $(parentEl);\n            jelm.contents().unwrap();\n        }\n        \n        \n    }     </script><script type=\"text/javascript\" src=\"search.js\"></script>";
            doc.LoadHtml(mig_content);
            doc.GetElementbyId("readability-page-1").PrependChild(HtmlNode.CreateNode("<div><h1>" + article.Title + "</h1>" + "<a> <sub>" + article.SiteName + "</sub> </a>\n<hr></div>"));



            if (doc.DocumentNode.SelectNodes("//*[self::img or self::video or self::iframe or self::table]") != null)
            {
                foreach (HtmlNode inner in doc.DocumentNode.SelectNodes("//*[self::img or self::video or self::iframe or self::table]"))
                {
                    inner.SetAttributeValue("width", "100%");
                    inner.SetAttributeValue("height", "50%");

                }
            }

            return doc.DocumentNode.OuterHtml;

        }

       void Load_Content()
        {
            base_layout = new AbsoluteLayout();           
            htmlSource = new HtmlWebViewSource();
            if(new_or_saved)
                htmlSource.Html = filter_content();
            else
                htmlSource.Html = raw_data;

           
            sayfa = new TransparentWebView();
            sayfa.Source = htmlSource;
            base_layout.Children.Add(sayfa, new Rectangle(0, 0, 1, 1), AbsoluteLayoutFlags.All);

            sayfa.VerticalOptions = LayoutOptions.FillAndExpand;
            sayfa.HorizontalOptions = LayoutOptions.FillAndExpand;
            sayfa.Margin = 0;



            _settings = settings_page();

            setting_frame = new Frame();
            setting_frame.IsVisible = false;
            setting_frame.HasShadow = true;
            setting_frame.CornerRadius = 10;
            setting_frame.Padding = 0;
            setting_frame.BackgroundColor = Color.FromHex("#2c2c2c");
            setting_frame.Content = _settings;


            top_settings = top_pane();
            base_layout.Children.Add(setting_frame, new Rectangle(0.5, 0.95, AbsoluteLayout.AutoSize, AbsoluteLayout.AutoSize), AbsoluteLayoutFlags.PositionProportional);
            base_layout.Children.Add(top_settings, new Rectangle(0.5, 0.05, AbsoluteLayout.AutoSize, AbsoluteLayout.AutoSize), AbsoluteLayoutFlags.PositionProportional);

            Grid search_panel = search_pane();
            search_frame = new Frame();
            search_frame.Opacity = 0.8;
            search_frame.IsVisible = false;
            search_frame.CornerRadius = 25;
            search_frame.Padding = 0;
            search_frame.HasShadow = false;
            search_frame.BackgroundColor = Color.FromHex("#2c2c2c");
            search_frame.Content = search_panel;
            base_layout.Children.Add(search_frame, new Rectangle(0.5, 0.05, AbsoluteLayout.AutoSize, AbsoluteLayout.AutoSize), AbsoluteLayoutFlags.PositionProportional);


            Vapolia.Lib.Ui.Gesture.SetDoubleTapCommand(sayfa, new Command<Point>(Point => { setting_frame.IsVisible = !setting_frame.IsVisible; top_settings.IsVisible = !top_settings.IsVisible; }));

            Content = base_layout;
            if(!new_or_saved)
                start_font_size();

        }

        async void add_bookmark(object sender, EventArgs e)
        {

            bkmrk = !bkmrk;

            var b = (ImageButton)sender;
            if (bkmrk == true)
            {
                b.Source = "bookmark_filled.png";
               await save_file();
            }
            else
            {
                b.Source = "bookmark.png";
                delete_file();

            }
        }

        void sound_clicked(object sender, EventArgs e)
        {           
            Navigation.PushAsync(new Speech(article_content));

        }
        void saved_clicked(object sender, EventArgs e)
        {
            Navigation.PushAsync(new Bookmarks());

        }
   

        async Task save_file()
        {

            string path = Environment.GetFolderPath(Environment.SpecialFolder.Personal);
            string filename = Path.Combine(path, ArticleName);
            bool doesTypeOneExist = File.Exists(filename);
           
            var page = await sayfa.EvaluateJavaScriptAsync("document.documentElement.innerHTML");
            
            // Unescape that damn Unicode Java bull.
            page = Regex.Replace(page, @"\\[Uu]([0-9A-Fa-f]{4})", m => char.ToString((char)ushort.Parse(m.Groups[1].Value, NumberStyles.AllowHexSpecifier)));
            page = Regex.Unescape(page);

            using (System.IO.StreamWriter file = new System.IO.StreamWriter(filename, false))
            {
                file.WriteLine(page);
            }


        }

        void delete_file() {
            string path = Environment.GetFolderPath(Environment.SpecialFolder.Personal);
            string filename = Path.Combine(path, ArticleName);
            bool doesTypeOneExist = File.Exists(filename);
            if (doesTypeOneExist) {
                File.Delete(filename);
            
            }


        }

        void bookmark_check() {
            string path = Environment.GetFolderPath(Environment.SpecialFolder.Personal);
            string filename = Path.Combine(path, ArticleName);
            bool doesTypeOneExist = File.Exists(filename);
            if (doesTypeOneExist)
            {
                bkmrk = true;
                bookmark.Source = "bookmark_filled.png";

            }
            else {
                bkmrk = false;
                bookmark.Source = "bookmark.png";
            }
        }



        Grid search_pane() { 
            Grid search = new Grid { Padding = 5, BackgroundColor = Color.Transparent, IsVisible = true  };
            search.Margin = 5;
            search.Padding = 5; 
            search_entry = new CustomEntry();
            search_entry.TextColor = Color.White;
            search_entry.PlaceholderColor = Color.FromHex("#f0f0f0");
            search_entry.Placeholder = "Search";
            search_entry.BackgroundColor = Color.Transparent;
            search_entry.TextChanged += Entry_TextChanged;
            search.Children.Add(search_entry, 1,0);
            ImageButton close = new ImageButton { HeightRequest = 10, WidthRequest = 10,Aspect = Aspect.AspectFit , Source = "cross.png" ,BackgroundColor = Color.Transparent};
            ImageButton up = new ImageButton { Aspect = Aspect.AspectFit, Source = "up.png", BackgroundColor = Color.Transparent };
            up.Clicked += Up_Clicked;
            ImageButton down = new ImageButton { Aspect = Aspect.AspectFit, Source = "down.png", BackgroundColor = Color.Transparent };
            down.Clicked += Down_Clicked;
            search.Children.Add(close, 0, 0);
            search.Children.Add(up, 5, 0);
            search.Children.Add(down, 7, 0);
            Grid.SetColumnSpan(search_entry, 4);
            
            close.Clicked += Close_Clicked;
            return search;
        }

        async void Up_Clicked(object sender, EventArgs e)
        {
            await sayfa.EvaluateJavaScriptAsync("up_f();");
        }

        async void Down_Clicked(object sender, EventArgs e)
        {
            await sayfa.EvaluateJavaScriptAsync("down_f();");
        }


        async void Entry_TextChanged(object sender, TextChangedEventArgs e)
        {
            if (!e.NewTextValue.Equals(""))
            {
                await sayfa.EvaluateJavaScriptAsync("MyApp_HighlightAllOccurencesOfString(\"" + e.NewTextValue + "\");");
            }
            else {
                await sayfa.EvaluateJavaScriptAsync("MyApp_RemoveAllHighlights();");
            }

        }


        async void Close_Clicked(object sender, EventArgs e)
        {
            search_frame.IsVisible = false;
            search_entry.Text = "";
            await sayfa.EvaluateJavaScriptAsync("MyApp_RemoveAllHighlights();");
        }


        Grid top_pane()
        {
            Grid top_panel = new Grid { Padding = 5, BackgroundColor = Color.Transparent, IsVisible = false };
            RowDefinition rd = new RowDefinition();
            rd.Height = GridLength.Star;
            top_panel.RowDefinitions.Add(rd);
            top_panel.Margin = 5;
            top_panel.Padding = 5;

            ImageButton back = new ImageButton { HeightRequest = 50, WidthRequest = 50, CornerRadius = 25, Aspect = Aspect.AspectFit, Source = "back_arrow.png", BackgroundColor = Color.FromHex("#2c2c2c") };
            back.Clicked += back_clicked;
            bookmark = new ImageButton { HeightRequest = 50, WidthRequest = 50, CornerRadius = 25, Padding = 10, Aspect = Aspect.AspectFit, Source = "bookmark.png", BackgroundColor = Color.FromHex("#2c2c2c") };
            bookmark.Clicked += add_bookmark;
            ImageButton sound = new ImageButton { HeightRequest = 50, WidthRequest = 50, CornerRadius = 25, Padding = 10, Aspect = Aspect.AspectFit, Source = "sound.png", BackgroundColor = Color.FromHex("#2c2c2c") };
            sound.Clicked += sound_clicked;
            ImageButton saved = new ImageButton { HeightRequest = 50, WidthRequest = 50, CornerRadius = 25, Padding = 10, Aspect = Aspect.AspectFit, Source = "saved.png", BackgroundColor = Color.FromHex("#2c2c2c") };
            saved.Clicked += saved_clicked;
            ImageButton src = new ImageButton { HeightRequest = 50, WidthRequest = 50, CornerRadius = 25, Padding = 10, Aspect = Aspect.AspectFit, Source = "search.png", BackgroundColor = Color.FromHex("#2c2c2c") };
            src.Clicked += src_clicked;

            top_panel.Children.Add(back, 1, 0);
            top_panel.Children.Add(bookmark, 2, 0);
            top_panel.Children.Add(sound, 4, 0);
            top_panel.Children.Add(saved, 3, 0);
            top_panel.Children.Add(new Label(), 0, 0);
            top_panel.Children.Add(src, 5, 0);
            top_panel.Children.Add(new Label(), 6, 0);



            return top_panel;
        }

        private void src_clicked(object sender, EventArgs e)
        {
            top_settings.IsVisible = false;
            setting_frame.IsVisible = false;
            search_frame.IsVisible = true;
        }

        Grid settings_page()
        {

            Grid settings = new Grid { Padding = 5, BackgroundColor = Color.FromHex("#2c2c2c"), IsVisible = true };
            settings.Margin = 5;
            settings.WidthRequest = (Application.Current.MainPage.Width / 20) * 13;
            settings.HeightRequest = (Application.Current.MainPage.Height / 5);
            ImageButton white_bg = new ImageButton { CornerRadius = 5, Aspect = Aspect.AspectFill, Margin = 0, Source = "white.png", BackgroundColor = Color.Transparent };
            ImageButton bej_bg = new ImageButton { CornerRadius = 5, Aspect = Aspect.AspectFill, Margin = 0, Source = "bej.png", BackgroundColor = Color.Transparent };
            ImageButton gray_bg = new ImageButton { CornerRadius = 5, Aspect = Aspect.AspectFill, Margin = 0, Source = "gray.png", BackgroundColor = Color.Transparent };
            ImageButton black_bg = new ImageButton { CornerRadius = 5, Aspect = Aspect.AspectFill, Margin = 0, Source = "black.png", BackgroundColor = Color.Transparent };
            white_bg.Clicked += white_background;
            bej_bg.Clicked += bej_background;
            gray_bg.Clicked += gray_background;
            black_bg.Clicked += black_background;

            settings.Children.Add(white_bg, 0, 1);
            settings.Children.Add(bej_bg, 1, 1);
            settings.Children.Add(gray_bg, 2, 1);
            settings.Children.Add(black_bg, 3, 1);


            ImageButton f_inc = new ImageButton { HorizontalOptions = LayoutOptions.Start, Aspect = Aspect.AspectFit, Padding = 0 , Margin = 8, Source = "f_inc.png", BackgroundColor = Color.Transparent };
            ImageButton f_dec = new ImageButton { HorizontalOptions = LayoutOptions.End, Aspect = Aspect.AspectFit, Padding = 0,  Margin = 0, Source = "f_dec.png", BackgroundColor = Color.Transparent };
            f_yuzde = new Label { Text = "100", FontSize = 10, TextColor = Color.White, VerticalOptions = LayoutOptions.Center , HorizontalOptions = LayoutOptions.Center };

            f_inc.Clicked += fs_up;
            f_dec.Clicked += fs_down;




            Frame font_frame = new Frame();
            font_frame.HasShadow = false;
            font_frame.Padding = 0;
            font_frame.Margin = new Thickness(0, 10, 0, 10);
            font_frame.CornerRadius = 30;
            font_frame.BackgroundColor = Color.FromHex("#1e1e1e");
            Grid fgrid = new Grid { BackgroundColor = Color.Transparent ,Padding=0};
            fgrid.Children.Add(f_dec, 0, 0);
            fgrid.Children.Add(f_yuzde, 1, 0);
            fgrid.Children.Add(f_inc, 2, 0);

            font_frame.Content = fgrid;

            settings.Children.Add(font_frame, 2, 0);
            Grid.SetColumnSpan(font_frame, 2);


            Frame picker_frame = new Frame();
            picker_frame.Padding = new Thickness(5, 0, 5, 0);
            picker_frame.HasShadow = false;
            picker_frame.Margin = new Thickness(0, 10, 0, 10);
            picker_frame.CornerRadius = 30;
            picker_frame.BackgroundColor = Color.FromHex("#1e1e1e");

            Picker font_picker = new Picker { Title = "Select font", TitleColor = Color.Black, FontSize = 13 };
            List<String> fontlar = new List<string>();
            fontlar.Add("Times New Roman");
            fontlar.Add("Arial");
            fontlar.Add("Courier");
            fontlar.Add("Monospace");
            fontlar.Add("Sans-serif");

            font_picker.ItemsSource = fontlar;
            font_picker.SelectedIndex = 0;
            font_picker.TextColor = Color.White;
            font_picker.SelectedIndexChanged += OnPickerSelectedIndexChanged;
            font_picker.BackgroundColor = Color.Transparent;
            font_picker.HorizontalOptions = LayoutOptions.Center;
            picker_frame.Content = font_picker;
            settings.Children.Add(picker_frame, 0, 0);
            Grid.SetColumnSpan(picker_frame, 2);


            return settings;
        }


        async private void OnPickerSelectedIndexChanged(object sender, EventArgs e)
        {
            Picker picker = sender as Picker;
            var selectedItem = picker.SelectedItem; // This is the model selected in the picker
            await sayfa.EvaluateJavaScriptAsync("document.getElementById(\"readability-page-1\").style.fontFamily=\"" + selectedItem.ToString() + "\"");
        }



        void start_font_size() {
            var doc = new HtmlDocument();
            doc.LoadHtml(raw_data);
            var font_size = doc.GetElementbyId("readability-page-1").GetAttributeValue("style", null);
            if (font_size != null && font_size.Contains("font-size"))
            {
                
               
               var resultString = Regex.Match(font_size, @"\d+").Value;

                int diff = int.Parse(resultString) - 16;
                int yuzde = 100 + ((diff / 2) * 10);
                f_yuzde.Text = yuzde.ToString();

            }
        }

        async void fs_up(object sender, EventArgs args)
        {

            var font_size = await sayfa.EvaluateJavaScriptAsync("document.getElementById(\"readability-page-1\").style.fontSize");
            if (font_size.ToString().Equals(""))
            {
                await sayfa.EvaluateJavaScriptAsync("document.getElementById(\"readability-page-1\").style.fontSize='18px'");
                f_yuzde.Text = "110";
            }
            else
            {
                font_size = font_size.ToString().Substring(0, 2);
                int size = Int32.Parse(font_size) + 2;
                if (size <= 30)
                {
                    await sayfa.EvaluateJavaScriptAsync("document.getElementById(\"readability-page-1\").style.fontSize='" + size.ToString() + "px'");
                    var yuzde = Int32.Parse(f_yuzde.Text);
                    yuzde += 10;
                    f_yuzde.Text = yuzde.ToString();
                }
            }




        }
        async void fs_down(object sender, EventArgs args)
        {
            var font_size = await sayfa.EvaluateJavaScriptAsync("document.getElementById(\"readability-page-1\").style.fontSize");
            if (font_size.ToString().Equals(""))
            {
                await sayfa.EvaluateJavaScriptAsync("document.getElementById(\"readability-page-1\").style.fontSize='14px'");
                f_yuzde.Text = "90";
            }
            else
            {
                font_size = font_size.ToString().Substring(0, 2);
                int size = Int32.Parse(font_size) - 2;
                if (size >= 10)
                {

                    await sayfa.EvaluateJavaScriptAsync("document.getElementById(\"readability-page-1\").style.fontSize='" + size.ToString() + "px'");
                    var yuzde = Int32.Parse(f_yuzde.Text);
                    yuzde -= 10;
                    f_yuzde.Text = yuzde.ToString();
                }

            }

        }

        async void white_background(object sender, EventArgs args)
        {
            base_layout.BackgroundColor = Color.White;
            await sayfa.EvaluateJavaScriptAsync("document.getElementById(\"readability-page-1\").style.color='black'");

        }
        async void bej_background(object sender, EventArgs args)
        {
            base_layout.BackgroundColor = Color.FromHex("#f6efdc");
            await sayfa.EvaluateJavaScriptAsync("document.getElementById(\"readability-page-1\").style.color='black'");

        }
        async void gray_background(object sender, EventArgs args)
        {
            base_layout.BackgroundColor = Color.FromHex("#585858");
            await sayfa.EvaluateJavaScriptAsync("document.getElementById(\"readability-page-1\").style.color='white'");

        }
        async void black_background(object sender, EventArgs args)
        {
            base_layout.BackgroundColor = Color.Black;
            await sayfa.EvaluateJavaScriptAsync("document.getElementById(\"readability-page-1\").style.color='white'");


        }


     


    }
}
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using Xamarin.Essentials;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace SArticle.Views
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class Speech : ContentPage
    {
        string[] strList;
        String article;
        bool pp = false;
        bool Stop = false;
        int last = 0;
        CancellationTokenSource cts;
        int node = 0;
        Slider slider;
        ImageButton play;
        Picker lang_picker =  new Picker();
        SpeechOptions settings = new SpeechOptions();
        Array locale_list;

        protected async override void OnAppearing() {
            var locales = await TextToSpeech.GetLocalesAsync();
            List<String> llist = new List<string>();
            locale_list = locales.ToArray();
            int trindex = 0;
            int ct = 0;
            foreach (var locale in locales) {
                llist.Add(locale.Name);
                if (locale.Language.Equals("tr"))
                    trindex = ct;
                ct++;
                }
            lang_picker.ItemsSource = llist;
            lang_picker.SelectedIndex = trindex;
            settings.Locale = (Locale)locale_list.GetValue(trindex);
            lang_picker.BackgroundColor = Color.Transparent;
            
        }


        protected override void OnDisappearing()
        {
            base.OnDisappearing();
            CancelSpeech();
        }
       




            public Speech(String s)
        {
            InitializeComponent();
            article = s;
            ArticleContent.Text = s;

            string str = "\n";
            char character = char.Parse(str);


            strList = article.Split(new char[] { character});
            node = strList.Length - 1;
            switch (Device.RuntimePlatform)
            {
                case Device.Android:
                    NavigationPage.SetHasNavigationBar(this, false);
                    break;
                default:
                    break;
            };

            var mainDisplayInfo = DeviceDisplay.MainDisplayInfo;
            var density = mainDisplayInfo.Density;
            var height = mainDisplayInfo.Height;
            var width = mainDisplayInfo.Width;
            Frame frame = sp_settings();
            frame.HeightRequest = (height / density) / 8;
            frame.WidthRequest = (width / density)  *3/ 4;
            sp_layout.Children.Add(frame,0,1);
            slider.Maximum = strList.Length - 1;
            lang_picker.SelectedIndexChanged += OnPickerSelectedIndexChanged;


        }
        private void OnPickerSelectedIndexChanged(object sender, EventArgs e)
        {
            Picker picker = sender as Picker;
            var selectedItem = picker.SelectedIndex;
            if(locale_list != null && settings != null)
                settings.Locale = (Locale)locale_list.GetValue(selectedItem);

        }

        Frame sp_settings() {       

            Frame frame = new Frame();
            frame.BackgroundColor = Color.FromHex("#2c2c2c");
            frame.CornerRadius = 10;
            Grid grid = new Grid();

            lang_picker.TextColor = Color.White;

            play = new ImageButton { WidthRequest=30,HeightRequest=30,BackgroundColor=Color.Transparent,Source="play.png"};
            play.Clicked += play_clicked;
            grid.Children.Add(play,3,2);
            slider = new Slider {MinimumTrackColor=Color.White,ThumbColor=Color.White , IsEnabled=false, Minimum = 0};
            grid.Children.Add(slider,0,0);
            grid.Children.Add(lang_picker, 0, 2);
            Grid.SetColumnSpan(lang_picker, 2);
            // Grid.SetRowSpan(lang_picker,2);
            lang_picker.FontSize = 12;
            Grid.SetColumnSpan(slider, 5);
            ImageButton prev = new ImageButton { WidthRequest = 30, HeightRequest = 30, BackgroundColor = Color.Transparent, Source = "prev.png" };
            prev.Clicked += ImageButton_Clicked;
            ImageButton next = new ImageButton { WidthRequest = 30, HeightRequest = 30, BackgroundColor = Color.Transparent, Source = "next.png" };
            next.Clicked += ImageButton_Clicked_1;
            grid.Children.Add(prev, 2, 2);
            grid.Children.Add(next, 4, 2);
          //  grid.Children.Add(new Label(), 0, 3);
           

            frame.Content = grid;
            return frame;
        }
        public void CancelSpeech()
        {
            Stop = true;
            if (cts?.IsCancellationRequested ?? true)
                return;

            cts.Cancel();
        }
        private void play_clicked(object sender, EventArgs e)
        {
            pp = !pp;
            if (!pp)
            {
                CancelSpeech();
                Stop = true;
                play.Source = "play.png";
            }
            else
            {
                Stop = false;
                play.Source = "pause.png";
                speak();


            }
        }

        async void speak()
        {


          


            string str = "\n";
            char character = char.Parse(str);

            //string str2 = ".";
            //char character2 = char.Parse(str2);
            strList = article.Split(new char[] { character,/* character2*/ });
            
            for (int i = last; i < strList.Length; i++)
            {

                if (!Stop)
                {
                    

                    slider.Value = i;
                    string content = strList[i];
                    if (content.Equals(""))
                        continue;
                    color_text(i);

                    last = i;


                    cts = new CancellationTokenSource();
                    await TextToSpeech.SpeakAsync(content, settings,cancelToken: cts.Token);
                }
                else
                    return;



            }


        }

        private void ImageButton_Clicked(object sender, EventArgs e)
        {
            CancelSpeech(); Stop = true;
            play.Source = "play.png";
            if (last > 0)
            {
                last--;
                color_text(last);

            }


            slider.Value = last;
        }


        private void ImageButton_Clicked_1(object sender, EventArgs e)
        {
            CancelSpeech(); Stop = true;
            play.Source = "play.png";
            if (last < node)
            {
                last++;
                color_text(last);

            }

            slider.Value = last;
        }


        void color_text(int i)
        {
            slider.Value = i;
            string content = strList[i];
            var formattedString = new FormattedString();
            for (int j = 0; j < strList.Length; j++)
            {
                if (i == j)
                {
                    formattedString.Spans.Add(new Span { Text = strList[j] + "\n", ForegroundColor = Color.Black, BackgroundColor = Color.Yellow });
                }
                else
                {
                    formattedString.Spans.Add(new Span { Text = strList[j] + "\n", ForegroundColor = Color.Black, });
                }

            }
            ArticleContent.FormattedText = formattedString;
        }



    }
}
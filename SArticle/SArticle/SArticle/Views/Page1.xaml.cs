using System;
using System.Threading.Tasks;
using Plugin.Permissions;
using Xamarin.Essentials;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;
using Plugin.Permissions.Abstractions;
namespace SArticle.Views
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class Page1 : ContentPage
    {
        public Page1()
        {
            InitializeComponent();
            Vapolia.Lib.Ui.Gesture.SetTapCommand2(google, new Command<Point>(Point => { url_entry.Text = "https://www.google.com/"; }));
            Vapolia.Lib.Ui.Gesture.SetTapCommand2(bing, new Command<Point>(Point => { url_entry.Text = "https://www.bing.com/"; }));
            Vapolia.Lib.Ui.Gesture.SetTapCommand2(yandex, new Command<Point>(Point => { url_entry.Text = "https://www.yandex.com/"; }));
            iconpic.HeightRequest = (DeviceDisplay.MainDisplayInfo.Height / DeviceDisplay.MainDisplayInfo.Density) / 4;
            iconpic.WidthRequest = (DeviceDisplay.MainDisplayInfo.Height / DeviceDisplay.MainDisplayInfo.Density) / 4;
        


        }

        async void paste_clipboard(object sender, EventArgs args)
        {
            var text = await Clipboard.GetTextAsync();
            url_entry.Text = text;

        }

        void go_mainpage(object sender, EventArgs args)
        {

            var url_string = url_entry.Text;

            if (Uri.IsWellFormedUriString(url_string, UriKind.Absolute))
            {
                if(clean_sw.IsToggled)
                    Navigation.PushAsync(new Page2(url_string));
                else
                    Navigation.PushAsync(new FirstWeb(url_string));
            }
            else
            {
                DisplayAlert("Error", "Input is not valid url", "Ok");
            }


        }
        void open_bookmarks(object sender, EventArgs args) {
            Navigation.PushAsync(new Bookmarks());
        }

        void open_history(object sender, EventArgs args)
        {
            Navigation.PushAsync(new History());
        }
        protected async override void OnAppearing()
        {
            base.OnAppearing();
            try
            {
                var status = await CrossPermissions.Current.CheckPermissionStatusAsync<StoragePermission>();
                if (status != Plugin.Permissions.Abstractions.PermissionStatus.Granted)
                {
                    if (await CrossPermissions.Current.ShouldShowRequestPermissionRationaleAsync(Permission.Location))
                    {
                        await DisplayAlert("Need Storage", "Gunna need that storage", "OK");
                    }

                    status = await CrossPermissions.Current.RequestPermissionAsync<StoragePermission>();
                }

                if (status == Plugin.Permissions.Abstractions.PermissionStatus.Granted)
                {
                    //Query permission
                }
                else if (status != Plugin.Permissions.Abstractions.PermissionStatus.Unknown)
                {
                    //location denied
                }
            }
            catch (Exception ex)
            {
               await DisplayAlert("Permission Exception on page 1","Could not grant storage permissions","ok");
            }
        }

        }
}
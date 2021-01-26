using System;

using Android.App;
using Android.Content.PM;
using Android.Runtime;
using Android.Views;
using Android.Widget;
using Android.OS;
using LabelHtml.Forms.Plugin.Droid;
using System.Threading.Tasks;
using Plugin.Permissions;

namespace SArticle.Droid
{
    [Activity(Label = "SArticle", Icon = "@mipmap/icon", Theme = "@style/MainTheme", MainLauncher = true, ConfigurationChanges = ConfigChanges.ScreenSize | ConfigChanges.Orientation | ConfigChanges.UiMode | ConfigChanges.ScreenLayout | ConfigChanges.SmallestScreenSize )]
    public class MainActivity : global::Xamarin.Forms.Platform.Android.FormsAppCompatActivity
    {
        protected override void OnCreate(Bundle savedInstanceState)
        {
            TabLayoutResource = Resource.Layout.Tabbar;
            ToolbarResource = Resource.Layout.Toolbar;

            base.OnCreate(savedInstanceState);         

            Xamarin.Essentials.Platform.Init(this, savedInstanceState);
            global::Xamarin.Forms.Forms.Init(this, savedInstanceState);
            LoadApplication(new App());
        }
     
        public override void OnActionModeStarted(ActionMode mode)
        {
            IMenu menu = mode.Menu;
            menu.Add("Highlight");
            menu.Add("UnHighlight");
            menu.GetItem(0).SetOnMenuItemClickListener(new TransparentWebViewRenderer.MyMenuItemOnMenuItemClickListener(this, mode));
            menu.GetItem(1).SetOnMenuItemClickListener(new TransparentWebViewRenderer.removeMenuItemClickListener(this, mode));
            base.OnActionModeStarted(mode);
        }
        public override void OnRequestPermissionsResult(int requestCode, string[] permissions, [GeneratedEnum] Android.Content.PM.Permission[] grantResults)
        {
            PermissionsImplementation.Current.OnRequestPermissionsResult(requestCode, permissions, grantResults);
            base.OnRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

 



}

using System;
using System.Threading.Tasks;
using Android.Content;
using Android.Views;
using SArticle;
using SArticle.Droid;
using Xamarin.Forms;
using Xamarin.Forms.Platform.Android;

[assembly: ExportRenderer(typeof(TransparentWebView), typeof(TransparentWebViewRenderer))]
namespace SArticle.Droid
{

    class TransparentWebViewRenderer : WebViewRenderer 
    {

        public static WebView webview;
        public TransparentWebViewRenderer(Context context) : base(context)
        {
        }

        protected override void OnElementChanged(ElementChangedEventArgs<WebView> e)
        {
            base.OnElementChanged(e);
            webview = e.NewElement as WebView;
            // Setting the background as transparent
            this.Control.SetBackgroundColor(Android.Graphics.Color.Transparent);
        }

        internal class MyMenuItemOnMenuItemClickListener : Java.Lang.Object, IMenuItemOnMenuItemClickListener
        {
            private MainActivity mainActivity;
            public Func<string, Task<string>> EvaluateJavascript { get; set; }
            private ActionMode mode;
       

            public MyMenuItemOnMenuItemClickListener(MainActivity mainActivity, ActionMode mode)
            {
                this.mainActivity = mainActivity;
                this.mode = mode;
            }
            private async Task ActionDone()
            {

                await webview.EvaluateJavaScriptAsync("highlightSelectedText()");
            }

             public bool OnMenuItemClick(IMenuItem item)
            {
                ActionDone();
                //close menu if menu item is clicked
                if (mode != null)
                    mode.Finish();
                return true;
            }

        }
        internal class removeMenuItemClickListener : Java.Lang.Object, IMenuItemOnMenuItemClickListener
        {
            private MainActivity mainActivity;
            public Func<string, Task<string>> EvaluateJavascript { get; set; }
            private ActionMode mode;


            public removeMenuItemClickListener(MainActivity mainActivity, ActionMode mode)
            {
                this.mainActivity = mainActivity;
                this.mode = mode;
            }
            private async Task ActionDone() {

                await webview.EvaluateJavaScriptAsync("unhighlight()");
            }
            public bool OnMenuItemClick(IMenuItem item)
            {
                ActionDone();
                //close menu if menu item is clicked
                if (mode != null)
                    mode.Finish();
                return true;
            }

        }

    }







}

using SArticle;
using SArticle.iOS;
using UIKit;
using ObjCRuntime;
using Xamarin.Forms;
using Xamarin.Forms.Platform.iOS;
using Foundation;
using System;

[assembly: ExportRenderer(typeof(TransparentWebView), typeof(TransparentWebViewRenderer))]
namespace SArticle.iOS
{
    class TransparentWebViewRenderer : WkWebViewRenderer
    {
        WebView webview;
      

        protected override void OnElementChanged(VisualElementChangedEventArgs e)
        {
            base.OnElementChanged(e);

            webview = e.NewElement as WebView;

            // Setting the background as transparent
            this.Opaque = false;
            this.BackgroundColor = Color.Transparent.ToUIColor();
            if (NativeView != null)
            {
                this.BecomeFirstResponder();

                UIMenuController menuController = UIMenuController.SharedMenuController;
                UIMenuItem item1 = new UIMenuItem("Copy", new Selector("Action1"));
                UIMenuItem item2 = new UIMenuItem("Select All", new Selector("Action2"));
                UIMenuItem item3 = new UIMenuItem("Highlight", new Selector("Action3"));
                UIMenuItem item4 = new UIMenuItem("UnHighlight", new Selector("Action4"));
                UIMenuItem[] items = new UIMenuItem[] { item1, item2, item3 ,item4};
                menuController.MenuItems = items;
            }
        }

        public override bool CanPerform(Selector action, NSObject withSender)
        {
            if (action == new Selector("Action1") || action == new Selector("Action2") || action == new Selector("Action3") || action == new Selector("Action4"))
            {
                return true;
            }
            return false;
        }


        [Export("Action1")]
        void Action1()
        {
           
            Console.WriteLine("111");
        }

        [Export("Action2")]
        void Action2()
        {
            Console.WriteLine("222");
        }

        [Export("Action3")]
        async void Action3()
        {
            await webview.EvaluateJavaScriptAsync("highlightSelectedText()");


        }
        [Export("Action4")]
        async void Action4()
        {
                     
            await webview.EvaluateJavaScriptAsync("unhighlight()");

        }
    }
}
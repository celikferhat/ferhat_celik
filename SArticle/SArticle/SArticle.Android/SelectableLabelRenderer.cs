using System.ComponentModel;
using Android.Content;
using Android.Views;
using Android.Widget;
using SArticle.Controls;
using SArticle.Droid.Controls;
using Xamarin.Forms;
using Xamarin.Forms.Platform.Android;

[assembly: ExportRenderer(typeof(SelectableLabel), typeof(SelectableLabelRenderer))]

namespace SArticle.Droid.Controls
{

    public class MyTextView : TextView
    {
        public MyTextView(Context context) : base(context)
        {

        }

        protected override void OnSelectionChanged(int selStart, int selEnd)
        {
            //here you know the index
        }
    }




    public class SelectableLabelRenderer : ViewRenderer<SelectableLabel, MyTextView>
    {
        MyTextView textView;
        public SelectableLabelRenderer(Context context) : base(context)
        {

        }

        protected override void OnElementChanged(ElementChangedEventArgs<SelectableLabel> e)
        {
            base.OnElementChanged(e);

            var label = (SelectableLabel)Element;
            if (label == null)
                return;

            if (Control == null)
            {
                textView = new MyTextView(this.Context);
            }

            textView.Enabled = true;
            textView.Focusable = true;
            textView.LongClickable = true;
            textView.SetTextIsSelectable(true);

            // Initial properties Set
            textView.Text = label.Text;
            textView.SetTextColor(label.TextColor.ToAndroid());
            switch (label.FontAttributes)
            {
                case FontAttributes.None:
                    textView.SetTypeface(null, Android.Graphics.TypefaceStyle.Normal);
                    break;
                case FontAttributes.Bold:
                    textView.SetTypeface(null, Android.Graphics.TypefaceStyle.Bold);
                    break;
                case FontAttributes.Italic:
                    textView.SetTypeface(null, Android.Graphics.TypefaceStyle.Italic);
                    break;
                default:
                    textView.SetTypeface(null, Android.Graphics.TypefaceStyle.Normal);
                    break;
            }

            textView.TextSize = (float)label.FontSize;


            SetNativeControl(textView);
        }
        protected override void OnElementPropertyChanged(object sender, PropertyChangedEventArgs e)
        {
            base.OnElementPropertyChanged(sender, e);

            if (e.PropertyName == SelectableLabel.TextProperty.PropertyName)
            {
                if (Control != null && Element != null && !string.IsNullOrWhiteSpace(Element.Text))
                {
                    textView.Text = Element.Text;
                }
            }
            else if (e.PropertyName == SelectableLabel.TextColorProperty.PropertyName)
            {
                if (Control != null && Element != null)
                {
                    textView.SetTextColor(Element.TextColor.ToAndroid());
                }
            }
            else if (e.PropertyName == SelectableLabel.FontAttributesProperty.PropertyName
                        || e.PropertyName == SelectableLabel.FontSizeProperty.PropertyName)
            {
                if (Control != null && Element != null)
                {
                    switch (Element.FontAttributes)
                    {
                        case FontAttributes.None:
                            textView.SetTypeface(null, Android.Graphics.TypefaceStyle.Normal);
                            break;
                        case FontAttributes.Bold:
                            textView.SetTypeface(null, Android.Graphics.TypefaceStyle.Bold);
                            break;
                        case FontAttributes.Italic:
                            textView.SetTypeface(null, Android.Graphics.TypefaceStyle.Italic);
                            break;
                        default:
                            textView.SetTypeface(null, Android.Graphics.TypefaceStyle.Normal);
                            break;
                    }

                    textView.TextSize = (float)Element.FontSize;
                }
            }
        }


    }
}
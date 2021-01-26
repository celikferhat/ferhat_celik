package crc649a2e92dab23a600b;


public class UrlImageParser
	extends java.lang.Object
	implements
		mono.android.IGCUserPeer,
		android.text.Html.ImageGetter
{
/** @hide */
	public static final String __md_methods;
	static {
		__md_methods = 
			"n_getDrawable:(Ljava/lang/String;)Landroid/graphics/drawable/Drawable;:GetGetDrawable_Ljava_lang_String_Handler:Android.Text.Html/IImageGetterInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n" +
			"";
		mono.android.Runtime.register ("LabelHtml.Forms.Plugin.Droid.UrlImageParser, HtmlLabel.Forms.Plugin", UrlImageParser.class, __md_methods);
	}


	public UrlImageParser ()
	{
		super ();
		if (getClass () == UrlImageParser.class)
			mono.android.TypeManager.Activate ("LabelHtml.Forms.Plugin.Droid.UrlImageParser, HtmlLabel.Forms.Plugin", "", this, new java.lang.Object[] {  });
	}

	public UrlImageParser (android.widget.TextView p0)
	{
		super ();
		if (getClass () == UrlImageParser.class)
			mono.android.TypeManager.Activate ("LabelHtml.Forms.Plugin.Droid.UrlImageParser, HtmlLabel.Forms.Plugin", "Android.Widget.TextView, Mono.Android", this, new java.lang.Object[] { p0 });
	}


	public android.graphics.drawable.Drawable getDrawable (java.lang.String p0)
	{
		return n_getDrawable (p0);
	}

	private native android.graphics.drawable.Drawable n_getDrawable (java.lang.String p0);

	private java.util.ArrayList refList;
	public void monodroidAddReference (java.lang.Object obj)
	{
		if (refList == null)
			refList = new java.util.ArrayList ();
		refList.add (obj);
	}

	public void monodroidClearReferences ()
	{
		if (refList != null)
			refList.clear ();
	}
}

package md5e834a954f8ce4916ee22ff63c678460f;


public class UrlDrawable
	extends android.graphics.drawable.BitmapDrawable
	implements
		mono.android.IGCUserPeer
{
/** @hide */
	public static final String __md_methods;
	static {
		__md_methods = 
			"n_draw:(Landroid/graphics/Canvas;)V:GetDraw_Landroid_graphics_Canvas_Handler\n" +
			"";
		mono.android.Runtime.register ("LabelHtml.Forms.Plugin.Droid.UrlDrawable, HtmlLabel.Forms.Plugin", UrlDrawable.class, __md_methods);
	}


	public UrlDrawable ()
	{
		super ();
		if (getClass () == UrlDrawable.class)
			mono.android.TypeManager.Activate ("LabelHtml.Forms.Plugin.Droid.UrlDrawable, HtmlLabel.Forms.Plugin", "", this, new java.lang.Object[] {  });
	}


	public UrlDrawable (android.content.res.Resources p0, android.graphics.Bitmap p1)
	{
		super (p0, p1);
		if (getClass () == UrlDrawable.class)
			mono.android.TypeManager.Activate ("LabelHtml.Forms.Plugin.Droid.UrlDrawable, HtmlLabel.Forms.Plugin", "Android.Content.Res.Resources, Mono.Android:Android.Graphics.Bitmap, Mono.Android", this, new java.lang.Object[] { p0, p1 });
	}


	public void draw (android.graphics.Canvas p0)
	{
		n_draw (p0);
	}

	private native void n_draw (android.graphics.Canvas p0);

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

package crc649a2e92dab23a600b;


public class ImageGetterAsyncTask
	extends android.os.AsyncTask
	implements
		mono.android.IGCUserPeer
{
/** @hide */
	public static final String __md_methods;
	static {
		__md_methods = 
			"n_doInBackground:([Ljava/lang/Object;)Ljava/lang/Object;:GetDoInBackground_arrayLjava_lang_Object_Handler\n" +
			"n_onPostExecute:(Ljava/lang/Object;)V:GetOnPostExecute_Ljava_lang_Object_Handler\n" +
			"";
		mono.android.Runtime.register ("LabelHtml.Forms.Plugin.Droid.ImageGetterAsyncTask, HtmlLabel.Forms.Plugin", ImageGetterAsyncTask.class, __md_methods);
	}


	public ImageGetterAsyncTask ()
	{
		super ();
		if (getClass () == ImageGetterAsyncTask.class)
			mono.android.TypeManager.Activate ("LabelHtml.Forms.Plugin.Droid.ImageGetterAsyncTask, HtmlLabel.Forms.Plugin", "", this, new java.lang.Object[] {  });
	}

	public ImageGetterAsyncTask (crc649a2e92dab23a600b.UrlDrawable p0, android.widget.TextView p1)
	{
		super ();
		if (getClass () == ImageGetterAsyncTask.class)
			mono.android.TypeManager.Activate ("LabelHtml.Forms.Plugin.Droid.ImageGetterAsyncTask, HtmlLabel.Forms.Plugin", "LabelHtml.Forms.Plugin.Droid.UrlDrawable, HtmlLabel.Forms.Plugin:Android.Widget.TextView, Mono.Android", this, new java.lang.Object[] { p0, p1 });
	}


	public java.lang.Object doInBackground (java.lang.Object[] p0)
	{
		return n_doInBackground (p0);
	}

	private native java.lang.Object n_doInBackground (java.lang.Object[] p0);


	public void onPostExecute (java.lang.Object p0)
	{
		n_onPostExecute (p0);
	}

	private native void n_onPostExecute (java.lang.Object p0);

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

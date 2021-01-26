package md5058c3af7bab83a506e5c1a137de3b37d;


public class TransparentWebViewRenderer_MyMenuItemOnMenuItemClickListener
	extends java.lang.Object
	implements
		mono.android.IGCUserPeer,
		android.view.MenuItem.OnMenuItemClickListener
{
/** @hide */
	public static final String __md_methods;
	static {
		__md_methods = 
			"n_onMenuItemClick:(Landroid/view/MenuItem;)Z:GetOnMenuItemClick_Landroid_view_MenuItem_Handler:Android.Views.IMenuItemOnMenuItemClickListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n" +
			"";
		mono.android.Runtime.register ("SArticle.Droid.TransparentWebViewRenderer+MyMenuItemOnMenuItemClickListener, SArticle.Android", TransparentWebViewRenderer_MyMenuItemOnMenuItemClickListener.class, __md_methods);
	}


	public TransparentWebViewRenderer_MyMenuItemOnMenuItemClickListener ()
	{
		super ();
		if (getClass () == TransparentWebViewRenderer_MyMenuItemOnMenuItemClickListener.class)
			mono.android.TypeManager.Activate ("SArticle.Droid.TransparentWebViewRenderer+MyMenuItemOnMenuItemClickListener, SArticle.Android", "", this, new java.lang.Object[] {  });
	}

	public TransparentWebViewRenderer_MyMenuItemOnMenuItemClickListener (md5058c3af7bab83a506e5c1a137de3b37d.MainActivity p0, android.view.ActionMode p1)
	{
		super ();
		if (getClass () == TransparentWebViewRenderer_MyMenuItemOnMenuItemClickListener.class)
			mono.android.TypeManager.Activate ("SArticle.Droid.TransparentWebViewRenderer+MyMenuItemOnMenuItemClickListener, SArticle.Android", "SArticle.Droid.MainActivity, SArticle.Android:Android.Views.ActionMode, Mono.Android", this, new java.lang.Object[] { p0, p1 });
	}


	public boolean onMenuItemClick (android.view.MenuItem p0)
	{
		return n_onMenuItemClick (p0);
	}

	private native boolean n_onMenuItemClick (android.view.MenuItem p0);

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

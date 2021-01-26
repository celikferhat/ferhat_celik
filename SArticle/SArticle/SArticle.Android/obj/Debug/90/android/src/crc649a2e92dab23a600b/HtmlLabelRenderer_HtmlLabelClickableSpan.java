package crc649a2e92dab23a600b;


public class HtmlLabelRenderer_HtmlLabelClickableSpan
	extends android.text.style.ClickableSpan
	implements
		mono.android.IGCUserPeer
{
/** @hide */
	public static final String __md_methods;
	static {
		__md_methods = 
			"n_updateDrawState:(Landroid/text/TextPaint;)V:GetUpdateDrawState_Landroid_text_TextPaint_Handler\n" +
			"n_onClick:(Landroid/view/View;)V:GetOnClick_Landroid_view_View_Handler\n" +
			"";
		mono.android.Runtime.register ("LabelHtml.Forms.Plugin.Droid.HtmlLabelRenderer+HtmlLabelClickableSpan, HtmlLabel.Forms.Plugin", HtmlLabelRenderer_HtmlLabelClickableSpan.class, __md_methods);
	}


	public HtmlLabelRenderer_HtmlLabelClickableSpan ()
	{
		super ();
		if (getClass () == HtmlLabelRenderer_HtmlLabelClickableSpan.class)
			mono.android.TypeManager.Activate ("LabelHtml.Forms.Plugin.Droid.HtmlLabelRenderer+HtmlLabelClickableSpan, HtmlLabel.Forms.Plugin", "", this, new java.lang.Object[] {  });
	}


	public void updateDrawState (android.text.TextPaint p0)
	{
		n_updateDrawState (p0);
	}

	private native void n_updateDrawState (android.text.TextPaint p0);


	public void onClick (android.view.View p0)
	{
		n_onClick (p0);
	}

	private native void n_onClick (android.view.View p0);

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

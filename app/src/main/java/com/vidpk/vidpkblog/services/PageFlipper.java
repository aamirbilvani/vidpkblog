package com.vidpk.vidpkblog.services;

import android.content.Context;
import android.content.Intent;

import com.vidpk.vidpkblog.model.VidpkObject;

public class PageFlipper {
	
	private VidpkObject parent;
	private Context context;
	private Class<?> nextClass;

	public PageFlipper(Context context, Class<?> nextClass, VidpkObject parent)
	{
		this.context = context;
		this.nextClass = nextClass;
		this.parent = parent;
	}

	public void flipPage()
	{
		Intent intent = new Intent(context, nextClass);
		intent.putExtra("parent", parent);
		context.startActivity(intent);
	}
}

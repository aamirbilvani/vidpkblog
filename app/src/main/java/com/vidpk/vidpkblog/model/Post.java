package com.vidpk.vidpkblog.model;

import com.vidpk.vidpkblog.util.Globals;

public class Post extends VidpkObject{

	private static final long serialVersionUID = 1L;
	public String title;
	public String url;
	public String image;

    public Post(String title, String url, String image)
	{
		this.title = title;
		this.url = url;
		this.image = image;
	}

    public String retrieveThumbnail()
	{
		return this.image;
	}
}

package com.vidpk.vidpkblog;


import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import com.vidpk.vidpkblog.model.Post;



public class PostViewer extends Activity{

	private Post post;
	private WebView browser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post_viewer);

		browser = (WebView) findViewById(R.id.webView);

		post = (Post) getIntent().getSerializableExtra("parent");

		browser.loadUrl(post.url);
	}
}
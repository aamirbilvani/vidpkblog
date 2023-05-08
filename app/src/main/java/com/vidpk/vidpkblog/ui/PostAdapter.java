package com.vidpk.vidpkblog.ui;

import java.util.ArrayList;

import com.vidpk.vidpkblog.R;
import com.vidpk.vidpkblog.io.ImageCacher;
import com.vidpk.vidpkblog.model.Post;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PostAdapter extends ArrayAdapter<Post> {
	private final Context context;
	private final ArrayList<Post> posts;
	private ImageCacher image_cacher;

	public PostAdapter(Context context, int textViewResourceId,
					   ArrayList<Post> posts) {
		super(context, R.layout.post_row, posts);
		this.context = context;
		this.posts = posts;
		image_cacher = new ImageCacher(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View rowView = inflater.inflate(R.layout.post_row, parent, false);

		Post thisPost = posts.get(position);

		Typeface tf1 = Typeface.createFromAsset(this.context.getAssets(),
	            "fonts/Raleway-SemiBold.otf");
		Typeface tf2 = Typeface.createFromAsset(this.context.getAssets(),
	            "fonts/Raleway-Regular.otf");

		TextView showName = (TextView) rowView.findViewById(R.id.show_name);
		showName.setText(thisPost.title);
		showName.setTypeface(tf1);

		TextView showLastUpdated = (TextView) rowView
				.findViewById(R.id.show_last_updated);
		showLastUpdated.setText(thisPost.url);
		showLastUpdated.setTypeface(tf2);
		


		ImageView imageView = (ImageView) rowView.findViewById(R.id.show_thumb);
		String url = thisPost.retrieveThumbnail();

    	image_cacher.setImage(url, imageView);

		return rowView;
	}
}
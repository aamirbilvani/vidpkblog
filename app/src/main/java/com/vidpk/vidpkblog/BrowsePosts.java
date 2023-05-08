package com.vidpk.vidpkblog;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.SoundEffectConstants;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

import com.vidpk.vidpkblog.model.Post;
import com.vidpk.vidpkblog.model.VidpkObject;
import com.vidpk.vidpkblog.services.PageFlipper;
import com.vidpk.vidpkblog.services.ServerRequestHandler;
import com.vidpk.vidpkblog.ui.PostAdapter;
import com.vidpk.vidpkblog.util.Globals;

public class BrowsePosts extends ListActivity implements VidpkListActivity {

	private ArrayList<Post> posts = new ArrayList<Post>();
	private PostAdapter postAdapter;
	private int currentFirstVisibleItem;

	private ServerRequestHandler srh = new ServerRequestHandler(
			BrowsePosts.this, this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_browse);

		postAdapter = new PostAdapter(this,
				android.R.layout.simple_list_item_1, posts);

		setListAdapter(postAdapter);
		ListView thisList = (ListView) findViewById(android.R.id.list);
		thisList.setOnScrollListener(scrollListener);
		srh.performRequest(0);
	}

	private OnScrollListener scrollListener = new OnScrollListener() {

		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
			if ((totalItemCount - Globals
					.getTotalInAView(findViewById(android.R.id.list))) == firstVisibleItem
					&& currentFirstVisibleItem != firstVisibleItem) {
				srh.performRequest(
						(totalItemCount + Globals.PER_PAGE - 1) / Globals.PER_PAGE); // rounding up the division so when we
										// reach the end of the list the last
										// batch is not returned again
			}
			currentFirstVisibleItem = firstVisibleItem;
		}

		public void onScrollStateChanged(AbsListView view, int scrollState) {
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.browse_by_shows, menu);
		return true;
	}

	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		v.playSoundEffect(SoundEffectConstants.CLICK);

		Post post = postAdapter.getItem(position);
		PageFlipper pf = new PageFlipper(this, PostViewer.class, post);
		pf.flipPage();
	}


	public void dataChanged(ArrayList<VidpkObject> newResults) {
		Iterator<VidpkObject> i = newResults.iterator();
		while (i.hasNext()) {
			Post thisSh = (Post) i.next();
			posts.add(thisSh);
		}
		postAdapter.notifyDataSetChanged();
	}
}
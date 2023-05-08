package com.vidpk.vidpkblog;

import java.util.ArrayList;

import android.app.Activity;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.widget.ExpandableListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.vidpk.vidpkblog.model.HomepageButton;
import com.vidpk.vidpkblog.ui.HomepageExpandableListAdapter;
import com.vidpk.vidpkblog.util.Globals;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		AdView mAdView = (AdView) findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		mAdView.loadAd(adRequest);
		
		Typeface tf = Typeface.createFromAsset(getAssets(),
				"fonts/Raleway-SemiBold.otf");

		if (!Globals.isInternetConnectionAvailable(this)) {
			Globals.showExitAlertDialog(MainActivity.this, MainActivity.this,
					getString(R.string.no_internet_title),
					getString(R.string.no_internet_text));
		}

		ExpandableListView expandbleLis = (ExpandableListView) findViewById(R.id.mainExpListView);
   		expandbleLis.setGroupIndicator(null);
		expandbleLis.setClickable(true);
		setGroupData();
		setChildGroupData();
		HomepageExpandableListAdapter mNewAdapter = new HomepageExpandableListAdapter(
   				groupItem, childItem, MainActivity.this);
   		mNewAdapter.setInflater(
   				(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE),
   				MainActivity.this);
   		expandbleLis.setAdapter(mNewAdapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	public void setGroupData() {
		groupItem.add(getString(R.string.posts));
	}

	ArrayList<String> groupItem = new ArrayList<String>();
	ArrayList<Object> childItem = new ArrayList<Object>();

	public void setChildGroupData() {
		/**
		 * Add Data For TV
		 */
		ArrayList<HomepageButton> child = new ArrayList<HomepageButton>();
		child.add(new HomepageButton(getString(R.string.btn_latest_posts),
				Globals.HOMEPAGE_BTN_LatestPosts));
		childItem.add(child);
	}

}
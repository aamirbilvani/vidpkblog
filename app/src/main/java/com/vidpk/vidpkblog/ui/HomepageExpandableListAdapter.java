package com.vidpk.vidpkblog.ui;

import java.util.ArrayList;

import com.vidpk.vidpkblog.BrowsePosts;
import com.vidpk.vidpkblog.R;
import com.vidpk.vidpkblog.model.HomepageButton;
import com.vidpk.vidpkblog.services.PageFlipper;
import com.vidpk.vidpkblog.util.Globals;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

public class HomepageExpandableListAdapter extends BaseExpandableListAdapter {

	public ArrayList<String> groupItem;
	public ArrayList<HomepageButton> tempChild;
	public ArrayList<Object> Childtem = new ArrayList<Object>();
	public LayoutInflater minflater;
	public Activity activity;
	private Typeface tf;
	private PageFlipper pageflip;
	private Context context;


	public HomepageExpandableListAdapter(ArrayList<String> grList,
			ArrayList<Object> childItem, Context c) {
		groupItem = grList;
		this.Childtem = childItem;
		this.context = c;
	}

	public void setInflater(LayoutInflater mInflater, Activity act) {
		this.minflater = mInflater;
		activity = act;
		tf = Typeface.createFromAsset(act.getAssets(),
				"fonts/Raleway-SemiBold.otf");
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return null;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		tempChild = (ArrayList<HomepageButton>) Childtem.get(groupPosition);
		if (convertView == null) {
			convertView = minflater.inflate(R.layout.childrow, null);
		}

		TextView text = (TextView) convertView.findViewById(R.id.textView1);
		text.setTypeface(tf);
		HomepageButton hb = (HomepageButton)tempChild.get(childPosition);
		text.setText(hb.getText());
		View clickerView = convertView.findViewById(R.id.childLayout); //The inner LinearLayout
		clickerView.setTag(hb);
		
		clickerView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				v.playSoundEffect(SoundEffectConstants.CLICK);

				if (((HomepageButton)v.getTag()).getId() == Globals.HOMEPAGE_BTN_LatestPosts) {
					pageflip = new PageFlipper(context, BrowsePosts.class, null);
				}

				pageflip.flipPage();
			}
		});
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return ((ArrayList<String>) Childtem.get(groupPosition)).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return null;
	}

	@Override
	public int getGroupCount() {
		return groupItem.size();
	}

	@Override
	public void onGroupCollapsed(int groupPosition) {
		super.onGroupCollapsed(groupPosition);
	}

	@Override
	public void onGroupExpanded(int groupPosition) {
		super.onGroupExpanded(groupPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return 0;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = minflater.inflate(R.layout.grouprow, null);
		}
		((CheckedTextView) convertView).setText(groupItem.get(groupPosition));
		((CheckedTextView) convertView).setChecked(isExpanded);
		((CheckedTextView) convertView).setTypeface(tf);
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}

}
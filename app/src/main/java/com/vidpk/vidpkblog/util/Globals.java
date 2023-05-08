package com.vidpk.vidpkblog.util;

import com.vidpk.vidpkblog.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.Toast;

public class Globals {
	public static final int PER_PAGE = 10;

	public static final int HOMEPAGE_BTN_LatestPosts = 1;
	
	public static boolean higherPriorityRequestAvailable = false;

	public static int getTotalInAView(View l) {
		int height_in_px = l.getHeight();
		float density = l.getResources().getDisplayMetrics().density;
		float height_in_dp = height_in_px / density;
		float show_row_height = l.getResources().getDimension(
				R.dimen.show_row_height)
				/ density;
		int total_in_a_view = (int) ((height_in_dp + show_row_height) / show_row_height); // Rounding
																							// up
		return total_in_a_view;
	}

	public static void showToast(Context c, CharSequence message) {
		Toast.makeText(c, message, Toast.LENGTH_LONG).show();
	}

	public static void showExitAlertDialog(final Activity activity,
			Context context, String title, String message) {
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();

		alertDialog.setTitle(title);
		alertDialog.setMessage(message);
		alertDialog.setIcon(R.drawable.error);

		// Setting OK Button
		alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				activity.finish();
			}
		});

		// Showing Alert Message
		alertDialog.show();
	}

	public static boolean isInternetConnectionAvailable(Context c) {
		ConnectivityManager cm = (ConnectivityManager) c
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cm != null) {
			NetworkInfo[] info = cm.getAllNetworkInfo();
			if (info != null)
				for (int i = 0; i < info.length; i++)
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
		}
		return false;
	}
	
	public static String cleanString(String s)
	{
		s = s.replace("&amp;", "&");
		s = s.replace("&quot;", "\"");
		s = s.replace("&#039;", "'");
		return s;
	}
}

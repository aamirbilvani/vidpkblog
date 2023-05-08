package com.vidpk.vidpkblog.services;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.AsyncTask;
import android.view.View;

import com.vidpk.vidpkblog.R;
import com.vidpk.vidpkblog.VidpkListActivity;
import com.vidpk.vidpkblog.model.VidpkObject;
import com.vidpk.vidpkblog.util.Globals;

public class ServerRequestHandler {
	private GenericSeeker<VidpkObject> seeker;
	private ProgressDialog progressDialog;
	private Context context;
	private Activity activity;
	private PerformRequest task;

	public ServerRequestHandler(Context context, Activity activity) {
		this.context = context;
		this.activity = activity;
	}

	public void performRequest(int subsequent) {
		Globals.higherPriorityRequestAvailable = true;
		seeker = new PostSeeker();

		if(subsequent==0)
			progressDialog = ProgressDialog.show(context, context.getString(R.string.please_wait),
					context.getString(R.string.retrieving), true, false);
		else
			progressDialog = ProgressDialog.show(context, context.getString(R.string.please_wait),
					context.getString(R.string.retrieving_next_ten), true, false);

		task = new PerformRequest();
		if (subsequent != 0)
			task.setSubsequent(subsequent);
		//task.execute();
		task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		progressDialog
				.setOnCancelListener(new CancelTaskOnCancelListener(task));
	}

	private class PerformRequest extends
			AsyncTask<String, Void, List<VidpkObject>> {

		private int subsequent = 0;

		public void setSubsequent(int s) {
			subsequent = s;
		}

		@Override
		protected List<VidpkObject> doInBackground(String... params) {
				return seeker.find(context, subsequent);
		}

		@Override
		protected void onPostExecute(final List<VidpkObject> result) {
			activity.runOnUiThread(new Runnable() {

				@Override
				public void run() {
					if (progressDialog != null && progressDialog.isShowing()) {
						progressDialog.dismiss();
						progressDialog = null;
					}
					if (result != null && !result.isEmpty()) {
						ArrayList<VidpkObject> resultAL = (ArrayList<VidpkObject>) result;
						VidpkListActivity a = (VidpkListActivity) activity;
						a.dataChanged(resultAL);
						Globals.higherPriorityRequestAvailable = false;
					}
					else if (result.isEmpty())
					{
						activity.findViewById(R.id.no_data).setVisibility(View.VISIBLE); //visible
						//Dont show this. Could be no results returned
						//Globals.showToast(context, context.getString(R.string.no_internet_text_short));
					}
				}
			});
		}
	}

	private class CancelTaskOnCancelListener implements OnCancelListener {
		private AsyncTask<?, ?, ?> task;

		public CancelTaskOnCancelListener(AsyncTask<?, ?, ?> task) {
			this.task = task;
		}

		@Override
		public void onCancel(DialogInterface dialog) {
			if (task != null) {
				task.cancel(true);
				task = null;
			}
		}
	}
}
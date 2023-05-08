package com.vidpk.vidpkblog;

import java.util.ArrayList;

import com.vidpk.vidpkblog.model.VidpkObject;

public interface VidpkListActivity {

	public void dataChanged(ArrayList<VidpkObject> newResults);
}

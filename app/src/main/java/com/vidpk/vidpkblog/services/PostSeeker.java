package com.vidpk.vidpkblog.services;

import java.util.ArrayList;

import android.content.Context;

import com.vidpk.vidpkblog.model.VidpkObject;

public class PostSeeker extends GenericSeeker<VidpkObject> {

	private int page = 1;

	public ArrayList<VidpkObject> find(Context c){
		return find(c, 0);
	}

	public ArrayList<VidpkObject> find(Context c, int subsequent){
		return retrieveShowList(c, subsequent);
	}

	private ArrayList<VidpkObject> retrieveShowList(Context c, int subsequent){
		page = subsequent + 1;
		String url = constructSearchUrl();
		String response = httpRetriever.retrieve(c, url);
		//Log.d(getClass().getSimpleName(), response); //Will show exception if string is null
		return jsonParser.parsePostResponse(response);
	}

	public String retrieveUniqueURL() {
		return "/blog";
	}

}

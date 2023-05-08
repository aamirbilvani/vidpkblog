package com.vidpk.vidpkblog.services;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.vidpk.vidpkblog.model.Post;
import com.vidpk.vidpkblog.model.VidpkObject;

public class MyJsonParser {

	public ArrayList<VidpkObject> parsePostResponse(String json) {
		ArrayList<VidpkObject> parsedList = new ArrayList<VidpkObject>();
		if(json==null)
			return parsedList;
		try {
			JSONObject o = new JSONObject(json);
			JSONArray jArray = o.getJSONArray("posts");
			for (int i = 0; i < jArray.length(); i++) {
				JSONObject entry = jArray.getJSONObject(i);
				Post s = new Post((String) entry.get("title"),
						(String) entry.get("URL"), (String) entry.get("featured_image"));
				parsedList.add(s);
			}
		} catch (JSONException e) {
			System.out.println(e.toString());
			System.out.println(e.getStackTrace());
		}
		return parsedList;
	}
}
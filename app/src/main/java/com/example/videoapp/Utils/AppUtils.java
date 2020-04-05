package com.example.videoapp.Utils;

import android.content.Context;

import com.example.videoapp.model.VideoContent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class AppUtils {

    private static String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("videosdata.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static ArrayList<VideoContent> parseAndGetVideoContent(Context context) {
        ArrayList<VideoContent> videoContentArrayList = new ArrayList<>();
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset(context));
            JSONArray categoriesArray = obj.getJSONArray("categories");
            if (categoriesArray.length() > 0) {
                JSONObject insideObject = categoriesArray.getJSONObject(0);
                JSONArray videosArray = insideObject.getJSONArray("videos");
                if (videosArray.length() > 0) {
                    for (int i = 0; i < videosArray.length(); i++) {
                        JSONObject contentObj = videosArray.getJSONObject(i);
                        VideoContent videoContent = new VideoContent();
                        videoContent.setVideoPath(contentObj.getString("sources"));
                        videoContent.setVideoDescription(contentObj.getString("description"));
                        videoContent.setVideoTitle(contentObj.getString("title"));
                        videoContent.setThumbnail(contentObj.getString("thumb"));
                        videoContentArrayList.add(videoContent);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return videoContentArrayList;
    }
}

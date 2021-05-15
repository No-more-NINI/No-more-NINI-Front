package com.app.nomorenini;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class Location extends AsyncTask<Pair<Activity,Integer>,Integer,Void> {

	LocationTracker locationTrack;
	public void requestGET(String url, final Activity act){
		url="http://10.0.2.2:3000"+url;
		RequestQueue queue = Volley.newRequestQueue(act);
		//String url ="http://10.0.2.2:3000/offers?id=1";
		//String json="{'id':"+usr.email+"}";
		//String json="{\'id\':1}";
		StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {

					}
				}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Log.println(Log.ERROR,"WTF",error.toString());
			}
		});
		queue.add(stringRequest);
	}

	/*public void getLocationReq(
			final Activity act,
			final RepositoryCallback<Pair<Double,Double>> callback
	) {
		executor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					Pair<Double,Double> result = get_location(act);

					callback.onComplete(result);
				} catch (Exception e) {
					//callback.onComplete("result");
				}
			}
		});
	}*/


	Pair<Double, Double> get_location(Activity act){
		locationTrack = new LocationTracker(act);


		if (locationTrack.canGetLocation()) {
			double longitude = locationTrack.getLongitude();
			double latitude = locationTrack.getLatitude();
			return new Pair<Double, Double>(latitude,longitude);
			//Toast.makeText(getApplicationContext(), "Longitude:" + Double.toString(longitude) + "\nLatitude:" + Double.toString(latitude), Toast.LENGTH_SHORT).show();
		} else {
			locationTrack.showSettingsAlert();
		}
		return null;
	}

	@Override
	protected Void doInBackground(Pair<Activity,Integer>... activities) {
		while(true) {
			Pair l=get_location(activities[0].first);
			requestGET("/location?id="+activities[0].second+"&latitude="+l.first+"&longitude="+l.second,activities[0].first);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//return null;
	}
}

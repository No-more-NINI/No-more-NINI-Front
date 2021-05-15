package com.app.nomorenini;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.app.nomorenini.model.Company;
import com.app.nomorenini.model.Offer;
import com.app.nomorenini.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Connector {

	private static Connector instance;
	private static User usr;
	RequestQueue queue;
	public Connector(User usr){
		this.usr=usr;
	}
	/*private class GetUrlContentTask extends AsyncTask<String, Integer, String> {
		protected String doInBackground(String... urls) {
			URL url = null;
			try {
				url = new URL(urls[0]);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			HttpURLConnection connection = null;
			try {
				connection = (HttpURLConnection) url.openConnection();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				connection.setRequestMethod("GET");
			} catch (ProtocolException e) {
				e.printStackTrace();
			}
			connection.setDoOutput(true);
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			try {
				connection.connect();
			} catch (IOException e) {
				e.printStackTrace();
			}
			BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String content = "", line = null;
			while (true) {
				try {
					if (!((line = rd.readLine()) != null)) break;
				} catch (IOException e) {
					e.printStackTrace();
				}
				content += line + "\n";
			}
			return content;
		}

		protected void onProgressUpdate(Integer... progress) {
		}

		protected void onPostExecute(String result) {
			// this is executed on the main thread after the process is over
			// update your UI here
			displayMessage(result);
		}
	}*/
	void request(String url, String json, final MyActivity act) throws JSONException {
		JSONObject r=new JSONObject(json);
		//JsonObjectRequest
		Log.println(10,"ALERTA","no entenc res");
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
				(Request.Method.GET, url, r, new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						//textView.setText("Response: " + response.toString());

						/*if(act!=null)
							act.respond(response);*/
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO: Handle error
						Log.println(Log.ERROR,"ALERTA","no entenc res");
					}
				});

	}
	void get_offers(Company comp, Activity act) throws JSONException {
		String url="";
		String json="{\'email\':\'id\':"+comp.id+'}';
	}
	void geolocation(float latitude, float longitude){
		String url="";
		String json="{'latitude': "+latitude+", 'longitude':"+longitude+"}";
	}

	void near_by(){
		String url="";
		String json="{'type':"+User.getType(usr)+"}";
	}

	void accept_offer(Offer offert){
		String url="";
		String json="{'id':"+offert.id+", 'userId':"+this+"}";
	}
	void log_in(MyActivity act) throws JSONException {

		String url="http://localhost:3000/offers";
		//String json="{'id':"+usr.email+"}";
		String json="{\'id\':1}";
		Log.println(10,"ALERTA","bona tarda nopi");
		request(url,json,act);

	}
	public static Connector getInstance(User usr) {
		if (instance == null) {
			instance = new Connector(usr);
		}
		return instance;
	}

}

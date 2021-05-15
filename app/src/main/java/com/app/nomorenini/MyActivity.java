package com.app.nomorenini;


import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

abstract class MyActivity extends AppCompatActivity {

	public abstract void respond(String data) ;
	public void requestGET(String url, final MyActivity act){
		url="http://10.0.2.2:3000"+url;
		RequestQueue queue = Volley.newRequestQueue(this);
		//String url ="http://10.0.2.2:3000/offers?id=1";
		//String json="{'id':"+usr.email+"}";
		//String json="{\'id\':1}";
		StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						// Display the first 500 characters of the response string.
						//textView.setText("Response is: "+ response.substring(0,500));
						//Toast toast1 = Toast.makeText(getApplicationContext(), "god job my man", Toast.LENGTH_SHORT);
						//toast1.show();
						act.respond(response);
					}
				}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Log.println(Log.ERROR,"WTF",error.toString());
				Toast toast1 = Toast.makeText(getApplicationContext(), "Toast por defecto", Toast.LENGTH_SHORT);
				toast1.show();
			}
		});
		queue.add(stringRequest);
	}
	public void requestPUT(String url, final String data, final MyActivity act){
		url="http://10.0.2.2:3000"+url;
		RequestQueue queue = Volley.newRequestQueue(this);
		//String url ="http://10.0.2.2:3000/offers?id=1";
		//String json="{'id':"+usr.email+"}";
		String json="{\'id\':1}";
		StringRequest stringRequest = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				// Display the first 500 characters of the response string.
				//textView.setText("Response is: "+ response.substring(0,500));
				//Toast toast1 = Toast.makeText(getApplicationContext(), "god job my man", Toast.LENGTH_SHORT);
				//toast1.show();
				act.respond(response);
			}}, new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					Log.println(Log.ERROR,"WTF",error.toString());
					Toast toast1 = Toast.makeText(getApplicationContext(), "Toast por defecto", Toast.LENGTH_SHORT);
					toast1.show();
				}
		}){
			@Override
			public byte[] getBody() throws AuthFailureError {
				return data.toString().getBytes();
			}

			@Override
			public String getBodyContentType() {
				return "application/json";
			}
		};
		queue.add(stringRequest);
	}
}

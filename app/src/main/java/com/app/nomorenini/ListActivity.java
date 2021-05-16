package com.app.nomorenini;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.nomorenini.model.Employee;
import com.app.nomorenini.model.User;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
	User usr;
	MyAdapter adapter;
	public void respond(String data) {
		//do list updates
		List<User> usrs=new ArrayList<>();
		Log.println(Log.ERROR,"OJO AKI",data);
		data=data.substring(1,data.length()-2);
		String[] it=data.split("\\},");
		for(String s:it){
			usrs.add(new User(s+'}'));
		}
		adapter.addAll(usrs);
	}

	public void requestGET(String url, final ListActivity act){
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
	private class MyAdapter extends ArrayAdapter<User> {
		int type;
		public MyAdapter(Context context, ArrayList<User> users) {
			super(context, 0, users);
			if(usr instanceof Employee)
				this.type=0;
			else
				this.type=1;
		}



		@Override

		public View getView(int position, View convertView, ViewGroup parent) {
			User user = getItem(position);
			if (convertView == null) {

				convertView = LayoutInflater.from(getContext()).inflate(R.layout.user_item, parent, false);

			}
			TextView tvName = (TextView) convertView.findViewById(R.id.name);
			TextView tvDesc = (TextView) convertView.findViewById(R.id.description);
			//TextView tvSalary = (TextView) convertView.findViewById(R.id.dalary);
			/*if(type==0){
				tvDesc.setVisibility(View.INVISIBLE);

				//tvSalary.setText();
			}*/
			tvName.setText(user.name);
			return convertView;
		}
	}
	public void get_nearby(){

	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		usr=(User) getIntent().getSerializableExtra("user");
		setContentView(R.layout.activity_list);
		ArrayList<User> arrayOfUsers = new ArrayList<User>();

		adapter = new MyAdapter(this, arrayOfUsers);
		ListView listView = (ListView) findViewById(R.id.listview);
		listView.setAdapter(adapter);
		requestGET("/nearby?id="+usr.id+"&type="+usr.type,this);
	}
}

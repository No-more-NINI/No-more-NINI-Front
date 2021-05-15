package com.app.nomorenini;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.nomorenini.model.User;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends MyActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		/*WebView myWebView = new WebView(this.getBaseContext());
		myWebView.getSettings().setDomStorageEnabled(true);
		myWebView.getSettings().setJavaScriptEnabled(true);
		myWebView.getSettings().setSupportZoom(false);
		myWebView.getSettings().setBuiltInZoomControls(false);
		myWebView.getSettings().setLoadWithOverviewMode(true);
		myWebView.getSettings().setUseWideViewPort(true);
		myWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		//myWebView.setWebViewClient(new ourViewClient());
		//myWebView.Settings.AllowContentAccess = true;

		myWebView.loadUrl("https://nw843l9pdoz.typeform.com/to/EoxyAnct");
		setContentView(myWebView);
		myWebView.addJavascriptInterface(new Object()
		{
			@JavascriptInterface
			public void onUrlChange(String url)
			{
				Log.d("LOGIN::", "Clicked");
				Toast.makeText(getBaseContext(), "Login clicked", Toast.LENGTH_LONG).show();
			}
		}, "Submit");*/
		final MyActivity act=this;
		Button clickButton = (Button) findViewById(R.id.login);
		clickButton.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				String usr=((EditText)findViewById(R.id.user)).getText().toString();
				String pass=((EditText)findViewById(R.id.pass)).getText().toString();
				try {
					//Log.println(10,"ALERTA","no entenc res de res");
					//Connector.getInstance(new User(usr)).log_in(act);
					requestGET("/login?email="+usr+"&pass="+pass,act);
					//init();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void respond(String data) {
		//Log.println(10,"SAJHBDFJSDHFGHJSGVBDFHGSDFHGHBS","FSDFHKJBSDFHHSHDIFHBSJIKDFH");
		User u=null;
		try {
			data=data.substring(1, data.length() - 1);
			u=new User(new JSONObject(data));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		Toast toast1 =
				Toast.makeText(getApplicationContext(), "hello there", Toast.LENGTH_SHORT);
		toast1.show();
		Log.println(Log.ERROR,"LOOOOOOK",data);

		Intent k = new Intent(this, ListActivity.class);
		startActivity(k);
		new Location().execute(new Pair<Activity, Integer>(this,u.id));//doInBackground(new Pair(this,u.id));
	}
}

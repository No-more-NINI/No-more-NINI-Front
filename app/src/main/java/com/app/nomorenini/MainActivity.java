package com.app.nomorenini;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity {

	private ArrayList<String> permissionsToRequest;
	private ArrayList permissionsRejected = new ArrayList();
	private ArrayList permissions = new ArrayList();

	private final static int ALL_PERMISSIONS_RESULT = 101;
	LocationTracker locationTrack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//https://nw843l9pdoz.typeform.com/to/EoxyAnct
		/*WebView myWebView = new WebView(this);
		setContentView(myWebView);
		if (Build.VERSION.SDK_INT >= 19) {
			myWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		}
		myWebView.loadUrl("https://nw843l9pdoz.typeform.com/to/EoxyAnct");*/
		permissions.add(ACCESS_FINE_LOCATION);
		permissions.add(ACCESS_COARSE_LOCATION);

		permissionsToRequest = findUnAskedPermissions(permissions);
		//get the permissions we have asked for before but are not granted..
		//we will store this in a global list to access later.


		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {


			if (permissionsToRequest.size() > 0)
				requestPermissions((String[]) permissionsToRequest.toArray(new String[permissionsToRequest.size()]), ALL_PERMISSIONS_RESULT);
		}


		Button btn = (Button) findViewById(R.id.btn);


		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				locationTrack = new LocationTracker(MainActivity.this);


				if (locationTrack.canGetLocation()) {


					double longitude = locationTrack.getLongitude();
					double latitude = locationTrack.getLatitude();

					Toast.makeText(getApplicationContext(), "Longitude:" + Double.toString(longitude) + "\nLatitude:" + Double.toString(latitude), Toast.LENGTH_SHORT).show();
				} else {

					locationTrack.showSettingsAlert();
				}

			}
		});
	}
	private ArrayList findUnAskedPermissions(ArrayList<String> wanted) {
		ArrayList result = new ArrayList();

		for (String perm : wanted) {
			if (!hasPermission(perm)) {
				result.add(perm);
			}
		}

		return result;
	}

	private boolean hasPermission(String permission) {
		if (canMakeSmores()) {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
				return (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
			}
		}
		return true;
	}

	private boolean canMakeSmores() {
		return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
	}


	@TargetApi(Build.VERSION_CODES.M)
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

		switch (requestCode) {

			case ALL_PERMISSIONS_RESULT:
				for (String perms : permissionsToRequest) {
					if (!hasPermission(perms)) {
						permissionsRejected.add(perms);
					}
				}

				if (permissionsRejected.size() > 0) {


					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
						if (shouldShowRequestPermissionRationale((String) permissionsRejected.get(0))) {
							showMessageOKCancel("These permissions are mandatory for the application. Please allow access.",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(DialogInterface dialog, int which) {
											if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
												requestPermissions((String[]) permissionsRejected.toArray(new String[permissionsRejected.size()]), ALL_PERMISSIONS_RESULT);
											}
										}
									});
							return;
						}
					}

				}

				break;
		}

	}

	private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
		new AlertDialog.Builder(MainActivity.this)
				.setMessage(message)
				.setPositiveButton("OK", okListener)
				.setNegativeButton("Cancel", null)
				.create()
				.show();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		locationTrack.stopListener();
	}
}


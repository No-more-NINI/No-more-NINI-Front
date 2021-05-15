package com.app.nomorenini;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Requester {
	public void get(){
		// Create URL
		URL githubEndpoint = new URL("https://api.github.com/");

// Create connection
		HttpsURLConnection myConnection = (HttpsURLConnection) githubEndpoint.openConnection();
		myConnection.setRequestProperty("User-Agent", "my-rest-app-v0.1");
		myConnection.setRequestProperty("Accept", "application/vnd.github.v3+json");
		myConnection.setRequestProperty("Contact-Me", "hathibelagal@example.com");
		if (myConnection.getResponseCode() == 200) {
			// Success
			// Further processing here
		} else {
			// Error handling code goes here
		}

		InputStream responseBody = myConnection.getInputStream();
		InputStreamReader responseBodyReader =
				new InputStreamReader(responseBody, "UTF-8");

		JsonReader jsonReader = new JsonReader(responseBodyReader);
		jsonReader.beginObject(); // Start processing the JSON object
		while (jsonReader.hasNext()) { // Loop through all keys
			String key = jsonReader.nextName(); // Fetch the next key
			if (key.equals("organization_url")) { // Check if desired key
				// Fetch the value as a String
				String value = jsonReader.nextString();

				// Do something with the value
				// ...

				break; // Break out of the loop
			} else {
				jsonReader.skipValue(); // Skip values of other keys
			}
		}
	}
}

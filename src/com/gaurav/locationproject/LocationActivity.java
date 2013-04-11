package com.gaurav.locationproject;

import parsing.ParseXML;
import android.location.Address;
import android.location.Location;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.location.Geocoder;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.SAXException;
public class LocationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        
        
        
        Button button = (Button) findViewById(R.id.button1); 
        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				  GPSTracker gps = new GPSTracker(LocationActivity.this);
			        if(gps.canGetLocation() == false){
			        	Log.d("GPSLogFromActivity","False");
			        	gps.showSettingsAlert();
			        }
			        else{
			        	//Log.d("Location from Activity: ",gps.location.toString());
			        	double latitude = gps.getLatitude();
			        	System.out.println("Latitude: "+latitude);
			        	double longitude = gps.getLongitude();
			        	System.out.println("Longitude: "+longitude);
			        	
			        	Log.d("GPSLogFromActivity","True");
			        	Geocoder geo = 	new Geocoder(LocationActivity.this);
			        	List<Address> address = null;
			        	String distanceToLocation=null;
			        	String addressStreet=null;
						try {
							Log.d("Inside try catch","true");
							address = geo.getFromLocation(latitude, longitude, 1);
							if(address.size() == 1){
								Log.d("Address Size",""+address.size());
								addressStreet = address.get(0).getAddressLine(0);
								
							}else{
								Log.d("Address Size",""+address.size());
								addressStreet = null;
							}
							Log.d("address street",addressStreet);
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						String locationURL="http://maps.googleapis.com/maps/api/distancematrix/xml?origins="+latitude+","+longitude+"&destinations=Pimpri+IN&mode=road&language=en-EN&sensor=false";
						ParseXML parser = new ParseXML();
						try {
							distanceToLocation = parser.parseXML(locationURL);
						} catch (SAXException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			        	Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude + "\nArea: " + addressStreet + "\nDistance: " + distanceToLocation, Toast.LENGTH_LONG).show();
			        }
				
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.location, menu);
        return true;
    }
    
}

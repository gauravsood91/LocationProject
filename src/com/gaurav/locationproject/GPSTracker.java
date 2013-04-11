package com.gaurav.locationproject;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import java.lang.*;

public class GPSTracker extends Service implements LocationListener {
	
	private final Context mcontext;
	boolean isGPSEnabled = false;
	boolean canGetLocation = false;
	double latitude,longitude;
	
	Location location;
	
	private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
	private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1;
	
	protected LocationManager locationManager;
	
	public GPSTracker(Context context){
		this.mcontext = context;
		//this.location = getLocation();
		
		try{
			locationManager = (LocationManager)mcontext.getSystemService(LOCATION_SERVICE);
			LocationListener listener = new LocationListener() {
				
				@Override
				public void onStatusChanged(String provider, int status, Bundle extras) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onProviderEnabled(String provider) {
					// TODO Auto-generated method stub
					Log.d("Provider Enabled: ","Enabled");
					
				}
				
				@Override
				public void onProviderDisabled(String provider) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onLocationChanged(Location location1) {
					// TODO Auto-generated method stub
					Log.d("Location Change","Location from Emulator Control");
					location = location1;
					
				}
			};
			isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
			if(isGPSEnabled){
				this.canGetLocation = true;
				Log.d("Can Get GPS Location?",""+canGetLocation);
				locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 0, 0, listener);
				if(locationManager!=null){
					Log.d("Location Manager","Not Null");
					location = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
					if(location!=null){
						Log.d("Location","Not Null");
						latitude = location.getLatitude();
						longitude = location.getLongitude();
					}
					else{
						Log.d("Location","Null");
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		}

	

	public boolean canGetLocation(){
		return this.canGetLocation;
	}
	
	public void showSettingsAlert(){
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(mcontext);
		alertDialog.setTitle("GPS Settings");
		alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");

		alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                mcontext.startActivity(intent);
			}
		});
		alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
            }
        });
 
        // Showing Alert Message
        alertDialog.show();
	}
	
	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	public double getLatitude() {
		// TODO Auto-generated method stub
		if(location!=null){
			Log.d("Location",location.toString());
			latitude = location.getLatitude();
		}
		return latitude;
	}

	public double getLongitude() {
		// TODO Auto-generated method stub
		if(location!=null){
			Log.d("Location",location.toString());
			longitude = location.getLongitude();
		}
		return longitude;
	}
	}



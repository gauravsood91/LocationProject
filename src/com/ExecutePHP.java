package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ExecutePHP {
	public String PHPResult(String currentLocation,String nextStop,String distance,String ETA ) throws IOException{
		String result=null;
		String urlString = "http://smsbustrack.site90.com/test.php?CurrentLocation="+currentLocation+"&NextBusStop="+nextStop+"&DistanceToNextStop="+distance+"&ETA="+ETA;
		
		URL url = new URL(urlString);
		URLConnection conn = url.openConnection();
		
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(conn.getInputStream()));
		 
		        String inputLine;
		        while ((inputLine = in.readLine()) != null)
		        {
		            System.out.println(inputLine);
		            result = inputLine;
		            break;
		        }
		        in.close();
		    	
		return result;
	}
}

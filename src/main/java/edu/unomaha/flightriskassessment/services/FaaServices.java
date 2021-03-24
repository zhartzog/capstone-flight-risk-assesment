package edu.unomaha.flightriskassessment.services;

import edu.unomaha.flightriskassessment.models.awc.Metar;
import edu.unomaha.flightriskassessment.models.faa.Runway;
import org.json.JSONArray;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

@Service
public class FaaServices
{

    //Returns object as a JSON string
    private String readAll(Reader reader)
    {
        try
        {
            StringBuilder sb = new StringBuilder();
            int cp;
            while ( (cp = reader.read()) != -1 )
            {
                sb.append((char) cp);
            }
            return sb.toString();
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
        return "";
    }

    private String getGlobalID(String airportID)
    {
        try
        {
            String gloablID_url = "https://services6.arcgis.com/ssFJjBXIUyZDrSYZ/arcgis/rest/services/US_Airport/FeatureServer/0/query?where=ICAO_ID=%27"+airportID+"%27&outFields=GLOBAL_ID&outSR=4326&f=json";
            InputStream is = new URL(gloablID_url).openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            JSONArray data = json.getJSONArray("features");
            JSONObject globalID = data.getJSONObject(0).getJSONObject("attributes");

            return globalID.getString("GLOBAL_ID");

        } catch ( MalformedURLException | JSONException e )
        {
            e.printStackTrace();
        } catch ( IOException e )
        {
            e.printStackTrace();
        }
        return null;
    }

    public List<Runway> getRunways(String airportID)
    {
        String globalID = getGlobalID(airportID);
        List<Runway> runways = new ArrayList<Runway>();
        try
        {
            String url = "https://services6.arcgis.com/ssFJjBXIUyZDrSYZ/arcgis/rest/services/Runways/FeatureServer/0/query?where=AIRPORT_ID=%27"+globalID+"%27&outFields=DESIGNATOR,LENGTH,WIDTH&outSR=4326&f=json";
            InputStream is = new URL(url).openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            JSONArray data = json.getJSONArray("features");
            JSONObject attributes;

            for(int i = 0; i < data.length(); i++)
            {
                attributes = data.getJSONObject(i).getJSONObject("attributes");
                Runway temp = new Runway(attributes.getString("DESIGNATOR"),attributes.getInt("LENGTH"), attributes.getInt("WIDTH"));
                runways.add(temp);
            }

            return runways;

        } catch ( MalformedURLException | JSONException e )
        {
            e.printStackTrace();
        } catch ( IOException e )
        {
            e.printStackTrace();
        }
        return null;
    }
}

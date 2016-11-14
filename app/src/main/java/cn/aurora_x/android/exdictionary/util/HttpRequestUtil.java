package cn.aurora_x.android.exdictionary.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Rinko on 2016/11/13.
 */
public class HttpRequestUtil {
    public static String sendJsonRequest(String url){
        String response=null;
        try{
            HttpURLConnection urlConnection=(HttpURLConnection)(new URL(url).openConnection());
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestMethod("GET");
            int statusCode = urlConnection.getResponseCode();
            if (statusCode==200){
                InputStream inputStream=new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder responseBuilder=new StringBuilder();
                String line=null;
                while((line=reader.readLine())!=null){
                    responseBuilder.append(line+"\n");
                }
                response=responseBuilder.toString();
            }
            else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            return response;
        }
    }
}

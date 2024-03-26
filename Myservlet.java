package com.mypackage;

import jakarta.servlet.ServletException; 
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;
import com.google.gson.JsonObject;


public class Myservlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Myservlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try {
		String location = request.getParameter("wheather_location");
		String ApiKey = "223740190429f0e152e1335875d87464";
		String Api = "https://api.openweathermap.org/data/2.5/weather?q="+location+"&appid="+ApiKey;
		
		URL url = new URL(Api);
		HttpURLConnection connection = (HttpsURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		
		InputStream sp = connection.getInputStream();
		InputStreamReader reader = new InputStreamReader(sp);
		
		StringBuilder sb = new StringBuilder();
		
		Scanner S1 = new Scanner(reader);
		
		while(S1.hasNext()) 
		{		
			sb.append(S1.nextLine());		
		}
		S1.close();
		
		
		 Gson gson = new Gson();
         JsonObject jsonObject = gson.fromJson(sb.toString(), JsonObject.class);
         
         long dateTimestamp = jsonObject.get("dt").getAsLong() * 1000;
         String date = new Date(dateTimestamp).toString();
         
         double temperatureKelvin = jsonObject.getAsJsonObject("main").get("temp").getAsDouble();
         int temperatureCelsius = (int) (temperatureKelvin - 273.15);
        
         int humidity = jsonObject.getAsJsonObject("main").get("humidity").getAsInt();
         
         double windSpeed = jsonObject.getAsJsonObject("wind").get("speed").getAsDouble();
         
         String weatherCondition = jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject().get("main").getAsString();
         
         
         
         request.setAttribute("date", date);
         request.setAttribute("city", location);
         request.setAttribute("temperature", temperatureCelsius);
         request.setAttribute("weatherCondition", weatherCondition); 
         request.setAttribute("humidity", humidity);    
         request.setAttribute("windSpeed", windSpeed);
         request.setAttribute("weatherData", sb.toString());
         
         connection.disconnect();
         
         request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		catch(Exception e)
		{
			
			request.getRequestDispatcher("index.html").forward(request, response);
		}
         }
	
	}
	



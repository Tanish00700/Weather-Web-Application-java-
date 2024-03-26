<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Weather</title>
 <link rel="stylesheet" href="Style.css">
</head>
<body>
<h1 id="mainh1" > Weather Application</h1>

	 <div class="mainContainer">
     <form action="Myservlet" method="post" class="searchInput">
            <input type="text" placeholder="Enter City Name" id="searchInput" name="wheather_location"/>
            <button id="searchButton">Search</button>
      </form>
        <div class="weatherDetails">
            <div class="weatherIcon">
                <img src="data:image/jpeg;base64,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" alt="Clouds" id="weather-icon">
                <h2>${temperature} °C</h2>
                 <input type="hidden" id="wc" value="${weatherCondition}"></input>
            </div>
            
            <div class="cityDetails">        
                <div class="desc"><strong>${city}</strong></div>
                <div class="date">${date}</div>
            </div>
            <div class="windDetails">
            	<div class="humidityBox">
            	<img src="https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEhgr7XehXJkOPXbZr8xL42sZEFYlS-1fQcvUMsS2HrrV8pcj3GDFaYmYmeb3vXfMrjGXpViEDVfvLcqI7pJ03pKb_9ldQm-Cj9SlGW2Op8rxArgIhlD6oSLGQQKH9IqH1urPpQ4EAMCs3KOwbzLu57FDKv01PioBJBdR6pqlaxZTJr3HwxOUlFhC9EFyw/s320/thermometer.png" alt="Humidity">
                <div class="humidity">
                   <span>Humidity </span>
                   <h2>${humidity}% </h2>
                </div>
               </div> 
               
                <div class="windSpeed">
                    <img src="https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEiyaIguDPkbBMnUDQkGp3wLRj_kvd_GIQ4RHQar7a32mUGtwg3wHLIe0ejKqryX8dnJu-gqU6CBnDo47O7BlzCMCwRbB7u0Pj0CbtGwtyhd8Y8cgEMaSuZKrw5-62etXwo7UoY509umLmndsRmEqqO0FKocqTqjzHvJFC2AEEYjUax9tc1JMWxIWAQR4g/s320/wind.png">
                    <div class="wind">
                    <span>Wind Speed</span>
                    <h2> ${windSpeed} km/h</h2>
                    </div>
                </div>
            </div>
        </div>
    </div>
	

</body>
</html>
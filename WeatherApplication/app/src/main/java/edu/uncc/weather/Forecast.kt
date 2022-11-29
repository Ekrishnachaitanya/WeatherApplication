package edu.uncc.weather

import edu.uncc.weather.WeatherDescription

class Forecast {
    var main: Main? = null
    var weather: List<WeatherDescription>? = null
    var dt_txt: String? = null
    override fun toString(): String {
        return "Forecast{" +
                "main=" + main +
                ", weather=" + weather +
                ", dt_txt='" + dt_txt + '\'' +
                '}'
    }
}

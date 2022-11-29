package edu.uncc.weather

import edu.uncc.weather.Forecast

class WeatherForecasts {
    var list: List<Forecast>? = null
    override fun toString(): String {
        return "WeatherForecasts{" +
                "list=" + list +
                '}'
    }
}

/* Assignment: InClass08
   File Name: Group40_InClass08
   Student Names: Krishna Chaitanya Emmala, Naga Sivaram Mannam
*/
package edu.uncc.weather

import edu.uncc.weather.Wind
import edu.uncc.weather.Cloud
import edu.uncc.weather.WeatherDescription
import java.util.ArrayList

class Weather {
    var main: Main? = null
    var wind: Wind? = null
    var clouds: Cloud? = null
    var weather: ArrayList<WeatherDescription>? = null
    override fun toString(): String {
        return "Weather{" +
                "main=" + main +
                ", wind=" + wind +
                ", clouds=" + clouds +
                ", weather=" + weather +
                '}'
    }
}
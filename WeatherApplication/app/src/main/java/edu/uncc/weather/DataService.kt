package edu.uncc.weather

import edu.uncc.weather.DataService.City
import java.io.Serializable
import java.util.ArrayList

object DataService {
    val cities: ArrayList<City?> = object : ArrayList<City?>() {
        init {
            add(City("US", "Charlotte"))
            add(City("US", "Chicago"))
            add(City("US", "New York"))
            add(City("US", "Miami"))
            add(City("US", "San Francisco"))
            add(City("US", "Baltimore"))
            add(City("US", "Houston"))
            add(City("UK", "London"))
            add(City("UK", "Bristol"))
            add(City("UK", "Cambridge"))
            add(City("UK", "Liverpool"))
            add(City("AE", "Abu Dhabi"))
            add(City("AE", "Dubai"))
            add(City("AE", "Sharjah"))
            add(City("JP", "Tokyo"))
            add(City("JP", "Kyoto"))
            add(City("JP", "Hashimoto"))
            add(City("JP", "Osaka"))
        }
    }

    class City(var country: String, var city: String) : Serializable {

        override fun toString(): String {
            return "$city,$country"
        }
    }
}

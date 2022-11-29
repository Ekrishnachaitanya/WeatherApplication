/* Assignment: InClass08
   File Name: Group40_InClass08
   Student Names: Krishna Chaitanya Emmala, Naga Sivaram Mannam
*/
package edu.uncc.weather

import androidx.appcompat.app.AppCompatActivity
import edu.uncc.weather.CitiesFragment.CitiesFragmentListener
import edu.uncc.weather.CurrentWeatherFragment.CurrentWeatherListener
import android.os.Bundle
import edu.uncc.weather.R
import edu.uncc.weather.CitiesFragment
import edu.uncc.weather.DataService.City
import edu.uncc.weather.CurrentWeatherFragment
import edu.uncc.weather.WeatherForecastFragment

class MainActivity : AppCompatActivity(), CitiesFragmentListener, CurrentWeatherListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(R.id.rootView, CitiesFragment())
            .commit()
    }

    override fun gotoCurrentWeather(city: City?) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.rootView, CurrentWeatherFragment.newInstance(city))
            .addToBackStack(null)
            .commit()
    }


    override fun gotoForeCast(city: City?) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.rootView, WeatherForecastFragment.newInstance(city))
            .addToBackStack(null)
            .commit()
    }
}
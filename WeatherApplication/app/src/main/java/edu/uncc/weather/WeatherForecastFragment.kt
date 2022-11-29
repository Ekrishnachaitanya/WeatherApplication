package edu.uncc.weather

import okhttp3.*
import edu.uncc.weather.DataService.City
import androidx.recyclerview.widget.LinearLayoutManager
import android.os.Bundle
import edu.uncc.weather.WeatherForecastFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import edu.uncc.weather.R
import kotlin.Throws
import com.google.gson.Gson
import edu.uncc.weather.WeatherForecasts
import edu.uncc.weather.Forecast
import edu.uncc.weather.WeatherForecastFragment.ForecastAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.uncc.weather.WeatherDescription
import com.squareup.picasso.Picasso
import android.widget.TextView
import androidx.fragment.app.Fragment
import edu.uncc.weather.databinding.FragmentWeatherForecastBinding
import okhttp3.*
import java.io.IOException

/**
 * A simple [Fragment] subclass.
 * Use the [WeatherForecastFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WeatherForecastFragment() : Fragment() {
    private var mCity: City? = null
    var binding: FragmentWeatherForecastBinding? = null
    var layoutManager: LinearLayoutManager? = null
    var client = OkHttpClient()
    var API_KEY = "2e71b765d4464c1420ecd4bc8199a3dd"
    var CITY = "q"
    var APP_ID = "appid"
    var urlImg = "https://openweathermap.org/img/wn/"
    var png = "@2x.png"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mCity = requireArguments().getSerializable(ARG_PARAM_CITY) as City?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWeatherForecastBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = activity
        if (activity != null) activity.title = getString(R.string.weatherForecast)
        val request = Request.Builder()
            .url("https://www.api.openweathermap.org/data/2.5/forecast?"+CITY+"="+mCity!!.city+"&"+APP_ID+"="+API_KEY)
            .build()
        val client = OkHttpClient();
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val gson = Gson()
                    val responseBody = response.body!!.string()
                    val weatherForecasts = gson.fromJson(responseBody, WeatherForecasts::class.java)
                    val forecasts = weatherForecasts.list
                    requireActivity().runOnUiThread(Runnable {
                        val adapter = forecasts?.let { ForecastAdapter(it) }
                        binding!!.recyclerView.adapter = adapter
                    })
                }
            }
        })
        binding!!.cityAndCountryValue.text = mCity!!.city + "," + mCity!!.country
        binding!!.recyclerView.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(activity)
        binding!!.recyclerView.layoutManager = layoutManager
    }

    internal inner class ForecastAdapter(var weatherForecasts: List<Forecast>) :
        RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.forecast_weather_cell, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val forecast = weatherForecasts[position]
            holder.dateTextView.text = forecast.dt_txt
            val main = forecast.main
            val description = forecast.weather!![0]
            holder.temperatureTextView.text = main!!.temp + getString(R.string.fLabel)
            holder.maxTempValue.text = main.temp + getString(R.string.fLabel)
            holder.minTempValue.text = main.temp + getString(R.string.fLabel)
            holder.descriptionTextView.text = description.description
            Picasso.get().load(urlImg + description.icon + png).into(holder.imageView)
        }

        override fun getItemCount(): Int {
            return weatherForecasts.size
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var dateTextView: TextView
            var temperatureTextView: TextView
            var maxTempValue: TextView
            var minTempValue: TextView
            var descriptionTextView: TextView
            var imageView: ImageView

            init {
                dateTextView = itemView.findViewById(R.id.dateTextView)
                temperatureTextView = itemView.findViewById(R.id.temperatureTextView)
                maxTempValue = itemView.findViewById(R.id.maxTempValue)
                minTempValue = itemView.findViewById(R.id.minTempValue)
                descriptionTextView = itemView.findViewById(R.id.descriptionTextView)
                imageView = itemView.findViewById(R.id.weatherImageView)
            }
        }
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM_CITY = "ARG_PARAM_CITY"

        // TODO: Rename and change types and number of parameters
        fun newInstance(city: City?): WeatherForecastFragment {
            val fragment = WeatherForecastFragment()
            val args = Bundle()
            args.putSerializable(ARG_PARAM_CITY, city)
            fragment.arguments = args
            return fragment
        }
    }
}

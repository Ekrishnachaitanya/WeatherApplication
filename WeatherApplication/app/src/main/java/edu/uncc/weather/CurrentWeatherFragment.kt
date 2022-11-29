package edu.uncc.weather

import android.content.Context
import okhttp3.HttpUrl.Builder.scheme
import okhttp3.HttpUrl.Builder.host
import okhttp3.HttpUrl.Builder.addPathSegment
import okhttp3.HttpUrl.Builder.addEncodedQueryParameter
import okhttp3.HttpUrl.Builder.build
import okhttp3.Request.Builder.url
import okhttp3.Request.Builder.build
import okhttp3.OkHttpClient.newCall
import okhttp3.Call.enqueue
import okhttp3.Response.isSuccessful
import okhttp3.Response.body
import okhttp3.ResponseBody.string
import edu.uncc.weather.Cloud.all
import edu.uncc.weather.DataService.City
import edu.uncc.weather.CurrentWeatherFragment.CurrentWeatherListener
import android.os.Bundle
import edu.uncc.weather.CurrentWeatherFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlin.Throws
import com.google.gson.Gson
import edu.uncc.weather.Weather
import edu.uncc.weather.Wind
import edu.uncc.weather.Cloud
import edu.uncc.weather.WeatherDescription
import com.squareup.picasso.Picasso
import edu.uncc.weather.R
import edu.uncc.weather.databinding.FragmentCurrentWeatherBinding
import okhttp3.*
import java.io.IOException

class CurrentWeatherFragment : Fragment() {
    private var mCity: City? = null
    var binding: FragmentCurrentWeatherBinding? = null
    var wlistener: CurrentWeatherListener? = null
    var client = OkHttpClient()
    var API_KEY = "2e71b765d4464c1420ecd4bc8199a3dd"
    var CITY = "q"
    var APP_ID = "appid"
    var urlImg = "https://openweathermap.org/img/wn/"
    var png = "@2x.png"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mCity = arguments!!.getSerializable(ARG_PARAM_CITY) as City?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCurrentWeatherBinding.inflate(inflater, container, false)
        Thread.currentThread().id
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity!!.title = "Current Weather"
        val builder = Builder()
        val url: HttpUrl = builder.scheme("https")
            .host("api.openweathermap.org")
            .addPathSegment("data")
            .addPathSegment("2.5")
            .addPathSegment("weather")
            .addEncodedQueryParameter(CITY, mCity!!.city)
            .addEncodedQueryParameter(APP_ID, API_KEY)
            .build()
        val request: Request = Builder()
            .url(url)
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val gson = Gson()
                    val responseBody = response.body!!.string()
                    val weather = gson.fromJson(responseBody, Weather::class.java)
                    activity!!.runOnUiThread {
                        val main = weather.main
                        val wind = weather.wind
                        val cloud = weather.clouds
                        val description = weather.weather[0]
                        Picasso.get().load(urlImg + description.icon + png)
                            .into(binding!!.imageView)
                        binding!!.cityTextView.text = mCity!!.city + "," + mCity!!.country
                        binding!!.temperatureValue.text = main.temp + getString(R.string.fLabel)
                        binding!!.temperatureMaxValue.text =
                            main.temp_max + getString(R.string.fLabel)
                        binding!!.temperatureMinValue.text =
                            main.temp_min + getString(R.string.fLabel)
                        binding!!.descriptionValue.text = description.description
                        binding!!.humidityValue.text =
                            main.humidity + getString(R.string.percentageSymbol)
                        binding!!.windSpeedValue.text =
                            wind.speed + getString(R.string.milesPerHrLabel)
                        binding!!.windDegreeValue.text =
                            wind.deg + getString(R.string.degreesLabel)
                        binding!!.cloudinessValue.text =
                            cloud.all + getString(R.string.percentageSymbol)
                        binding!!.foreCastButton.setOnClickListener { wlistener!!.gotoForeCast(mCity) }
                    }
                }
            }
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        wlistener = context as CurrentWeatherListener
    }

    interface CurrentWeatherListener {
        fun gotoForeCast(city: City?)
    }

    companion object {
        private const val ARG_PARAM_CITY = "ARG_PARAM_CITY"
        fun newInstance(city: City?): CurrentWeatherFragment {
            val fragment = CurrentWeatherFragment()
            val args = Bundle()
            args.putSerializable(ARG_PARAM_CITY, city)
            fragment.arguments = args
            return fragment
        }
    }
}

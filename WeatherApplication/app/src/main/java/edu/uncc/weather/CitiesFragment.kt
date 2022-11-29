
package edu.uncc.weather

import android.R
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import edu.uncc.weather.DataService.City
import edu.uncc.weather.DataService
import android.widget.AdapterView.OnItemClickListener
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import edu.uncc.weather.CitiesFragment.CitiesFragmentListener
import edu.uncc.weather.databinding.FragmentCitiesBinding

class CitiesFragment : Fragment() {
    var binding: FragmentCitiesBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCitiesBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    var adapter: ArrayAdapter<City>? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity!!.title = "Cities"
        adapter =
            ArrayAdapter(activity!!, R.layout.simple_list_item_1, R.id.text1, DataService.cities)
        binding!!.listView.adapter = adapter
        binding!!.listView.onItemClickListener =
            OnItemClickListener { adapterView, view, position, l ->
                mListener!!.gotoCurrentWeather(adapter!!.getItem(position))
            }
    }

    var mListener: CitiesFragmentListener? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = context as CitiesFragmentListener
    }

    interface CitiesFragmentListener {
        fun gotoCurrentWeather(city: City?)
    }
}

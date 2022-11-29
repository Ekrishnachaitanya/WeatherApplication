package edu.uncc.weather

class WeatherDescription {
    var description: String? = null
    var icon: String? = null
    override fun toString(): String {
        return "WeatherDescription{" +
                "description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                '}'
    }
}

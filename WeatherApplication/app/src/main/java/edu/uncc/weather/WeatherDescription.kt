/* Assignment: InClass08
   File Name: Group40_InClass08
   Student Names: Krishna Chaitanya Emmala, Naga Sivaram Mannam
*/
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
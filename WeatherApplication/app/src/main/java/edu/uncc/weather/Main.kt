/* Assignment: InClass08
   File Name: Group40_InClass08
   Student Names: Krishna Chaitanya Emmala, Naga Sivaram Mannam
*/
package edu.uncc.weather

class Main {
    var temp: String? = null
    var temp_min: String? = null
    var temp_max: String? = null
    var humidity: String? = null
    override fun toString(): String {
        return "Main{" +
                "temp='" + temp + '\'' +
                ", temp_min='" + temp_min + '\'' +
                ", temp_max='" + temp_max + '\'' +
                ", humidity='" + humidity + '\'' +
                '}'
    }
}
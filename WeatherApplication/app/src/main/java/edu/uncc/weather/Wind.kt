/* Assignment: InClass08
   File Name: Group40_InClass08
   Student Names: Krishna Chaitanya Emmala, Naga Sivaram Mannam
*/
package edu.uncc.weather

class Wind {
    var speed: String? = null
    var deg: String? = null
    override fun toString(): String {
        return "Wind{" +
                "speed='" + speed + '\'' +
                ", deg='" + deg + '\'' +
                '}'
    }
}
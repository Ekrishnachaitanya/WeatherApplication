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

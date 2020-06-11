package KMeans

import kotlin.math.pow
import kotlin.math.sqrt

class EuclideanDistance: Distance {
    override fun calculate(f1: Record, f2: Record): Double {
        val sum =  (f1.lat - f2.lat).pow(2) + (f1.lng - f2.lng).pow(2)
        return sqrt(sum)
    }
}
package KMeans

interface Distance {
    fun calculate(f1: Record, f2: Record): Double
}
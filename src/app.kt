import KMeans.EuclideanDistance
import KMeans.KMeans
import KMeans.Record
import kotlin.system.measureTimeMillis


fun main() {
    // Testing TSP
//    val elapsed = measureTimeMillis {
//        executeTsp()
//    }
//    println("elapsedTime : ${elapsed}ms")

    // Testing Kmeans
    //Record(0, 456.0, 320.0),
    val records = listOf(
        Record(0, 456.0, 320.0),
        Record(1, 228.0, 0.0),
        Record(2, 912.0, 0.0),
        Record(3, 0.0, 80.0),
        Record(4, 114.0, 80.0),
        Record(5, 570.0, 160.0),
        Record(6, 798.0, 160.0),
        Record(7, 342.0, 240.0),
        Record(8, 684.0, 240.0),
        Record(9, 570.0, 400.0),
        Record(10, 912.0, 400.0),
        Record(11, 114.0, 480.0),
        Record(12, 228.0, 480.0),
        Record(13, 342.0, 560.0),
        Record(14, 684.0, 560.0),
        Record(15, 0.0, 640.0),
        Record(16, 798.0, 640.0)
    )

    val kmean = KMeans()
    val result = kmean.fit(
        records = records,
        k = 4,
        distance = EuclideanDistance(),
        maxIterations = 20
    )
    println("result:$result")
}


fun executeTsp() {
    val tsp = Tsp()
    val matrix = arrayOf(
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 0
    )
    tsp.cal(matrix, 1, 12)
    tsp.bestRoute()
}

fun executeExample() {
    val example = Example()
    val matrix = arrayOf(
        0, 1, 2, 3, 0
    )

    example.cal(matrix, 1, 3)
    example.bestRoute()
}

fun <T> Array<T>.swap(i: Int, j: Int) {
    with(this[i]) {
        this@swap[i] = this@swap[j]
        this@swap[j] = this
    }
}
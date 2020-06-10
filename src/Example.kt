import java.util.ArrayList

class Example {

    private val distanceMatrix = arrayOf(
        arrayOf(0, 10, 35, 30),
        arrayOf(10, 0, 30, 15),
        arrayOf(35, 30, 0, 30),
        arrayOf(30, 15, 30, 0)
    )

    private var minDistance = 0
    private lateinit var minRoute : MutableList<Int>

    fun bestRoute() {
        println("==================================")
        for (item in minRoute) {
            print("$item ")
        }
        println("")
        println("best distance:$minDistance")
        println("==================================")
    }

    private fun showDistance(first: Int, second: Int) : Int {
        return distanceMatrix[first][second]
    }

    fun cal(array: Array<Int>, start: Int, end: Int) {
        if (start == end) {
            var first = 0
            var totalDistance = 0
            for (item in array) {
                print("$item ")
                totalDistance += showDistance(first, item)
                first = item
            }
            if (minDistance == 0 || minDistance > totalDistance) {
                minDistance = totalDistance
                minRoute = array.toMutableList()
            }
            print("  ==>  ")
            println("totalDistance:$totalDistance")
        } else {
            for (i in start..end step 1) {
                array.swap(start, i)
                cal(array, start + 1, end)
                array.swap(start, i)
            }
        }
    }
}


class Tsp {
    private val distanceMatrix = arrayOf(
        arrayOf(0, 2451, 713, 1018, 1631, 1374, 2408, 213, 2571, 875, 1420, 2145, 1972),
        arrayOf(2451, 0, 1745, 1524, 831, 1240, 959, 2596, 403, 1589, 1374, 357, 579),
        arrayOf(713, 1745, 0, 355, 920, 803, 1737, 851, 1858, 262, 940, 1453, 1260),
        arrayOf(1018, 1524, 355, 0, 700, 862, 1395, 1123, 1584, 466, 1056, 1280, 987),
        arrayOf(1631, 831, 920, 700, 0, 663, 1021, 1769, 949, 796, 879, 586, 371),
        arrayOf(1374, 1240, 803, 862, 663, 0, 1681, 1551, 1765, 547, 225, 887, 999),
        arrayOf(2408, 959, 1737, 1395, 1021, 1681, 0, 2493, 678, 1724, 1891, 1114, 701),
        arrayOf(213, 2596, 851, 1123, 1769, 1551, 2493, 0, 2699, 1038, 1605, 2300, 2099),
        arrayOf(2571, 403, 1858, 1584, 949, 1765, 678, 2699, 0, 1744, 1645, 653, 600),
        arrayOf(875, 1589, 262, 466, 796, 547, 1724, 1038, 1744, 0, 679, 1272, 1162),
        arrayOf(1420, 1374, 940, 1056, 879, 225, 1891, 1605, 1645, 679, 0, 1017, 1200),
        arrayOf(2145, 357, 1453, 1280, 586, 887, 1114, 2300, 653, 1272, 1017, 0, 504),
        arrayOf(1972, 579, 1260, 987, 371, 999, 701, 2099, 600, 1162, 1200, 504, 0)
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
                //print("$item ")
                totalDistance += showDistance(first, item)
                first = item
            }
            if (minDistance == 0 || minDistance > totalDistance) {
                minDistance = totalDistance
                minRoute = array.toMutableList()
            }
            //print("  ==>  ")
            //println("totalDistance:$totalDistance")
        } else {
            for (i in start..end step 1) {
                array.swap(start, i)
                cal(array, start + 1, end)
                array.swap(start, i)
            }
        }
    }
}

package KMeans

import java.util.*
import kotlin.collections.HashMap
import kotlin.math.max
import kotlin.math.min

/**
 * https://www.baeldung.com/java-k-means-clustering-algorithm
 */

data class Record(val positionIndex: Int, val lat: Double, val lng: Double)

class KMeans {

    private val random: Random = Random()

    fun fit(
        records: List<Record>,
        k: Int,
        distance: Distance,
        maxIterations: Int
    ): HashMap<Record, MutableList<Record>> {
        // omitted
        var centroids = randomCentroids(records, k)
        var clusters = HashMap<Record, MutableList<Record>>()
        var lastState = HashMap<Record, MutableList<Record>>()

        for (i in 0 until maxIterations) {
            val isLastIteration = i == maxIterations - 1
            println("isLastIteration=${isLastIteration}")

            // in each iteration we should find the nearest centroid for each record
            for (record in records) {
                val centroid = nearestCentroid(record, centroids = centroids, distance = distance)
                assignToCluster(clusters = clusters, record = record, centroid = centroid)
            }

            // if the assignments do not change, then the algorithm terminates
            val shouldTerminate = isLastIteration || clusters == lastState
            lastState = clusters
            if (shouldTerminate)
                break

            // at the end of each iteration we should relocate the centroids
            centroids = relocateCentroids(clusters = clusters)
            println("centroids = $centroids")
            clusters = HashMap()
        }

        return lastState
    }

    private fun randomCentroids (
        records: List<Record>,
        k: Int
    ): List<Record> {
        val centroids: MutableList<Record> = ArrayList()
        var latMax: Double = Double.MIN_VALUE
        var latMin: Double = Double.MAX_VALUE
        var lngMax: Double = Double.MIN_VALUE
        var lngMin: Double = Double.MAX_VALUE

        for (record in records) {
            latMax = max(record.lat, latMax)
            latMin = min(record.lat, latMin)
            lngMax = max(record.lng, lngMax)
            lngMin = min(record.lng, lngMin)
        }

        for (i in 0 until k) {
            val centroid = Record(
                -1,
                random.nextDouble() * (latMax - latMin) + latMin,
                random.nextDouble() * (lngMax - lngMin) + lngMin
            )
            centroids.add(centroid)
        }
        return centroids
    }

    private fun nearestCentroid(
        record: Record,
        centroids: List<Record>,
        distance: Distance
    ): Record? {
        var minimumDistance = Double.MAX_VALUE
        var nearest: Record? = null
        for (centroid in centroids) {
            val currentDistance = distance.calculate(record, centroid)
            if (currentDistance < minimumDistance) {
                minimumDistance = currentDistance
                nearest = centroid
            }
        }
        return nearest
    }

    private fun assignToCluster(
        clusters: HashMap<Record, MutableList<Record>>,
        record: Record,
        centroid: Record?
    ) {
        centroid?.let {
            if (clusters[centroid] == null)
                clusters[centroid] = arrayListOf()

            clusters[centroid]?.add(record)
        }
    }

    private fun average(centroid: Record, records: List<Record>) : Record {
        if (records.isEmpty())
            return centroid

        var sumLat = 0.0
        var sumLng = 0.0
        for (record in records) {
            sumLat += record.lat
            sumLng += record.lng
        }

        return Record(-1,sumLat/records.size,sumLng/records.size)
    }

    private fun relocateCentroids(clusters : MutableMap<Record, MutableList<Record>>): List<Record> {
        return clusters.entries
            .map {
                average(it.key, it.value)
            }
            .toList()
    }
}
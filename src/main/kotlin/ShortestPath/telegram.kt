package ShortestPath

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.PriorityQueue

// 전보 - 유명알고리즘 대회
private const val INF = 100000000
fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))

    val (numOfCity,numOfLines,startCity) = br.readLine().split(" ").map{it.toInt()}

    val graph = ArrayList<ArrayList<City>>()
    for(i in 0 .. numOfCity){
        graph.add(ArrayList())
    }
    val costArray = Array(numOfCity+1){ INF }

    for (i in 0 until numOfLines){
        val (start, end, cost) = br.readLine().split(" ").map{it.toInt()}
        graph[start].add(City(end, cost))
    }

    dijkstra(startCity, costArray, graph)

    val subListOfCost = costArray.toList().subList(1, costArray.size)
    var count = 0
    var maxCost = 0
    subListOfCost.forEach{
        if(it != INF){
            count++
            maxCost = maxOf(maxCost, it)
        }
    }
    println("${count-1} $maxCost")
    br.close()
} // end of ShortestPath.main()

fun dijkstra(startCity: Int, costArray:Array<Int>, graph: ArrayList<ArrayList<City>>){
    val q = PriorityQueue<City>()
    costArray[startCity] = 0
    q.add(City(startCity,0))

    while (q.isNotEmpty()){
        val (now, costUntilNow) = q.poll()
        if (costArray[now] < costUntilNow) continue

        graph[now].forEach{
            val newCost = costUntilNow + it.cost
            if (newCost < costArray[it.index]){
                costArray[it.index] = newCost
                q.add(City(it.index, newCost))
            }
        }
    }

} // end of ShortestPath.dijkstra()

data class City(val index: Int, val cost: Int):Comparable<City>{
    override fun compareTo(other: City): Int = cost - this.cost
} // end of ShortestPath.City
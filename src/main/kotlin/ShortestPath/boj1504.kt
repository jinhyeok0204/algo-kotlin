package ShortestPath

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.PriorityQueue

private const val INF = 100000000

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, e) = br.readLine().split(" ").map{it.toInt()}
    val distance = IntArray(n+1){ INF }
    val graph = Array(n+1){ArrayList<Vertex>()}

    for(i in 0 until e){
        // a에서 b로 가는비용이 c
        val (a, b, c) = br.readLine().split(" ").map{it.toInt()}
        graph[a].add(Vertex(b, c))
        graph[b].add(Vertex(a, c))
    }

    val start = 1
    dijkstra(start, distance, graph)

    val (v1, v2) = br.readLine().split(" ").map{it.toInt()}

    // 1) 1 -> v1 -> v2 -> n
    // 2) 1 -> v2 -> v1 -> n

    var v1First = distance[v1] // 1 -> v1
    var v2First = distance[v2] // 1 -> v2

    resetDistance(n, distance)

    dijkstra(v1, distance, graph)
    v1First += distance[v2] // v1 -> v2
    v2First += distance[n] // v2 -> n

    resetDistance(n, distance)

    dijkstra(v2, distance, graph)
    v1First += distance[n] // v1 -> n
    v2First += distance[v1] // v2 -> v1

    val result = if(v1First < v2First) v1First else v2First

    if(result >= INF) println(-1) else println(result)

    br.close()
}// end of ShortestPath.main()

fun dijkstra(start:Int, distance:IntArray, graph:Array<ArrayList<Vertex>>){
    val q = PriorityQueue<Vertex>()
    distance[start] = 0
    q.add(Vertex(start, distance[start]))

    while (q.isNotEmpty()){
        val (now, distanceUntilNow) = q.poll()

        // 현재 Vertex가 이미 처리된 적이 있다면 무시
        if (distance[now] < distanceUntilNow ) continue

        graph[now].forEach{
            val newDistance = distanceUntilNow + it.dist
            if(newDistance < distance[it.index]){
                distance[it.index] = newDistance
                q.add(Vertex(it.index, newDistance))
            }
        }
    }
} // end of ShortestPath.dijkstra()

fun resetDistance(n:Int, distance: IntArray){
    for(i in 0..n){
        distance[i] = INF
    }
} // end of ShortestPath.resetDistance()

data class Vertex(val index: Int, val dist: Int):Comparable<Vertex>{
    override fun compareTo(other: Vertex): Int = dist - this.dist
}// end of ShortestPath.Vertex
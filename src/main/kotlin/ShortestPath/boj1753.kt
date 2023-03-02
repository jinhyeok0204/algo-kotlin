package ShortestPath

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.PriorityQueue

// BOJ 1753 최단거리

private const val INF = 1000000000

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    val (V, E) = br.readLine().split(" ").map { it.toInt() }
    val startNode = br.readLine().toInt()
    val distance = Array(V + 1) { INF }

    val graph = ArrayList<ArrayList<Node>>()
    for (i in 0..V) {
        graph.add(ArrayList())
    }
    for (i in 0 until E) {
        val (u, v, w) = br.readLine().split(" ").map { it.toInt() }
        graph[u].add(Node(v, w))
    }
    dijkstra(startNode, distance, graph)

    for(i in 1..V){
        if(distance[i] == INF){
            println("ShortestPath.INF")
        }
        else{
            println(distance[i])
        }
    }

    br.close()
} // end of ShortestPath.main()


private fun dijkstra(startNode: Int, distance:Array<Int>, graph:ArrayList<ArrayList<Node>>){
    val q = PriorityQueue<Node>()

    q.add(Node(startNode, 0))
    distance[startNode] = 0

    while(!q.isEmpty()){
        val (now, cost) = q.poll()
        if (distance[now] < cost)
            continue

        for(i in graph[now]){
            val cost = distance[now] + i.distance
            if (cost < distance[i.index]){
                distance[i.index] = cost
                q.add(Node(i.index, cost))
            }
        }
    }
} // end of ShortestPath.dijkstra()

data class Node(val index:Int, val distance:Int):Comparable<Node>{
    override fun compareTo(other: Node): Int = distance - other.distance
} // end of ShortestPath.Node
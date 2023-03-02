package ShortestPath

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private const val INF = 1000000000
fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val t = br.readLine().toInt()

    for (i in 0 until t){
        val n = br.readLine().toInt()
        solution(n, br)
    }

    br.close()
}

fun solution(size: Int, br:BufferedReader){
    val graph = Array(size){IntArray(size){0} }
    val distance = Array(size){IntArray(size){ INF } }
    for(i in 0 until size){
        graph[i] = br.readLine().split(" ").map{it.toInt()}.toIntArray()
    }
    dijkstra(0, 0, distance, graph, size)
    // 상하좌우

    print(distance[size-1][size-1])
}

fun dijkstra(startX:Int, startY:Int, distance:Array<IntArray>, graph:Array<IntArray>, size:Int){

    val q = PriorityQueue<Block>()
    distance[startX][startY] = graph[startX][startY]
    q.add(Block(startX, startY, graph[startX][startY]))

    val dx = listOf(-1, 0, 1, 0)
    val dy = listOf(0, 1, 0, -1)
    while(q.isNotEmpty()){
        val (toX, toY, untilEnergy) = q.poll()
        if (distance[toX][toY] < untilEnergy){
            continue
        }
        for (i in 0 until 4){
            val nx = toX + dx[i]
            val ny = toY + dy[i]
            if (nx <0 || nx >= size || ny < 0 || ny >= size) {
                continue
            }
            val newEnergy = untilEnergy + graph[nx][ny]
            if (newEnergy < distance[nx][ny]){
                distance[nx][ny] = newEnergy
                q.add(Block(nx, ny, newEnergy))
            }
        }
    }

}

data class Block(val x:Int, val y:Int, val energy:Int):Comparable<Block>{
    override fun compareTo(other: Block) = energy - this.energy
}
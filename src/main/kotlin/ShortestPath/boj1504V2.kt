package ShortestPath

import java.io.BufferedReader
import java.io.InputStreamReader

private const val INF = 100000000
fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, e) = br.readLine().split(" ").map{it.toInt()}

    val distanceTable = Array(n+1){IntArray(n+1){ INF }}
    for(i in 1 .. n){
        for(j in 1 .. n){
            if(i == j) distanceTable[i][j] = 0
        }
    }
    for(i in 0 until e){
        val (a,b,c) = br.readLine().split(" ").map{it.toInt()}
        distanceTable[a][b] = c
        distanceTable[b][a] = c
    }

    for(k in 1..n){
        for(a in 1..n){
            for(b in 1..n){
                distanceTable[a][b] = minOf(distanceTable[a][b], distanceTable[a][k] + distanceTable[k][b])
            }
        }
    }

    val (v1, v2) = br.readLine().split(" ").map{it.toInt()}
    val v1First = distanceTable[1][v1] + distanceTable[v1][v2] + distanceTable[v2][n]
    val v2First = distanceTable[1][v2] + distanceTable[v2][v1] + distanceTable[v1][n]
    val result = if(v1First < v2First) v1First else v2First

    if (result >= INF || result == 0) println(-1)
    else println(result)
}
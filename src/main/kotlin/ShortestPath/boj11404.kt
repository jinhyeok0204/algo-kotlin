package ShortestPath

import java.io.BufferedReader
import java.io.InputStreamReader

private const val INF = 1000000000
fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val m = br.readLine().toInt()

    val distanceTable = Array(n+1){IntArray(n+1){ INF } }

    // 시작지점 == 종료지점이면 0으로 초기화
    for (i in 1..n){
        for(j in 1..n){
            if(i == j) distanceTable[i][j] = 0
        }
    }

    for(i in 0 until m){
        val(a,b,c) = br.readLine().split(" ").map{it.toInt()}
        distanceTable[a][b] = minOf(distanceTable[a][b], c)
    }

    for(k in 1..n){
        for(a in 1..n){
            for(b in 1..n){
                distanceTable[a][b] = minOf(distanceTable[a][b], distanceTable[a][k] + distanceTable[k][b])
            }
        }
    }

    for(i in 1..n){
        for(j in 1..n){
            if(distanceTable[i][j] >= INF) { print("${0} ") }
            else                           { print("${distanceTable[i][j]} ")}
        }
        println()
    }
    br.close()
} // end of ShortestPath.main()

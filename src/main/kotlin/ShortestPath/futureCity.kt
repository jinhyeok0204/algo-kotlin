package ShortestPath

import java.io.BufferedReader
import java.io.InputStreamReader

// 플로이드 워셜 알고리즘 !
private const val INF = 1000000000
fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, m) = br.readLine().split(" ").map{it.toInt()}

    val distanceTable = Array(n+1) { IntArray(n+1) { INF } } // 거리를 저장할 2차원 배열

    // 자기 자신에서 자기 자신으로 가는 비용은 0
    for(i in 1..n){
        for(j in 1 .. n){
            if (i == j) {
                distanceTable[i][j] = 0
            }
        }
    }

    // 연결된 회사끼리 가는 비용은 1로 설정
    for (i in 0 until m){
        val (s, e) = br.readLine().split(" ").map{it.toInt()}
        distanceTable[s][e] = 1
        distanceTable[e][s] = 1
    }

    // Dab = min(Dab , Dak + Dkb)
    for (k in 1..n){
        for(a in 1 .. n){
            for(b in 1..n){
                distanceTable[a][b] = minOf( distanceTable[a][b] ,distanceTable[a][k] + distanceTable[k][b])
            }
        }
    }

    val (x, k) = br.readLine().split(" ").map{it.toInt()}
    val cost = distanceTable[1][k] + distanceTable[k][x]
    if (cost >= INF) println(-1)
    else println(cost)
    br.close()
}
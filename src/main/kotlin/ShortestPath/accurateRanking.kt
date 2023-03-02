package ShortestPath

import java.io.BufferedReader
import java.io.InputStreamReader


// 정확한 순위 - K대회
private const val INF = 1000000000
fun main(){

    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, m) = br.readLine().split(" ").map{it.toInt()}

    val table = Array(n+1){ IntArray(n+1){ INF } }

    for(i in 1..n){
        for(j in 1..n){
            if(i == j) table[i][j] = 0
        }
    }

    for(i in 0 until m){
        val (a, b) = br.readLine().split(" ").map{it.toInt()} // a번 학생의 성적이 b번 학생보다 낮다
        table[a][b] = 1
    }

    for(k in 1..n){
        for(a in 1..n){
            for(b in 1..n){
                table[a][b] = minOf(table[a][b], table[a][k] + table[k][b])
            }
        }
    }

    for(i in 1..n){
        for(j in 1..n){
            print(table[i][j])
            print(" ")
        }
        println()
    }
    br.close()

} // end of ShortestPath.main()
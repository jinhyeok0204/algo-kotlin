package ShortestPath

import java.io.BufferedReader
import java.io.InputStreamReader

// boj1956 운동

private const val INF = 1000000000
fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))

    val (v, e) = br.readLine().split(" ").map{it.toInt()}
    val graph = Array(v+1){IntArray(v+1){ INF } }

    for(i in 1 .. v){
        for( j in 1 .. v){
            if(i == j){graph[i][j] = 0}
        }
    }

    for(i in 0 until e){
        val (a, b, c) = br.readLine().split(" ").map{it.toInt()}
        graph[a][b] = c
    }

    for(k in 1..v){
        for(a in 1..v){
            for(b in 1..v){
                graph[a][b] = minOf(graph[a][b], graph[a][k] + graph[k][b])
            }
        }
    }

    var cycleValue = INF
    for(i in 1..v){
        for(j in 1..v){
            if(graph[i][j] in 1 until INF && graph[j][i] in 1 until INF){
                cycleValue = minOf(cycleValue, graph[i][j] + graph[j][i])
            }
        }
    }
    if (cycleValue == INF) {println(-1)}
    else                   {println(cycleValue)}

    br.close()

}// end of ShortestPath.main()
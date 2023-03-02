package Greedy

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val s  = br.readLine().split("").map{it.toInt()}
    var result = s[0]

    for(i in 1 until s.size - 1){
        val num = s[i]
        if (num <= 1 || result <= 1){
            result += num
        }
        else{
            result *= num
        }
    }

    println(result)
    br.close()
}
package Greedy

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(){
    var br = BufferedReader(InputStreamReader(System.`in`))

    val s : String = br.readLine()

    var now = '4'

    var oneCount = 0
    var zeroCount = 0

    for(i in s.indices){
        if (s[i] != now){
            if(s[i] == '0'){
                zeroCount++
                now = '0'
            }
            else{
                oneCount++
                now = '1'
            }
        }
    }


    val result = minOf(oneCount, zeroCount)
    println(result)
    br.close()
}
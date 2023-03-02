package Greedy

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, m, k) = br.readLine().split(" ").map{it.toInt()}
    // 주어진 수들을 m번 더하여 가장 큰 수를 만드는 법칙, 한 인덱스를 k번 초과하여 연속해서 더할 수 없음

    val numbers = br.readLine().split(" ").map{it.toInt()}.sortedDescending()


    val a:Int = m / k
    val b = m % k
    val biggest = numbers[0]
    val second = numbers[1]

    val result = (biggest * k) * a + (second * b)
    println(result)

    br.close()
}
package Greedy

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.PriorityQueue

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))

    val n = br.readLine().toInt()

    val q = PriorityQueue<Meeting>()
    for(i in 0 until n){
        val (start , end) = br.readLine().split(" ").map{it.toInt()}
        q.add(Meeting(start, end))
    }

    var beforeEnd = 0
    var count = 0

    for(i in 0 until n){
        val newMeeting = q.poll()
        if (beforeEnd <= newMeeting.start){
            count++
            beforeEnd = newMeeting.end
        }

    }

    println(count)
    br.close()
}

data class Meeting(val start: Int, val end: Int):Comparable<Meeting>{
    override fun compareTo(other: Meeting): Int {

        return if(end == other.end) {return start - other.start}
        else{end - other.end}
    }
}
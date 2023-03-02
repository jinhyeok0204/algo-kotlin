package ShortestPath

fun main(args: Array<String>) {
    val n = readln()!!.toInt()
    val numList = readln()!!.split(" ").map{i:String -> i.toInt()}.sorted()
    val sum = readln()!!.toInt()

    var count = 0
    var start = 0
    var end = n - 1

    while(start < end){
        val result = numList[start] + numList[end]

        when {
            result == sum -> {
                count++
                end--
            }
            result < sum -> {start++}
            else -> {end--}
        }
    }
    print(count)
}

fun fibonacci() {
    (1..20).forEach { i: Int -> print("${getFibonacci(i)}${if (i == 20) "" else ","} ") }
}

fun getFibonacci(value: Int): Int {
    if (value == 1) {
        return 0
    }
    if (value == 2) {
        return 1
    }

    return getFibonacci(value - 1) + getFibonacci(value - 2)
}

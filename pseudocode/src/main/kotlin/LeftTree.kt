fun generateLeftTree(num: Int) {
    (1..num).forEach { i: Int -> println(
        (1..i).joinToString("") { "*" }
    )  }
}
fun numberTranslation(num: Int) {

    if (num <= 2000) {
        println("Angka harus lebih besar dari 2000")
        return
    }

    if (num > 9999) {
        println("Angka harus lebih kecil dari 9999")
        return
    }

    println(translate(num))
}

fun translate(num: Int): String {
    val hundreds = getHundreds(num)
    val tens = getTens(num)
    println(num % 100)
    return numMapping(num / 1000) + " Ribu " + hundreds + tens + numMapping(num % 100)
}

fun getTens(num: Int): String {
    val tens = num % 100
    return if (tens == 10) {
        "Sepuluh "
    } else if (tens == 11) {
        "Sebelas "
    } else if (tens in 11..19) {
        "${numMapping(tens % 10)} Belas "
    } else if (tens / 10 != 0) {
        "${numMapping(tens / 10)} Puluh ${numMapping(tens % 10)}"
    } else {
        ""
    }
}

fun getHundreds(num: Int): String {
    return if (num % 1000 == 100 || (num % 1000) / 100 == 1) {
        "Seratus "
    } else if ((num % 1000) / 100 != 0) {
        "${numMapping((num % 1000) / 100)} Ratus "
    } else {
        ""
    }
}


fun numMapping(c: Int): String {
    return when (c) {
        1 -> "Satu"
        2 -> "Dua"
        3 -> "Tiga"
        4 -> "Empat"
        5 -> "Lima"
        6 -> "Enam"
        7 -> "Tujuh"
        8 -> "Delapan"
        9 -> "Sembilan"
        else -> ""
    }
}

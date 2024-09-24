fun progressionNumber(){
    for (value in 50..100 step 5){
        when{
            value <= 60 -> println("Nilai $value -> KURANG")
            value in 61..70 -> println("Nilai $value -> CUKUP")
            value in 71..80 -> println("Nilai $value -> BAIK")
            else -> println("Nilai $value -> LUAR BIASA")
        }
    }
}
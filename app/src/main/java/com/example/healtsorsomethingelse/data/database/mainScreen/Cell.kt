package com.example.healtsorsomethingelse.data.database.mainScreen

sealed class Cell () {
    //data class Topic(val topicType: Int)
    /**
     * List type
     */
    data class SubRecyclerCell(val listType: Int, val content: List<SubCell>) : Cell()
    data class ChapterCell(
        val id: Int,
        val topic: String,
        val imageUrl: String
    ) : Cell()
}

sealed class SubCell {

    data class RecyclerSubCell(
        val id: Int,
        val topic: String,
        val imageUrl: String
    ) : SubCell()
/*
    data class ChapterCell(
        val id: Int,
        val topic: String,
        val icon: String,
    )*/

    /*data class Popular(
        val id: Int,
        val topic: String,
        val imageUrl: String
    ) : SubCell()

    //Продумать загрузку файлов.
    data class Workout(
        val workoutType: Int,
        val topic: String,
        val imageFile: String
    ) : SubCell()*/
}

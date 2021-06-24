package com.example.healtsorsomethingelse.data.database.mainScreen

sealed class Cell {
    //data class Topic(val topicType: Int)
    /**
     * List type
     * 1 - Recommendation
     * 2 - Popular
     * 3 - Workout
     */
    data class Content(val listType: Int, val content: List<SubCell>)
}

sealed class SubCell {

    data class Recommendation(
        val id: Int,
        val topic: String,
        val imageUrl: String
    ) : SubCell()

    data class Popular(
        val id: Int,
        val topic: String,
        val imageUrl: String
    ) : SubCell()

    //Продумать загрузку файлов.
    data class Workout(
        val workoutType: Int,
        val topic: String,
        val imageFile: String
    ) : SubCell()
}

package com.example.healtsorsomethingelse.data.database.mainScreen

/**
 * Class that contain data received from server for main page in database.
 * There are few type of class.
 * 1. Block.
 * 2. List
 */
sealed class UserDatabaseContent {

    /**
     * Data class for block cell
     * @param id block id to handle click
     * @param imageUrl image that need to be downloaded
     * @param topic
     */
    data class Block(
        val id: Int,
        val imageUrl: String,
        val topic: String,
    ) : UserDatabaseContent()

    /**
     * Data class for content list.
     * e.g. Favorite, workouts, recipes.
     * @param typeId Type of list.
     * 1 - Special, 2 - Popular, 3 - Workouts, 4 - Recipes.
     * @param topic
     * @param content list of items
     */
    data class ContentList(
        val typeId: Int,
        val topic: String,
        val content: List<ContentItem>
    ) : UserDatabaseContent()
}


/**
 * Contain main data for list cell
 * @param itemId
 * @param foregroundImage Image that use see on the screen
 * @param itemType type of cell. Could be 1 - recipe, 2 - workout, 3 - advice.
 * @param subTitle Cell description
 */
data class ContentItem(
    val itemId: Int,
    val itemType: Int,
    val foregroundImage: String,
    val subTitle: String
)
package com.uds.pautas.model

import com.uds.pautas.util.StatusEnum

data class Schedule(
    var title: String,
    var description: String,
    var detail: String,
    var author: String
) {
    var id: Int? = null
    var status: StatusEnum = StatusEnum.OPEN
    var user: User? = null

    constructor() : this("", "", "", "")

    companion object {
        const val TABLE_NAME = "shedule"
        const val COLUMN_ID = "id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_DETAIL = "detail"
        const val COLUMN_AUTHOR = "author"
        const val COLUMN_STATUS = "status"
        const val COLUMN_USER_ID = "user_id"
    }

    fun validInsert(): Boolean {
        title = title.trim()
        description = description.trim()
        detail = detail.trim()
        author.trim()
        return title.isNotEmpty() && description.isNotEmpty() && detail.isNotEmpty() && author.isNotEmpty()
    }

}
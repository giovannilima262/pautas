package com.uds.pautas.model

data class User(
    var id: Int,
    var email: String,
    var password: String
) {
    constructor() : this(0, "", "")
    companion object {
        const val TABLE_NAME = "user"
        const val COLUMN_ID = "id"
        const val COLUMN_PASSWORD = "password"
        const val COLUMN_EMAIL = "email"
    }
}
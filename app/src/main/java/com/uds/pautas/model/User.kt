package com.uds.pautas.model

data class User(
    var name: String,
    var email: String,
    var password: String
) {
    var id: Int? = null

    constructor() : this("", "", "")
    constructor(email: String, password: String) : this("", email, password)
    constructor(email: String): this("", email, "")

    companion object {
        const val TABLE_NAME = "user"
        const val COLUMN_ID = "id"
        const val COLUMN_PASSWORD = "password"
        const val COLUMN_EMAIL = "email"
    }

    fun validSingup(): Boolean {
        return name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()
    }

    fun validLogin(): Boolean {
        return email.isNotEmpty() && password.isNotEmpty()
    }

    fun validLogged(): Boolean {
        return id != null
    }
}
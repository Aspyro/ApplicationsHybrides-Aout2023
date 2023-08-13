package dev.aspyro.androidapplication.databaseroom

class User(i: Int) {

    var id: Int = 0
        get
        private set

    var email: String = "null"

    var pwd: String = "null"
        get
        private set


    constructor(i: Int, e: String, p: String) : this(i) {
        id = i
        email = e
        pwd = p
    }

    override fun toString(): String {
        val sb = StringBuilder()

        sb.append("ID : $id\nEmail : $email\nPassword : $pwd\n")

        return sb.toString()
    }
}
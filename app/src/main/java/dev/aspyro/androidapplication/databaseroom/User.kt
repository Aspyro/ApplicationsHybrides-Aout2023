package dev.aspyro.androidapplication.databaseroom

class User(i: Int) {

    var id: Int = 0
        get
        private set

    var login = "null"
        get
        private set

    var pwd: String = "null"
        get
        private set

    var email: String = "null"

    constructor(i: Int, l: String, p: String, e: String) : this(i) {
        id = i
        login = l
        pwd = p
        email = e
    }

    override fun toString(): String {
        val sb = StringBuilder()

        sb.append("ID : $id\nLogin : $login\nPassword : $pwd\nEmail : $email")

        return sb.toString()
    }
}
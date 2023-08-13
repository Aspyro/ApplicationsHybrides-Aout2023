package dev.aspyro.androidapplication.databaseroom

class User(i: Int) {

    var id: Int = 0
        get
        private set

    var email: String = "null"
        get
        private set

    var pwd: String = "null"
        get
        private set

    var access: Int = 0
        get
        private set


    constructor(i: Int, e: String, p: String, a: Int) : this(i) {
        id = i
        email = e
        pwd = p
        access = a
    }

    override fun toString(): String {
        val sb = StringBuilder()

        sb.append("ID : $id\nEmail : $email\nPassword : $pwd\nAccess : $access")

        return sb.toString()
    }
}
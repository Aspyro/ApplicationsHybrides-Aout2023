package dev.aspyro.androidapplication.databaseroom

class Asset(i: Int) {

    var id: Int = 0
        get
        private set

    var hardware: String = "null"
        get
        private set

    var brand: String = "null"
        get
        private set

    var model: String = "null"
        get
        private set

    var reference: String = "null"
        get
        private set

    var status: String = "null"
        get
        private set

    constructor(i: Int, h: String, b: String, m: String, r: String, s: String) : this(i) {
        id = i
        hardware = h
        brand = b
        model = m
        reference = r
        status = s
    }

    override fun toString(): String {
        val sb = StringBuilder()

        sb.append("ID : $id\n")
            .append("Hardware : $hardware\n")
            .append("Brand : $brand\n")
            .append("Model : $model\n")
            .append("Reference : $reference\n")
            .append("Status : $status")

        return sb.toString()
    }
}
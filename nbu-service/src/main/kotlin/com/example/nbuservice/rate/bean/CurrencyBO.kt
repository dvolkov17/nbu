package com.example.nbuservice.rate.bean

import java.util.*

class CurrencyBO {

    val updated: Date?
    var rate: Double
    val alias: String

    constructor(alias: String, rate: Double) {
        this.alias = alias
        this.rate = rate
        this.updated = null
    }

    constructor(alias: String, rate: Double, updated: Date?) {
        this.alias = alias
        this.rate = rate
        this.updated = updated
    }

}

package com.example.nbuservice.rate.exception

class CurrencyNotFound : Exception {

    constructor() : super()

    constructor(message: String) : super(message)
}

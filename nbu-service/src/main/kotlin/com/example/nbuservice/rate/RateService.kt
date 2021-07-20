package com.example.nbuservice.rate

import com.example.nbuservice.rate.bean.CurrencyBO

interface RateService {

    fun getRates() : List<CurrencyBO>

    fun getRate(alias : String) : CurrencyBO

    fun updateCurrenciesRates()

    fun saveOrUpdateCurrency(currency: CurrencyBO)


}

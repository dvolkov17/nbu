package com.example.nbuservice.rate.schedule

import com.example.nbuservice.rate.RateService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class UpdateCurrenciesRatesTask ()  {

    @Autowired
    private lateinit var rateService: RateService


    @Scheduled(cron = "0 0 17 * * *", zone = "Europe/Kiev")
    fun updateCurrenciesRates() {
        rateService.updateCurrenciesRates()
    }
}

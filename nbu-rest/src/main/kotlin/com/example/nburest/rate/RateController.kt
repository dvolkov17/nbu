package com.example.nburest.rate

import com.example.nburest.rate.bean.CurrencyRO
import org.springframework.beans.factory.annotation.Autowired
import com.example.nbuservice.rate.RateService
import com.example.nbuservice.rate.bean.CurrencyBO
import com.example.nbuservice.rate.exception.CurrencyNotFound
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class RateController {

    @Autowired
    private lateinit var rateService: RateService

    @GetMapping("/rates")
    fun getRates(): List<CurrencyBO> {
        return rateService.getRates()
    }

    @GetMapping("/rate/{alias}")
    fun getRate(@PathVariable alias: String): ResponseEntity<Any> {
        try {
            return ResponseEntity.ok().body(rateService.getRate(alias))
        } catch (e: CurrencyNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message)
        }
    }

    @PostMapping("/rate")
    fun saveOrUpdateCurrency(@RequestBody request: CurrencyRO): ResponseEntity<Any>  {
        try {
            if(request.alias.isBlank() || request.alias.length > 3) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Alias is blank or contains more than 3 chars")
            }
            rateService.saveOrUpdateCurrency(CurrencyBO(request.alias, request.rate))
            return ResponseEntity.ok().build()
        }  catch (e: Exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message)
        }

    }

}

package com.example.nbuservice.rate

import com.example.nbupersistence.rate.RateRepository
import com.example.nbuservice.rate.bean.NbuCurrency
import com.example.nbuservice.rate.bean.CurrencyBO
import com.example.nbuservice.rate.bean.RateObjectMapper
import com.example.nbuservice.rate.exception.CurrencyNotFound
import com.github.kittinunf.fuel.httpGet
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class RateServiceImpl : RateService {

    val BASE_NBU_CURRENCY_URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?valcode=";

    @Autowired
    private lateinit var repository: RateRepository

    override fun getRates(): List<CurrencyBO> {
        return RateObjectMapper.mapToBO(repository.findAll());
    }

    @Throws(CurrencyNotFound::class)
    override fun getRate(alias: String): CurrencyBO {
        val optionalRate = repository.findById(alias.toUpperCase())
        if (!optionalRate.isPresent) {
            throw CurrencyNotFound("Currency with alias {$alias} not found")
        }
        return RateObjectMapper.mapToBO(optionalRate.get());
    }

    override fun updateCurrenciesRates() {
        val currenciesToUpdate: List<CurrencyBO> = getRates()

        for (rate in currenciesToUpdate) {
            val nbuCurrency = getCurrencyFromNBU(rate.alias)
            if (nbuCurrency.isNotEmpty()) {
                rate.rate = nbuCurrency[0].rate
            }
        }

        repository.saveAll(RateObjectMapper.mapToEntity(currenciesToUpdate))
    }

    override fun saveOrUpdateCurrency(currency: CurrencyBO) {
        repository.save(RateObjectMapper.mapToEntity(currency))
    }

    private fun getCurrencyFromNBU(alias: String): ArrayList<NbuCurrency> {
        val URL = getNbuCurrencyUrl(alias)
        val nbuRatesToRet = ArrayList<NbuCurrency>()

        val (request, response, result) = URL
                .httpGet()
                .responseObject(NbuCurrency.Deserializer())

        val (nbuCurrencies, err) = result

        nbuCurrencies?.forEach { nbuCurrency ->

            nbuRatesToRet.add(nbuCurrency)
        }

        return nbuRatesToRet
    }

    private fun getNbuCurrencyUrl(alias: String): String {
        return "$BASE_NBU_CURRENCY_URL$alias&json"
    }


}

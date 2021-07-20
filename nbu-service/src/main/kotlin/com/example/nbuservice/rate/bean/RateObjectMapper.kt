package com.example.nbuservice.rate.bean

import com.example.nbupersistence.rate.bean.RateEntity
import kotlin.streams.toList

class RateObjectMapper {

    companion object {
        fun mapToBO(toMap : List<RateEntity>) : List<CurrencyBO> {
            return toMap.stream().map { e -> mapToBO(e) }.toList()
        }

        fun mapToBO(toMap : RateEntity) : CurrencyBO {
            return CurrencyBO(toMap.alias, toMap.rate, toMap.updated)
        }

        fun mapToEntity(toMap : List<CurrencyBO>) : List<RateEntity> {
            return toMap.stream().map { e -> mapToEntity(e) }.toList()
        }

        fun mapToEntity(toMap : CurrencyBO) : RateEntity {
            return RateEntity(toMap.alias, toMap.rate, toMap.updated)
        }
    }


}

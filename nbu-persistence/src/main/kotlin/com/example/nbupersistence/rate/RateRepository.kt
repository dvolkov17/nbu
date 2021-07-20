package com.example.nbupersistence.rate

import com.example.nbupersistence.rate.bean.RateEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.util.*

@Component
interface RateRepository : JpaRepository<RateEntity, String> {


}

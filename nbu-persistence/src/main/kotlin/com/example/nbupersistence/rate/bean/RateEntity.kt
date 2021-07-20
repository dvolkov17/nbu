package com.example.nbupersistence.rate.bean

import org.hibernate.annotations.UpdateTimestamp
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "currency")
class RateEntity(
        @Id
        @Column(nullable = false)
        val alias: String,

        @Column()
        val rate: Double,

        @UpdateTimestamp
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name = "updated")
        val updated: Date?


)

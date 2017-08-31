package fr.efaya.statmyteam.domain

import org.springframework.data.annotation.Id
import java.util.*

open class CommonObject {
    @Id
    val id : String = UUID.randomUUID().toString()
}
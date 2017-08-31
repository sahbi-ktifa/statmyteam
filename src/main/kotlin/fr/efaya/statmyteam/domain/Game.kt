package fr.efaya.statmyteam.domain

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*
import kotlin.collections.HashSet

@Document(collection = "games")
class Game : CommonObject() {
    val date : Date = Date()
    val sequences : Set<String> = HashSet()
    val team: String? = null
    val scoreTeam : Int = 0
    val opponent: String? = null
    val opponentTeam : Int = 0

}

@Repository
interface GameRepository : MongoRepository<Game, String>
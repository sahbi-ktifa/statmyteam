package fr.efaya.statmyteam

import fr.efaya.statmyteam.domain.Game
import fr.efaya.statmyteam.domain.GameRepository
import fr.efaya.statmyteam.domain.Sequence
import fr.efaya.statmyteam.domain.SequenceRepository
import org.springframework.http.HttpStatus
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class StatMyTeamWebServiceController(val gameRepository : GameRepository, val sequenceRepository: SequenceRepository) {

    @GetMapping("/games")
    fun retrieveGames(): List<Game> = gameRepository.findAll()

    @GetMapping("/games/{id}")
    fun retrieveGame(@PathVariable id:String): Game {
        val game = gameRepository.findById(id) ?: throw GameNotFoundException("No game found with id : $id.")
        return game.get()
    }

    @GetMapping("/sequences/{id}")
    fun retrieveSequence(@PathVariable id:String): Optional<Sequence>? = sequenceRepository.findById(id)

    @PostMapping("/games/{id}/sequence")
    @Transactional
    fun addSequenceToGame(@PathVariable id: String, @RequestBody sequence: Sequence) {
        val game = retrieveGame(id)
        sequenceRepository.save(sequence)
        game.sequences.plus(sequence.id)
        gameRepository.save(game)
    }

}

class ErrorMessage(val msg: String, val date: Date)

@RestControllerAdvice
class ApiWebServiceAdviceController {

    @ResponseStatus(HttpStatus.NOT_FOUND)  // 404
    @ExceptionHandler(GameNotFoundException::class)
    fun handleNotFound(exception: GameNotFoundException): ErrorMessage {
        return ErrorMessage(exception.message ?: "No game found.", Date())
    }
}
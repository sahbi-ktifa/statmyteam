package fr.efaya.statmyteam

import fr.efaya.statmyteam.domain.Game
import fr.efaya.statmyteam.domain.GameRepository
import fr.efaya.statmyteam.domain.Sequence
import fr.efaya.statmyteam.domain.SequenceRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class StatMyTeamWebServiceControllerTest {
    @Mock lateinit var gameRepository: GameRepository
    @Mock lateinit var sequenceRepository: SequenceRepository

    @InjectMocks lateinit var controller : StatMyTeamWebServiceController

    @Test(expected = GameNotFoundException::class)
    fun useRepoButGameNotFound() {
        controller.addSequenceToGame("23", fr.efaya.statmyteam.domain.Sequence())
    }

    @Test
    fun useRepo() {
        Mockito.`when`(gameRepository.findById("1")).thenReturn(Optional.of(Game()))

        controller.addSequenceToGame("1", fr.efaya.statmyteam.domain.Sequence())

        Mockito.verify(gameRepository, Mockito.times(1)).findById(ArgumentMatchers.eq("1"))
        Mockito.verify(gameRepository, Mockito.times(1)).save(ArgumentMatchers.any(Game::class.java))
        Mockito.verify(sequenceRepository, Mockito.times(1)).save(ArgumentMatchers.any(Sequence::class.java))
    }

}

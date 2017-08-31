package fr.efaya.statmyteam.domain

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Document(collection = "sequences")
class Sequence : CommonObject() {
    val time : Date = Date()
    val parts : Set<SequencePart> = HashSet()
}

open class SequencePart
open class SequencePartInitializer : SequencePart()
open class SequencePartFinalizer : SequencePart()

class KickOff : SequencePartInitializer()
class ThrowIn : SequencePartInitializer()
class Corner : SequencePartInitializer()
class Penalty : SequencePartInitializer()
class FreeKick : SequencePartInitializer()
class GoalKick : SequencePartInitializer()

class ShootOnTarget : SequencePartFinalizer()
class ShootFromOutside : SequencePartFinalizer()
class BallLost : SequencePartFinalizer()
class Goal : SequencePartFinalizer()
class OffSide : SequencePartFinalizer()
class HalfTime : SequencePartFinalizer()
class EndOfGame : SequencePartFinalizer()


class DefensivePlay : SequencePartInitializer()
class Possession(val player : String) : SequencePart()

@Repository
interface SequenceRepository : MongoRepository<Sequence, String>

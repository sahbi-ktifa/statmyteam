package fr.efaya.statmyteam

open class StatMyTeamException(msg : String) : Throwable()

class GameNotFoundException(msg :String) : StatMyTeamException(msg)
class SequenceInvalidException(msg : String) : StatMyTeamException(msg)

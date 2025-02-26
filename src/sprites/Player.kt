package sprites

import java.awt.Point

// I have no idea what im doing honestly yet
// was watching sorting algorithm videos while doing this

class Player(startPos:Point, startHealth:Int) : GameObject() {
    var health = startHealth

    init {
        this.pos = startPos

    }

    fun damage(){
        TODO("um i got hungry will make later")
    }



}
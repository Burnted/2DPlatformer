package sprites

import java.awt.Point

class Enemy (startX: Int, startY: Int) : DynamicGameObject(startX, startY, "testImg.png"){
    fun idle(){
        TODO("Add idle movement")
    }

    override fun jump() {
        TODO("Not yet implemented")
    }

}
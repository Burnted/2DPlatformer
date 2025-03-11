package sprites

import GamePanel
import java.awt.Graphics2D
import java.awt.Point
import java.awt.event.KeyEvent
import java.awt.image.ImageObserver

// I have no idea what im doing honestly yet
// was watching sorting algorithm videos while doing this

class Player(startX: Int, startY: Int) : DynamicGameObject(startX, startY, "testImg.png") {

    override fun jump() {
        val jumpStrength = 600.0
        if (isOnGround) {
            this.y -= 1
            verticalVelocity = -jumpStrength
            isOnGround = false
        }
    }

}
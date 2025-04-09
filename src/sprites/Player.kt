package sprites

import GamePanel
import java.awt.Graphics2D
import java.awt.Point
import java.awt.event.KeyEvent
import java.awt.image.ImageObserver
import kotlin.math.abs

// I have no idea what im doing honestly yet
// was watching sorting algorithm videos while doing this

class Player(startX: Int, startY: Int) : DynamicGameObject(startX, startY, "testImg.png") {
    var shifted = false

    fun move(strength: Double) {
        horizontalVelocity = if (shifted) {
            strength / abs(strength) * 100.0
        } else {
            strength
        }
    }
    override fun jump() {
        val jumpStrength = 500.0
        if (isOnGround) {
            this.y -= 1
            verticalVelocity = -jumpStrength
            isOnGround = false
        }
    }

}
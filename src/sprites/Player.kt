package sprites

import GamePanel
import java.awt.Point
import java.awt.event.KeyEvent

// I have no idea what im doing honestly yet
// was watching sorting algorithm videos while doing this

class Player(startPos: Point) : DynamicGameObject(startPos, "testImg.png") {



    fun keyPressed(e: KeyEvent) {
        when (e.keyCode) {
            KeyEvent.VK_SPACE -> jump(10.0)
            KeyEvent.VK_UP -> jump(15.0)
            KeyEvent.VK_RIGHT -> {

                applyHorizontalPush(1.0)
                this.isMoving = true

            }

            KeyEvent.VK_LEFT -> {

                applyHorizontalPush(-1.0)
                this.isMoving = true

            }
        }
    }

    fun keyReleased() {
        this.isMoving = false
    }


    fun damage() {
        TODO("um i got hungry will make later")
    }


    fun update() {
        this.applyFriction()
        this.calcProjectileMotion()
        hitbox = pos.x..pos.x+GamePanel.TILE_SIZE
    }


}
package sprites

import GamePanel
import java.awt.Point
import java.awt.event.KeyEvent

// I have no idea what im doing honestly yet
// was watching sorting algorithm videos while doing this

class Player(startPos:Point) : DynamicGameObject(startPos, "testImg.png") {

//    override fun checkYCollision(posY:Int): Boolean {
//        return posY >= GamePanel.HEIGHT - GamePanel.TILE_SIZE
//    }

//    private fun calcProjectileMotion() {
//        if(this.checkYCollision(pos.y)) {
//            verticalVelocity = 0.0
//            pos.y = GamePanel.HEIGHT - GamePanel.TILE_SIZE
//            println("COLLISION")
//            return
//        }
//
//        println("x: ${pos.x}, y: ${pos.y}")
//
//        pos.y = (pos.y+verticalVelocity).toInt()
//        verticalVelocity += gravity
//    }

    fun keyPressed(e: KeyEvent) {
        when (e.keyCode) {
            KeyEvent.VK_SPACE -> jump(10.0)
            KeyEvent.VK_RIGHT -> {
                this.isMoving = true
                applyHorizontalPush(5.0)

            }
            KeyEvent.VK_LEFT -> {
                this.isMoving = true
                applyHorizontalPush(-5.0)

            }
        }
    }

    fun keyReleased(e: KeyEvent) {
        this.isMoving = false
    }


    fun damage(){
        TODO("um i got hungry will make later")
    }



    fun update(){
        this.applyFriction()
        this.calcProjectileMotion()
    }







}
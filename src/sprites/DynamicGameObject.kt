package sprites

import GamePanel
import java.awt.Color
import java.awt.Graphics2D
import java.awt.Point
import java.awt.Rectangle
import java.awt.image.BufferedImage
import java.awt.image.ImageObserver
import java.io.IOException
import javax.imageio.ImageIO
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

/*
These objects can move and are affected by gravity
 */

open class DynamicGameObject(startX: Int, startY: Int, img: String) : GameObject(startX, startY, img) {
    var verticalVelocity = 0.0
    var horizontalVelocity = 0.0
    var isMoving = false
    var isOnGround = false


    var previousX = startX
    var previousY = startY
    var collisionCheckBounds = Rectangle()


    open fun jump(){
        val jumpStrength = 600.0
        if (isOnGround) {
            this.y -= 1
            verticalVelocity = -jumpStrength
            isOnGround = false
        }
    }

    fun update(deltaTime: Double) {
        previousX = x
        previousY = y

        x += (horizontalVelocity * deltaTime).roundToInt()  // Move horizontally with delta time
        y += (verticalVelocity* deltaTime).roundToInt()  // Move vertically with delta time

        // Check for ground collision and stop falling
        if (y >= GamePanel.HEIGHT - GamePanel.TILE_SIZE) {
            y = GamePanel.HEIGHT - GamePanel.TILE_SIZE
            verticalVelocity = 0.0
            isOnGround = true
        }
        bounds.setLocation(x, y)
        collisionCheckBounds.setFrameFromCenter(bounds.centerX, bounds.centerY,bounds.x-50.0, bounds.y-50.0)
    }

    override fun render(g2d: Graphics2D, renderX: Int, renderY: Int, observer: ImageObserver) {
        //g2d.drawImage(super.image, renderX, renderY, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, observer)
        g2d.color= Color.BLUE
        g2d.fillRect(renderX, renderY, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE)
        g2d.color= Color.red
        g2d.draw(collisionCheckBounds)
    }
}
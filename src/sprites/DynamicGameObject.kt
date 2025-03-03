package sprites

import GamePanel
import java.awt.Graphics2D
import java.awt.Point
import java.awt.Rectangle
import java.awt.image.BufferedImage
import java.awt.image.ImageObserver
import java.io.IOException
import javax.imageio.ImageIO
import kotlin.math.absoluteValue

/*
These objects can move and are affected by gravity
 */

open class DynamicGameObject(var pos:Point, private val img: String) {
    private val gravity = 0.5
    private val frictionConstant = 0.5
    var verticalVelocity = 0.0
    var horizontalVelocity = 0.0
    var isMoving = false
    var previousPosition = Point(pos.x, pos.y)


    private var image:BufferedImage? = null
    private val tileSize = GamePanel.TILE_SIZE

    var bounds = Rectangle(pos.x,pos.y,tileSize,tileSize)

    init {
        getBufferedImageFromStream()
    }

    private fun checkYCollision(posY:Int): Boolean {
        return posY >= GamePanel.HEIGHT - GamePanel.TILE_SIZE
    }


    fun jump(strength: Double){
        if (checkYCollision(pos.y)){
            pos.y -=1
            verticalVelocity = -strength
        }
    }

    fun applyHorizontalPush(strength:Double) {
        pos.x += (0.01*strength.absoluteValue/strength).toInt()
        horizontalVelocity = strength
    }

    protected fun applyFriction() {
        if (isMoving){
            return
        }
        if (!checkYCollision(pos.y)){
            return
        }

        if (horizontalVelocity in -frictionConstant/2..frictionConstant/2){
            return
        }

        if (horizontalVelocity > frictionConstant/2) {
            horizontalVelocity -= frictionConstant

        }
        else if (horizontalVelocity < -frictionConstant/2){
            horizontalVelocity += frictionConstant
        }
    }

    // gravity controlled motion, is always checked
    protected fun calcProjectileMotion() {
        previousPosition.move(pos.x, pos.y)

        pos.x += horizontalVelocity.toInt()
        if(checkYCollision(pos.y)) {
            verticalVelocity = 0.0
            pos.y = GamePanel.HEIGHT - GamePanel.TILE_SIZE

            return
        }

        pos.y = (pos.y+verticalVelocity).toInt()

        verticalVelocity += gravity
    }


    fun render(g2d: Graphics2D, observer: ImageObserver) {
        g2d.drawImage(image, pos.x, pos.y,tileSize,tileSize, observer)
    }

    private fun getBufferedImageFromStream() {
        val classLoader = javaClass.classLoader
        val inputStream = classLoader.getResourceAsStream(img)

        // the stream holding the file content
        requireNotNull(inputStream != null) { "file not found! $img" }

        try {
            image = ImageIO.read(inputStream)
        } catch (ignored: IOException) {
        }
    }
}
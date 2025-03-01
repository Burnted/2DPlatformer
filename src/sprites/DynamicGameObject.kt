package sprites

import GamePanel
import java.awt.Graphics2D
import java.awt.Point
import java.awt.image.BufferedImage
import java.awt.image.ImageObserver
import java.io.IOException
import javax.imageio.ImageIO

open class DynamicGameObject(var pos:Point, private val img: String) {

    private var image:BufferedImage? = null
    private val tileSize = GamePanel.TILE_SIZE


    var verticalVelocity = 0.0
    var horizontalVelocity = 0.0
    private val gravity = 0.5
    private val frictionConstant = 0.5


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

    fun moveHorizontal(){

    }

    fun applyHorizontalPush(strength:Double) {
        horizontalVelocity += strength
    }

    protected fun applyFriction() {
        if (!checkYCollision(pos.y)){
            return
        }
        if (horizontalVelocity in -frictionConstant/2..frictionConstant/2){
            return
        }

        if (horizontalVelocity > frictionConstant/2) {
            pos.x = (pos.x + horizontalVelocity).toInt()
            horizontalVelocity -= frictionConstant

        }
        else if (horizontalVelocity < -frictionConstant/2){
            pos.x = (pos.x + horizontalVelocity).toInt()
            horizontalVelocity += frictionConstant
        }
        println("pos: ${pos.x},${pos.y}")
        println("velocityH: $horizontalVelocity")
    }

    // gravity controlled motion, is always checked
    protected fun calcProjectileMotion() {
        if(checkYCollision(pos.y)) {
            verticalVelocity = 0.0
            pos.y = GamePanel.HEIGHT - GamePanel.TILE_SIZE

            return
        }
        pos.y = (pos.y+verticalVelocity).toInt()
        pos.x += horizontalVelocity.toInt()
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
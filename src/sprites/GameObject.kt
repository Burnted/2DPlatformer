package sprites

import java.awt.Graphics2D
import java.awt.Point
import java.awt.Rectangle
import java.awt.image.BufferedImage
import java.awt.image.ImageObserver
import java.io.IOException
import javax.imageio.ImageIO

open class GameObject(var pos: Point, private val img: String) {
    private var image: BufferedImage? = null
    private val tileSize = GamePanel.TILE_SIZE

    var bounds = Rectangle(pos.x,pos.y,tileSize,tileSize)

    init {
        getBufferedImageFromStream()
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
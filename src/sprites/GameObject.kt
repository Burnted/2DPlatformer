package sprites

import GamePanel
import java.awt.Graphics2D
import java.awt.Point
import java.awt.Rectangle
import java.awt.image.BufferedImage
import java.awt.image.ImageObserver
import java.io.IOException
import javax.imageio.ImageIO

abstract class GameObject(var x: Int, var y: Int, private val img: String) {
    private var image: BufferedImage? = null
    private val tileSize = GamePanel.TILE_SIZE

    var bounds = Rectangle(x, y, tileSize, tileSize)

    init {
        getBufferedImageFromStream()
    }

    abstract fun render(g2d: Graphics2D, renderX:Int, renderY:Int, observer: ImageObserver,)

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
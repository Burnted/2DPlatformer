import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.JPanel
import javax.swing.Timer
import sprites.Player
import java.awt.*

class GamePanel : JPanel(), ActionListener, KeyListener {



    private var squareX = 0
    private var squareY = 5

    companion object {
        const val TILE_SIZE = 50
        const val WIDTH = 800
        const val HEIGHT = 100
    }


    private val timer = Timer(17, this)

    private val player = Player(Point(squareX,squareY))

    init {
        this.preferredSize = Dimension(WIDTH, HEIGHT)
        timer.start()
    }

    override fun paintComponent(g: Graphics?) {
        val g2d = g as Graphics2D
        super.paintComponent(g)
        player.render(g2d, this)

    }


    override fun actionPerformed(e: ActionEvent?) {

        player.update()
        repaint()
//        fall(newY)
//        var isFalling = true
//
//        if (checkFloorCollision(newY)) {
//            newY = HEIGHT - TILE_SIZE
//            isFalling = false
//
//        }
//
//        if (isFalling) {
//            newY = (newY+velocity).toInt()
//            velocity += gravity
//
//        }
    }


    override fun keyTyped(e: KeyEvent?) {

    }

    override fun keyPressed(e: KeyEvent?) {
        //println("someting happened")
        if (e != null) {
            player.keyPressed(e)
//            if (e.keyCode == KeyEvent.VK_SPACE && checkFloorCollision(player.pos.y)) {
//                velocity = -20.0
//                newY -= 5
//            }
        }
    }
    override fun keyReleased(e: KeyEvent?) {

    }


}
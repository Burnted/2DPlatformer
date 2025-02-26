import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.JPanel
import javax.swing.Timer
import kotlin.math.pow

class GamePanel : JPanel(), ActionListener, KeyListener {
    private val width = 500
    private val height = 1000

    private val gravity = 1 // m/s^2
    private var timeElapsed = 0.0 // in s
    private var fallTime = 0.0
    private var velocity = 0.0 // m/s

    private var squareX = 250
    private var squareY = 700

    private var squareW = 50
    private var squareH = 50

    private var newY = squareY

    private val timer = Timer(17, this)

    init {
        this.preferredSize = Dimension(width, height)
        timer.start()
    }


    override fun paintComponent(g: Graphics?) {
        super.paintComponent(g)
        val g2d = g as Graphics2D
        g2d.color = Color.black
        g2d.fillRect(squareX, squareY, squareW, squareH)
    }

    private fun fall(y: Int) {
        squareY = y
        repaint()
    }

    private fun checkFloorCollision(y: Int): Boolean {
        return y >= height-squareH
    }

    override fun actionPerformed(e: ActionEvent?) {

        fall(newY)
        var isFalling = true

        if (checkFloorCollision(newY)) {

            newY=height-squareH
            isFalling = false

        }

        if (isFalling) {
            newY = (newY+velocity).toInt()
            velocity += gravity

        }
    }


    override fun keyTyped(e: KeyEvent?) {

    }

    override fun keyPressed(e: KeyEvent?) {
        //println("someting happened")
        if (e != null) {
            if (e.keyCode == KeyEvent.VK_SPACE) {
                velocity = -20.0
                newY -= 5
            }
        }
    }
    override fun keyReleased(e: KeyEvent?) {

    }
}
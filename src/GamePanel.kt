import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JPanel
import javax.swing.Timer

class GamePanel: JPanel(), ActionListener {
    private val width = 500
    private val height = 1000

    var gravity = 1 // m/s^2
    var timeElapsed = 0.0 // in s
    var velocity = 0.0 // m/s

    var squareX = 250
    var squareY = 0

    var squareW = 50
    var squareH = 50

    var myY =0

    val timer = Timer(8, this)

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

    private fun fall(y:Int) {
        squareY = y
//        if (squareY >= height-squareH) {
//            squareY = height-squareH
//        }else{
//
//        }

        repaint()
    }

    override fun actionPerformed(e: ActionEvent?) {


        fall(myY)
        if (myY >= height-squareH ){
            velocity = -10.0
            myY = height-2*squareH


        }
        else{
            myY = (velocity * timeElapsed + myY).toInt()
            velocity += gravity * timeElapsed
            timeElapsed += timer.delay.toDouble()/1000
            println(velocity)
        }


    }

}
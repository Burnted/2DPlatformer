import physics.PhysicsEngine
import sprites.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.JPanel
import javax.swing.Timer
import java.awt.*
import kotlin.math.roundToInt

class GamePanel : JPanel(), KeyListener, Runnable {

    companion object {
        const val TILE_SIZE = 50
        const val WIDTH = 1500
        const val HEIGHT = 400
        private const val TPS = 30
        const val TIME_PER_TICK = 1_000_000_000L / TPS // Nanoseconds per tick
    }

    private var isRunning = false
    private var thread: Thread? = null
    private var fps = 0

    private var alpha = 1.0

    private val player = Player(100, 200)
    private val enemy = Enemy(400,100)

    //private val dynamicObjects = arrayListOf(player, enemy)

    private val floor1 = WorldObject(1.0f , 220,300, "testFloor.png")
    private val floor2 = WorldObject(0.5f, 400, 250, "testFloor.png")

    private val collisionObjects = arrayListOf(floor1, floor2)

    private val physicsEngine = PhysicsEngine(collisionObjects)

    init {
        this.preferredSize = Dimension(WIDTH, HEIGHT)
        start()
    }

    private fun start(){
        isRunning = true
        thread = Thread(this)
        thread!!.start()
    }

    private fun stop(){
        isRunning = false
        thread?.join()
    }

    override fun run() {
        var lastTime = System.nanoTime()
        var frames = 0
        var timer = System.currentTimeMillis()
        var deltaTime = 0.0

        while (isRunning) {
            val now = System.nanoTime()
            val elapsed = now - lastTime
            deltaTime += elapsed/TIME_PER_TICK.toDouble()
            lastTime = now

            if (deltaTime >= 1) {
                updateGame(deltaTime/60)
                deltaTime --
            }

            alpha = deltaTime

            repaint()
            frames++

            if (System.currentTimeMillis() - timer >= 1000) {
                fps = frames
                frames = 0
                timer += 1000
            }

            Thread.sleep(1)  // Prevent CPU overuse
        }
    }

    private fun updateGame(deltaTime: Double) {
        player.update(deltaTime)
        physicsEngine.update(player, deltaTime)  // Apply physics with delta time
    }

    override fun paintComponent(g: Graphics?) {
        val g2d = g as Graphics2D
        super.paintComponent(g)

        player.render(g2d, (player.previousX + (player.x - player.previousX) * alpha).roundToInt(), (player.previousY + (player.y - player.previousY) * alpha).roundToInt(),this)
        floor1.render(g2d, floor1.x, floor1.y,this)
        g2d.color = Color.BLACK
        g2d.drawString("FPS: $fps",10,10)
    }

    override fun keyTyped(e: KeyEvent?) {

    }

    override fun keyPressed(e: KeyEvent?) {
        if (e == null) {
            return
        }

        player.isMoving = true

        when(e.keyCode) {
            KeyEvent.VK_UP -> player.jump()
            KeyEvent.VK_SPACE -> player.jump()
            KeyEvent.VK_RIGHT -> player.horizontalVelocity = 300.0
            KeyEvent.VK_LEFT -> player.horizontalVelocity = -300.0
        }
    }

    override fun keyReleased(e: KeyEvent?) {
        player.isMoving = false
    }

}
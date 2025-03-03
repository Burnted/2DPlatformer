import sprites.DynamicGameObject
import sprites.Enemy
import sprites.GameObject
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.JPanel
import javax.swing.Timer
import sprites.Player
import java.awt.*

class GamePanel : JPanel(), ActionListener, KeyListener {

    companion object {
        const val TILE_SIZE = 50
        const val WIDTH = 1500
        const val HEIGHT = 300
    }

    private val playerX = 220
    private val playerY = 200

    private val timer = Timer(17, this)

    private val player = Player(Point(playerX, playerY))
    private val enemy = Enemy(Point(400, 100))

    private val dynamicObjects = arrayListOf(player, enemy)

    private val floor1 = GameObject(Point(220, 250), "testFloor.png")
    private val floor2 = GameObject(Point(400, 250), "testFloor.png")

    private val collisionObjects = hashSetOf(floor1, floor2)

    init {
        this.preferredSize = Dimension(WIDTH, HEIGHT)
        this.isDoubleBuffered = true
        timer.start()
    }

    override fun paintComponent(g: Graphics?) {
        val g2d = g as Graphics2D
        super.paintComponent(g)

        for (dynamicObject in dynamicObjects) {
            dynamicObject.render(g2d, this)
        }

        for (collisionObject in collisionObjects) {
            collisionObject.render(g2d, this)
        }
    }


    private fun checkXCollision(){
        for (dynamicObject in dynamicObjects) {
            val entityY = dynamicObject.pos.y
            val entityBounds = dynamicObject.bounds

            for (collisionObject in collisionObjects) {

                if (collisionObject.pos.y !in entityY..entityY+ TILE_SIZE) continue
                val objBounds = collisionObject.bounds


                if (!entityBounds.intersects(objBounds)) continue
                if (entityY + TILE_SIZE <= objBounds.centerY) continue
                dynamicObject.horizontalVelocity = 0.0
                dynamicObject.pos.x = dynamicObject.previousPosition.x
            }
        }
    }

    private fun checkYCollision() {
        for(dynamicObject in dynamicObjects) {
            dynamicObject.isOnGround = false
            val entityY = dynamicObject.pos.y

            if (entityY >= HEIGHT - TILE_SIZE) {
                dynamicObject.isOnGround = true
                dynamicObject.verticalVelocity = 0.0
                dynamicObject.pos.y = HEIGHT - TILE_SIZE
                continue
            }

            val entityBounds = dynamicObject.bounds

            for (collisionObject in collisionObjects) {
                val objBounds = collisionObject.bounds
                if (!entityBounds.intersects(objBounds)) continue

                if (entityY + TILE_SIZE <= objBounds.centerY) {
                    dynamicObject.isOnGround = true
                    dynamicObject.verticalVelocity = 0.0
                    dynamicObject.pos.y = collisionObject.pos.y - TILE_SIZE + 1
                    break
                }
            }
        }
    }

    // Ticks per second (or any rate you prefer)
    private val ticksPerSecond = 60
    private val nsPerTick = 1000000000L / ticksPerSecond
    private var lastTime = System.nanoTime()
    private var delta = 0.0

    override fun actionPerformed(e: ActionEvent?) {
        val now = System.nanoTime()
        delta += (now - lastTime) / nsPerTick
        lastTime = now

        while (delta >= 1) {
            // Update game state for physics logic
            player.update()
            enemy.update()

            // Handle collisions and movement
            checkYCollision()
            checkXCollision()
            delta -= 1
        }

        repaint()  // Render the game
    }



    override fun keyTyped(e: KeyEvent?) {

    }

    override fun keyPressed(e: KeyEvent?) {
        if (e != null) {
            player.keyPressed(e)
        }
    }

    override fun keyReleased(e: KeyEvent?) {
        player.keyReleased()
    }


}
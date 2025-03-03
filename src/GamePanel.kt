import sprites.CollisionObject
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

    private var playerX = 100
    private var playerY = 200

    private val collisionObjX = 220
    private var collisionObjY = 250


    private val timer = Timer(17, this)

    private val player = Player(Point(playerX,playerY))
    private val floor = CollisionObject(Point(collisionObjX,collisionObjY), "testFloor.png")

    init {
        this.preferredSize = Dimension(WIDTH, HEIGHT)
        timer.start()
    }

    override fun paintComponent(g: Graphics?) {
        val g2d = g as Graphics2D
        super.paintComponent(g)

        player.render(g2d, this)
        floor.render(g2d, this)
    }


    private fun rectangleCollisionTest(){

        if (player.bounds.intersects(floor.bounds)){

            player.horizontalVelocity = 0.0
            player.verticalVelocity = 0.0
            player.pos.move(player.previousPosition.x, player.previousPosition.y)
        }
    }

    // this function works for the most part, it just only works for one block and
    // I don't quite know how to check every object in the game
    // maybe make a hitbox scalar that applies to the player pos and checks for intersections
    // in a list of hitboxes for the other objects, the issue is idk how to do that.
    private fun collision(pos: Point){

        // instead of cheking if the player has collided with every object in the game,
        // have each object run a check if it has collided with a player
        // maybe implement a render distance sorta check in the actionPerformed function.
        // but yea move this function into the CollisionObject class, requires less work.

        if (collisionObjX > pos.x+TILE_SIZE || pos.x >= (collisionObjX + TILE_SIZE)){
            return
        }


        if(pos.y >= collisionObjY-TILE_SIZE){
            player.verticalVelocity = 0.0
            player.horizontalVelocity = 0.0
            //player.pos.y=collisionObjY-TILE_SIZE
            return
        }


//
//        if (collisionObjX <= pos.x+TILE_SIZE && pos.x <= collisionObjX){
//            println("inside cube on left")
//            player.horizontalVelocity = 0.0
//            player.pos.x=collisionObjX-TILE_SIZE
//            return
//
//        }
//        if (pos.x <= (collisionObjX + TILE_SIZE) && collisionObjX+ TILE_SIZE <= pos.x+TILE_SIZE){
//            println("inside cube on right")
//            player.horizontalVelocity = 0.0
//            player.pos.x=(collisionObjX + TILE_SIZE)
//            return
//        }
    }

    override fun actionPerformed(e: ActionEvent?) {

        //collision(player.pos)

        player.update()
        rectangleCollisionTest()

        repaint()

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
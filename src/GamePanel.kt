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

    private var playerX = 300
    private var playerY = 5

    private var collisionObjX = 200
    private var collisionObjY = 220

    private var collisionObjX2 = 300
    private var collisionObjY2 = 320

    private var collisionObjLeft = intArrayOf(collisionObjX- TILE_SIZE, collisionObjX2- TILE_SIZE)
    private var collisionObjRight = intArrayOf(collisionObjX+ TILE_SIZE, collisionObjX2+ TILE_SIZE)
    private var collisionObjTop = intArrayOf(collisionObjY,collisionObjY2)






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

    // trying out a hitbox idea here, seems to work really well
    // but I'm worried it slow down when scaled up

    // ok so yea right now it works amazingly

    // ok actually still don't know what side of the block it is on so it
    // kinda just shoves it into the block
    private fun collisionTest() {
        if(!player.isMoving){
            return
        }

        
        for (x: Int in player.hitbox){
            if (x in floor.hitbox){
                player.horizontalVelocity = 0.0
                player.pos.x = x
                return
            }
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
        collisionTest()
        player.update()
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
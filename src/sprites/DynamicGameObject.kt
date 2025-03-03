package sprites

import GamePanel
import java.awt.Graphics2D
import java.awt.Point
import java.awt.Rectangle
import java.awt.image.BufferedImage
import java.awt.image.ImageObserver
import java.io.IOException
import javax.imageio.ImageIO
import kotlin.math.absoluteValue

/*
These objects can move and are affected by gravity
 */

open class DynamicGameObject(startPos: Point, img: String) : GameObject(startPos, img) {
    private val gravity = 0.5
    private val frictionConstant = 0.5
    var verticalVelocity = 0.0
    var horizontalVelocity = 0.0
    var isMoving = false
    var isOnGround = false
    var previousPosition = Point(pos.x, pos.y)


    fun jump(strength: Double) {
        if (isOnGround) {
            pos.y -= 10
            verticalVelocity = -strength
        }
    }

    fun applyHorizontalPush(strength: Double) {
        pos.x += (0.01 * strength.absoluteValue / strength).toInt()
        horizontalVelocity = strength
    }

    protected fun applyFriction() {
        if (isMoving|| !isOnGround) {
            return
        }

        if (horizontalVelocity in -frictionConstant / 2..frictionConstant / 2) {
            return
        }

        if (horizontalVelocity > frictionConstant / 2) {
            horizontalVelocity -= frictionConstant

        } else if (horizontalVelocity < -frictionConstant / 2) {
            horizontalVelocity += frictionConstant
        }
    }

    // gravity controlled motion, is always checked
    protected fun calcProjectileMotion() {
        previousPosition.move(pos.x, pos.y)

        pos.x += horizontalVelocity.toInt()
        if ( isOnGround) {
            return
        }

        pos.y = (pos.y + verticalVelocity).toInt()

        verticalVelocity += gravity
    }
}
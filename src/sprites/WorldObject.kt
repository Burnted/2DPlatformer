package sprites

import java.awt.Graphics2D
import java.awt.image.ImageObserver

class WorldObject(frictionCoeff:Float, startX:Int, startY:Int, img:String) : GameObject(startX, startY, img) {
    override fun render(g2d: Graphics2D, renderX: Int, renderY: Int, observer: ImageObserver) {
        TODO("Not yet implemented")
    }

}
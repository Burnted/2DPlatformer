package sprites

import java.awt.Point

class Enemy (startPos: Point): DynamicGameObject(startPos, "testImg.png"){
    fun update() {
        this.jump(10.0)
        this.applyFriction()
        this.calcProjectileMotion()
        this.bounds.x = this.pos.x
        this.bounds.y = this.pos.y

    }
}
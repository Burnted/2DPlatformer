package physics

import GamePanel
import sprites.DynamicGameObject
import sprites.WorldObject

class PhysicsEngine(private val worldObjects: ArrayList<WorldObject>) {
    private val gravity = 2000.0 // -> px/s^2

    private val tileSize = GamePanel.TILE_SIZE

    // This method handles physics updates for all dynamic objects in the game
    fun update(entity: DynamicGameObject, deltaTime: Double) {
        //for (entity in entities) {
            applyGravity(entity,deltaTime)
            applyFriction(entity, deltaTime)
        handleCollision(entity, deltaTime)
        //}

    }

    private fun applyFriction(entity: DynamicGameObject, deltaTime: Double) {
        val frictionCoeff = 0.5f
        if (entity.isMoving|| !entity.isOnGround) {
            return
        }

        val acceleration = frictionCoeff * gravity
        val horizontalVelocity = entity.horizontalVelocity

        if (horizontalVelocity in -frictionCoeff / 2..frictionCoeff / 2) {
            return
        }

        if (horizontalVelocity > frictionCoeff / 2) {
            //println(entity.horizontalVelocity)
            entity.horizontalVelocity -= acceleration * deltaTime

        } else if (horizontalVelocity < -frictionCoeff / 2) {
            entity.horizontalVelocity += acceleration * deltaTime
        }
    }

    private fun handleCollision(entity: DynamicGameObject, deltaTime: Double) {
        for (worldObject in worldObjects) {
            val worldObjBounds = worldObject.bounds
            val entityBounds = entity.bounds
            if (!worldObjBounds.intersects(entity.collisionCheckBounds)) continue

            println("ready to collide")
            if (worldObjBounds.intersects(entityBounds)) {
                println("collided")
            }
        }
    }

    private fun checkYCollision() {
//        for(entity in entitys) {
//            entity.isOnGround = false
//            val entityY = entity.y
//
//            if (entityY >= HEIGHT - tileSize) {
//                entity.isOnGround = true
//                entity.verticalVelocity = 0.0
//                entity.y = HEIGHT - tileSize
//                continue
//            }
//
//            val entityBounds = entity.bounds
//
//            for (collisionObject in collisionObjects) {
//                val objBounds = collisionObject.bounds
//                if (!entityBounds.intersects(objBounds)) continue
//
//                if (entityY + tileSize <= objBounds.centerY) {
//                    entity.isOnGround = true
//                    entity.verticalVelocity = 0.0
//                    entity.y = collisionObject.y - tileSize + 1
//                    break
//                }
//            }
//        }
    }

    // Applies gravity to the object (changes its vertical velocity)
    private fun applyGravity(obj: DynamicGameObject, deltaTime: Double) {
        obj.verticalVelocity += gravity * deltaTime  // Apply gravity with delta time
    }



}
package com.maattss.intro.exercise.sprites

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.maattss.intro.exercise.IntroExercise
import com.maattss.intro.exercise.states.Task4State

class Ball {
    val texture: Texture = Texture("pong/ball.png")
    val position: Vector2 = Vector2((IntroExercise.WIDTH / 2).toFloat(),
            (IntroExercise.HEIGHT / 2).toFloat())
    private var up: Boolean = true
    private var right: Boolean = true
    private val speed: Int = 400
    val bounds: Rectangle = Rectangle(position.x, position.y, 20f, 20f)

    fun update(dt: Float, state: Task4State, pl: LeftPaddle, pr: RightPaddle) {
        bounds.setPosition(position.x, position.y)
        if (bounds.overlaps(pl.bounds) || bounds.overlaps(pr.bounds)) {
            right = !right
        }
        moveY(speed * dt)
        moveX(speed * dt, state)
    }

    fun moveX(speed: Float, state: Task4State) {
        if (right) {
            position.x += speed
        } else {
            position.x -= speed
        }
        if (position.x > IntroExercise.WIDTH) {
            position.x = IntroExercise.WIDTH / 2.toFloat()
            position.y = IntroExercise.HEIGHT / 2.toFloat()
            right = false
            up = !up
            state.incLeftScore()
        }
        if (position.x < 0) {
            position.x = IntroExercise.WIDTH / 2.toFloat()
            position.y = IntroExercise.HEIGHT / 2.toFloat()
            right = true
            up = !up
            state.incRightScore()
        }
    }

    fun moveY(speed: Float) {
        if (up) {
            position.y += speed
        } else {
            position.y -= speed
        }
        if (up && position.y > IntroExercise.HEIGHT) up = false
        if (!up && position.y < 0) up = true
    }
}
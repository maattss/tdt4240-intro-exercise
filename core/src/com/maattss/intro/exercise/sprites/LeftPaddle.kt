package com.maattss.intro.exercise.sprites

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.maattss.intro.exercise.IntroExercise

class LeftPaddle(x: Int, y: Int) {
    val position: Vector2
    val texture: Texture
    private val speed: Int
    val bounds: Rectangle

    fun update() {
        bounds.setPosition(position.x, position.y)
        if (position.y < 0) {
            position.y = 0f
        }
        if (position.y > IntroExercise.HEIGHT - texture.height) {
            position.y = IntroExercise.HEIGHT - texture.height.toFloat()
        }
        if (Gdx.input.isTouched) { // Using mouse as the controller for left player
            val mousePos = IntroExercise.HEIGHT - Gdx.input.y
            if (mousePos - speed > position.y) {
                position.y += speed.toFloat()
            } else if (mousePos + speed < position.y) {
                position.y -= speed.toFloat()
            }
        }
    }

    init {
        position = Vector2(x.toFloat(), y.toFloat())
        speed = 20
        texture = Texture("pong/paddle.png")
        bounds = Rectangle(position.x, position.y, texture.width.toFloat(), texture.height.toFloat())
    }
}
package com.sigmotoa.estasinefro

import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.cardview.widget.CardView

/**
 * Utilidad para manejar animaciones en la aplicación
 */
class AnimationUtils {

    companion object {

        /**
         * Aplica animación de presión a un botón
         */
        fun setButtonPressAnimation(context: Context, button: Button) {
            button.setOnTouchListener { v, event ->
                when (event.action) {
                    android.view.MotionEvent.ACTION_DOWN -> {
                        val pressAnim = AnimationUtils.loadAnimation(context, R.anim.button_press_scale)
                        v.startAnimation(pressAnim)
                        true
                    }
                    android.view.MotionEvent.ACTION_UP, android.view.MotionEvent.ACTION_CANCEL -> {
                        val releaseAnim = AnimationUtils.loadAnimation(context, R.anim.button_release_scale)
                        v.startAnimation(releaseAnim)
                        v.performClick()
                        true
                    }
                    else -> false
                }
            }
        }

        /**
         * Aplica animación de presión a una tarjeta
         */
        fun setCardPressAnimation(context: Context, card: CardView) {
            card.setOnTouchListener { v, event ->
                when (event.action) {
                    android.view.MotionEvent.ACTION_DOWN -> {
                        val pressAnim = AnimationUtils.loadAnimation(context, R.anim.button_press_scale)
                        v.startAnimation(pressAnim)
                        true
                    }
                    android.view.MotionEvent.ACTION_UP, android.view.MotionEvent.ACTION_CANCEL -> {
                        val releaseAnim = AnimationUtils.loadAnimation(context, R.anim.button_release_scale)
                        v.startAnimation(releaseAnim)
                        v.performClick()
                        true
                    }
                    else -> false
                }
            }
        }

        /**
         * Aplica animación de rebote a una vista
         */
        fun bounceAnimation(context: Context, view: View) {
            val bounceAnim = AnimationUtils.loadAnimation(context, R.anim.button_bounce)
            view.startAnimation(bounceAnim)
        }

        /**
         * Aplica animación de entrada con fade
         */
        fun fadeInAnimation(context: Context, view: View) {
            val fadeInAnim = AnimationUtils.loadAnimation(context, R.anim.fade_in)
            view.startAnimation(fadeInAnim)
        }

        /**
         * Aplica animación de entrada desde abajo
         */
        fun slideUpAnimation(context: Context, view: View) {
            val slideUpAnim = AnimationUtils.loadAnimation(context, R.anim.slide_up)
            view.startAnimation(slideUpAnim)
        }

        /**
         * Aplica animación de entrada desde arriba
         */
        fun slideDownAnimation(context: Context, view: View) {
            val slideDownAnim = AnimationUtils.loadAnimation(context, R.anim.slide_down)
            view.startAnimation(slideDownAnim)
        }

        /**
         * Aplica animación de escala
         */
        fun scaleInAnimation(context: Context, view: View) {
            val scaleInAnim = AnimationUtils.loadAnimation(context, R.anim.scale_in)
            view.startAnimation(scaleInAnim)
        }

        /**
         * Crea una animación de escalado personalizada
         */
        fun createScaleAnimation(view: View, fromScale: Float, toScale: Float, duration: Long) {
            val scaleX = ObjectAnimator.ofFloat(view, "scaleX", fromScale, toScale)
            val scaleY = ObjectAnimator.ofFloat(view, "scaleY", fromScale, toScale)

            val animatorSet = AnimatorSet()
            animatorSet.playTogether(scaleX, scaleY)
            animatorSet.duration = duration
            animatorSet.start()
        }

        /**
         * Aplica animaciones secuenciales a múltiples vistas
         */
        fun animateViewsSequentially(context: Context, views: List<View>, animations: List<Animation>) {
            if (views.size != animations.size) return

            for (i in views.indices) {
                val view = views[i]
                val animation = animations[i]
                animation.startOffset = (i * 100).toLong() // 100ms de retraso entre cada vista
                view.startAnimation(animation)
            }
        }

        /**
         * Aplica animación de pulso continuo
         */
        fun pulseAnimation(view: View) {
            val pulseIn = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 1.05f)
            val pulseOut = ObjectAnimator.ofFloat(view, "scaleX", 1.05f, 1.0f)
            val pulseInY = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 1.05f)
            val pulseOutY = ObjectAnimator.ofFloat(view, "scaleY", 1.05f, 1.0f)

            val animatorSet = AnimatorSet()
            animatorSet.play(pulseIn).with(pulseInY)
            animatorSet.play(pulseOut).with(pulseOutY).after(pulseIn)

            animatorSet.duration = 1000
            // Para animación infinita, creamos un bucle manual
            animatorSet.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: android.animation.Animator) {
                    animatorSet.start()
                }
            })
            animatorSet.start()
        }

        /**
         * Detiene todas las animaciones de una vista
         */
        fun clearAnimation(view: View) {
            view.clearAnimation()
        }
    }
}
package org.greenbyme.angelhack.extention

import android.view.animation.Animation

abstract class AnimationEndListener : Animation.AnimationListener {
    override fun onAnimationStart(animation: Animation?) {
    }
    abstract override fun onAnimationEnd(animation: Animation?)
    override fun onAnimationRepeat(animation: Animation?) {
    }
}
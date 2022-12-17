package com.example.vkcup.ui.main

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.updatePadding
import com.example.vkcup.R
import com.example.vkcup.databinding.ItemBinding


class ItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: ItemBinding
    var text = ""
        set(value) {
            binding.title.text = value
            field = value
        }
    private var isChecked = false

    init {
        binding = ItemBinding.inflate(LayoutInflater.from(context), this, true)
        background = ContextCompat.getDrawable(context, R.drawable.shape_white_17)
    }

    private fun updateView() {
        if (isChecked) {
            binding.image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.check))
            background = ContextCompat.getDrawable(context, R.drawable.shape_orange)
            binding.image.updatePadding(3, 6, 3, 5)
        } else {
            binding.image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.plus))
            background = ContextCompat.getDrawable(context, R.drawable.shape_white_17)
            binding.image.updatePadding(0, 0, 0, 0)
        }
    }

    private fun anim() {
        val anim1 = AnimationUtils.loadAnimation(context, R.anim.anim_increase_to_2);
        val anim2 = AnimationUtils.loadAnimation(context, R.anim.anim_decrease_to_1);
        val animSet = AnimationSet(true)
        animSet.addAnimation(anim1)
        animSet.addAnimation(anim2)
        startAnimation(animSet)
    }

    fun click(afterClick: (Boolean) -> Unit) {
        anim()
        isChecked = !isChecked
        updateView()
        afterClick.invoke(isChecked)
    }

    override fun onSaveInstanceState(): Parcelable {
        super.onSaveInstanceState()
        val bundle = Bundle()
        bundle.putParcelable("superState", super.onSaveInstanceState())
        bundle.putBoolean("isChecked", isChecked)
        return bundle
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        var viewState: Parcelable
        if (state is Bundle) {
            isChecked = state.getBoolean("isChecked")
            viewState = state.getParcelable<Parcelable>("superState")!!
            updateView()
            super.onRestoreInstanceState(viewState)
        }
    }
}
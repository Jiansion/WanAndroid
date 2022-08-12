package com.conch.wanandroid.base

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * @author YeJain
 * @date 2022/8/9 17:09
 * Fragment 使用 ViewBinding 简化操作
 */
class FragmentViewBinding<V : ViewBinding>(
    private val bindingCreator: () -> V
) : ReadOnlyProperty<Fragment, V>, LifecycleEventObserver {

    private var binding: V? = null

    override fun getValue(thisRef: Fragment, property: KProperty<*>): V {
        binding?.let {
            return it
        }

        val lifecycle = thisRef.viewLifecycleOwner.lifecycle
        if (lifecycle.currentState == Lifecycle.State.DESTROYED) {
            this.binding = null
            throw  IllegalStateException("不能在 onDestroyView 之后进行绑定")
        } else {
            lifecycle.addObserver(this)
            val viewBinding = bindingCreator.invoke()
            this.binding = viewBinding
            return viewBinding
        }

    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            binding = null
            source.lifecycle.removeObserver(this)
        }
    }
}

fun <V : ViewBinding> Fragment.viewBinding(binder: (View) -> V): FragmentViewBinding<V> {
    return FragmentViewBinding { binder.invoke(this.requireView()) }
}
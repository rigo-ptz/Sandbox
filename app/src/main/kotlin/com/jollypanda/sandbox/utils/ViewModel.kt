package com.jollypanda.sandbox.utils

import androidx.lifecycle.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import kotlin.reflect.KClass

/**
 * @author Yamushev Igor
 * @since  08.06.18
 */
fun <T : ViewModel> androidx.fragment.app.FragmentActivity.viewModel(clazz: KClass<T>) = lazy {
    ViewModelProviders.of(this).get(clazz.java)
}

fun <T : ViewModel> androidx.fragment.app.Fragment.viewModel(clazz: KClass<T>) = lazy {
    ViewModelProviders.of(this).get(clazz.java)
}

fun <T> LiveData<T>.observe(lifecycleOwner: LifecycleOwner, observer: (value: T?) -> Unit) {
    this.observe(lifecycleOwner, Observer { observer.invoke(it) })
}

fun<T> liveDataOf(defValue: T? = null) = MutableLiveData<T>().apply {
    defValue?.apply { postValue(this) }
}
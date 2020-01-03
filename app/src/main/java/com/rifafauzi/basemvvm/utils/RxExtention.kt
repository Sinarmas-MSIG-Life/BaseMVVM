package com.rifafauzi.basemvvm.utils

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by rrifafauzikomara on 2020-01-03.
 */
 
operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
    add(disposable)
}
package org.greenbyme.angelhack.data.local

import org.greenbyme.angelhack.utils.AutoClearDisposable

interface BaseRepository {
    val autoClearDisposable: AutoClearDisposable
    fun observeLifeCycle()
}
package es.fabiogomez.repository

import android.content.Context
import es.fabiogomez.repository.cache.Cache
import es.fabiogomez.repository.cache.CacheImpl
import java.lang.ref.WeakReference


class RepositoryImpl(context: Context): Repository {

    val weakContext = WeakReference<Context>(context)
    private val cache : Cache = CacheImpl(weakContext.get()!!)

    override fun deleteAllShops(success: () -> Unit, error: (errorMessage: String) -> Unit) {
        cache.deleteAllShops(success, error)
    }

}
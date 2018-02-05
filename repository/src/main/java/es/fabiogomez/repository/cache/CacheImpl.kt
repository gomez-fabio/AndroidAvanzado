package es.fabiogomez.repository.cache

import android.content.Context
import es.fabiogomez.repository.db.DBHelper
import es.fabiogomez.repository.db.build
import es.fabiogomez.repository.db.dao.ShopDao
import es.fabiogomez.repository.thread.DispatchOnMainThread
import java.lang.ref.WeakReference

internal class CacheImpl(context:Context): Cache{

    val context = WeakReference<Context>(context)

    override fun deleteAllShops(success: () -> Unit, error: (errorMessage: String) -> Unit) {
        // Así lanzamos el ShopDao(cacheDBHelper()).deleteAll() en un hilo en 2º plano
        Thread(Runnable{
            var successDeleting = ShopDao(cacheDBHelper()).deleteAll()

            // Así volvemos al hilo ppal con la respuesta
            DispatchOnMainThread(Runnable {
                if (successDeleting) {
                    success()
                } else {
                    error("Infernal error deleting")
                }
            })
        }).run()

    }

    private fun cacheDBHelper() : DBHelper {
        return build(context.get()!!,"MadridShops.sqlite", 1)
    }
}

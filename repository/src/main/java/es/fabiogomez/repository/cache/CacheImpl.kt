package es.fabiogomez.repository.cache

import android.content.Context
import es.fabiogomez.repository.db.DBHelper
import es.fabiogomez.repository.db.build
import es.fabiogomez.repository.db.dao.ShopDao
import es.fabiogomez.repository.model.ShopEntity
import es.fabiogomez.repository.thread.DispatchOnMainThread
import java.lang.ref.WeakReference

internal class CacheImpl(context:Context): Cache{
    val context = WeakReference<Context>(context)

    private fun cacheDBHelper() : DBHelper {
        return build(context.get()!!,"MadridShops.sqlite", 1)
    }

    override fun getAllShops(success: (shops: List<ShopEntity>) -> Unit, error: (errorMessage: String) -> Unit) {
        Thread(Runnable{
            var shops = ShopDao(cacheDBHelper()).query()

            DispatchOnMainThread(Runnable {
                if (shops.count() > 0) {
                    success(shops)
                } else {
                    error("Infernal error getting shops")
                }
            })
        }).run()
    }

    override fun saveAllShops(shops: List<ShopEntity>, success: () -> Unit, error: (errorMessage: String) -> Unit) {
        Thread(Runnable{
            try {
                shops.forEach { ShopDao(cacheDBHelper()).insert(it) }
                DispatchOnMainThread(Runnable {
                    success()
                })
            } catch (e: Exception) {
                DispatchOnMainThread(Runnable {
                    error("Infernal error saving shops")
                })
            }
        }).run()
    }

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
}

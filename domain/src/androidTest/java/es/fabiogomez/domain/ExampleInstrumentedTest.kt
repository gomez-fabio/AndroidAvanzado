package es.fabiogomez.domain

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import es.fabiogomez.repository.db.build
import es.fabiogomez.repository.db.dao.ShopDao
import es.fabiogomez.repository.model.ShopEntity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*


@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    val appContext = InstrumentationRegistry.getTargetContext()
    val dbhelper = build(appContext, "mydb.sqlite",1)

    @Test
    @Throws(Exception::class)

    fun given_valid_shopentity_it_gets_inserted_correctly() {


        val shop = ShopEntity(1,1,"tienda","",1.0f,2.0f,"","","","")

        val shopEntityDao = ShopDao(dbhelper)

        val id = shopEntityDao.insert(shop)

        assertTrue(id>0)
    }
}

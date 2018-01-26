package es.fabiogomez.madridshops

import android.support.multidex.MultiDexApplication
import android.util.Log
import es.fabiogomez.domain.interactor.ErrorCompletion
import es.fabiogomez.domain.interactor.getallshops.GetAllShopsInteractorFakeImpl
import es.fabiogomez.domain.interactor.SuccessCompletion
import es.fabiogomez.domain.model.Shops

// esta clase es el equivalente al appdelegate de ios
class MadridShopsApp: MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        // init code application wide, base de datos, inyeccion de dependecias, etc...

        Log.d("App", "onCreate JARL")

        val allShopsInteractor = GetAllShopsInteractorFakeImpl()

        allShopsInteractor.execute(
                success = { shops: Shops -> },
                error = { msg:String -> Unit }
                )

        allShopsInteractor.execute(
        success = object : SuccessCompletion<Shops>{
            override fun successCompletion(shops: Shops) {
                Log.d("Shops", "Count:" + shops.count())
            }
        },
        error = object: ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {

            }
        })
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }


}
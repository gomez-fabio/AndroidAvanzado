package es.fabiogomez.domain.interactor

import es.fabiogomez.domain.model.Shop
import es.fabiogomez.domain.model.Shops

class GetAllShopsInteractorFakeImpl : GetAllShopsInteractor {

    override fun execute(success: SuccessCompletion<Shops>, error: ErrorCompletion) {
        var allOk = false


        // Connect to the repository
        if (allOk) {
            val shops =  createFakeListOfShops()

            success.successCompletion(shops)
        } else {
            error.errorCompletion("Error while accesing repository JARL")
        }
    }

    fun createFakeListOfShops() : Shops {
        val list = ArrayList<Shop>()

        for (i in 0..100) {
            val shop = Shop(i, "Shop#" + i, "Address#" + i)
            list.add(shop)
        }

        val shops = Shops(list)
        return shops
    }

}

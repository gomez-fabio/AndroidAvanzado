package es.fabiogomez.domain.interactor

import es.fabiogomez.domain.model.Shops

interface GetAllShopsInteractor {
    fun execute(success: SuccessCompletion<Shops>, error: ErrorCompletion)
}
package es.fabiogomez.repository

interface Repository {
    fun deleteAllShops(success: () -> Unit, error: (errorMessage:String)-> Unit)
}

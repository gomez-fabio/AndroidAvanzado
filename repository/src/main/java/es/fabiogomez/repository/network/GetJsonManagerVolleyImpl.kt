package es.fabiogomez.repository.network

import android.content.Context
import android.util.Log
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class GetJsonManagerVolleyImpl: GetJsonManager {

     var context: Context? = null
     var requestQueue: RequestQueue? = null

     override fun execute(url: String, ) {

         // create request ( success, failure)
        var request = StringRequest(url,
            Response.Listener {
                Log.d("JSON", it)               // Bloque de success
            }, Response.ErrorListener {
                it.localizedMessage                 // Bloque de error
            })

         // add request to queue
         requestQueue().add(request)

     }

    // get request queue
    fun requestQueue(): RequestQueue {
        if (requestQueue == null ) {
            // Me creo la cola pasándole como contexto el contexto que me pasan
            requestQueue = Volley.newRequestQueue(context)
        }

        return requestQueue!!
    }
 }
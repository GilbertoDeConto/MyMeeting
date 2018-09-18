package br.edu.unisep.mymeeting.api

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

open class AppQueue {

    companion object {

        private var q : RequestQueue? = null

        fun startQueue(context: Context){

            if(q == null){
                q = Volley.newRequestQueue(context)
            }
        }

        fun <T> addToQueue(req : Request<T>){
            q!!.add(req)
        }
    }

}
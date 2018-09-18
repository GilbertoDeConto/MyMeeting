package br.edu.unisep.mymeeting.api

import br.edu.unisep.mymeeting.vo.ReuniaoVO
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ReuniaoAPI {

    private val SERVER = "http://localhost:8080/mymeeting/ws/reuniao"

    private val WS_LISTA = "${SERVER}/listar"
    private val WS_SALVAR = "${SERVER}/incluir"

    fun listar( fnRetorno : (List<ReuniaoVO>) -> Unit){

        val req = StringRequest(Request.Method.GET, WS_LISTA,
                Response.Listener { retorno ->

                    val gs = Gson()
                    val tipo = object : TypeToken<List<ReuniaoVO>>(){}.type

                    val lista = gs.fromJson< List<ReuniaoVO> >(retorno, tipo)

                    fnRetorno(lista)
                },
                Response.ErrorListener { erro ->
                    erro.printStackTrace()
                })

        AppQueue.addToQueue(req)
    }

    fun salvar(reuniao : ReuniaoVO, fnRetorno: () -> Unit){

        val gs = Gson()
        val conteudo = gs.toJson(reuniao)

        val req = object: StringRequest(Request.Method.POST, WS_SALVAR,
                Response.Listener { retorno ->
                    fnRetorno()
                },
                Response.ErrorListener { erro ->
                    erro.printStackTrace()
                }){

            override fun getBody() : ByteArray{
                return conteudo.toByteArray()
            }

            override fun getBodyContentType(): String {
                return "application/json"
            }
        }

        AppQueue.addToQueue(req)
    }

}
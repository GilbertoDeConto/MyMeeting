package br.edu.unisep.mymeeting.api

import br.edu.unisep.mymeeting.vo.ReuniaoVO
import br.edu.unisep.mymeeting.vo.TopicoVO
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TopicoAPI {

    private val SERVER = "http://localhost:8080/mymeeting/ws/topico"

    private val WS_LISTA = "${SERVER}/listar"
    private val WS_SALVAR = "${SERVER}/incluir"

    fun listar(reuniao : Int, fnRetorno : (List<TopicoVO>) -> Unit){

        val ws =  "${WS_LISTA}/${reuniao}";

        val req = StringRequest(Request.Method.GET, ws,
                Response.Listener { retorno ->

                    val gs = Gson()
                    val tipo = object : TypeToken<List<TopicoVO>>(){}.type

                    val lista = gs.fromJson< List<TopicoVO> >(retorno, tipo)

                    fnRetorno(lista)
                },
                Response.ErrorListener { erro ->
                    erro.printStackTrace()
                })

        AppQueue.addToQueue(req)
    }

    fun salvar(reuniao : TopicoVO, fnRetorno: () -> Unit){

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
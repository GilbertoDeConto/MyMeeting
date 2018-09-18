package br.edu.unisep.mymeeting

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import br.edu.unisep.mymeeting.adapter.ReuniaoAdapter
import br.edu.unisep.mymeeting.api.AppQueue
import br.edu.unisep.mymeeting.api.ReuniaoAPI
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var adapter : ReuniaoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = ReuniaoAdapter(this)
        listaReunioes.adapter = adapter

        listaReunioes.setOnItemClickListener {parent, view, position, id ->

            val i = Intent(this, ListaTopicosActivity::class.java)
            i.putExtra("REUNIAO", id.toInt())
            startActivityForResult(i, 2)
        }

        // Inicia a fila de requisições da aplicação
        AppQueue.startQueue(this)

        obterListaReunioes()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_reuniao, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        val i = Intent(this, NovaReuniaoActivity::class.java)
        startActivityForResult(i, 1)

        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        obterListaReunioes()
    }

    fun obterListaReunioes() {

        var api = ReuniaoAPI()
        api.listar { reunioes ->
            adapter!!.clear()
            adapter!!.addAll(reunioes)
            adapter!!.notifyDataSetChanged()
        }
    }

}

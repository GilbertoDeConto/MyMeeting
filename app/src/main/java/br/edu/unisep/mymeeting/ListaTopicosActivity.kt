package br.edu.unisep.mymeeting

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import br.edu.unisep.mymeeting.adapter.TopicoAdapter
import br.edu.unisep.mymeeting.api.TopicoAPI
import kotlinx.android.synthetic.main.lista_topicos.*

class ListaTopicosActivity : AppCompatActivity() {

    private var adapter : TopicoAdapter? = null

    private var reuniao : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.lista_topicos)

        adapter = TopicoAdapter(this)
        listaTopicos.adapter = adapter

        reuniao = intent.getIntExtra("REUNIAO", -1)

        obterListaTopicos()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_topico, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        val i = Intent(this, NovoTopicoActivity::class.java)
        i.putExtra("REUNIAO", reuniao)
        startActivityForResult(i, 1)

        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        obterListaTopicos()
    }

    fun obterListaTopicos(){

        var api = TopicoAPI()
        api.listar (reuniao, { topicos ->
            adapter!!.clear()
            adapter!!.addAll(topicos)
            adapter!!.notifyDataSetChanged()
        })
    }

}

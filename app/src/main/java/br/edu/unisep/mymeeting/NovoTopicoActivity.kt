package br.edu.unisep.mymeeting

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import br.edu.unisep.mymeeting.api.ReuniaoAPI
import br.edu.unisep.mymeeting.api.TopicoAPI
import br.edu.unisep.mymeeting.vo.ReuniaoVO
import br.edu.unisep.mymeeting.vo.TopicoVO
import kotlinx.android.synthetic.main.activity_nova_reuniao.*
import kotlinx.android.synthetic.main.lista_topicos.*
import kotlinx.android.synthetic.main.novo_topico.*

class NovoTopicoActivity : AppCompatActivity() {

    private var reuniao : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.novo_topico)

        reuniao = intent.getIntExtra("REUNIAO", -1)
    }

    fun salvar(v : View) {

        val t = TopicoVO()

        val r = ReuniaoVO()
        r.id = reuniao

        t.descricao = txtTopico.text.toString()
        t.tempo = txtTempo.text.toString().toDouble()
        t.reuniao = r

        if(rdBaixa.isSelected){
            t.prioridade = 1;
        } else if (rdMedia.isSelected){
            t.prioridade = 2;
        } else {
            t.prioridade = 3;
        }

        val api = TopicoAPI()

        api.salvar(t, {
            setResult(Activity.RESULT_OK)
            finish()
        })
    }

}

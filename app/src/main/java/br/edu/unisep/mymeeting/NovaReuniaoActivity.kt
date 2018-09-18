package br.edu.unisep.mymeeting

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import br.edu.unisep.mymeeting.api.ReuniaoAPI
import br.edu.unisep.mymeeting.vo.ReuniaoVO
import kotlinx.android.synthetic.main.activity_nova_reuniao.*

class NovaReuniaoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nova_reuniao)
    }

    fun salvar(v : View) {

        val r = ReuniaoVO()

        r.titulo = txtTitulo.text.toString()
        r.objetivo = txtObjetivos.text.toString()
        r.assunto = txtAssunto.text.toString()

        val api = ReuniaoAPI()

        api.salvar(r, {
            setResult(Activity.RESULT_OK)
            finish()
        })
    }

}

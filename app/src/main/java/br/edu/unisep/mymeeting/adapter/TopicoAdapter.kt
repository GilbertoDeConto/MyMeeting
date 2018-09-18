package br.edu.unisep.mymeeting.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import br.edu.unisep.mymeeting.R
import br.edu.unisep.mymeeting.vo.TopicoVO
import kotlinx.android.synthetic.main.item_topico.view.*

class TopicoAdapter(context : Context) : ArrayAdapter<TopicoVO>(context, 0) {

    private val inflater = LayoutInflater.from(context)

    override fun getItemId(position: Int): Long {
        val r = getItem(position)
        return r.id!!.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val item = inflater.inflate(R.layout.item_topico, null)
        val r = getItem(position)

        item.lblDescricao.text = r.descricao
        item.lblTempo.text = r.tempo.toString()

        return item
    }
}
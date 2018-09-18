package br.edu.unisep.mymeeting.vo

data class TopicoVO(var id : Int? = null,
                    var descricao : String? = null,
                    var tempo : Double? = null,
                    var prioridade : Int? = null,
                    var reuniao : ReuniaoVO? = null) {
}
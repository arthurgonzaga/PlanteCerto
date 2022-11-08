package br.com.plantecerto.domain.data.entity.responses

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Arthur Gonzaga on 10/30/2022
 */
data class ZoneamentoResponse(
    val cultura: String,
    val uf: String,
    val municipio: String,
    val risco: Int,
    val solo: String,
    private val diaFim: Int,
    private val diaIni: Int,
    private val mesFim: Int,
    private val mesIni: Int,
    private val safraFim: Int,
    private val safraIni: Int,
) {

    fun getInicioSafraDate(pattern: String): String {
        val calendar = Calendar.getInstance()
        calendar.set(safraIni, mesIni, diaIni)
        return SimpleDateFormat(pattern, Locale.getDefault()).format(calendar.time)
    }

    fun getFimSafraDate(pattern: String) : String {
        val calendar = Calendar.getInstance()
        calendar.set(safraFim, mesFim, diaFim)
        return SimpleDateFormat(pattern, Locale.getDefault()).format(calendar.time)
    }

    companion object {
        const val AGRITEC_FORMAT = "yyyy-MM-dd"
        const val APP_FORMAT = "dd/MM/yyyy"
    }
}
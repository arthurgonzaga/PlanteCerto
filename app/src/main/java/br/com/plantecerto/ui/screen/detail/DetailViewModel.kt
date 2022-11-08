package br.com.plantecerto.ui.screen.detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.plantecerto.domain.data.entity.model.Info
import br.com.plantecerto.domain.data.entity.model.TextInfo
import br.com.plantecerto.domain.data.entity.responses.ZoneamentoResponse
import br.com.plantecerto.domain.data.database.CulturasDAO
import br.com.plantecerto.domain.data.database.MunicipioDao
import br.com.plantecerto.domain.data.repository.agritec.AgritecRepository
import br.com.plantecerto.domain.data.repository.ipinfo.IpInfoRepository
import br.com.plantecerto.domain.usecase.generatedtext.GenerateTextUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Arthur Gonzaga on 10/30/2022
 */
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val municipioDao: MunicipioDao,
    private val culturasDAO: CulturasDAO,
    private val ipInfoRepository: IpInfoRepository,
    private val agritecRepository: AgritecRepository,
) : ViewModel() {

    private val generateTextUseCase: GenerateTextUseCase = GenerateTextUseCase()
    private val pastPredictions = hashMapOf<String, DetailUiState>()

    val uiState = mutableStateOf(DetailUiState())

    fun getPredictions(
        culturaName: String,
    ) {
        if (culturaName in pastPredictions) {
            uiState.value = pastPredictions[culturaName]!!
            return
        }
        if (
            ((culturaName == uiState.value.culturaName) && !uiState.value.hasError)
            || uiState.value.error?.message == GENERIC_ERROR
        ) return

        uiState.value = uiState.value.copy(isLoading = true, culturaName = culturaName)
        viewModelScope.launch {
            try {
                // Get location by IP Address
                val location = ipInfoRepository.getLocation()

                // Get codigo IBGE from municipio
                val codigoIBGE = municipioDao.getCodigoIbgeFrom(
                    uf = location.uf,
                    municipio = location.municipio
                )

                // Get Cultura
                val cultura = culturasDAO.getCulturaBy(name = culturaName)

                // Get zoneamentos from codigoIBGE
                val zoneamentoList = agritecRepository.getZoneamento(
                    codigoIBGE = codigoIBGE!!,
                    idCultura = cultura.idCultura,
                )

                if (zoneamentoList.isEmpty()) {
                    uiState.value = uiState.value.copy(
                        isLoading = false,
                        error = Throwable("Não recomendamos plantar ${culturaName.lowercase()} neste local.")
                    )
                    pastPredictions[culturaName] = uiState.value
                    return@launch
                } else {
                    val produtividade = agritecRepository.getProdutividade(
                        codigoIBGE = codigoIBGE,
                        dataPlantio = zoneamentoList[0].getInicioSafraDate(ZoneamentoResponse.AGRITEC_FORMAT),
                        culturaEntity = cultura
                    )

                    uiState.value = uiState.value.copy(
                        isLoading = false,
                        error = null,
                        generatedTexts = listOf(
                            TextInfo(
                                title = null,
                                text = generateTextUseCase(
                                    zoneamentoList = zoneamentoList,
                                    produtividade = produtividade
                                )
                            )
                        )
                    )
                }
                pastPredictions[culturaName] = uiState.value

            } catch (e: Exception) {
                e.printStackTrace()
                uiState.value = uiState.value.copy(
                    isLoading = false,
                    error = Throwable(GENERIC_ERROR),
                    generatedTexts = emptyList()
                )
                pastPredictions[culturaName] = uiState.value
            }
        }
    }

    companion object {
        const val GENERIC_ERROR =
            "Algo de errado aconteceu... Tente novamente mais tarde ou em outro local."
    }
}

data class DetailUiState(
    val culturaName: String? = null,
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val generatedTexts: List<Info> = emptyList()
) {
    val hasError get() = error != null
}
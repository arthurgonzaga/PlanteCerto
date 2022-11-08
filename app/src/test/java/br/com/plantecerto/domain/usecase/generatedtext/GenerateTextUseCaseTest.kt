package br.com.plantecerto.domain.usecase.generatedtext

import br.com.plantecerto.domain.data.entity.responses.ProdutividadeResponse
import br.com.plantecerto.domain.data.entity.responses.ZoneamentoResponse
import org.junit.Before
import org.junit.Test

/**
 * Created by Arthur Gonzaga on 10/31/2022
 */
internal class GenerateTextUseCaseTest {

    val generateTextUseCase = GenerateTextUseCase()

    @Test
    fun `should generate text when giving 3 zoneamentos`() {
        // Given
        val zoneamentoList = listOf(zoneamento1, zoneamento2, zoneamento3)

        // When
        val result = generateTextUseCase(zoneamentoList, produtividade)

        // Then
        println(result)
        assert(result.contains("arenoso, argiloso e calcario"))
    }

    @Test
    fun `should generate text when giving 2 zoneamentos`() {
        // Given
        val zoneamentoList = listOf(zoneamento1, zoneamento2)

        // When
        val result = generateTextUseCase(zoneamentoList, produtividade)

        // Then
        println(result)
        assert(result.contains("arenoso e argiloso"))
    }

    @Test
    fun `should generate text when giving 1 zoneamento1`() {
        // Given
        val zoneamentoList = listOf(zoneamento1)

        // When
        val result = generateTextUseCase(zoneamentoList, produtividade)

        // Then
        println(result)
        assert(result.contains("arenoso "))
    }

    companion object {
        val zoneamento1 = ZoneamentoResponse(
            uf = "PB",
            cultura = "Milho",
            municipio = "Teste",
            risco = 20,
            solo = "ARENOSO",
            diaIni = 10,
            diaFim = 20,
            mesIni = 3,
            mesFim = 4,
            safraFim = 2023,
            safraIni = 2022,
        )

        val zoneamento2 = ZoneamentoResponse(
            uf = "PB",
            cultura = "Milho",
            municipio = "Teste",
            risco = 20,
            solo = "ARGILOSO",
            diaIni = 10,
            diaFim = 20,
            mesIni = 3,
            mesFim = 4,
            safraFim = 2023,
            safraIni = 2022,
        )

        val zoneamento3 = ZoneamentoResponse(
            uf = "PB",
            cultura = "Milho",
            municipio = "Teste",
            risco = 20,
            solo = "CALCARIO",
            diaIni = 10,
            diaFim = 20,
            mesIni = 3,
            mesFim = 4,
            safraFim = 2023,
            safraIni = 2022,
        )

        val produtividade = ProdutividadeResponse(
            produtividadeMediaMunicipio = listOf(0.5,0.2,0.7,0.6,2.0, 2.605754512088104)
        )
    }
}
package br.com.plantecerto.domain.data.responses

import com.google.gson.annotations.SerializedName

/**
 * Created by Arthur Gonzaga on 10/25/2022
 */
data class AccessTokenResponse(
    @SerializedName("access_token")
    private val accessToken: String,
    @SerializedName("token_type")
    private val tokenType: String,
) {
    fun get(): String = "$tokenType $accessToken"
}

package com.guilhermekunz.cfapp.api.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

class FactsResponse : ArrayList<FactsResponseItem>()

@Parcelize
data class FactsResponseItem(
    @SerializedName("__v") val vn: Int,
    @SerializedName("_id") val id: String,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("deleted") val deleted: Boolean,
    @SerializedName("source") val source: String,
    @SerializedName("status") val status: Status,
    @SerializedName("text") val text: String,
    @SerializedName("type") val type: String,
    @SerializedName("updatedAt") val updatedAt: String,
    @SerializedName("used") val used: Boolean,
    @SerializedName("user") val user: String
) : Parcelable

@Parcelize
data class Status(
    @SerializedName("feedback") val feedback: String,
    @SerializedName("sentCount") val sentCount: Int,
    @SerializedName("verified") val verified: Boolean
) : Parcelable
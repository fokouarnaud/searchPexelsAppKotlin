package com.example.myapplication.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class PexelsVideoPicture(
    val id: Long,
    val picture: String,
    @SerializedName("nr")
    val numPreview: Long
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PexelsVideoPicture

        if (id != other.id) return false
        if (picture != other.picture) return false
        if (numPreview != other.numPreview) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + picture.hashCode()
        result = 31 * result + numPreview.hashCode()
        return result
    }
}
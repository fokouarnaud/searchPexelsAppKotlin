package com.example.cleanpexels.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class PexelsPhoto(
    val id: Long,
    val width: Long,
    val height: Long,
    @SerializedName("url")
    val urlToSite: String,
    val photographer: String,
    val photographer_url: String,
    val photographer_id: Long,
    @SerializedName("avg_color")
    val color: String,
    val src: PexelsPhotoSrc
):Serializable{
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PexelsPhoto

        if (id != other.id) return false
        if (width != other.width) return false
        if (height != other.height) return false
        if (urlToSite != other.urlToSite) return false
        if (photographer != other.photographer) return false
        if (photographer_url != other.photographer_url) return false
        if (photographer_id != other.photographer_id) return false
        if (color != other.color) return false
        if (src != other.src) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + width.hashCode()
        result = 31 * result + height.hashCode()
        result = 31 * result + urlToSite.hashCode()
        result = 31 * result + photographer.hashCode()
        result = 31 * result + photographer_url.hashCode()
        result = 31 * result + photographer_id.hashCode()
        result = 31 * result + color.hashCode()
        result = 31 * result + src.hashCode()
        return result
    }
}
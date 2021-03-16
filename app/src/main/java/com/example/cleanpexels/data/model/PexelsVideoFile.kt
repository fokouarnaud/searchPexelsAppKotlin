package com.example.cleanpexels.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PexelsVideoFile(
    val id: Long,
    val quality: String,
    @SerializedName("file_type")
    val fileType: String,
    val width: Long,
    val height: Long,
    val link: String
):Serializable{
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PexelsVideoFile

        if (id != other.id) return false
        if (quality != other.quality) return false
        if (fileType != other.fileType) return false
        if (width != other.width) return false
        if (height != other.height) return false
        if (link != other.link) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + quality.hashCode()
        result = 31 * result + fileType.hashCode()
        result = 31 * result + width.hashCode()
        result = 31 * result + height.hashCode()
        result = 31 * result + link.hashCode()
        return result
    }
}
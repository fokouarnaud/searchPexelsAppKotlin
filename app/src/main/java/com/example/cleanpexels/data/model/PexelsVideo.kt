package com.example.cleanpexels.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PexelsVideo(
    val id: Long,
    val width: Long,
    val height: Long,
    @SerializedName("url")
    val urlToSite: String,
    val image: String,
    val duration: Long,
    val user: PexelsVideoUser,
    @SerializedName("video_files")
    val videoFiles: List<PexelsVideoFile>,
    @SerializedName("video_pictures")
    val videoPictures: List<PexelsVideoPicture>,
) :Serializable{
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PexelsVideo

        if (id != other.id) return false
        if (width != other.width) return false
        if (height != other.height) return false
        if (urlToSite != other.urlToSite) return false
        if (image != other.image) return false
        if (duration != other.duration) return false
        if (user != other.user) return false
        if (videoFiles != other.videoFiles) return false
        if (videoPictures != other.videoPictures) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + width.hashCode()
        result = 31 * result + height.hashCode()
        result = 31 * result + urlToSite.hashCode()
        result = 31 * result + image.hashCode()
        result = 31 * result + duration.hashCode()
        result = 31 * result + user.hashCode()
        result = 31 * result + videoFiles.hashCode()
        result = 31 * result + videoPictures.hashCode()
        return result
    }
}
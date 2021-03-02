package studio.lifehack.test.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import studio.lifehack.test.BuildConfig

data class Company(

    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("img")
    @Expose
    var imageUrl: String,

    @SerializedName("description")
    @Expose
    val desc: String,

    @SerializedName("lat")
    @Expose
    val lat: Double,

    @SerializedName("lon")
    @Expose
    val lon: Double,

    @SerializedName("www")
    @Expose
    val www: String?,

    @SerializedName("phone")
    @Expose
    val phone: String?
) {
    fun getFullImageUrl() = "${BuildConfig.BASE_URL}test_task/$imageUrl"
}
package com.example.cafebrown.data.models.verify

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "userInfo"
)
data class PostVerificationCodeResponse(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @SerializedName("name")
    val firstName: String,
    @SerializedName("family")
    val lastName: String,
    @SerializedName("birthDate")
    val birthDate: String?,
    @SerializedName("sex")
    val sex: Boolean,
    @SerializedName("mobile")
    val mobile: String,
    @SerializedName("accessToken")
    val accessToken: String
)

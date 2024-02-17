package com.rustam.bookshelf.model

import com.google.gson.annotations.SerializedName


data class Pdf (

  @SerializedName("isAvailable" ) var isAvailable : Boolean? = null

)
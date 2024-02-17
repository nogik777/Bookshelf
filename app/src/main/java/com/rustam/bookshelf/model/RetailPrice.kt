package com.rustam.bookshelf.model

import com.google.gson.annotations.SerializedName


data class RetailPrice (

  @SerializedName("amountInMicros" ) var amountInMicros : Int?    = null,
  @SerializedName("currencyCode"   ) var currencyCode   : String? = null

)
package com.rustam.bookshelf.model

import com.google.gson.annotations.SerializedName


data class SearchInfo (

  @SerializedName("textSnippet" ) var textSnippet : String? = null

)
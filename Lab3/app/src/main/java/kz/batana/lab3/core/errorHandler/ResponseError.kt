/*
 * Copyright (c) DAR Ecosystem 2018.
 *
 * Created by sough on 14/08/2018.
 */
package kz.batana.lab3.core.errorHandler

import com.google.gson.annotations.SerializedName

/**
 *
 */
data class ResponseError(
        @SerializedName("status") val status: String,
        @SerializedName("message") val message: String
)
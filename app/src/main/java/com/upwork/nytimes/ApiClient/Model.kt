package com.upwork.nytimes.ApiClient

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

enum class ResponseCode constructor(val code: Int) {
    OK(200),
    BadRequest(400),
    Unauthenticated(401),
    Unauthorized(403),
    NotFound(404),
    RequestTimeOut(408),
    Conflict(409),
    InValidateData(422),
    Blocked(423),
    ForceUpdate(426),
    ServerError(500);
}

@Keep
data class BaseModelResponse<T>(
    var status: Boolean,
    val copyright: String = "",
    val num_results: Int,
    val results: T? = null
) : Serializable

@Keep
data class BaseListResponse<T>(
    var status: Boolean,
    val copyright: String = "",
    val num_results: Int,
    val results: ArrayList<T>? = null
) : Serializable

@Keep
@Parcelize
data class MostPopularResponse(
    val status: String? = "",
    val copyright: String? = "",
    val fault: FaultData? = null,
    val results: ArrayList<MostPopularData>? = arrayListOf(),
    val num_results: Int? = 0
) : Parcelable

@Keep
@Parcelize
data class FaultData(
    val faultstring: String? = ""
) : Parcelable


@Keep
@Parcelize
data class MostPopularData(
    val uri: String? = "",
    val url: String? = "",
    val id: String? = "",
    val asset_id: String? = "",
    val source: String? = "",
    var published_date: String? = "",
    val updated: String? = "",
    val section: String? = "",
    var subsection: String? = "",
    val nytdsection: String? = "",
    val adx_keywords: String? = "",
    val column: String? = "",
    val byline: String? = "",
    val type: String? = "",
    val title: String? = "",
    val abstract: String? = "",
    val des_facet: ArrayList<String>? = arrayListOf(),
    val org_facet: ArrayList<String>? = arrayListOf(),
    val per_facet: ArrayList<String>? = arrayListOf(),
    val geo_facet: ArrayList<String>? = arrayListOf(),
    val media: ArrayList<ModelMedia>? = arrayListOf(),
    val eta_id: Int? = 0
) : Parcelable


@Keep
@Parcelize
data class ModelMedia(
    val type: String? = "",
    val subtype: String? = "",
    val caption: String? = "",
    val copyright: String? = "",
    val approved_for_syndication: Int? = 0
) : Parcelable




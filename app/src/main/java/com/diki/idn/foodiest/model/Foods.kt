package com.diki.idn.foodiest.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Foods(
    var name: String,
    var place: String,
    var kind: String,
    var desc: String,
    var image: Int
) : Parcelable
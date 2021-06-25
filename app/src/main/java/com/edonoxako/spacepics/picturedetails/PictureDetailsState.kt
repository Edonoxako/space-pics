package com.edonoxako.spacepics.picturedetails

sealed class PictureDetailsState {

    data class Loaded(
        val pictureDetails: PictureDetails
    ) : PictureDetailsState()

    data class Error(
        val errorReason: String
    ) : PictureDetailsState()

    object InProgress : PictureDetailsState()

    object Undefined : PictureDetailsState()
}
package com.edonoxako.spacepics.picturedetails

import com.edonoxako.spacepics.PictureDetailsProto
import com.edonoxako.spacepics.data.network.ApodDto

//todo do something with method names
class PictureDetailsMapper {

    fun map(apodDto: ApodDto): PictureDetails {
        return PictureDetails(
            pictureTitle = requireNotNull(apodDto.title) { "Title is empty" },
            pictureExplanation = requireNotNull(apodDto.explanation) { "Explanation is empty" },
            pictureUrl = requireNotNull(apodDto.url) { "Picture url is empty" }
        )
    }

    fun map(detailsProto: PictureDetailsProto): PictureDetails {
        return PictureDetails(
            pictureTitle = requireNotNull(detailsProto.pictureTitle) { "Title is empty" },
            pictureExplanation = requireNotNull(detailsProto.pictureExplanation) {
                "Explanation is empty"
            },
            pictureUrl = requireNotNull(detailsProto.pictureUrl) { "Picture url is empty" }
        )
    }

    fun map(pictureDetails: PictureDetails): PictureDetailsProto {
        return PictureDetailsProto.newBuilder()
            .setPictureTitle(pictureDetails.pictureTitle)
            .setPictureExplanation(pictureDetails.pictureExplanation)
            .setPictureUrl(pictureDetails.pictureUrl)
            .build()
    }
}
package com.edonoxako.spacepics.picturedetails

import com.edonoxako.spacepics.data.network.ApodDto

class PictureDetailsMapper {

    fun map(apodDto: ApodDto): PictureDetails {
        return PictureDetails(
            pictureTitle = requireNotNull(apodDto.title) { "Title is empty" },
            pictureExplanation = requireNotNull(apodDto.explanation) { "Explanation is empty" },
            pictureUrl = requireNotNull(apodDto.url) { "Picture url is empty" }
        )
    }
}
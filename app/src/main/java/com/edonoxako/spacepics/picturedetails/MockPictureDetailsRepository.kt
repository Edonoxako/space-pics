package com.edonoxako.spacepics.picturedetails

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class MockPictureDetailsRepository : RemotePictureDetailsRepository {

    override suspend fun getPictureDetails(): PictureDetails {
        return withContext(Dispatchers.IO) {
            delay(5000L)
            MOCK_PICTURE_DETAILS
        }
    }

    companion object {

        private const val MOCK_TITLE = "A Supercell Thunderstorm Over Texas"

        private val MOCK_EXPLANATION =
            """Is that a cloud or an alien spaceship?  It's an unusual and 
    sometimes dangerous type of
    thunderstorm cloud called a supercell. Supercells may spawn damaging tornados, hail, downbursts 
    of air, or drenching rain.  Or they may just look impressive. A supercell harbors a mesocyclone
    -- a rising column of air surrounded by drafts of falling air.  Supercells could occur over many 
    places on Earth but are particularly common in  Tornado Alley of the USA. Featured here are four 
    time-lapse sequences of a supercell in 2013 rotating above and moving across Booker, Texas.  
    Captured in the video are new clouds forming near the storm center, dust swirling on the ground, 
    lightning flashing in the upper clouds, all while the impressively sculptured complex rotates
    ominously.  Finally, after a few hours, as shown in the final sequence, dense rain falls as the 
    storm begins to die out.   Notable images submitted to APOD: Last week's solar eclipse""".trim()

        private const val MOCK_PICTURE_URL =
            "https://apod.nasa.gov/apod/image/2106/AndromedaGalaxy-SingleShotMina-4688-net1200.jpg"

        private val MOCK_PICTURE_DETAILS = PictureDetails(
            pictureTitle = MOCK_TITLE,
            pictureExplanation = MOCK_EXPLANATION,
            pictureUrl = MOCK_PICTURE_URL
        )
    }
}
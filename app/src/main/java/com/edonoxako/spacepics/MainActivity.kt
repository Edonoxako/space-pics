package com.edonoxako.spacepics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.edonoxako.spacepics.picturedetails.PictureDetailsState
import com.edonoxako.spacepics.picturedetails.PictureDetailsViewModel
import com.edonoxako.spacepics.ui.theme.SpacePicsTheme
import com.edonoxako.spacepics.ui.theme.spacePicsTypography

class MainActivity : ComponentActivity() {

    private val viewModel: PictureDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpacePicsTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val state = viewModel.pictureDetailsState.observeAsState()
                    when(val pictureDetailsState = state.value) {
                        is PictureDetailsState.Loaded -> PictureDetails(
                            title = pictureDetailsState.pictureDetails.pictureTitle,
                            explanation = pictureDetailsState.pictureDetails.pictureExplanation
                        )
                        is PictureDetailsState.InProgress -> ProgressSpinner()
                        is PictureDetailsState.Error -> Error(errorReson = pictureDetailsState.errorReason)
                        is PictureDetailsState.Undefined -> Error(errorReson = "Nothing to show")
                    }
                }
            }
        }
    }
}

@Composable
fun ProgressSpinner() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth().fillMaxHeight()
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun Error(errorReson: String) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth().fillMaxHeight()
    ) {
        Text(text = errorReson)
    }
}

@Composable
fun PictureDetails(title: String, explanation: String) {
    Box {
        NasaImage()
        Surface(
            color = Color.White,
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(top = (280 - 16).dp), //todo remove magic numbers and hardcode
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            elevation = 4.dp
        ) {
            Column(modifier = Modifier.padding(all = 16.dp)) {
                Spacer(modifier = Modifier.height(16.dp))
                Title(title = title)
                Spacer(modifier = Modifier.height(16.dp))
                Explanation(explanation = explanation)
            }
        }
    }
}

@Composable
fun NasaImage() {
    Image(
        painter = ColorPainter(Color.Blue),
        contentScale = ContentScale.Crop,
        contentDescription = null,
        modifier = Modifier
            .height(280.dp)
            .fillMaxWidth()
    )
}

@Composable
fun Title(title: String) {
    Text(
        text = title,
        style = spacePicsTypography.h5,
    )
}

@Composable
fun Explanation(explanation: String) {
    Text(
        text = explanation,
        style = spacePicsTypography.body1
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SpacePicsTheme {
        PictureDetails(
            title = MOCK_TITLE,
            explanation = MOCK_EXPLANATION
        )
    }
}

private const val MOCK_TITLE = "A Supercell Thunderstorm Over Texas"
private val MOCK_EXPLANATION = """Is that a cloud or an alien spaceship?  It's an unusual and 
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
package me.turkergoksu.kefilm.now_playing.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.google.accompanist.coil.rememberCoilPainter
import me.turkergoksu.kefilm.Constants
import me.turkergoksu.kefilm.R
import me.turkergoksu.kefilm.now_playing.data.NowPlayingMovieItem
import me.turkergoksu.kefilm.theme.KefilmTheme

/**
 * Created by turkergoksu on 18-Jun-21.
 */
@ExperimentalMaterialApi
@Preview
@Composable
fun PreviewNowPlayingMovieItem() {
    val item = NowPlayingMovieItem(
        id = 0,
        posterPath = "",
        title = "The Irishman"
    )
    KefilmTheme {
        NowPlayingMovieItem(nowPlayingMovieItem = item) { }
    }
}

@ExperimentalMaterialApi
@Composable
fun NowPlayingMovieItem(nowPlayingMovieItem: NowPlayingMovieItem, onClick: () -> Unit) {
    ConstraintLayout(
        modifier = Modifier
            .width(LocalConfiguration.current.screenWidthDp.dp)
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 16.dp,
                bottom = 82.dp
            ) //TODO Bottom nav height = 50dp, paddings 16.dp = 82
    ) {
        val (card, text) = createRefs()

        Card(
            elevation = 3.dp,
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier
                .constrainAs(card) {
                    top.linkTo(parent.top)
                    bottom.linkTo(text.top, margin = 8.dp)
                    height = Dimension.preferredWrapContent
                },
            onClick = onClick
        ) {
            Image(
                painter = rememberCoilPainter(
                    request = Constants.API_IMAGE_URL + nowPlayingMovieItem.posterPath,
                    previewPlaceholder = R.drawable.dummy_movie_poster,
                    fadeIn = true
                ),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
//        Text(
//            text = nowPlayingMovieItem.title,
//            color = Color.Red,
//            maxLines = 1,
//            style = MaterialTheme.typography.h5,
//            overflow = TextOverflow.Ellipsis,
//            textAlign = TextAlign.Center,
//            modifier = Modifier.constrainAs(text) {
//                bottom.linkTo(parent.bottom)
//                start.linkTo(parent.start)
//                end.linkTo(parent.end)
//            }
//        )
    }
}
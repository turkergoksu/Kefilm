package me.turkergoksu.kefilm.movie_detail.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.coil.rememberCoilPainter

/**
 * Created by turkergoksu on 21-Jun-21.
 */
@Composable
fun MovieDetailScreen(navController: NavController, viewModel: MovieDetailViewModel, movieId: Int) {

    viewModel.fetchMovieDetail(movieId)

    val viewState = viewModel.movieDetail.observeAsState().value
    if (viewState != null) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            val widthDp = LocalConfiguration.current.screenWidthDp.dp
            Row(verticalAlignment = Alignment.CenterVertically) {
                // SideInfo
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(0.dp)
                        .width(widthDp / 3),
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(32.dp)
                            .border(
                                width = 1.dp,
                                color = viewState.voteAverageColor(),
                                shape = RoundedCornerShape(4.dp)
                            )
                    ) {
                        Text(
                            text = viewState.voteAverageText(),
                            fontSize = 14.sp,
                            color = viewState.voteAverageColor(),
                        )
                    }
                    Divider(
                        color = Color.Black,
                        modifier = Modifier
                            .alpha(alpha = .5f)
                            .width(48.dp)
                            .padding(vertical = 16.dp)
                    )
                    Text(
                        text = viewState.releaseYear(),
                        fontSize = 14.sp,
                    )
                    Divider(
                        color = Color.Black,
                        modifier = Modifier
                            .alpha(alpha = .5f)
                            .width(48.dp)
                            .padding(vertical = 16.dp)
                    )
                    Text(
                        text = viewState.runtimeText(),
                        fontSize = 14.sp,
                    )
                    Divider(
                        color = Color.Black,
                        modifier = Modifier
                            .alpha(alpha = .5f)
                            .width(48.dp)
                            .padding(vertical = 16.dp)
                    )
                    Text(
                        text = viewState.budgetText(),
                        fontSize = 14.sp,
                    )
                }

                // Poster
                Card(
                    elevation = 3.dp,
                    shape = RoundedCornerShape(bottomStart = 96.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(450.dp)
                ) {
                    Image(
                        painter = rememberCoilPainter(
                            request = viewState.posterUrl(),
//                        previewPlaceholder = R.drawable.dummy_movie_poster,
                            fadeIn = true
                        ),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )
                }
            }

            // MainInfo
            Column(
                modifier = Modifier.padding(start = widthDp / 3, top = 16.dp)
            ) {
                Text(text = viewState.title(), fontSize = 20.sp)
                Text(
                    text = viewState.genreText(),
                    fontSize = 12.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .alpha(.5f)
                )
                Text(
                    text = viewState.overview(),
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 8.dp, end = 8.dp),
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}
package me.turkergoksu.kefilm.popular.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.google.accompanist.coil.rememberCoilPainter
import me.turkergoksu.kefilm.Constants
import me.turkergoksu.kefilm.R
import me.turkergoksu.kefilm.popular.data.PopularMovieItem
import me.turkergoksu.kefilm.theme.KefilmTheme

/**
 * Created by turkergoksu on 23-Jun-21.
 */
@Preview
@Composable
fun PreviewPopularMovieItem() {
    val item = PopularMovieItem(
        id = 0,
        backdropPath = "/z2UtGA1WggESspi6KOXeo66lvLx.jpg",
        title = "The Irishman",
        popularity = 1234,
    )
    KefilmTheme {
        PopularMovieItem(item = item) {

        }
    }
}

@Composable
fun PopularMovieItem(
    item: PopularMovieItem,
    paddingValues: PaddingValues = PaddingValues(all = 16.dp),
    onClick: (PopularMovieItem) -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .padding(paddingValues)
            .clickable {
                onClick(item)
            },
    ) {
        val (backdrop, title, popularity) = createRefs()

        Card(shape = RoundedCornerShape(16.dp), modifier = Modifier.constrainAs(backdrop) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }) {
            Image(
                painter = rememberCoilPainter(
                    request = Constants.API_IMAGE_URL + item.backdropPath,
                    previewPlaceholder = R.drawable.dummy_movie_poster,
                    fadeIn = true
                ),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(150.dp)
            )
        }

        Text(
            text = item.title,
            color = Color.White,
            fontSize = 14.sp,
            maxLines = 1,
            modifier = Modifier
                .background(
                    color = Color.Black,
                    shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
                )
                .padding(8.dp)
                .constrainAs(title) {
                    bottom.linkTo(backdrop.bottom)
                    start.linkTo(backdrop.start)
                    end.linkTo(backdrop.end)
                    width = Dimension.fillToConstraints
                }
        )
    }
}
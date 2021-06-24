package me.turkergoksu.kefilm.popular.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
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
import me.turkergoksu.kefilm.theme.PopularMovieItemPopularityIconTint
import me.turkergoksu.kefilm.theme.PopularMovieItemTitleBackground

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
    paddingValues: PaddingValues = PaddingValues(vertical = 8.dp),
    onClick: (PopularMovieItem) -> Unit
) {
    ConstraintLayout(modifier = Modifier.padding(paddingValues)) {
        val (backdrop, title, popularity) = createRefs()

        Card(shape = RoundedCornerShape(16.dp), modifier = Modifier.constrainAs(backdrop) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }) {
            Image(
                painter = rememberCoilPainter(
                    request = Constants.API_W500_IMAGE_URL + item.backdropPath,
                    previewPlaceholder = R.drawable.dummy_movie_poster,
                    fadeIn = true
                ),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(225.dp)
                    .clickable {
                        onClick(item)
                    }
            )
        }

        Text(
            text = item.title,
            color = Color.White,
            fontSize = 18.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .background(
                    color = PopularMovieItemTitleBackground,
                    shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
                )
                .padding(16.dp)
                .constrainAs(title) {
                    bottom.linkTo(backdrop.bottom)
                    start.linkTo(backdrop.start)
                    end.linkTo(backdrop.end)
                    width = Dimension.fillToConstraints
                }
        )

        Row(
            modifier = Modifier
                .padding(8.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .background(color = PopularMovieItemTitleBackground)
                .constrainAs(popularity) {
                    top.linkTo(backdrop.top)
                    end.linkTo(backdrop.end)
                }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_popularity),
                contentDescription = null,
                tint = PopularMovieItemPopularityIconTint,
                modifier = Modifier.padding(start = 4.dp, top = 4.dp, bottom = 4.dp)
            )
            Text(
                text = item.popularity.toString(),
                color = Color.White,
                fontSize = 14.sp,
                maxLines = 1,
                modifier = Modifier.padding(start = 4.dp, top = 8.dp, bottom = 8.dp, end = 8.dp)
            )
        }
    }
}
package me.turkergoksu.kefilm.search.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.coil.rememberCoilPainter
import me.turkergoksu.kefilm.Constants
import me.turkergoksu.kefilm.R
import me.turkergoksu.kefilm.common.navigation.Screen

/**
 * Created by turkergoksu on 14-Aug-21.
 */
@Preview
@Composable
fun PreviewSearchScreen() {
//    SearchScreen()
}

@Composable
fun SearchScreen(navController: NavController, viewModel: SearchViewModel) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp)) {
        SearchField(viewModel)

        val data = viewModel.searchItemList.observeAsState().value ?: listOf()
        if (data.isNotEmpty()) {
            LazyColumn(contentPadding = PaddingValues()) {
                items(data.size) { index ->
                    val itemPadding = when (index) {
                        0 -> PaddingValues(bottom = 4.dp)
                        data.lastIndex -> PaddingValues(top = 4.dp, bottom = 8.dp)
                        else -> PaddingValues(vertical = 4.dp)
                    }

                    val searchItem = data[index]
                    SearchCardItem(
                        title = searchItem.title,
                        posterPath = searchItem.posterPath,
                        releaseYear = searchItem.releaseYear,
                        voteAverage = searchItem.voteAverage,
                        padding = itemPadding,
                    ) {
                        navController.navigate(Screen.MovieDetail.route(movieId = searchItem.id))
                    }
                }
            }
        }
    }
}

@Composable
private fun SearchCardItem(
    title: String,
    posterPath: String,
    releaseYear: Int,
    voteAverage: Double,
    padding: PaddingValues = PaddingValues(),
    onClick: () -> Unit,
) {

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.padding(padding)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }) {
            Card(shape = RoundedCornerShape(8.dp)) {
                Image(
                    painter = rememberCoilPainter(
                        request = Constants.API_IMAGE_URL + posterPath,
                        previewPlaceholder = R.drawable.dummy_movie_poster,
                        fadeIn = true
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(64.dp)
                        .height(64.dp)
                )
            }

            Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                Row {
                    Text(
                        text = title,
                        fontSize = 16.sp,
                        maxLines = 1,
                        color = Color.White,
                        style = MaterialTheme.typography.body1,
                    )
                    Text(
                        text = "($releaseYear)",
                        fontSize = 10.sp,
                        maxLines = 1,
                        color = Color.LightGray,
                        style = MaterialTheme.typography.subtitle1,
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
            }
        }
    }
}

@Composable
private fun SearchField(viewModel: SearchViewModel) {
    var text by remember { mutableStateOf(viewModel.query.value.orEmpty()) }
    val focusManager = LocalFocusManager.current

    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth()
    ) {
        Row {
            TextField(
                leadingIcon = {
                    Image(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(Color.LightGray),
                    )
                },
                value = text,
                onValueChange = { text = it },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.search_hint),
                        color = Color.LightGray
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
                keyboardActions = KeyboardActions(onSearch = {
                    viewModel.search(text)
                    focusManager.clearFocus()
                }),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(
                            listOf(
                                Color(0xff07070a),
                                Color(0xff24272b)
                            )
                        )
                    ),
            )
        }
    }
}
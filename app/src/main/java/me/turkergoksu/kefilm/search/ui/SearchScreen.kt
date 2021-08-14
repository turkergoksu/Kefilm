package me.turkergoksu.kefilm.search.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.turkergoksu.kefilm.R

/**
 * Created by turkergoksu on 14-Aug-21.
 */
@Composable
fun SearchScreen() {
    SearchCard()
}

@Preview
@Composable
fun PreviewSearchScreen() {
    SearchScreen()
}

@Composable
fun SearchCard() {
    var text by remember { mutableStateOf("") }

    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth()
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
                        text = stringResource(id = R.string.search),
                        color = Color.LightGray
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(
                            listOf(
                                Color(0xffa18cd1),
                                Color(0xfffbc2eb)
                            )
                        )
                    ),
            )
        }
    }
}
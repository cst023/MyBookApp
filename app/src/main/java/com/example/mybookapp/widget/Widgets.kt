package com.example.mybookapp.widget

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*



import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*



import androidx.compose.ui.graphics.RectangleShape

import androidx.compose.ui.text.buildAnnotatedString

import androidx.compose.ui.text.withStyle

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


import androidx.compose.material3.Card
import androidx.compose.material3.Icon

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import coil.compose.rememberAsyncImagePainter


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import coil.transform.CircleCropTransformation

import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.LocalContext
import coil.request.ImageRequest
import com.example.mybookapp.data.Book
import com.example.mybookapp.data.getBook


@ExperimentalAnimationApi
@Composable
fun BookRow(
    book: Book = getBook()[0],
    onItemClick: (String) -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable {
                onItemClick(book.id)
            },
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        BoxWithConstraints {
            val isCompact = maxWidth < 300.dp

            if (isCompact) {
                // Column layout for compact spaces
                Column(
                    modifier = Modifier.padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    BookImage(book)
                    BookDetails(book, expanded, onExpandChange = { expanded = !expanded })
                }
            } else {
                // Row layout for wider spaces
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.padding(12.dp)
                ) {
                    BookImage(book)
                    BookDetails(book, expanded, onExpandChange = { expanded = !expanded })
                }
            }
        }
    }
}

@Composable
private fun BookImage(book: Book) {
    Surface(
        modifier = Modifier
            .size(100.dp),
        shape = RectangleShape
    ) {
        Image(//bookImage
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(data = book.poster)
                    .apply {
                        crossfade(true)
                        transformations(CircleCropTransformation())
                    }
                    .build()
            ),
            contentDescription = "Book Poster"
        )
    }
}

@Composable
private fun BookDetails(
    book: Book,
    expanded: Boolean,
    onExpandChange: () -> Unit
) {
    Column(modifier = Modifier.padding(4.dp)) {
        Text(text = book.name, style = MaterialTheme.typography.bodyMedium)
        Text(text = "Author: ${book.author}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Rating: ${book.rating}", style = MaterialTheme.typography.bodyMedium)

        AnimatedVisibility(visible = expanded) {
            Column {
                HorizontalDivider(modifier = Modifier.padding(3.dp))

                Text(buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 13.sp)) {
                        append("Description: ")
                    }
                    withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 13.sp, fontWeight = FontWeight.Light)) {
                        append(book.description)
                    }
                })

            }
        }

        Icon(
            imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
            contentDescription = "Toggle Details",
            modifier = Modifier
                .size(25.dp)
                .clickable { onExpandChange() },
            tint = Color.DarkGray
        )
    }
}



@OptIn(ExperimentalAnimationApi::class)
@Preview(showBackground = true)
@Composable
fun BookRowPreview(){

    BookRow()

}
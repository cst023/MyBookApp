package com.example.mybookapp.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mybookapp.data.getBook
import com.example.mybookapp.widget.BookRow

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mybookapp.BookViewModel

@ExperimentalAnimationApi
@Composable
fun DetailScreen(
    navController: NavController,
    bookId: String?,
    bookViewModel: BookViewModel  // Add ViewModel here
) {
    val bookList = bookViewModel.getBooks()
    val selectedBook = bookList.firstOrNull { it.id == bookId } // Get the selected book
    var commentText by remember { mutableStateOf("") }

    Scaffold(
        topBar = { AddAppBar(navController) },
        content = { padding ->
            Surface(modifier = Modifier.padding(padding)) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    if (selectedBook != null) {
                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = selectedBook.description
                        )

                        BookRow(book = selectedBook)
                        Spacer(modifier = Modifier.height(8.dp))
                        HorizontalDivider()
                        Text(text = "Reviews")

                        Spacer(modifier = Modifier.height(8.dp))

                        // Comments Section
                        Column(modifier = Modifier.padding(8.dp)) {
                            Text(text = "Add a comment:")

                            // TextField for adding comment
                            OutlinedTextField(
                                value = commentText,
                                onValueChange = { commentText = it },
                                label = { Text(text = "Write a comment") },
                                modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            // Button to submit the comment
                            Button(onClick = {
                                if (commentText.isNotEmpty()) {
                                    // Use ViewModel to add the comment
                                    bookViewModel.addComment(bookId!!, commentText)
                                    commentText = "" // Reset text field
                                }
                            }) {
                                Text(text = "Submit")
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            // Displaying the list of comments
                            Text(text = "Comments:")
                            selectedBook.comments.forEach { comment ->
                                Text(text = comment, modifier = Modifier.padding(12.dp))
                                Divider()
                            }
                        }
                    }
                }
            }
        }
    )
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAppBar(navController: NavController) {
    TopAppBar(
        {
            Row(horizontalArrangement = Arrangement.Start) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Arrow Back",
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    })
                Spacer(modifier = Modifier.width(100.dp))
                Text(text = "Book details")
            }
        }
    )
}

/*
@Preview(showBackground = true)
@ExperimentalAnimationApi
@Composable
fun DetailScreenPreview() {
    DetailScreen(rememberNavController(), "r1")
}*/

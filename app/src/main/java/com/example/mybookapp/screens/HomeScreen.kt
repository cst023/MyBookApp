package com.example.mybookapp.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.smallTopAppBarColors
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.mybookapp.BookViewModel
import com.example.mybookapp.data.Book
import com.example.mybookapp.data.getBook
import com.example.mybookapp.navigation.BookScreens
import com.example.mybookapp.widget.BookRow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, bookViewModel: BookViewModel){

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("MyBookCollection") },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Blue,
                    titleContentColor = Color.White
                )
            )},
        content = { padding ->
            Surface(
                modifier = Modifier.padding(padding),
            ) {
                MainContent(navController = navController)
            }
        }
    )

}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainContent(navController: NavController,
                bookList: List<Book> = getBook()) {
    Column(modifier = Modifier.padding(12.dp)) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 150.dp),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(bookList) { book ->
                BookRow(book = book) { selectedBook ->
                    navController.navigate(route = BookScreens.DetailScreen.name + "/$selectedBook")
                }
            }
        }
    }
}



/*
@Preview(showBackground = true)
@Composable
fun DefaultPreview(){

    HomeScreen(rememberNavController())


}*/
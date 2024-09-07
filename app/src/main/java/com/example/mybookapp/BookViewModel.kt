package com.example.mybookapp

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.mybookapp.data.Book
import com.example.mybookapp.data.getBook

class BookViewModel : ViewModel() {
    private val books = mutableStateListOf(*getBook().toTypedArray()) // Use state list to track changes

    fun getBooks(): SnapshotStateList<Book> {
        return books
    }

    fun addComment(bookId: String, comment: String) {
        val book = books.firstOrNull { it.id == bookId }
        book?.comments?.add(0, " $comment") // Prepend the comment
    }
}

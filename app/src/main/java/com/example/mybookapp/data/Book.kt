package com.example.mybookapp.data

data class Book(
    val id: String,
    val name: String,
    val description: String,
    val author: String,
    val rating: String,
    val poster: String,
    var comments: MutableList<String> = mutableListOf() // comments list
)


fun getBook(): List<Book> {
    return listOf(
        Book(id = "r1",
            name = "The Little Prince",
            description = "A poetic and philosophical tale about a pilot who meets a young prince from another planet, exploring themes of love, loss, and the importance of imagination.",
            author = "Antoine de Saint-Exupéry",
            poster = "https://m.media-amazon.com/images/I/71OZY035QKL._AC_UF1000,1000_QL80_.jpg",
            rating = "4.5/5",
            comments = mutableListOf( // Add dummy comments here
                "Inspiring book.",
                "A timeless classic",
                "The illustrations were beautiful."
            )
        ),
        Book(id = "r2",
            name = "The Lord of the Rings",
            description = "An epic high fantasy trilogy about a hobbit who must destroy a powerful ring to save Middle-earth from the Dark Lord Sauron.",
            author = "J.R.R. Tolkien",
            poster = "https://m.media-amazon.com/images/I/512zVlD070L._AC_UF1000,1000_QL80_.jpg",
            rating = "4.7/5"),


        Book(id = "r3",
            name = "To Kill a Mockingbird",
            description = "A poignant coming-of-age story set in the American South that explores themes of racial injustice and childhood innocence.",
            author = "Harper Lee",
            poster = "https://cdn.kobo.com/book-images/63901e1f-e685-4659-8ba8-d1eab571a31e/1200/1200/False/to-kill-a-mockingbird-3.jpg",
            rating = "4.5/5"),

        Book(id = "r4",
            name = "One Hundred Years of Solitude",
            description = "A multi-generational saga of the Buendía family in the fictional town of Macondo, Colombia, blending magical realism with historical events.",
            author = "Gabriel García Márquez",
            poster = "https://www.blackstonepublishing.com/cdn/shop/files/90821-fvdb-Rectangle.jpg?v=1706147486",
            rating = "4.4/5"
        ),

        Book(id = "r5",
            name = "The Alchemist",
            description = "An inspirational story about a young shepherd who follows his dreams and travels to Egypt in search of treasure, learning valuable life lessons along the way.",
            author = "Paulo Coelho",
            poster = "https://cdn.kobo.com/book-images/5967e7fb-edc8-403b-9989-f8aab7b3ed89/1200/1200/False/the-alchemist-38.jpg",
            rating = "3.8/5"
        ),

        )

}
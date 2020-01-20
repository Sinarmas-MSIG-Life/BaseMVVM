package com.rifafauzi.basemvvm

import com.rifafauzi.basemvvm.model.Movies

/**
 * Created by rrifafauzikomara on 2020-01-20.
 */

object FakeData {

    fun getDummyListMovies() : List<Movies> {
        return listOf(
            Movies(
                743.027,
                1927,
                "/xBHvZcjRiWyobQ9kxBhO6B2dtRI.jpg",
                419704,
                "/5BwqwxMEjeFtdknRV792Svo0K1v.jpg",
                "Ad Astra",
                6.0,
                "The near future, a time when both hope and hardships drive humanity to look to the stars and beyond. While a mysterious phenomenon menaces to destroy life on planet Earth, astronaut Roy McBride undertakes a mission across the immensity of space and its many perils to uncover the truth about a lost expedition that decades before boldly faced emptiness and silence in search of the unknown.",
                "2019-09-17"
            )
        )
    }

}
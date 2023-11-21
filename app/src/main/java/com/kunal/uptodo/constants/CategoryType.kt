package com.kunal.uptodo.constants

import androidx.annotation.StringDef
import com.kunal.uptodo.constants.CategoryType.Companion.CREATE_NEW
import com.kunal.uptodo.constants.CategoryType.Companion.DESIGN
import com.kunal.uptodo.constants.CategoryType.Companion.GROCERY
import com.kunal.uptodo.constants.CategoryType.Companion.HEALTH
import com.kunal.uptodo.constants.CategoryType.Companion.HOME
import com.kunal.uptodo.constants.CategoryType.Companion.MOVIE
import com.kunal.uptodo.constants.CategoryType.Companion.MUSIC
import com.kunal.uptodo.constants.CategoryType.Companion.SOCIAL
import com.kunal.uptodo.constants.CategoryType.Companion.SPORT
import com.kunal.uptodo.constants.CategoryType.Companion.UNIVERSITY
import com.kunal.uptodo.constants.CategoryType.Companion.WORK

@Retention(AnnotationRetention.SOURCE)
@StringDef(
    GROCERY,
    WORK,
    SPORT,
    DESIGN,
    UNIVERSITY,
    SOCIAL,
    MUSIC,
    HEALTH,
    MOVIE,
    HOME,
    CREATE_NEW
)
annotation class CategoryType {
    companion object {
        const val GROCERY = "Grocery"
        const val WORK = "Work"
        const val SPORT = "Sport"
        const val DESIGN = "Design"
        const val UNIVERSITY = "University"
        const val SOCIAL = "Social"
        const val MUSIC = "Music"
        const val HEALTH = "Health"
        const val MOVIE = "Movie"
        const val HOME = "Home"
        const val CREATE_NEW = "Create New"
    }
}

package com.kunal.uptodo.data

import com.kunal.uptodo.R
import com.kunal.uptodo.constants.CategoryType
import com.kunal.uptodo.models.CategoryListModel

class CategoryList {
    fun getCategoryList(): List<CategoryListModel> {
        return listOf(
            CategoryListModel(
                R.drawable.ic_grocery,
                CategoryType.GROCERY
            ),
            CategoryListModel(
                R.drawable.ic_work,
                CategoryType.WORK
            ),
            CategoryListModel(
                R.drawable.ic_sport,
                CategoryType.SPORT
            ),
            CategoryListModel(
                R.drawable.ic_design,
                CategoryType.DESIGN
            ),
            CategoryListModel(
                R.drawable.ic_university,
                CategoryType.UNIVERSITY
            ),
            CategoryListModel(
                R.drawable.ic_social,
                CategoryType.SOCIAL
            ),
            CategoryListModel(
                R.drawable.ic_music,
                CategoryType.MUSIC
            ),
            CategoryListModel(
                R.drawable.ic_health,
                CategoryType.HEALTH
            ),
            CategoryListModel(
                R.drawable.ic_movie,
                CategoryType.MOVIE
            ),
            CategoryListModel(
                R.drawable.ic_category_home,
                CategoryType.HOME
            ),
            CategoryListModel(
                R.drawable.ic_new_category,
                CategoryType.CREATE_NEW
            )
        )
    }
}
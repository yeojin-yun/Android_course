package com.example.recipeappstudy
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val id: Int,
    val name: String,
    val image: String?,
    val description: String?,
    val subcategories: List<SubCategory>
): Parcelable {}
@Parcelize
data class SubCategory(
    val id: Int,
    val name: String,
    val image: String?,
    val description: String?
):Parcelable {}

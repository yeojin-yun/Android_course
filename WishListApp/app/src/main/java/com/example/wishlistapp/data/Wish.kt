package com.example.wishlistapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wish-table")
data class Wish(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "wish-title")
    val title: String = "",
    @ColumnInfo(name = "wish-description")
    val description: String = ""
)

object DummyList {
    val wishList = listOf(
        Wish(title = "면기", description = "면기를 제일 잘 쓰는데 2개 밖에 없음"),
        Wish(title = "킨 재스퍼 운동화", description = "나하고 어울리고 너무 편함. 근데 사이즈 없음"),
        Wish(title = "반스 운동화", description = "그냥 사고 싶음 이유 없을 무")
    )
}

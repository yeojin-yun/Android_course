package com.example.wishlistapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class WishDao {

    //아이템 추가 어노테이션(충돌 시에는 무시하는 전략)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addAWish(wishEntity: Wish)

    //load all wishes from table
    //이미 coroutines에서 제공하는 Flow를 사용하기 때문에 suspend가 따로 필요없음
    @Query("Select * from `wish-table`")
    abstract fun getAllWishes() : Flow<List<Wish>>

    //item update
    @Update
    abstract suspend fun updateAWish(wishEntity: Wish)

    //item delete
    abstract suspend fun deleteAWish(wishEntity: Wish)

    //load one item by id
    //이미 coroutines에서 제공하는 Flow를 사용하기 때문에 suspend가 따로 필요없음
    @Query("Select * from `wish-table` where id=:id")
    abstract fun getAWishById(id: Long) : Flow<Wish>
}
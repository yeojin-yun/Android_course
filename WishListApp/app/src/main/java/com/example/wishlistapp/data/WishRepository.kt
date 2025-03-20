package com.example.wishlistapp.data

import kotlinx.coroutines.flow.Flow

class WishRepository(private val wishDao: WishDao) {
    suspend fun addWish(wish: Wish) {
        wishDao.addAWish(wish)
    }

    fun getWishes(): Flow<List<Wish>> = wishDao.getAllWishes()

    fun getWishById(id: Long): Flow<Wish> = wishDao.getAWishById(id)

    suspend fun updateWish(wish: Wish) {
        wishDao.updateAWish(wish)
    }

    suspend fun deleteWish(wish: Wish) {
        wishDao.deleteAWish(wish)
    }
}
package com.example.ramrojutta.repository;

import com.example.ramrojutta.Model.BrandModel
import com.example.ramrojutta.Model.ItemsModel
import com.example.ramrojutta.Model.SliderModel
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class DataRepository {
    private val database = Firebase.database

    suspend fun getBanners(): List<SliderModel> {
        val dataSnapshot = database.getReference("Banner").get().await()
        return dataSnapshot.children.mapNotNull { it.getValue(SliderModel::class.java) }
    }

    suspend fun getBrands(): List<BrandModel> {
        val dataSnapshot = database.getReference("Category").get().await()
        return dataSnapshot.children.mapNotNull { it.getValue(BrandModel::class.java) }
    }

    suspend fun getPopular():List<ItemsModel> {
        val dataSnapshot = database.getReference("Items").get().await()
        return dataSnapshot.children.mapNotNull { it.getValue(ItemsModel::class.java) }
    }
}

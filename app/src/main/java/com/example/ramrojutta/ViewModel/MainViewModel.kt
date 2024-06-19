package com.example.ramrojutta.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ramrojutta.Model.BrandModel
import com.example.ramrojutta.Model.ItemsModel
import com.example.ramrojutta.Model.SliderModel
import com.example.ramrojutta.repository.DataRepository
import kotlinx.coroutines.launch

class MainViewModel(private val dataRepository: DataRepository) : ViewModel() {

    private val _banners = MutableLiveData<List<SliderModel>>()
    val banners: LiveData<List<SliderModel>> = _banners

    private val _brands = MutableLiveData<List<BrandModel>>()
    val brands: LiveData<List<BrandModel>> = _brands

    private val _popularItems = MutableLiveData<List<ItemsModel>>()
    val popularItems: LiveData<List<ItemsModel>> = _popularItems

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error


    fun loadBanners() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val bannersList = dataRepository.getBanners()
                _banners.value = bannersList
                _isLoading.value = false
            } catch (e: Exception) {
                _error.value = "Failed to load banners: ${e.message}"
                _isLoading.value = false
            }
        }
    }

    fun loadBrands() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val brandsList = dataRepository.getBrands()
                _brands.value = brandsList
                _isLoading.value = false
            } catch (e: Exception) {
                _error.value = "Failed to load brands: ${e.message}"
                _isLoading.value = false
            }
        }
    }

    fun loadPopularItems() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                _popularItems.value = dataRepository.getPopular()
                _isLoading.value = false
            } catch (e: Exception) {
                _error.value = "Failed to load brands: ${e.message}"
                _isLoading.value = false
            }
        }
    }
}

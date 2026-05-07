package com.example.androidacademyapi.data.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.androidacademyapi.data.network.apiservice.KtorProductApiService
import com.example.androidacademyapi.data.model.Product
import com.example.androidacademyapi.data.model.ProductRequest
import java.io.IOException

class KtorProductRepository(
    private val ktorProductApiService: KtorProductApiService,
    private val context: Context
): ProductRepository {

    private fun isInternetAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    override suspend fun getProducts(): Result<List<Product>> {
        if (!isInternetAvailable()) {
            return Result.failure(IOException("Nema pristupa internetu."))
        }
        return runCatching {
            ktorProductApiService.getProducts().products
        }
    }

    override suspend fun addProduct(request: ProductRequest): Result<Product> {
        return runCatching {
            ktorProductApiService.addProduct(request)
        }
    }

    override suspend fun updateProduct(
        id: Int,
        request: ProductRequest
    ): Result<Product> {
        return runCatching {
            ktorProductApiService.updateProduct(id,request)
        }
    }

    override suspend fun deleteProduct(id: Int): Result<Product> {
        return runCatching {
            ktorProductApiService.deleteProduct(id)
        }
    }

    override suspend fun getProductById(id: Int): Result<Product> {
        if (id < 0) {
            return Result.failure(IllegalArgumentException("ID proizvoda ne smije biti negativan."))
        }
        return runCatching {
            ktorProductApiService.getProductById(id)
        }
    }
}
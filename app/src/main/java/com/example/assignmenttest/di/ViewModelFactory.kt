package com.example.assignmenttest.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.assignmenttest.ui.viewmodel.MainViewModel

data class ViewModelFactory(private val context: Context):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(context) as T
            }
            else -> throw IllegalArgumentException("Unknown class name")
        }
    }
}
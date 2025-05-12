package com.example.scoreboardping.ui

import androidx.compose.runtime.mutableStateOf

class GameViewModel {
    private val _selectedItem = mutableStateOf("Edit")

    fun selectItem(label: String){
        _selectedItem.value = label
    }
}
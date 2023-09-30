package com.hamidrezabashiri.ezcard.ui.screens

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamidrezabashiri.ezcard.data.repository.app.AppConfigRepository
import com.hamidrezabashiri.ezcard.model.ThemeMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val appConfigRepository: AppConfigRepository) :
    ViewModel() {

    private val _appThemeState = mutableStateOf<ThemeMode?>(null)
    val appThemeState: State<ThemeMode?> = _appThemeState

    init {
        viewModelScope.launch {
            appConfigRepository.getAppTheme().collect { themeMode ->
                _appThemeState.value = themeMode
            }
        }
    }
}



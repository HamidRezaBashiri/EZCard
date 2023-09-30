package com.hamidrezabashiri.ezcard.ui.screens.settings

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamidrezabashiri.ezcard.data.repository.app.AppConfigRepository
import com.hamidrezabashiri.ezcard.model.ThemeMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val appConfigRepository: AppConfigRepository) :
    ViewModel() {


    private val _appThemeState = mutableStateOf<ThemeMode?>(ThemeMode.SYSTEM)
    val appThemeState: State<ThemeMode?> = _appThemeState

    private val _selectedLanguageTag = MutableStateFlow<String?>(null)
    val selectedLanguageTag = _selectedLanguageTag.asStateFlow()

    init {
        viewModelScope.launch {
            appConfigRepository.getAppTheme().collect { themeMode ->
                _appThemeState.value = themeMode
            }
        }
        viewModelScope.launch {
            appConfigRepository.getAppDefaultLanguageTag().collect {
                _selectedLanguageTag.value = it

            }
        }
    }

    fun onLanguageSelected(tag: String) {
        _selectedLanguageTag.value = tag
        viewModelScope.launch {
            appConfigRepository.setAppDefaultLanguage(tag)
        }
    }

    fun changeTheme(themeMode: ThemeMode) {
        viewModelScope.launch {
            appConfigRepository.setAppTheme(themeMode)
        }

    }

}
package com.aqua30.datastore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aqua30.local_preference.UserPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Saurabh Pant
 * @since 11/10/22
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val userPreference: UserPreference
) : ViewModel() {

    /**
     * username flow to observe changes in user name
     * */
    val userName: StateFlow<String> = userPreference.userName().filter {
        it.isNotEmpty()
    }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            "No saved data"
        )

    /**
     * save user name
     * */
    fun saveUserName(name: String) {
        viewModelScope.launch {
            userPreference.saveUserName(name)
        }
    }
}
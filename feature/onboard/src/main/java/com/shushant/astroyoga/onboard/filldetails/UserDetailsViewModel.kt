package com.shushant.astroyoga.onboard.filldetails

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewModelScope
import com.shushant.astroyoga.data.base.BaseViewModel
import com.shushant.astroyoga.data.datastore.PrefStorage
import com.shushant.astroyoga.data.model.LocationSearchResultItem
import com.shushant.astroyoga.network.utils.json
import com.skydoves.whatif.whatIfNotNullOrEmpty
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString

class UserDetailsViewModel(
    private val preferences: PrefStorage
) :
    BaseViewModel<UserDetailsState>(UserDetailsState()) {

    val composableScreens = MutableStateFlow(emptyList<UserScreens>())

    val selectedIndex = MutableStateFlow(0)

    init {
        viewModelScope.launch {
            preferences.getUserState.first().whatIfNotNullOrEmpty { data ->
                setState {
                    try {
                        json.decodeFromString(data)
                    } catch (e: Exception) {
                        UserDetailsState()
                    }
                }
            }
        }

    }

    fun setSelectedIndex(page: Int) {
        selectedIndex.update { page }.also {
            setState { state -> state.copy(filledIndex = page) }.also {
                updatePref()
            }
        }
    }

    fun addComposable(action: List<@Composable (() -> Unit)>) {
        composableScreens.update {
            mutableListOf<UserScreens>().apply {
                action.map {
                    add(UserScreens(action = it))
                }
            }
        }
    }

    fun setUserName(userName: String) {
        setState { state -> state.copy(userName = userName) }.also {
            updatePref()
        }
    }

    fun setGender(gender: Gender) {
        setState { state -> state.copy(gender = gender) }.also {
            updatePref()
        }
    }

    private fun updatePref() {
        viewModelScope.launch {
            preferences.setUserData(json.encodeToString(state.value))
        }
    }

    fun setSentimentalStatus(sentimentalStatus: SentimentalStatus) {
        setState { state -> state.copy(sentimentalStatus = sentimentalStatus) }.also {
            updatePref()
        }
    }

    fun setDob(dob: String) {
        setState { state -> state.copy(dob = dob) }.also {
            updatePref()
        }
    }

    fun setTob(tob: String) {
        setState { state -> state.copy(tob = tob) }.also {
            updatePref()
        }
    }

    fun setPob(pob: LocationSearchResultItem?) {
        setState { state -> state.copy(pob = pob) }.also {
            updatePref()
        }
    }

}
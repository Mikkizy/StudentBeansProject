package com.lord_ukaka.projectbymiracle.presentation.photos

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lord_ukaka.projectbymiracle.domain.usecases.GetAlbums
import com.lord_ukaka.projectbymiracle.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val getAlbums: GetAlbums
): ViewModel() {

    private val _state = mutableStateOf(PhotosState())
    val state: State<PhotosState> = _state

    private val _eventFlow = MutableSharedFlow<PhotosEvents>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        loadAlbums()
    }

    fun loadAlbums() {
        viewModelScope.launch {
            getAlbums().onEach { result ->
                when(result) {
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(
                            isLoading = true
                        )
                    }
                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            isError = true
                        )
                        _eventFlow.emit(PhotosEvents.ShowSnackbar(result.message ?: "An error occurred!"))
                    }
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            albums = result.data ?: emptyList(),
                            isLoading = false,
                            isError = false
                        )
                    }
                }
            }.launchIn(this)
        }
    }













}
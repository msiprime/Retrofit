package com.example.retrofit.university

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.university.data.model.University
import com.example.retrofit.university.data.model.UniversityItem
import com.example.retrofit.university.data.remote.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class UniversityViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {
    var universities = mutableStateOf<List<UniversityItem>>(emptyList())

    private val _errorMessage = MutableLiveData<String?>(null)

    init {
        getUniversityName()
    }

    private fun getUniversityName() {
        viewModelScope.launch {
            apiService.getUniversities().enqueue(object : Callback<University> {
                override fun onResponse(call: Call<University>, response: Response<University>) {
                    if (response.isSuccessful) {
                        universities.value = response.body()?.data!!
                    } else {
                    }
                }

                override fun onFailure(call: Call<University>, t: Throwable) {
                }
            })
        }
    }
}

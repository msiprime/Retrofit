package com.example.retrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.retrofit.model.RetrofitClient
import com.example.retrofit.model.University
import com.example.retrofit.ui.theme.RetrofitTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.runtime.remember
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.retrofit.model.UniversityItem
import coil.compose.rememberImagePainter


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            RetrofitTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //UniversityList()
                    UniversityList()
                }
            }
        }
    }
}



@Composable
fun UniversityItemCard(university: UniversityItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        //elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Image(
                painter = rememberImagePainter(data = university.coverImg),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Text(text = university.universityName, fontWeight = FontWeight.Bold)
            Text(text = university.address)
            Text(text = university.description)
            Text(text = "Created at: ${university.createdAt}")
            Text(text = "Updated at: ${university.updatedAt}")
        }
    }
}

@Composable
fun UniversityList() {
    var universities by remember { mutableStateOf(emptyList<UniversityItem>()) }
    val apiService = RetrofitClient.apiService
    val call = apiService.getUniversities()

    LaunchedEffect(call) {
        call.enqueue(object : Callback<University> {
            override fun onResponse(call: Call<University>, response: Response<University>) {
                if (response.isSuccessful) {
                    universities = response.body()?.data ?: emptyList()
                } else {
                    // Handle the error
                }
            }

            override fun onFailure(call: Call<University>, t: Throwable) {
            }
        })
    }



    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(state = scrollState),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        universities.forEach { university ->
            UniversityItemCard(university = university)
        }
    }
}

@Preview
@Composable
fun UniversityListPreview(){
    Column {
        UniversityList()
        Text(text = "Sakibul islam ")
    }
}


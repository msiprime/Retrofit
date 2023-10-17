package com.example.retrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.retrofit.core.ui.theme.RetrofitTheme
import com.example.retrofit.university.UniversityViewModel
import com.example.retrofit.university.data.model.UniversityItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RetrofitTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Home()
                }
            }
        }
    }
}

@Composable
fun Home(viewModel: UniversityViewModel = hiltViewModel()) {
    var list by remember {
        viewModel.universities
    }
    UniversityList(list = list)

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
fun UniversityList(list: List<UniversityItem>) {


    LazyColumn {
        items(list) { university ->
            UniversityItemCard(university = university)
        }
    }

}


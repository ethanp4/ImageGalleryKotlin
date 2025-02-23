package com.example.imagegallery

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imagegallery.ui.theme.ImageGalleryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ImageGalleryTheme {
                Scaffold(modifier = Modifier.fillMaxSize())
                { innerPadding ->
                    ArtworkGallery(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ArtworkGallery(modifier: Modifier) {
    val artworks = arrayOf(
        Artwork("Mona Lisa", "Leonardo da Vinci", 1517u, R.drawable.mona_lisa),
        Artwork("Starry Night", "Van Gogh", 1889u, R.drawable.starry_night),
        Artwork("The Last Supper", "Leonardo da Vinci", 1498u, R.drawable.last_supper),
    )
    val currentArtworkIndex = remember { mutableIntStateOf(0) }
    if (currentArtworkIndex.intValue < 0) { currentArtworkIndex.intValue = artworks.size-1 }
    if (currentArtworkIndex.intValue >= artworks.size) { currentArtworkIndex.intValue = 0 }
    Artwork(
        artwork = artworks[currentArtworkIndex.intValue],
        currentArtworkIndex = currentArtworkIndex,
        modifier = modifier.fillMaxSize()
    )
}


@Composable
fun Artwork(artwork: Artwork, currentArtworkIndex: MutableState<Int>, modifier: Modifier) {
    Column() {
        Image(painter = painterResource(artwork.imageRes), artwork.title, modifier= Modifier.fillMaxWidth().padding(top = 50.dp), alignment = Alignment.TopCenter)
        Box(modifier = Modifier.fillMaxSize().padding(bottom = 50.dp), contentAlignment = Alignment.BottomCenter){
            Column(){
                Text(artwork.title, modifier = Modifier.align(Alignment.CenterHorizontally), style = MaterialTheme.typography.titleLarge)
                Row(modifier = Modifier.align(Alignment.CenterHorizontally).padding(bottom = 12.dp)) {
                    Text(artwork.artist)
                    Text(" (" + artwork.year.toString() + ")")
                }
                Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    Button(onClick = { currentArtworkIndex.value--; }, Modifier.width(100.dp)) { Text("Prev") }
                    Button(onClick = { currentArtworkIndex.value++; }, Modifier.width(100.dp)) { Text("Next") }
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    ImageGalleryTheme {
//        Greeting("Android")
//    }
//}
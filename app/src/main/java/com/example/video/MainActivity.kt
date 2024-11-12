package com.example.video

import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    VideoPlayerApp()
                }
            }
        }
    }
}

@Composable
fun VideoPlayerApp() {
    var isLocalVideo by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        VideoPlayer(isLocalVideo)

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { isLocalVideo = !isLocalVideo }) {
            Text(if (isLocalVideo) "Switch to Internet Video" else "Switch to Local Video")
        }
    }
}

@Composable
fun VideoPlayer(isLocalVideo: Boolean) {
    val context = LocalContext.current

    // Define the video URI based on whether it's local or internet-based.
    val videoUri = remember(isLocalVideo) {
        if (isLocalVideo) {
            Uri.parse("android.resource://${context.packageName}/raw/babies") // Ensure this matches your file name.
        } else {
            Uri.parse("https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4") // A known working URL.
        }
    }

    var videoView by remember { mutableStateOf<VideoView?>(null) }

    DisposableEffect(videoUri) {
        // Stop playback if the video source changes.
        videoView?.apply {
            setVideoURI(videoUri)
            start() // Start playback with the new video URI.
        }
        onDispose { }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9f), // Maintain a 16:9 aspect ratio for the video.
        contentAlignment = Alignment.Center
    ) {
        AndroidView(
            factory = { ctx ->
                VideoView(ctx).apply {
                    setMediaController(MediaController(ctx).apply {
                        setAnchorView(this@apply)
                    })
                    setVideoURI(videoUri) // Set the initial video URI.
                    start() // Start the video automatically.
                    videoView = this // Store reference to the VideoView.
                }
            },
            modifier = Modifier.fillMaxSize(),
            update = { view ->
                view.setVideoURI(videoUri) // Re-set URI on update to ensure correct video source.
                view.start() // Restart playback after setting URI.
            }
        )
    }
}
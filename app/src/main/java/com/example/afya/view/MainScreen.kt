package com.example.afya.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.afya.R
import com.example.afya.model.Drug
import com.example.afya.model.Post
import com.example.afya.viewmodel.DrugViewModel
import com.example.afya.viewmodel.PostViewModel


sealed class Screen(val route: String) {
    object Posts : Screen("posts")
    object Drugs : Screen("drugs")
}

@Composable
fun MainScreen(
    postViewModel: PostViewModel,
    drugViewModel: DrugViewModel,
    modifier: Modifier = Modifier
) {
    val posts by postViewModel.uiState.collectAsState()
    val drugs by drugViewModel.drugState.collectAsState()

    var currentScreen by remember { mutableStateOf<Screen>(Screen.Posts) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(ImageVector.vectorResource(R.drawable.ic_posts), contentDescription = null) },
                    label = { Text("Posts") },
                    selected = currentScreen == Screen.Posts,
                    onClick = { currentScreen = Screen.Posts }
                )
                NavigationBarItem(
                    icon = { Icon(ImageVector.vectorResource(R.drawable.ic_drugs), contentDescription = null) },
                    label = { Text("Drugs") },
                    selected = currentScreen == Screen.Drugs,
                    onClick = { currentScreen = Screen.Drugs }
                )
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (currentScreen) {
                is Screen.Posts -> PostList(
                    posts = posts.posts,
                    modifier = Modifier.fillMaxSize()
                )
                is Screen.Drugs -> DrugList(
                    drugs = drugs.drugs,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
private fun PostList(
    posts: List<Post>,
    modifier: Modifier = Modifier
) {
    if (posts.isEmpty()) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(modifier = modifier.padding(16.dp)) {
            items(posts) { post ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Row(modifier = Modifier.padding(16.dp)) {
                        AsyncImage(
                            model = post.image,
                            contentDescription = null,
                            modifier = Modifier
                                .size(80.dp)
                                .clip(MaterialTheme.shapes.medium)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = post.title, style = MaterialTheme.typography.titleMedium)
                            Text(text = post.content, style = MaterialTheme.typography.bodyMedium)
                            Text("Drug: ${post.drugName}", style = MaterialTheme.typography.labelMedium)
                            Text("Location: ${post.location}", style = MaterialTheme.typography.labelMedium)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DrugList(
    drugs: List<Drug>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.padding(16.dp)) {
        items(drugs) { drug ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = drug.name, style = MaterialTheme.typography.titleMedium)
                    Text(text = drug.details, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}

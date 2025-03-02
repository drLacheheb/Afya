package com.example.afya.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.afya.R
import com.example.afya.model.Drug
import com.example.afya.model.Post
import com.example.afya.viewmodel.DrugViewModel
import com.example.afya.viewmodel.PostViewModel

sealed class Screen(val route: String) {
    object Posts : Screen("posts")
    object Drugs : Screen("drugs")
    object Profile : Screen("profile")
    object Contact : Screen("contact")
    object Messages : Screen("messages")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    postViewModel: PostViewModel,
    drugViewModel: DrugViewModel,
    modifier: Modifier = Modifier
) {
    // جمع الحالة من ViewModel
    val posts by postViewModel.uiState.collectAsState()
    val drugs by drugViewModel.drugState.collectAsState()

    // حالة الشاشة الحالية
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Posts) }

    // حالة البحث
    var searchQuery by remember { mutableStateOf("") }

    // تصفية المنشورات والأدوية بناءً على البحث
    val filteredPosts = posts?.posts?.filter { it.title.contains(searchQuery, ignoreCase = true) } ?: emptyList()
    val filteredDrugs = drugs?.drugs?.filter { it.name.contains(searchQuery, ignoreCase = true) } ?: emptyList()

    Scaffold(
        topBar = {
            if (currentScreen != Screen.Profile && currentScreen != Screen.Contact && currentScreen != Screen.Messages) {
                Column {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(
                                text = "Afya",
                                style = MaterialTheme.typography.headlineMedium,
                                color = Color(0xFF1B5E20),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    )
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        placeholder = { Text("Search...") },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                }
            }
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(painterResource(id = R.drawable.ic_posts), contentDescription = "Posts") },
                    label = { Text("Posts") },
                    selected = currentScreen == Screen.Posts,
                    onClick = { currentScreen = Screen.Posts }
                )
                NavigationBarItem(
                    icon = { Icon(painterResource(id = R.drawable.ic_drugs), contentDescription = "Drugs") },
                    label = { Text("Drugs") },
                    selected = currentScreen == Screen.Drugs,
                    onClick = { currentScreen = Screen.Drugs }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = currentScreen == Screen.Profile,
                    onClick = { currentScreen = Screen.Profile }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Phone, contentDescription = "Contact", modifier = Modifier.size(20.dp)) },
                    label = { Text("Calls", style = MaterialTheme.typography.labelSmall) },
                    selected = currentScreen == Screen.Contact,
                    onClick = { currentScreen = Screen.Contact }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Email, contentDescription = "Messages", modifier = Modifier.size(20.dp)) },
                    label = { Text("Msg", style = MaterialTheme.typography.labelSmall) },
                    selected = currentScreen == Screen.Messages,
                    onClick = { currentScreen = Screen.Messages }
                )
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (currentScreen) {
                is Screen.Posts -> PostGrid(
                    posts = filteredPosts,
                    modifier = Modifier.fillMaxSize()
                )
                is Screen.Drugs -> DrugGrid(
                    drugs = filteredDrugs,
                    modifier = Modifier.fillMaxSize()
                )
                is Screen.Profile -> ProfileScreen()
                is Screen.Contact -> ContactScreen()
                is Screen.Messages -> MessagesScreen()
            }
        }
    }
}

@Composable
fun PostGrid(posts: List<Post>, modifier: Modifier) {
    Column(modifier = modifier) {
        Button(
            onClick = { /* تنفيذ عملية إضافة منشور */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1B5E20)),
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add Post", tint = Color.White)
            Spacer(modifier = Modifier.width(4.dp))
            Text("Add Post", color = Color.White)
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(8.dp)
        ) {
            items(posts) { post ->
                Card(
                    modifier = Modifier.padding(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Gray),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AsyncImage(
                            model = post.image,
                            contentDescription = "Post Image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                                .clip(MaterialTheme.shapes.medium)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = post.title,
                            style = MaterialTheme.typography.titleLarge.copy(color = Color.White),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = post.content,
                            style = MaterialTheme.typography.bodyLarge.copy(color = Color.White),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}



@Composable
fun AsyncImage(model: String?, contentDescription: String, modifier: Modifier) {

}

@Composable
fun DrugGrid(drugs: List<Drug>, modifier: Modifier) {
    Column(modifier = modifier) {
        Button(
            onClick = { /* تنفيذ عملية إضافة دواء */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1B5E20)),
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add Drug", tint = Color.White)
            Spacer(modifier = Modifier.width(4.dp))
            Text("Add Drug", color = Color.White)
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(8.dp)
        ) {
            items(drugs) { drug ->
                Card(
                    modifier = Modifier.padding(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Gray),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = drug.name,
                            style = MaterialTheme.typography.titleLarge.copy(color = Color.White),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = drug.details,
                            style = MaterialTheme.typography.bodyLarge.copy(color = Color.White),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = { /* تنفيذ عملية طلب الدواء */ },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Request", color = Color(0xFF1B5E20))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileScreen() {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }

    val customColor = Color(0xFF1B5E20) // اللون الأخضر الخاص بـ "Afya"

    Column(
        modifier = Modifier.padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Profile",
            style = MaterialTheme.typography.headlineMedium,
            color = customColor
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text("First Name") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedLabelColor = customColor,
                unfocusedLabelColor = Color.Gray,
                focusedBorderColor = customColor,
                unfocusedBorderColor = Color.Gray,
                cursorColor = customColor,
                focusedTextColor = customColor,
                unfocusedTextColor = Color.Black
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Last Name") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedLabelColor = customColor,
                unfocusedLabelColor = Color.Gray,
                focusedBorderColor = customColor,
                unfocusedBorderColor = Color.Gray,
                cursorColor = customColor,
                focusedTextColor = customColor,
                unfocusedTextColor = Color.Black
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Phone Number") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedLabelColor = customColor,
                unfocusedLabelColor = Color.Gray,
                focusedBorderColor = customColor,
                unfocusedBorderColor = Color.Gray,
                cursorColor = customColor,
                focusedTextColor = customColor,
                unfocusedTextColor = Color.Black
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /* تنفيذ عملية حفظ البيانات */ },
            colors = ButtonDefaults.buttonColors(containerColor = customColor)
        ) {
            Text("Save", color = Color.White)
        }
    }
}

@Composable
fun ContactScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "No Calls", style = MaterialTheme.typography.headlineMedium)
    }
}

@Composable
fun MessagesScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "No Messages", style = MaterialTheme.typography.headlineMedium)
    }
}
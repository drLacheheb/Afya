package com.example.afya.presentation.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.afya.R
import com.example.afya.data.model.Drug
import com.example.afya.data.model.Post
import com.example.afya.presentation.viewmodel.DrugViewModel
import com.example.afya.presentation.viewmodel.PostViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.text.font.FontWeight


sealed class Screen(val route: String) {
    object Posts : Screen("posts")
    object Drugs : Screen("drugs")
    object Profile : Screen("profile")
    object Messages : Screen("messages")
    object Calls : Screen("calls")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    postViewModel: PostViewModel,
    drugViewModel: DrugViewModel,
    modifier: Modifier = Modifier
) {
    val posts by postViewModel.uiState.collectAsState()
    val drugs by drugViewModel.drugState.collectAsState()




    var currentScreen by remember { mutableStateOf<Screen>(Screen.Posts) }// تتبع الشاشة الحالية
    var searchQuery by remember { mutableStateOf("") }// تتبع النص المدخل في البحث


    Scaffold(
        topBar = {
            if (currentScreen !is Screen.Profile && currentScreen !is Screen.Calls && currentScreen !is Screen.Messages) {
                Column {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(
                                text = "Afya",
                                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                                color =  MaterialTheme.colorScheme.primary,
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
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = currentScreen == Screen.Profile,
                    onClick = { currentScreen = Screen.Profile }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Email, contentDescription = "Msgs") },
                    label = { Text("Msgs") },
                    selected = currentScreen == Screen.Messages,
                    onClick = { currentScreen = Screen.Messages }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Call, contentDescription = "Calls") },
                    label = { Text("Calls") },
                    selected = currentScreen == Screen.Calls,
                    onClick = { currentScreen = Screen.Calls }
                )
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (currentScreen) {
                is Screen.Posts -> PostList(
                    posts = posts.posts,
                    onAddPost = { /* ضع الأكشن المناسب عند الضغط */ }
                )
                is Screen.Drugs -> DrugList(
                    drugs = drugs.drugs,
                    onAddDrug = { /* ضع الإجراء المناسب هنا عند الضغط على زر إضافة دواء */ },)
                is Screen.Profile -> ProfileScreen()
                is Screen.Messages -> MessagesScreen()
                is Screen.Calls -> CallsScreen()
            }
        }
    }
}

@Composable
fun CallsScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("No Calls", style = MaterialTheme.typography.headlineMedium)
    }
}

@Composable
fun MessagesScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("No Messages", style = MaterialTheme.typography.headlineMedium)
    }
}


@Composable
fun ProfileScreen() {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var birthDay by remember { mutableStateOf("") }
    var birthMonth by remember { mutableStateOf("") }
    var birthYear by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Profile",
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text("First Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Last Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Phone Number") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = location,
            onValueChange = { location = it },
            label = { Text("Location") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Date of Birth",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp)
        )
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            OutlinedTextField(
                value = birthDay,
                onValueChange = { birthDay = it },
                label = { Text("Day") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            OutlinedTextField(
                value = birthMonth,
                onValueChange = { birthMonth = it },
                label = { Text("Month") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            OutlinedTextField(
                value = birthYear,
                onValueChange = { birthYear = it },
                label = { Text("Year") },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { }) {
            Text("Save Informations")
        }
    }
}


@Composable
fun DrugList(drugs: List<Drug>, onAddDrug: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        // زر إضافة دواء جديد
        Button(
            onClick = onAddDrug,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add informations about a drug")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Add informations about a drug")
        }

        // قائمة الأدوية
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(drugs) { drug ->
                DrugCard(drug)
            }
        }
    }
}

// بطاقة عرض تفاصيل الدواء
@Composable
fun DrugCard(drug: Drug) {
    var showContactOptions by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // صورة الدواء
            AsyncImage(
                model = drug.image,
                contentDescription = "Drug Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(MaterialTheme.shapes.medium)
            )
            Spacer(modifier = Modifier.height(12.dp))

            // اسم الدواء
            Text(
                text = drug.name,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))

            // تفاصيل الدواء
            Text(
                text = drug.details,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}


@Composable
fun PostList(posts: List<Post>, onAddPost: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        // زر إضافة منشور جديد
        Button(
            onClick = onAddPost,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add Post")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Add Post")
        }

        // قائمة المنشورات
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(posts) { post ->
                PostCard(post)
            }
        }
    }
}

// بطاقة عرض تفاصيل المنشور
@Composable
fun PostCard(post: Post) {
    var showContactOptions by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // صورة المنشور
            AsyncImage(
                model = post.image,
                contentDescription = "Post Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(MaterialTheme.shapes.medium)
            )
            Spacer(modifier = Modifier.height(12.dp))

            // نوع المنشور
            post.postType?.let { postType ->
                Surface(
                    modifier = Modifier
                        .padding(bottom = 8.dp),
                    shape = MaterialTheme.shapes.small,
                    color = MaterialTheme.colorScheme.secondaryContainer,
                ) {
                    Text(
                        text = postType.name,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }

            // عنوان المنشور
            Text(
                text = post.title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))

            // محتوى المنشور
            Text(
                text = post.content,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = { showContactOptions = !showContactOptions },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Contact")
            }

            // عرض أيقونات التواصل عند الضغط على الزر
            if (showContactOptions) {
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    IconButton(onClick = { /* إرسال رسالة */ }) {
                        Icon(Icons.Filled.Email, contentDescription = "Send Email", tint = Color.White)
                    }
                    IconButton(onClick = { /* الاتصال */ }) {
                        Icon(Icons.Filled.Phone, contentDescription = "Call", tint = Color.White)
                    }
                }
            }
        }
    }
}


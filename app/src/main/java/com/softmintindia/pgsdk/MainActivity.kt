package com.softmintindia.pgsdk

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.widget.ConstraintLayout
import com.softmintindia.pgsdk.ui.theme.PGSDKTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PGSDKTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { AppBar() }
                ) { innerPadding ->
                    MainContent(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar() {
    TopAppBar(
        title = {
            Text(
                text = "PGSDK - Payment Options",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color(0xFF3F51B5), // AppBar background color
            titleContentColor = Color.White    // Title text color
        )
    )
}

@Composable
fun MainContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Spacer(modifier = Modifier.height(24.dp))

        AmountDisplay()

        Spacer(modifier = Modifier.height(16.dp))

        // todo: Add Row with app icon, app name, and right arrow
        AppButtonsList()
        Spacer(modifier = Modifier.height(16.dp))






        ExpansionTile(
            title = "All Payment Options",
            content = { visibleButtons ->
                ButtonList(visibleButtons = visibleButtons)
            }
        )


        Spacer(modifier = Modifier.height(24.dp))


        Button(
            onClick = { Log.d("ButtonClick", "Continue clicked") },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(contentColor = Color(0xFF3F51B5)  ),
            modifier = Modifier
                .height(64.dp)
                .fillMaxWidth()
        ) {

            Text(text = "Continue", fontSize = 20.sp, color = Color.White, fontWeight = FontWeight.Bold,)
        }
    }
}

@Composable
fun RecommendedUpiAppCard(
    appName: String,
    iconResourceId: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 0.dp, // Border width
                color = Color(0xFFE9E9E9), // Border color
                shape = RoundedCornerShape(8.dp) // Rounded corners for the border
            )
            .padding(0.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp, focusedElevation = 0.dp), // Add some elevation for the card
        shape = RoundedCornerShape(8.dp), // Rounded corners for the card
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Button(
            onClick = onClick,
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // App Icon
                Image(
                    painter = painterResource(id = iconResourceId),
                    contentDescription = "$appName Icon",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))

                // App Name
                Text(
                    text = appName,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF5C6BC0),
                    modifier = Modifier.weight(1f)
                )

                // Right Arrow Icon
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = null,
                    tint = Color(0xFFDFDFDF)
                )
            }
        }
    }
}


@Composable
fun AmountDisplay() {
    Column(modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Amount",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "₹1000",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}


@Composable
fun AppButtonsList() {
    Column {
        Text(
            text = "Recommended",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 8.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp, // Border width
                    color = Color(0xFFE9E9E9), // Border color
                    shape = RoundedCornerShape(8.dp) // Rounded corners for the border
                )
                .padding(0.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp, focusedElevation = 0.dp),
            shape = RoundedCornerShape(8.dp), // Rounded corners for the card
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            RecommendedUpiAppCard(
                appName = "Phone Pe",
                iconResourceId = R.drawable.ic_phonepe
            ) {
                Log.d("ButtonClick", "App Name: Phone Pe")
            }
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = Color(0xFFE9E9E9)
            )
            RecommendedUpiAppCard(
                appName = "Google Pay",
                iconResourceId = R.drawable.ic_googlepay
            ) {
                Log.d("ButtonClick", "App Name: Google Pay")
            }

        }
    }
}



@Composable
fun ExpansionTile(title: String, content: @Composable (visibleButtons: List<String>) -> Unit) {
    var isExpanded by remember { mutableStateOf(false) }

    val buttonList = listOf(
        "Google Pay", "Phone Pe", "Paytm", "Amazon Pay",
        "MobiKwik", "FreeCharge", "WhatsApp Pay", "Bhim UPI",
        "PhonePe UPI", "Google UPI",
        "Apple Pay", "Samsung Pay", "PayPal", "Razorpay",
        "JioMoney", "AirTel Payments", "HDFC Pay", "ICICI Pay",
        "Kotak Pay", "SBI Pay", "Axis Pay", "Baroda Pay",
        "Yono SBI", "Bajaj Pay", "Vodafone M-Pesa", "Ola Money",
        "Zeta Pay", "Simpl Pay", "Ipay", "Cashfree Pay", "Pine Labs"
    )


    // Determine which buttons to show based on expansion state
    val visibleButtons = if (isExpanded) buttonList else buttonList.take(8)

    Card(
        modifier = Modifier
            .fillMaxWidth()
//            .fillMaxHeight(0.775f)
            .border(
                width = 1.dp, // Border width
                color = Color(0xFFE9E9E9), // Border color
                shape = RoundedCornerShape(8.dp) // Rounded corners for the border
            )
            .padding(0.dp), // Card padding
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp, focusedElevation = 0.dp), // Optional elevation for shadow effect
        shape = RoundedCornerShape(8.dp), // Rounded corners for the card
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFE8EAF6)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp) // Padding for content inside the Card
        ) {
            // Header of the ExpansionTile
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .clickable { isExpanded = !isExpanded },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF3F51B5),
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = Color(0xFF3F51B5)
                )
            }
            if (isExpanded) {
                Spacer(modifier = Modifier.height(8.dp))
            }
            content(visibleButtons) // Pass the visible buttons list to the content
        }
    }
}


@Composable
fun ButtonList(visibleButtons: List<String>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // 2 buttons per row
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(visibleButtons) { button ->
            Button(
                onClick = { Log.d("ButtonClick", "App Name: $button") },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp) // Padding between buttons
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Replace with actual icons for each button
                    Image(
                        painter = painterResource(id = R.drawable.ic_googlepay), // Replace with dynamic icons
                        contentDescription = "App Icon",
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))

                    // App Name
                    Text(
                        text = button,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF5C6BC0),
                        modifier = Modifier.weight(1f)
                    )

                    // Right Arrow Icon
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = null,
                        tint = Color(0xFFDFDFDF)
                    )
                }
            }
        }
    }
}


@Composable
fun AmountRowCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp), // Padding for the card
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp, focusedElevation = 0.dp), // Optional elevation for shadow effect
        shape = RoundedCornerShape(8.dp), // Rounded corners for the card
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFE8EAF6)
        )

    ) {
        // Row with amount text and continue button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), // Padding inside the card
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween // Space between text and button
        ) {
            // Amount text
            Text(
                text = "Amount: ₹1000", // Example amount
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            // Continue button

        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainContentPreview() {
    PGSDKTheme {
        Scaffold(
            topBar = { AppBar() }
        ) {
            MainContent(Modifier.padding(it))
        }
    }
}




package com.example.jetbizcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetbizcard.ui.theme.JetBizCardTheme
import com.example.jetbizcard.ui.theme.Typography

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetBizCardTheme {
                Surface(color = MaterialTheme.colors.background) {
                    ShowJetBizCard()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetBizCardTheme {
        Surface(color = MaterialTheme.colors.background) {
            ShowJetBizCard()
        }
    }
}

@Composable
fun ShowJetBizCard() {
    // A surface container using the 'background' color from the theme
    Surface (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        color = Color.LightGray
    )
    {
        val buttonClickedState = remember {
            mutableStateOf(false)
        }

        Card (
            modifier = Modifier
                .width(200.dp)
                .height(390.dp)
                .padding(12.dp),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        )
        {
            Column (
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                ShowProfileImage (
                    Modifier
                        .size(150.dp)
                        .padding(12.dp),
                    BorderStroke(1.dp, Color.LightGray),
                )
                Divider (
                    color = Color.Gray,
                    thickness = 1.dp
                )
                ShowProfileInfo()
                Button (
                    onClick = {
                        buttonClickedState.value = !buttonClickedState.value
                    },
                    modifier = Modifier.padding(top = 15.dp),
                    colors = ButtonDefaults.textButtonColors (
                        backgroundColor = MaterialTheme.colors.primaryVariant
                    )
                )
                {
                    Text("Portfolio", color = Color.White)
                }
                val buttonWasClicked = buttonClickedState.value
                if (buttonWasClicked) {
                    ShowPortfolioContent()
                }
                else {
                    HidePortfolioContent()
                }
            }
        }
    }
}

@Composable
fun HidePortfolioContent() {
    Box{}
}

@Preview
@Composable
fun ShowPortfolioContent() {
    Surface (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    )
    {
        ShowPortfolioList(listOf<String>(
            "Project1",
            "Project2",
            "Project3",
            "Project4",
        ))
    }
}

@Composable
fun ShowPortfolioList(data: List<String>) {
    LazyColumn (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        items(data) { item ->
            Card (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(13.dp),
                shape = RoundedCornerShape (
                    corner = CornerSize(15.dp)
                ),
                elevation = 4.dp
            )
            {
                Row (
                    modifier = Modifier
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    ShowProfileImage (
                        modifier = Modifier.size(75.dp).padding(5.dp),
                        border = BorderStroke(2.dp, Color.DarkGray)
                    )
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally
                    )
                    {
                        Text (
                            text = item,
                            fontWeight = FontWeight.Bold
                        )
                        Text (
                            text = "An awesome project!",
                            style = MaterialTheme.typography.body2
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ShowProfileInfo() {
    Text(
        "Miles P.",
        modifier = Modifier
            .padding(top = 2.dp, bottom = 2.dp),
        style = Typography.h4,
        color = MaterialTheme.colors.primaryVariant
    )
    Text(
        "Android Compose Programmer",
        textAlign = TextAlign.Left,
    )
    Text(
        "@composeProgrammer",
        style = Typography.subtitle1
    )
}


@Composable
private fun ShowProfileImage(modifier: Modifier, border: BorderStroke) {
    Surface(
        modifier = modifier,
        shape = CircleShape,
        border = border,
    )
    {
        Image (
            painter = painterResource(id = R.drawable.profile_image),
            contentDescription = "profile image",
            alpha = 0.7f
        )
    }
}
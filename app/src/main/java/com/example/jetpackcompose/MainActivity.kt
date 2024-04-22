@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.jetpackcompose

import com.example.jetpackcompose.ui.theme.*
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardColors
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.ui.theme.JetPackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //SmallTopAppBarExample()
            JetPackComposeTheme {
                CenterAlignedTopAppBarExample()
            }
        }
    }
}

@Preview()
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewComponent(){
    JetPackComposeTheme {
        CenterAlignedTopAppBarExample()
    }
}

@Composable
fun CenterAlignedTopAppBarExample() {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    val mContext = LocalContext.current

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = mainSv,
                    titleContentColor = White,
                ),
                title = {
                    Text(
                        text = stringResource(R.string.config),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = White,
                        fontSize = 16.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        mToast(mContext, "Back")
                    }) {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowLeft,
                            contentDescription = "Localized description",
                            tint = White
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        Toast.makeText(mContext, "This is a Sample Toast", Toast.LENGTH_LONG).show()
                    })
                    {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.icon_power),
                            tint = White,
                            contentDescription = "Localized description"
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)){
            Column {
                TextOptions(stringResource(R.string.generales))
                CardDemo(stringResource(R.string.notifications),1)
                CardDemo(stringResource(R.string.password), 1)
                CardDemo(stringResource(R.string.update), 1)
                Spacer(modifier = Modifier.height(20.dp))

                TextOptions(stringResource(R.string.metodo))
                CardDemo(stringResource(R.string.login), 0)
                CardDemo(stringResource(R.string.password),1)
                CardDemo(stringResource(R.string.update), 1)
                CardDemo(stringResource(R.string.update), 1)
                CardDemo(stringResource(R.string.finger_face), 0)
            }
        }
    }
}

@Composable
fun CardDemo(text: String, option: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp)
            .padding(end = 15.dp)
            .padding(top = 2.dp)
            .padding(bottom = 0.dp)
            .clickable { }
            .height(50.dp),
        colors = CardDefaults.cardColors(
            containerColor = White,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        shape = RoundedCornerShape(0)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
        ) {
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .padding(top = 0.dp)
                    .padding(bottom = 0.dp)
                    .padding(start = 8.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = text,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                if(option==1){
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = null,
                        tint = mainSv,
                        modifier = Modifier.size(30.dp)
                    )
                }else if(option == 0){
                    SwitchMinimalExample()
                }

            }
        }
    }
}

@Composable
fun TextOptions(text: String){
    Text(
        text = text,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        color = Black,
        fontSize = 16.sp,
        modifier = Modifier
            .padding(start = 16.dp)
            .padding(top = 8.dp)
            .padding(bottom = 8.dp)
    )
}

@Composable
fun SwitchMinimalExample() {
    var checked by remember { mutableStateOf(true) }

    Switch(
        checked = checked,
        onCheckedChange = {
            checked = it
        }
    )
}






private fun mToast(context: Context, text: String){
    Toast.makeText(context, text.toString(), Toast.LENGTH_LONG).show()
}
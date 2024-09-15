package uk.org.pentlandscouts.scoreboard_app.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.scoreboard_app.R

class NavTopBar {

    companion object {
        @Composable
        fun TopBarNav() {
            val title:String = "Scoreboard App"
            Row {

                Image(
                    // on below line we are specifying the drawable image for our image.
                    painter = painterResource(id = R.mipmap.pentland_logo_x2_foreground),
                    //painter = painterResource(id = R.mipmap.elephant_foreground),

                    contentDescription = "Scouts icon",
                    modifier = Modifier
                        .height(150.dp)
                        .width(150.dp)
                        .padding(5.dp)
                        .background(Color.Transparent)
                        .align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.height(9.dp))
                // in the top bar we are specifying tile as a text
                Text(
                    text = title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically),
                    textAlign = TextAlign.Justify,

                    )
            }
        }
    }

}
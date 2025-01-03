package uk.org.pentlandscouts.scoreboard_app.ui.topbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.scoreboard_app.R

@Composable
fun TopBarScreen() {
    val title= "Scoreboard App"
    Row {

        Image(
            // on below line we are specifying the drawable image for our image.
            painter = painterResource(id = R.mipmap.pentland_logo_x2_foreground ),
            //R.mipmap.pentland_logo_x2_foreground),
            contentDescription = "Scouts icon",
            modifier = Modifier
                .height(50.dp)
                .width(50.dp)
                .padding(5.dp)
                .background(Color.Transparent)
                .align(Alignment.CenterVertically)
        )
        Spacer(modifier = Modifier.height(9.dp))
        // in the top bar we are specifying tile as a text
        Text(
            text = title,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically)
                .background(Color.Transparent),

            textAlign = TextAlign.Justify,

            )
    }
}
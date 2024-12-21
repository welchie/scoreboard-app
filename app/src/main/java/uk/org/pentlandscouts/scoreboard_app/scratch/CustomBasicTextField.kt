
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uk.org.pentlandscouts.scoreboard_app.theme.FilterButtonBackground
import uk.org.pentlandscouts.scoreboard_app.theme.SearchBoxBackground
import uk.org.pentlandscouts.scoreboard_app.theme.SearchBoxLabel
import uk.org.pentlandscouts.scoreboard_app.theme.White

/**
 * Custom class for search filed in home page
 */
@Composable
fun CustomBasicTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .background(color = SearchBoxBackground, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp),
        cursorBrush = SolidColor(FilterButtonBackground),
        singleLine = true,
        textStyle = TextStyle(
            color = White,
            fontSize = 14.sp
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Leading Icon
                if (leadingIcon != null) {
                    leadingIcon()
                    Spacer(modifier = Modifier.width(8.dp))
                }

                // Placeholder or the TextField content
                Box(modifier = Modifier.weight(1f)) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            color = SearchBoxLabel,
                            fontSize = 16.sp
                        )
                    }
                    innerTextField()
                }

                // Trailing Icon
                if (trailingIcon != null) {
                    Spacer(modifier = Modifier.width(6.dp))
                    trailingIcon()
                }
            }
        },
    )
}
package com.example.scoreboard_app

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.breens.beetablescompose.BeeTablesCompose
import com.example.scoreboard_app.ui.theme.ScoreboardAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScoreboardAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),

                    color = MaterialTheme.colorScheme.background
                ) {
                    //ScoreBoard()
                    //scoreBoardLayout()
                    ScaffoldExample()
                }
            }
        }
    }

    @Composable
    fun scoreBoardLayout()
    {
        Column {
            MyDataTable()
            Row {
                ButtonRefresh { showMessage("Loading....")}
                ButtonSave { showMessage("Saving")}
                ButtonAdd { showMessage("Add Row")}
            }
        }

    }



    data class ScoreBoardData(val name:String, val numActivities: Int, val score:Int)
    @Composable
    fun MyDataTable() {

        val scoreBoardList = listOf(
            ScoreBoardData("Team 99",1,30),
            ScoreBoardData("Team 3",1,8),
            ScoreBoardData("Team 2",0,0),
            ScoreBoardData("Team 4",0,0),
            ScoreBoardData("Team 5",0,0),
            ScoreBoardData("Team 6",0,0),
            ScoreBoardData("Team 7",0,0),
            ScoreBoardData("Team 8",0,0),
            ScoreBoardData("Team 9",0,0),
            ScoreBoardData("Team 10",0,0),
            ScoreBoardData("Team 11",0,0),
            ScoreBoardData("Team 12",0,0),
            ScoreBoardData("Team 13",0,0),
            ScoreBoardData("Team 14",0,0),
            ScoreBoardData("Team 15",0,0),
            ScoreBoardData("Team 16",0,0),
            ScoreBoardData("Team 17",0,0),
            ScoreBoardData("Team 18",0,0),
            ScoreBoardData("Team 19",0,0),
            ScoreBoardData("Team 20",0,0),
            ScoreBoardData("Team 21",0,0)



        )

        val headerTitles = listOf("Team Name","Num Activities","Score")

        BeeTablesCompose (
            data = scoreBoardList,
            enableTableHeaderTitles = true,
            headerTableTitles = headerTitles,
            headerTitlesBorderColor = Color.White,
            headerTitlesBackGroundColor = Color.DarkGray,
            headerTitlesTextStyle = TextStyle(fontSize = 14.sp, color = Color.White),

            tableRowColors = listOf(Color.White, Color.White),
            rowBorderColor = Color.LightGray,
            rowTextStyle = TextStyle(fontSize = 10.sp, color =  Color.Black),
            tableElevation = 6.dp,
            shape = RoundedCornerShape(20.dp)
        )
    }

    @Composable
    fun ButtonAdd(onButtonClick: ()-> Unit){
        Button(
            onClick = onButtonClick,
            contentPadding = PaddingValues(all = 20.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 5.dp),
            modifier = Modifier.padding(20.dp)
        ){
            Text("Add")
        }
    }

    @Composable
    fun ButtonSave(onButtonClick: ()-> Unit){
        Button(
            onClick = onButtonClick,
            contentPadding = PaddingValues(all = 20.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 5.dp),
            modifier = Modifier.padding(20.dp)
        ){
            Text("Save")
        }
    }

    @Composable
    fun ButtonRefresh(onButtonClick: ()-> Unit){
        Button(
            onClick = onButtonClick,
            contentPadding = PaddingValues(all = 20.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 5.dp),
            modifier = Modifier.padding(20.dp)
        ){
            Text("Refresh")
        }
    }

    fun showMessage(message: String)
    {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
    }

}



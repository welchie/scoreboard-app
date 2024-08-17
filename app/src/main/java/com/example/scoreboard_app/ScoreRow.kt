package com.example.scoreboard_app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.breens.beetablescompose.BeeTablesCompose
import com.example.scoreboard_app.MainActivity.ScoreBoardData

@Preview
@Composable
fun ScoreBoard()
{
    //var scoredata = ScoreBoardData("Team 1",1,30)
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
        ScoreBoardData("Team 21",0,0),
        ScoreBoardData("Team 22",0,0),
        ScoreBoardData("Team 23",0,0),
        ScoreBoardData("Team 24",0,0),
        ScoreBoardData("Team 25",0,0),
        ScoreBoardData("Team 26",0,0),
        ScoreBoardData("Team 27",0,0),
        ScoreBoardData("Team 28",0,0),
        ScoreBoardData("Team 29",0,0),
        ScoreBoardData("Team 30",0,0),
        ScoreBoardData("Team 31",0,0),
        ScoreBoardData("Team 32",0,0),
        ScoreBoardData("Team 33",0,0),
        ScoreBoardData("Team 34",0,0),
        ScoreBoardData("Team 35",0,0), ScoreBoardData("Team 20",0,0),
        ScoreBoardData("Team 36",0,0),
        ScoreBoardData("Team 37",0,0)

    )



    Column {
        ScoreRowHeader()

         for(data in scoreBoardList) {
             ScoreDataRow(data)
         }
    }
}

@Preview
@Composable
fun ScoreRowHeader()
{
    Row {
        Text(text = "Team Name")
        Text(text = " ")
        Text(text = "Score")
        Text(text = " ")
        Text(text = "Num Activities ")
    }
}

@Preview
@Composable
fun ScoreDataRow(dataRow:ScoreBoardData)
{


    Row {

        Text(text = dataRow.name)
        Text(text = " ")
        Text(text = dataRow.score.toString())
        Text(text = " ")
        Text(text = dataRow.numActivities.toString())

    }
}

@Preview
@Composable
fun MyDataTable(modifier: Modifier) {

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

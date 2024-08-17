package com.example.scoreboard_app

import android.os.Message
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import com.example.scoreboard_app.MainActivity.ScoreBoardData

/*
 * Copyright 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */



@Preview
@Composable
fun LazyColumnTest() {
    Column(
        modifier = Modifier
            .background(Color.LightGray)
            .size(100.dp)
            .verticalScroll(rememberScrollState())
    )
    {
        LazyColumn {
            // Add a single item

            item {
                Text(text = "First item")
            }

            // Add 5 items
            items(500) { index ->
                Text(text = "Item: $index")
            }

            // Add another single item
            item {
                Text(text = "Last item")
            }
        }
    }
}


@Preview
@Composable
fun ScrollBoxes() {

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
    Column(
        modifier = Modifier
            .background(Color.LightGray)
            .size(600.dp)
            .fillMaxHeight()

    )
    {
        LazyColumn {


            // Add 5 items
            items(scoreBoardList) { index ->
                ScoreDataRow(index)

                //Text(text = "Item NEW: $index", modifier = Modifier.padding(2.dp).fillMaxHeight())
            }

        }
    }
}



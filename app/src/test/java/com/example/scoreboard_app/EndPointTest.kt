package com.example.scoreboard_app

import com.example.scoreboard_app.data.ScoreBoardAPI
import org.json.JSONArray
import org.junit.Assert.assertNotNull
import org.junit.Test

class EndPointTest {

    @Test
    fun getScorebaordTest()
    {
        val data = ScoreBoardAPI.getScoreBoardDataList()
        println("Number of teams ${data.length()}")

        println("Team Name \t No. Activities \t Score")
        for (i in 0 until data.length()) {
            val row = data.getJSONObject(i)
            println("${row.get("teamName")} \t ${row.get("numActivities")} \t ${row.get("score")}")
        }
        assertNotNull(data);
    }

}
package com.example.scoreboard_app

import com.example.scoreboard_app.data.ScoreBoardAPI
import org.junit.Assert.assertNotNull
import org.junit.Test

class EndPointTest {

    @Test
    fun getScorebaordTest()
    {
        val data = ScoreBoardAPI.getScoreBoard()
        println("Number of teams ${data.length()}")

        println("Team Name \t No. Activities \t Score")
        for (i in 0 until data.length()) {
            val row = data.getJSONObject(i)
            println("${row.get("teamName")} \t ${row.get("numActivities")} \t ${row.get("score")}")
        }
        assertNotNull(data);
    }

    @Test
    fun getTeamsTest()
    {
        val data = ScoreBoardAPI.getTeams()
        println("Number of teams ${data.length()}")

        println("Team Name \t Logo \t Id")
        for (i in 0 until data.length()) {
            val row = data.getJSONObject(i)
            println("${row.get("name")} \t ${row.get("logo")} \t ${row.get("id")}")
        }
        assertNotNull(data);
    }

}
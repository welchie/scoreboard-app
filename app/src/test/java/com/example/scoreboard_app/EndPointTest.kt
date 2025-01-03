package com.example.scoreboard_app

import org.junit.Assert.assertEquals
import uk.org.pentlandscouts.scoreboard_app.data.ScoreBoardAPI
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

    @Test
    fun getActivitiesTest()
    {
        val data = ScoreBoardAPI.getActivities()
        println("Number of Activities ${data.length()}")

        println("Activity Name \t Description \t Max Score \t Zone \t Id")
        for (i in 0 until data.length()) {
            val row = data.getJSONObject(i)
            println("${row.get("name")} \t ${row.get("description")} \t ${row.get("maxScore")} \t ${row.get("zone")} \t ${row.get("id")}")
        }
        assertNotNull(data);
    }

    @Test
    fun addScoreTest()
    {
        val teams = ScoreBoardAPI.getTeams()
        println("Number of teams ${teams.length()}")

        println("Team Name \t Logo \t Id")
        for (i in 0 until teams.length()) {
            val row = teams.getJSONObject(i)
            println("${row.get("name")} \t ${row.get("logo")} \t ${row.get("id")}")
        }

        val activities = ScoreBoardAPI.getActivities()
        println("Number of Activities ${activities.length()}")

        println("Activity Name \t Description \t Max Score \t Zone \t Id")
        for (i in 0 until activities.length()) {
            val row = activities.getJSONObject(i)
            println("${row.get("name")} \t ${row.get("description")} \t ${row.get("maxScore")} \t ${row.get("zone")} \t ${row.get("id")}")
        }

        var teamID:Int = teams.getJSONObject(0).get("id").toString().toInt()
        var actId = activities.getJSONObject(0).get("id").toString().toInt()
        var score = 25

        val status = ScoreBoardAPI.addScore(teamID,actId,score)
        assertEquals(status,200);
    }

}
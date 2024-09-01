package com.example.scoreboard_app

import org.json.JSONArray
import org.junit.Assert.assertNotNull
import org.junit.Test

class EndPointTest {

    private val URL: String = "http://ec2-34-243-199-28.eu-west-1.compute.amazonaws.com:8080/scoreboard/table";

    @Suppress("EXTENSION_SHADOWED_BY_MEMBER")
    operator fun <T> JSONArray.iterator(): Iterator<T> =
        @Suppress("UNCHECKED_CAST")
        (0 until this.length()).asSequence().map { this.get(it) as T }.iterator()

    @Test
    fun canConnect()
    {
        val data = ScoreBoardRest.getScoreBoardData()
        println("Number of teams ${data.length()}")

        println("Team Name \t No. Activities \t Score")
        for (i in 0 until data.length()) {
            val row = data.getJSONObject(i)
            println("${row.get("teamName")} \t ${row.get("numActivities")} \t ${row.get("score")}")
        }

        assertNotNull(data);
    }

}
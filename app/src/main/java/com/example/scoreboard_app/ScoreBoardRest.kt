package com.example.scoreboard_app

import khttp.responses.Response
import org.json.JSONObject


class ScoreBoardRest {

    fun getScoreBoardList () {
        lateinit var scoreBoardList: List<ScoreItem>
        scoreBoardList = ArrayList<ScoreItem>()
        val response: Response =
            khttp.get("http://ec2-34-243-199-28.eu-west-1.compute.amazonaws.com:8080/scoreboard/table")
        val obj: JSONObject = response.jsonObject
        println(obj)
        print(obj["teamName"])
        print(obj["score"])
        print(obj["numActivities"])
        print(obj["teamLogo"])

    }


}
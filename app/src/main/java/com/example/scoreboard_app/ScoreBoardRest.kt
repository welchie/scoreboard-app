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


        /**
         * [
         *   {
         *     "teamName": "Sharks",
         *     "score": 176,
         *     "numActivities": 4,
         *     "teamLogo": "2131492905"
         *   },
         *   {
         *     "teamName": "Owls",
         *     "score": 20,
         *     "numActivities": 4,
         *     "teamLogo": "2131492887"
         *   },
         *   {
         *     "teamName": "Elephants",
         *     "score": 10,
         *     "numActivities": 1,
         *     "teamLogo": "2131492868"
         *   },
         *   {
         *     "teamName": "Bees",
         *     "score": 0,
         *     "numActivities": 0,
         *     "teamLogo": "2131492865"
         *   },
         *   {
         *     "teamName": "Foxes",
         *     "score": 0,
         *     "numActivities": 0,
         *     "teamLogo": "2131492874"
         *   },
         *   {
         *     "teamName": "Pigs",
         *     "score": 0,
         *     "numActivities": 0,
         *     "teamLogo": "2131492896"
         *   },
         *   {
         *     "teamName": "Lions",
         *     "score": 0,
         *     "numActivities": 0,
         *     "teamLogo": "2131492884"
         *   },
         *   {
         *     "teamName": "Rays",
         *     "score": 0,
         *     "numActivities": 0,
         *     "teamLogo": "2131492899"
         *   },
         *   {
         *     "teamName": "Fish",
         *     "score": 0,
         *     "numActivities": 0,
         *     "teamLogo": "2131492871"
         *   },
         *   {
         *     "teamName": "Pandas",
         *     "score": 0,
         *     "numActivities": 0,
         *     "teamLogo": "2131492890"
         *   },
         *   {
         *     "teamName": "Hippos",
         *     "score": 0,
         *     "numActivities": 0,
         *     "teamLogo": "2131492880"
         *   },
         *   {
         *     "teamName": "Zebras",
         *     "score": 0,
         *     "numActivities": 0,
         *     "teamLogo": "2131492908"
         *   }
         * ]
         */
    }


}
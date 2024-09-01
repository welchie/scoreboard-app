package com.example.scoreboard_app

import khttp.responses.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject


class ScoreBoardRest {


    companion object {
        suspend fun getScoreBoardData(): JSONArray {
            return withContext(Dispatchers.IO) { getScoreBoardDataList() }
        }

        fun getScoreBoardDataList(): JSONArray {

            val URL: String = "http://ec2-34-243-199-28.eu-west-1.compute.amazonaws.com:8080/scoreboard/table"

            val response: Response =
                khttp.get(URL)
            val statusCode = response.statusCode
            //println("Status Code: $statusCode")
            val data: JSONArray = response.jsonArray
            //println(data)

            return data
        }
    }


}
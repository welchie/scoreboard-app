package com.example.scoreboard_app.data

import khttp.responses.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray


class ScoreBoardAPI {


    companion object {
        suspend fun getScoreBoardData(): JSONArray {
            return withContext(Dispatchers.IO) { getScoreBoardDataList() }
        }

        fun getScoreBoardDataList(): JSONArray {

            val URL: String = "http://ec2-34-243-199-28.eu-west-1.compute.amazonaws.com:8080/scoreboard/table"

            val response: Response =
                khttp.get(URL)
            val statusCode = response.statusCode
            val data: JSONArray = response.jsonArray

            return data
        }
    }


}
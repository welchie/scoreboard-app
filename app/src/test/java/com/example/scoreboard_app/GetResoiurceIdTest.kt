package com.example.scoreboard_app

import android.R.drawable
import android.util.Log
import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.lang.reflect.Field


class GetResoiurceIdTest {

    @Test
    fun getResourceIdTest()
    {
        //R.drawable.lion_background
        //val resourceID = getResourceId("lion_foreground");
        assertEquals(2131558420,getResourceIdFromMipMap("lion_foreground"))
        assertEquals(2131558423,getResourceIdFromMipMap("owl_foreground"))
        assertEquals(2131558404,getResourceIdFromMipMap("elephant_foreground"))
        assertEquals(2131558401,getResourceIdFromMipMap("bee_foreground"))
        assertEquals(2131558438,getResourceIdFromMipMap("pig_foreground"))
        assertEquals(2131558410,getResourceIdFromMipMap("fox_foreground"))
        assertEquals(2131558447,getResourceIdFromMipMap("shark_foreground"))
        assertEquals(2131558450,getResourceIdFromMipMap("zebra_foreground"))
        assertEquals(2131558441,getResourceIdFromMipMap("ray_foreground"))
        assertEquals(2131558416,getResourceIdFromMipMap("hippo_foreground"))
        assertEquals(2131558426,getResourceIdFromMipMap("panda_foreground"))
        assertEquals(2131558407,getResourceIdFromMipMap("fish_foreground"))

    }

    fun getResourceIdFromMipMap(name: String): Int {
        try {
            val res: Class<*> = R.mipmap::class.java
            val field: Field = res.getField(name)
            val drawableId: Int = field.getInt(null)
            return drawableId;
        } catch (e: Exception) {
            Log.e("MyTag", "Failure to get drawable id.", e)
        }

        return -1;
    }
}
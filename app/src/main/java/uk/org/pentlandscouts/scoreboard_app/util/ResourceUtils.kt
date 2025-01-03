package uk.org.pentlandscouts.scoreboard_app.util

import android.util.Log
import com.example.scoreboard_app.R
import java.lang.reflect.Field

class ResourceUtils {
    companion object {
        fun getResourceIdFromMipMap(name: String): Int {
            try {
                val res: Class<*> = R.mipmap::class.java
                val field: Field = res.getField(name)
                val drawableId: Int = field.getInt(null)
                return drawableId
            } catch (e: Exception) {
                Log.e("MyTag", "Failure to get drawable id.", e)
                return R.mipmap.pentland_logo_foreground
            }
        }


    }
}
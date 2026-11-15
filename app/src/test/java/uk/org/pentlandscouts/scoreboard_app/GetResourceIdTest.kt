package uk.org.pentlandscouts.scoreboard_app

import org.junit.Assert.assertEquals
import org.junit.Test
import uk.org.pentlandscouts.scoreboard_app.util.ResourceUtils

class GetResourceIdTest {

    @Test
    fun getResourceIdTest() {
        assertEquals(R.mipmap.lion_foreground, ResourceUtils.getResourceIdFromMipMap("lion_foreground"))
        assertEquals(R.mipmap.owl_foreground, ResourceUtils.getResourceIdFromMipMap("owl_foreground"))
        assertEquals(R.mipmap.elephant_foreground, ResourceUtils.getResourceIdFromMipMap("elephant_foreground"))
        assertEquals(R.mipmap.bee_foreground, ResourceUtils.getResourceIdFromMipMap("bee_foreground"))
        assertEquals(R.mipmap.pig_foreground, ResourceUtils.getResourceIdFromMipMap("pig_foreground"))
        assertEquals(R.mipmap.fox_foreground, ResourceUtils.getResourceIdFromMipMap("fox_foreground"))
        assertEquals(R.mipmap.shark_foreground, ResourceUtils.getResourceIdFromMipMap("shark_foreground"))
        assertEquals(R.mipmap.zebra_foreground, ResourceUtils.getResourceIdFromMipMap("zebra_foreground"))
        assertEquals(R.mipmap.ray_foreground, ResourceUtils.getResourceIdFromMipMap("ray_foreground"))
        assertEquals(R.mipmap.hippo_foreground, ResourceUtils.getResourceIdFromMipMap("hippo_foreground"))
        assertEquals(R.mipmap.panda_foreground, ResourceUtils.getResourceIdFromMipMap("panda_foreground"))
        assertEquals(R.mipmap.fish_foreground, ResourceUtils.getResourceIdFromMipMap("fish_foreground"))
        assertEquals(R.mipmap.gecko_foreground, ResourceUtils.getResourceIdFromMipMap("gecko_foreground"))
        assertEquals(R.mipmap.parrot_foreground, ResourceUtils.getResourceIdFromMipMap("parrot_foreground"))
    }

    @Test
    fun getUnknownResourceReturnsDefaultLogo() {
        assertEquals(
            R.mipmap.pentland_logo_foreground,
            ResourceUtils.getResourceIdFromMipMap("unknown_logo")
        )
    }
}

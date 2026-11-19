package uk.org.pentlandscouts.scoreboard_app.util

import uk.org.pentlandscouts.scoreboard_app.R

class ResourceUtils {
    companion object {
        private val mipmapResources: Map<String, Int> = mapOf(
            "bee" to R.mipmap.bee,
            "bee_foreground" to R.mipmap.bee_foreground,
            "bee_round" to R.mipmap.bee_round,
            "elephant" to R.mipmap.elephant,
            "elephant_foreground" to R.mipmap.elephant_foreground,
            "elephant_round" to R.mipmap.elephant_round,
            "fish" to R.mipmap.fish,
            "fish_foreground" to R.mipmap.fish_foreground,
            "fish_round" to R.mipmap.fish_round,
            "fox" to R.mipmap.fox,
            "fox_foreground" to R.mipmap.fox_foreground,
            "fox_round" to R.mipmap.fox_round,
            "gecko" to R.mipmap.gecko,
            "gecko_foreground" to R.mipmap.gecko_foreground,
            "gecko_round" to R.mipmap.gecko_round,
            "hippo" to R.mipmap.hippo,
            "hippo_foreground" to R.mipmap.hippo_foreground,
            "hippo_round" to R.mipmap.hippo_round,
            "ic_launcher_round" to R.mipmap.ic_launcher_round,
            "lion" to R.mipmap.lion,
            "lion_foreground" to R.mipmap.lion_foreground,
            "lion_round" to R.mipmap.lion_round,
            "owl" to R.mipmap.owl,
            "owl_foreground" to R.mipmap.owl_foreground,
            "owl_round" to R.mipmap.owl_round,
            "panda" to R.mipmap.panda,
            "panda_foreground" to R.mipmap.panda_foreground,
            "panda_round" to R.mipmap.panda_round,
            "parrot" to R.mipmap.parrot,
            "parrot_foreground" to R.mipmap.parrot_foreground,
            "parrot_round" to R.mipmap.parrot_round,
            "pentland_logo" to R.mipmap.pentland_logo,
            "pentland_logo_foreground" to R.mipmap.pentland_logo_foreground,
            "pentland_logo_round" to R.mipmap.pentland_logo_round,
            "pentland_logo_x2" to R.mipmap.pentland_logo_x2,
            "pentland_logo_x2_foreground" to R.mipmap.pentland_logo_x2_foreground,
            "pentland_logo_x2_round" to R.mipmap.pentland_logo_x2_round,
            "pig" to R.mipmap.pig,
            "pig_foreground" to R.mipmap.pig_foreground,
            "pig_round" to R.mipmap.pig_round,
            "ray" to R.mipmap.ray,
            "ray_foreground" to R.mipmap.ray_foreground,
            "ray_round" to R.mipmap.ray_round,
            "scout_logo" to R.mipmap.scout_logo,
            "scout_logo_foreground" to R.mipmap.scout_logo_foreground,
            "scout_logo_round" to R.mipmap.scout_logo_round,
            "shark" to R.mipmap.shark,
            "shark_foreground" to R.mipmap.shark_foreground,
            "shark_round" to R.mipmap.shark_round,
            "zebra" to R.mipmap.zebra,
            "zebra_foreground" to R.mipmap.zebra_foreground,
            "zebra_round" to R.mipmap.zebra_round
        )

        fun getResourceIdFromMipMap(name: String): Int {
            return mipmapResources[name] ?: R.mipmap.pentland_logo_foreground
        }
    }
}

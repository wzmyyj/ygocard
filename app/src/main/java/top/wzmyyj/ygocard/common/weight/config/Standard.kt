package top.wzmyyj.ygocard.common.weight.config

/**
 * Created on 2021/05/18.
 *
 * @author feling
 * @version 1.0.0
 * @since 1.0.0
 */
object Standard {

    val moldSize = intArrayOf(813, 1185)

    object Pic {
        val position = intArrayOf(100, 219)
        val size = intArrayOf(614, 616)
        val position_lb = intArrayOf(57, 213)
        val size_lb = intArrayOf(702, 528)
        const val flatRatio = 1.8f
    }

    object Mask {
        val position = intArrayOf(47, 735)
        val size = intArrayOf(719, 391)
    }

    object Attribute {
        val position = intArrayOf(680, 57)
        val size = intArrayOf(75, 75)
    }

    object Star {
        val position = intArrayOf(686, 145)
        val size = intArrayOf(50, 50)
        const val distance = 55
    }

    object Arrows {
        val center = floatArrayOf(406.5f, 526.5f)
        val arrow1_size = intArrayOf(140, 41)
        val arrow1_position = intArrayOf(-70, 308)
        val arrow2_size = intArrayOf(70, 70)
        val arrow2_position = intArrayOf(-336, 267)
    }

    object Line {
        val position = intArrayOf(64, 1079)
        const val width = 683
        const val lineWidth = 2
    }

    object ATK {
        const val font = "attack"
        const val fontSize = 36
        val position = intArrayOf(585, 1107)
        const val maxWidth = 72
        const val label = "ATK/"
        val labelPosition = intArrayOf(513, 1107)
    }

    object DEF {
        const val font = "link"
        const val fontSize = 36
        const val linkFontSize = 30
        val position = intArrayOf(750, 1107)
        const val maxWidth = 72
        const val label = "DEF/"
        const val linkLabel = "LINK-"
        val labelPosition = intArrayOf(678, 1107)
        val linkLabelPosition = intArrayOf(716, 1107)
    }

    object LbNumber {
        const val font = "lbNum"
        const val fontSize = 52
        val leftPosition = intArrayOf(86, 854)
        val rightPosition = intArrayOf(729, 854)
    }

    object CardBag {
        const val font = "password"
        const val fontSize = 24
        val position = intArrayOf(728, 871)
        val linkPosition = intArrayOf(665, 872)
        val pendulumPosition = intArrayOf(66, 1104)
    }

    object Password {
        const val font = "password"
        const val fontSize = 23
        val position = intArrayOf(40, 1147)
    }

    object Holo {
        val position = intArrayOf(743, 1115)
        val size = intArrayOf(42, 42)
    }

    object Name {
        const val font = "name"
        const val fontSize = 65
        const val maxWidth = 610
        val position = intArrayOf(65, 117)
    }


    object MonsterRace {
        const val font = "race"
        const val fontSize = 26
        val position = intArrayOf(53, 915)
        const val maxWidth = 610
    }

    object MonsterDesc {
        const val font = "desc"
        const val fontSize = 24
        val position = intArrayOf(64, 942)
        const val lineHeight = 26f
        const val maxLines = 6
        const val maxWidth = 683
    }

    object MonsterLbDesc {
        const val font = "desc"
        const val fontSize = 23
        val position = intArrayOf(128, 770)
        const val lineHeight = 24.5f
        const val maxLines = 5
        const val maxWidth = 556
    }

    object SpellDesc {
        const val font = "desc"
        const val fontSize = 24
        val position = intArrayOf(66, 915)
        const val lineHeight = 24f
        const val maxLines = 9
        const val maxWidth = 683
    }

    object SpellType {
        const val font = "type"
        const val fontSize = 48
        val position = intArrayOf(750, 185)
        val iconPosition = intArrayOf(667, 147)
        val iconSize = intArrayOf(46, 46)
    }

    object Copyright {
        const val font = "copyright"
        const val fontSize = 18
        val position = intArrayOf(730, 1146)
    }

}
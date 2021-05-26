package top.wzmyyj.ygocard.common.data

/**
 * Created on 2021/05/18.
 *
 * @author feling
 * @version 1.0.0
 * @since 1.0.0
 */
class CardInfo {

    companion object {
        const val CARD_TYPE_MONSTER = 0
        const val CARD_TYPE_SPELL = 1
        const val CARD_TYPE_TRAP = 2

        const val MONSTER_TYPE_TC = 0
        const val MONSTER_TYPE_XG = 1
        const val MONSTER_TYPE_YS = 1
        const val MONSTER_TYPE_RH = 1
        const val MONSTER_TYPE_TT = 1
        const val MONSTER_TYPE_LJ = 1
        const val MONSTER_TYPE_LB = 1
    }

    var lbDesc = ""
    var monsterDesc = "这个卡名的①②的效果1回合各能使用1次。\n" +
            "①：效果被无效化的怪兽在场上存在的场合，自己·对方的主要阶段才能发动。这张卡从手卡特殊召唤。" +
            "②：对方把怪兽特殊召唤的场合，根据那只怪兽从何处特殊召唤可以从以下效果选择1个发动。" +
            "●手卡：从手卡把1只怪兽特殊召唤。" +
            "●卡组：自己从卡组抽2张。" +
            "●额外卡组：选从额外卡组特殊召唤的那1只怪兽破坏。"
    var spellDesc = ""

    var i = 0

    var y = 0

    var z = 1

    var t = 0

    var m = 0

    var s = 1
}
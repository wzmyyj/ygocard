package top.wzmyyj.ygocard.common.weight.data

import android.graphics.Color

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


    var name: String = "奥金魔术师"
    var nameColor: Int = Color.BLACK

    var lbDesc: String = "①选择场上一张卡破坏。不然就给我10000块钱。②选择目的一张卡除外。不然就给我10000块钱。"
    var desc: String = "这个卡名的①②的效果1回合各能使用1次。\n" +
            "①：效果被无效化的怪兽在场上存在的场合，自己·对方的主要阶段才能发动。这张卡从手卡特殊召唤。" +
            "②：对方把怪兽特殊召唤的场合，根据那只怪兽从何处特殊召唤可以从以下效果选择1个发动。" +
            "●手卡：从手卡把1只怪兽特殊召唤。" +
            "●卡组：自己从卡组抽2张。" +
            "●额外卡组：选从额外卡组特殊召唤的那1只怪兽破坏。"

    var level: Int = 7
    var rank: Int = 4

    var bag: String = "EP19-JP001"

    var password: String = "1234567890"

    var copyright: String = "ⓒスタジオ·ダイス /集英社·テレビ東京·KONAMI"

    var showHolo: Boolean = true

    fun isMonster(): Boolean {
        return true
    }

    fun isPendulum(): Boolean {
        return true
    }

    fun isLink(): Boolean {
        return false
    }

    fun isXYZ(): Boolean {
        return false
    }

    fun isSynchro(): Boolean {
        return false
    }

    fun hasSpellIcon(): Boolean {
        return true
    }

    fun getRace(): String {
        return "【電子界族/鏈接/效果】"
    }
}
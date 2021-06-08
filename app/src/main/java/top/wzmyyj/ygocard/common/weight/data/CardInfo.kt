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

    object Language {
        const val TC = 0
        const val SC = 1
        const val JP = 2
        const val EN = 3
        const val KR = 4
    }

    object CardType {
        const val MONSTER = 0
        const val SPELL = 1
        const val TRAP = 2
    }

    object MonsterType {
        const val TC = 0
        const val XG = 1
        const val YS = 100
        const val RH = 200
        const val TT = 300
        const val CL = 400
        const val LB = 50
        const val LJ = 600
        const val TK = 1000
    }

    object Attribute {
        const val MO = -1
        const val XIAN = -2
        const val LIGHT = 1
        const val DARK = 2
        const val FIRE = 3
        const val WATER = 4
        const val EARTH = 5
        const val WIND = 6
        const val DIVINE = 7
    }

    object SpellType {
        const val ST_TC = 0
        const val ST_SG = 1
        const val ST_ZB = 2
        const val ST_CD = 3
        const val ST_YX = 4
        const val ST_YS = 5
        const val ST_FJ = 6
    }

    var lang = Language.SC

    var cardType: Int = CardType.MONSTER
    var monsterTypes: IntArray = intArrayOf(MonsterType.XG, MonsterType.LB, -1)

    var name: String = "奥金魔术师"
    var nameColor: Int = Color.BLACK

    var lbDesc: String = "①选择场上一张卡破坏。不然就给我10000块钱。②选择目的一张卡除外。不然就给我10000块钱。"
    var desc: String = "这个卡名的①②的效果1回合各能使用1次。\n" +
            "①：效果被无效化的怪兽在场上存在的场合，自己·对方的主要阶段才能发动。这张卡从手卡特殊召唤。" +
            "②：对方把怪兽特殊召唤的场合，根据那只怪兽从何处特殊召唤可以从以下效果选择1个发动。" +
            "●手卡：从手卡把1只怪兽特殊召唤。" +
            "●卡组：自己从卡组抽2张。" +
            "●额外卡组：选从额外卡组特殊召唤的那1只怪兽破坏。"


    var attr: Int = Attribute.LIGHT
    var level: Int = 7
    var rank: Int = 4

    var bag: String = "EP19-JP001"

    var password: String = "1234567890"

    var copyright: String = "ⓒスタジオ·ダイス /集英社·テレビ東京·KONAMI"

    var showHolo: Boolean = true

    var spellType: Int = SpellType.ST_TC

    fun isSpell(): Boolean {
        return cardType == CardType.SPELL
    }

    fun isTrap(): Boolean {
        return cardType == CardType.TRAP
    }

    fun isMonster(): Boolean {
        return cardType == CardType.MONSTER
    }

    fun isPendulum(): Boolean {
        return isMonster() && monsterTypes.contains(MonsterType.LB)
    }

    fun isLink(): Boolean {
        return isMonster() && monsterTypes.contains(MonsterType.LJ)
    }

    fun isXYZ(): Boolean {
        return isMonster() && monsterTypes.contains(MonsterType.CL)
    }

    fun isSynchro(): Boolean {
        return isMonster() && monsterTypes.contains(MonsterType.TT)
    }

    fun isFusion(): Boolean {
        return isMonster() && monsterTypes.contains(MonsterType.RH)
    }

    fun isRitual(): Boolean {
        return isMonster() && monsterTypes.contains(MonsterType.YS)
    }

    fun isToken(): Boolean {
        return isMonster() && monsterTypes.contains(MonsterType.TK)
    }

    fun isNormal(): Boolean {
        return isMonster() && monsterTypes.contains(MonsterType.TC)
    }

    fun hasSpellIcon(): Boolean {
        return (isSpell() || isTrap()) && spellType != SpellType.ST_TC
    }

    fun getRace(): String {
        return "【電子界族/鏈接/效果】"
    }

}
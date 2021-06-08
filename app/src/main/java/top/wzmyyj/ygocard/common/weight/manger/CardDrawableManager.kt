package top.wzmyyj.ygocard.common.weight.manger

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import top.wzmyyj.ygocard.R
import top.wzmyyj.ygocard.common.weight.data.CardInfo
import top.wzmyyj.ygocard.common.weight.data.CardInfo.Attribute.DARK
import top.wzmyyj.ygocard.common.weight.data.CardInfo.Attribute.DIVINE
import top.wzmyyj.ygocard.common.weight.data.CardInfo.Attribute.EARTH
import top.wzmyyj.ygocard.common.weight.data.CardInfo.Attribute.FIRE
import top.wzmyyj.ygocard.common.weight.data.CardInfo.Attribute.LIGHT
import top.wzmyyj.ygocard.common.weight.data.CardInfo.Attribute.MO
import top.wzmyyj.ygocard.common.weight.data.CardInfo.Attribute.WATER
import top.wzmyyj.ygocard.common.weight.data.CardInfo.Attribute.WIND
import top.wzmyyj.ygocard.common.weight.data.CardInfo.Attribute.XIAN
import top.wzmyyj.ygocard.common.weight.data.CardInfo.Language.EN
import top.wzmyyj.ygocard.common.weight.data.CardInfo.Language.JP
import top.wzmyyj.ygocard.common.weight.data.CardInfo.Language.KR
import top.wzmyyj.ygocard.common.weight.data.CardInfo.Language.SC
import top.wzmyyj.ygocard.common.weight.data.CardInfo.Language.TC
import top.wzmyyj.ygocard.common.weight.data.CardInfo.SpellType.ST_CD
import top.wzmyyj.ygocard.common.weight.data.CardInfo.SpellType.ST_FJ
import top.wzmyyj.ygocard.common.weight.data.CardInfo.SpellType.ST_SG
import top.wzmyyj.ygocard.common.weight.data.CardInfo.SpellType.ST_YS
import top.wzmyyj.ygocard.common.weight.data.CardInfo.SpellType.ST_YX
import top.wzmyyj.ygocard.common.weight.data.CardInfo.SpellType.ST_ZB
import java.lang.RuntimeException

/**
 * Created on 2021/06/04.
 *
 * @author feling
 * @version 1.0.0
 * @since 1.0.0
 */
class CardDrawableManager(private val context: Context) {

    private val _aoj by lazy { getDrawable(R.drawable.aoj) }

    private val _spell by lazy { getDrawable(R.drawable.c_frame_spell) }
    private val _trap by lazy { getDrawable(R.drawable.c_frame_trap) }
    private val _tc by lazy { getDrawable(R.drawable.c_frame_monster_tc) }
    private val _tcLb by lazy { getDrawable(R.drawable.c_frame_monster_tc_lb) }
    private val _xg by lazy { getDrawable(R.drawable.c_frame_monster_xg) }
    private val _xgLb by lazy { getDrawable(R.drawable.c_frame_monster_xg_lb) }
    private val _ys by lazy { getDrawable(R.drawable.c_frame_monster_ys) }
    private val _ysLb by lazy { getDrawable(R.drawable.c_frame_monster_ys_lb) }
    private val _rh by lazy { getDrawable(R.drawable.c_frame_monster_rh) }
    private val _rhLb by lazy { getDrawable(R.drawable.c_frame_monster_rh_lb) }
    private val _tt by lazy { getDrawable(R.drawable.c_frame_monster_tt) }
    private val _ttLb by lazy { getDrawable(R.drawable.c_frame_monster_tt_lb) }
    private val _cl by lazy { getDrawable(R.drawable.c_frame_monster_cl) }
    private val _clLb by lazy { getDrawable(R.drawable.c_frame_monster_cl_lb) }
    private val _lj by lazy { getDrawable(R.drawable.c_frame_monster_lj) }
    private val _tk by lazy { getDrawable(R.drawable.c_frame_monster_tk) }

    private val _level by lazy { getDrawable(R.drawable.c_star_level) }
    private val _rank by lazy { getDrawable(R.drawable.c_star_rank) }
    private val _lbMask by lazy { getDrawable(R.drawable.c_mask_lb) }

    private val _linkArrows by lazy {
        val a1 = getDrawable(R.drawable.c_arrow1_0)
        val a2 = getDrawable(R.drawable.c_arrow1_1)
        val a3 = getDrawable(R.drawable.c_arrow2_0)
        val a4 = getDrawable(R.drawable.c_arrow2_1)
        return@lazy arrayListOf(a1, a2, a3, a4)
    }

    private val _attrCN by lazy {
        val map = HashMap<Int, Drawable>()
        map[MO] = getDrawable(R.drawable.c_attr_spell)
        map[XIAN] = getDrawable(R.drawable.c_attr_trap)
        map[LIGHT] = getDrawable(R.drawable.c_attr_light)
        map[DARK] = getDrawable(R.drawable.c_attr_dark)
        map[FIRE] = getDrawable(R.drawable.c_attr_fire)
        map[WATER] = getDrawable(R.drawable.c_attr_water)
        map[EARTH] = getDrawable(R.drawable.c_attr_earth)
        map[WIND] = getDrawable(R.drawable.c_attr_wind)
        map[DIVINE] = getDrawable(R.drawable.c_attr_divine)
        return@lazy map
    }

    private val _attrJP by lazy {
        val map = HashMap<Int, Drawable>()
        map[MO] = getDrawable(R.drawable.c_attr_spell_jp)
        map[XIAN] = getDrawable(R.drawable.c_attr_trap_jp)
        map[LIGHT] = getDrawable(R.drawable.c_attr_light_jp)
        map[DARK] = getDrawable(R.drawable.c_attr_dark_jp)
        map[FIRE] = getDrawable(R.drawable.c_attr_fire_jp)
        map[WATER] = getDrawable(R.drawable.c_attr_water_jp)
        map[EARTH] = getDrawable(R.drawable.c_attr_earth_jp)
        map[WIND] = getDrawable(R.drawable.c_attr_wind_jp)
        map[DIVINE] = getDrawable(R.drawable.c_attr_divine_jp)
        return@lazy map
    }

    private val _attrEN by lazy {
        val map = HashMap<Int, Drawable>()
        map[MO] = getDrawable(R.drawable.c_attr_spell_en)
        map[XIAN] = getDrawable(R.drawable.c_attr_trap_en)
        map[LIGHT] = getDrawable(R.drawable.c_attr_light_en)
        map[DARK] = getDrawable(R.drawable.c_attr_dark_en)
        map[FIRE] = getDrawable(R.drawable.c_attr_fire_en)
        map[WATER] = getDrawable(R.drawable.c_attr_water_en)
        map[EARTH] = getDrawable(R.drawable.c_attr_earth_en)
        map[WIND] = getDrawable(R.drawable.c_attr_wind_en)
        map[DIVINE] = getDrawable(R.drawable.c_attr_divine_en)
        return@lazy map
    }

    private val _attrKR by lazy {
        val map = HashMap<Int, Drawable>()
        map[MO] = getDrawable(R.drawable.c_attr_spell_kr)
        map[XIAN] = getDrawable(R.drawable.c_attr_trap_kr)
        map[LIGHT] = getDrawable(R.drawable.c_attr_light_kr)
        map[DARK] = getDrawable(R.drawable.c_attr_dark_kr)
        map[FIRE] = getDrawable(R.drawable.c_attr_fire_kr)
        map[WATER] = getDrawable(R.drawable.c_attr_water_kr)
        map[EARTH] = getDrawable(R.drawable.c_attr_earth_kr)
        map[WIND] = getDrawable(R.drawable.c_attr_wind_kr)
        map[DIVINE] = getDrawable(R.drawable.c_attr_divine_kr)
        return@lazy map
    }

    private val _icon by lazy {
        val map = HashMap<Int, Drawable>()
        map[ST_SG] = getDrawable(R.drawable.c_icon_sg)
        map[ST_ZB] = getDrawable(R.drawable.c_icon_zb)
        map[ST_CD] = getDrawable(R.drawable.c_icon_cd)
        map[ST_YX] = getDrawable(R.drawable.c_icon_yx)
        map[ST_YS] = getDrawable(R.drawable.c_icon_ys)
        map[ST_FJ] = getDrawable(R.drawable.c_icon_fj)
        return@lazy map
    }

    private val _holo by lazy { getDrawable(R.drawable.c_holo) }


    private fun getDrawable(ids: Int): Drawable {
        return ContextCompat.getDrawable(context, ids)!!
    }

    /**
     * 获取框框。
     */
    fun getFrame(info: CardInfo): Drawable {
        return when {
            info.isSpell() -> _spell
            info.isTrap() -> _trap
            info.isRitual() -> if (info.isPendulum()) _ysLb else _ys
            info.isFusion() -> if (info.isPendulum()) _rhLb else _rh
            info.isSynchro() -> if (info.isPendulum()) _ttLb else _tt
            info.isXYZ() -> if (info.isPendulum()) _clLb else _cl
            info.isLink() -> _lj
            info.isToken() -> _tk
            info.isNormal() -> if (info.isPendulum()) _tcLb else _tc
            else -> if (info.isPendulum()) _xgLb else _xg
        }
    }

    /**
     * 获取属性。
     */
    fun getAttribute(info: CardInfo): Drawable {
        val map = when (info.lang) {
            SC, TC -> _attrCN
            JP -> _attrJP
            EN -> _attrEN
            KR -> _attrKR
            else -> throw RuntimeException("no such lang!")
        }
        if (info.isSpell()) return map[MO]!!
        if (info.isTrap()) return map[XIAN]!!
        return map[info.attr] ?: throw RuntimeException("no such attr!")
    }

    /**
     * 魔法陷阱图标。
     */
    fun getSpellIcon(info: CardInfo): Drawable {
        if (!info.hasSpellIcon()) throw RuntimeException("not has spellIcon!")
        return _icon[info.spellType] ?: throw RuntimeException("no such spellType!")
    }

    /**
     * 获取等级。
     */
    fun getLevel(): Drawable {
        return _level
    }

    /**
     * 获取阶级。
     */
    fun getRank(): Drawable {
        return _rank
    }

    /**
     * 获取连接箭头。
     */
    fun getLinkArrows(): List<Drawable> {
        return _linkArrows
    }

    /**
     * 获取防伪标记。
     */
    fun getHolo(): Drawable {
        return _holo
    }

    /**
     * 获取灵摆遮罩。
     */
    fun getLbMask(): Drawable {
        return _lbMask
    }

    /**
     * 获取默认图片。
     */
    fun getDefaultImage(): Drawable {
        return _aoj
    }

}
package top.wzmyyj.ygocard.common.weight.manger

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import top.wzmyyj.ygocard.R
import top.wzmyyj.ygocard.common.weight.data.CardInfo

/**
 * Created on 2021/06/04.
 *
 * @author feling
 * @version 1.0.0
 * @since 1.0.0
 */
class CardDrawableManager(private val context: Context) {

    private val _aoj by lazy { ContextCompat.getDrawable(context, R.drawable.aoj)!! }
    private val _fd by lazy { ContextCompat.getDrawable(context, R.drawable.c_frame_monster_xg)!! }
    private val _fdLb by lazy { ContextCompat.getDrawable(context, R.drawable.c_frame_monster_xg_lb)!! }
    private val _ad by lazy { ContextCompat.getDrawable(context, R.drawable.c_attr_dark)!! }

    private val _level by lazy { ContextCompat.getDrawable(context, R.drawable.c_star_level)!! }
    private val _rank by lazy { ContextCompat.getDrawable(context, R.drawable.c_star_rank)!! }
    private val _lbMask by lazy { ContextCompat.getDrawable(context, R.drawable.c_mask_lb)!! }

    private val _linkArrows by lazy {
        val a1 = ContextCompat.getDrawable(context, R.drawable.c_arrow1_0)!!
        val a2 = ContextCompat.getDrawable(context, R.drawable.c_arrow1_1)!!
        val a3 = ContextCompat.getDrawable(context, R.drawable.c_arrow2_0)!!
        val a4 = ContextCompat.getDrawable(context, R.drawable.c_arrow2_1)!!
        return@lazy arrayListOf(a1, a2, a3, a4)
    }

    private val _spellIcon by lazy { ContextCompat.getDrawable(context, R.drawable.c_icon_sg)!! }

    private val _holo by lazy { ContextCompat.getDrawable(context, R.drawable.c_holo)!! }


    /**
     * 获取框框。
     */
    fun getFrame(info: CardInfo): Drawable {
        return if (info.isPendulum()) _fdLb else _fd
    }

    /**
     * 获取属性。
     */
    fun getAttribute(info: CardInfo): Drawable {
        return _ad
    }

    /**
     * 魔法陷阱图标。
     */
    fun getSpellIcon(info: CardInfo): Drawable {
        return _spellIcon
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

}
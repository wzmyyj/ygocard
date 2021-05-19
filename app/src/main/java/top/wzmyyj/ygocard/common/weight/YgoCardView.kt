package top.wzmyyj.ygocard.common.weight

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import androidx.appcompat.widget.*
import top.wzmyyj.ygocard.common.data.CardInfo

/**
 * Created on 2021/05/18.
 *
 * @author feling
 * @version 1.0.0
 * @since 1.0.0
 */
class YgoCardView : AppCompatImageView {

    constructor(context: Context) : super(context, null)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr) {
        //todo
    }


    private var info = CardInfo()

    fun setCardInfo(info: CardInfo) {
        this.info = info
        invalidate()
    }

    fun getCardInfo() = info

    override fun onDraw(canvas: Canvas?) {
        canvas ?: return
        val info = this.info
        drawCard(canvas, info)
    }

    private fun drawCard(canvas: Canvas, info: CardInfo) {

    }

    /**
     * 绘制框框。
     */
    private fun drawFrame(canvas: Canvas, info: CardInfo) {

    }

    /**
     * 绘制怪兽卡。
     */
    private fun drawMonster(canvas: Canvas, info: CardInfo) {

    }

    /**
     * 绘制魔法卡。
     */
    private fun drawSpell(canvas: Canvas, info: CardInfo) {

    }

    /**
     * 绘制陷阱卡。
     */
    private fun drawTrap(canvas: Canvas, info: CardInfo) {

    }

    /**
     * 绘制名称。
     */
    private fun drawName(canvas: Canvas, info: CardInfo) {

    }

    /**
     * 绘制属性。
     */
    private fun drawAttribute(canvas: Canvas, info: CardInfo) {

    }

    /**
     * 绘制等级。
     */
    private fun drawLevel(canvas: Canvas, info: CardInfo) {

    }

    /**
     * 绘制阶级。
     */
    private fun drawRank(canvas: Canvas, info: CardInfo) {

    }

    /**
     * 绘制图面。
     */
    private fun drawImage(canvas: Canvas, info: CardInfo) {

    }

    /**
     * 绘制卡包。
     */
    private fun drawBag(canvas: Canvas, info: CardInfo) {

    }

    /**
     * 绘制怪兽卡类型。
     */
    private fun drawMonsterType(canvas: Canvas, info: CardInfo) {

    }

    /**
     * 绘制效果。
     */
    private fun drawDesc(canvas: Canvas, info: CardInfo) {

    }

    /**
     * 绘制灵摆效果。
     */
    private fun drawPDesc(canvas: Canvas, info: CardInfo) {

    }

    /**
     * 绘制灵摆刻度。
     */
    private fun drawPNumber(canvas: Canvas, info: CardInfo) {

    }

    /**
     * 绘制连接箭头。
     */
    private fun drawLinkArrow(canvas: Canvas, info: CardInfo) {

    }

    /**
     * 绘制攻击力防御力。
     */
    private fun drawATKAndDEF(canvas: Canvas, info: CardInfo) {

    }

    /**
     * 绘制魔法陷阱和图标。
     */
    private fun drawSpellOrTrapWithIcon(canvas: Canvas, info: CardInfo) {

    }

    /**
     * 绘制卡密。
     */
    private fun drawPassword(canvas: Canvas, info: CardInfo) {

    }

    /**
     * 绘制版权。
     */
    private fun drawCopyright(canvas: Canvas, info: CardInfo) {

    }

    /**
     * 绘制防伪标记。
     */
    private fun drawHolo(canvas: Canvas, info: CardInfo) {

    }

}
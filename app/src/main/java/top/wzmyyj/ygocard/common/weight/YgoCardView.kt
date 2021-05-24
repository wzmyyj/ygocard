package top.wzmyyj.ygocard.common.weight

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import top.wzmyyj.ygocard.R
import top.wzmyyj.ygocard.common.config.Standard
import top.wzmyyj.ygocard.common.config.Standard.Arrows
import top.wzmyyj.ygocard.common.config.Standard.Attribute
import top.wzmyyj.ygocard.common.config.Standard.CardBag
import top.wzmyyj.ygocard.common.config.Standard.Copyright
import top.wzmyyj.ygocard.common.config.Standard.Holo
import top.wzmyyj.ygocard.common.config.Standard.Name
import top.wzmyyj.ygocard.common.config.Standard.Password
import top.wzmyyj.ygocard.common.config.Standard.Star
import top.wzmyyj.ygocard.common.config.Standard.moldSize
import top.wzmyyj.ygocard.common.data.CardInfo
import kotlin.math.roundToInt


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

    private var scaleD: Float = 1f

    private var info = CardInfo()

    fun setCardInfo(info: CardInfo) {
        this.info = info
        invalidate()
    }

    fun getCardInfo() = info

    private fun Int.d(): Int {
        return (this * scaleD).roundToInt()
    }

    private fun Int.f(): Float {
        return this * scaleD
    }

    private fun Float.f(): Float {
        return this * scaleD
    }

    private val fd by lazy { ContextCompat.getDrawable(context, R.drawable.c_f_monster_lj)!! }
    private val ad by lazy { ContextCompat.getDrawable(context, R.drawable.c_a_cn_light)!! }

    private val level by lazy { ContextCompat.getDrawable(context, R.drawable.c_star_level)!! }
    private val rank by lazy { ContextCompat.getDrawable(context, R.drawable.c_star_rank)!! }

    private val arrow10 by lazy { ContextCompat.getDrawable(context, R.drawable.c_arrow1_0)!! }
    private val arrow11 by lazy { ContextCompat.getDrawable(context, R.drawable.c_arrow1_1)!! }
    private val arrow20 by lazy { ContextCompat.getDrawable(context, R.drawable.c_arrow2_0)!! }
    private val arrow21 by lazy { ContextCompat.getDrawable(context, R.drawable.c_arrow2_1)!! }

    private val holo by lazy { ContextCompat.getDrawable(context, R.drawable.c_holo_black)!! }

    private val nameTf by lazy { Typeface.createFromAsset(resources.assets, "fonts/cn.ttf") }
    private val passwordTf by lazy {
        Typeface.createFromAsset(
            resources.assets,
            "fonts/password.ttf"
        )
    }
    private val copyrightTf by lazy {
        Typeface.createFromAsset(
            resources.assets,
            "fonts/copyright.ttf"
        )
    }

    private val namePaint = Paint()
    private val passwordPaint = Paint()
    private val bagPaint = Paint()
    private val copyrightPaint = Paint()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        if (width > 0) {
            scaleD = 1.0f * width / moldSize[0]
            super.onMeasure(
                MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(
                    (scaleD * moldSize[1]).roundToInt(), MeasureSpec.EXACTLY
                )
            )
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }

    override fun onDraw(canvas: Canvas?) {
        canvas ?: return
        val info = this.info
        drawCard(canvas, info)
//        super.onDraw(canvas)
    }

    private fun drawCard(canvas: Canvas, info: CardInfo) {
        drawMonster(canvas, info)


        drawBag(canvas, info)
        drawPassword(canvas, info)
        drawCopyright(canvas, info)
        drawHolo(canvas, info)
    }

    /**
     * 绘制怪兽卡。
     */
    private fun drawMonster(canvas: Canvas, info: CardInfo) {
        drawFrame(canvas, info)
        drawName(canvas, info)
        drawAttribute(canvas, info)
        drawLevel(canvas, info)
        drawRank(canvas, info)
        drawLinkArrow(canvas, info)
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
     * 绘制框框。
     */
    private fun drawFrame(canvas: Canvas, info: CardInfo) {
        val frameDrawable = fd
        frameDrawable.setBounds(0, 0, moldSize[0].d(), moldSize[1].d())
        frameDrawable.draw(canvas)
    }

    /**
     * 绘制名称。
     */
    private fun drawName(canvas: Canvas, info: CardInfo) {
        val name = "雙穹之騎士 阿斯特拉姆"
        val typeface = nameTf
        val textPaint = namePaint
        val pos = Name.position
        val size = Name.fontSize.f()
        val maxW = Name.maxWidth.f()
        val x = pos[0].f()
        val y = pos[1].f()
        textPaint.color = Color.WHITE
        textPaint.textSize = size
        textPaint.typeface = typeface
        textPaint.isAntiAlias = true
        val w = textPaint.measureText(name)
        if (w > maxW) {
            val scaleX = (maxW * 100 / w) / 100f
            textPaint.textScaleX = scaleX
        }
        canvas.drawText(name, x, y, textPaint)
        if (info.y > 0) {
            textPaint.strokeWidth = 1.f()
            textPaint.color = Color.GRAY
            textPaint.style = Paint.Style.STROKE
            canvas.drawText(name, x, y, textPaint)
        }
        textPaint.reset()
    }

    /**
     * 绘制属性。
     */
    private fun drawAttribute(canvas: Canvas, info: CardInfo) {
        val attributeDrawable = ad
        val pos = Attribute.position
        val size = Attribute.size
        val right = pos[0] + size[0]
        val bottom = pos[1] + size[1]
        attributeDrawable.setBounds(pos[0].d(), pos[1].d(), right.d(), bottom.d())
        attributeDrawable.draw(canvas)
    }

    /**
     * 绘制等级。
     */
    private fun drawLevel(canvas: Canvas, info: CardInfo) {
        if (info.i == 0) return
        val lv = 4
        if (lv > 13 || lv <= 0) return
        val levelDrawable = level
        val pos = Star.position
        val size = Star.size
        val distance = Star.distance
        for (i in 0 until lv) {
            val left = pos[0] - distance * i
            val right = left + size[0]
            val bottom = pos[1] + size[1]
            levelDrawable.setBounds(left.d(), pos[1].d(), right.d(), bottom.d())
            levelDrawable.draw(canvas)
        }
    }

    /**
     * 绘制阶级。
     */
    private fun drawRank(canvas: Canvas, info: CardInfo) {
        if (info.i == 0) return
        val rk = 4
        if (rk > 13 || rk <= 0) return
        val rankDrawable = rank
        val pos = Star.position
        val size = Star.size
        val distance = Star.distance
        for (i in 0 until rk) {
            val left = moldSize[0] - pos[0] - size[0] + distance * i
            val right = left + size[0]
            val bottom = pos[1] + size[1]
            rankDrawable.setBounds(left.d(), pos[1].d(), right.d(), bottom.d())
            rankDrawable.draw(canvas)
        }
    }

    /**
     * 绘制连接箭头。
     */
    private fun drawLinkArrow(canvas: Canvas, info: CardInfo) {
        val arr = arrayOf(0, 1, 1, 0, 0, 0, 1, 1)
        val arrow10Drawable = arrow10
        val arrow11Drawable = arrow11
        val arrow20Drawable = arrow20
        val arrow21Drawable = arrow21
        for ((i, v) in arr.withIndex()) {
            var pos: IntArray
            var size: IntArray
            var d: Drawable
            if (i % 2 == 0) {
                pos = Arrows.arrow1_position
                size = Arrows.arrow1_size
                d = if (v == 0) arrow10Drawable else arrow11Drawable
            } else {
                pos = Arrows.arrow2_position
                size = Arrows.arrow2_size
                d = if (v == 0) arrow20Drawable else arrow21Drawable
            }
            val center = Arrows.center
            val right = pos[0] + size[0]
            val bottom = pos[1] + size[1]
            d.setBounds(pos[0].d(), pos[1].d(), right.d(), bottom.d())
            val c0 = (center[0] + 0.5f).f()
            val c1 = center[1].f()
            canvas.save()
            canvas.translate(c0, c1)
            canvas.rotate(90f * (i / 2))
            d.draw(canvas)
            canvas.restore()
        }
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
        val password = "EP19-JP001"
        val typeface = passwordTf
        val textPaint = bagPaint
        val pos = when (info.z) {
            1 -> CardBag.linkPosition
            2 -> CardBag.pendulumPosition
            else -> CardBag.position
        }
        val size = CardBag.fontSize.f()
        val x = pos[0].f()
        val y = pos[1].f()
        textPaint.color = Color.BLACK
        textPaint.textSize = size
        textPaint.typeface = typeface
        textPaint.isAntiAlias = true
        textPaint.textAlign = if (info.z == 2) Paint.Align.LEFT else Paint.Align.RIGHT
        canvas.drawText(password, x, y, textPaint)
        textPaint.reset()
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
        val password = "12345678"
        val typeface = passwordTf
        val textPaint = passwordPaint
        val pos = Password.position
        val size = Password.fontSize.f()
        val x = pos[0].f()
        val y = pos[1].f()
        textPaint.color = Color.BLACK
        textPaint.textSize = size
        textPaint.typeface = typeface
        textPaint.isAntiAlias = true
        textPaint.textAlign = Paint.Align.LEFT
        canvas.drawText(password, x, y, textPaint)
        textPaint.reset()
    }

    /**
     * 绘制版权。
     */
    private fun drawCopyright(canvas: Canvas, info: CardInfo) {
        val copyright = "ⓒスタジオ·ダイス /集英社·テレビ東京·KONAMI"
        val typeface = copyrightTf
        val textPaint = copyrightPaint
        val pos = Copyright.position
        val size = Copyright.fontSize.f()
        val x = pos[0].f()
        val y = pos[1].f()
        textPaint.color = Color.BLACK
        textPaint.textSize = size
        textPaint.typeface = typeface
        textPaint.isAntiAlias = true
        textPaint.textAlign = Paint.Align.RIGHT
        canvas.drawText(copyright, x, y, textPaint)
        textPaint.reset()
    }

    /**
     * 绘制防伪标记。
     */
    private fun drawHolo(canvas: Canvas, info: CardInfo) {
        val pos = Holo.position
        val size = Holo.size
        val holoDrawable = holo
        val right = pos[0] + size[0]
        val bottom = pos[1] + size[1]
        holoDrawable.setBounds(pos[0].d(), pos[1].d(), right.d(), bottom.d())
        holoDrawable.draw(canvas)
    }

}
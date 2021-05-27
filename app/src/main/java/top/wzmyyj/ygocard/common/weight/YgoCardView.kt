package top.wzmyyj.ygocard.common.weight

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import top.wzmyyj.ygocard.R
import top.wzmyyj.ygocard.common.config.Standard.ATK
import top.wzmyyj.ygocard.common.config.Standard.Arrows
import top.wzmyyj.ygocard.common.config.Standard.Attribute
import top.wzmyyj.ygocard.common.config.Standard.CardBag
import top.wzmyyj.ygocard.common.config.Standard.Copyright
import top.wzmyyj.ygocard.common.config.Standard.DEF
import top.wzmyyj.ygocard.common.config.Standard.Holo
import top.wzmyyj.ygocard.common.config.Standard.LbNumber
import top.wzmyyj.ygocard.common.config.Standard.Line
import top.wzmyyj.ygocard.common.config.Standard.MonsterDesc
import top.wzmyyj.ygocard.common.config.Standard.MonsterLbDesc
import top.wzmyyj.ygocard.common.config.Standard.MonsterRace
import top.wzmyyj.ygocard.common.config.Standard.Name
import top.wzmyyj.ygocard.common.config.Standard.Password
import top.wzmyyj.ygocard.common.config.Standard.Pic
import top.wzmyyj.ygocard.common.config.Standard.SpellDesc
import top.wzmyyj.ygocard.common.config.Standard.SpellType
import top.wzmyyj.ygocard.common.config.Standard.Star
import top.wzmyyj.ygocard.common.config.Standard.moldSize
import top.wzmyyj.ygocard.common.data.CardInfo
import kotlin.math.*


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

    private val aoj by lazy { ContextCompat.getDrawable(context, R.drawable.aoj)!! }
    private val fd by lazy { ContextCompat.getDrawable(context, R.drawable.c_f_monster_xg)!! }
    private val ad by lazy { ContextCompat.getDrawable(context, R.drawable.c_a_cn_light)!! }

    private val level by lazy { ContextCompat.getDrawable(context, R.drawable.c_star_level)!! }
    private val rank by lazy { ContextCompat.getDrawable(context, R.drawable.c_star_rank)!! }

    private val arrow10 by lazy { ContextCompat.getDrawable(context, R.drawable.c_arrow1_0)!! }
    private val arrow11 by lazy { ContextCompat.getDrawable(context, R.drawable.c_arrow1_1)!! }
    private val arrow20 by lazy { ContextCompat.getDrawable(context, R.drawable.c_arrow2_0)!! }
    private val arrow21 by lazy { ContextCompat.getDrawable(context, R.drawable.c_arrow2_1)!! }

    private val spellIcon by lazy { ContextCompat.getDrawable(context, R.drawable.c_icon_sg)!! }

    private val holo by lazy { ContextCompat.getDrawable(context, R.drawable.c_holo_black)!! }

    private val cnTf by lazy { Typeface.createFromAsset(resources.assets, "fonts/cn.ttf") }
    private val numberTf by lazy { Typeface.createFromAsset(resources.assets, "fonts/number.ttf") }
    private val linkTf by lazy { Typeface.createFromAsset(resources.assets, "fonts/link.ttf") }
    private val raceTf by lazy { Typeface.createFromAsset(resources.assets, "fonts/race.ttf") }

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
    private val racePaint = Paint()
    private val atkDefPaint = Paint()
    private val lpNumberPaint = Paint()
    private val spellPaint = Paint()
    private val descPaint = Paint()

    private val passwordPaint = Paint()
    private val bagPaint = Paint()
    private val copyrightPaint = Paint()

    private val mTextPaint by lazy {
//        val paint = TextPaint(Paint.ANTI_ALIAS_FLAG)
//        paint.density = resources.displayMetrics.density
//        return@lazy paint
        TextView(context).paint
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        if (width > 0) {
            scaleD = 1.0f * width / moldSize[0]
            super.onMeasure(
                MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(moldSize[1].d(), MeasureSpec.EXACTLY)
            )
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }

    override fun onDraw(canvas: Canvas?) {
        canvas ?: return
        val info = this.info
        drawCard(canvas, info)
    }

    private fun drawCard(canvas: Canvas, info: CardInfo) {
        drawMonster(canvas, info)
        drawSpellOrTrap(canvas, info)
        drawBag(canvas, info)
        drawPassword(canvas, info)
        drawCopyright(canvas, info)
        drawHolo(canvas, info)
    }

    /**
     * 绘制怪兽卡。
     */
    private fun drawMonster(canvas: Canvas, info: CardInfo) {
        if (info.t != 0) return
        drawFrame(canvas, info)
        drawName(canvas, info)
        drawAttribute(canvas, info)
        drawImage(canvas, info)
        drawLevel(canvas, info)
        drawRank(canvas, info)
        drawLinkArrow(canvas, info)
        drawLbNumber(canvas, info)
        drawLbDesc(canvas, info)
        drawMonsterRace(canvas, info)
        drawMonsterDesc(canvas, info)
        drawATKAndDEF(canvas, info)
    }

    /**
     * 绘制魔法卡或陷阱卡。
     */
    private fun drawSpellOrTrap(canvas: Canvas, info: CardInfo) {
        if (info.t == 0) return
        drawFrame(canvas, info)
        drawName(canvas, info)
        drawAttribute(canvas, info)
        drawSpellOrTrapWithIcon(canvas, info)
        drawImage(canvas, info)
        drawSpellOrTrapDesc(canvas, info)
    }

    /**
     * 绘制框框。
     */
    private fun drawFrame(canvas: Canvas, info: CardInfo) {
        val frameDrawable = fd
        val right = moldSize[0].d()
        val bottom = moldSize[1].d()
        frameDrawable.setBounds(0, 0, right, bottom)
        frameDrawable.draw(canvas)
    }

    /**
     * 绘制名称。
     */
    private fun drawName(canvas: Canvas, info: CardInfo) {
        val name = "雙穹之騎士 阿斯特拉姆"
        val typeface = cnTf
        val textPaint = namePaint
        val pos = Name.position
        val size = Name.fontSize.f()
        val maxW = Name.maxWidth.f()
        textPaint.color = Color.BLACK
        textPaint.textSize = size
        textPaint.typeface = typeface
        textPaint.style = Paint.Style.FILL
        textPaint.isAntiAlias = true
        val x = pos[0].f()
        val y = pos[1].f()
        canvas.fillText(name, x, y, textPaint, maxW)
        if (info.y > 0) {
            textPaint.strokeWidth = 1.f()
            textPaint.color = Color.GRAY
            textPaint.style = Paint.Style.STROKE
            canvas.fillText(name, x, y, textPaint, maxW)
        }
    }

    /**
     * 绘制图面。
     */
    private fun drawImage(canvas: Canvas, info: CardInfo) {
        val imageDrawable = aoj
        val pos: IntArray
        val size: IntArray
        if (info.m == 1 && info.t == 0) {
            pos = Pic.position_lb
            size = Pic.size_lb
        } else {
            pos = Pic.position
            size = Pic.size
        }
        val left = pos[0].d()
        val top = pos[1].d()
        val right = (pos[0] + size[0]).d()
        val bottom = (pos[1] + size[1]).d()
        imageDrawable.setBounds(left, top, right, bottom)
        imageDrawable.draw(canvas)
    }


    /**
     * 绘制属性。
     */
    private fun drawAttribute(canvas: Canvas, info: CardInfo) {
        val attributeDrawable = ad
        val pos = Attribute.position
        val size = Attribute.size
        val left = pos[0].d()
        val top = pos[1].d()
        val right = (pos[0] + size[0]).d()
        val bottom = (pos[1] + size[1]).d()
        attributeDrawable.setBounds(left, top, right, bottom)
        attributeDrawable.draw(canvas)
    }

    /**
     * 绘制等级。
     */
    private fun drawLevel(canvas: Canvas, info: CardInfo) {
        if (info.m == 3 || info.m == 2) return
        val lv = 4
        if (lv > 13 || lv <= 0) return
        val levelDrawable = level
        val pos = Star.position
        val size = Star.size
        val distance = Star.distance
        for (i in 0 until lv) {
            val start = (pos[0] - distance * i)
            val left = start.d()
            val top = pos[1].d()
            val right = (start + size[0]).d()
            val bottom = (pos[1] + size[1]).d()
            levelDrawable.setBounds(left, top, right, bottom)
            levelDrawable.draw(canvas)
        }
    }

    /**
     * 绘制阶级。
     */
    private fun drawRank(canvas: Canvas, info: CardInfo) {
        if (info.m != 3) return
        val rk = 4
        if (rk > 13 || rk <= 0) return
        val rankDrawable = rank
        val pos = Star.position
        val size = Star.size
        val distance = Star.distance
        for (i in 0 until rk) {
            val start = (moldSize[0] - pos[0] - size[0] + distance * i)
            val left = start.d()
            val top = pos[1].d()
            val right = (start + size[0]).d()
            val bottom = (pos[1] + size[1]).d()
            rankDrawable.setBounds(left, top, right, bottom)
            rankDrawable.draw(canvas)
        }
    }

    /**
     * 绘制连接箭头。
     */
    private fun drawLinkArrow(canvas: Canvas, info: CardInfo) {
        if (info.m != 2) return
        val arr = arrayOf(0, 1, 1, 0, 0, 0, 1, 1)
        val arrow10Drawable = arrow10
        val arrow11Drawable = arrow11
        val arrow20Drawable = arrow20
        val arrow21Drawable = arrow21
        for ((i, v) in arr.withIndex()) {
            val pos: IntArray
            val size: IntArray
            val arrowDrawable: Drawable
            if (i % 2 == 0) {
                pos = Arrows.arrow1_position
                size = Arrows.arrow1_size
                arrowDrawable = if (v == 0) arrow10Drawable else arrow11Drawable
            } else {
                pos = Arrows.arrow2_position
                size = Arrows.arrow2_size
                arrowDrawable = if (v == 0) arrow20Drawable else arrow21Drawable
            }
            val left = pos[0].d()
            val top = pos[1].d()
            val right = (pos[0] + size[0]).d()
            val bottom = (pos[1] + size[1]).d()
            arrowDrawable.setBounds(left, top, right, bottom)
            val center = Arrows.center
            val c0 = center[0].f()
            val c1 = center[1].f()
            val degrees = 90f * (i / 2)
            canvas.save()
            canvas.translate(c0, c1)
            canvas.rotate(degrees)
            arrowDrawable.draw(canvas)
            canvas.restore()
        }
    }

    /**
     * 绘制灵摆刻度。
     */
    private fun drawLbNumber(canvas: Canvas, info: CardInfo) {
        if (info.m != 1) return
        val number = "10"
        val typeface = numberTf
        val textPaint = lpNumberPaint
        val size = LbNumber.fontSize.f()
        textPaint.color = Color.BLACK
        textPaint.textSize = size
        textPaint.typeface = typeface
        textPaint.isAntiAlias = true
        textPaint.textAlign = Paint.Align.CENTER
        val leftPos = LbNumber.leftPosition
        val xl = leftPos[0].f()
        val yl = leftPos[1].f()
        canvas.drawText(number, xl, yl, textPaint)
        val rightPos = LbNumber.rightPosition
        val xr = rightPos[0].f()
        val yr = rightPos[1].f()
        canvas.drawText(number, xr, yr, textPaint)
    }

    /**
     * 绘制怪兽卡类型。
     */
    private fun drawMonsterRace(canvas: Canvas, info: CardInfo) {
        val race = "【電子界族/鏈接/效果】"
        val typeface = cnTf
        val textPaint = racePaint
        val pos = MonsterRace.position
        val size = MonsterRace.fontSize.f()
        val maxW = MonsterRace.maxWidth.f()
        textPaint.color = Color.BLACK
        textPaint.textSize = size
        textPaint.typeface = typeface
        textPaint.isAntiAlias = true
        val x = pos[0].f()
        val y = pos[1].f()
        canvas.fillText(race, x, y, textPaint, maxW)
    }

    /**
     * 绘制攻击力防御力。
     */
    private fun drawATKAndDEF(canvas: Canvas, info: CardInfo) {
        val typeface = numberTf
        val textPaint = atkDefPaint
        textPaint.color = Color.BLACK
        textPaint.typeface = typeface
        textPaint.isAntiAlias = true
        textPaint.textAlign = Paint.Align.RIGHT
        // 绘制 ATK/
        val atkLabel = ATK.label
        val atkLabelPos = ATK.labelPosition
        val atkSize = ATK.fontSize.f()
        textPaint.textSize = atkSize
        val x1 = atkLabelPos[0].f()
        val y1 = atkLabelPos[1].f()
        canvas.drawText(atkLabel, x1, y1, textPaint)
        // 绘制 atk数值
        val atkNumber = "3000"
        val atkNumberPos = ATK.position
        val atkNumberSize = ATK.fontSize.f()
        val atkNumberMaxW = ATK.maxWidth.f()
        textPaint.textSize = atkNumberSize
        val x2 = atkNumberPos[0].f()
        val y2 = atkNumberPos[1].f()
        canvas.drawText(atkNumber, x2, y2, textPaint)
        canvas.fillText(atkNumber, x2, y2, textPaint, atkNumberMaxW)
        if (info.m != 2) {
            // 绘制 DEF/
            val defLabel = DEF.label
            val defLabelPos = DEF.labelPosition
            val defSize = DEF.fontSize.f()
            textPaint.textSize = defSize
            val x3 = defLabelPos[0].f()
            val y3 = defLabelPos[1].f()
            canvas.drawText(defLabel, x3, y3, textPaint)
            // 绘制 def数值
            val defNumber = "3000"
            val defNumberPos = DEF.position
            val defNumberMaxW = DEF.maxWidth.f()
            val x4 = defNumberPos[0].f()
            val y4 = defNumberPos[1].f()
            canvas.fillText(defNumber, x4, y4, textPaint, defNumberMaxW)
        } else {
            // 绘制 LINK-
            val linkTypeface = linkTf
            val linkLabel = DEF.linkLabel
            val linkLabelPos = DEF.linkLabelPosition
            val linkSize = DEF.linkFontSize.f()
            textPaint.typeface = linkTypeface
            textPaint.textSize = linkSize
            val x5 = linkLabelPos[0].f()
            val y5 = linkLabelPos[1].f()
            canvas.drawText(linkLabel, x5, y5, textPaint)
            // 绘制 link数值
            val linkNumber = "4"
            textPaint.textAlign = Paint.Align.LEFT
            canvas.drawText(linkNumber, x5, y5, textPaint)
        }
        // 绘制 黑线
        val linePosition = Line.position
        val lineWidth = Line.lineWidth
        val lineLength = Line.width
        val sX = linePosition[0].f()
        val sY = linePosition[1].f()
        val eX = (linePosition[0] + lineLength).f()
        val eY = (linePosition[1] + lineWidth).f()
        canvas.drawRect(sX, sY, eX, eY, textPaint)
    }

    /**
     * 绘制魔法陷阱和图标。
     */
    private fun drawSpellOrTrapWithIcon(canvas: Canvas, info: CardInfo) {
        val typeDesc = "魔法卡"
        val typeface = cnTf
        val textPaint = spellPaint
        val size = SpellType.fontSize.f()
        textPaint.color = Color.BLACK
        textPaint.textSize = size
        textPaint.typeface = typeface
        textPaint.isAntiAlias = true
        textPaint.textAlign = Paint.Align.RIGHT
        if (info.s == 1) {
            val iconPos = SpellType.iconPosition
            val iconSize = SpellType.iconSize
            val iconDrawable = spellIcon
            val left = iconPos[0].d()
            val top = iconPos[1].d()
            val right = (iconPos[0] + iconSize[0]).d()
            val bottom = (iconPos[1] + iconSize[1]).d()
            iconDrawable.setBounds(left, top, right, bottom)
            iconDrawable.draw(canvas)
            val typeEnd = "】"
            val textPos = SpellType.position
            val x1 = textPos[0].f()
            val y1 = textPos[1].f()
            canvas.drawText(typeEnd, x1, y1, textPaint)
            val type = "【$typeDesc"
            val x2 = iconPos[0].f()
            val y2 = textPos[1].f()
            canvas.drawText(type, x2, y2, textPaint)
        } else {
            val type = "【$typeDesc】"
            val pos = SpellType.position
            val x = pos[0].f()
            val y = pos[1].f()
            canvas.drawText(type, x, y, textPaint)
        }
    }

    private var tempLbDesc = ""
    private var tempMonsterDesc = ""
    private var tempSpellDesc = ""
    private val tempLbDescList = ArrayList<String>()
    private val tempMonsterDescList = ArrayList<String>()
    private val tempSpellDescList = ArrayList<String>()
    private val tempLbScaleList = ArrayList<Float>()
    private val tempMonsterScaleList = ArrayList<Float>()
    private val tempSpellScaleList = ArrayList<Float>()

    /**
     * 绘制灵摆效果。
     */
    private fun drawLbDesc(canvas: Canvas, info: CardInfo) {
        if (info.i == 0) return
        val desc = info.lbDesc
        val descList = tempLbDescList
        val scaleList = tempLbScaleList
        val typeface = cnTf
        val textPaint = descPaint
        val size = MonsterLbDesc.fontSize.f()
        val pos = MonsterDesc.position
        val lineHeight = MonsterLbDesc.lineHeight
        val maxWidth = MonsterLbDesc.maxWidth.f()
        val maxLines = MonsterLbDesc.maxLines
        textPaint.color = Color.BLACK
        textPaint.textSize = size
        textPaint.typeface = typeface
        textPaint.isAntiAlias = true
        textPaint.textAlign = Paint.Align.LEFT
        if (tempLbDesc != desc) {
            descSplit(desc, descList, scaleList, textPaint, maxLines, maxWidth)
            tempLbDesc = desc
        }
        if (descList.size != scaleList.size) throw RuntimeException("error")
        for (i in descList.indices) {
            val para = descList[i]
            val scale = scaleList[i]
            val x = pos[0].f()
            val y = (pos[1] + i * lineHeight).f()
            canvas.fillText(para, x, y, textPaint, maxWidth, scale)
        }
    }

    /**
     * 绘制效果。
     */
    private fun drawMonsterDesc(canvas: Canvas, info: CardInfo) {
        val desc = info.monsterDesc
        val descList = tempMonsterDescList
        val scaleList = tempMonsterScaleList
        val typeface = cnTf
        val textPaint = mTextPaint
        val size = MonsterDesc.fontSize.f()
        val pos = MonsterDesc.position
        val lineHeight = MonsterDesc.lineHeight
        val maxWidth = MonsterDesc.maxWidth.f()
        val maxLines = MonsterDesc.maxLines
        textPaint.color = Color.BLACK
        textPaint.textSize = size
        textPaint.typeface = typeface
        textPaint.isAntiAlias = true
        textPaint.textAlign = Paint.Align.LEFT
        if (tempMonsterDesc != desc) {
            descSplit(desc, descList, scaleList, textPaint, maxLines, maxWidth)
//            tempMonsterDesc = desc
        }
        if (descList.size != scaleList.size) throw RuntimeException("error")
        for (i in descList.indices) {
            val para = descList[i]
            val scale = scaleList[i]
            val x = pos[0].f()
            val y = (pos[1] + i * lineHeight).f()
            canvas.fillText(para, x, y, textPaint, maxWidth, scale)
        }
    }

    /**
     * 绘制魔法陷阱效果。
     */
    private fun drawSpellOrTrapDesc(canvas: Canvas, info: CardInfo) {
        if (info.i == 0) return
        val desc = info.spellDesc
        val descList = tempSpellDescList
        val scaleList = tempSpellScaleList
        val typeface = cnTf
        val textPaint = descPaint
        val size = SpellDesc.fontSize.f()
        val pos = SpellDesc.position
        val lineHeight = SpellDesc.lineHeight
        val maxWidth = SpellDesc.maxWidth.f()
        val maxLines = SpellDesc.maxLines
        textPaint.color = Color.BLACK
        textPaint.textSize = size
        textPaint.typeface = typeface
        textPaint.isAntiAlias = true
        textPaint.textAlign = Paint.Align.LEFT
        if (tempSpellDesc != desc) {
            descSplit(desc, descList, scaleList, textPaint, maxLines, maxWidth)
            tempSpellDesc = desc
        }
        if (descList.size != scaleList.size) throw RuntimeException("error")
        for (i in descList.indices) {
            val para = descList[i]
            val scale = scaleList[i]
            val x = pos[0].f()
            val y = (pos[1] + i * lineHeight).f()
            canvas.fillText(para, x, y, textPaint, maxWidth, scale)
        }
    }

    /**
     * 对描述分割换行。
     */
    private fun descSplit(
        desc: String, descList: ArrayList<String>, scaleList: ArrayList<Float>,
        paint: Paint, maxLines: Int, maxWidth: Float
    ) {
        // 根据'\n'分割。
        val tempList = desc.split('\n').toMutableList()
        // 超出的段数压到最后一行。
        while (tempList.size > maxLines) {
            tempList[maxLines - 1] += tempList.last()
            tempList.removeLast()
        }
        tempList.removeAll { it.isEmpty() }
        // 每段占几行。
        val tempLinesArray = IntArray(tempList.size)
        // 如果全部一样放缩，能够显示得下的临界Scale。
        var mScale = 1f
        while (mScale > 0) {
            var lines = 0
            for ((i, para) in tempList.withIndex()) {
                val line = ceil(mScale * paint.measureText(para) / maxWidth).roundToInt()
                lines += line
                tempLinesArray[i] = line
            }
            if (lines > maxLines) mScale -= 0.01f else break
        }
        descList.clear()
        scaleList.clear()
        for ((i, para) in tempList.withIndex()) {
            val line = tempLinesArray[i]
            if (line == 1) {// 只给一行显示。
                val w = paint.measureText(para)
                val scaleX = if (w > maxWidth) -1f else 1f
                descList.add(para)
                scaleList.add(scaleX)
            } else if (line > 1) {// 需要分割。
                val w = paint.measureText(para)
                val hasWidth = line * maxWidth
                val scaleX = if (w > hasWidth) (hasWidth / w) else 1f
                val len = para.length
                val oneF = len / line
                var start = 0
                var end = oneF
                val py = paint.textSize * scaleX / 5
                for (li in 0 until line) {
                    // 找分割的位置。
                    var minX = w
                    var pEnd = end
                    while (end < len) {
                        val pE = para.getOrNull(pEnd)
                        if (pE == '。' || pE == '，' || pE == '：') pEnd++
                        val tw = paint.measureText(para, start, pEnd) * scaleX
                        val mX = abs(tw - maxWidth - py)
                        if (minX > mX) {
                            minX = mX
                            end = pEnd
                        } else break
                        if (tw > maxWidth) pEnd-- else pEnd++
                    }
                    if (li == line - 1) {
                        // 把剩余的加入列表。
                        val ts = para.substring(start, len)
                        val ww = paint.measureText(ts)
                        val sww = ww * scaleX
                        val scaleXX = if (sww > maxWidth) -1f else scaleX
                        descList.add(ts)
                        scaleList.add(scaleXX)
                        break
                    } else {
                        val ts = para.substring(start, end)
                        descList.add(ts)
                        scaleList.add(-1f)
                    }
                    start = min(len - 1, end)
                    end = start + oneF
                }
            }
        }
    }

    /**
     * 绘制卡包。
     */
    private fun drawBag(canvas: Canvas, info: CardInfo) {
        val password = "EP19-JP001"
        val typeface = passwordTf
        val textPaint = bagPaint
        val size = CardBag.fontSize.f()
        textPaint.color = Color.BLACK
        textPaint.textSize = size
        textPaint.typeface = typeface
        textPaint.isAntiAlias = true
        textPaint.textAlign = Paint.Align.RIGHT
        val pos = when {
            info.t == 0 && info.m == 1 -> {
                textPaint.textAlign = Paint.Align.LEFT
                CardBag.pendulumPosition
            }
            info.t == 0 && info.m == 2 -> CardBag.linkPosition
            else -> CardBag.position
        }
        val x = pos[0].f()
        val y = pos[1].f()
        canvas.drawText(password, x, y, textPaint)
    }

    /**
     * 绘制卡密。
     */
    private fun drawPassword(canvas: Canvas, info: CardInfo) {
        val password = "12345678"
        val typeface = passwordTf
        val textPaint = passwordPaint
        val size = Password.fontSize.f()
        textPaint.color = Color.BLACK
        textPaint.textSize = size
        textPaint.typeface = typeface
        textPaint.isAntiAlias = true
        textPaint.textAlign = Paint.Align.LEFT
        val pos = Password.position
        val x = pos[0].f()
        val y = pos[1].f()
        canvas.drawText(password, x, y, textPaint)
    }

    /**
     * 绘制版权。
     */
    private fun drawCopyright(canvas: Canvas, info: CardInfo) {
        val copyright = "ⓒスタジオ·ダイス /集英社·テレビ東京·KONAMI"
        val typeface = copyrightTf
        val textPaint = copyrightPaint
        val size = Copyright.fontSize.f()
        textPaint.color = Color.BLACK
        textPaint.textSize = size
        textPaint.typeface = typeface
        textPaint.isAntiAlias = true
        textPaint.textAlign = Paint.Align.RIGHT
        val pos = Copyright.position
        val x = pos[0].f()
        val y = pos[1].f()
        canvas.drawText(copyright, x, y, textPaint)
    }

    /**
     * 绘制防伪标记。
     */
    private fun drawHolo(canvas: Canvas, info: CardInfo) {
        val pos = Holo.position
        val size = Holo.size
        val holoDrawable = holo
        val left = pos[0].d()
        val top = pos[1].d()
        val right = (pos[0] + size[0]).d()
        val bottom = (pos[1] + size[1]).d()
        holoDrawable.setBounds(left, top, right, bottom)
        holoDrawable.draw(canvas)
    }


    /**
     * 绘制文字在限制区域内。
     */
    private fun Canvas.fillText(
        para: String, x: Float, y: Float,
        paint: Paint, width: Float, scX: Float = 1f
    ) {
        val canvas = this
        val w = paint.measureText(para)
        val h = width / w
        val scale = if (scX < h && scX > 0f) scX else h
        canvas.save()
        canvas.scale(scale, 1f, x, y)
        canvas.drawText(para, x, y, paint)
        canvas.restore()
    }

}
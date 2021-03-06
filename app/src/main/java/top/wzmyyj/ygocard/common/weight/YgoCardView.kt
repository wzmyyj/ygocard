package top.wzmyyj.ygocard.common.weight

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import top.wzmyyj.ygocard.common.weight.config.Standard.ATK
import top.wzmyyj.ygocard.common.weight.config.Standard.Arrows
import top.wzmyyj.ygocard.common.weight.config.Standard.Attribute
import top.wzmyyj.ygocard.common.weight.config.Standard.CardBag
import top.wzmyyj.ygocard.common.weight.config.Standard.Copyright
import top.wzmyyj.ygocard.common.weight.config.Standard.DEF
import top.wzmyyj.ygocard.common.weight.config.Standard.Holo
import top.wzmyyj.ygocard.common.weight.config.Standard.LbNumber
import top.wzmyyj.ygocard.common.weight.config.Standard.Line
import top.wzmyyj.ygocard.common.weight.config.Standard.Mask
import top.wzmyyj.ygocard.common.weight.config.Standard.MonsterDesc
import top.wzmyyj.ygocard.common.weight.config.Standard.MonsterLbDesc
import top.wzmyyj.ygocard.common.weight.config.Standard.MonsterRace
import top.wzmyyj.ygocard.common.weight.config.Standard.Name
import top.wzmyyj.ygocard.common.weight.config.Standard.Password
import top.wzmyyj.ygocard.common.weight.config.Standard.Pic
import top.wzmyyj.ygocard.common.weight.config.Standard.SpellDesc
import top.wzmyyj.ygocard.common.weight.config.Standard.SpellType
import top.wzmyyj.ygocard.common.weight.config.Standard.Star
import top.wzmyyj.ygocard.common.weight.config.Standard.moldSize
import top.wzmyyj.ygocard.common.weight.data.CardInfo
import top.wzmyyj.ygocard.common.weight.manger.CardDrawableManager
import top.wzmyyj.ygocard.common.weight.manger.CardFontManager
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

    private val cdManager by lazy { CardDrawableManager(context) }
    private val cfManager by lazy { CardFontManager(context) }

    private val namePaint = Paint()
    private val racePaint = Paint()
    private val atkDefPaint = Paint()
    private val lpNumberPaint = Paint()
    private val spellPaint = Paint()
    private val descPaint = Paint()

    private val passwordPaint = Paint()
    private val bagPaint = Paint()
    private val copyrightPaint = Paint()

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
     * ??????????????????
     */
    private fun drawMonster(canvas: Canvas, info: CardInfo) {
        if (!info.isMonster()) return
        drawFrame(canvas, info)
        drawName(canvas, info)
        drawAttribute(canvas, info)
        drawImage(canvas, info)
        drawLbMask(canvas, info)
        drawLevel(canvas, info)
        drawRank(canvas, info)
        drawLinkArrow(canvas, info)
        drawLbMark(canvas, info)
        drawLbDesc(canvas, info)
        drawMonsterRace(canvas, info)
        drawMonsterDesc(canvas, info)
        drawATKAndDEF(canvas, info)
    }

    /**
     * ??????????????????????????????
     */
    private fun drawSpellOrTrap(canvas: Canvas, info: CardInfo) {
        if (info.isMonster()) return
        drawFrame(canvas, info)
        drawName(canvas, info)
        drawAttribute(canvas, info)
        drawSpellOrTrapWithIcon(canvas, info)
        drawImage(canvas, info)
        drawSpellOrTrapDesc(canvas, info)
    }

    /**
     * ???????????????
     */
    private fun drawFrame(canvas: Canvas, info: CardInfo) {
        val frameDrawable = cdManager.getFrame(info)
        val right = moldSize[0].d()
        val bottom = moldSize[1].d()
        frameDrawable.setBounds(0, 0, right, bottom)
        frameDrawable.draw(canvas)
    }

    /**
     * ???????????????
     */
    private fun drawName(canvas: Canvas, info: CardInfo) {
        val name = info.name
        if (name.isEmpty()) return
        val typeface = cfManager.getName(info)
        val textPaint = namePaint
        val pos = Name.position
        val size = Name.fontSize.f()
        val maxW = Name.maxWidth.f()
        textPaint.color = info.nameColor
        textPaint.textSize = size
        textPaint.typeface = typeface
        textPaint.style = Paint.Style.FILL
        textPaint.isAntiAlias = true
        val x = pos[0].f()
        val y = pos[1].f()
        canvas.fillText(name, x, y, textPaint, maxW)
        if (info.isSynchro() && info.nameColor != Color.BLACK) {
            textPaint.strokeWidth = 1.f()
            textPaint.color = Color.GRAY
            textPaint.style = Paint.Style.STROKE
            canvas.fillText(name, x, y, textPaint, maxW)
        }
    }

    /**
     * ???????????????
     */
    private fun drawImage(canvas: Canvas, info: CardInfo) {
        val imageDrawable = cdManager.getDefaultImage() as BitmapDrawable
        val pos: IntArray
        val size: IntArray
        if (info.isMonster() && info.isPendulum()) {
            pos = Pic.position_lb
            size = Pic.size_lb
        } else {
            pos = Pic.position
            size = Pic.size
        }
        val bitmap = imageDrawable.bitmap
        val isFlat = 1f * bitmap.width / bitmap.height > Pic.flatRatio
        val height = if (isFlat) size[1] else size[0]
        val left = pos[0].d()
        val top = pos[1].d()
        val right = (pos[0] + size[0]).d()
        val bottom = (pos[1] + height).d()
        imageDrawable.setBounds(left, top, right, bottom)
        imageDrawable.draw(canvas)
    }

    /**
     * ?????????????????????
     */
    private fun drawLbMask(canvas: Canvas, info: CardInfo) {
        if (!info.isPendulum()) return
        val pos = Mask.position
        val size = Mask.size
        val maskDrawable = cdManager.getLbMask()
        val left = pos[0].d()
        val top = pos[1].d()
        val right = (pos[0] + size[0]).d()
        val bottom = (pos[1] + size[1]).d()
        maskDrawable.setBounds(left, top, right, bottom)
        maskDrawable.draw(canvas)
    }

    /**
     * ???????????????
     */
    private fun drawAttribute(canvas: Canvas, info: CardInfo) {
        val attributeDrawable = cdManager.getAttribute(info)
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
     * ???????????????
     */
    private fun drawLevel(canvas: Canvas, info: CardInfo) {
        if (info.isLink() || info.isXYZ()) return
        val lv = info.level
        if (lv > 13 || lv <= 0) return
        val levelDrawable = cdManager.getLevel()
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
     * ???????????????
     */
    private fun drawRank(canvas: Canvas, info: CardInfo) {
        if (!info.isXYZ()) return
        val rk = info.rank
        if (rk > 13 || rk <= 0) return
        val rankDrawable = cdManager.getRank()
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
     * ?????????????????????
     */
    private fun drawLinkArrow(canvas: Canvas, info: CardInfo) {
        if (!info.isLink()) return
        val arr = arrayOf(0, 1, 1, 0, 0, 0, 1, 1)
        val linkArrows = cdManager.getLinkArrows()
        for ((i, v) in arr.withIndex()) {
            val pos: IntArray
            val size: IntArray
            val arrowDrawable: Drawable
            if (i % 2 == 0) {
                pos = Arrows.arrow1_position
                size = Arrows.arrow1_size
                arrowDrawable = if (v == 0) linkArrows[0] else linkArrows[1]
            } else {
                pos = Arrows.arrow2_position
                size = Arrows.arrow2_size
                arrowDrawable = if (v == 0) linkArrows[2] else linkArrows[3]
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
     * ?????????????????????
     */
    private fun drawLbMark(canvas: Canvas, info: CardInfo) {
        if (!info.isPendulum()) return
        val lbMark = info.lbMark
        if (lbMark > 13 || lbMark <= 0) return
        val number = lbMark.toString()
        val typeface = cfManager.getNumber()
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
     * ????????????????????????
     */
    private fun drawMonsterRace(canvas: Canvas, info: CardInfo) {
        val race = info.getRace()
        if (race.isEmpty()) return
        val typeface = cfManager.getDesc(info)
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
     * ???????????????????????????
     */
    private fun drawATKAndDEF(canvas: Canvas, info: CardInfo) {
        val typeface = cfManager.getNumber()
        val textPaint = atkDefPaint
        textPaint.color = Color.BLACK
        textPaint.typeface = typeface
        textPaint.isAntiAlias = true
        textPaint.textAlign = Paint.Align.RIGHT
        // ?????? ATK/
        val atkLabel = ATK.label
        val atkLabelPos = ATK.labelPosition
        val atkSize = ATK.fontSize.f()
        textPaint.textSize = atkSize
        val x1 = atkLabelPos[0].f()
        val y1 = atkLabelPos[1].f()
        canvas.drawText(atkLabel, x1, y1, textPaint)
        // ?????? atk??????
        val atkNumber = "3000"
        val atkNumberPos = ATK.position
        val atkNumberSize = ATK.fontSize.f()
        val atkNumberMaxW = ATK.maxWidth.f()
        textPaint.textSize = atkNumberSize
        val x2 = atkNumberPos[0].f()
        val y2 = atkNumberPos[1].f()
        canvas.drawText(atkNumber, x2, y2, textPaint)
        canvas.fillText(atkNumber, x2, y2, textPaint, atkNumberMaxW)
        if (!info.isLink()) {
            // ?????? DEF/
            val defLabel = DEF.label
            val defLabelPos = DEF.labelPosition
            val defSize = DEF.fontSize.f()
            textPaint.textSize = defSize
            val x3 = defLabelPos[0].f()
            val y3 = defLabelPos[1].f()
            canvas.drawText(defLabel, x3, y3, textPaint)
            // ?????? def??????
            val defNumber = "3000"
            val defNumberPos = DEF.position
            val defNumberMaxW = DEF.maxWidth.f()
            val x4 = defNumberPos[0].f()
            val y4 = defNumberPos[1].f()
            canvas.fillText(defNumber, x4, y4, textPaint, defNumberMaxW)
        } else {
            // ?????? LINK-
            val linkTypeface = cfManager.getLink()
            val linkLabel = DEF.linkLabel
            val linkLabelPos = DEF.linkLabelPosition
            val linkSize = DEF.linkFontSize.f()
            textPaint.typeface = linkTypeface
            textPaint.textSize = linkSize
            val x5 = linkLabelPos[0].f()
            val y5 = linkLabelPos[1].f()
            canvas.drawText(linkLabel, x5, y5, textPaint)
            // ?????? link??????
            val linkNumber = "4"
            textPaint.textAlign = Paint.Align.LEFT
            canvas.drawText(linkNumber, x5, y5, textPaint)
        }
        // ?????? ??????
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
     * ??????????????????????????????
     */
    private fun drawSpellOrTrapWithIcon(canvas: Canvas, info: CardInfo) {
        val typeDesc = "?????????"
        val typeface = cfManager.getDesc(info)
        val textPaint = spellPaint
        val size = SpellType.fontSize.f()
        textPaint.color = Color.BLACK
        textPaint.textSize = size
        textPaint.typeface = typeface
        textPaint.isAntiAlias = true
        textPaint.textAlign = Paint.Align.RIGHT
        if (info.hasSpellIcon()) {
            val iconPos = SpellType.iconPosition
            val iconSize = SpellType.iconSize
            val iconDrawable = cdManager.getSpellIcon(info)
            val left = iconPos[0].d()
            val top = iconPos[1].d()
            val right = (iconPos[0] + iconSize[0]).d()
            val bottom = (iconPos[1] + iconSize[1]).d()
            iconDrawable.setBounds(left, top, right, bottom)
            iconDrawable.draw(canvas)
            val typeEnd = "???"
            val textPos = SpellType.position
            val x1 = textPos[0].f()
            val y1 = textPos[1].f()
            canvas.drawText(typeEnd, x1, y1, textPaint)
            val type = "???$typeDesc"
            val x2 = iconPos[0].f()
            val y2 = textPos[1].f()
            canvas.drawText(type, x2, y2, textPaint)
        } else {
            val type = "???$typeDesc???"
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
     * ?????????????????????
     */
    private fun drawLbDesc(canvas: Canvas, info: CardInfo) {
        if (!info.isPendulum()) return
        val desc = info.lbDesc
        if (desc.isEmpty()) return
        val descList = tempLbDescList
        val scaleList = tempLbScaleList
        val typeface = cfManager.getDesc(info)
        val textPaint = descPaint
        val size = MonsterLbDesc.fontSize.f()
        val pos = MonsterLbDesc.position
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
     * ???????????????
     */
    private fun drawMonsterDesc(canvas: Canvas, info: CardInfo) {
        val desc = info.desc
        if (desc.isEmpty()) return
        val descList = tempMonsterDescList
        val scaleList = tempMonsterScaleList
        val typeface = cfManager.getDesc(info)
        val textPaint = descPaint
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
            tempMonsterDesc = desc
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
     * ???????????????????????????
     */
    private fun drawSpellOrTrapDesc(canvas: Canvas, info: CardInfo) {
        if (info.isMonster()) return
        val desc = info.desc
        if (desc.isEmpty()) return
        val descList = tempSpellDescList
        val scaleList = tempSpellScaleList
        val typeface = cfManager.getDesc(info)
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
     * ????????????????????????
     */
    private fun descSplit(
        desc: String, descList: ArrayList<String>, scaleList: ArrayList<Float>,
        paint: Paint, maxLines: Int, maxWidth: Float
    ) {
        // ??????'\n'?????????
        val tempList = desc.split('\n').toMutableList()
        // ????????????????????????????????????
        while (tempList.size > maxLines) {
            tempList[maxLines - 1] += tempList.last()
            tempList.removeLast()
        }
        tempList.removeAll { it.isEmpty() }
        // ??????????????????
        val tempLinesArray = IntArray(tempList.size)
        // ??????????????????????????????????????????????????????Scale???
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
            if (line == 1) {// ?????????????????????
                val w = paint.measureText(para)
                val scaleX = if (w > maxWidth) -1f else 1f
                descList.add(para)
                scaleList.add(scaleX)
            } else if (line > 1) {// ???????????????
                val w = paint.measureText(para)
                val hasWidth = line * maxWidth
                val scaleX = if (w > hasWidth) (hasWidth / w) else 1f
                val len = para.length
                val oneF = len / line
                var start = 0
                var end = oneF
                val py = paint.textSize * scaleX / 5
                for (li in 0 until line) {
                    // ?????????????????????
                    var minX = w
                    var pEnd = end
                    while (end < len) {
                        val pE = para.getOrNull(pEnd)
                        if (pE == '???' || pE == '???' || pE == '???') pEnd++
                        val tw = paint.measureText(para, start, pEnd) * scaleX
                        val mX = abs(tw - maxWidth - py)
                        if (minX > mX) {
                            minX = mX
                            end = pEnd
                        } else break
                        if (tw > maxWidth) pEnd-- else pEnd++
                    }
                    if (li == line - 1) {
                        // ???????????????????????????
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
     * ???????????????
     */
    private fun drawBag(canvas: Canvas, info: CardInfo) {
        val bag = info.bag
        if (bag.isEmpty()) return
        val typeface = cfManager.getPassword()
        val textPaint = bagPaint
        val size = CardBag.fontSize.f()
        textPaint.color = Color.BLACK
        textPaint.textSize = size
        textPaint.typeface = typeface
        textPaint.isAntiAlias = true
        textPaint.textAlign = Paint.Align.RIGHT
        val pos = when {
            info.isMonster() && info.isPendulum() -> {
                textPaint.textAlign = Paint.Align.LEFT
                CardBag.pendulumPosition
            }
            info.isMonster() && info.isLink() -> CardBag.linkPosition
            else -> CardBag.position
        }
        val x = pos[0].f()
        val y = pos[1].f()
        canvas.drawText(bag, x, y, textPaint)
    }

    /**
     * ???????????????
     */
    private fun drawPassword(canvas: Canvas, info: CardInfo) {
        val password = info.password
        if (password.isEmpty()) return
        val typeface = cfManager.getPassword()
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
     * ???????????????
     */
    private fun drawCopyright(canvas: Canvas, info: CardInfo) {
        val copyright = info.copyright
        if (copyright.isEmpty()) return
        val typeface = cfManager.getCopyright()
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
     * ?????????????????????
     */
    private fun drawHolo(canvas: Canvas, info: CardInfo) {
        if (!info.showHolo) return
        val pos = Holo.position
        val size = Holo.size
        val holoDrawable = cdManager.getHolo()
        val left = pos[0].d()
        val top = pos[1].d()
        val right = (pos[0] + size[0]).d()
        val bottom = (pos[1] + size[1]).d()
        holoDrawable.setBounds(left, top, right, bottom)
        holoDrawable.draw(canvas)
    }

    /**
     * ?????????????????????????????????
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
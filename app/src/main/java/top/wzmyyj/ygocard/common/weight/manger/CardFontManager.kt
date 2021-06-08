package top.wzmyyj.ygocard.common.weight.manger

import android.content.Context
import android.graphics.Typeface
import top.wzmyyj.ygocard.common.weight.data.CardInfo
import top.wzmyyj.ygocard.common.weight.data.CardInfo.Language.EN
import top.wzmyyj.ygocard.common.weight.data.CardInfo.Language.JP
import top.wzmyyj.ygocard.common.weight.data.CardInfo.Language.KR
import top.wzmyyj.ygocard.common.weight.data.CardInfo.Language.SC
import top.wzmyyj.ygocard.common.weight.data.CardInfo.Language.TC

/**
 * Created on 2021/06/04.
 *
 * @author feling
 * @version 1.0.0
 * @since 1.0.0
 */
class CardFontManager(private val context: Context) {

    private val assets by lazy { context.resources.assets }
    private val _tc by lazy { getTypeface("fonts/cn.ttf") }
    private val _en by lazy { getTypeface("fonts/en.ttf") }
    private val _enName by lazy { getTypeface("fonts/en_name.ttf") }
    private val _sc by lazy { getTypeface("fonts/sc.ttf") }
    private val _jp by lazy { getTypeface("fonts/jp.ttf") }
    private val _kr by lazy { getTypeface("fonts/kr.ttf") }
    private val _number by lazy { getTypeface("fonts/number.ttf") }
    private val _link by lazy { getTypeface("fonts/link.ttf") }

    private val _pw by lazy { getTypeface("fonts/password.ttf") }
    private val _cr by lazy { getTypeface("fonts/copyright.ttf") }


    private fun getTypeface(path: String): Typeface {
        return Typeface.createFromAsset(assets, path)
    }

    /**
     * 获取名称字体。
     */
    fun getName(info: CardInfo): Typeface {
        return when (info.lang) {
            SC -> _sc
            TC -> _tc
            JP -> _jp
            EN -> _enName
            KR -> _kr
            else -> _tc
        }
    }

    /**
     * 获取描述字体。
     */
    fun getDesc(info: CardInfo): Typeface {
        return when (info.lang) {
            SC -> _sc
            TC -> _tc
            JP -> _jp
            EN -> _en
            KR -> _kr
            else -> _tc
        }
    }

    /**
     * 获取版权字体。
     */
    fun getCopyright(): Typeface {
        return _cr
    }

    /**
     * 获取卡密字体。
     */
    fun getPassword(): Typeface {
        return _pw
    }

    /**
     * 获取连接字体。
     */
    fun getLink(): Typeface {
        return _link
    }

    /**
     * 获取数字字体。
     */
    fun getNumber(): Typeface {
        return _number
    }
}
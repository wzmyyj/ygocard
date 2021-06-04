package top.wzmyyj.ygocard.card.repository

import retrofit2.Call
import top.wzmyyj.ygocard.card.api.CardApi
import top.wzmyyj.ygocard.card.bean.CardInfoBean
import top.wzmyyj.ygocard.card.bean.CardSearchBean
import top.wzmyyj.ygocard.card.bean.HttpBean
import top.wzmyyj.ygocard.common.net.RetrofitHelper

/**
 * Created on 2021/06/04.
 *
 * @author feling
 * @version 1.0.0
 * @since 1.0.0
 */
object CardRepository {

    // 域名
    private const val BASE_URL = "https://tools.kooriookami.top/"

    // 字段
    private const val LANG = "lang"
    private const val NAME = "name"

    private val api by lazy { RetrofitHelper.get(BASE_URL).create(CardApi::class.java) }

    /**
     * 获取卡片详情。
     *
     * @param id 卡密。
     * @param lang 语言。
     */
    fun getCardInfo(id: Long, lang: String): Call<HttpBean<CardInfoBean>> {
        val params = HashMap<String, Any>()
        params[LANG] = lang
        return api.getCardInfo(id, params)
    }

    /**
     * 搜索。通过名称。
     *
     * @param name 名称。
     * @param lang 语言。
     */
    fun getCardsBySearch(name: String, lang: String): Call<HttpBean<List<CardSearchBean>>> {
        val params = HashMap<String, Any>()
        params[NAME] = name
        params[LANG] = lang
        return api.getCardsBySearch(params)
    }
}
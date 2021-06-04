package top.wzmyyj.ygocard.card.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap
import top.wzmyyj.ygocard.card.bean.CardInfoBean
import top.wzmyyj.ygocard.card.bean.CardSearchBean
import top.wzmyyj.ygocard.card.bean.HttpBean

/**
 * Created on 2021/06/04.
 *
 * @author feling
 * @version 1.0.0
 * @since 1.0.0
 */
interface CardApi {

    /**
     * 获取卡片详情。
     */
    @GET("/api/yugioh/card/{id}")
    fun getCardInfo(@Path("id") id: Long, @QueryMap params: HashMap<String, Any>)
            : Call<HttpBean<CardInfoBean>>

    /**
     * 搜索。
     */
    @GET("/api/yugioh/card")
    fun getCardsBySearch(@QueryMap params: HashMap<String, Any>): Call<HttpBean<List<CardSearchBean>>>
}
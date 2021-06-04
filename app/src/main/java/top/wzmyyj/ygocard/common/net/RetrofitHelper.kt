package top.wzmyyj.ygocard.common.net

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import top.wzmyyj.ygocard.common.tools.LogUtil
import java.util.concurrent.TimeUnit

/**
 * Created on 2021/06/04.
 *
 * @author feling
 * @version 1.0.0
 * @since 1.0.0
 */
object RetrofitHelper {

    /**
     * 获取 Retrofit。
     *
     * @param baseUrl 域名。
     */
    fun get(baseUrl: String): Retrofit {
        return getRetrofitBuilder().baseUrl(baseUrl).build()
    }

    /**
     * 获取 Retrofit。
     */
    fun get(): Retrofit {
        return getRetrofitBuilder().build()
    }

    /**
     * 获取 Retrofit.Builder 对象。
     */
    private fun getRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(getClientBuilder().build())
    }

    /**
     * 获取 OkHttpClient.Builder 对象。
     */
    private fun getClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient().newBuilder()
            .addInterceptor(logInterceptor) // 拦截日志
            .connectTimeout(10, TimeUnit.SECONDS) //设置超时时间
            .readTimeout(10, TimeUnit.SECONDS) //设置读取超时时间
            .writeTimeout(10, TimeUnit.SECONDS) //设置写入超时时间
    }

    /**
     * 日志拦截器。
     */
    private val logInterceptor = HttpLoggingInterceptor(LogUtil::j)
        .setLevel(HttpLoggingInterceptor.Level.BODY)
}
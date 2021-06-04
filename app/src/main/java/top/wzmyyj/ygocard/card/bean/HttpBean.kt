package top.wzmyyj.ygocard.card.bean

/**
 * Created on 2021/06/04.
 *
 * @author feling
 * @version 1.0.0
 * @since 1.0.0
 */
class HttpBean<T> {
    /**
     * 状态码，200正常。
     */
    var status: Int = 0

    /**
     * 消息。
     */
    var message: String? = null

    /**
     * 数据。
     */
    var data: T? = null
}
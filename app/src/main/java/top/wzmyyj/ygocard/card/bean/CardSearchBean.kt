package top.wzmyyj.ygocard.card.bean

/**
 * Created on 2021/06/04.
 *
 * @author feling
 * @version 1.0.0
 * @since 1.0.0
 */
class CardSearchBean {
    /**
     * 卡密。如果存在异画卡的时候，可能不准。
     */
    var id: Long = 0L

    /**
     * 名称。
     */
    var name: String? = null
}
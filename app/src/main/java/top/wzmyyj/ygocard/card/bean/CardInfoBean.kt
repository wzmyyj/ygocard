package top.wzmyyj.ygocard.card.bean

/**
 * Created on 2021/06/04.
 *
 * @author feling
 * @version 1.0.0
 * @since 1.0.0
 */
class CardInfoBean {

    /**
     * 卡密。如果存在异画卡的时候，可能不准。
     */
    var id: Long = 0L

    /**
     * 名称。
     */
    var name: String? = null

    /**
     * 描述。
     */
    var desc: String? = null

    /**
     * 类型。
     */
    var type: Int = 0

    /**
     * 攻击力/防御力。
     */
    var atk: Int = 0
    var def: Int = 0

    /**
     * 等级。
     */
    var level: Int = 0

    /**
     * 种族。
     */
    var race: Int = 0

    /**
     * 属性。
     */
    var attribute: Int = 0

    /**
     * 卡包。
     */
    var setid: String? = null

}
package top.wzmyyj.ygocard.common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import top.wzmyyj.ygocard.R

/**
 * Created on 2021/06/09.
 *
 * @author feling
 * @version 1.0.0
 * @since 1.0.0
 */
open class CBaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initData()
    }

    protected open fun initView() {
        val f = getFragment() ?: return
        setContentView(R.layout.activity_base)
        addFragment(R.id.fl_container, f)
    }

    protected open fun initData() {

    }

    protected open fun getFragment(): Fragment? {
        return null
    }
}
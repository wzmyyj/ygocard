package top.wzmyyj.ygocard.common.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.DefaultLifecycleObserver

/**
 * Created on 2019/09/30.
 *
 * @author feling
 * @version 1.0.0
 * @since 1.0.0
 */
open class CBaseViewModel(application: Application) :
    AndroidViewModel(application), DefaultLifecycleObserver {

}
package top.wzmyyj.ygocard.common.base

import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import top.wzmyyj.ygocard.common.utils.FragmentUtil


/**
 * Created on 2020/10/10.
 *
 * @author feling
 * @version 1.0.0
 * @since 1.0.0
 */
/**
 * @see FragmentUtil.addFragment
 */
@MainThread
fun FragmentManager.addFragment(
    containerId: Int, fragment: Fragment,
    isAddToBackStack: Boolean = false, tag: String? = null
) {
    FragmentUtil.addFragment(this, containerId, fragment, isAddToBackStack, tag)
}

/**
 * @see FragmentUtil.replaceFragment
 */
@MainThread
fun FragmentManager.replaceFragment(
    containerId: Int, fragment: Fragment,
    isAddToBackStack: Boolean = false, tag: String? = null
) {
    FragmentUtil.replaceFragment(this, containerId, fragment, isAddToBackStack, tag)
}

/**
 * @see FragmentUtil.addFragment
 */
@MainThread
fun FragmentActivity.addFragment(
    containerId: Int, fragment: Fragment,
    isAddToBackStack: Boolean = false, tag: String? = null
) {
    supportFragmentManager.addFragment(containerId, fragment, isAddToBackStack, tag)
}

/**
 * @see FragmentUtil.replaceFragment
 */
@MainThread
fun FragmentActivity.replaceFragment(
    containerId: Int, fragment: Fragment,
    isAddToBackStack: Boolean = false, tag: String? = null
) {
    supportFragmentManager.addFragment(containerId, fragment, isAddToBackStack, tag)
}

/**
 * @see FragmentUtil.addFragment
 */
@MainThread
fun Fragment.addFragment(
    containerId: Int, fragment: Fragment,
    isAddToBackStack: Boolean = false, tag: String? = null
) {
    childFragmentManager.addFragment(containerId, fragment, isAddToBackStack, tag)
}

/**
 * @see FragmentUtil.replaceFragment
 */
@MainThread
fun Fragment.replaceFragment(
    containerId: Int, fragment: Fragment,
    isAddToBackStack: Boolean = false, tag: String? = null
) {
    childFragmentManager.replaceFragment(containerId, fragment, isAddToBackStack, tag)
}

/**
 * @see ViewModelProvider.get
 */
@MainThread
fun <T : CBaseViewModel> FragmentActivity.getViewModel(modelClass: Class<T>): T {
    return ViewModelProvider(this).get(modelClass).addToLifecycleOwner(this)
}

/**
 * @see ViewModelProvider.get
 */
@MainThread
fun <T : CBaseViewModel> Fragment.getViewModel(modelClass: Class<T>): T {
    return ViewModelProvider(this).get(modelClass).addToLifecycleOwner(this)
}

/**
 * @see ViewModelProvider.get
 */
@MainThread
fun <T : CBaseViewModel> Fragment.getViewModelOfParent(modelClass: Class<T>): T? {
    val owner = parentFragment ?: return null
    return ViewModelProvider(owner).get(modelClass).addToLifecycleOwner(this)
}

/**
 * @see ViewModelProvider.get
 */
@MainThread
fun <T : CBaseViewModel> Fragment.getViewModelOfActivity(modelClass: Class<T>): T? {
    val owner = activity ?: return null
    return ViewModelProvider(owner).get(modelClass).addToLifecycleOwner(this)
}

/**
 * @see LifecycleOwner.getLifecycle
 */
@MainThread
fun <T : CBaseViewModel> T.addToLifecycleOwner(owner: LifecycleOwner): T {
    owner.lifecycle.addObserver(this)
    return this
}

package cu.infocap.gobmun.base

import android.content.Context
import android.support.v4.app.Fragment
import dagger.android.support.AndroidSupportInjection


open class BaseFragment: Fragment() {

    protected var listener: OnItemClickListener? = null

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        if (context is OnItemClickListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnItemClickListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}
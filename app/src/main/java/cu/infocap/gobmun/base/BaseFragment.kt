package cu.infocap.gobmun.base

import android.content.Context
import dagger.android.support.DaggerFragment


open class BaseFragment: DaggerFragment() {

    protected var listener: OnItemClickListener? = null

    override fun onAttach(context: Context) {
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
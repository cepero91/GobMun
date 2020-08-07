package cu.infocap.gobmun.ui.aboutus.handler

import cu.infocap.gobmun.ui.aboutus.item.AboutUsServiceModel

interface AboutUsServiceListener {
    fun onServiceClick(pos: Int, data: AboutUsServiceModel)
}
package cu.infocap.gobmun.ui.welcome.item

import ir.mirrajabi.searchdialog.core.Searchable

class MunSearchModel(val id: String, private var title: String) : Searchable {
    override fun getTitle(): String {
        return title
    }
}
package cu.infocap.gobmun.ui.gservice.item

import cu.infocap.gobmun.base.BaseItem

data class GServiceItem(var id: String, var title: String, var resource: Int) : BaseItem() {
    override fun toString(): String {
        return "$id-$title"
    }
}
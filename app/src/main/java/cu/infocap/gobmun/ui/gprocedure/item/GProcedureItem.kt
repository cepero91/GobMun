package cu.infocap.gobmun.ui.gprocedure.item

import cu.infocap.gobmun.base.BaseItem

data class GProcedureItem(var id: String, var title: String, var resource: Int) : BaseItem(){
    override fun toString(): String {
        return "$id->$title"
    }
}
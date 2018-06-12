package com.arkhipov.ayur.rbplants.data.model

import android.graphics.drawable.Drawable
import android.net.Uri
import com.pchmn.materialchips.model.ChipInterface

data class Rod(
    var id_rod: String = "",
    var name_ru: String = "",
    var name_latin: String = ""
): ChipInterface {
    override fun getInfo() = name_latin
    override fun getAvatarDrawable() = null
    override fun getLabel() = name_ru
    override fun getId() = id_rod
    override fun getAvatarUri() = null
}

data class PriznakiRoda(
    var id_priznak_rod: String = "",
    var name: String = ""
): ChipInterface {
    override fun getInfo() = null
    override fun getAvatarDrawable() = null
    override fun getLabel() = name
    override fun getId() = id_priznak_rod
    override fun getAvatarUri() = null
}


data class OpredelenieRoda(
    var id_opredelenie_rod: String = "",
    var id_rod: String = "",
    var id_priznak_rod: String = ""
)

data class GroupRodovihPriznakov(
    var id_group_rod_priznak: String = "",
    var id_priznak_rod: String = "",
    var id_name_rod_group: String = ""
)

data class NameRodovihGroup(
    var id_name_rod_group: String = "",
    var name: String = ""
): ChipInterface {
    override fun getInfo() = null
    override fun getAvatarDrawable() = null
    override fun getLabel() = name
    override fun getId() = id_name_rod_group
    override fun getAvatarUri() = null
}
package com.arkhipov.ayur.rbplants.data

import com.pchmn.materialchips.model.ChipInterface

data class ChipRod(
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

data class ChipPriznakiRoda(
    var id_priznak_rod: String = "",
    var name: String = ""
): ChipInterface {
    override fun getInfo() = null
    override fun getAvatarDrawable() = null
    override fun getLabel() = name
    override fun getId() = id_priznak_rod
    override fun getAvatarUri() = null
}

data class ChipNameRodovihGroup(
    var id_name_rod_group: String = "",
    var name: String = ""
): ChipInterface {
    override fun getInfo() = null
    override fun getAvatarDrawable() = null
    override fun getLabel() = name
    override fun getId() = id_name_rod_group
    override fun getAvatarUri() = null
}

data class ChipVid(
    var id_vid: String = "",
    var id_rod: String = "",
    var id_user: String = "",
    var name_ru: String = "",
    var name_latin: String = "",
    var description: String = "",
    var status: String = ""
): ChipInterface {
    override fun getInfo() = name_latin
    override fun getAvatarDrawable() = null
    override fun getLabel() = name_ru
    override fun getId() = id_rod
    override fun getAvatarUri() = null
}

data class ChipPriznakiVida(
    var id_priznak_vid: String = "",
    var id_user: String = "",
    var id_rod: String = "",
    var name: String = "",
    var status: String = ""
): ChipInterface {
    override fun getInfo() = null
    override fun getAvatarDrawable() = null
    override fun getLabel() = name
    override fun getId() = id_rod
    override fun getAvatarUri() = null
}

data class ChipNameVidovihGroup(
    var id_name_vid_group: String = "",
    var id_user: String = "",
    var name: String = ""
): ChipInterface {
    override fun getInfo() = ""
    override fun getAvatarDrawable() = null
    override fun getLabel() = name
    override fun getId() = id_name_vid_group
    override fun getAvatarUri() = null
}
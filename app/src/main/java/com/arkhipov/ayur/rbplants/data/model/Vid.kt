package com.arkhipov.ayur.rbplants.data.model

import com.pchmn.materialchips.model.ChipInterface

data class Vid(
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
    override fun getId() = id_vid
    override fun getAvatarUri() = null
}

data class PriznakiVida(
    var id_priznak_vid: String = "",
    var id_user: String = "",
    var id_rod: String = "",
    var name: String = "",
    var status: String = ""
): ChipInterface {
    override fun getInfo() = null
    override fun getAvatarDrawable() = null
    override fun getLabel() = name
    override fun getId() = id_priznak_vid
    override fun getAvatarUri() = null
}

data class OpredelenieVida(
    var id_opredelenie_vid: String = "",
    var id_vid: String = "",
    var id_priznak_vid: String = "",
    var id_user: String = "",
    var status: String = ""
)

data class GroupVidovihPriznakov(
    var id_group_vid_priznak: String = "",
    var id_priznak_vid: String = "",
    var id_name_vid_group: String = "",
    var id_user: String = "",
    var status: String = ""
)

data class NameVidovihGroup(
    var id_name_vid_group: String = "",
    var id_user: String = "",
    var name: String = ""
): ChipInterface {
    override fun getInfo() = null
    override fun getAvatarDrawable() = null
    override fun getLabel() = name
    override fun getId() = id_name_vid_group
    override fun getAvatarUri() = null
}
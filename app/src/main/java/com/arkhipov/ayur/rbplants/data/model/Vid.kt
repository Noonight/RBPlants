package com.arkhipov.ayur.rbplants.data.model

data class Vid(
    var id_vid: String = "",
    var id_rod: String = "",
    var id_user: String = "",
    var name_ru: String = "",
    var name_latin: String = "",
    var description: String = "",
    var status: String = ""
)

data class PriznakiVida(
    var id_priznak_vid: String = "",
    var id_user: String = "",
    var id_rod: String = "",
    var name: String = "",
    var status: String = ""
)

data class OpredelenieVida(
    var id_opredelenie_vid: String = "",
    var id_vid: String = "",
    var id_priznak_vid: String = "",
    var id_user: String = "",
    var status: String = ""
)

data class GroupVidovihProznakov(
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
)
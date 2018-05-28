package com.arkhipov.ayur.rbplants.data.model

data class Rod(
    var id_rod: String = "",
    var name_ru: String = "",
    var name_latin: String = ""
)

data class PriznakiRoda(
    var id_priznak_rod: String = "",
    var name: String = ""
)

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
)
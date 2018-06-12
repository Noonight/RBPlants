package com.arkhipov.ayur.rbplants.ui.main.search

import com.arkhipov.ayur.rbplants.any.base.base_mvp.MvpPresenter
import com.arkhipov.ayur.rbplants.any.utils.Log
import com.arkhipov.ayur.rbplants.data.ChipRod
import com.arkhipov.ayur.rbplants.data.model.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.pchmn.materialchips.ChipView
import com.pchmn.materialchips.model.ChipInterface
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.lang.Exception
import java.util.function.BiConsumer
import javax.inject.Inject
import kotlin.reflect.KClass

class SearchPresenter @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val fireDatabase: FirebaseDatabase,
    private val fireCloud: FirebaseStorage
) : MvpPresenter<SearchView>() {

    lateinit var dbChip: DBChip

    fun onInitViews() {
        dbChip = DBChip(fireDatabase)

        Log.d(dbChip.getRods({
            view.putFilterableChipData(it)
        }).toString())

        getTest()
    }

    private fun getInitialCheapData(): List<ChipInterface> {
        var chipList = mutableListOf<ChipInterface>()
        val tmpTagData: List<CheapParams> = listOf(
            CheapParams(1, "Василистник", "Thalictrum"),
            CheapParams(2, "Прострел", "Pulsatilla"))
        chipList.addAll(tmpTagData)
        //Log.d(dbChip.getRods().toString())
        return chipList
    }

    fun getTest() {
        dbChip.reactiveGet("Rod")!!
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("On subscribe")
                var answ = mutableListOf<Rod>()
                it.forEach {
                    val rod = Rod()
                    rod.id_rod = (it.value as HashMap<Any, Any>).get("id").toString()
                    rod.name_ru = (it.value as HashMap<Any, Any>).get("name_ru").toString()
                    rod.name_latin = (it.value as HashMap<Any, Any>).get("name_latin").toString()
                    answ.add(rod)
                }
                var chipAnsw = mutableListOf<ChipRod>()
                answ.forEach {
                    chipAnsw.add(ChipRod(it.id_rod, it.name_ru, it.name_latin))
                }

                view.putFilterableChipData(chipAnsw)

                Log.d(chipAnsw.toString())
            }, {
                Log.w(it as Exception?)
            })
    }

    fun setFilterableChipData(filterableList: List<ChipInterface>) {
        view.putFilterableChipData(filterableList)
    }

    fun onChipAdded(chip: ChipInterface, size: Int) {
        Log.d(chip.label)
    }

    fun onChipRemoved(chip: ChipInterface, size: Int) {

    }

    class DBChip(private val database: FirebaseDatabase) {
        var curRod: Int = -1
        var curRodPriznaki: List<PriznakiRoda>? = null
        var curRodGroup: List<NameRodovihGroup>? = null
        var curVid: Int = -1
        var curVidPriznaki: List<PriznakiVida>? = null
        var curVidGroup: List<NameVidovihGroup>? = null

        inline fun getRods(crossinline funV: (List<ChipInterface>) -> Unit) {
            var rods = mutableListOf<Rod>()

            reactiveGet("Rod")!!
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("On subscribe")
                    var answ = mutableListOf<Rod>()
                    it.forEach {
                        val rod = Rod()
                        rod.id_rod = (it.value as HashMap<Any, Any>).get("id").toString()
                        rod.name_ru = (it.value as HashMap<Any, Any>).get("name_ru").toString()
                        rod.name_latin = (it.value as HashMap<Any, Any>).get("name_latin").toString()
                        answ.add(rod)
                    }
                    /*var chipAnsw = mutableListOf<ChipRod>()
                    answ.forEach {
                        chipAnsw.add(ChipRod(it.id_rod, it.name_ru, it.name_latin))
                    }*/
                    //view.putFilterableChipData(chipAnsw)
                    funV(answ)
                    Log.d(answ.toString())
                }, {
                    Log.w(it as Exception?)
                })
        }

        inline fun getPriznakiRoda(crossinline funV: (List<ChipInterface>) -> Unit) {
            var rods = mutableListOf<PriznakiRoda>()

            reactiveGet("PriznakiRoda")!!
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("On subscribe")
                    var answ = mutableListOf<PriznakiRoda>()
                    it.forEach {
                        val rod = PriznakiRoda()
                        rod.id_priznak_rod = (it.value as HashMap<Any, Any>).get("id").toString()
                        rod.name = (it.value as HashMap<Any, Any>).get("name").toString()
                        answ.add(rod)
                    }
                    /*var chipAnsw = mutableListOf<ChipRod>()
                    answ.forEach {
                        chipAnsw.add(ChipRod(it.id_rod, it.name_ru, it.name_latin))
                    }*/
                    //view.putFilterableChipData(chipAnsw)
                    funV(answ)
                    Log.d(answ.toString())
                }, {
                    Log.w(it as Exception?)
                })
        }

        fun <T> responseToList(response: DataSnapshot/*, c: Class<T>*/): MutableList</*Class<*/T> {
            val data = response.value
            var answ = mutableListOf<T>()
            /*response.children.forEach {
                answ.add(it)
            }*/
            (data as List<T>).forEach {
                //answ.add(hashMapToObject((it as HashMap<Any, Any>), c))
                if (it != null) {
                    answ.add(it)
                }
            }
            return answ
        }

        fun responseToMutableIterable(response: DataSnapshot): MutableIterable<DataSnapshot>? {
            return response.children
        }

        /*fun <T> hashMapToObject(map: HashMap<Any, Any>, d: Class<T>): Class<T> {
            d.fields.forEach {
                if (map.containsKey(it.name)) {
                    it.set(it.name, map.getValue(it.name))
                }
            }
            return d
        }*/

        fun getDB(): DB {
            var rods = mutableListOf<Rod>()
            database.reference.child("Rod").addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError?) {}
                override fun onDataChange(p0: DataSnapshot?) {
                    val dbRods = p0?.value
                    rods.addAll(dbRods as Collection<Rod>)
                }
            })
            var priznakiRodas = mutableListOf<PriznakiRoda>()
            database.reference.child("PriznakiRoda").addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError?) {}
                override fun onDataChange(p0: DataSnapshot?) {
                    val dbRods = p0?.value
                    priznakiRodas.addAll(dbRods as Collection<PriznakiRoda>)
                }
            })
            var opredelenieRodas = mutableListOf<OpredelenieRoda>()
            database.reference.child("OpredelenieRoda").addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError?) {}
                override fun onDataChange(p0: DataSnapshot?) {
                    val dbRods = p0?.value
                    opredelenieRodas.addAll(dbRods as Collection<OpredelenieRoda>)
                }
            })
            var groupRodovihPriznakov = mutableListOf<GroupRodovihPriznakov>()
            database.reference.child("GroupRodovihPriznakov").addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError?) {}
                override fun onDataChange(p0: DataSnapshot?) {
                    val dbRods = p0?.value
                    groupRodovihPriznakov.addAll(dbRods as Collection<GroupRodovihPriznakov>)
                }
            })
            var nameRodovihGroups = mutableListOf<NameRodovihGroup>()
            database.reference.child("Rod").addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError?) {}
                override fun onDataChange(p0: DataSnapshot?) {
                    val dbRods = p0?.value
                    nameRodovihGroups.addAll(dbRods as Collection<NameRodovihGroup>)
                }
            })

            var vids = mutableListOf<Vid>()
            database.reference.child("Vid").addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError?) {}
                override fun onDataChange(p0: DataSnapshot?) {
                    val dbRods = p0?.value
                    vids.addAll(dbRods as Collection<Vid>)
                }
            })
            var priznakiVidas = mutableListOf<PriznakiVida>()
            database.reference.child("PriznakiVida").addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError?) {}
                override fun onDataChange(p0: DataSnapshot?) {
                    val dbRods = p0?.value
                    priznakiVidas.addAll(dbRods as Collection<PriznakiVida>)
                }
            })
            var opredelenieVidas = mutableListOf<OpredelenieVida>()
            database.reference.child("OpredelenieVida").addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError?) {}
                override fun onDataChange(p0: DataSnapshot?) {
                    val dbRods = p0?.value
                    opredelenieVidas.addAll(dbRods as Collection<OpredelenieVida>)
                }
            })
            var groupVidovihPriznakov = mutableListOf<GroupVidovihPriznakov>()
            database.reference.child("GroupVidovihPriznakov").addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError?) {}
                override fun onDataChange(p0: DataSnapshot?) {
                    val dbRods = p0?.value
                    groupVidovihPriznakov.addAll(dbRods as Collection<GroupVidovihPriznakov>)
                }
            })
            var nameVidovihGroups = mutableListOf<NameVidovihGroup>()
            database.reference.child("NameVidovihGroup").addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError?) {}
                override fun onDataChange(p0: DataSnapshot?) {
                    val dbRods = p0?.value
                    if (dbRods != null) {
                        nameVidovihGroups.addAll(dbRods as Collection<NameVidovihGroup>)
                    } else {
                        Log.d("NAME VIDOVIH GROUP IS EMPTY")
                    }
                }
            })

            return DB(
                rods,
                priznakiRodas,
                opredelenieRodas,
                groupRodovihPriznakov,
                nameRodovihGroups,
                vids,
                priznakiVidas,
                opredelenieVidas,
                groupVidovihPriznakov,
                nameVidovihGroups
            )
        }

        fun reactiveGet(child: String): Flowable<MutableIterable<DataSnapshot>>? {
            return Flowable.create({ subscriber: FlowableEmitter<MutableIterable<DataSnapshot>> ->
                database.reference.child(child).addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(error: DatabaseError?) =
                        subscriber.onError(error!!.toException())

                    override fun onDataChange(snapshot: DataSnapshot?) {
                        //val data = responseToList<T>(snapshot!!)
                        subscriber.onNext(responseToMutableIterable(snapshot!!)!!)
                    }
                })
            }, BackpressureStrategy.BUFFER)
        }

        data class DB(
            var rods: List<Rod> = mutableListOf(),
            var priznakiRoda: List<PriznakiRoda> = mutableListOf(),
            var opredelenieRoda: List<OpredelenieRoda> = mutableListOf(),
            var groupRodovihPriznakov: List<GroupRodovihPriznakov> = mutableListOf(),
            var nameRodovihGroup: List<NameRodovihGroup> = mutableListOf(),
            var vids: List<Vid> = mutableListOf(),
            var priznakiVida: List<PriznakiVida> = mutableListOf(),
            var opredelenieVida: List<OpredelenieVida> = mutableListOf(),
            var groupVidovihPriznakov: List<GroupVidovihPriznakov> = mutableListOf(),
            var nameVidovihGroup: List<NameVidovihGroup> = mutableListOf()
        )
    }
}




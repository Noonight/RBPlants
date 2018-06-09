package com.arkhipov.ayur.rbplants.ui.main

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.HandlerThread
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.arkhipov.ayur.rbplants.App
import com.arkhipov.ayur.rbplants.R
import com.arkhipov.ayur.rbplants.any.base.fragmentnavigations.NavigationDefaults
import com.arkhipov.ayur.rbplants.ui.NavigationIds.Companion.BottomItems.Companion.DATA
import com.arkhipov.ayur.rbplants.ui.NavigationIds.Companion.BottomItems.Companion.PROFILE_MENU
import com.arkhipov.ayur.rbplants.ui.NavigationIds.Companion.BottomItems.Companion.SEARCH
import com.arkhipov.ayur.rbplants.ui.main.camera.CameraFragment
import com.arkhipov.ayur.rbplants.ui.main.search.SearchFragment
import com.arkhipov.ayur.rbplants.any.utils.DialogUtils
import com.arkhipov.ayur.rbplants.any.utils.Log
import com.arkhipov.ayur.rbplants.ui.google_sheets.RequestPermissionsToolImpl
import com.arkhipov.ayur.rbplants.ui.main.profile.ProfileFragment
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.google.android.cameraview.CameraView
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView {
    @Inject
    lateinit var presenter: MainPresenter
    private lateinit var bottomNavigation: AHBottomNavigation

    private lateinit var requestPermissionsToolImpl: RequestPermissionsToolImpl

    companion object {
        val REQUEST_CAMERA_PERMISSION = 1007
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App[this].component.inject(this)
        presenter.attachView(this)

        NavigationDefaults.NavigationDefaultsHolder.navigationDefaults()
            .navigationIconListener {
                onBackPressed()
            }

        bottomNavigation()

        initViews()
    }

    private fun initViews() {
        //bottomNavigation.currentItem = 0
        showSearch()
        //bottomNavigation.currentItem = 2
        requestPermissionsToolImpl = RequestPermissionsToolImpl()
        //requestPermissions()
    }

    @SuppressLint("ResourceAsColor")
    fun bottomNavigation() {
        bottomNavigation = findViewById(R.id.main_bottom_navigation)
        bottomNavigation.addItems(
            NavigationDefaults.NavigationDefaultsHolder
                .navigationDefaults()
                .navigationItems()
                .bottomNavigationItems())
        bottomNavigation.accentColor = resources.getColor(R.color.accent)
        bottomNavigation.setOnTabSelectedListener(object : AHBottomNavigation.OnTabSelectedListener {
            override fun onTabSelected(position: Int, wasSelected: Boolean): Boolean {
                Log.d(" Bottom navigation pressed: position = $position and wasSelected = $wasSelected")
                when (position) {
                /**
                 * [SEARCH]
                 * */
                    0 -> if (!wasSelected) showSearch()
                /**
                 * [DATA]
                 * */
                    1 -> {
                        if (!wasSelected) {
                            if (requestPermissionsToolImpl.isPermissionsGranted(this@MainActivity,
                                    listOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE).toTypedArray())) {
                                showCamera()
                            } else {
                                requestPermissions()
                                if (requestPermissionsToolImpl.isPermissionsGranted(this@MainActivity,
                                        listOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE).toTypedArray())) {
                                    showCamera()
                                }
                            }
                        }
                    }
                /**
                 * [PROFILE_MENU]
                 * */
                    2 -> if (!wasSelected) showProfile()
                }
                return true
            }

        })
    }

    private fun requestPermissions() {
        val permissions = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        //requestPermissionsToolImpl = RequestPermissionsToolImpl()
        requestPermissionsToolImpl.requestPermissions(this, permissions)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {

        var grantedAllPermissions = true
        for (grantResult in grantResults) {
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                grantedAllPermissions = false
            }
        }

        if (grantResults.size != permissions.size || !grantedAllPermissions) {
            bottomNavigation.setCurrentItem(0)
            requestPermissionsToolImpl.onPermissionDenied()
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }

    }

    override fun showSearch() {
        Log.d("Show search fragment")
        //bottomNavigation.currentItem = 0
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_fragment_container, SearchFragment(), SearchFragment::class.java.simpleName)
            .commit()
    }

    override fun showCamera() {
        //bottomNavigation.currentItem = 1
        Log.d("Show data fragment")
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_fragment_container, CameraFragment(), CameraFragment::class.java.simpleName)
            .addToBackStack(null)
            .commit()
    }

    override fun showProfile() {
        Log.d("Show menu profile fragment")
        //bottomNavigation.currentItem = 2
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_fragment_container, ProfileFragment())
            .commit()
    }

    override fun onBackPressed() {
        Log.d("Back stack entry count = ${supportFragmentManager.backStackEntryCount}")
        if (isBackStackEmpty()) {
            DialogUtils.createOkCancel(this, R.string.alert_sign_out, R.string.confirm_exit, ok = {
                Log.d("Exit DialogUtils positive pressed")
                finish()
            }, cancel = {
                Log.d("Exit Dialog Utils negative pressed")
            }).show()
        } else if (supportFragmentManager.findFragmentByTag(CameraFragment::class.java.simpleName) == supportFragmentManager.primaryNavigationFragment) {
            //showBottomNavigation()
            //don't working
        } else if (/*supportFragmentManager.backStackEntryCount > 0 && */bottomNavigation.isHidden) {
            showBottomNavigation()
        } else {
            super.onBackPressed()
            Log.d("Navigation item BACK pressed!")
        }
        /*if (supportFragmentManager.backStackEntryCount > 0*//* && bottomNavigation.isHidden*//*) {
            showBottomNavigation()
        }*/
        showBottomNavigation() // TODO change
        //if (supportFragmentManager.back)
    }

    fun isBackStackEmpty(): Boolean {
        if (supportFragmentManager.backStackEntryCount == 0) {
            return true
        }
        return false
    }

    override fun hideBottomNavigation() {
        bottomNavigation.hideBottomNavigation(false)
    }

    override fun showBottomNavigation() {
        //bottomNavigation.refresh()
        bottomNavigation.restoreBottomNavigation(false)
    }
}
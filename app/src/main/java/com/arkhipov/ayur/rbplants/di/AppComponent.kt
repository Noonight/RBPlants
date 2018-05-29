package com.arkhipov.ayur.rbplants.di

import com.arkhipov.ayur.rbplants.App
import com.arkhipov.ayur.rbplants.di.modules.ContextModule
import com.arkhipov.ayur.rbplants.di.modules.FirebaseModule
import com.arkhipov.ayur.rbplants.di.modules.RealmHelperModule
import com.arkhipov.ayur.rbplants.ui.authorization.AuthActivity
import com.arkhipov.ayur.rbplants.ui.authorization.sign_in.SignInFragment
import com.arkhipov.ayur.rbplants.ui.authorization.sign_up.SignUpFragment
import com.arkhipov.ayur.rbplants.ui.main.MainActivity
import com.arkhipov.ayur.rbplants.ui.main.data.DataFragment
import com.arkhipov.ayur.rbplants.ui.main.profile.ProfileFragment
import com.arkhipov.ayur.rbplants.ui.main.profile.edit.EditProfileFragment
import com.arkhipov.ayur.rbplants.ui.main.profile.my_groups.MyGroupsFragment
import com.arkhipov.ayur.rbplants.ui.main.profile.my_groups.MyPlantsFragment
import com.arkhipov.ayur.rbplants.ui.main.profile.my_images.MyImagesFragment
import com.arkhipov.ayur.rbplants.ui.main.profile.my_images.MyImagesPresenter
import com.arkhipov.ayur.rbplants.ui.main.search.SearchFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [(FirebaseModule::class), (ContextModule::class), (RealmHelperModule::class)])
interface AppComponent {
    fun inject(app: App)

    fun inject(authActivity: AuthActivity)

    fun inject(signInFragment: SignInFragment)

    fun inject(signUpFragment: SignUpFragment)

    fun inject(mainActivity: MainActivity)

    fun inject(searchFragment: SearchFragment)

    fun inject(dataFragment: DataFragment)

    fun inject(profile: ProfileFragment)

    fun inject(editProfileFragment: EditProfileFragment)

    fun inject(myImagesFragment: MyImagesFragment)

    fun inject(myGroupsFragment: MyGroupsFragment)

    fun inject(myPlantsFragment: MyPlantsFragment)
}

/*@Singleton
@Component(modules = [AppModule::class])
interface AuthComponent
{
    fun inject(authActivity: AuthActivity)

    fun presenter(): AuthPresenter
}

@Singleton
@Component(modules = [AppModule::class])
interface SignInComponent
{
    fun inject(signInFragment: SignInFragment)

    fun presenter(): SignInPresenter
}

@Singleton
@Component(modules = [AppModule::class])
interface SignUpComponent
{
    fun inject(signUpFragment: SignUpFragment)

    fun presenter(): SignUpPresenter
}*/

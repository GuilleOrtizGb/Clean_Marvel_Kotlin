package com.puzzlebench.clean_marvel_kotlin.RoboElectric

import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.CharacterDetailFragment
import junit.framework.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.util.FragmentTestUtil.startFragment
import android.R
import android.support.v7.widget.RecyclerView
import org.robolectric.shadows.ShadowActivity
import android.R.attr.visible
import com.puzzlebench.clean_marvel_kotlin.presentation.MainActivity
import org.junit.Before
import org.robolectric.Shadows.shadowOf
import org.robolectric.android.controller.ActivityController
import android.widget.TextView
import com.puzzlebench.clean_marvel_kotlin.BuildConfig
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_character_dialog_detail.view.*
import org.powermock.core.classloader.annotations.PowerMockIgnore
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor
import org.robolectric.annotation.Config
import org.powermock.api.mockito.PowerMockito
import io.realm.log.RealmLog
import org.junit.Assert.assertThat
import org.powermock.api.mockito.PowerMockito.`when`
import org.powermock.api.mockito.PowerMockito.mockStatic
import org.powermock.modules.junit4.rule.PowerMockRule
import org.junit.Rule
import org.hamcrest.CoreMatchers.`is`


@RunWith(RobolectricTestRunner::class)
@PowerMockIgnore("org.mockito.*", "org.robolectric.*", "android.*")
@SuppressStaticInitializationFor("io.realm.internal.Util")
@PrepareForTest(Realm::class, RealmLog::class)

class CharacterClickTest{

    private lateinit var activity: MainActivity

    @get:Rule val rule = PowerMockRule()
     var mockRealm: Realm? = null

    @Before
    fun setUp(){

        mockStatic(RealmLog::class.java)
        mockStatic(Realm::class.java)

        val mockRealm = PowerMockito.mock(Realm::class.java)

        `when`(Realm.getDefaultInstance()).thenReturn(mockRealm)

        this.mockRealm = mockRealm

        activity=Robolectric.setupActivity(MainActivity::class.java)

    }

    @Test
    fun shouldBeAbleToGetDefaultInstance() {
        assertThat(Realm.getDefaultInstance(), `is`(mockRealm))
    }

    @Test
    fun recyclerView_hasData (){
        var recyclerView: RecyclerView = activity.recycleView
        // val tvHelloWorld = activity.findViewById(R.id.tvHelloWorld) as TextView

        recyclerView.performClick()
        assertNotNull(recyclerView.adapter.itemCount)
        assertNotNull(recyclerView.recycledViewPool)
//        assertNotNull(recyclerView.swapAdapter(null,true))

    }

    @Test
    fun clickingCharacter_showsDetailFragement(){
        var fragment: CharacterDetailFragment = CharacterDetailFragment.newInstance(1)

        startFragment(fragment)
        assertNotNull(fragment)

        val activityController = Robolectric.buildActivity<MainActivity>(MainActivity::class.java)
        activityController.create().start().visible()

        val myActivityShadow = shadowOf(activityController.get())

        //val currentRecyclerView = myActivityShadow.findViewById(R.id.recycleView) as RecyclerView
        //currentRecyclerView.getChildAt(0).performClick()
    }
}
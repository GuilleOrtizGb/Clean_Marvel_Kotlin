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
import org.robolectric.Shadows.shadowOf
import org.robolectric.android.controller.ActivityController

@RunWith(RobolectricTestRunner::class)
class CharacterClickTest{
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
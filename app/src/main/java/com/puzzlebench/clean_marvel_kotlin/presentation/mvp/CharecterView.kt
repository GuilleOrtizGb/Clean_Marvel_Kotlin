package com.puzzlebench.clean_marvel_kotlin.presentation.mvp

import android.app.FragmentTransaction
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.puzzlebench.clean_marvel_kotlin.R
import com.puzzlebench.clean_marvel_kotlin.data.ContentProvider.CharacterLoader
import com.puzzlebench.clean_marvel_kotlin.data.ContentProvider.UpdateCharacters
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.presentation.MainActivity
import com.puzzlebench.clean_marvel_kotlin.presentation.adapter.CharacterAdapter
import com.puzzlebench.clean_marvel_kotlin.presentation.extension.showToast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ref.WeakReference

class CharecterView(val activity: MainActivity) : UpdateCharacters {


    private val CHARACTER_LOADER_ID=101
    private val SPAN_COUNT = 1

    var adapter:CharacterAdapter?=null

    fun init() {
        if (activity != null) {

             adapter = CharacterAdapter { character ->
                val fragment=CharacterDetailFragment.newInstance(character.id)
                fragment.show(activity.fragmentManager,"detailDialogTag")
            }

            activity.recycleView.layoutManager = GridLayoutManager(activity, SPAN_COUNT)
            activity.recycleView.adapter = adapter
            activity.loaderManager.initLoader(CHARACTER_LOADER_ID,null, CharacterLoader(activity,this))
            showLoading()
        }

    }

    fun getFloatinButton(): FloatingActionButton {
        return  activity.floatingActionButton

    }

    override fun updateCharacters(characters: List<Character>) {
        hideLoading()
        showCharacters(characters)
    }

    fun showToast(message: String) {
        if (activity != null) {
            activity.applicationContext.showToast(message)

        }
    }

    fun showToastNoItemToShow() {
        if (activity != null) {
            val message = activity.baseContext.resources.getString(R.string.message_no_items_to_show)
            activity.applicationContext.showToast(message)

        }
    }

    fun showToastNetworkError(error: String) {
       activity.applicationContext.showToast(error)
    }

    fun hideLoading() {
        activity.progressBar.visibility = View.GONE
    }

    fun showCharacters(characters: List<Character>) {
        adapter?.data = characters
    }

    fun showLoading() {
        activity.progressBar.visibility = View.VISIBLE

    }
}

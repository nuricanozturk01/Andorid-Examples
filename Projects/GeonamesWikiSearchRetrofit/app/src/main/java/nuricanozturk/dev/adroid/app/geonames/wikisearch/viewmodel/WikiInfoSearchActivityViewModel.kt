package nuricanozturk.dev.adroid.app.geonames.wikisearch.viewmodel

import nuricanozturk.dev.adroid.app.geonames.wikisearch.WikiInfoSearchActivity
import java.lang.ref.WeakReference

class WikiInfoSearchActivityViewModel(activity : WikiInfoSearchActivity)
{
    private val mWeakReference = WeakReference(activity)


    fun handleGetButtonClicked() = mWeakReference.get()?.handleGetButtonClicked()

    fun handleListViewItemClicked(pos : Int) = mWeakReference.get()?.handleListViewItemClicked(pos)
}
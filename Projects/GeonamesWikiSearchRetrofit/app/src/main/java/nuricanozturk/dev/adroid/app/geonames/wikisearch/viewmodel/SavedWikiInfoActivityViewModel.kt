package nuricanozturk.dev.adroid.app.geonames.wikisearch.viewmodel

import android.view.View
import nuricanozturk.dev.adroid.app.geonames.wikisearch.MainActivity
import nuricanozturk.dev.adroid.app.geonames.wikisearch.SavedWikiInfoActivity
import java.lang.ref.WeakReference

class SavedWikiInfoActivityViewModel(activity : SavedWikiInfoActivity)
{
    private val mWeakReference = WeakReference(activity)

    fun handleListViewItemClicked(pos : Int) = mWeakReference.get()?.handleListViewItemClicked(pos)

    fun handleRemoveAllButtonClicked() = mWeakReference.get()?.handleRemoveAllButtonClicked()

}
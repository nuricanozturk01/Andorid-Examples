package nuricanozturk.dev.adroid.app.geonames.wikisearch.viewmodel

import nuricanozturk.dev.adroid.app.geonames.wikisearch.WikiInfoSummaryActivity
import java.lang.ref.WeakReference

class WikiInfoSummaryViewModel(activity : WikiInfoSummaryActivity)
{
    private val mWeakReference = WeakReference(activity)

    fun handleSaveButtonClicked() = mWeakReference.get()?.handleSaveButtonClicked()

}
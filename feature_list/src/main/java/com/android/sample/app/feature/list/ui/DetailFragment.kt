package com.android.sample.app.feature.list.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import coil.annotation.ExperimentalCoilApi
import com.android.sample.app.feature.list.R
import com.android.sample.app.feature.list.ui.component.DetailView
import com.android.sample.common.theme.ComposeTheme
import com.android.sample.common.util.composeView

@ExperimentalCoilApi
class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = composeView {
        ComposeTheme {
            val poster = DetailFragmentArgs.fromBundle(requireArguments()).poster
            DetailView(
                poster,
                findNavController()::navigateUp
            ) {
                val arg = DetailFragmentArgs(poster).toBundle()
                val pendingIntent = findNavController()
                    .createDeepLink()
                    .setDestination(R.id.detailFragment)
                    .setArguments(arg)
                    .createPendingIntent()
                Notifier.postNotification(poster, requireContext(), pendingIntent)
            }
        }
    }
}
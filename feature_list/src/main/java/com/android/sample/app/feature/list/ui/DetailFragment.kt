package com.android.sample.app.feature.list.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.android.sample.app.feature.list.R
import com.android.sample.app.feature.list.ui.detail.DetailsScreen
import com.android.sample.common.theme.ComposeTheme
import com.android.sample.common.util.composeView

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = composeView {
        ComposeTheme {
            val args: DetailFragmentArgs by navArgs()
            DetailsScreen(
                args.poster,
                findNavController()::navigateUp
            ) {
                val pendingIntent = findNavController()
                    .createDeepLink()
                    .setDestination(R.id.detailFragment)
                    .setArguments(args.toBundle())
                    .createPendingIntent()
                Notifier.postNotification(args.poster, requireContext(), pendingIntent)
            }
        }
    }
}
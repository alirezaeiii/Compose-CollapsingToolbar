package com.android.sample.app.feature.list.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import coil.annotation.ExperimentalCoilApi
import com.android.sample.app.feature.list.ui.component.DetailView
import com.android.sample.app.feature.list.ui.theme.ComposeTheme
import com.android.sample.common.util.composeView

@ExperimentalCoilApi
class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = composeView {
        ComposeTheme {
            DetailView(
                DetailFragmentArgs.fromBundle(requireArguments()).poster,
                findNavController()::navigateUp
            )
        }
    }
}
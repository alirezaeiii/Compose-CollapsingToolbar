package com.android.sample.app.feature.list.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.android.sample.app.feature.list.R
import com.android.sample.app.feature.list.ui.main.VerticalCollection
import com.android.sample.common.theme.ComposeTheme
import com.android.sample.common.ui.Content
import com.android.sample.common.util.composeView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = composeView {
        ComposeTheme {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Text(text = stringResource(id = R.string.disney))
                            }
                        },
                        elevation = 8.dp,
                        modifier = Modifier.clip(
                            RoundedCornerShape(
                                bottomStart = 18.dp,
                                bottomEnd = 18.dp
                            )
                        )
                    )
                },
                content = {
                    Content(viewModel) { posters ->
                        VerticalCollection(posters) { poster ->
                            val destination =
                                MainFragmentDirections.actionMainFragmentToDetailFragment(
                                    poster
                                )
                            findNavController().navigate(destination)
                        }
                    }
                }
            )
        }
    }
}

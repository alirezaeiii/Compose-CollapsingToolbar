package com.android.sample.app.feature.list.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.annotation.ExperimentalCoilApi
import com.android.sample.app.feature.list.R
import com.android.sample.app.feature.list.ui.component.ErrorView
import com.android.sample.app.feature.list.ui.component.VerticalListView
import com.android.sample.common.theme.ComposeTheme
import com.android.sample.common.util.ViewState
import com.android.sample.common.util.composeView
import com.android.sample.core.response.Poster
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalCoilApi
@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = composeView {
        ComposeTheme {
            val viewState = viewModel.stateFlow.collectAsState().value
            Content(viewState = viewState)
        }
    }

    @Composable
    private fun Content(viewState: ViewState<List<Poster>>) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Column(modifier = Modifier.padding(4.dp)) {
                            Text(text = stringResource(id = R.string.disney))
                        }
                    },
                    elevation = 8.dp,
                )
            },
            content = {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    when (viewState) {
                        is ViewState.Loading -> CircularProgressIndicator()
                        is ViewState.Success -> {
                            val isRefreshing by viewModel.isRefreshing.collectAsState()
                            SwipeRefresh(
                                state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
                                onRefresh = { viewModel.refresh() },
                            ) {
                                VerticalListView(viewState.data, OnClickListener { poster ->
                                    val destination =
                                        MainFragmentDirections.actionMainFragmentToDetailFragment(
                                            poster
                                        )
                                    findNavController().navigate(destination)
                                })
                            }
                        }
                        is ViewState.Error ->
                            ErrorView(message = viewState.message, viewModel::refresh)
                    }
                }
            })
    }
}

package me.turkergoksu.kefilm.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

/**
 * Created by turkergoksu on 18-Jun-21.
 */
abstract class BaseComposeFragment<VM : ViewModel> : Fragment() {
    abstract val viewModel: VM

    @Composable
    abstract fun Content()

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                this@BaseComposeFragment.Content()
            }
        }
    }
}
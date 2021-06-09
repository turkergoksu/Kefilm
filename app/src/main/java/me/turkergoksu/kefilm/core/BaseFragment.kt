package me.turkergoksu.kefilm.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

/**
 * Created by turkergoksu on 09-Jun-21.
 */
abstract class BaseFragment<DB : ViewDataBinding, VM : ViewModel> : Fragment() {

    abstract val viewModel: VM

    private var _binding: DB? = null
    protected val binding: DB get() = _binding!!

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = getViewBinding()
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    abstract fun getViewBinding(): DB

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
package com.example.assignmenttest.base


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.assignmenttest.di.fragmentModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinContext
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import org.kodein.di.generic.kcontext

abstract class BaseFragment<B : ViewDataBinding, VM : ViewModel> : Fragment(), KodeinAware {
    protected lateinit var binding: B
    lateinit var viewModel: VM

    override val kodeinContext: KodeinContext<*> get() = kcontext(activity)

    // todo Get the "global" application kodein.
    private val _parentKodein by closestKodein()

    override val kodein: Kodein = Kodein.lazy {
        // todo 	Extends the "global" application kodein, to be able to access, with this new Kodein object,
        //  all bindings defined at the application level
        extend(_parentKodein)
        // todo Activity/fragment specific bindings
        import(fragmentModule)
    }

    private val viewModelFactory by instance<ViewModelProvider.Factory>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindContentView(inflater, layoutId(), container)
        fragmentInitialized()
        return binding.root
    }

    private fun bindContentView(inflater: LayoutInflater, layoutId: Int, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        viewModel = ViewModelProvider(this, viewModelFactory).get(getViewModelClass())
    }

    abstract fun getViewModelClass(): Class<VM>

    @LayoutRes
    protected abstract fun layoutId(): Int

    abstract fun fragmentInitialized()
}
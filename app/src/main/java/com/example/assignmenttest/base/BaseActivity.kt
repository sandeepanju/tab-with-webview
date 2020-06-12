package com.example.assignmenttest.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.kodein.di.Copy
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.android.retainedKodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

abstract class BaseActivity<B : ViewDataBinding,Vm : ViewModel>: AppCompatActivity(),KodeinAware{
   protected lateinit var binding: B
   protected lateinit var viewModel: Vm

    // todo Get the "global" application kodein.
    private val parentKodien by closestKodein()

    /*todo Using retainedKodein instead of Kodein ensures that
       the Kodein object is retained and not recreated between activity restarts.*/
    override val kodein by retainedKodein {
        // todo 	Extends the "global" application kodein, to be able to access, with this new Kodein object,
        //  all bindings defined at the application level
        extend(parentKodien,copy = Copy.All)
        // todo activity specific bindings
        bind<Activity>() with singleton { this@BaseActivity }
        bind<Context>("ActivityContext") with singleton { this@BaseActivity }
        import(activityModule)
    }

    private val viewModelFactory by instance<ViewModelProvider.Factory>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindContentView(layoutId())

    }

    private fun bindContentView(layoutId: Int) {
        binding = DataBindingUtil.setContentView(this, layoutId)
        viewModel = ViewModelProvider(this, viewModelFactory).get(getViewModelClass())

    }
    @LayoutRes
    abstract fun layoutId(): Int
    abstract fun getViewModelClass(): Class<Vm>
}
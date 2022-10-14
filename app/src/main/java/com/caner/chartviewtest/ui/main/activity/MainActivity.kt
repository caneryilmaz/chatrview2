package com.caner.chartviewtest.ui.main.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.caner.chartviewtest.R
import com.caner.chartviewtest.ui.main.fragment.CustomThemesFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startFragment(CustomThemesFragment::class.java)
    }
    private fun <T : Fragment> startFragment(
        fragmentClass: Class<T>,
        shouldAddToBackStack: Boolean = true
    ) {
        supportFragmentManager
            .beginTransaction().apply {
                replace(
                    R.id.container,
                    FragmentFactory.getInstance(fragmentClass)
                )
                if (shouldAddToBackStack) {
                    addToBackStack(null)
                }
                commit()
            }
    }
}
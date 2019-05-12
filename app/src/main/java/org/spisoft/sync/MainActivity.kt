package org.spisoft.sync

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.spisoft.sync.account.AccountListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                setFragment(AccountListFragment())
                return@OnNavigationItemSelectedListener true
            }

        }
        false
    }
    private var mFragment: Fragment ?= null

    fun setFragment(fragment: Fragment) {
        this.mFragment = fragment
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment, fragment)
            .addToBackStack(null).commit()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}

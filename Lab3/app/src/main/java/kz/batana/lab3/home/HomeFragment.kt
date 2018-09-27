package kz.batana.lab3.home


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.*
import kz.batana.lab3.R

class HomeFragment : Fragment() {

    private lateinit var recentNewsFragment: NewsFragment
    private lateinit var categoryFragment: CategoryFragment

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //View pager fragments
        cleanChildFragments(childFragmentManager)
        val adapter = HomeFragmentPagerAdapter(childFragmentManager)
        recentNewsFragment = NewsFragment.newInstance()
        categoryFragment = CategoryFragment.newInstance()

        adapter.addFragment(recentNewsFragment, resources.getString(R.string.tab_title1))
        adapter.addFragment(categoryFragment, resources.getString(R.string.tab_title2))
        view_pager_home.adapter = adapter
        tab_layout_home.setupWithViewPager(view_pager_home)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    private fun cleanChildFragments(childFragmentManager: FragmentManager) {
        val childFragments = childFragmentManager.fragments
        if (!childFragments.isEmpty()) {
            val ft = childFragmentManager.beginTransaction()
            for (fragment in childFragments) {
                ft.remove(fragment)
            }
            ft.commit()
        }
    }



}

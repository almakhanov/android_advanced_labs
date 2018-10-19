package kz.batana.lab3.home


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.*
import kz.batana.lab3.R
import kz.batana.lab3.home.news.NewsFragment
import kz.batana.lab3.home.todo.TodoFragment

class HomeFragment : Fragment() {

    private lateinit var recentNewsFragment: NewsFragment
    private lateinit var categoryFragment: TodoFragment

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //View pager fragments
        val adapter = HomeFragmentPagerAdapter(childFragmentManager)
        recentNewsFragment = NewsFragment.newInstance()
        categoryFragment = TodoFragment.newInstance()

        adapter.addFragment(recentNewsFragment, resources.getString(R.string.tab_title1))
        adapter.addFragment(categoryFragment, resources.getString(R.string.tab_title2))
        view_pager_home.adapter = adapter
        tab_layout_home.setupWithViewPager(view_pager_home)
    }



}

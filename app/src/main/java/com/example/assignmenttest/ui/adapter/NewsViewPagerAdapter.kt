package com.example.assignmenttest.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.assignmenttest.ui.fragment.NewsFragment
import com.example.assignmenttest.ui.model.MNews

class NewsViewPagerAdapter(
    fragmentManager: FragmentManager,
    private var mapNews: HashMap<String, ArrayList<MNews>>,
    private var titleList: ArrayList<String>
): FragmentStatePagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
    return NewsFragment(mapNews[titleList[position]])
    }

    override fun getCount(): Int {
       return titleList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleList[position]
    }
}
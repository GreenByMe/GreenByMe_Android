package org.greenbyme.angelhack.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_home.*
import org.greenbyme.angelhack.R


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var missionList = mutableListOf<Item>(Item("1"), Item("2"), Item("3"), Item("4"))
        var newsList = mutableListOf<Item>(Item("4"), Item("3"), Item("2"), Item("1"))

        val missionAdapter = HomeRecyclerAdapter(missionList)
        var newsAdapter = HomeRecyclerAdapter(newsList)

        rv_home_mission.adapter = missionAdapter
        rv_home_news.adapter = newsAdapter


        val lm_mission = LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false)
        val lm_news = LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false)

        rv_home_mission.layoutManager = lm_mission
        rv_home_news.layoutManager = lm_news
    }
}

package com.xx.lesson

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.xx.base.BaseView
import com.xx.lesson.entity.Lesson

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class LessonActivity :AppCompatActivity(),BaseView<LessonPresenter?>,Toolbar.OnMenuItemClickListener{

    private val lessonPresenter = LessonPresenter(this)
    private val lessonAdapter = LessonAdapter()
    private lateinit var refreshLayout:SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson)
        initLayout()
        getPresenter()?.fetchData()
    }

    /**
     * 初始化布局
     */
    private fun initLayout(){
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.inflateMenu(R.menu.menu_lesson)
        toolbar.setOnMenuItemClickListener(this)

        val recyclerView = findViewById<RecyclerView>(R.id.list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = lessonAdapter
        recyclerView.addItemDecoration(DividerItemDecoration(this,LinearLayout.VERTICAL))
        refreshLayout = findViewById(R.id.refresh)
        refreshLayout.setOnRefreshListener {getPresenter()?.fetchData()}
        refreshLayout.isRefreshing = true
    }

    override fun getPresenter(): LessonPresenter? {
        return lessonPresenter
    }

    /**
     * 展示数据,停止刷新动画
     */
    public fun showResult(lessons :List<Lesson>?){
        lessonAdapter.updateAndNotify(lessons!!)
        refreshLayout.isRefreshing = false
    }

    override fun onMenuItemClick(p0: MenuItem?): Boolean {
        getPresenter()?.showPlayback()
        return false
    }
}
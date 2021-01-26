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
class LessonActivity : AppCompatActivity(), BaseView<LessonPresenter>,
    Toolbar.OnMenuItemClickListener {

    private val lessonAdapter = LessonAdapter()
    private lateinit var refreshLayout: SwipeRefreshLayout

    override val presenter: LessonPresenter by lazy {
        LessonPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson)
        initLayout()
        presenter.fetchData()
    }

    /**
     * 初始化布局
     */
    private fun initLayout() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.inflateMenu(R.menu.menu_lesson)
        toolbar.setOnMenuItemClickListener(this)

        findViewById<RecyclerView>(R.id.list).apply {
            layoutManager = LinearLayoutManager(this@LessonActivity)
            adapter = lessonAdapter
            addItemDecoration(DividerItemDecoration(this@LessonActivity, LinearLayout.VERTICAL))
        }

        findViewById<SwipeRefreshLayout>(R.id.refresh).apply {
            setOnRefreshListener { presenter.fetchData() }
            isRefreshing = true
        }
    }

    /**
     * 展示数据,停止刷新动画
     */
    internal fun showResult(lessons: List<Lesson>?) {
        lessonAdapter.updateAndNotify(lessons!!)
        refreshLayout.isRefreshing = false
    }

    override fun onMenuItemClick(p0: MenuItem?): Boolean {
        presenter.showPlayback()
        return false
    }

}
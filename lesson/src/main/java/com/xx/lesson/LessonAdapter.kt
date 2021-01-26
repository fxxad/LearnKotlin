package com.xx.lesson

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xx.base.BaseViewHolder
import com.xx.lesson.entity.Lesson

class LessonAdapter : RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {

    private var list: List<Lesson> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        return LessonViewHolder.onCreate(parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        //这里的arraylist使用数组取值方式，是因为定义了操作符
        holder.onBind(list[position])
    }

    fun updateAndNotify(data: List<Lesson>) {
        list = data
        notifyDataSetChanged()
    }

    class LessonViewHolder internal constructor(itemView: View) : BaseViewHolder(itemView) {
        fun onBind(lesson: Lesson) {
            // ?: 如果操作符前面的值为null则把操作符后面的值赋值
            setText(R.id.tv_date, lesson.date ?: "日期待定")
            setText(R.id.tv_content, lesson.content!!)

            lesson.state?.let {
                setText(R.id.tv_state, it.stateName()!!)
                val color = when(it){
                    Lesson.State.PLAYBACK -> R.color.purple_200
                    Lesson.State.LIVE -> R.color.purple_500
                    Lesson.State.WAIT -> R.color.purple_700
                }
                getView<View>(R.id.tv_state)!!.setBackgroundColor(itemView.context.resources.getColor(color))
            }

//            val state = lesson.state
//            if (state != null) {
//                setText(R.id.tv_state, state.stateName()!!)
//                val color = when(state){
//                    Lesson.State.PLAYBACK -> R.color.purple_200
//                    Lesson.State.LIVE -> R.color.purple_500
//                    Lesson.State.WAIT -> R.color.purple_700
//                }
//                getView<View>(R.id.tv_state)!!.setBackgroundColor(itemView.context.resources.getColor(color))
//            }

        }

        companion object {
            fun onCreate(parent: ViewGroup): LessonViewHolder {
                return LessonViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_lesson, parent, false)
                )
            }
        }
    }

}
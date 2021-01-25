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

        holder.onBind(list[position])
    }

    fun updateAndNotify(data: List<Lesson>) {
        list = data
        notifyDataSetChanged()
    }

    class LessonViewHolder internal constructor(itemView: View) : BaseViewHolder(itemView) {
        fun onBind(lesson: Lesson) {
            var date = lesson.getDate()
            if (date == null) {
                date = "日期待定"
            }
            setText(R.id.tv_date, date)
            setText(R.id.tv_content, lesson.getContent()!!)
            val state = lesson.getState()
            if (state != null) {
                setText(R.id.tv_state, state.stateName()!!)
                var color = when(state){
                    Lesson.State.PLAYBACK -> R.color.purple_200
                    Lesson.State.LIVE -> R.color.purple_500
                    Lesson.State.WAIT -> R.color.purple_700
                    else -> R.color.teal_200
                }
                getView<View>(R.id.tv_state)!!.setBackgroundColor(itemView.context.resources.getColor(color))
            }

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
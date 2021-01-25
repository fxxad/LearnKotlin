package com.xx.lesson

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.reflect.TypeToken
import com.xx.base.http.EntityCallback
import com.xx.base.http.HttpClient.get
import com.xx.base.util.toast
import com.xx.lesson.entity.Lesson

/**
 * Presenter
 */
class LessonPresenter(activity: LessonActivity) {
    companion object{
        //const 编译器常量
        const val LESSON_PATH = "lessons"
    }

    private var activity:LessonActivity?= activity

    private var lessons:List<Lesson> = ArrayList()

    private val type = object : TypeToken<List<Lesson?>?>(){}.type

    fun fetchData(){
        get(LESSON_PATH,type,object :EntityCallback<List<Lesson>>{
            override fun onFailure(errorMsg: String) {
                activity!!.runOnUiThread{
                    toast(errorMsg)
                }
            }

            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun onSuccess(data: List<Lesson>) {
                this@LessonPresenter.lessons = data
                activity!!.runOnUiThread{
                    activity!!.showResult(data)
                }
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun showPlayback(){
        val playbackLessons: MutableList<Lesson> = ArrayList()
        for (lesson in lessons){
            if(lesson.getState() == Lesson.State.PLAYBACK){
                playbackLessons.add(lesson)
            }
        }
        activity!!.showResult(playbackLessons)
    }
}
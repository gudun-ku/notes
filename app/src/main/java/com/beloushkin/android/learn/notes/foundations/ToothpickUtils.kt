package com.beloushkin.android.learn.notes.foundations

import com.beloushkin.android.learn.notes.notes.INoteModel
import com.beloushkin.android.learn.notes.notes.NoteLocalModel
import com.beloushkin.android.learn.notes.tasks.ITaskModel
import com.beloushkin.android.learn.notes.tasks.TaskLocalModel
import toothpick.Toothpick
import toothpick.config.Module

object ApplicationScope {
    val scope = Toothpick.openScope(this).apply {
        installModules(ApplicationModule)
    }
}

object ApplicationModule: Module() {
    init {
        bind(INoteModel::class.java).toInstance(NoteLocalModel())
        bind(ITaskModel::class.java).toInstance(TaskLocalModel())
    }
}
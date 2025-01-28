package com.example.dagger

import com.example.repository.TaskRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun getTaskRepository(): TaskRepository
}
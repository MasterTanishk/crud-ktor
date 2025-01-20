package com.example.dagger

import com.example.database.DatabaseFactory
import com.example.repository.PostgresTaskRepository
import com.example.repository.TaskRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import javax.sql.DataSource

@Module
class AppModule {
    @Singleton
    @Provides
    fun provideDataSource(): DataSource {
        return DatabaseFactory().createDataSource()
    }
    @Singleton
    @Provides
    fun provideTaskRepository(dataSource: DataSource): TaskRepository {
        return PostgresTaskRepository(dataSource)
    }
}
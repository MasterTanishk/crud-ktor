package com.example.dagger;

import com.example.repository.TaskRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.Providers;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.sql.DataSource;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class AppModule_ProvideTaskRepositoryFactory implements Factory<TaskRepository> {
  private final AppModule module;

  private final Provider<DataSource> dataSourceProvider;

  public AppModule_ProvideTaskRepositoryFactory(AppModule module,
      Provider<DataSource> dataSourceProvider) {
    this.module = module;
    this.dataSourceProvider = dataSourceProvider;
  }

  @Override
  public TaskRepository get() {
    return provideTaskRepository(module, dataSourceProvider.get());
  }

  public static AppModule_ProvideTaskRepositoryFactory create(AppModule module,
      javax.inject.Provider<DataSource> dataSourceProvider) {
    return new AppModule_ProvideTaskRepositoryFactory(module, Providers.asDaggerProvider(dataSourceProvider));
  }

  public static AppModule_ProvideTaskRepositoryFactory create(AppModule module,
      Provider<DataSource> dataSourceProvider) {
    return new AppModule_ProvideTaskRepositoryFactory(module, dataSourceProvider);
  }

  public static TaskRepository provideTaskRepository(AppModule instance, DataSource dataSource) {
    return Preconditions.checkNotNullFromProvides(instance.provideTaskRepository(dataSource));
  }
}

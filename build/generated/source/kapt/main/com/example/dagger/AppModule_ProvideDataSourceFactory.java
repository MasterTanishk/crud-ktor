package com.example.dagger;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class AppModule_ProvideDataSourceFactory implements Factory<DataSource> {
  private final AppModule module;

  public AppModule_ProvideDataSourceFactory(AppModule module) {
    this.module = module;
  }

  @Override
  public DataSource get() {
    return provideDataSource(module);
  }

  public static AppModule_ProvideDataSourceFactory create(AppModule module) {
    return new AppModule_ProvideDataSourceFactory(module);
  }

  public static DataSource provideDataSource(AppModule instance) {
    return Preconditions.checkNotNullFromProvides(instance.provideDataSource());
  }
}

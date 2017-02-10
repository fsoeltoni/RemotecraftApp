package com.zireck.remotecraft.dagger.components;

import com.zireck.remotecraft.dagger.PerActivity;
import com.zireck.remotecraft.dagger.modules.ActivityModule;
import com.zireck.remotecraft.dagger.modules.InteractorsModule;
import com.zireck.remotecraft.dagger.modules.PresentersModule;
import com.zireck.remotecraft.dagger.modules.UiModule;
import com.zireck.remotecraft.view.activity.SearchServerActivity;
import dagger.Component;

@PerActivity
@Component(
    dependencies = ApplicationComponent.class,
    modules = {
        ActivityModule.class,
        UiModule.class,
        InteractorsModule.class,
        PresentersModule.class
    }
)
public interface SearchServerComponent extends ActivityComponent {
  void inject(SearchServerActivity searchServerActivity);
}

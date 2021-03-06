package com.zireck.remotecraft.infrastructure.provider;

import android.content.Context;
import com.zireck.remotecraft.domain.Permission;
import com.zireck.remotecraft.domain.provider.PermissionProvider;
import com.zireck.remotecraft.infrastructure.entity.PermissionEntity;
import com.zireck.remotecraft.infrastructure.entity.mapper.PermissionEntityDataMapper;
import com.zireck.remotecraft.infrastructure.permission.AndroidPermissionChecker;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class) public class PermissionDataProviderTest {

  private PermissionProvider permissionProvider;

  @Mock private AndroidPermissionChecker mockAndroidPermissionChecker;
  @Mock private PermissionEntityDataMapper mockPermissionEntityDataMapper;

  @Before public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);

    permissionProvider =
        new PermissionDataProvider(mockAndroidPermissionChecker, mockPermissionEntityDataMapper);
  }

  @Test public void shouldNotifyPermissionIsGrantedWhenItsActuallyGranted() throws Exception {
    Permission permission = getPermission();
    PermissionEntity permissionEntity = getPermissionEntity();
    when(mockPermissionEntityDataMapper.transformInverse(permission)).thenReturn(permissionEntity);
    when(mockAndroidPermissionChecker.checkSelfPermission(any(Context.class),
        same(permissionEntity))).thenReturn(0);
    when(mockAndroidPermissionChecker.isGranted(0)).thenReturn(true);

    Single<Boolean> isGranted = permissionProvider.isGranted(permission);

    assertThat(isGranted, notNullValue());
    TestObserver<Boolean> testObserver = isGranted.test();
    testObserver.assertNoErrors();
    List<Boolean> streamValues = testObserver.values();
    assertThat(streamValues, notNullValue());
    assertThat(streamValues.size(), is(1));
    assertThat(streamValues.get(0), is(true));
  }

  @Test public void shouldNotifyPermissionNotGrantedWhenItsActuallyNotGranted() throws Exception {
    Permission permission = getPermission();
    PermissionEntity permissionEntity = getPermissionEntity();
    when(mockPermissionEntityDataMapper.transformInverse(permission)).thenReturn(permissionEntity);
    when(mockAndroidPermissionChecker.checkSelfPermission(any(Context.class),
        same(permissionEntity))).thenReturn(-1);
    when(mockAndroidPermissionChecker.isGranted(-1)).thenReturn(false);

    Single<Boolean> isGranted = permissionProvider.isGranted(permission);

    assertThat(isGranted, notNullValue());
    TestObserver<Boolean> testObserver = isGranted.test();
    testObserver.assertNoErrors();
    List<Boolean> streamValues = testObserver.values();
    assertThat(streamValues, notNullValue());
    assertThat(streamValues.size(), is(1));
    assertThat(streamValues.get(0), is(false));
  }

  private Permission getPermission() {
    return new Permission.Builder()
        .permission("CAMERA")
        .build();
  }

  private PermissionEntity getPermissionEntity() {
    return new PermissionEntity.Builder()
        .permission("CAMERA")
        .build();
  }
}
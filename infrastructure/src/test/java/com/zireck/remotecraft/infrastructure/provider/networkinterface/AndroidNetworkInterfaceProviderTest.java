package com.zireck.remotecraft.infrastructure.provider.networkinterface;

import java.net.NetworkInterface;
import java.util.Collection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class) public class AndroidNetworkInterfaceProviderTest {

  private AndroidNetworkInterfaceProvider androidNetworkInterfaceProvider;

  @Before public void setUp() throws Exception {
    androidNetworkInterfaceProvider = new AndroidNetworkInterfaceProvider();
  }

  @Test public void shouldObtainAValidNetworkInterfaceCollection() throws Exception {
    Collection<NetworkInterface> networkInterfaces =
        androidNetworkInterfaceProvider.getNetworkInterfaces();

    assertThat(networkInterfaces, notNullValue());
    assertThat(networkInterfaces, is(instanceOf(Collection.class)));
  }
}
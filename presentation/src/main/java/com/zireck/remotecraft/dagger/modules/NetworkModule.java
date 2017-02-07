package com.zireck.remotecraft.dagger.modules;

import com.zireck.remotecraft.domain.provider.NetworkProvider;
import com.zireck.remotecraft.infrastructure.manager.NetworkProtocolManager;
import com.zireck.remotecraft.infrastructure.manager.ServerSearchManager;
import com.zireck.remotecraft.infrastructure.protocol.mapper.MessageJsonMapper;
import com.zireck.remotecraft.infrastructure.protocol.mapper.ServerMapper;
import com.zireck.remotecraft.infrastructure.provider.NetworkDataProvider;
import com.zireck.remotecraft.infrastructure.provider.broadcastaddress.AndroidBroadcastAddressProvider;
import com.zireck.remotecraft.infrastructure.provider.broadcastaddress.BroadcastAddressProvider;
import com.zireck.remotecraft.infrastructure.provider.networkinterface.AndroidNetworkInterfaceProvider;
import com.zireck.remotecraft.infrastructure.provider.networkinterface.NetworkInterfaceProvider;
import com.zireck.remotecraft.infrastructure.tool.JsonSerializer;
import com.zireck.remotecraft.infrastructure.tool.NetworkConnectionlessDatagramTransmitter;
import com.zireck.remotecraft.infrastructure.tool.NetworkConnectionlessTransmitter;
import com.zireck.remotecraft.infrastructure.validation.NetworkInterfaceValidator;
import com.zireck.remotecraft.infrastructure.validation.ServerMessageValidator;
import dagger.Module;
import dagger.Provides;
import java.net.DatagramSocket;
import java.net.SocketException;
import javax.inject.Singleton;

@Module public class NetworkModule {

  public NetworkModule() {

  }

  @Provides @Singleton DatagramSocket provideDatagramSocket() {
    // TODO use Optional
    DatagramSocket datagramSocket;
    try {
      datagramSocket = new DatagramSocket();
    } catch (SocketException e) {
      datagramSocket = null;
    }

    return datagramSocket;
  }

  @Provides @Singleton NetworkConnectionlessTransmitter provideNetworkConnectionlessTransmitter(
      DatagramSocket datagramSocket) {
    return new NetworkConnectionlessDatagramTransmitter(datagramSocket);
  }

  @Provides @Singleton NetworkProvider provideNetworkProvider(
      NetworkDataProvider networkDataProvider) {
    return networkDataProvider;
  }

  @Provides @Singleton NetworkInterfaceProvider provideNetworkInterfaceProvider() {
    return new AndroidNetworkInterfaceProvider();
  }

  @Provides @Singleton BroadcastAddressProvider provideBroadcastAddressProvider(
      NetworkInterfaceProvider networkInterfaceProvider,
      NetworkInterfaceValidator networkInterfaceValidator) {
    return new AndroidBroadcastAddressProvider(networkInterfaceProvider, networkInterfaceValidator);
  }

  @Provides @Singleton ServerSearchManager provideServerSearchManager(
      NetworkConnectionlessTransmitter networkConnectionlessTransmitter,
      BroadcastAddressProvider broadcastAddressProvider,
      NetworkProtocolManager networkProtocolManager, MessageJsonMapper messageJsonMapper,
      ServerMapper serverMapper, ServerMessageValidator serverValidator) {
    return new ServerSearchManager(networkConnectionlessTransmitter, broadcastAddressProvider,
        networkProtocolManager, messageJsonMapper, serverMapper, serverValidator);
  }

  @Provides @Singleton NetworkProtocolManager provideNetworkProtocolManager(
      JsonSerializer jsonSerializer) {
    return new NetworkProtocolManager(jsonSerializer);
  }
}

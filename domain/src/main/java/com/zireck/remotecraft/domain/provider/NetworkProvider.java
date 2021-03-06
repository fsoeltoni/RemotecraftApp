package com.zireck.remotecraft.domain.provider;

import com.zireck.remotecraft.domain.NetworkAddress;
import com.zireck.remotecraft.domain.Server;
import io.reactivex.Maybe;

public interface NetworkProvider {
  Maybe<Server> searchServer();
  Maybe<Server> searchServer(NetworkAddress networkAddress);
}

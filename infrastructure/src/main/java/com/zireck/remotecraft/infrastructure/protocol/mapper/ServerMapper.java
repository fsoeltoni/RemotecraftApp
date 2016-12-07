package com.zireck.remotecraft.infrastructure.protocol.mapper;

import com.zireck.remotecraft.infrastructure.entity.WorldEntity;
import com.zireck.remotecraft.infrastructure.protocol.data.Server;
import javax.inject.Inject;

public class ServerMapper {

  @Inject public ServerMapper() {

  }

  public WorldEntity transform(Server server) {
    if (server == null) {
      return null;
    }

    WorldEntity worldEntity = new WorldEntity.Builder().ip(server.getIp())
        .seed(server.getSeed())
        .name(server.getWorldName())
        .player(server.getPlayerName())
        .build();

    return worldEntity;
  }
}
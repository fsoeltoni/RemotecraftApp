package com.zireck.remotecraft.infrastructure.protocol;

import com.zireck.remotecraft.infrastructure.protocol.base.type.CommandProtocol;
import com.zireck.remotecraft.infrastructure.protocol.messages.CommandMessage;
import com.zireck.remotecraft.infrastructure.protocol.enumeration.CommandType;

public class ProtocolMessageComposer {

  public ProtocolMessageComposer() {

  }

  public CommandMessage composeGetServerInfoCommand() {
    return new CommandMessage.Builder()
        .with(new CommandProtocol(CommandType.GET_SERVER_INFO))
        .build();
  }
}

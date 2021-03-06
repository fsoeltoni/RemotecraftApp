package com.zireck.remotecraft.server.mock.protocol;

public enum CommandType {
  GET_SERVER_INFO("get_server_info");

  private final String command;

  private CommandType(String command) {
    this.command = command;
  }

  @Override public String toString() {
    return command;
  }
}

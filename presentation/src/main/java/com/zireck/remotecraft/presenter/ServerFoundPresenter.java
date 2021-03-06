package com.zireck.remotecraft.presenter;

import android.support.annotation.NonNull;
import com.zireck.remotecraft.model.ServerModel;
import com.zireck.remotecraft.view.ServerFoundView;

public class ServerFoundPresenter implements Presenter<ServerFoundView> {

  private ServerFoundView view;
  private ServerModel serverModel;

  public ServerFoundPresenter() {

  }

  public void setServer(ServerModel serverModel) {
    this.serverModel = serverModel;
    showServerInView(serverModel);
  }

  @Override public void attachView(@NonNull ServerFoundView view) {
    this.view = view;
  }

  @Override public void resume() {
    showServerInView(serverModel);
  }

  @Override public void pause() {

  }

  @Override public void destroy() {

  }

  public void onClickAccept() {
    view.navigateBack(true, serverModel);
  }

  public void onClickCancel() {
    view.navigateBack(false, serverModel);
  }

  private void showServerInView(ServerModel serverModel) {
    if (serverModel == null) {
      showErrorServerInView();
    } else {
      showValidServerInView(serverModel);
    }
  }

  private void showErrorServerInView() {
    view.showError("Invalid Server");
  }

  private void showValidServerInView(ServerModel serverModel) {
    view.renderServer(serverModel);
  }
}

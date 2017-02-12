package com.zireck.remotecraft.view;

import com.zireck.remotecraft.model.ServerModel;

public interface SearchServerView extends BaseView {
  void navigateToServerDetail(ServerModel serverModel);
  void showMessage(String message);
  void showError(Exception exception);
  void closeMenu();
  void enableMenu();
  void disableMenu();
  void showLoading();
  void hideLoading();
  void startQrScanner();
  void stopQrScanner();
  void showNetworkAddressDialog();
}

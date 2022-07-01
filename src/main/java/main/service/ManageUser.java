package main.service;


public interface ManageUser {

  /**
   * сохранить нового пользователя
   *
   * @param userName
   * @param chatId
   */
  void saveUser(String userName, Long chatId);

  /**
   * сохранить нового пользователя
   *
   * @param firstName
   * @param lastName
   * @param chatId
   */
  void saveUser(String firstName, String lastName, Long chatId);


}

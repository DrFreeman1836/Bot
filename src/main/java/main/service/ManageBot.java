package main.service;


public interface ManageBot {

    /**
     * сохранить нового пользователя
     *
     * @param userName
     */
    void saveUser(String userName);

    /**
     * сохранить нового пользователя
     *
     * @param firstName
     * @param lastNAme
     */
    void saveUser(String firstName, String lastNAme);


}

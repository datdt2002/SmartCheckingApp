package vn.skymapglobal.smartcheckingapp.repositories;

import java.util.UUID;

import io.realm.Realm;
import vn.skymapglobal.smartcheckingapp.models.User;

public class UserRepository {
    private Realm realm;

    public UserRepository() {
        realm = Realm.getDefaultInstance();
    }

    public void closeRealm() {
        if (realm != null && !realm.isClosed()) {
            realm.close();
        }
    }
    public void registerUser(String inputFullname, String inputUsername, String inputPassword) {
        realm.beginTransaction();
        try {
            User existingUser = realm.where(User.class).equalTo("username", inputUsername).findFirst();
            if (existingUser == null) {
                User newUser = realm.createObject(User.class, UUID.randomUUID().toString());
                newUser.setFullname(inputFullname);
                newUser.setUsername(inputUsername);
                newUser.setPassword(inputPassword);
                realm.commitTransaction();
            } else {
                realm.cancelTransaction();
            }
        } catch (Exception e) {
            realm.cancelTransaction();
            e.printStackTrace();
        }
    }
    public boolean loginUser(String inputUsername, String inputPassword) {
        User user = realm.where(User.class)
                .equalTo("username", inputUsername)
                .equalTo("password", inputPassword)
                .findFirst();

        return user != null;
    }
}

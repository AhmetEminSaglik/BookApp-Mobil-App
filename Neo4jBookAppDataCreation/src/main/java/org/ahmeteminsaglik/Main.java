package org.ahmeteminsaglik;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    private static void createUser() {
        for (int i = 0; i < 20; i++) {

        }
    }

    private static String getUserCreateQuery(String name, String lastname, String username, String password, int totalFollowers) {
        return "create (n:User{name:" + name + ",lastname:" + lastname + ",username:" + username + ",password:" + password + ",totalFollowers:" + totalFollowers + "})\n";
    }

}
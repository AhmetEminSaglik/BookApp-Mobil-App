package org.ahmeteminsaglik.bookapp.business;

import org.ahmeteminsaglik.bookapp.dataaccess.EnumGender;
import org.ahmeteminsaglik.bookapp.model.User;

import java.util.ArrayList;
import java.util.List;

public class StaticData {
    static int imgCounter = 0;
    static int counter = 0;
    private static final List<User> userList = new ArrayList<>();
    private static User[][] userRelationArr;

    public static List<User> getUserList() {
        if (userList.isEmpty()) {
            userList.add(new User(-1L, "Michael", "Donna", "user1", "pass", EnumGender.MAN.getName(), 5, 5, "https://randomuser.me/api/portraits/" + getName(EnumGender.MAN.getName()) + "/" + getUserImage() + ".jpg"));
            userList.add(new User(-1L, "James", "Charles", "user3", "pass", EnumGender.MAN.getName(), 5, 5, "https://randomuser.me/api/portraits/" + getName(EnumGender.MAN.getName()) + "/" + getUserImage() + ".jpg"));
            userList.add(new User(-1L, "Thomas", "Sarah", "user5", "pass", EnumGender.MAN.getName(), 5, 5, "https://randomuser.me/api/portraits/" + getName(EnumGender.MAN.getName()) + "/" + getUserImage() + ".jpg"));
            userList.add(new User(-1L, "Donna", "Paul", "user7", "pass", EnumGender.MAN.getName(), 5, 5, "https://randomuser.me/api/portraits/" + getName(EnumGender.MAN.getName()) + "/" + getUserImage() + ".jpg"));
            userList.add(new User(-1L, "William", "Donna", "user9", "pass", EnumGender.MAN.getName(), 5, 5, "https://randomuser.me/api/portraits/" + getName(EnumGender.MAN.getName()) + "/" + getUserImage() + ".jpg"));
            userList.add(new User(-1L, "Andrew", "Daniel", "user11", "pass", EnumGender.MAN.getName(), 5, 5, "https://randomuser.me/api/portraits/" + getName(EnumGender.MAN.getName()) + "/" + getUserImage() + ".jpg"));
            userList.add(new User(-1L, "Steven", "Daniel", "user13", "pass", EnumGender.MAN.getName(), 5, 5, "https://randomuser.me/api/portraits/" + getName(EnumGender.MAN.getName()) + "/" + getUserImage() + ".jpg"));
            userList.add(new User(-1L, "Paul", "David", "user15", "pass", EnumGender.MAN.getName(), 5, 5, "https://randomuser.me/api/portraits/" + getName(EnumGender.MAN.getName()) + "/" + getUserImage() + ".jpg"));
            userList.add(new User(-1L, "Matthew", "Michael", "user17", "pass", EnumGender.MAN.getName(), 5, 5, "https://randomuser.me/api/portraits/" + getName(EnumGender.MAN.getName()) + "/" + getUserImage() + ".jpg"));
            userList.add(new User(-1L, "Daniel", "Susan", "user19", "pass", EnumGender.MAN.getName(), 5, 5, "https://randomuser.me/api/portraits/" + getName(EnumGender.MAN.getName()) + "/" + getUserImage() + ".jpg"));
            userList.add(new User(-1L, "Matthew", "Mark", "user21", "pass", EnumGender.MAN.getName(), 5, 5, "https://randomuser.me/api/portraits/" + getName(EnumGender.MAN.getName()) + "/" + getUserImage() + ".jpg"));
            userList.add(new User(-1L, "John", "William", "user23", "pass", EnumGender.MAN.getName(), 5, 5, "https://randomuser.me/api/portraits/" + getName(EnumGender.MAN.getName()) + "/" + getUserImage() + ".jpg"));
            userList.add(new User(-1L, "Thomas", "Elizabeth", "user25", "pass", EnumGender.MAN.getName(), 5, 5, "https://randomuser.me/api/portraits/" + getName(EnumGender.MAN.getName()) + "/" + getUserImage() + ".jpg"));
            userList.add(new User(-1L, "Andrew", "Jennifer", "user27", "pass", EnumGender.MAN.getName(), 5, 5, "https://randomuser.me/api/portraits/" + getName(EnumGender.MAN.getName()) + "/" + getUserImage() + ".jpg"));
            userList.add(new User(-1L, "Thomas", "Nancy", "user29", "pass", EnumGender.MAN.getName(), 5, 5, "https://randomuser.me/api/portraits/" + getName(EnumGender.MAN.getName()) + "/" + getUserImage() + ".jpg"));
            userList.add(new User(-1L, "Michelle", "Mark", "user2", "pass", EnumGender.WOMAN.getName(), 5, 5, "https://randomuser.me/api/portraits/" + getName(EnumGender.WOMAN.getName()) + "/" + getUserImage() + ".jpg"));
            userList.add(new User(-1L, "Kimberly", "Mary", "user4", "pass", EnumGender.WOMAN.getName(), 5, 5, "https://randomuser.me/api/portraits/" + getName(EnumGender.WOMAN.getName()) + "/" + getUserImage() + ".jpg"));
            userList.add(new User(-1L, "Patricia", "Elizabeth", "user6", "pass", EnumGender.WOMAN.getName(), 5, 5, "https://randomuser.me/api/portraits/" + getName(EnumGender.WOMAN.getName()) + "/" + getUserImage() + ".jpg"));
            userList.add(new User(-1L, "Mary", "Michael", "user8", "pass", EnumGender.WOMAN.getName(), 5, 5, "https://randomuser.me/api/portraits/" + getName(EnumGender.WOMAN.getName()) + "/" + getUserImage() + ".jpg"));
            userList.add(new User(-1L, "Ashley", "Karen", "user10", "pass", EnumGender.WOMAN.getName(), 5, 5, "https://randomuser.me/api/portraits/" + getName(EnumGender.WOMAN.getName()) + "/" + getUserImage() + ".jpg"));
            userList.add(new User(-1L, "Barbara", "Karen", "user12", "pass", EnumGender.WOMAN.getName(), 5, 5, "https://randomuser.me/api/portraits/" + getName(EnumGender.WOMAN.getName()) + "/" + getUserImage() + ".jpg"));
            userList.add(new User(-1L, "Betty", "Matthew", "user14", "pass", EnumGender.WOMAN.getName(), 5, 5, "https://randomuser.me/api/portraits/" + getName(EnumGender.WOMAN.getName()) + "/" + getUserImage() + ".jpg"));
            userList.add(new User(-1L, "Kimberly", "Margaret", "user16", "pass", EnumGender.WOMAN.getName(), 5, 5, "https://randomuser.me/api/portraits/" + getName(EnumGender.WOMAN.getName()) + "/" + getUserImage() + ".jpg"));
            userList.add(new User(-1L, "Margaret", "Mark", "user18", "pass", EnumGender.WOMAN.getName(), 5, 5, "https://randomuser.me/api/portraits/" + getName(EnumGender.WOMAN.getName()) + "/" + getUserImage() + ".jpg"));
            userList.add(new User(-1L, "Michelle", "William", "user20", "pass", EnumGender.WOMAN.getName(), 5, 5, "https://randomuser.me/api/portraits/" + getName(EnumGender.WOMAN.getName()) + "/" + getUserImage() + ".jpg"));
            userList.add(new User(-1L, "Amanda", "Barbara", "user22", "pass", EnumGender.WOMAN.getName(), 5, 5, "https://randomuser.me/api/portraits/" + getName(EnumGender.WOMAN.getName()) + "/" + getUserImage() + ".jpg"));
            userList.add(new User(-1L, "Elizabeth", "Jessica", "user24", "pass", EnumGender.WOMAN.getName(), 5, 5, "https://randomuser.me/api/portraits/" + getName(EnumGender.WOMAN.getName()) + "/" + getUserImage() + ".jpg"));
            userList.add(new User(-1L, "Mary", "Michael", "user26", "pass", EnumGender.WOMAN.getName(), 5, 5, "https://randomuser.me/api/portraits/" + getName(EnumGender.WOMAN.getName()) + "/" + getUserImage() + ".jpg"));
            userList.add(new User(-1L, "Jennifer", "Margaret", "user28", "pass", EnumGender.WOMAN.getName(), 5, 5, "https://randomuser.me/api/portraits/" + getName(EnumGender.WOMAN.getName()) + "/" + getUserImage() + ".jpg"));
            userList.add(new User(-1L, "Sarah", "Margaret", "user30", "pass", EnumGender.WOMAN.getName(), 5, 5, "https://randomuser.me/api/portraits/" + getName(EnumGender.WOMAN.getName()) + "/" + getUserImage() + ".jpg"));

        }
        return userList;
    }

    private static String getName(String gender) {
        return gender.equals(EnumGender.MAN.getName()) ? "men" : "women";
    }

    private static int getUserImage() {
        int imgNo = imgCounter;
        imgCounter++;
        return imgNo;
    }

    static void addRelationToArr(User user1, User user2) {
        userRelationArr[counter][0] = user1;
        userRelationArr[counter][1] = user2;
        counter++;
    }

    public static User[][] getRelationArr() {
        if (userRelationArr == null) {
            userRelationArr = new User[465][2];
            addRelationToArr(userList.get(1), userList.get(24));
            addRelationToArr(userList.get(1), userList.get(25));
            addRelationToArr(userList.get(1), userList.get(7));
            addRelationToArr(userList.get(1), userList.get(8));
            addRelationToArr(userList.get(1), userList.get(26));
            addRelationToArr(userList.get(1), userList.get(17));
            addRelationToArr(userList.get(1), userList.get(21));
            addRelationToArr(userList.get(1), userList.get(29));
            addRelationToArr(userList.get(1), userList.get(5));
            addRelationToArr(userList.get(1), userList.get(22));
            addRelationToArr(userList.get(1), userList.get(6));
            addRelationToArr(userList.get(1), userList.get(3));
            addRelationToArr(userList.get(1), userList.get(28));
            addRelationToArr(userList.get(1), userList.get(2));
            addRelationToArr(userList.get(1), userList.get(18));
            addRelationToArr(userList.get(1), userList.get(12));
            addRelationToArr(userList.get(1), userList.get(9));
            addRelationToArr(userList.get(1), userList.get(10));
            addRelationToArr(userList.get(1), userList.get(15));
            addRelationToArr(userList.get(1), userList.get(19));
            addRelationToArr(userList.get(1), userList.get(0));
            addRelationToArr(userList.get(1), userList.get(20));
            addRelationToArr(userList.get(1), userList.get(11));
            addRelationToArr(userList.get(1), userList.get(23));
            addRelationToArr(userList.get(1), userList.get(27));
            addRelationToArr(userList.get(1), userList.get(4));
            addRelationToArr(userList.get(1), userList.get(13));
            addRelationToArr(userList.get(1), userList.get(16));
            addRelationToArr(userList.get(2), userList.get(23));
            addRelationToArr(userList.get(2), userList.get(27));
            addRelationToArr(userList.get(3), userList.get(16));
            addRelationToArr(userList.get(3), userList.get(9));
            addRelationToArr(userList.get(3), userList.get(1));
            addRelationToArr(userList.get(3), userList.get(18));
            addRelationToArr(userList.get(3), userList.get(6));
            addRelationToArr(userList.get(3), userList.get(19));
            addRelationToArr(userList.get(3), userList.get(20));
            addRelationToArr(userList.get(3), userList.get(12));
            addRelationToArr(userList.get(3), userList.get(25));
            addRelationToArr(userList.get(3), userList.get(13));
            addRelationToArr(userList.get(3), userList.get(14));
            addRelationToArr(userList.get(3), userList.get(11));
            addRelationToArr(userList.get(3), userList.get(26));
            addRelationToArr(userList.get(3), userList.get(21));
            addRelationToArr(userList.get(3), userList.get(2));
            addRelationToArr(userList.get(3), userList.get(0));
            addRelationToArr(userList.get(3), userList.get(4));
            addRelationToArr(userList.get(3), userList.get(23));
            addRelationToArr(userList.get(3), userList.get(28));
            addRelationToArr(userList.get(3), userList.get(5));
            addRelationToArr(userList.get(3), userList.get(22));
            addRelationToArr(userList.get(3), userList.get(7));
            addRelationToArr(userList.get(3), userList.get(8));
            addRelationToArr(userList.get(4), userList.get(2));
            addRelationToArr(userList.get(4), userList.get(6));
            addRelationToArr(userList.get(4), userList.get(0));
            addRelationToArr(userList.get(4), userList.get(3));
            addRelationToArr(userList.get(4), userList.get(16));
            addRelationToArr(userList.get(4), userList.get(29));
            addRelationToArr(userList.get(4), userList.get(7));
            addRelationToArr(userList.get(4), userList.get(12));
            addRelationToArr(userList.get(4), userList.get(19));
            addRelationToArr(userList.get(4), userList.get(18));
            addRelationToArr(userList.get(4), userList.get(25));
            addRelationToArr(userList.get(4), userList.get(26));
            addRelationToArr(userList.get(4), userList.get(22));
            addRelationToArr(userList.get(4), userList.get(1));
            addRelationToArr(userList.get(4), userList.get(27));
            addRelationToArr(userList.get(4), userList.get(28));
            addRelationToArr(userList.get(4), userList.get(24));
            addRelationToArr(userList.get(4), userList.get(9));
            addRelationToArr(userList.get(4), userList.get(15));
            addRelationToArr(userList.get(4), userList.get(8));
            addRelationToArr(userList.get(4), userList.get(21));
            addRelationToArr(userList.get(4), userList.get(23));
            addRelationToArr(userList.get(4), userList.get(10));
            addRelationToArr(userList.get(4), userList.get(5));
            addRelationToArr(userList.get(5), userList.get(22));
            addRelationToArr(userList.get(5), userList.get(23));
            addRelationToArr(userList.get(5), userList.get(20));
            addRelationToArr(userList.get(5), userList.get(8));
            addRelationToArr(userList.get(5), userList.get(26));
            addRelationToArr(userList.get(5), userList.get(10));
            addRelationToArr(userList.get(5), userList.get(9));
            addRelationToArr(userList.get(5), userList.get(6));
            addRelationToArr(userList.get(5), userList.get(27));
            addRelationToArr(userList.get(5), userList.get(17));
            addRelationToArr(userList.get(5), userList.get(15));
            addRelationToArr(userList.get(5), userList.get(4));
            addRelationToArr(userList.get(5), userList.get(7));
            addRelationToArr(userList.get(6), userList.get(19));
            addRelationToArr(userList.get(6), userList.get(13));
            addRelationToArr(userList.get(6), userList.get(17));
            addRelationToArr(userList.get(6), userList.get(12));
            addRelationToArr(userList.get(6), userList.get(29));
            addRelationToArr(userList.get(6), userList.get(25));
            addRelationToArr(userList.get(6), userList.get(15));
            addRelationToArr(userList.get(7), userList.get(16));
            addRelationToArr(userList.get(7), userList.get(6));
            addRelationToArr(userList.get(7), userList.get(13));
            addRelationToArr(userList.get(7), userList.get(0));
            addRelationToArr(userList.get(7), userList.get(14));
            addRelationToArr(userList.get(7), userList.get(19));
            addRelationToArr(userList.get(7), userList.get(2));
            addRelationToArr(userList.get(7), userList.get(17));
            addRelationToArr(userList.get(7), userList.get(26));
            addRelationToArr(userList.get(7), userList.get(11));
            addRelationToArr(userList.get(7), userList.get(8));
            addRelationToArr(userList.get(7), userList.get(1));
            addRelationToArr(userList.get(7), userList.get(18));
            addRelationToArr(userList.get(7), userList.get(29));
            addRelationToArr(userList.get(7), userList.get(3));
            addRelationToArr(userList.get(7), userList.get(22));
            addRelationToArr(userList.get(7), userList.get(4));
            addRelationToArr(userList.get(7), userList.get(5));
            addRelationToArr(userList.get(7), userList.get(20));
            addRelationToArr(userList.get(7), userList.get(21));
            addRelationToArr(userList.get(7), userList.get(9));
            addRelationToArr(userList.get(7), userList.get(12));
            addRelationToArr(userList.get(7), userList.get(28));
            addRelationToArr(userList.get(7), userList.get(10));
            addRelationToArr(userList.get(7), userList.get(15));
            addRelationToArr(userList.get(7), userList.get(23));
            addRelationToArr(userList.get(7), userList.get(27));
            addRelationToArr(userList.get(7), userList.get(24));
            addRelationToArr(userList.get(8), userList.get(7));
            addRelationToArr(userList.get(8), userList.get(29));
            addRelationToArr(userList.get(8), userList.get(1));
            addRelationToArr(userList.get(8), userList.get(17));
            addRelationToArr(userList.get(8), userList.get(23));
            addRelationToArr(userList.get(8), userList.get(9));
            addRelationToArr(userList.get(9), userList.get(4));
            addRelationToArr(userList.get(9), userList.get(23));
            addRelationToArr(userList.get(9), userList.get(27));
            addRelationToArr(userList.get(10), userList.get(9));
            addRelationToArr(userList.get(10), userList.get(11));
            addRelationToArr(userList.get(10), userList.get(15));
            addRelationToArr(userList.get(10), userList.get(14));
            addRelationToArr(userList.get(10), userList.get(16));
            addRelationToArr(userList.get(10), userList.get(22));
            addRelationToArr(userList.get(10), userList.get(28));
            addRelationToArr(userList.get(10), userList.get(4));
            addRelationToArr(userList.get(10), userList.get(23));
            addRelationToArr(userList.get(10), userList.get(17));
            addRelationToArr(userList.get(10), userList.get(12));
            addRelationToArr(userList.get(10), userList.get(18));
            addRelationToArr(userList.get(10), userList.get(7));
            addRelationToArr(userList.get(10), userList.get(26));
            addRelationToArr(userList.get(10), userList.get(27));
            addRelationToArr(userList.get(10), userList.get(29));
            addRelationToArr(userList.get(10), userList.get(2));
            addRelationToArr(userList.get(10), userList.get(0));
            addRelationToArr(userList.get(10), userList.get(13));
            addRelationToArr(userList.get(10), userList.get(19));
            addRelationToArr(userList.get(10), userList.get(20));
            addRelationToArr(userList.get(10), userList.get(21));
            addRelationToArr(userList.get(11), userList.get(27));
            addRelationToArr(userList.get(11), userList.get(26));
            addRelationToArr(userList.get(11), userList.get(10));
            addRelationToArr(userList.get(11), userList.get(20));
            addRelationToArr(userList.get(11), userList.get(2));
            addRelationToArr(userList.get(11), userList.get(12));
            addRelationToArr(userList.get(11), userList.get(24));
            addRelationToArr(userList.get(11), userList.get(18));
            addRelationToArr(userList.get(11), userList.get(0));
            addRelationToArr(userList.get(11), userList.get(13));
            addRelationToArr(userList.get(11), userList.get(4));
            addRelationToArr(userList.get(11), userList.get(17));
            addRelationToArr(userList.get(11), userList.get(25));
            addRelationToArr(userList.get(11), userList.get(19));
            addRelationToArr(userList.get(11), userList.get(7));
            addRelationToArr(userList.get(12), userList.get(3));
            addRelationToArr(userList.get(12), userList.get(27));
            addRelationToArr(userList.get(12), userList.get(5));
            addRelationToArr(userList.get(12), userList.get(16));
            addRelationToArr(userList.get(12), userList.get(23));
            addRelationToArr(userList.get(12), userList.get(7));
            addRelationToArr(userList.get(12), userList.get(15));
            addRelationToArr(userList.get(12), userList.get(6));
            addRelationToArr(userList.get(12), userList.get(8));
            addRelationToArr(userList.get(12), userList.get(28));
            addRelationToArr(userList.get(12), userList.get(14));
            addRelationToArr(userList.get(12), userList.get(4));
            addRelationToArr(userList.get(12), userList.get(29));
            addRelationToArr(userList.get(12), userList.get(22));
            addRelationToArr(userList.get(12), userList.get(0));
            addRelationToArr(userList.get(12), userList.get(1));
            addRelationToArr(userList.get(12), userList.get(18));
            addRelationToArr(userList.get(12), userList.get(9));
            addRelationToArr(userList.get(12), userList.get(17));
            addRelationToArr(userList.get(12), userList.get(11));
            addRelationToArr(userList.get(12), userList.get(19));
            addRelationToArr(userList.get(12), userList.get(24));
            addRelationToArr(userList.get(12), userList.get(13));
            addRelationToArr(userList.get(12), userList.get(2));
            addRelationToArr(userList.get(12), userList.get(20));
            addRelationToArr(userList.get(12), userList.get(10));
            addRelationToArr(userList.get(12), userList.get(21));
            addRelationToArr(userList.get(12), userList.get(25));
            addRelationToArr(userList.get(13), userList.get(21));
            addRelationToArr(userList.get(14), userList.get(5));
            addRelationToArr(userList.get(14), userList.get(20));
            addRelationToArr(userList.get(14), userList.get(21));
            addRelationToArr(userList.get(14), userList.get(15));
            addRelationToArr(userList.get(14), userList.get(16));
            addRelationToArr(userList.get(14), userList.get(23));
            addRelationToArr(userList.get(14), userList.get(6));
            addRelationToArr(userList.get(14), userList.get(26));
            addRelationToArr(userList.get(14), userList.get(13));
            addRelationToArr(userList.get(14), userList.get(24));
            addRelationToArr(userList.get(14), userList.get(25));
            addRelationToArr(userList.get(14), userList.get(1));
            addRelationToArr(userList.get(14), userList.get(12));
            addRelationToArr(userList.get(14), userList.get(17));
            addRelationToArr(userList.get(14), userList.get(29));
            addRelationToArr(userList.get(14), userList.get(22));
            addRelationToArr(userList.get(15), userList.get(17));
            addRelationToArr(userList.get(15), userList.get(29));
            addRelationToArr(userList.get(15), userList.get(2));
            addRelationToArr(userList.get(15), userList.get(5));
            addRelationToArr(userList.get(15), userList.get(4));
            addRelationToArr(userList.get(15), userList.get(22));
            addRelationToArr(userList.get(15), userList.get(11));
            addRelationToArr(userList.get(15), userList.get(23));
            addRelationToArr(userList.get(15), userList.get(21));
            addRelationToArr(userList.get(15), userList.get(13));
            addRelationToArr(userList.get(15), userList.get(25));
            addRelationToArr(userList.get(15), userList.get(9));
            addRelationToArr(userList.get(15), userList.get(3));
            addRelationToArr(userList.get(15), userList.get(28));
            addRelationToArr(userList.get(15), userList.get(1));
            addRelationToArr(userList.get(15), userList.get(24));
            addRelationToArr(userList.get(15), userList.get(0));
            addRelationToArr(userList.get(15), userList.get(18));
            addRelationToArr(userList.get(16), userList.get(12));
            addRelationToArr(userList.get(16), userList.get(1));
            addRelationToArr(userList.get(16), userList.get(11));
            addRelationToArr(userList.get(16), userList.get(4));
            addRelationToArr(userList.get(16), userList.get(9));
            addRelationToArr(userList.get(16), userList.get(17));
            addRelationToArr(userList.get(16), userList.get(2));
            addRelationToArr(userList.get(16), userList.get(19));
            addRelationToArr(userList.get(16), userList.get(3));
            addRelationToArr(userList.get(16), userList.get(23));
            addRelationToArr(userList.get(16), userList.get(5));
            addRelationToArr(userList.get(16), userList.get(24));
            addRelationToArr(userList.get(16), userList.get(7));
            addRelationToArr(userList.get(16), userList.get(18));
            addRelationToArr(userList.get(16), userList.get(6));
            addRelationToArr(userList.get(16), userList.get(25));
            addRelationToArr(userList.get(16), userList.get(0));
            addRelationToArr(userList.get(17), userList.get(3));
            addRelationToArr(userList.get(17), userList.get(13));
            addRelationToArr(userList.get(17), userList.get(11));
            addRelationToArr(userList.get(17), userList.get(27));
            addRelationToArr(userList.get(17), userList.get(8));
            addRelationToArr(userList.get(17), userList.get(28));
            addRelationToArr(userList.get(17), userList.get(21));
            addRelationToArr(userList.get(17), userList.get(14));
            addRelationToArr(userList.get(17), userList.get(9));
            addRelationToArr(userList.get(17), userList.get(10));
            addRelationToArr(userList.get(17), userList.get(15));
            addRelationToArr(userList.get(17), userList.get(12));
            addRelationToArr(userList.get(17), userList.get(25));
            addRelationToArr(userList.get(17), userList.get(29));
            addRelationToArr(userList.get(17), userList.get(16));
            addRelationToArr(userList.get(17), userList.get(5));
            addRelationToArr(userList.get(17), userList.get(1));
            addRelationToArr(userList.get(17), userList.get(26));
            addRelationToArr(userList.get(17), userList.get(20));
            addRelationToArr(userList.get(17), userList.get(23));
            addRelationToArr(userList.get(17), userList.get(4));
            addRelationToArr(userList.get(17), userList.get(18));
            addRelationToArr(userList.get(17), userList.get(19));
            addRelationToArr(userList.get(17), userList.get(6));
            addRelationToArr(userList.get(17), userList.get(22));
            addRelationToArr(userList.get(17), userList.get(2));
            addRelationToArr(userList.get(17), userList.get(24));
            addRelationToArr(userList.get(17), userList.get(0));
            addRelationToArr(userList.get(18), userList.get(24));
            addRelationToArr(userList.get(18), userList.get(19));
            addRelationToArr(userList.get(18), userList.get(16));
            addRelationToArr(userList.get(18), userList.get(2));
            addRelationToArr(userList.get(18), userList.get(28));
            addRelationToArr(userList.get(18), userList.get(23));
            addRelationToArr(userList.get(19), userList.get(9));
            addRelationToArr(userList.get(19), userList.get(6));
            addRelationToArr(userList.get(19), userList.get(23));
            addRelationToArr(userList.get(19), userList.get(20));
            addRelationToArr(userList.get(19), userList.get(1));
            addRelationToArr(userList.get(19), userList.get(21));
            addRelationToArr(userList.get(19), userList.get(4));
            addRelationToArr(userList.get(19), userList.get(2));
            addRelationToArr(userList.get(20), userList.get(9));
            addRelationToArr(userList.get(20), userList.get(17));
            addRelationToArr(userList.get(20), userList.get(23));
            addRelationToArr(userList.get(20), userList.get(18));
            addRelationToArr(userList.get(20), userList.get(26));
            addRelationToArr(userList.get(20), userList.get(6));
            addRelationToArr(userList.get(20), userList.get(0));
            addRelationToArr(userList.get(20), userList.get(2));
            addRelationToArr(userList.get(20), userList.get(7));
            addRelationToArr(userList.get(20), userList.get(27));
            addRelationToArr(userList.get(20), userList.get(28));
            addRelationToArr(userList.get(20), userList.get(29));
            addRelationToArr(userList.get(20), userList.get(14));
            addRelationToArr(userList.get(20), userList.get(24));
            addRelationToArr(userList.get(20), userList.get(1));
            addRelationToArr(userList.get(20), userList.get(4));
            addRelationToArr(userList.get(20), userList.get(3));
            addRelationToArr(userList.get(21), userList.get(8));
            addRelationToArr(userList.get(21), userList.get(4));
            addRelationToArr(userList.get(21), userList.get(5));
            addRelationToArr(userList.get(21), userList.get(0));
            addRelationToArr(userList.get(21), userList.get(29));
            addRelationToArr(userList.get(21), userList.get(25));
            addRelationToArr(userList.get(21), userList.get(6));
            addRelationToArr(userList.get(21), userList.get(27));
            addRelationToArr(userList.get(21), userList.get(11));
            addRelationToArr(userList.get(21), userList.get(22));
            addRelationToArr(userList.get(21), userList.get(15));
            addRelationToArr(userList.get(21), userList.get(20));
            addRelationToArr(userList.get(21), userList.get(16));
            addRelationToArr(userList.get(21), userList.get(7));
            addRelationToArr(userList.get(21), userList.get(3));
            addRelationToArr(userList.get(21), userList.get(9));
            addRelationToArr(userList.get(21), userList.get(10));
            addRelationToArr(userList.get(21), userList.get(1));
            addRelationToArr(userList.get(21), userList.get(17));
            addRelationToArr(userList.get(21), userList.get(23));
            addRelationToArr(userList.get(21), userList.get(18));
            addRelationToArr(userList.get(21), userList.get(12));
            addRelationToArr(userList.get(22), userList.get(25));
            addRelationToArr(userList.get(22), userList.get(14));
            addRelationToArr(userList.get(22), userList.get(21));
            addRelationToArr(userList.get(22), userList.get(5));
            addRelationToArr(userList.get(23), userList.get(24));
            addRelationToArr(userList.get(23), userList.get(2));
            addRelationToArr(userList.get(23), userList.get(25));
            addRelationToArr(userList.get(23), userList.get(14));
            addRelationToArr(userList.get(23), userList.get(6));
            addRelationToArr(userList.get(23), userList.get(0));
            addRelationToArr(userList.get(23), userList.get(5));
            addRelationToArr(userList.get(23), userList.get(15));
            addRelationToArr(userList.get(23), userList.get(17));
            addRelationToArr(userList.get(23), userList.get(27));
            addRelationToArr(userList.get(23), userList.get(28));
            addRelationToArr(userList.get(23), userList.get(13));
            addRelationToArr(userList.get(23), userList.get(3));
            addRelationToArr(userList.get(23), userList.get(9));
            addRelationToArr(userList.get(23), userList.get(10));
            addRelationToArr(userList.get(23), userList.get(21));
            addRelationToArr(userList.get(23), userList.get(7));
            addRelationToArr(userList.get(23), userList.get(1));
            addRelationToArr(userList.get(23), userList.get(18));
            addRelationToArr(userList.get(23), userList.get(16));
            addRelationToArr(userList.get(23), userList.get(8));
            addRelationToArr(userList.get(24), userList.get(14));
            addRelationToArr(userList.get(24), userList.get(29));
            addRelationToArr(userList.get(24), userList.get(15));
            addRelationToArr(userList.get(24), userList.get(23));
            addRelationToArr(userList.get(24), userList.get(5));
            addRelationToArr(userList.get(24), userList.get(0));
            addRelationToArr(userList.get(24), userList.get(26));
            addRelationToArr(userList.get(24), userList.get(27));
            addRelationToArr(userList.get(24), userList.get(8));
            addRelationToArr(userList.get(24), userList.get(16));
            addRelationToArr(userList.get(24), userList.get(4));
            addRelationToArr(userList.get(24), userList.get(22));
            addRelationToArr(userList.get(24), userList.get(7));
            addRelationToArr(userList.get(24), userList.get(13));
            addRelationToArr(userList.get(24), userList.get(25));
            addRelationToArr(userList.get(24), userList.get(21));
            addRelationToArr(userList.get(24), userList.get(19));
            addRelationToArr(userList.get(24), userList.get(28));
            addRelationToArr(userList.get(24), userList.get(6));
            addRelationToArr(userList.get(24), userList.get(9));
            addRelationToArr(userList.get(24), userList.get(1));
            addRelationToArr(userList.get(24), userList.get(2));
            addRelationToArr(userList.get(24), userList.get(3));
            addRelationToArr(userList.get(24), userList.get(10));
            addRelationToArr(userList.get(24), userList.get(17));
            addRelationToArr(userList.get(24), userList.get(11));
            addRelationToArr(userList.get(25), userList.get(0));
            addRelationToArr(userList.get(25), userList.get(13));
            addRelationToArr(userList.get(25), userList.get(15));
            addRelationToArr(userList.get(25), userList.get(16));
            addRelationToArr(userList.get(25), userList.get(17));
            addRelationToArr(userList.get(25), userList.get(27));
            addRelationToArr(userList.get(25), userList.get(28));
            addRelationToArr(userList.get(25), userList.get(23));
            addRelationToArr(userList.get(25), userList.get(26));
            addRelationToArr(userList.get(25), userList.get(29));
            addRelationToArr(userList.get(25), userList.get(11));
            addRelationToArr(userList.get(25), userList.get(9));
            addRelationToArr(userList.get(25), userList.get(8));
            addRelationToArr(userList.get(25), userList.get(18));
            addRelationToArr(userList.get(25), userList.get(10));
            addRelationToArr(userList.get(26), userList.get(16));
            addRelationToArr(userList.get(26), userList.get(1));
            addRelationToArr(userList.get(26), userList.get(2));
            addRelationToArr(userList.get(26), userList.get(24));
            addRelationToArr(userList.get(26), userList.get(27));
            addRelationToArr(userList.get(26), userList.get(3));
            addRelationToArr(userList.get(26), userList.get(9));
            addRelationToArr(userList.get(26), userList.get(21));
            addRelationToArr(userList.get(27), userList.get(7));
            addRelationToArr(userList.get(27), userList.get(6));
            addRelationToArr(userList.get(27), userList.get(8));
            addRelationToArr(userList.get(27), userList.get(28));
            addRelationToArr(userList.get(27), userList.get(25));
            addRelationToArr(userList.get(27), userList.get(15));
            addRelationToArr(userList.get(27), userList.get(12));
            addRelationToArr(userList.get(27), userList.get(26));
            addRelationToArr(userList.get(27), userList.get(13));
            addRelationToArr(userList.get(27), userList.get(24));
            addRelationToArr(userList.get(27), userList.get(19));
            addRelationToArr(userList.get(27), userList.get(5));
            addRelationToArr(userList.get(27), userList.get(29));
            addRelationToArr(userList.get(27), userList.get(0));
            addRelationToArr(userList.get(27), userList.get(9));
            addRelationToArr(userList.get(27), userList.get(1));
            addRelationToArr(userList.get(27), userList.get(18));
            addRelationToArr(userList.get(27), userList.get(17));
            addRelationToArr(userList.get(27), userList.get(14));
            addRelationToArr(userList.get(27), userList.get(2));
            addRelationToArr(userList.get(27), userList.get(3));
            addRelationToArr(userList.get(27), userList.get(10));
            addRelationToArr(userList.get(28), userList.get(18));
            addRelationToArr(userList.get(28), userList.get(19));
            addRelationToArr(userList.get(28), userList.get(26));
            addRelationToArr(userList.get(28), userList.get(8));
            addRelationToArr(userList.get(28), userList.get(21));
            addRelationToArr(userList.get(28), userList.get(3));
            addRelationToArr(userList.get(28), userList.get(6));
            addRelationToArr(userList.get(28), userList.get(20));
            addRelationToArr(userList.get(28), userList.get(16));
            addRelationToArr(userList.get(28), userList.get(13));
            addRelationToArr(userList.get(28), userList.get(0));
            addRelationToArr(userList.get(28), userList.get(27));
            addRelationToArr(userList.get(28), userList.get(2));
            addRelationToArr(userList.get(28), userList.get(11));
            addRelationToArr(userList.get(28), userList.get(29));
            addRelationToArr(userList.get(28), userList.get(4));
            addRelationToArr(userList.get(28), userList.get(14));
            addRelationToArr(userList.get(29), userList.get(17));
            addRelationToArr(userList.get(29), userList.get(13));
            addRelationToArr(userList.get(29), userList.get(5));
            addRelationToArr(userList.get(29), userList.get(22));
            addRelationToArr(userList.get(29), userList.get(4));
            addRelationToArr(userList.get(29), userList.get(18));
            addRelationToArr(userList.get(29), userList.get(1));
            addRelationToArr(userList.get(29), userList.get(2));
            addRelationToArr(userList.get(29), userList.get(3));
            addRelationToArr(userList.get(29), userList.get(12));
            addRelationToArr(userList.get(29), userList.get(27));
            addRelationToArr(userList.get(29), userList.get(21));
            addRelationToArr(userList.get(29), userList.get(23));
            addRelationToArr(userList.get(29), userList.get(19));
            addRelationToArr(userList.get(29), userList.get(14));
            addRelationToArr(userList.get(29), userList.get(6));
            addRelationToArr(userList.get(29), userList.get(11));
            addRelationToArr(userList.get(29), userList.get(20));
            addRelationToArr(userList.get(29), userList.get(24));
            addRelationToArr(userList.get(29), userList.get(0));
        }
        return userRelationArr;
    }
}
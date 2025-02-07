package org.ahmeteminsaglik.bookapp;

import org.ahmeteminsaglik.fileoperation.business.concretes.FileOperationFacade;
import org.ahmeteminsaglik.fileoperation.dataaccess.concretes.ReadFileManagement;
import org.ahmeteminsaglik.fileoperation.dataaccess.concretes.WriteFileManagement;
import org.ahmeteminsaglik.fileoperation.entities.concretes.FileFundamental;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class ClearIndexTxtFileMain {
    public static void main(String[] args) {
//        InputStream inStream = ClearIndexTxtFileMain.class.getClassLoader().getResourceAsStream("userAgents.txt");
//        System.out.println("gelen deger : intStream : " + inStream);
//        System.exit(0);
        FileFundamental fileFund = getFile("indexes");
        FileOperationFacade fofIndexes = new FileOperationFacade(new WriteFileManagement(fileFund), new ReadFileManagement(fileFund));
        fofIndexes.read();
        List<String> list = fofIndexes.getReadDataList();
//        System.out.println("read data : (size)" + list.size());

        HashSet<String> hashSet = new HashSet<>(list);
//        System.out.println("hashsetSize : " + hashSet.size());

        list = new ArrayList<>(hashSet);
//        System.out.println(list);
        Collections.sort(list);
//        System.out.println(list);

        FileFundamental newFileFund = getFile("completed-indexes");

        FileOperationFacade fofCompletedIndexes = new FileOperationFacade(new WriteFileManagement(newFileFund), new ReadFileManagement(newFileFund));
        fofCompletedIndexes.write(list);

    }

    private static FileFundamental getFile(String fileName) {
        FileFundamental fileFund = new FileFundamental();
        fileFund.setFileName(fileName);
        fileFund.setPath("src\\main\\resources\\");
        fileFund.setFileExtension("txt");
        return fileFund;
    }
}

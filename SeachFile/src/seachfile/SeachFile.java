/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seachfile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Huong Giang
 */
public class SeachFile {

    void listFolder(File dir, String x) throws IOException {
        File[] subDirs = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        //     System.out.println("\nDirectory of " + dir.getAbsolutePath());
        BufferedWriter bw = null;
        FileWriter fw = null;
        File fn = new File("C:\\Users\\Huong Giang\\Desktop\\KetQua.txt");

        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile() && file.isHidden() == false) {
                //       System.out.println(file.getName());

                //           BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(file), "UTF8"));
                String str;
                String s;

                while ((str = br.readLine()) != null) {
                    s = str.toLowerCase();
                    //              System.out.println(str);
                    if (s.indexOf(x) == -1) {
                        continue;
                    } else {
                        System.out.println(file.getAbsolutePath());
                        if (!fn.exists()) {
                            fn.createNewFile();
                        }
                        fw = new FileWriter(fn.getAbsoluteFile(), true);
                        bw = new BufferedWriter(fw);
                        bw.write(dir.getAbsolutePath() + "\\" + file.getName() + "\r\n");
                        break;
                    }
                }
            }
            // true = append file

            if (bw != null) {
                bw.close();
            }

            if (fw != null) {
                fw.close();
            }
        }
        for (File folder : subDirs) {
            listFolder(folder, x);
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in, "UTF-8");
        System.out.println("Nhap thu muc ban muon tim kim (D:\\ABC):");
        String a = sc.nextLine();
        System.out.print("Nhap tu can tim: ");
        String m = sc.nextLine();
        String x = m.toLowerCase();
        //      System.out.println(x);
        new SeachFile().listFolder(new File(a), x);
    }
}

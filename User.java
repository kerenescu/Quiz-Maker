package com.example.project;

import java.io.*;
import java.util.Arrays;
import java.util.*;
import java.lang.*;
import java.io.InputStreamReader;

public class User {
    public static int id = 0;
    public static void addUser(String username, String password) {

        id++;
        try (FileWriter fw = new FileWriter("src/main/java/com/example/project/UserList.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            out.println(id + "," + username + "," + password);
        }
        catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }
    public static boolean checkExistingUser(String username) {
        try (BufferedReader br = new BufferedReader(new FileReader(("src/main/java/com/example/project/UserList.txt")))) {
            String line;
            String comma = ",";
            String check;
            while ((line = br.readLine()) != null) {
                check = line.split(comma)[1];
                if (check.equals(username)) {
                    return true;
                }
            }
            return false;

        }
        catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
        return false;
    }
    public static void create(String[] args) {

        if (args.length == 1) {
            System.out.println("{ 'status' : 'error', 'message' : 'Please provide username'}");
        }
        else if (args.length == 2) {
            System.out.println("{ 'status' : 'error', 'message' : 'Please provide password'}");
        }
        else {
            String usernameData;
            String delims = "'";
            usernameData = (args[1].split(delims))[1];
            if (checkExistingUser(usernameData)) {
                System.out.println("{ 'status' : 'error', 'message' : 'User already exists'}");
            }
            else {
                String passwordData;
                //usernameData = (args[1].split(delims))[1].substring(1, (args[1].split(delims))[1].length() - 1); " "
                //usernameData = (args[1].split(delims))[1];
                passwordData = (args[2].split(delims))[1];
                addUser(usernameData, passwordData);
                System.out.println("{ 'status' : 'ok', 'message' : 'User created successfully'}");
            }
        }
    }
    public static boolean userAuthentication(String[] args){

        String usernameData;
        String passwordData;
        if(args.length <=2) {
            System.out.println("{ 'status' : 'error', 'message' : 'You need to be authenticated'}");
            return false;
        }

        String delims = "'";
        usernameData = (args[1].split(delims))[1];
        passwordData = (args[2].split(delims))[1];

        if(!checkExistingUser(usernameData)){
            System.out.println("{ 'status' : 'error', 'message' : 'Login failed'}");
            return false;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(("src/main/java/com/example/project/UserList.txt")))) {
            String line;
            String comma = ",";
            String check;
            while ((line = br.readLine()) != null) {
                check = line.split(comma)[1];
                if (check.equals(usernameData)) {
                    check = line.split(comma)[2];
                    if(!(check.equals(passwordData))) {
                        System.out.println("{ 'status' : 'error', 'message' : 'Login failed'}");
                        return false;
                    }
                }
            }
        }
        catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
        return true;
    }
}

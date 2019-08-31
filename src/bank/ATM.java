package bank;
import java.util.*;
import java.io.*;

public class ATM {
    static Scanner uInput = new Scanner(System.in);
    static ArrayList<Account> accounts = new ArrayList<Account>();

    public static void main(String[] args) {
        try {

            FileInputStream fis = new FileInputStream("accounts.heshan");
            ObjectInputStream ois = new ObjectInputStream(fis);
            accounts = (ArrayList<Account>) ois.readObject();
            ois.close();

            homeMenu();

            FileOutputStream fos = new FileOutputStream("accounts.heshan");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(accounts);
            oos.close();

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void homeMenu(){

        System.out.println("1.Create account");
        System.out.println("2.Login");
        System.out.println("3.Exit");
        System.out.print("Enter :");
        int command = uInput.nextInt();
        if (command == 1) {
            accounts.add(createAcc());
            logIn(accounts);
        } else if (command == 2) {
            logIn(accounts);
        }else if(command == 3){
            return;
        } else {
            System.out.println("Invalid command");
        }
    }

    public static Account createAcc() {
        System.out.println("--------------Create Account-----------");
        System.out.print("Account Number:");
        String accNo = uInput.next();
        System.out.print("Pin:");
        int pin = uInput.nextInt();
        System.out.print("Name:");
        String name = uInput.next();
        Account a = new Account(accNo, name, pin, 0);
        return a;
    }

    public static void logIn(ArrayList<Account> accounts) {
        int x = 0;
        while (x < 3) {
            System.out.println("-----------Log In----------");
            System.out.print("Account number:");
            String accNo = uInput.next();
            System.out.print("Pin:");
            int pin = uInput.nextInt();
            for (Account a : accounts) {
                if (a.isLogged(accNo, pin)) {
                    System.out.println("------------Successfully logged in welcome " + a.getName() + "---------------");
                    mainMenu(a);
                    return;
                }
            }
            x += 1;
            System.out.println("Invalid login credentials.You have " + (3 - x) + " attempts left.");
        }
    }

    public static void mainMenu(Account a) {
        System.out.println("---------------------------");
        System.out.println("1.Check balance");
        System.out.println("2.Deposit");
        System.out.println("3.Withdraw");
        System.out.println("4.Transaction");
        System.out.println("5.Logout");
        System.out.print("Enter command:");
        int command = uInput.nextInt();
        if (command == 1) {
            a.getBalance();
            mainMenu(a);
        } else if (command == 2) {
            System.out.println("-----------Deposit-----------");
            System.out.print("Enter D.Amount:");
            double amount = uInput.nextDouble();
            a.deposit(amount);
            mainMenu(a);
        }else if(command ==  3){
            System.out.println("-----------Withdraw-----------");
            a.getBalance();
            System.out.print("Enter W.Amount:");
            double amount = uInput.nextDouble();
            a.withdraw(amount);
            mainMenu(a);
        }else if(command == 4){
            System.out.println("----------Transaction---------");
            a.getBalance();
            System.out.print("Enter account number of the receiver:");
            String aNum = uInput.next();
            System.out.print("Enter T.Amount:");
            double amount = uInput.nextDouble();
            if (amount>a.getBal()){
                System.out.println("Insufficient balance");
                mainMenu(a);
            }
            boolean isAcc = false;
            for (Account aTrans: accounts) {
                if(aTrans.getAcNumber().equals(aNum)){
                    aTrans.deposit(amount);
                    a.withdraw(amount);
                    System.out.println("Successfully Transacted LKR,"+amount+" to " + aTrans.getName());
                    isAcc = true;
                }
            }
            if(!isAcc){
                System.out.println("Invalid Account Number");
            }
            mainMenu(a);
        }else if(command == 5){
            homeMenu();
        }else{
            System.out.println("-----------Invalid command try again----------------");
            mainMenu(a);
        }
    }
}

package bank;

import java.io.Serializable;

public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    private String accNo;
    private String name;
    private double balance;
    private int pin;

    Account(String accNo, String name, int pin, double balance) {
        this.accNo = accNo;
        this.name = name;
        this.pin = pin;
        this.balance = balance;
    }

    public String getName(){
        return this.name;
    }
    public String getAcNumber(){
        return this.accNo;
    }

    public boolean isLogged(String accNo, int pin) {
        return this.pin == pin && accNo.equals(this.accNo);
    }

    public void getBalance(){
        System.out.println("---------------------------");
        System.out.println("LKR,Av.Bal:"+balance);
        System.out.println("---------------------------");
    }

    public void deposit(double amount){
        System.out.println("---------------------------");
        if(amount>0){
            this.balance += amount;
            System.out.println("Cash Deposit");
            System.out.println("LKR,D.Amount:"+amount);
            System.out.println("LKR,New.Bal:"+this.balance);
        }else{
            System.out.println("invalid amount try again");
        }
        System.out.println("---------------------------");
    }

    public void withdraw(double amount){
        System.out.println("---------------------------");
        if(amount>0 && amount<this.balance){
            this.balance -= amount;
            System.out.println("Cash Withdraw");
            System.out.println("LKR,W.Amount:"+amount);
            System.out.println("LKR,New.Bal:"+this.balance);
        }else{
            System.out.println("invalid amount try again");
        }
        System.out.println("---------------------------");
    }

    public double getBal(){
        return this.balance;
    }
}

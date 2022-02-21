package Bankaccountdemo;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.Scanner;


public class BankAccountDemo {
    public static boolean isLogin = false;

    public static void main(String[] args) throws Exception {
        int option = 0;
        Scanner scanner = new Scanner(System.in);
        while (option == 0) {
            System.out.println("==== SELECT AN OPTION ====\n");
            System.out.println("1. Create new account");
            System.out.println("2. Sign In\n");

            while (option < 1 || option > 2) {
                System.out.print(" Type your choice: ");
                option = scanner.nextInt();
            }
        }


        switch (option) {
            case 1:
                System.out.println("\n\n==== CREATE NEW ACCOUNT ====\n");
                System.out.print("Enter first name: ");
                String firstName = scanner.next().trim();
                System.out.print("Enter last name: ");
                String lastName = scanner.next().trim();

                Account account = new Account(firstName, lastName);
                account.register();
                break;

            case 2:
                while (isLogin = false) {
                    System.out.println("\n\n==== SIGN IN ====\n");
                    System.out.print("Enter your card number: ");
                    String pincode = scanner.next();
                    Operation operation = new Operation(cardnumber, pincode);

                    try {
                        Connection c = DataBase.connection();
                        Statement stmt4 = c.createStatement();
                        String sql4 = "SELECT * FROM Card WHERE card_numder = '" + cardnumber + "' AND pincode = '" + pincode + "'";
                        ResultSet rs4 = stmt4.executeQuery(sql4);

                        if (rs4.next()) {
                            isLogin = true;

                            System.out.println("\n\n==== LOGIN SUCCESS ====\n");
                            System.out.println("--- Enter an option---\n");
                            System.out.println("1. Balance");
                            System.out.println("2. Deposit");
                            System.out.println("3. Send to other person");

                            int option_user = 0;

                            while (option_user < 1 || option_user > 3) {
                                System.out.print("\nType your choise: ");

                                option_user = scanner.nextInt();
                            }

                            int balance = 0;

                            switch (option_user) {
                                case 1:
                                    System.out.println("\n\n==== SHOW BALANCE ====\n");
                                    balance = operation.showBalance(cardnumber);
                                    System.out.println(balance + "$");
                                    break;

                                case 2:
                                    System.out.println("\n\n==== MAKE DEPOSIT ====\n");
                                    int amount = 0;
                                    while (amount <= 0) {
                                        System.out.print("Type amount: ");
                                    }
                                    amount = scanner.nextInt();
                            }
                            operation.deposite(amount, cardnumber);
                            balance = operation.showBalance(cardnumber);
                            System.out.println("\nCurrent balance is + " + balance + "$");
                            break;

                            case 3:
                                System.out.println("\n\n==== SEND MONEY TO OTHER CARD ====\n");
                                System.out.print("Enter number of other client: ");
                                String number_other = scanner.next();
                                int amount_other = 0;
                                while (amount_other <= 0) {
                                    System.out.print("Enter amount for other client: ");
                                    amount_other = scanner.nextInt();
                                }
                                operation.sendMoneyToOther(amount_other, number_other, cardnumber);
                                System.out.println("\nYou sent" + amount_other + "$ to" + number_other);
                                break;
                            default:
                                break;
                        }

                    } else{
                        System.out.println("\nLogin fail");
                    }

                } catch(Exception e){
                System.out.println(e);
            }
        }
        break;

        default:

        break;

    }
}
}





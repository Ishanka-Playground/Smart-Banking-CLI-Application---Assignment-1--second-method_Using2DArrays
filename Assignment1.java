import java.util.Arrays;
import java.util.Scanner;

public class Assignment1 {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        final String CLEAR = "\033[H\033[2J";
        final String COLOR_BLUE_BOLD = "\033[34;1m";
        final String COLOR_RED_BOLD = "\033[31;1m";
        final String COLOR_GREEN_BOLD = "\033[33;1m";
        final String RESET = "\033[0m";

        final String DASHBOARD = "Welcome to Smart Banking App";
        final String ADD_ACCOUNT = "Open New Account";
        final String DEPOSIT_MONEY = "Deposit Money";
        final String WITHDRAW_MONEY = "Withdraw Money";
        final String TRANSFER_MONEY = "Transfer Money";
        final String CHECK_AC_BALANCE = "Check Account Balance";
        final String DROP_EXISTING_AC = "Drop Existing Account";

        final String ERROR_MSG = String.format("\t%s%s%s\n", COLOR_RED_BOLD, "%s", RESET);
        final String SUCCESS_MSG = String.format("\t%s%s%s\n", COLOR_GREEN_BOLD, "%s", RESET);

        String screen = DASHBOARD;

        // Arrays 
        String [][] accountDetailsArray = new String[0][3]; // {{id,name,balance},{},{}}
  

        //
        do {
    
            final String APP_TITLE = String.format("%s%s%s",
            COLOR_BLUE_BOLD, screen, RESET);

            System.out.println(CLEAR);
            System.out.println("\t" + APP_TITLE + "\n");

            ///
            switch (screen){

                case DASHBOARD:

                System.out.println("\t[1]. Open New Account");
                System.out.println("\t[2]. Deposit Money");
                System.out.println("\t[3]. Withdraw Money");
                System.out.println("\t[4]. Transfer Money");
                System.out.println("\t[5]. Check Account Balance");
                System.out.println("\t[6]. Drop Existing Account");
                System.out.println("\t[7]. Exit\n");
                System.out.print("\tEnter an option to continue: ");
                int option = SCANNER.nextInt();
                SCANNER.nextLine();

                    switch (option){
                        case 1: screen = ADD_ACCOUNT; break;
                        case 2: screen = DEPOSIT_MONEY; break;
                        case 3: screen = WITHDRAW_MONEY; break;
                        case 4: screen = TRANSFER_MONEY; break;
                        case 5: screen = CHECK_AC_BALANCE; break;
                        case 6: screen = DROP_EXISTING_AC; break;
                        case 7: System.out.println(CLEAR); System.exit(0);
                        default: continue;
                    }
                    break;


                case ADD_ACCOUNT:
                
                    boolean valid;
                    String name;
                    String id = "";

                    do {

                        // ID
                        valid = true;
                        
                        if (accountDetailsArray.length !=0) {
                            System.out.printf("\tID: SDB-%05d ",accountDetailsArray.length); // automatic
                            id = String.format("SDB-%05d", accountDetailsArray.length);
                        }  
                        else {
                            System.out.printf("\tID: SDB-%05d ",1);
                            id = String.format("SDB-%05d", 1);

                        }

                        System.out.println();

                        // Name
                        System.out.print("\tName :");
                        name = SCANNER.nextLine().strip();

                        if (name.isBlank()){
                            System.out.printf(ERROR_MSG, "Name can't be empty");
                            valid = false;
                            continue;
                        }
                        for (int i = 0; i < name.length(); i++) {
                            if (!(Character.isLetter(name.charAt(i)) || 
                                Character.isSpaceChar(name.charAt(i))) ) {
                                System.out.printf(ERROR_MSG, "Invalid name");
                                valid = false;
                                break;
                            }
                        }

                    } while (!valid);
    
                
                    //Initial Payment
                    double initialDeposit;
                    do {
                        valid = true;
                        System.out.print("\tInitial Deposit : Rs ");
                        initialDeposit = SCANNER.nextDouble();
                        SCANNER.nextLine();
                            if (initialDeposit < 5000){
                                System.out.printf(ERROR_MSG, "Insuficient fund");
                                valid = false;
                                continue;
                            }             
                    } while (!valid);

                    
                    String[][] newDetailsArray = new String[accountDetailsArray.length + 1][3];


                    for (int i = 0; i < accountDetailsArray.length; i++) {
                        newDetailsArray[i][0] = accountDetailsArray[i][0];
                        newDetailsArray[i][1] = accountDetailsArray[i][1];
                        newDetailsArray[i][2] = accountDetailsArray[i][2];
                    }

                    newDetailsArray[newDetailsArray.length-1][0]= String.format("SDB-%05d", newDetailsArray.length);
                    newDetailsArray[newDetailsArray.length-1][1] = name;
                    newDetailsArray[newDetailsArray.length-1][2]= ""+ initialDeposit;

                    accountDetailsArray = newDetailsArray;

                    for (int i = 0; i < accountDetailsArray.length; i++) {
                        System.out.println(Arrays.toString(accountDetailsArray[i]));
                    }


                    System.out.println();
                    System.out.printf(SUCCESS_MSG, 
                        String.format("%s:%s has been saved successfully", id, name));
                    System.out.print("\tDo you want to continue adding (Y/n)? ");
                    if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = DASHBOARD;
                    break;


                // case DEPOSIT_MONEY:

                //     valid = true;
                //     String acNumber;
                //     int acNumberOnly=0;

                //     //Enter Account number
                //     ////
                //     loop1:
                //     do {
                //         System.out.print("\tEnter A/C number :");
                //         acNumber = SCANNER.nextLine().strip();
                        

                //         if (acNumber.isBlank()){
                //             System.out.printf(ERROR_MSG, "A/C number can't be empty");
                //             valid = false;
                //             continue;
                //         }

                //         if (!acNumber.startsWith("SDB-")|| acNumber.length()<5){
                //             System.out.printf(ERROR_MSG, "Invalid format");
                //             valid = false;
                //             continue;
                //         }

                //         for (int i = 4; i < acNumber.length(); i++) {
                //             if (!Character.isDigit(acNumber.charAt(i)) ){
                //             System.out.printf(ERROR_MSG, "Invalid format");
                //             valid = false;
                //             continue loop1;
                //             }
                //         }
                
                //         acNumberOnly = Integer.valueOf(acNumber.substring(4, acNumber.length()).strip());

                //         if (acNumberOnly ==0){
                //             System.out.printf(ERROR_MSG, "Account number can't be Zero");
                //             valid = false;
                //             continue;
                //         }

                //         if (acNumberOnly >idArray.length){
                //             System.out.printf(ERROR_MSG, "Account number is not found");
                //             valid = false;
                //             continue;
                //         }
                //     ////    
                //     } while (!valid);

                //     // Current Balance

                //     System.out.printf("\tCurrent Balance : Rs %,.2f\n" ,balanceArray[acNumberOnly-1]);


                //     // Deposit Amount

                //     double depositAmount=0;
                //     /////
                //     do {
                //         valid = true;
                //         System.out.print("\tDeposit Amount :");
                //         depositAmount = SCANNER.nextDouble();
                //         SCANNER.nextLine();

                //         if(depositAmount < 500){
                //             System.out.printf(ERROR_MSG, "In sufficint fund");
                //             valid = false;
                //             continue;                            
                //         }
                //     /////   
                //     } while (!valid);

                //     // New Balance

                //     balanceArray[acNumberOnly-1] += depositAmount;

                //     System.out.printf("\tNew Balance : Rs %,.2f\n" ,balanceArray[acNumberOnly-1]);

                //     System.out.println();
                //     System.out.printf(SUCCESS_MSG, 
                //         String.format("%s has been deposit successfully", depositAmount));
                //     System.out.print("\tDo you want to continue adding (Y/n)? ");
                //     if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue;
                //     screen = DASHBOARD;
                //     break;


                // case WITHDRAW_MONEY:

                //     valid = true;
                //     acNumberOnly =0;


                //     //Enter Account number
                //     ////
                //     loop2:
                //     do {
                //         System.out.print("\tEnter A/C number :");
                //         acNumber = SCANNER.nextLine().strip();
                        

                //         if (acNumber.isBlank()){
                //             System.out.printf(ERROR_MSG, "A/C number can't be empty");
                //             valid = false;
                //             continue;
                //         }

                //         if (!acNumber.startsWith("SDB-")|| acNumber.length()<5){
                //             System.out.printf(ERROR_MSG, "Invalid format");
                //             valid = false;
                //             continue;
                //         }

                //         for (int i = 4; i < acNumber.length(); i++) {
                //             if (!Character.isDigit(acNumber.charAt(i)) ){
                //             System.out.printf(ERROR_MSG, "Invalid format");
                //             valid = false;
                //             continue loop2;
                //             }
                //         }
                
                //         acNumberOnly = Integer.valueOf(acNumber.substring(4, acNumber.length()).strip());

                //         if (acNumberOnly ==0){
                //             System.out.printf(ERROR_MSG, "Account number can't be Zero");
                //             valid = false;
                //             continue;
                //         }

                //         if (acNumberOnly >idArray.length){
                //             System.out.printf(ERROR_MSG, "Account number is not found");
                //             valid = false;
                //             continue;
                //         }
                //     ////    
                //     } while (!valid);

                //     // Current Balance

                //     System.out.printf("\tCurrent Balance : Rs %,.2f\n" ,balanceArray[acNumberOnly-1]);


                //     // withdraw Amount

                //     double withdrawAmount = 0;
                //     double newBalance= balanceArray[acNumberOnly-1];
                    
                //     do {
                //         valid = true;
                //         System.out.print("\tWithdraw Amount :");
                //         withdrawAmount = SCANNER.nextDouble();
                //         SCANNER.nextLine();

                //         if(withdrawAmount < 100){
                //             System.out.printf(ERROR_MSG, " Minimum withdraw is Rs 100");
                //             valid = false;
                //             continue;                            
                //         }

                //         // new balance
                //         newBalance = balanceArray[acNumberOnly-1] - withdrawAmount;

                //         if (newBalance <500){
                //             System.out.printf(ERROR_MSG, " Balance is insufficient");
                //             valid = false;
                //             continue;
                //         } 
                      
                //     } while (!valid);


                //     balanceArray[acNumberOnly-1] = newBalance;


                //     System.out.printf("\tNew Balance : Rs %,.2f\n" ,newBalance);

                //     System.out.println();
                //     System.out.printf(SUCCESS_MSG, 
                //         String.format("%s has been withdrawn successfully", withdrawAmount));
                //     System.out.print("\tDo you want to continue adding (Y/n)? ");
                //     if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue;
                //     screen = DASHBOARD;
                //     break;







            ///
            }

        //  
        } while (true);



        
    }
}
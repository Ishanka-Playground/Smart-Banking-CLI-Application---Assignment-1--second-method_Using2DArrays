import java.util.Arrays;
import java.util.Scanner;

public class Assignment1 {

    private static final Scanner SCANNER = new Scanner(System.in);



    final static String CLEAR = "\033[H\033[2J";
    final static String COLOR_BLUE_BOLD = "\033[34;1m";
    final static String COLOR_RED_BOLD = "\033[31;1m";
    final static String COLOR_GREEN_BOLD = "\033[33;1m";
    final static String RESET = "\033[0m";

    final static String DASHBOARD = "Welcome to Smart Banking App";
    final static String ADD_ACCOUNT = "Open New Account";
    final static String DEPOSIT_MONEY = "Deposit Money";
    final static String WITHDRAW_MONEY = "Withdraw Money";
    final static String TRANSFER_MONEY = "Transfer Money";
    final static String CHECK_AC_BALANCE = "Check Account Balance";
    final static String DROP_EXISTING_AC = "Drop Existing Account";

    final static String ERROR_MSG = String.format("\t%s%s%s\n", COLOR_RED_BOLD, "%s", RESET);
    final static String SUCCESS_MSG = String.format("\t%s%s%s\n", COLOR_GREEN_BOLD, "%s", RESET);

    // Arrays 
    static String [][] accountDetailsArray = new String[0][3]; // {{id,name,balance},{},{}}

    public static void main(String[] args) {

        String screen = DASHBOARD;

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
                            System.out.printf("\tID: SDB-%05d ",accountDetailsArray.length+1); // automatic
                            id = String.format("SDB-%05d", accountDetailsArray.length+1);
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

                            newDetailsArray[i][0]= accountDetailsArray[i][0];
                            newDetailsArray[i][1]= accountDetailsArray[i][1];
                            newDetailsArray[i][2]= accountDetailsArray[i][2];
                    }
                     
                    newDetailsArray[newDetailsArray.length-1][0]= String.format("SDB-%05d", newDetailsArray.length);
                    newDetailsArray[newDetailsArray.length-1][1] = name;
                    newDetailsArray[newDetailsArray.length-1][2]= initialDeposit+"";

                    accountDetailsArray = newDetailsArray;


                    System.out.println();
                    System.out.printf(SUCCESS_MSG, 
                        String.format("%s:%s has been saved successfully", id, name));
                    System.out.print("\tDo you want to continue adding (Y/n)? ");
                    if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = DASHBOARD;
                    break;


                case DEPOSIT_MONEY:

                    int acNumberOnly= idValidation("Enter A/C Number: ");

                    // Current Balance

                    System.out.printf("\tCurrent Balance : Rs %,.2f\n" ,Double.parseDouble(accountDetailsArray[acNumberOnly-1][2]) );


                    // Deposit Amount

                    double depositAmount=0;
                    /////
                    do {
                        valid = true;
                        System.out.print("\tDeposit Amount :");
                        depositAmount = SCANNER.nextDouble();
                        SCANNER.nextLine();

                        if(depositAmount < 500){
                            System.out.printf(ERROR_MSG, "In sufficint fund");
                            valid = false;
                            continue;                            
                        }
                    /////   
                    } while (!valid);

                    // New Balance

                    accountDetailsArray[acNumberOnly-1][2] = depositAmount+ Double.parseDouble(accountDetailsArray[acNumberOnly-1][2])+"";

                    System.out.printf("\tNew Balance : Rs %,.2f\n" ,Double.parseDouble(accountDetailsArray[acNumberOnly-1][2]) );

                    System.out.println();
                    System.out.printf(SUCCESS_MSG, 
                        String.format("%s has been deposit successfully", depositAmount));
                    System.out.print("\tDo you want to continue adding (Y/n)? ");
                    if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = DASHBOARD;
                    break;


                case WITHDRAW_MONEY:

                    // ID validation

                    acNumberOnly = idValidation("Enter Account Number : ");


                    // Current Balance

                    System.out.printf("\tCurrent Balance : Rs %,.2f\n" ,Double.parseDouble(accountDetailsArray[acNumberOnly-1][2]) );


                    // withdraw Amount

                    double withdrawAmount = withdrwAndTransfer(acNumberOnly, "Withdraw");


                    accountDetailsArray[acNumberOnly-1][2]  = Double.parseDouble(accountDetailsArray[acNumberOnly-1][2]) - withdrawAmount+ "";



                    System.out.printf("\tNew Balance : Rs %,.2f\n" ,Double.parseDouble(accountDetailsArray[acNumberOnly-1][2]));

                    System.out.println();
                    System.out.printf(SUCCESS_MSG, 
                    String.format("%s has been withdrawn successfully", withdrawAmount));
                    System.out.print("\tDo you want to continue adding (Y/n)? ");
                    if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = DASHBOARD;
                    break;


                case TRANSFER_MONEY :
                    
                    int fromAcNumber = idValidation("Enter From A/C Number: ");
                    System.out.printf( "\tFrom A/C Name : %s\n\n",accountDetailsArray[fromAcNumber-1][1]) ;
                    int toAcNumber = idValidation("Enter To A/C Number: ");
                    System.out.printf( "\tFrom A/C Name : %s\n\n",accountDetailsArray[toAcNumber-1][1]) ;

                    System.out.printf("\tFrom A/C Balance : Rs %,.2f\n" ,Double.parseDouble(accountDetailsArray[fromAcNumber-1][2]) );
                    System.out.printf("\tTo A/C Balance : Rs %,.2f\n\n" ,Double.parseDouble(accountDetailsArray[toAcNumber-1][2]) );

                    double transferAmount = withdrwAndTransfer(fromAcNumber, "Enter");

                    accountDetailsArray[fromAcNumber-1][2]  = Double.parseDouble(accountDetailsArray[fromAcNumber-1][2])-transferAmount*1.02 + "";
                    accountDetailsArray[toAcNumber-1][2]  = Double.parseDouble(accountDetailsArray[toAcNumber-1][2])+ transferAmount + "";

                    System.out.printf("\tNew From A/C Balance : Rs %,.2f\n" ,Double.parseDouble(accountDetailsArray[fromAcNumber-1][2]) );
                    System.out.printf("\tNew To A/C Balance : Rs %,.2f\n\n" ,Double.parseDouble(accountDetailsArray[toAcNumber-1][2]) );


                    System.out.println();
                    System.out.printf(SUCCESS_MSG, 
                    String.format("%s has been transfered successfully", transferAmount));
                    System.out.print("\tDo you want to continue adding (Y/n)? ");
                    if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = DASHBOARD;
                    break;




                case CHECK_AC_BALANCE :

                    int checkAccount = idValidation("Enter A/C Number: ");
                    System.out.println();
                    System.out.printf("\tA/C Holder Name: %s\n",accountDetailsArray[checkAccount-1][1]);
                    System.out.printf("\tCurrent Balance: %s\n",accountDetailsArray[checkAccount-1][2]);




                case DROP_EXISTING_AC :

                    int deleteAc = idValidation("Enter A/C Number: ");
                    System.out.println();
                    System.out.printf("\tA/C Holder Name: %s\n",accountDetailsArray[deleteAc-1][1]);
                    System.out.printf("\tCurrent Balance: %s\n",accountDetailsArray[deleteAc-1][2]);

                    
                    String deletedAccount = accountDetailsArray[deleteAc-1][0];


                    System.out.print("\tDo you want to delete (Y/n)? ");
                    if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) {

                        String [][] deleteArray = new String[accountDetailsArray.length-1][];

                        for (int i = 0, j=0; i < deleteArray.length; i++ ,j++) {
                            if (i == deleteAc-1) {
                                j++;
                                continue;
                            }

                            deleteArray[i][0]= accountDetailsArray[j][0];
                            deleteArray[i][1]= accountDetailsArray[j][1];
                            deleteArray[i][2]= accountDetailsArray[j][2];
                            
                        }

                        accountDetailsArray = deleteArray;
                        
                    }else {
                        screen = DASHBOARD;
                        break;
                    }

                    System.out.println();
                    System.out.printf(SUCCESS_MSG, 
                    String.format("%s has been deleted successfully", deletedAccount));
                    System.out.print("\tDo you want to continue adding (Y/n)? ");
                    if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = DASHBOARD;
                    break;

            }
  
        } while (true);     
    }




    public static int idValidation(String value) {

        boolean valid;
        String acNumber;
        int acNumberOnly = 0;

        loop1:
        do {

            valid =true;
        
            System.out.printf("\t%s",value);
            acNumber = SCANNER.nextLine().strip();
            

            if (acNumber.isBlank()){
                System.out.printf(ERROR_MSG, "A/C number can't be empty");
                valid = false;
                continue;
            }

            if (!acNumber.startsWith("SDB-")|| acNumber.length()<5){
                System.out.printf(ERROR_MSG, "Invalid format");
                valid = false;
                continue;
            }

            for (int i = 4; i < acNumber.length(); i++) {
                if (!Character.isDigit(acNumber.charAt(i)) ){
                System.out.printf(ERROR_MSG, "Invalid format");
                valid = false;
                continue loop1;
                }
            }
    
            acNumberOnly = Integer.valueOf(acNumber.substring(4, acNumber.length()).strip());

            if (acNumberOnly ==0){
                System.out.printf(ERROR_MSG, "Account number can't be Zero");
                valid = false;
                continue;
            }

            if (acNumberOnly >accountDetailsArray.length){
                System.out.printf(ERROR_MSG, "Account number is not found");
                valid = false;
                continue;
            }

        ////    
        } while (!valid);

        return acNumberOnly;
        
    }



    
    public static double withdrwAndTransfer (int acNumberIndex , String test) {

        double amount = 0;
        double newBalance= Double.parseDouble(accountDetailsArray[acNumberIndex-1][2]) ;
        boolean valid;
                    
        do {
            valid = true;
            System.out.printf("\t%s Amount :",test);
            amount = SCANNER.nextDouble();
            SCANNER.nextLine();

            if(amount < 100){
                System.out.printf(ERROR_MSG, " Minimum withdraw is Rs 100");
                valid = false;
                continue;                            
            }

            // new balance
            newBalance = Double.parseDouble(accountDetailsArray[acNumberIndex-1][2]) - amount;

            if (newBalance <500){
                System.out.printf(ERROR_MSG, " Balance is insufficient");
                valid = false;
                continue;
            } 
            
        } while (!valid);

        return amount;


    }

}
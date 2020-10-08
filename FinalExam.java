//CISC 3115, MY9, VING HEI FUNG
import java.util.*;
import java.io.*;

public class FinalExam
{
  public static void main(String[] args)throws FileNotFoundException
  {
    //File testcase = new File("myInput.txt"); Used to run a test case for the code
    PrintWriter outFile = new PrintWriter("myOutput.txt");
    boolean not_done = true;
    char choice;
    Scanner input = new Scanner(System.in);
    ArrayList<SavingsAccount> accounts = new ArrayList<SavingsAccount>(50);
    readAccts(accounts);
    printAccts(accounts,outFile);
    do
    {
      printMenu();
      choice = input.next().charAt(0);
      switch(choice)
      {
        case 'Q':
        case 'q':
          printAccts(accounts,outFile);
          outFile.close();
          System.out.println("Terminating....");
          not_done = false;
          break;
        case 'B':
        case 'b':
          getBalance(accounts,input,outFile);
          break;
        case 'D':
        case 'd':
          makeDeposit(accounts,input,outFile);
          break;
        case 'W':
        case 'w':
          makeWithdrawal(accounts,input,outFile);
          break;
        case 'I':
        case 'i':
          addInterest(accounts,input,outFile);
          break;
        default:
          outFile.println();
          outFile.println("Error: " + choice + " - is an invalid selection.");
          outFile.flush();
          break;
      }
    }
    while(not_done);
  }
  public static void readAccts(ArrayList<SavingsAccount> accounts)throws FileNotFoundException
  {
    File myFile = new File("myAccts.txt");
    Scanner input = new Scanner(myFile);
    while(input.hasNext())
    {
      accounts.add(new SavingsAccount(input.nextInt(),input.nextDouble()));
    }
  }
  public static void printAccts(ArrayList<SavingsAccount> accounts, PrintWriter outFile)
  {
    outFile.println();
    outFile.println("\t\t\t Ving Hei Fung, CISC3115, MY9");
    outFile.println("\t\t Ving Hei Fung's Database of Bank Accounts");
    outFile.printf("\t\t\t  %-15s%-15s%n","Account#'s", "Balance");
    for(int i=0;i<accounts.size();i++)
    {
      outFile.printf("\t\t\t  %-15d$%-15.2f%n",accounts.get(i).getAcctNumber(),accounts.get(i).getBalance());
    }
    outFile.flush();
  }
  public static void printMenu()
  {
    System.out.println("Q - quit the program");
    System.out.println("B - get account balance");
    System.out.println("D - make deposit");
    System.out.println("W - make withdrawal");
    System.out.println("I - add interest");
  }
  public static void getBalance(ArrayList<SavingsAccount> accounts, Scanner input, PrintWriter outFile)
  {
    System.out.println("Please enter an account number.");
    int acctNum = input.nextInt();
    try
    {
      int index = findAcct(accounts,acctNum);
      outFile.println();
      outFile.println("Transaction Requested: Balance Inquiry");
      outFile.println("Account Number: " + acctNum);
      outFile.printf("Current Balance: $%.2f%n",accounts.get(index).getBalance());
      outFile.println();
    }
    catch(AccountNotFound e)
    {
      outFile.println("Transaction Requested: Balance Inquiry");
      outFile.println("Account Number: " + e.getAcctNum());
      outFile.println("Error: Account does not exist.");
      outFile.println();
    }
    outFile.flush();
  }
    
  public static void makeDeposit(ArrayList<SavingsAccount> accounts, Scanner input, PrintWriter outFile)
  {
    System.out.println("Please enter an account number.");
    int acctNum = input.nextInt();
    try
    {
      int index = findAcct(accounts,acctNum);
      System.out.println("Please enter an amount: ");
      double oldBal = accounts.get(index).getBalance();
      double amount = input.nextDouble();
      try
      {
        accounts.get(index).makeDeposit(amount);
        outFile.println("Transaction Requested: Deposit");
        outFile.println("Account Number: " + acctNum);
        outFile.printf("Old Balance: $%.2f%n", oldBal);
        outFile.println("Amount to Deposit: $" + amount);
        outFile.printf("New Balance: $%.2f%n", accounts.get(index).getBalance());
        outFile.println();
      }
      catch(NegativeAmountEntered e)
      {
        outFile.println("Transaction Requested: Deposit");
        outFile.println("Account Number: " + acctNum);
        outFile.printf("Error: $%.2f is an invalid amount", e.getAmount());
        outFile.println();
      }
    }
    catch(AccountNotFound e)
    {
      outFile.println("Transaction Requested: Deposit");
      outFile.println("Account Number: " + e.getAcctNum());
      outFile.println("Error: Account does not exist.");
      outFile.println();
    }
    outFile.flush();
  }
  public static void makeWithdrawal(ArrayList<SavingsAccount> accounts, Scanner input, PrintWriter outFile)
  {
    System.out.println("Please enter an account number.");
    int acctNum = input.nextInt();
    try
    {
      int index = findAcct(accounts,acctNum);
      System.out.println("Please enter an amount: ");
      double oldBal = accounts.get(index).getBalance();
      double amount = input.nextDouble();
      try
      {
        outFile.println();
        accounts.get(index).makeWithdrawal(amount);
        outFile.println("Transaction Requested: Withdrawal");
        outFile.println("Account Number: " + acctNum);
        outFile.printf("Old Balance: $%.2f%n", oldBal);
        outFile.println("Amount to Deposit: $" + amount);
        outFile.printf("New Balance: $%.2f%n", accounts.get(index).getBalance());
        outFile.println();
      }
      catch(NegativeAmountEntered e)
      {
        outFile.println("Transaction Requested: Withdrawal");
        outFile.println("Account Number: " + acctNum);
        outFile.printf("Error: $%.2f is an invalid amount", e.getAmount());
        outFile.println();
      }
      catch(InsufficientFunds e)
      {
        outFile.println("Transaction Requested: Withdrawal");
        outFile.println("Account Number: " + acctNum);
        outFile.printf("Error: Insufficient Funds");
        outFile.println();
      }
    }
    catch(AccountNotFound e)
    {
      outFile.println("Transaction Requested: Withdrawal");
      outFile.println("Account Number: " + e.getAcctNum());
      outFile.println("Error: Account does not exist.");
      outFile.println();
    }
    outFile.flush();
  }
  public static void addInterest(ArrayList<SavingsAccount> accounts, Scanner input, PrintWriter outFile)
  {
    System.out.println("Please enter an account number.");
    int acctNum = input.nextInt();
    try
    {
      outFile.println();
      int index = findAcct(accounts,acctNum);
      double oldBal = accounts.get(index).getBalance();
      accounts.get(index).addInterest(0.01);
      outFile.println("Transaction Requested: Add Interest");
      outFile.println("Account Number: " + acctNum);
      outFile.printf("Old Balance: $%.2f%n", oldBal);
      outFile.println("Interest Rate: 0.01%");
      outFile.printf("New Balance: $%.2f%n", accounts.get(index).getBalance());
      outFile.println();
    }
    catch(AccountNotFound e)
    {
      outFile.println("Transaction Requested: Add Interest");
      outFile.println("Account Number: " + e.getAcctNum());
      outFile.println("Error: Account does not exist.");
      outFile.println();
    }
  }
  public static int findAcct(ArrayList<SavingsAccount> accounts,int request)throws AccountNotFound
  {
    for(int i=0;i<accounts.size();i++)
    {
      if(accounts.get(i).getAcctNumber() == request)
        return i;
    }
    throw new AccountNotFound(request);
  }
}
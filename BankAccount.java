import java.io.*;

public abstract class BankAccount
{
  protected int acctNum;
  protected double acctBal;
  
  public BankAccount(int acctNum,double acctBal)
  {
    this.acctNum = acctNum;
    this.acctBal = acctBal;
  }
  public BankAccount(BankAccount other)
  {
    this.acctNum = other.acctNum;
    this.acctBal = other.acctBal;
  }
  abstract double getBalance();
  
  abstract void makeDeposit(double deposit)throws NegativeAmountEntered;
  
  abstract void makeWithdrawal(double withdrawal)throws NegativeAmountEntered, 
    InsufficientFunds;
}
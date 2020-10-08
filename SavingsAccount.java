import java.io.*;

public class SavingsAccount extends BankAccount implements Interest
{
  public SavingsAccount(int acctNum,double acctBal)
  {
    super(acctNum,acctBal);
  }
  public SavingsAccount(SavingsAccount other)
  {
    super(other);
  }
  public int getAcctNumber()
  {
    return acctNum;
  }
  public double getBalance()
  {
    return acctBal;
  }
  public void makeDeposit(double deposit)throws NegativeAmountEntered
  {
    if(deposit < 0)
    {
      throw new NegativeAmountEntered(deposit);
    }
    else
    {
      acctBal += deposit;
    }
  }
  public void makeWithdrawal(double withdrawal)throws NegativeAmountEntered, InsufficientFunds
  {
    if(withdrawal < 0)
      throw new NegativeAmountEntered(withdrawal);
    else if(acctBal - withdrawal < 0)
      throw new InsufficientFunds(withdrawal);
    else
      acctBal -= withdrawal;
  }
  public void addInterest(double rate)
  {
    acctBal+=(acctBal*rate);
  }
}

import java.io.*;

public class AccountNotFound extends Exception
{
  int acctNum;
  
  public AccountNotFound(int acctNum)
  {
    this.acctNum = acctNum;
  }
  public int getAcctNum()
  {
    return acctNum;
  }
}
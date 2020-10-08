import java.io.*;

public class NegativeAmountEntered extends Exception
{
  private double amount;
  
  public NegativeAmountEntered(double amount)
  {
    this.amount = amount;
  }
  public double getAmount()
  {
    return amount;
  }
}
  
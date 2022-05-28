package ja;

public class Executive extends Employee {
    public void raise(Number amount) {
      System.out.println("Executive got raise");
    }
    
    public void raise(java.math.BigDecimal amount) {
      System.out.println("Executive got outlandish raise");
    }
  }
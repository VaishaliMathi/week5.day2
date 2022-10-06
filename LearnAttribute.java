package week5.day2;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class LearnAttribute {
   @Test(priority=1)
	  public  void create() {

	}
  
   @Test(invocationCount = 2,invocationTimeOut = 5000,threadPoolSize = 3)
      public void Edit() {
}
   @Test  
   public void delete() {
	throw new RuntimeException();
}
   @Test(dependsOnMethods = {"walk"})
   public void run() {
   }
   @Test(timeOut = 2000)
   public void walk() {
   
   }
   @Test(dependsOnMethods = {"delete"},alwaysRun = true)
   public void sleep() {
	   
   }
   
   
   
   
   
   }
   
   
   
   
   
   
   


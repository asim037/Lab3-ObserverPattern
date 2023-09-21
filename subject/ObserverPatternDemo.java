/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subject;

/**
 *
 * @author FA20-BSE-037
 */
public class ObserverPatternDemo {
    public static void main(String[] args) {
      Subject subject = new Subject();
      //BinaryObserver binaryob = new BinaryObserver(subject);
      

      HexaObserver Hexaob = new HexaObserver(subject);
      OctalObserver octalob = new OctalObserver(subject);
      BinaryObserver binaryob = new BinaryObserver(subject);

      System.out.println("First state change: 15");	
      subject.setState(15);
      binaryob.detach();
      System.out.println("Second state change: 10");	
      subject.setState(10);
      octalob.detach();
      System.out.println("Third state change: 20");
      subject.setState(20);
      
      
   }
    
}

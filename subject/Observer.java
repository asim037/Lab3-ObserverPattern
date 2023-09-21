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

public abstract class Observer {
   protected Subject subject;
   abstract void update();
   public void detach() {
        subject.detach(this);
    }
}
    


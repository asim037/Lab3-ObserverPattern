/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package decoratorss;

/**
 *
 * @author LAPTOP HOUSE
 */
public class RedShapeDecorator extends ShapeDecorator {

    public RedShapeDecorator(Shape decoratedShape) {
        super(decoratedShape, 2.0); // Set the price for a red border decoration
    }

    @Override
    public void draw() {
        decoratedShape.draw();
        computeCost();
    }

    private void computeCost() {
        System.out.println("Cost: " + getPrice());
    }
}


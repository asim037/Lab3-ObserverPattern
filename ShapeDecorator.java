/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package decoratorss;

/**
 *
 * @author LAPTOP HOUSE
 */
public abstract class ShapeDecorator implements Shape {
    protected Shape decoratedShape;
    private double price;

    public ShapeDecorator(Shape decoratedShape, double price) {
        this.decoratedShape = decoratedShape;
        this.price = price;
    }

    public void draw() {
        decoratedShape.draw();
        computeCost();
    }

    private void computeCost() {
        System.out.println("Cost: " + (decoratedShape.getPrice() + price));
    }

    @Override
    public double getPrice() {
        return decoratedShape.getPrice() + price;
    }
}
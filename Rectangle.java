/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package decoratorss;

/**
 *
 * @author LAPTOP HOUSE
 */

public class Rectangle implements Shape {
    private double price = 10.0; // Set the default price for a rectangle

    @Override
    public void draw() {
        System.out.println("Shape: Rectangle");
    }

    @Override
    public double getPrice() {
        return price;
    }
}
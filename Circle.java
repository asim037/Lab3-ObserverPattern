/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package decoratorss;

/**
 *
 * @author LAPTOP HOUSE
 */
public class Circle implements Shape {
    private double price = 5.0; // Set the default price for a circle

    @Override
    public void draw() {
        System.out.println("Shape: Circle");
    }

    @Override
    public double getPrice() {
        return price;
    }
}
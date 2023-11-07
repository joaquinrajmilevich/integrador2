package com.educa.integrador2;

import com.educa.integrador2.entity.Product;
import com.educa.integrador2.service.ProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class Integrador2Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Integrador2Application.class, args);

		ProductService productService = context.getBean(ProductService.class);

		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("Select an operation:");
			System.out.println("1. Create Product");
			System.out.println("2. Add Stock");
			System.out.println("3. Remove Stock");
			System.out.println("4. Update Price");
			System.out.println("5. View Products");
			System.out.println("0. Exit");

			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
				case 1:
					createProduct(productService, scanner);
					break;
				case 2:
					addStock(productService, scanner);
					break;
				case 3:
					removeStock(productService, scanner);
					break;
				case 4:
					updatePrice(productService, scanner);
					break;
				case 5:
					viewAllProducts(productService); // Call the new option
					break;
				case 0:
					context.close();
					return;
				default:
					System.out.println("Invalid choice. Please try again.");
			}
		}
	}
	private static void createProduct(ProductService productService, Scanner scanner) {
		System.out.print("Enter product name: ");
		String name = scanner.nextLine();

		System.out.print("Enter product description: ");
		String description = scanner.nextLine();

		System.out.print("Enter sales price: ");
		double price = scanner.nextDouble();

		System.out.print("Enter initial stock quantity: ");
		int stock = scanner.nextInt();

		Product product = new Product(name, description, price, stock);
		productService.createProduct(product);
		System.out.println("Product created: " + product);
	}

	private static void addStock(ProductService productService, Scanner scanner) {
		System.out.print("Enter product ID: ");
		Long productId = scanner.nextLong();

		System.out.print("Enter quantity to add: ");
		int quantity = scanner.nextInt();

		Product product = productService.addStock(productId, quantity);
		System.out.println("Stock added: " + product);
	}

	private static void removeStock(ProductService productService, Scanner scanner) {
		System.out.print("Enter product ID: ");
		Long productId = scanner.nextLong();

		System.out.print("Enter quantity to remove: ");
		int quantity = scanner.nextInt();

		Product product = productService.removeStock(productId, quantity);
		System.out.println("Stock removed: " + product);
	}
	private static void updatePrice(ProductService productService, Scanner scanner) {
		System.out.print("Enter product ID: ");
		Long productId = scanner.nextLong();

		System.out.print("Enter updated price: ");
		double newPrice = scanner.nextDouble();

		Product product = productService.updateProductPrice(productId, newPrice);
		System.out.println("Stock removed: " + product);
	}
	private static void viewAllProducts(ProductService productService) {
		List<Product> products = productService.getAllProducts();
		if (products.isEmpty()) {
			System.out.println("No products available.");
		} else {
			System.out.println("List of Products:");
			for (Product product : products) {
				System.out.println(product);
			}
		}
	}
}

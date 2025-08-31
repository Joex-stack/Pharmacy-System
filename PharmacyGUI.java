import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.time.LocalDate;

public class PharmacyGUI extends Application {
    private Mangersys pharmacySystem = new Mangersys();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("ZSC Pharmacy");

        Button btnMakeOrder = new Button("Make Order");
        Button btnAdmin = new Button("Admin");
        Button btnExit = new Button("Exit");

        btnMakeOrder.setOnAction(e -> showMakeOrderPage());
        btnAdmin.setOnAction(e -> showAdminPage());
        btnExit.setOnAction(e -> primaryStage.close());

        VBox mainMenuLayout = new VBox(10, btnMakeOrder, btnAdmin, btnExit);
        mainMenuLayout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        Scene mainMenuScene = new Scene(mainMenuLayout, 300, 200);

        primaryStage.setScene(mainMenuScene);
        primaryStage.show();
    }

    private void showMakeOrderPage() {
        Stage makeOrderStage = new Stage();
        makeOrderStage.setTitle("Make Order");

        Button btnOffline = new Button("Offline Order");
        Button btnOnline = new Button("Online Order");

        btnOffline.setOnAction(e -> makeOrderStage.close());
        btnOnline.setOnAction(e -> makeOrderStage.close());

        VBox makeOrderLayout = new VBox(10, btnOffline, btnOnline);
        makeOrderLayout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        Scene makeOrderScene = new Scene(makeOrderLayout, 300, 200);

        makeOrderStage.setScene(makeOrderScene);
        makeOrderStage.show();
    }

    private void showAdminPage() {
        Stage adminStage = new Stage();
        adminStage.setTitle("Admin");

        Button btnShowOrders = new Button("Show Orders");
        Button btnAddItem = new Button("Add Item");

        btnShowOrders.setOnAction(e -> showOrdersPage());
        btnAddItem.setOnAction(e -> showAddItemPage());

        VBox adminLayout = new VBox(10, btnShowOrders, btnAddItem);
        adminLayout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        Scene adminScene = new Scene(adminLayout, 300, 200);

        adminStage.setScene(adminScene);
        adminStage.show();
    }

    private void showOrdersPage() {
        Stage ordersStage = new Stage();
        ordersStage.setTitle("Orders");

        ListView<String> ordersList = new ListView<>();
        ordersList.getItems().add("Order 1: John Doe - $100");
        ordersList.getItems().add("Order 2: Jane Smith - $50");

        Button btnCloseOrders = new Button("Close");
        btnCloseOrders.setOnAction(e -> ordersStage.close());

        VBox ordersLayout = new VBox(10, ordersList, btnCloseOrders);
        ordersLayout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        Scene ordersScene = new Scene(ordersLayout, 400, 300);

        ordersStage.setScene(ordersScene);
        ordersStage.show();
    }

    private void showAddItemPage() {
        Stage addItemStage = new Stage();
        addItemStage.setTitle("Add Item");

        TextField txtName = new TextField();
        txtName.setPromptText("Item Name");
        TextField txtCompany = new TextField();
        txtCompany.setPromptText("Company Name");
        TextField txtCategory = new TextField();
        txtCategory.setPromptText("Category");
        TextField txtPrice = new TextField();
        txtPrice.setPromptText("Price");
        TextField txtExpiryDate = new TextField();
        txtExpiryDate.setPromptText("Expiry Date (YYYY-MM-DD)");

        Button btnAddDrug = new Button("Add Drug");
        Button btnAddHygiene = new Button("Add Hygiene");
        Button btnClose = new Button("Close");

        btnAddDrug.setOnAction(e -> {
            try {
                String name = txtName.getText();
                String company = txtCompany.getText();
                String category = txtCategory.getText();
                double price = Double.parseDouble(txtPrice.getText());
                LocalDate expiryDate = LocalDate.parse(txtExpiryDate.getText());
                Drugs drug = new Drugs(name, company, category, price, expiryDate, "General");
                pharmacySystem.addtostorage(drug);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        btnAddHygiene.setOnAction(e -> {
            try {
                String name = txtName.getText();
                String company = txtCompany.getText();
                String category = txtCategory.getText();
                double price = Double.parseDouble(txtPrice.getText());
                LocalDate expiryDate = LocalDate.parse(txtExpiryDate.getText());
                Hygiene hygiene = new Hygiene(name, company, category, price, expiryDate, "Skin", true);
                pharmacySystem.addtostorage(hygiene);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        btnClose.setOnAction(e -> addItemStage.close());

        VBox addItemLayout = new VBox(10, txtName, txtCompany, txtCategory, txtPrice, txtExpiryDate, btnAddDrug, btnAddHygiene, btnClose);
        addItemLayout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        Scene addItemScene = new Scene(addItemLayout, 400, 350);

        addItemStage.setScene(addItemScene);
        addItemStage.show();
    }
}

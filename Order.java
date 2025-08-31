/*
 *
 *   NUMBER OOOOOOOOOOOOOOOOOOOOOOOOOOOOONNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNEEEEEEEEEEEEEEEEEE 1111111111111111111111111111111111
 */
 //  NUMBER OOOOOOOOOOOOOOOOOOOOOOOOOOOOONNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNEEEEEEEEEEEEEEEEEE 1111111111111111111111111111111111
 //! NUMBER OOOOOOOOOOOOOOOOOOOOOOOOOOOOONNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNEEEEEEEEEEEEEEEEEE 1111111111111111111111111111111111 
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

import javax.management.relation.InvalidRoleValueException;

class Pair{
    public Item item;
    public int amount;
    public Pair(){}
    public Pair (Item i ,int x){
        this.item=i;
        this.amount=x;
    }
}
public class Order
implements Features {
    private ArrayList<Pair>itemss=new ArrayList<>();
    private double totalcost=0;
    private LocalDate dayDate;
    private String customername;
    boolean iscard=true;
    private String cardNumber = null;
    static int ordernum=0;
    int id;
    public Order() {
        dayDate = LocalDate.now();
        id=++ordernum; 
    }
    public Order(String name){
        dayDate = LocalDate.now(); 
        this.customername=name;
        id=++ordernum; 

    }
    boolean is_card(){
        return this.iscard;
    }

    public String getDayDate() {
        return dayDate.toString();
    }
    public void buy(Item m,int x){
        itemss.add(new Pair(m,x));
        totalcost+=x*m.getcost();
    }
    public void setpayment(boolean x){
        this.iscard=x;
    }
    public double donedeal(double cash) {
        if (!iscard) { 
            if (cash < totalcost) {
                throw new IllegalArgumentException("Insufficient cash. Required: " + totalcost + ", Provided: " + cash);
            }
            return cash - totalcost; 
        } else {
            return 0; 
        }
    }
    public double donedeal(double cash, String cnum) {
        if(iscard){
            this.cardNumber=cnum;
            System.out.println("Payment successful using card. Total paid: " + totalcost + " EGP.");
            return 0;
        }
        else{
            throw new IllegalStateException("You have chossen cash method\n");
        }
    }

    
   @Override
   public String getname() {
    return this.customername;
   }
   @Override
   public void setname(String n) {
    this.customername=n;
   }
   @Override
   public int getid() {
    return this.id;
   }
   @Override
   public void printinfo() {
    System.out.println("-------------------------------------------------------------------------------------");
    System.out.println("order number : "+this.id);
    System.out.println("Coustomer name : "+this.customername);
    System.out.println("Order has been don  on : "+this.dayDate.toString());
   
    System.out.println("Payment Method: " + (iscard ? "Credit Card: " + this.cardNumber : "Cash" ));
    System.out.println("Total Cost :"+this.totalcost);

    
    System.out.println("-------------------------------------------------------------------------------------");
   }
   @Override
   public void printdetails() {
    this.printinfo();
    System.out.println("Items has been bought :");
    for(Pair m:itemss) {
        m.item.printdetails();
        System.out.println("Amount : "+m.amount);
    }
   }
   @Override
   public double getcost() {
    return this.totalcost;
   }
   @Override
   public int getnum() {
    return Order.ordernum;
   }




    
}
 class OnlineOrder extends Order {
    private String deliveryAddress;
    private String email;
    private boolean isShipped = false;
    
    public OnlineOrder(){}

    public OnlineOrder(String customerName, String deliveryAddress, String email) {
        super(customerName);
        this.deliveryAddress = deliveryAddress;
        this.email = email;
    }

    public void setDeliveryAddress(String address) {
        this.deliveryAddress = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void markShipped() {
        this.isShipped = true;
    }

    public boolean isShipped() {
        return isShipped;
    }

    @Override
    public void printinfo() {
        super.printinfo();
        System.out.println("Delivery Address: " + deliveryAddress);
        System.out.println("Email: " + email);
        System.out.println("Shipping Status: " + (isShipped ? "Shipped" : "Pending"));
    }
    @Override
public void printdetails(){
    this.printinfo();
    super.printdetails();
    System.out.println("=============================================================================================");

}

}
class OrderManger {

    ArrayList<Order>Orders =new ArrayList<>() ;

    public OrderManger(){}

    public void onlineorder() {
    OnlineOrder o = new OnlineOrder();
    Scanner input = new Scanner(System.in);
    int x = -1;
    int amount = 0;

    while (true) {
        Mangersys.Storage.showitems();
        System.out.print("Please Add Item to your order (if done enter -1): ");
        try {
            x = input.nextInt();
            if (x == -1) break;
            if (x < 0 || x >= Mangersys.Storage.getsize()) {
                System.out.println("❌ Invalid item index. Try again.");
                continue;
            }
            System.out.print("Enter Amount: ");
            amount = input.nextInt();
            o.buy(Mangersys.Storage.sell(x), amount);
        } catch (Exception e) {
            System.out.println("❌ Please enter valid numbers.");
            input.nextLine(); // clear buffer
        }
    }

    input.nextLine(); // clear buffer
    System.out.print("Enter your name: ");
    o.setname(input.nextLine());

    System.out.print("Enter delivery address: ");
    o.setDeliveryAddress(input.nextLine());

    System.out.print("Enter your email: ");
    o.setEmail(input.nextLine());

    // Payment selection
    while (true) {
        System.out.println("Total Price is: " + o.getcost() + " EGP");
        System.out.println("1. Cash on delivery");
        System.out.println("2. Credit card");
        System.out.print("Enter your choice: ");
        try {
            x = input.nextInt();
            if (x == 1 || x == 2) break;
            System.out.println("Invalid choice. Please enter 1 or 2.");
        } catch (Exception e) {
            System.out.println("Please enter a valid number (1 or 2).");
            input.nextLine(); // clear buffer
        }
    }

    o.setpayment(x == 2);

    if (!o.is_card()) {
        while (true) {
            try {
                System.out.print("Enter cash amount: ");
                double cash = input.nextDouble();
                double change = o.donedeal(cash);
                System.out.printf("Transaction successful. Change: %.2f EGP\n", change);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                input.nextLine();
            }
        }
    } else {
        input.nextLine(); // clear buffer
        System.out.print("Enter card number: ");
        String cardNumber = input.nextLine();
        o.donedeal(0, cardNumber);
    }

    Orders.add(o);
    o.printinfo();
}

    public void offlineorder() {
    Order o = new Order();
    Scanner input = new Scanner(System.in);
    int x = -1;
    int amount = 0;

    while (true) {
        Mangersys.Storage.showitems();
        System.out.print("Please Add Item to your order (if done enter -1): ");

        try {
            x = input.nextInt();
            if (x == -1) break;
            if (x < 0 || x >= Mangersys.Storage.getsize()) {
                System.out.println("❌ Invalid index. Try again.");
                continue;
            }
            System.out.print("Enter Amount: ");
            amount = input.nextInt();
            o.buy(Mangersys.Storage.sell(x), amount);
        } catch (Exception e) {
            System.out.println("❌ Please enter valid numbers.");
            input.nextLine(); // clear buffer
        }
    }

    input.nextLine(); // clear buffer
    System.out.print("Enter your Name: ");
    o.setname(input.nextLine());

    // Payment choice
    while (true) {
        System.out.println("Total Price is: " + o.getcost() + " EGP");
        System.out.println("1. Cash");
        System.out.println("2. Credit card");
        System.out.print("Enter your choice: ");
        try {
            x = input.nextInt();
            if (x == 1 || x == 2) break;
            System.out.println("Invalid choice. Please enter 1 or 2.");
        } catch (Exception e) {
            System.out.println("Please enter a valid number (1 or 2).");
            input.nextLine();
        }
    }

    o.setpayment(x == 2);

    if (!o.is_card()) {
        // Cash
        while (true) {
            try {
                System.out.print("Enter cash amount: ");
                double cash = input.nextDouble();
                double change = o.donedeal(cash);
                System.out.printf("Transaction successful. Change: %.2f EGP\n", change);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                input.nextLine();
            }
        }
    } else {
        // Card
        input.nextLine();
        System.out.print("Enter card number: ");
        String cardNum = input.nextLine();
        o.donedeal(0, cardNum);
    }

    // Assuming Orders is declared somewhere (e.g., List<Order> Orders = new ArrayList<>();)
    Orders.add(o);
    o.printinfo();
}


    public void ShowOrders(){
        for(Order o:Orders){
            o.printdetails();
            System.out.println("=============================================================================================");
        }
    }
    public double Totalcash(){
        double c=0;
        for(Order o:Orders){
            c+=o.getcost();
        }
        return c;
    }

   

}

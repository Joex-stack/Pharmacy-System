/*
 *
 *   NUMBER OOOOOOOOOOOOOOOOOOOOOOOOOOOOONNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNEEEEEEEEEEEEEEEEEE 1111111111111111111111111111111111
 */
 //  NUMBER OOOOOOOOOOOOOOOOOOOOOOOOOOOOONNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNEEEEEEEEEEEEEEEEEE 1111111111111111111111111111111111
 //! NUMBER OOOOOOOOOOOOOOOOOOOOOOOOOOOOONNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNEEEEEEEEEEEEEEEEEE 1111111111111111111111111111111111
 
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import javax.management.relation.InvalidRoleValueException;

public class Mangersys {
    public static ItemManeger Storage=new ItemManeger();
    private OrderManger orders=new OrderManger();
    private int way=1;


   public Mangersys(){
    this.initialize();
   }

   public int Mainmenu() throws InvalidRoleValueException {
    Scanner sc = new Scanner(System.in);  // Don't close this Scanner!
    System.out.println("Welcome to ZSC pharmacy");
    System.out.println("1. Make order");
    System.out.println("2. Admin");
    System.out.println("3. Exit");

    while (true) {
        System.out.print("Enter your choice: ");
        try {
            way = sc.nextInt();
            if (way == 1 || way == 2 || way == 3) {
                return way;
            } else {
                System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        } catch (Exception e) {
            sc.nextLine();
            throw new InvalidRoleValueException("Please enter a valid input (1, 2, or 3).");
        }
    }
}
    public void seconedpage() throws InvalidRoleValueException{
        if(way==1) this.Makeorder();
        else if(way==2) this.adminway();
        else return;
    }


   public void addtostorage(Item m){
    Storage.additem(m);
   }
   private void initialize() {
    ItemManeger storage = new ItemManeger();

    // 10 Drugs
    storage.additem(new Drugs("Paracetamol", "PharmaPlus", "Painkiller", 15.50, LocalDate.of(2026, 5, 1), "General"));
    storage.additem(new Drugs("Amoxicillin", "MediLab", "Antibiotic", 32.75, LocalDate.of(2025, 12, 15), "Infection"));
    storage.additem(new Drugs("Ibuprofen", "HealFast", "Anti-inflammatory", 27.90, LocalDate.of(2026, 3, 10), "Muscle"));
    storage.additem(new Drugs("Cetirizine", "AllerFree", "Antihistamine", 19.00, LocalDate.of(2025, 10, 1), "Allergy"));
    storage.additem(new Drugs("Insulin", "BioMed", "Diabetes", 150.00, LocalDate.of(2025, 7, 20), "Endocrine"));
    storage.additem(new Drugs("Omeprazole", "StomachEase", "Digestive", 22.00, LocalDate.of(2026, 6, 30), "Stomach"));
    storage.additem(new Drugs("Aspirin", "HeartCare", "Blood thinner", 10.50, LocalDate.of(2026, 1, 1), "Cardio"));
    storage.additem(new Drugs("Salbutamol", "BreatheWell", "Asthma", 35.00, LocalDate.of(2025, 11, 20), "Respiratory"));
    storage.additem(new Drugs("Loratadine", "AllerFree", "Antihistamine", 21.00, LocalDate.of(2025, 9, 9), "Allergy"));
    storage.additem(new Drugs("Metformin", "Glucocare", "Diabetes", 45.00, LocalDate.of(2026, 4, 18), "Endocrine"));

    // 10 Hygiene
    storage.additem(new Hygiene("Hand Wash", "CleanHands", "Sanitizer", 25.00, LocalDate.of(2026, 8, 15), "Daily Use", true));
    storage.additem(new Hygiene("Toothpaste", "WhiteSmile", "Oral Care", 18.50, LocalDate.of(2027, 1, 5), "Oral", false));
    storage.additem(new Hygiene("Shampoo", "SilkySoft", "Hair Care", 42.30, LocalDate.of(2026, 11, 11), "Hair", true));
    storage.additem(new Hygiene("Body Soap", "FreshBody", "Skin Care", 12.00, LocalDate.of(2026, 9, 9), "Skin", false));
    storage.additem(new Hygiene("Facial Wipes", "GentleTouch", "Cosmetics", 30.00, LocalDate.of(2025, 8, 8), "Face", true));
    storage.additem(new Hygiene("Deodorant", "CoolScent", "Body Care", 35.00, LocalDate.of(2026, 12, 1), "Body", false));
    storage.additem(new Hygiene("Mouthwash", "FreshBreath", "Oral Care", 28.00, LocalDate.of(2027, 2, 14), "Oral", false));
    storage.additem(new Hygiene("Face Wash", "GlowSkin", "Cosmetics", 33.00, LocalDate.of(2026, 10, 10), "Face", true));
    storage.additem(new Hygiene("Wet Tissues", "CleanSoft", "Wipes", 22.00, LocalDate.of(2026, 7, 7), "General", true));
    storage.additem(new Hygiene("Hair Gel", "StyleMax", "Hair", 40.00, LocalDate.of(2026, 5, 5), "Hair", false));

    Mangersys.Storage = storage;

   
}


public void Makeorder() throws InvalidRoleValueException{
    Scanner input=new Scanner(System.in);
    int x=-1; 
    System.out.println("1. Offline");
    System.out.println("2. Online");

    while (true) {
        System.out.print("Enter your choice: ");
        try {
            way = input.nextInt();
            if (way == 1 || way == 2 ) {
                break;
            } else {
                System.out.println("Invalid choice. Please enter 1, 2.");
            }
        } catch (Exception e) {
            input.nextLine();
            throw new InvalidRoleValueException("Please enter a valid input (1 or 2).");
        }
    }
    if(this.way==1) this.orders.offlineorder();
    else this.orders.onlineorder();
    System.out.println("----------------------------------------------------------------");
   
    
}




public void adminway() throws InvalidRoleValueException{
    Scanner input = new Scanner(System.in);

    System.out.println("1. Show All orders");
    System.out.println("2. Add Item");

    while (true) {
        System.out.print("Enter your choice: ");
        try {
            way = input.nextInt();
            if (way == 1 || way == 2 ) {
                break;
            } else {
                System.out.println("Invalid choice. Please enter 1, 2.");
            }
        } catch (Exception e) {
            input.nextLine();
            throw new InvalidRoleValueException("Please enter a valid input (1 or 2).");
        }
    }
    if(way==1) this.showallo();
    else this.addItem();
    
}
private void addItem() throws InvalidRoleValueException {
    System.out.println("1. Drugs");
    System.out.println("2. Hygiene");
    Scanner input = new Scanner(System.in);
    int x = -1;

    while (true) {
        System.out.print("Enter your choice: ");
        try {
            x = input.nextInt();
            input.nextLine(); // clear buffer
            if (x == 1 || x == 2) {
                break;
            } else {
                System.out.println("Invalid choice. Please enter 1 or 2.");
            }
        } catch (Exception e) {
            input.nextLine();
            throw new InvalidRoleValueException("Please enter a valid input (1 or 2).");
        }
    }

    System.out.print("Enter item name: ");
    String name = input.nextLine();

    System.out.print("Enter company name: ");
    String company = input.nextLine();

    System.out.print("Enter category: ");
    String category = input.nextLine();

    System.out.print("Enter price: ");
    double price = input.nextDouble();
    input.nextLine(); 

    System.out.print("Enter expiry date (YYYY-MM-DD): ");
    LocalDate expDate = LocalDate.parse(input.nextLine());

    if (x == 1) {
        System.out.print("Enter specialization: ");
        String spec = input.nextLine();
        Drugs d = new Drugs(name, company, category, price, expDate, spec);
        Storage.additem(d);
    } else {
        System.out.print("Enter usage type: ");
        String usageType = input.nextLine();

        System.out.print("Is it for sensitive skin? (true/false): ");
        boolean forSensitive = input.nextBoolean();
        input.nextLine(); // clear buffer

        Hygiene h = new Hygiene(name, company, category, price, expDate, usageType, forSensitive);
        Storage.additem(h);
    }

    System.out.println("Item added successfully!");
}

private void showallo() {
    orders.ShowOrders();
    
    System.out.println("Summary :-");
    System.out.println("Total items sold :" +Mangersys.Storage.getnumsolds());
    System.out.println("Total cash : "+orders.Totalcash()   );
}

}

   



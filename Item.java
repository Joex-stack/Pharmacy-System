/*
 *
 *  NUMBER OOOOOOOOOOOOOOOOOOOOOOOOOOOOONNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNEEEEEEEEEEEEEEEEEE /\/\/\/\/\/\ 1111111111111111111111111111111111
 */
 // NUMBER OOOOOOOOOOOOOOOOOOOOOOOOOOOOONNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNEEEEEEEEEEEEEEEEEE \/\/\/\/\/\/\ 1111111111111111111111111111111111
 //!NUMBER OOOOOOOOOOOOOOOOOOOOOOOOOOOOONNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNEEEEEEEEEEEEEEEEEE \/\/\/\/\/\/\ 1111111111111111111111111111111111

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

interface Features
{
    String getname();
    void setname(String n);
    int getid();
    void printinfo();
    void printdetails();
    double getcost();
    int getnum();
}
public abstract class Item implements Features, Comparable<Item>
{

    protected String name;
    protected String category;
    protected String company;
    protected double price;
     static int itemnum=0;
    protected int id;
    protected LocalDate expirDate;


    public Item(){
        id=++itemnum;
    }
    public Item (String n,String cat,double p){
        id=++itemnum;
        this.name=n;
        this.category=cat;
        this.price=p;
    }
    public Item (String n,double p,String cmp){
        id=++itemnum;
        this.name=n;
        this.company=cmp;
        this.price=p;
    }
    public Item (String n,String cmp,String cat,double p,LocalDate expirDate){
        id=++itemnum;
        this.name=n;
        this.company=cmp;
        this.category=cat;
        this.price=p;
        this.expirDate=expirDate;
    }
    public Item(Item m){
        this.id=++itemnum;
        this.name=m.getname();
        this.category=m.category;
        this.price=m.getcost();
    }
    public void destroy() {
        itemnum--;
    }
    public void setexdat(LocalDate expirDate){
        this.expirDate=expirDate;
    }
    public String getexdate(){
        return this.expirDate.toString();
    }
    public String getname() {
        return name;
    }
    public void setname(String name) {
        this.name = name;
    }
    public String getcategory() {
        return category;
    }
    public void setcategory(String category) {
        this.category = category;
    }
    public String getcompany() {
        return company;
    }
    public void setcompany(String company) {
        this.company = company;
    }
    public double getprice() {
        return price;
    }
    public void setprice(double price) {
        this.price = price;
    }
    public int getnum() {
        return itemnum;
    }
    public int getid() {
        return id;
    }
    public void printinfo(){
        System.out.println("Item name : "+this.name+" Item price : "+this.price);
        System.err.println("--------------------------------------------------+");
    }
    public void printdetails(){
        System.out.println("Item id : "+id);
        System.out.println("Item name : "+this.name+"  Item price : "+this.price);
        System.out.println("Item category : "+this.category +"  Item company : "+this.company);

    }
    public int compareTo(Item other) {
    int nameCompare = this.name.compareTo(other.name);
    if (nameCompare != 0) {
        return nameCompare;
    }
    return Double.compare(this.price, other.price);
}

@Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;

    Item item = (Item) obj;

    return id == item.id &&
           Double.compare(item.price, price) == 0 &&
           name.equals(item.name) &&
           Objects.equals(category, item.category) &&
           Objects.equals(company, item.company) &&
           Objects.equals(expirDate, item.expirDate);
}
public abstract String gettype();

  

}


class Drugs extends Item {
    protected String specialization;

    public Drugs(String n, String cmp, String cat, double p, LocalDate expirDate, String s) {
        super(n, cmp, cat, p, expirDate); 
        this.specialization = s;
    }
    public Drugs(Drugs d){
        super((Item)d);
        this.specialization=d.getSpecialization();
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String s) {
        this.specialization = s;
    }

    @Override
    public void printdetails() {
        super.printdetails();
        System.out.println("Specialization: " + specialization);
        System.err.println("--------------------------------------------------+");
    }

    @Override
    public double getcost() {
        return this.price;
        
    }
    @Override

    public String gettype(){
        return "Drugs"+this.specialization;
    }
  
}


 class Hygiene extends Item {
    private String usageType;    
    private boolean forSensitiveSkin;

    public Hygiene(String n, String cmp, String cat, double p, LocalDate expirDate,
                   String usageType, boolean forSensitiveSkin) {
        super(n, cmp, cat, p, expirDate);
        this.usageType = usageType;
        this.forSensitiveSkin = forSensitiveSkin;
    }
    public Hygiene(Hygiene h){
        super((Item)h);
        this.usageType=h.getUsageType();
        this.forSensitiveSkin=h.isForSensitiveSkin();
    }

    public String getUsageType() {
        return usageType;
    }

    public void setUsageType(String usageType) {
        this.usageType = usageType;
    }

    public boolean isForSensitiveSkin() {
        return forSensitiveSkin;
    }

    public void setForSensitiveSkin(boolean forSensitiveSkin) {
        this.forSensitiveSkin = forSensitiveSkin;
    }

    @Override
    public void printdetails() {
        super.printdetails();
        System.out.println("Usage Type: " + usageType);
        System.out.println("For Sensitive Skin: " + (forSensitiveSkin ? "Yes" : "No"));
        System.err.println("--------------------------------------------------+");
    }

    @Override
    public double getcost() {
        return this.price;
    }

    @Override
    public String gettype() {
        return "Hygiene"+this.usageType;
    }
}

class ItemManeger{
   private ArrayList<Item> items=new ArrayList<>();
   private ArrayList<Item> sold=new ArrayList<>();
   private double totalb=0;

   public ItemManeger(){}
   public ItemManeger(ArrayList<Item> item){
    this.items=item;
    Collections.sort(this.items);
   }
   public void additem(Item m){
    this.items.add(m);
    Collections.sort(this.items);
   }
   public Item sell(int index) {
    if (index < 0 || index >= items.size()) {
        throw new IndexOutOfBoundsException("Invalid index");
    }
    Item.itemnum--;
    sold.add(items.get(index));
    totalb+=items.get(index).getprice();
    return items.get(index);
}
    void showitems(){
        for(int i=0;i<items.size();i++){
            System.out.print(i+") ");
            items.get(i).printdetails();
           
        }
   
    }
    Item get(int index ){
        if (index < 0 || index >= items.size()) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        return this.items.get(index);
    }
    public int getnumsolds(){
        return sold.size();
    }
    public int getsize(){return this.items.size();}

}


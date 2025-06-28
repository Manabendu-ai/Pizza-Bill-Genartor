import java.util.Scanner;

class Pizza{ // base pizza
    int type;
    double total_price = 0.0;
    static final double VEG_PRICE = 300.0;
    static final double NON_VEG_PRICE = 400.0;
    static final double GST = 0.18;
    static final double SERVICE_TAX = 0.07;
    int layers_ = 0;
    Pizza(int type){
        this.type = type;
        total_price += (type == 2) ? NON_VEG_PRICE : VEG_PRICE;
    }
    public void extraCheese(int layer){
        layers_ = layer;
        total_price += layer*100.0;
    }
    public void extraToppings(){
        total_price += 100.0;
    }
    public void takeAway(){
        total_price += 20.0;
    }
    public double finalBill(){
        return total_price * (1 + GST + SERVICE_TAX);

    }
}

class DeluxPizza extends Pizza{
    DeluxPizza(int type){
        super(type);
        this.extraCheese(2);
        this.extraToppings();
    }
}

public class Main{
    int type;
    boolean isBase;
    int layers;
    double total_price;
    double total_price_notax;
    double[] price_spread = new double[4];
    int toppings=0;
    Scanner sc;
    Main(){
        sc = new Scanner(System.in);
        System.out.println("===Welcome TO Pizza Pool \uD83C\uDF55!===");
        System.out.print("\nSelect your Pizza type:\n1) Base variant\n2) Delux Variant\n:");
        int select = sc.nextInt();
        this.isBase = (select == 1) ? true : false;
        this.simulate(this.isBase);
    }

    public void simulate(boolean isBase){
        String meal;
        String parcel = "no";
        if(isBase){
            System.out.println("\nExcellent Choice \uD83D\uDC4F");
            System.out.print("\nSelect your meal preference:\n1) Veg\n2) Non-Veg\n:");
            type = sc.nextInt();
            Pizza pizza = new Pizza(type);
            if(type == 1) {
                meal = "VEG";
                price_spread[0] = pizza.VEG_PRICE;
            }else{
                meal = "Non Veg";
                price_spread[0] = pizza.NON_VEG_PRICE;
            }
            System.out.print("\nWould You love to have an extra layer of cheese?[0 or 1]\n:");
            int extraCh = sc.nextInt();
            if(extraCh == 1){
                System.out.print("Number of layers? \n:");
                layers = sc.nextInt();
                pizza.extraCheese(layers);
                price_spread[1] = layers*100.0;
            }
            System.out.print("\nWould You love to have an extra Toppings?[0 or 1]\n:");
            int extraTp = sc.nextInt();
            if(extraTp == 1){
                toppings = 1;
                pizza.extraToppings();
                price_spread[2] = 100;
            }
            System.out.print("\nWould You like to Have it here or TakeAway?[0 or 1]\n:");
            int isTake = sc.nextInt();
            if(isTake == 1){
                parcel = "Yes";
                pizza.takeAway();
                price_spread[3] = 20;
            }
            total_price = pizza.finalBill();
            total_price_notax = pizza.total_price;
        }
        else{
            layers = 2;
            toppings = 1;
            price_spread[1] = 200;
            price_spread[2] = 100;
            System.out.println("\nExcellent Choice \uD83D\uDC4F");
            System.out.print("\nSelect your meal preference:\n1) Veg\n2) Non-Veg\n:");
            type = sc.nextInt();
            DeluxPizza pizza = new DeluxPizza(type);
            if(type == 1) {
                meal = "VEG";
                price_spread[0] = pizza.VEG_PRICE;
            }else{
                meal = "Non Veg";
                price_spread[0] = pizza.NON_VEG_PRICE;
            }
            System.out.print("\nWould You like to Have it here or TakeAway?[0 or 1]\n:");
            int isTake = sc.nextInt();
            if(isTake == 1){
                parcel = "Yes";
                pizza.takeAway();
                price_spread[3] = 20;
            }
            total_price = pizza.finalBill();
            total_price_notax = pizza.total_price;
        }
        System.out.println("=============== Final Bill ===============");

        System.out.printf("%-23s : %-14s ₹%.2f\n", "Meal Type", meal, price_spread[0]);
        System.out.printf("%-23s : %-14s ₹%.2f\n", "Extra Cheese Layers", layers + " x 100", price_spread[1]);
        System.out.printf("%-23s : %-14s ₹%.2f\n", "Extra Toppings", toppings + " x 100", price_spread[2]);
        System.out.printf("%-23s : %-14s ₹%.2f\n", "TakeAway", parcel, price_spread[3]);
        System.out.printf("%-23s : %-14s ₹%.2f\n", "GST", "18%", total_price_notax * 0.18);
        System.out.printf("%-23s : %-14s ₹%.2f\n", "Service Tax", "7%", total_price_notax * 0.07);

        System.out.println("-----------------------------------------------");

        System.out.printf("%-23s : %-14s ₹%.2f\n", "Total Price", "", total_price);

        System.out.println("\nThank Your Visiting PizzaPool\uD83C\uDF55!\nWould love to see you soon");

    }

    public static void main(String args[]){
        Main main = new Main();
    }
}
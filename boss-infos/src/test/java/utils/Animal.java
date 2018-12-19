package utils;

/**
 * Created by qxr4383 on 2018/12/10.
 */
public class Animal {
    public static String name = "A";

    public Animal(){
        a();
    }
    public Animal(String name){
        this.name = name;
        a();
    }
    public void a(){
        System.out.println(name);
    }
}

class Dog extends Animal{

    public static String name = "D";


    public Dog(String name){
        super(name);
        this.name = name;
        System.out.println(name);
    }

    public void a(){
        System.out.println(name);
    }

    public static void main(String[] args) {
        //Animal animal = new Dog("C");
        Animal animal = new Animal();
    }

}


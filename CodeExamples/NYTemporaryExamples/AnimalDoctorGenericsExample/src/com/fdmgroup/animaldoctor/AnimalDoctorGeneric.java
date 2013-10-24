package com.fdmgroup.animaldoctor;

import java.util.ArrayList;
import java.util.List;

public class AnimalDoctorGeneric {
	
    // change the argument from Animal[] to ArrayList<Animal>
    public void checkAnimals(List<Animal> animals) {
      for(Animal a : animals) {
        a.checkup();
      }
    }
    
    public void addAnimal(List<? extends Animal> animals) {


    }
    
    
    
    public static void main(String[] args) {
       // make ArrayLists instead of arrays for Dog, Cat, Bird
       List<Dog> dogs = new ArrayList<Dog>();
       
       dogs.add(new Dog());
       dogs.add(new Dog());
       
       List<Cat> cats = new ArrayList<Cat>();
       cats.add(new Cat());
       cats.add(new Cat());
       
       List<Bird> birds = new ArrayList<Bird>();
       birds.add(new Bird());
       
       // this code is the same as the Array version
       AnimalDoctorGeneric doc = new AnimalDoctorGeneric();
       
       // this worked when we used arrays instead of ArrayLists
       //doc.checkAnimals(dogs);  // send a List<Dog>
	   //doc.checkAnimals(cats);  // send a List<Cat>
	   //doc.checkAnimals(birds); // send a List<Bird>
       
       doc.addAnimal(dogs);
    		
       
       
       /* 
         	What is the difference?
        
	       public void foo(List<?> list){}
	       
	       public void foo(List<Object> list){}       
	       
       
       
       */
       
       
       
       
       
       
       
       
       
       
       
       
    }
}
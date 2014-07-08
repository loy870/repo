package com.fdmgroup.genericsyntax;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;



public class GenericSyntax {

	
	public static void main(String[] args)
	{

		List<Object> myList = new ArrayList<Object>();
		
		myList = new LinkedList<Object>();
		
		List<?> list1 = new ArrayList<Object>();
		// here
		list1 = new ArrayList<String>();
		// here
		
	}
}


class GenericClass<W extends Comparable> {			// as the class type

	W	anInstance;		// as an instance variable type
	W[]	anArrayOfTs;	// as an array type

	GenericClass(W anInstance) { // as an argument type

		this.anInstance = anInstance;
	}

	W getW() { // as a return type

		return anInstance;
	}
}
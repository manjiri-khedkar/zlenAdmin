package com.zlenadmin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

class Combination {
	private static double max_no=-1;
	private static ArrayList<Double> result;
	
    static void sum_up_recursive(ArrayList<Double> numbers, double target, ArrayList<Double> partial,Double s,String parent) {
    	System.out.println(String.format("enter into numbers: %d, partial= %d, sum= %.2f parent: %s",numbers.size(),partial.size(),s, parent));
       //double s = 0;
       //for (double x: partial) s += x;
       //System.out.println(partial);
      /* if (s>max_no){
    	   max_no=s;
       }*/
       if (s <= target && s>=max_no){
           // System.out.println("sum("+Arrays.toString(partial.toArray())+")="+target);
    	   System.out.printf("Max: %.2f \n" , max_no);
            max_no=s;
            result=partial;
       }
       if (s >= target)
            return;
       
       for(int i=0;i<numbers.size();i++) {
             ArrayList<Double> remaining = new ArrayList<Double>();
             double n = numbers.get(i);
            // double rmn_sum=0.0;
             for (int j=i+1; j<numbers.size();j++) {
            	 remaining.add(numbers.get(j));
            	 //rmn_sum+=numbers.get(j);
             }
             ArrayList<Double> partial_rec = new ArrayList<Double>(partial);
             partial_rec.add(n);
             Double tmp_sum = s+ n;
             if (tmp_sum >target){
            	// System.out.printf("breaked.. %.2f -  %d\n" , tmp_sum , remaining.size());
            	 break;
             }
            // if ((tmp_sum+rmn_sum)>max_no){
            	 // System.out.println("Combination calling sum_up_recursive: "+ remaining);
                 sum_up_recursive(remaining,target,partial_rec,tmp_sum, parent+"-"+i);
             //}else{
            	 
             //}
       }
       //System.out.println(String.format("exit from numbers: %d, partial= %d, sum= %.2f ",numbers.size(),partial.size(),s));
    }
    static ArrayList<Double> sum_up(ArrayList<Double> numbers, double target) {
    	max_no=-1;
    	result= new ArrayList<Double>();
    	System.out.println();
    	for (Double d: numbers){
    		
    		//System.out.print(d.floatValue() +" ");
    		System.out.printf("%.2f, ", d);
    	}
    	System.out.println();
    	System.out.printf("Target: %.2f",target);
    	if (numbers.size()>0){
    		sum_up_recursive(numbers,target,new ArrayList<Double>(),0.0,"1");
    	}
    	//System.out.println("Combination : "+ result);
        return result;
    }
    public static void main(String args[]) {
    	Double[] numbers = { 24038000.00, 20361600.00, 16119600.00, 14079400.00, 13089600.00, 11675600.00, 10645400.00, 9191000.00, 8484000.00, 7898200.00, 7867900.00, 7070000.00, 6969000.00, 6908400.00, 6908400.00, 6868000.00, 6464000.00, 6363000.00, 5948900.00, 5837800.00, 5817600.00, 5757000.00, 5494400.00, 5322700.00, 5151000.00, 5151000.00, 4858100.00, 4726800.00, 4545000.00, 4332900.00, 4070300.00, 3999600.00, 3444100.00, 3030000.00  };
        double target = 120000000.00;
        ArrayList<Double> l = new ArrayList<Double>(Arrays.asList(numbers));
        Collections.sort(l);
        sum_up(l,target);
        double sum=0.0;
        for (Double d: result){
    		sum+=d;
    		//System.out.print(d.floatValue() +" ");
    		System.out.printf("result : %.2f, \t ", d);
    	}
        System.out.printf("Final Result:  %.2f", sum);
    }
}
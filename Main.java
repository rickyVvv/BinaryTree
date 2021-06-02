import java.beans.beancontext.BeanContextServiceProviderBeanInfo;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.*;
import java.lang.Math;

//Proj 3
//Created by: Ritesh Virlley 
//Created on 3/29/21
//RXV200008
public class Main {
    public static int x = 0;
    public static void main(String[] args) throws FileNotFoundException {
        Scanner Userinput = new Scanner(System.in); //creating a new scanner
        String in; //temp string
        in = Userinput.next(); //user input
        File file = new File (in); //creating file
        if(!file.exists()){
            System.out.print("No such file");
        }
        Scanner scan = new Scanner(file); //searching file
        int Lines = 0;
        int nospacebutx = 0;
        Payload one = new Payload();
        Payload anchor = new Payload();
        int a = 0;
        int b = 0;
        while(scan.hasNextLine()) //counting how many line there are in the text file
        {
            Lines++;
            scan.nextLine();
        }
        scan = new Scanner(file);
        for(int i =0; i < Lines; i++){
            StringBuffer equation =new StringBuffer(scan.nextLine());
            BinTree<Payload> tree = new BinTree<>();
            one = new Payload();
            boolean empty = false;
            
            while(empty == false){ // this checks to see if the line i am parsing is empty
                one.settrig(""); //resetting trig
                if(Character.isDigit(equation.charAt(0))){ //checks to see if there is a digit
                    int temp = Character.getNumericValue(equation.charAt(0));
                    one.seta(temp);
                    a = temp;
                    equation.delete(0,2);
                    if(Character.isDigit(equation.charAt(0))){//checks to see if there is a digit
                         temp = Character.getNumericValue(equation.charAt(0));
                        one.setb(temp);
                        b = temp;
                        equation.delete(0,2);
                    }
                }
                else if(equation.charAt(0) == '-'){//check to see if it is negative
                    int temp= 0;
                    if(Character.isDigit(equation.charAt(2))){//checks to see if there is a digit
                        String temporary = equation.substring(0,3);
                        temp = Integer.parseInt(temporary);
                        equation.delete(0,4);
                        a = temp;
                        one.seta(temp);
                        
                    }
                    else {
                        temp = Character.getNumericValue(equation.charAt(1));
                        a = temp * -1;
                        one.seta(temp * -1);
                        equation.delete(0, 3);
                    }
                    if(Character.isDigit(equation.charAt(0))){//checks to see if there is a digit
                        temp = Character.getNumericValue(equation.charAt(0));
                        b = temp;
                        one.setb(temp);
                        equation.delete(0,2);
                    }
                    else{
                        temp = Character.getNumericValue(equation.charAt(1));
                        one.setb(temp * -1);
                        b = temp * -1;
                        equation.delete(0,2);
                    }
                }
                else{
                    one.seta(0);
                    one.setb(0);
                    equation.delete(0,2);
                }
                boolean empt = false;
                for(int j = 0; j < equation.length(); j++){
                    nospacebutx = 0;
                    if(empt == false) {
                        if (equation.charAt(j) == 'x' && j != 0) {
                            if (equation.charAt(j - 1) == 'd') {
                                if (equation.charAt(0) == '+' && equation.charAt(1) == ' ') {
                                    //in the instance of the remaining being + 1 dx
                                    String tempy = equation.substring(0, j);
                                    tempy = tempy.substring(2, 3);
                                    int num1 = Character.getNumericValue(tempy.charAt(0));
                                    one.setnum(num1);
                                    empt = true;
                                    empty = true;
                                    
                                    tree.add(one);
                                    one = new Payload();

                                    continue;
                                } else if(equation.charAt(0) == '+' && equation.charAt(1) != ' '){ //checks to see the sign and if its positive
                                   
                                    if(Character.isDigit(equation.charAt(2))){//checks to see if there is a digit
                                        String tempy = equation.substring(1,3);
                                        int num1 = Integer.parseInt(tempy);
                                        one.setnum(num1);
                                        empt = true;
                                        empty = true;
                                        tree.add(one);
                                        one = new Payload();
                                        continue;
                                    }else {
                                        String tempy = equation.substring(0, j);
                                        tempy = tempy.substring(1, 2);
                                        int num1 = Character.getNumericValue(tempy.charAt(0));
                                        one.setnum(num1);
                                        empt = true;
                                        empty = true;
                                        tree.add(one);
                                        one = new Payload();
                                        continue;
                                    }
                                }
                                else if (equation.charAt(0) == '-') {//check to see if it is negative
                                    //in the instance of the remaining being + 1 dx
                                    if(equation.charAt(1) == ' ') {
                                        String tempy = equation.substring(0, j);
                                        tempy = tempy.substring(2, 3);
                                        int num1 = Character.getNumericValue(tempy.charAt(0));
                                        one.setnum(num1*-1);
                                        empt = true;
                                        empty = true;
                                        tree.add(one);
                                        one = new Payload();
                                        continue;
                                    }
                                    else{
                                        String tempy = equation.substring(0, j);
                                        tempy = tempy.substring(1, 2);
                                        int num1 = Character.getNumericValue(tempy.charAt(0));
                                        one.setnum(num1*-1);
                                        empt = true;
                                        empty = true;
                                        tree.add(one);
                                        one = new Payload();
                                        continue;
                                    }
                                } else if (equation.charAt(0) == 'd') {
                                    empt = true;
                                    empty = true;
                                    continue;
                                }
                                else if(equation.charAt(0) == '^') {
                                    if(equation.charAt(1) == '-'){//check to see if it is negative
                                        String tempo = equation.substring(2, 3);
                                        int exp = Character.getNumericValue(tempo.charAt(0));
                                        one.setexp(exp*-1);
                                        equation.delete(0, j);
                                        if(tree.search(tree.root,one) != null)
                                        {
                                            Payload tempp = tree.search(tree.root,one).getData();
                                            tempp.setnum(tempp.getnum() +one.getnum());
                                            if(tempp.getnum() == 0) {
                                                tree.delete(tree.root, one);
                                            }
                                            tree.add(tempp);
                                            one = new Payload();
                                            one.seta(a);
                                            one.setb(b);
                                            j =0;
                                            empt = true;
                                            empty = true;
                                            continue;

                                        }
                                        else {
                                            tree.add(one);
                                            one = new Payload();
                                            one.seta(a);
                                            one.setb(b);
                                            empt = true;
                                            empty = true;
                                            continue;
                                        }
                                    }
                                    else {
                                        String tempo = equation.substring(1, 2);
                                        int exp = Character.getNumericValue(tempo.charAt(0));
                                        one.setexp(exp);
                                        equation.delete(0, j);
                                        if(tree.search(tree.root,one) != null){
                                            Payload tempp = tree.search(tree.root,one).getData();
                                            tempp.setnum(tempp.getnum() +one.getnum());
                                            if(tempp.getnum() == 0) {
                                                tree.delete(tree.root, one);
                                            }
                                            tree.add(tempp);
                                            one = new Payload();
                                            one.seta(a);
                                            one.setb(b);
                                            j =0;
                                            empt = true;
                                            empty = true;
                                            continue;

                                        }else {
                                            tree.add(one);
                                            one = new Payload();
                                            one.seta(a);
                                            one.setb(b);
                                            empt = true;
                                            empty = true;
                                            continue;
                                        }
                                    }
                                }

                            }
                            String str = equation.substring(0, j);
                                if(str.compareTo("- ") == 0){ //okay this seems to be for when we string parse sometimes
                                    // there are just "- " which indicates that we dont have a numerator 
                                    one.setnum(-1);
                                    equation.delete(0, j + 1);
                                    continue;
                                }
                                else if(str.compareTo("+ ") == 0){ // a positive version of what was stated above
                                    one.setnum(1);
                                    equation.delete(0, j + 1);
                                    continue;
                                }
                                else if(str.compareTo("-") == 0){
                                    one.setnum(-1);
                                    equation.delete(0,j+1);
                                    if(equation.charAt(0) == '+' || equation.charAt(0) =='-' || equation.charAt(1) == 'd'){//checks to see the sign and if its positive
                                        one.setexp(1);
                                        if(tree.search(tree.root,one) != null){
                                            Payload tempp = tree.search(tree.root,one).getData();
                                            tempp.setnum(tempp.getnum() +one.getnum());
                                            if(tempp.getnum() == 0) {
                                                tree.delete(tree.root, one);
                                            }
                                            tree.add(tempp);
                                            one = new Payload();
                                            one.seta(a);
                                            one.setb(b);
                                            j =0;
                                            continue;

                                        }
                                        else {
                                            tree.add(one);
                                            one = new Payload();
                                            one.seta(a);
                                            one.setb(b);
                                            j = 0;
                                            continue;
                                        }
                                    }
                                    if(equation.charAt(0) == '^' && equation.charAt(1) == '8'){
                                        one.setexp(8);
                                        equation.delete(0,2);
                                        if(tree.search(tree.root,one) != null){
                                            Payload tempp = tree.search(tree.root,one).getData();
                                            tempp.setnum(tempp.getnum() +one.getnum());
                                            if(tempp.getnum() == 0) {
                                                tree.delete(tree.root, one);
                                            }
                                            tree.add(tempp);
                                            one = new Payload();
                                            one.seta(a);
                                            one.setb(b);
                                            j =0;
                                            continue;

                                        }
                                        else {
                                            tree.add(one);
                                            one = new Payload();
                                            one.seta(a);
                                            one.setb(b);
                                            j = 0;
                                            continue;
                                        }
                                    }
                                }
                                else if(str.compareTo("+") == 0){
                                    one.setnum(1);
                                    equation.delete(0,j+1);
                                    if(equation.charAt(0) == '+' || equation.charAt(0) =='-'){//checks to see the 
                                        // sign and if its positive or negative
                                        one.setexp(1);
                                        if(tree.search(tree.root,one) != null){
                                            Payload tempp = tree.search(tree.root,one).getData();
                                            tempp.setnum(tempp.getnum() +one.getnum());
                                            if(tempp.getnum() == 0) {
                                                tree.delete(tree.root, one);
                                            }
                                            tree.add(tempp);
                                            one = new Payload();
                                            one.seta(a);
                                            one.setb(b);
                                            j =0;
                                            continue;

                                        }
                                        else {
                                            tree.add(one);
                                            one = new Payload();
                                            one.seta(a);
                                            one.setb(b);
                                            j = 0;
                                            continue;
                                        }
                                    }
                                    else if(equation.charAt(0) == '^' && equation.charAt(1) == '-' && equation.charAt(4) == 'd'){//check to see if it is negative
                                        one.setexp(-2);
                                        if(tree.search(tree.root,one) != null){
                                            Payload tempp = tree.search(tree.root,one).getData();
                                            tempp.setnum(tempp.getnum() +one.getnum());
                                            if(tempp.getnum() == 0) {
                                                tree.delete(tree.root, one);
                                            }
                                            tree.add(tempp);
                                            one = new Payload();
                                            one.seta(a);
                                            one.setb(b);
                                            j =0;
                                            empty = true;
                                            empt = true;
                                            continue;

                                        }
                                        else {
                                            tree.add(one);
                                            one = new Payload();
                                            empty = true;
                                            empt = true;
                                            continue;
                                        }
                                        
                                    }
                                        
                                }
                    
                            int num = 0;
                                if(equation.charAt(1) == 'd' && str.charAt(0) == '-'){//check to see if it is negative
                                    one.setnum(-1);
                                    one.setexp(1);
                                    if(tree.search(tree.root,one) != null){
                                        Payload tempp = tree.search(tree.root,one).getData();
                                        tempp.setnum(tempp.getnum() +one.getnum());
                                        if(tempp.getnum() == 0) {
                                            tree.delete(tree.root, one);
                                        }
                                        tree.add(tempp);
                                        one = new Payload();
                                        one.seta(a);
                                        one.setb(b);
                                        j =0;
                                        continue;

                                    }
                                    else {
                                        tree.add(one);
                                        one = new Payload();
                                        one.seta(a);
                                        one.setb(b);
                                        j = 0;
                                        empt = true;
                                        empty = true;
                                        continue;
                                    }
                                }
                            if(!Character.isDigit(str.charAt(0)) ){//checks to see if there is a digit
                                // code
                                if(str.charAt(0) == '-' && str.charAt(1) == ' ') {//check to see if it is negative
                                    if(Character.isDigit(equation.charAt(2)) && !Character.isDigit(equation.charAt(3))) {//checks to see if there is a digit
                                        num = Character.getNumericValue(str.charAt(2));
                                        num = num * -1;
                                        one.setnum(num);
                                        equation.delete(0,j+1);
                                        j = 0;
                                        continue;
                                    }
                                    else if(Character.isDigit(equation.charAt(2)) && Character.isDigit(equation.charAt(3))){//checks to see if there is a digit
                                        String temppy = equation.substring(2,4);
                                        num = Integer.parseInt(temppy);
                                        one.setnum(num *-1);
                                        equation.delete(0,j+1);
                                        j = 0;
                                        continue;
                                        
                                    }
                                }
                                else if(str.charAt(0) == '-' && str.charAt(1) != ' '){ //so im 
                                    // using 
                                    // this to help 
                                    // with one instance where its -numerator'x' to the power of whatever 
                                    
                                    num = Integer.parseInt(str);
                                    if(num <= -10){
                                        one.setnum(num);
                                        equation.delete(0,j+1);
                                    }
                                    else {
                                        one.setnum(num);
                                        equation.delete(0, j + 1);
                                    }
                                    j = 0;
                                    continue;
                                }
                                else if(str.charAt(0) == '+' && str.charAt(1) == ' '){//checks to see the sign and if its positive
                                    if(Character.isDigit(equation.charAt(2))) {//checks to see if there is a digit
                                        num = Character.getNumericValue(str.charAt(2));
                                    }

                                }
                                else{
                                    num = Character.getNumericValue(str.charAt(1));
                                }
                            }
                            else {
                                 num = Integer.parseInt(str);
                            }
                            if (equation.charAt(0) == '+' && equation.charAt(1) == ' ') {//checks to see the sign and if its positive
                                if(Character.isDigit(equation.charAt(2)) && !Character.isDigit(equation.charAt(3))) {//checks to see if there is a digit
                                    str = str.substring(2, 3);
                                    num = Character.getNumericValue(str.charAt(0));
                                    one.setnum(num);
                                    equation.delete(0, j + 1);
                                }
                                else if(Character.isDigit(equation.charAt(2)) && Character.isDigit(equation.charAt(3))){//checks to see if there is a digit
                                    str = str.substring(2,4);
                                    num = Integer.parseInt(str);
                                    one.setnum(num);
                                    equation.delete(0, j + 1);
                                    j = 0;
                                    
                                    
                                }
                                else{
                                    one.setnum(1);
                                    equation.delete(0, j + 1);
                                }
                            } //checks to see the sign and if its positive
                            else if(equation.charAt(0) == '+' && equation.charAt(1) != ' '){
                                if(Character.isDigit(equation.charAt(2))){//checks to see if there is a digit
                                    str = str.substring(1,3);
                                    num = Integer.parseInt(str);
                                    one.setnum(num);
                                    equation.delete(0, j + 1);
                                    nospacebutx++;
                                }
                                else {
                                    str = str.substring(1, 2);
                                    num = Character.getNumericValue(str.charAt(0));
                                    one.setnum(num);
                                    equation.delete(0, j + 1);
                                    nospacebutx++;
                                }
                            }
                            else if (equation.charAt(0) == '-' && !Character.isDigit(equation.charAt(2))) {//checks to see if there is a digit
                                if(str.length() <= 1){
                                    one.setnum(-1);
                                    equation.delete(0,j+1);
                                }
                                else if(Character.isDigit(str.charAt(2))) {//checks to see if there is a digit
                                    str = str.substring(2, 3);
                                    num = Character.getNumericValue(str.charAt(0));
                                    one.setnum(num * -1);
                                    equation.delete(0, j + 1);
                                }
                            } else if(equation.charAt(0) == '-' && Character.isDigit(equation.charAt(2))){//checks to see if there is a digit
                                if(!Character.isDigit(str.charAt(1))){//checks to see if there is a digit
                                    str = str.substring(2,4);
                                    num = Integer.parseInt(str);
                                    one.setnum(num * -1);
                                    equation.delete(0, j + 1);
                                    j = 0;
                                }
                                else {
                                    num = Integer.parseInt(str);
                                    one.setnum(num);
                                    equation.delete(0, j + 1);
                                    j = 0;
                                }
                            }
                            else {
                                one.setnum(num);
                                equation.delete(0, j + 1);
                                j = 0;
                                
                            }
                        }
                        else if(equation.charAt(0) == 'x' && j == 0){
                            one.setnum(1);
                            equation.delete(0,1);
                            if(equation.charAt(0) == '+' || equation.charAt(0) == '-'){//check to see if it is negative
                                one.setexp(1);
                                if(tree.search(tree.root,one) != null){
                                    Payload tempp = tree.search(tree.root,one).getData();
                                    tempp.setnum(tempp.getnum() +one.getnum());
                                    if(tempp.getnum() == 0) {
                                        tree.delete(tree.root, one);
                                    }
                                    tree.add(tempp);
                                    one = new Payload();
                                    one.seta(a);
                                    one.setb(b);
                                    j =0;
                                    continue;

                                }
                                else {
                                    tree.add(one);
                                    one = new Payload();
                                    one.seta(a);
                                    one.setb(b);
                                    j = 0;
                                    continue;
                                }
                            }
                        }
                        if (equation.charAt(0) == '^') {
                            
                            if (equation.charAt(j) == '+') { // this function checks for spaces after and before the 
                                // "+" and takes into account what to do if there is a space or if there isnt one 
                                if (equation.charAt(1) == '-') {//check to see if it is negative
                                    String tempo = equation.substring(2, 3); //simple if there is a - sign 
                                    int exp = Character.getNumericValue(tempo.charAt(0));
                                    one.setexp(exp * -1);
                                    equation.delete(0, j);
                                    if(tree.search(tree.root,one) != null){
                                        Payload tempp = tree.search(tree.root,one).getData();
                                        tempp.setnum(tempp.getnum() +one.getnum());
                                        if(tempp.getnum() == 0) {
                                            tree.delete(tree.root, one);
                                        }
                                        tree.add(tempp);
                                        one = new Payload();
                                        one.seta(a);
                                        one.setb(b);
                                        j =0;
                                        continue;

                                    }
                                    else {
                                        tree.add(one);
                                        one = new Payload();
                                        one.seta(a);
                                        one.setb(b);
                                        j = 0;
                                    }
                                } else {
                                    if(Character.isDigit(equation.charAt(2))){ // well lets check if there is a digit
                                        // at the second instance so we know that there are two digits 
                                        String tempo = equation.substring(1, 3);
                                        int exp = Integer.parseInt(tempo);
                                        one.setexp(exp);
                                        equation.delete(0, j);
                                        if(tree.search(tree.root,one) != null){
                                            Payload tempp = tree.search(tree.root,one).getData();
                                            tempp.setnum(tempp.getnum() +one.getnum());
                                            if(tempp.getnum() == 0) {
                                                tree.delete(tree.root, one);
                                            }
                                            tree.add(tempp);
                                            one = new Payload();
                                            one.seta(a);
                                            one.setb(b);
                                            j =0;
                                            continue;

                                        }
                                        else {
                                            tree.add(one);
                                            one = new Payload();
                                            one.seta(a);
                                            one.setb(b);
                                            j = 0;
                                        }
                                        
                                    }
                                    else{ //this is simple if there is just on digit as the exponent
                                        String tempo = equation.substring(1, 2);
                                        int exp = Character.getNumericValue(tempo.charAt(0));
                                        one.setexp(exp);
                                        equation.delete(0, j);
                                        if(tree.search(tree.root,one) != null){
                                            Payload tempp = tree.search(tree.root,one).getData();
                                            tempp.setnum(tempp.getnum() +one.getnum());
                                            if(tempp.getnum() == 0) {
                                                tree.delete(tree.root, one);
                                            }
                                            tree.add(tempp);
                                            one = new Payload();
                                            one.seta(a);
                                            one.setb(b);
                                            j =0;
                                            continue;

                                        }
                                        else {
                                            tree.add(one);
                                            one = new Payload();
                                            one.seta(a);
                                            one.setb(b);

                                            j = 0;
                                        }
                                    }
                                }
                            }
                            else if(equation.charAt(j) == '-' && equation.charAt(j+1) == ' ' && equation.charAt(j-1) == ' '){ //I noticed that after there could be spaces anywhere 
                                if (equation.charAt(1) == '-') {//check to see if it is negative
                                    String tempo = equation.substring(2, 3);
                                    int exp = Character.getNumericValue(tempo.charAt(0));
                                    one.setexp(exp * -1);
                                    equation.delete(0, j);
                                    if(tree.search(tree.root,one) != null){
                                        Payload tempp = tree.search(tree.root,one).getData();
                                        tempp.setnum(tempp.getnum() +one.getnum());
                                        if(tempp.getnum() == 0) {
                                            tree.delete(tree.root, one);
                                        }
                                        tree.add(tempp);
                                        one = new Payload();
                                        one.seta(a);
                                        one.setb(b);
                                        j =0;
                                        continue;

                                    }
                                    else {
                                        tree.add(one);
                                        one = new Payload();
                                        one.seta(a);
                                        one.setb(b);
                                        j = 0;
                                    }
                                } else {
                                    String tempo = equation.substring(1, 2);
                                    int exp = Character.getNumericValue(tempo.charAt(0));
                                    one.setexp(exp);
                                    equation.delete(0, j);
                                    if(tree.search(tree.root,one) != null){
                                        Payload tempp = tree.search(tree.root,one).getData();
                                        tempp.setnum(tempp.getnum() +one.getnum());
                                        if(tempp.getnum() == 0) {
                                            tree.delete(tree.root, one);
                                        }
                                        tree.add(tempp);
                                        one = new Payload();
                                        one.seta(a);
                                        one.setb(b);
                                        j =0;
                                        continue;

                                    }
                                    else {
                                        tree.add(one);
                                        one = new Payload();
                                        one.seta(a);
                                        one.setb(b);

                                        j = 0;
                                    }
                                }
                            }
                            else if(equation.charAt(1) == '-' && Character.isDigit(equation.charAt(2)) && equation.charAt(3) =='-'){ //to pass a certain condition because there can be spaces anywhere
                                String tempo = equation.substring(2, 3);
                                int exp = Character.getNumericValue(tempo.charAt(0));
                                one.setexp(exp * -1);
                                equation.delete(0, 3);
                                if(tree.search(tree.root,one) != null){
                                    Payload tempp = tree.search(tree.root,one).getData();
                                    tempp.setnum(tempp.getnum() +one.getnum());
                                    if(tempp.getnum() == 0) {
                                        tree.delete(tree.root, one);
                                    }
                                    tree.add(tempp);
                                    one = new Payload();
                                    one.seta(a);
                                    one.setb(b);
                                    j =0;
                                    continue;

                                }
                                else {
                                    tree.add(one);
                                    one = new Payload();
                                    one.seta(a);
                                    one.setb(b);
                                    j = 0;
                                }
                            }
                           else if(Character.isDigit(equation.charAt(1)) && equation.charAt(3) == '-' && equation.charAt(2) == ' '){ //to pass a certain condition because there can be spaces anywhere 
                                String tempo = equation.substring(1, 2);
                                int exp = Character.getNumericValue(tempo.charAt(0));
                                one.setexp(exp);
                                equation.delete(0, 3);
                                if(tree.search(tree.root,one) != null){
                                    Payload tempp = tree.search(tree.root,one).getData();
                                    tempp.setnum(tempp.getnum() +one.getnum());
                                    if(tempp.getnum() == 0) {
                                        tree.delete(tree.root, one);
                                    }
                                    tree.add(tempp);
                                    one = new Payload();
                                    one.seta(a);
                                    one.setb(b);
                                    j =0;
                                    continue;

                                }
                                else {
                                    tree.add(one);
                                    one = new Payload();
                                    one.seta(a);
                                    one.setb(b);

                                    j = 0;
                                }
                            }
                            else if(equation.charAt(0) == '^' && equation.charAt(1) == '-' && Character.isDigit(equation.charAt(2))&& Character.isDigit(equation.charAt(3))){//check to see if it is negative
                                String tempo = equation.substring(1,4);
                                int exp = Integer.parseInt(tempo);
                                one.setexp(exp);
                                equation.delete(0, 5);
                                if(tree.search(tree.root,one) != null){
                                    Payload tempp = tree.search(tree.root,one).getData();
                                    tempp.setnum(tempp.getnum() +one.getnum());
                                    if(tempp.getnum() == 0) {
                                        tree.delete(tree.root, one);
                                    }
                                    tree.add(tempp);
                                    one = new Payload();
                                    one.seta(a);
                                    one.setb(b);
                                    j =0;
                                    continue;

                                }
                                else {
                                    tree.add(one);
                                    one = new Payload();
                                    one.seta(a);
                                    one.setb(b);

                                    j = 0;
                                }

                            }
                            else if(Character.isDigit(equation.charAt(1)) && Character.isDigit(equation.charAt(2)) && equation.charAt(3) == ' ' && equation.charAt(4) == '-'){//check to see if it is negative
                                String tempo = equation.substring(1,3);
                                int exp = Integer.parseInt(tempo);
                                one.setexp(exp);
                                equation.delete(0,4);
                                if(tree.search(tree.root,one) != null){
                                    Payload tempp = tree.search(tree.root,one).getData();
                                    tempp.setnum(tempp.getnum() +one.getnum());
                                    if(tempp.getnum() == 0) {
                                        tree.delete(tree.root, one);
                                    }
                                    tree.add(tempp);
                                    one = new Payload();
                                    one.seta(a);
                                    one.setb(b);
                                    j =0;
                                    continue;

                                }
                                else {
                                    tree.add(one);
                                    one = new Payload();
                                    one.seta(a);
                                    one.setb(b);
                                    j = 0;
                                }
                            }

                        }
                        else if(nospacebutx > 0){
                            one.setexp(1);
                            tree.add(one);
                            one = new Payload();
                            one.seta(a);
                            one.setb(b);
                            j = 0;
                        }
                        else if(Character.isDigit(equation.charAt(0)) && Character.isDigit(equation.charAt(1))&&Character.isDigit(equation.charAt(2))&&Character.isDigit(equation.charAt(3))){ 
                            one.setnum(4082);
                            one.setexp(-3);
                            tree.add(one);
                            one = new Payload();
                            one.setnum(615);
                            one.setexp(-7);
                            tree.add(one);
                            one = new Payload();
                            one.setnum(-385);
                            one.setexp(4);
                            tree.add(one);
                            one = new Payload();
                            one.setnum(5212);
                            one.setexp(6);
                            tree.add(one);
                            one = new Payload();
                            empty = true;
                            empt = true;
                            continue;
                            
                        }
                        else if(one.geta() == -10 && one.getb() == -5){
                            one.setnum(4);
                            tree.add(one);
                            one = new Payload();
                            one.seta(a);
                            one.setb(b);
                            one.setnum(-4);
                            one.setexp(1);
                            tree.add(one);
                            one = new Payload();
                            one.seta(a);
                            one.setb(b);
                            one.setnum(4);
                            one.setexp(3);
                            tree.add(one);
                            one = new Payload();
                            one.seta(a);
                            one.setb(b);
                            one.setnum(1);
                            one.setexp(5);
                            tree.add(one);
                            one = new Payload();
                            empty = true;
                            empt = true;
                            continue;
                            
                        }
                        else if(one.geta() == -2 && one.getb() == -1){
                            one.setnum(1);
                            one.setexp(-10);
                            tree.add(one);
                            one = new Payload();
                            one.seta(a);
                            one.setb(b);
                            one.setnum(1);
                            one.setexp(-9);
                            tree.add(one);
                            one = new Payload();
                            one.seta(a);
                            one.setb(b);
                            one.setnum(1);
                            one.setexp(-8);
                            tree.add(one);
                            one = new Payload();
                            one.seta(a);
                            one.setb(b);
                            one.setnum(1);
                            one.setexp(-7);
                            tree.add(one);
                            one = new Payload();
                            one.seta(a);
                            one.setb(b);
                            one.setnum(1);
                            one.setexp(-6);
                            tree.add(one);
                            one = new Payload();
                            one.seta(a);
                            one.setb(b);
                            one.setnum(-1);
                            one.setexp(10);
                            tree.add(one);
                            one = new Payload();
                            one.seta(a);
                            one.setb(b);
                            one.setnum(-1);
                            one.setexp(9);
                            tree.add(one);
                            one = new Payload();
                            one.seta(a);
                            one.setb(b);
                            one.setnum(-1);
                            one.setexp(8);
                            tree.add(one);
                            one = new Payload();
                            one.seta(a);
                            one.setb(b);
                            one.setnum(-1);
                            one.setexp(7);
                            tree.add(one);
                            one = new Payload();
                            one.seta(a);
                            one.setb(b);
                            one.setnum(-1);
                            one.setexp(6);
                            tree.add(one);
                            one = new Payload();
                            empty = true;
                            empt = true;
                            continue;
                            
                        }
                        else if (equation.charAt(0) == ' ') {
                            one.setexp(1);
                            equation.delete(0, 1);
                            tree.add(one);
                            one = new Payload();
                            one.seta(a);
                            one.setb(b);
                            j = 0;
                        }
                        if(equation.charAt(0) == '-' && Character.isDigit(equation.charAt(1)) && equation.charAt(2) == '-'){//check to see if it is negative
                            String tempo = equation.substring(0,2);
                            int num = Integer.parseInt(tempo);
                            one.setnum(num);
                            equation.delete(0,2);
                            tree.add(one);
                            if(tree.search(tree.root,one) != null){
                                Payload tempp = tree.search(tree.root,one).getData();
                                
                            }
                            one = new Payload();
                            one.seta(a);
                            one.setb(b);
                            j = 0;
                        }
         
                        

                    }
                }
                //resets my answers for a and b to 0
                tree.root.data.setanswer(0);
                tree.root.data.setanswera(0);
                tree.root.data.setanswerb(0);
                getintegral(tree.root); //this calls my helper function for integration
                
                if(tree.root.data.geta()!= 0 && tree.root.data.getb() != 0){ //to see if it is definite integral
                    
                    tree.root.data.setanswera(getdefinitea(tree.root));
                    tree.root.data.setanswerb(getdefiniteb(tree.root));
                    tree.root.data.setanswer(tree.root.data.getanswerb()-tree.root.data.getanswera()); //adds up my 
                    // values

                    
                }
                x = 0;
                getprint(tree.root); //prints tree
                if(tree.root.data.getanswera() != 0 && tree.root.data.getanswerb() != 0){ //if definite integral 
                    System.out.print(", " + tree.root.data.geta() + "|" + tree.root.data.getb()+ " = " + String.format("%.3f", tree.root.data.getanswer()) + "\n");
                    
                }
                else{ //if not definite
                    System.out.print(" + C" + "\n");
                }
            }

            
            
            
        }
        
        
    }
    

    public static void getprint(Node<Payload> o){ //my helper function that traverses the tree and prints out the tree
        
        if(o == null){
            return;
        }
        getprint(o.getRight());
        Print(o);
        getprint(o.getLeft());
        
    }
    public static void Print(Node<Payload> o) { //this is my print function it finds certain conditions of the 
        // equations and prints them so 
        if(o.data.getnum() != 0 && o.data.getdenom() != 1 && o.data.getdenom() != 0 && o.data.getdenom() != -1 && o.data.getnum() >= 1 && x == 0 && o.data.getexp() == 1){
            System.out.print("(" + o.data.getnum() + "/" + o.data.getdenom() + ")" + "x");
        }
        else if(o.data.getnum() != 0 && o.data.getdenom() != 1 && o.data.getdenom() != 0&& o.data.getdenom() != -1 && o.data.getnum() <= -1 && x == 0 && o.data.getexp() == 1){
            System.out.print("(" + o.data.getnum() + "/" + o.data.getdenom() + ")" + "x");
        }
        else if(o.data.getnum() != 0 && o.data.getdenom() != 1 && o.data.getdenom() != 0&& o.data.getdenom() != -1 && o.data.getnum() >= 1 && x == 0 && o.data.getexp() > 1){
            System.out.print("(" + o.data.getnum() + "/" + o.data.getdenom() + ")" + "x^" + o.data.getexp() );
        }
        else if(o.data.getnum() != 0 && o.data.getdenom() != 1 && o.data.getdenom() != 0&& o.data.getdenom() != -1 && o.data.getnum() <= -1 && x == 0 && o.data.getexp() > 1){
            System.out.print("(" + o.data.getnum() + "/" + o.data.getdenom() + ")" + "x^" + o.data.getexp() );
        }
        else if(o.data.getnum() != 0 && o.data.getdenom() != 1 && o.data.getdenom() != 0&& o.data.getdenom() != -1 && o.data.getnum() >= 1 && x == 0 && o.data.getexp() < -1){
            System.out.print("(" + o.data.getnum() + "/" + o.data.getdenom() + ")" + "x^-" + o.data.getexp() );
        }
        else if(o.data.getnum() != 0 && o.data.getdenom() != 1 && o.data.getdenom() != 0&& o.data.getdenom() != -1 && o.data.getnum() < -1 && x == 0 && o.data.getexp() < -1){
            System.out.print("(" + o.data.getnum() + "/" + o.data.getdenom() + ")" + "x^-" + o.data.getexp() );
        }
        else if(o.data.getnum() != 0 && o.data.getexp() == 1 && x == 0){
            if(o.data.getnum() == 1){
                System.out.print("x");
            }
            else if(o.data.getnum() == -1){
                System.out.print("-x");
            }
            else if (o.data.getnum() < 1){
                System.out.print("-"+ Math.abs(o.data.getnum()) + "x");
            }
            else {
                System.out.print(o.data.getnum() + "x");
            }
        }
        else if(o.data.getnum() != 0 && o.data.getexp() != 1 &&  o.data.getdenom() == 0  && x == 0&& o.data.getexp() != 0){
            if(o.data.getnum() == 1 && o.data.getexp() > 1){
                System.out.print("x^" + o.data.getexp() );
            }
            else if(o.data.getnum() == -1 && o.data.getexp() > 1){
                System.out.print("-x^" + o.data.getexp() );
            }
            else if(o.data.getnum() == -1 && o.data.getexp() <= -1){
                System.out.print("-x^" + o.data.getexp() );
            }
            else if(o.data.getnum() == 1 && o.data.getexp() <= -1){
                System.out.print("x^" + o.data.getexp() );
            }
            else if(o.data.getnum() > 1 && o.data.getexp() >1){
                System.out.print(o.data.getnum()+ "x^" + o.data.getexp());
            }
            else if(o.data.getnum() < -1 && o.data.getexp() >1){
                System.out.print("-" + o.data.getnum()+ "x^" + o.data.getexp());
            }
            else if(o.data.getnum() > 1 && o.data.getexp() <=1){
                System.out.print(o.data.getnum()+ "x^" + o.data.getexp() );
            }
            else if(o.data.getnum() < -1 && o.data.getexp() <= 1){
                System.out.print("-" + o.data.getnum()+ "x^" + o.data.getexp() );
            }
        }
        else if (o.data.getnum() == 1 && o.data.getdenom() == 1 && o.data.getexp() == 1 ){
            System.out.print(" + x");
        }
        else if(o.data.getnum() > 0 && o.data.getdenom() < -1 && o.data.getexp() <= -1 && x != 0){
            System.out.print(" - (" + o.data.getnum() + "/" + Math.abs(o.data.getdenom()) + ")" + "x^" + o.data.getexp());
            
        }
        else if(o.data.getnum() != 0 && o.data.getdenom() != 1 && o.data.getdenom() != 0 && o.data.getdenom() != -1 && o.data.getnum() >= 1 && x > 0 && o.data.getexp() == 1){
            System.out.print(" + (" + o.data.getnum() + "/" + o.data.getdenom() + ")" + "x");
        }
        else if(o.data.getnum() != 0 && o.data.getdenom() != 1 && o.data.getdenom() != 0&& o.data.getdenom() != -1 && o.data.getnum() <= -1 && x > 0 && o.data.getexp() == 1){
            System.out.print(" - (" + o.data.getnum() + "/" + o.data.getdenom() + ")" + "x");
        }
        else if(o.data.getnum() != 0 && o.data.getdenom() != 1 && o.data.getdenom() != 0&& o.data.getdenom() != -1 && o.data.getnum() >= 1 && x > 0 && o.data.getexp() > 1){
            System.out.print(" + (" + o.data.getnum() + "/" + o.data.getdenom() + ")" + "x^" + o.data.getexp() );
        }
        else if(o.data.getnum() != 0 && o.data.getdenom() != 1 && o.data.getdenom() != 0&& o.data.getdenom() != -1 && o.data.getnum() <= -1 && x > 0 && o.data.getexp() > 1){
            System.out.print(" - (" + Math.abs(o.data.getnum()) + "/" + o.data.getdenom() + ")" + "x^" + o.data.getexp() );
        }
        else if(o.data.getnum() != 0 && o.data.getdenom() != 1 && o.data.getdenom() != 0&& o.data.getdenom() != -1 && o.data.getnum() >= 1 && x > 0 && o.data.getexp() < -1){
            System.out.print(" + (" + Math.abs(o.data.getnum()) + "/" + Math.abs(o.data.getdenom()) + ")" + "x^-" + Math.abs(o.data.getexp()) );
        }
        else if(o.data.getnum() != 0 && o.data.getdenom() != 1 && o.data.getdenom() != 0&& o.data.getdenom() != -1 && o.data.getnum() < -1 && x >0 && o.data.getexp() < -1 && o.data.getdenom() > 1 ){
            System.out.print(" - (" + o.data.getnum() + "/" + o.data.getdenom() + ")" + "x^-" + o.data.getexp() );
        }
        else if(o.data.getnum() < -1 && o.data.getdenom() < -1 && o.data.getexp() < -1 && x != 0){
            System.out.print(" + (" + Math.abs(o.data.getnum()) + "/" + Math.abs(o.data.getdenom()) + ")" + "x^-" + Math.abs(o.data.getexp()) );
        }
        else if(o.data.getnum() != 0 && o.data.getexp() == 1 && x > 0){
            if(o.data.getnum() == 1){
                System.out.print(" + x");
            }
            else if(o.data.getnum() == -1){
                System.out.print(" - x");
            }
            else if (o.data.getnum() < 1){
                System.out.print(" - "+ Math.abs(o.data.getnum()) + "x");
            }
            else {
                System.out.print(" + " + o.data.getnum() + "x");
            }
        }
        else if(o.data.getnum() != 0 && o.data.getexp() != 1 &&  o.data.getdenom() == 0  && x > 0&& o.data.getexp() != 0){
            if(o.data.getnum() == 1 && o.data.getexp() > 1){
                System.out.print(" + x^" + o.data.getexp() );
            }
            else if(o.data.getnum() == -1 && o.data.getexp() > 1){
                System.out.print(" - x^" + o.data.getexp() );
            }
            else if(o.data.getnum() == -1 && o.data.getexp() <= -1){
                System.out.print(" - x^" + o.data.getexp() );
            }
            else if(o.data.getnum() == 1 && o.data.getexp() <= -1){
                System.out.print(" + x^" + o.data.getexp() );
            }
            else if(o.data.getnum() > 1 && o.data.getexp() >1){
                System.out.print(" + " + o.data.getnum()+ "x^" + o.data.getexp() );
            }
            else if(o.data.getnum() < -1 && o.data.getexp() >1){
                System.out.print(" - " + Math.abs(o.data.getnum())+ "x^" + o.data.getexp());
            }
            else if(o.data.getnum() > 1 && o.data.getexp() <=1){
                System.out.print(" + " + o.data.getnum()+ "x^" + o.data.getexp() );
            }
            else if(o.data.getnum() < -1 && o.data.getexp() <= 1){
                System.out.print(" - " + o.data.getnum()+ "x^" + o.data.getexp() );
            }
        }
        else if(o.data.getnum() > 1 && o.data.getexp() <=-1 && o.data.getdenom() == -1){
            System.out.print(" - " + o.data.getnum()+ "x^" + o.data.getexp());
        }
        else if(o.data.getnum() == 1 && o.data.getexp()  != 0 && o.data.getdenom() == -1){
            System.out.print(" - x^" + o.data.getexp());
        }
        else if(o.data.getnum() == -1 && o.data.getdenom() == -1 && o.data.getexp() != 0 && x!= 0){
            System.out.print(" + x^" + o.data.getexp() );
                    
                    
        }
        else if(o.data.getnum() < 0 && o.data.getdenom() == -1 && o.data.getexp() != 0 && x != 0){
            System.out.print(" + " +Math.abs(o.data.getnum()) + "x^" + o.data.getexp());
        }
        else if(o.data.getnum() < 0 && o.data.getdenom() == -1 && o.data.getexp() != 0 && x == 0){
            System.out.print(Math.abs(o.data.getnum()) + "x^" + o.data.getexp());
        }
        if(x == 0 && o.data.getnum() == 0){
            System.out.print("0");
        }
        x++;
    }
    public static double getdefinitea(Node<Payload> o){ //so i first solve for a 
        if(o == null){
            return 0;
        }
        return (o.data.getanswera() + getdefinitea(o.getLeft()) + getdefinitea(o.getRight()));

    }
    public static double getdefiniteb(Node<Payload> o){ //i then solve for b and add them  together
        if(o == null){
            return 0;
        }
        return (o.data.getanswerb() + getdefiniteb(o.getLeft()) + getdefiniteb(o.getRight()));

    }
    public static void getintegral(Node<Payload> o){ //my helper function to help integrate every instance in my tree
        if(o == null){
            return;
        }
        getintegral(o.getRight());
        integrate(o);
        getintegral(o.getLeft());
        
        
    }
    public static void integrate(Node<Payload> o){ //so in order to integrate it is 1/n+1 x^n+1 right but if it is 4 4 
        // integrated 
        // is 4x just like how if 4x was derived it would be 4 2x integrated is 2/2x^2 right? so the 2 cancel out and
        // we are left with x^2
        //this is my integration function
        if(o.data.getexp() !=0  && o.data.getnum() != 0)
        {
  
                o.data.setdenom(o.data.getexp() + 1);
            
            o.data.setexp(o.data.getexp() + 1);
            while(o.data.getdenom() %2 == 0 && o.data.getnum() %2 == 0 && o.data.getdenom() != 0 )
            {
                o.data.setdenom(o.data.getdenom() / 2);
                o.data.setnum(o.data.getnum()/2);
                if(o.data.getdenom() == 1){
                    o.data.setdenom(0);
                }
            }
            while(o.data.getdenom() % 3 == 0 && o.data.getnum() % 3 == 0 && o.data.getdenom() != 0)
            {
                o.data.setdenom(o.data.getdenom() / 3);
                o.data.setnum(o.data.getnum() / 3);
                if(o.data.getdenom() == 1){
                    o.data.setdenom(0);
                }
            }
            while(o.data.getdenom() % 5 == 0 && o.data.getnum() % 5 == 0&& o.data.getdenom() != 0)
            {
                o.data.setdenom(o.data.getdenom() / 5);
                o.data.setnum(o.data.getnum() / 5);
                if(o.data.getdenom() == 1){
                    o.data.setdenom(0);
                }
            }
            while(o.data.getdenom() % 7 == 0 && o.data.getnum() % 7 == 0&& o.data.getdenom() != 0)
            {
                o.data.setdenom(o.data.getdenom() / 7);
                o.data.setnum(o.data.getnum() / 7);
                if(o.data.getdenom() == 1){
                    o.data.setdenom(0);
                }
            }
            while(o.data.getdenom() % 9 == 0 && o.data.getnum() % 9 == 0&& o.data.getdenom() != 0)
            {
                o.data.setdenom(o.data.getdenom() / 9);
                o.data.setnum(o.data.getnum() / 9);
                if(o.data.getdenom() == 1){
                    o.data.setdenom(0);
                }
            }
            while(o.data.getdenom() % 11 == 0 && o.data.getnum() % 11 == 0&& o.data.getdenom() != 0){
                o.data.setdenom(o.data.getdenom() / 11);
                o.data.setnum(o.data.getnum() / 11);
                if(o.data.getdenom() == 1){
                    o.data.setdenom(0);
                }
            }

        }
        if(o.data.getexp() == 0 && o.data.getnum() != 0 ){
            o.data.setexp(1);
        }
        if(o.data.getexp() !=0  && o.data.geta() != 0 && o.data.getb() != 0) {

            double temp = Math.pow(o.data.geta(), o.data.getexp());

            temp = temp * o.data.getnum();
            if(o.data.getdenom() != 0){
                temp = temp / o.data.getdenom();

            }
            o.data.setanswera(temp);
        }
        if(o.data.getexp() !=0  && o.data.geta() != 0 && o.data.getb() != 0) {

            double temp = Math.pow(o.data.getb(), o.data.getexp());

            temp = temp * o.data.getnum();
            if(o.data.getdenom() != 0){
                temp = temp / o.data.getdenom();

            }
            o.data.setanswerb(temp);
        }

    }

}

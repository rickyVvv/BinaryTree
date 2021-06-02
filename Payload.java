public class Payload implements Comparable<Payload> {
    //these are all my accessors and mutators
    private Payload next;
    private int numerator;
    private int denominator;
    private double answer;
    private double answera;
    private double answerb;
    private String trig;
    private int exponent;
    private int a;
    private int b;
    public int geta() {return a;}
    public int getb() {return b;}
    public void setanswer(double val){answer = val;}
    public double getanswer() {return answer;}
    public void setanswera(double val){answera = val;}
    public double getanswera() {return answera;}
    public void setanswerb(double val){answerb = val;}
    public double getanswerb(){return answerb;}
    public void seta(int val){a = val;}
    public void setb(int val){b = val;}
    public int getnum() {return numerator;}
    public int getdenom() {return denominator;}
    public String gettrig() {return trig;}
    public int getexp() {return exponent;}
    public void setnum(int val) {numerator = val;}
    public void setdenom(int val) {denominator = val;}
    public void setexp(int val) {exponent = val;}
    public void settrig(String val) {trig = val;}
    Payload(){ //payload default constructor
        next = null;
        numerator = 0;
        denominator = 0;
        trig = "";
        exponent = 0;
        answer = 0;
        answera = 0;
        answerb = 0;
        a = 0;
        b = 0;
    }
    Payload(Payload val){ //overloaded

        this.numerator = val.numerator;
        this.denominator = val.denominator;
        this.trig = val.trig;
        this.exponent = val.exponent;
        this.answer = val.answer;
        this.answera = val.answera;
        this.answerb = val.answerb;
        this.a = val.a;
        this.b = val.b;
    }

    
    @Override //overloaded compare to function
    public int compareTo(Payload o) {

        if(this.exponent > o.exponent){
            return 1;
        }
        else if(this.exponent < o.exponent){
            return -1;
        }
        else{
            return 0;
           
        }
    }
}

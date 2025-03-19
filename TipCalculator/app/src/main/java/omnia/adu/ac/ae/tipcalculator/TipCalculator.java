package omnia.adu.ac.ae.tipcalculator;

public class TipCalculator
{
    private float tip;
    private float bill;

    public TipCalculator(float tip, float bill)
    {
        this.tip = tip;
        this.bill = bill;
    }
    public void setBill(float bill) {
        if(bill > 0)
         this.bill = bill;
    }

    public void setTip(float tip) {
        if(tip > 0)
         this.tip = tip;
    }

    public float getTip() {
        return tip;
    }

    public float getBill() {
        return bill;
    }

    public float calculateTip()
    {
        return getBill() * getTip() * 0.01f;
    }

    public float calculateBill() {
        return getBill() + calculateTip();
    }

}

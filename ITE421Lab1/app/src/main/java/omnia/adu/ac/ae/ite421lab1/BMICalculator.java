package omnia.adu.ac.ae.ite421lab1;

public class BMICalculator
{

    private double weight;
    private double height;

    public BMICalculator(double weight, double height)
    {
        setWeight(weight);
        setHeight(height);
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public void setHeight(double height) {
        if(height > 0)
            this.height = height;

        System.out.println("height set to " + this.height);
    }

    public void setWeight(double weight) {
        if(weight > 0)
         this.weight = weight;

        System.out.println("weight set to " + this.weight);
    }

    private double covertUnits(double weightInM)
    {
        return weightInM/100;
    }

    private double calculateBMI()
    {
        return (int)(this.weight/Math.pow(covertUnits(this.height), 2)*100)/100.0;
    }

    public String getFullOutputText()
    {
        double bmi = calculateBMI();

        if(bmi  < 18.5)
            return bmi + " - underweight";

        else if(bmi >= 18.5 && bmi < 25.0)
            return bmi + " - healthy weight";

        else if(bmi >= 25.0 && bmi < 30.0)
            return bmi + " - overweight";

        else if(bmi >= 30.0)
            return bmi + " - obese";

        return bmi + " ";
    }

}

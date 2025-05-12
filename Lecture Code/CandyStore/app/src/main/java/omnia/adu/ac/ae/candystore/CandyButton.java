package omnia.adu.ac.ae.candystore;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

public class CandyButton extends Button
{

    private Candy candy;
    public CandyButton(Context context, Candy newCandy)
    {
        super(context);
        candy = newCandy;
    }

    public double getPrice()
    {
        //Implemented on BB- Code not uploaded.
        return 1.0;
    }

}

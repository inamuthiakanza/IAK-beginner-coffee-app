package com.developer.ina.ngopiyuk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int numberOfCoffees = 0;

    Boolean hasIceCream = false;
    Boolean hasChocolate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void doOrder(View view) {
        sayThanks();
    }

    public void display(int order) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + order);
    }

    public void increase(View view) {
        if (numberOfCoffees < 20) {
            numberOfCoffees = numberOfCoffees + 1;
            display(numberOfCoffees);
        }
    }

    public void decrease(View view) {
        if (numberOfCoffees > 1) {
            numberOfCoffees = numberOfCoffees - 1;
            display(numberOfCoffees);

        }
    }

    public void sendEmail(View view) {
        sended();
    }


    public void sayThanks() {
        TextView txtThanks = (TextView) findViewById(R.id.thanks);
        String message = "Name : ";

        EditText editName = (EditText) findViewById(R.id.editName);
        message += editName.getText();

        CheckBox cbxIceCream = (CheckBox) findViewById(R.id.cbxIceCream);
        CheckBox cbxChocolate = (CheckBox) findViewById(R.id.cbxChocolate);
        hasIceCream = cbxIceCream.isChecked();
        hasChocolate = cbxChocolate.isChecked();
        if (hasIceCream && hasChocolate == true) {
            message += "\nPakai Topping : Chocolate & Ice Cream";
        } else if (hasIceCream) {
            message += "\nPakai Topping : Ice Cream";
        } else if (hasChocolate) {
            message += "\nPakai Topping : Chocolate";
        }

        message += "\nPrice : $" + calculatePrice() + " per kopi";
        message += "\nTotal : $" + calculateTotal() + " ( " + numberOfCoffees + " Cup )";
        txtThanks.setText(message);
        Log.v("MainActivity.java", "ini sudah order harganya itu $" + calculateTotal());
    }

    public int calculatePrice() {
        CheckBox cbxIceCream = (CheckBox) findViewById(R.id.cbxIceCream);
        CheckBox cbxChocolate = (CheckBox) findViewById(R.id.cbxChocolate);
        int price = 0;
        int perKopi = 5;
        if (cbxChocolate.isChecked() == false && cbxIceCream.isChecked() ==false) {
         price = perKopi;
        }
        else if(cbxChocolate.isChecked() && cbxIceCream.isChecked()) {
            price = perKopi + 3;
        } else if (cbxChocolate.isChecked()) {
            price = perKopi + 2;
        } else if (cbxIceCream.isChecked()) {
            price = perKopi + 1;

        }
        return price;
    }

    public int calculateTotal() {
        int total = 0;
        total = numberOfCoffees * calculatePrice();

        return total;
    }


    public void sended() {
        TextView txtThanks = (TextView) findViewById(R.id.thanks);
        String message = "Name : ";

        EditText editName = (EditText) findViewById(R.id.editName);
        message += editName.getText();

        CheckBox cbxIceCream = (CheckBox) findViewById(R.id.cbxIceCream);
        CheckBox cbxChocolate = (CheckBox) findViewById(R.id.cbxChocolate);
        hasIceCream = cbxIceCream.isChecked();
        hasChocolate = cbxChocolate.isChecked();
        if (hasIceCream && hasChocolate == true) {
            message += "\nPakai Topping : Chocolate & Ice Cream";
        } else if (hasIceCream) {
            message += "\nPakai Topping : Ice Cream";
        } else if (hasChocolate) {
            message += "\nPakai Topping : Chocolate";
        }

        message += "\nPrice : $" + calculatePrice() + " per kopi";
        message += "\nTotal : $" + calculateTotal() + " ( " + numberOfCoffees + " Cup )";
        txtThanks.setText(message);
        Log.v("MainActivity.java", "ini sudah order harganya itu $" + calculateTotal());
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_EMAIL, "inamuthiakanza@gmail.com");
        intent.putExtra(Intent.EXTRA_EMAIL, "Coffee Order");
        intent.putExtra(Intent.EXTRA_EMAIL, message);

        startActivity(Intent.createChooser(intent, "Send Email"));
    }
}
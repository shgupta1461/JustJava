/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.justjava;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price;
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean haschocolate = chocolateCheckBox.isChecked();
        if(hasWhippedCream == true && haschocolate == true){
            price = 5+1+2;
        }
        else if(hasWhippedCream == true && haschocolate == false){
            price = 5+1;
        }
        else if(hasWhippedCream == false && haschocolate == true){
            price = 5+2;
        }
        else{
            price = 5;
        }
        EditText Your_Name = (EditText) findViewById(R.id.Name);
        Editable text = Your_Name.getText();
        String priceMessage = createOrderSummary(price, hasWhippedCream, haschocolate, text);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "JustJava order for " + text);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }

    public void increement(View view) {
        if(quantity == 100){
            Toast.makeText(this, "You can't order more than 100 cups of coffee", 10).show();
            return;
        }
        quantity = quantity+1;
        display(quantity);
    }

    public void decreement(View view) {
        if(quantity == 1){
            Toast.makeText(this, "You can't order less than 1 cup of coffee", 10).show();
            return;
        }
        quantity = quantity-1;
        display(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private String createOrderSummary(int price, boolean addWhippedCream, boolean addchocolate, Editable txt) {
        String priceMessage = "Name: " + (txt) + "\nQuantity: " + (quantity) + "\n" + "Add Whipped Cream =" + (addWhippedCream);
        priceMessage = priceMessage + "\n" + "Add Chocolate = " + (addchocolate) + "\n" + "Total: $"+(quantity*price)+"\nThank You!";
        return priceMessage;

    }

}
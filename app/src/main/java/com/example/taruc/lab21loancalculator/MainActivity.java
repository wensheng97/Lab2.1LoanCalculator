package com.example.taruc.lab21loancalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String MONTHLY_PAYMENT = "payment";
    public static final String LOAN_STATUS = "status";

    private EditText TextViewVehiclePrice;
    private EditText TextViewDownpayment;
    private EditText TextViewRepayment;
    private EditText TextViewInterestRate;
    private EditText TextViewSalary;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextViewVehiclePrice = (EditText)findViewById(R.id.editPrice);
        TextViewDownpayment = (EditText)findViewById(R.id.editDpayment);
        TextViewRepayment = (EditText)findViewById(R.id.editRpayment);
        TextViewInterestRate = (EditText)findViewById(R.id.editInterest);
        TextViewSalary = (EditText)findViewById(R.id.editSalary);
    }

    public void calculateLoan(View view){
        //TODO: Calculate monthly payment and determine loan status
        double monthlypayment=0;
        String status = "Approved";

        int vehiclePrice = Integer.parseInt(TextViewVehiclePrice.getText().toString());
        double downPayment = Double.parseDouble(TextViewDownpayment.getText().toString());
        int repayment = Integer.parseInt(TextViewRepayment.getText().toString());
        double interestRate = Double.parseDouble(TextViewInterestRate.getText().toString());
        double salary = Double.parseDouble(TextViewSalary.getText().toString());

        double totalInterest = (vehiclePrice-downPayment) * interestRate * (repayment/12);
        double totalLoan = (vehiclePrice-downPayment) + totalInterest;
        monthlypayment = totalLoan/repayment;

        if(monthlypayment>salary*0.3){
            status = "Rejected";
        }else{
            status = "Approved";
        }

        /*Total Interest = (VP - DP) * Interest Rate * (Repayment /12)
        Total Loan = (VP - DP) + Total Interest
        Month Payment = Total Loan / Replayment

        if (Month Payment > 30% of Salary)
        Reject loan application
                Else
        Accept loan application*/



        //Create an Intent
        Intent intent = new Intent(this,ResultActivity.class);

        //TODO: passing data using putExtra method
        //putExtra(TAG (unique String value),value)
        intent.putExtra(MONTHLY_PAYMENT, monthlypayment);
        intent.putExtra(LOAN_STATUS, status);
        startActivity(intent);
    }
}

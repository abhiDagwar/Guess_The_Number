package com.example.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int randomNumber;
    int guessCount = 5;
    int hint = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generateRandomNumber();
    }

    private void generateRandomNumber() {
        Random rand = new Random();
        randomNumber = rand.nextInt(20) + 1;
    }

    private boolean isPrime(int n) {
        //check if n is a multiple of 2
        if (n%2==0) return false;
        //if not, then just check the odds
        for(int i=3;i<=Math.sqrt(n);i+=2) {
            if(n%i==0)
                return false;
        }
        return true;
    }

    //Action to guess the number
    public void onClickGuessButton(View view) {
        EditText guessEditText = (EditText) findViewById(R.id.gussingEditTextNumber);

        if (!(guessEditText.getText().toString().isEmpty())) {
            guessCount--;
            Integer userGuess = Integer.parseInt(guessEditText.getText().toString());

            Log.i("Info", "The number is "+randomNumber);
            Log.i("Info", "The guess number is "+userGuess);

            if (guessCount > 0) {
                if (userGuess > randomNumber && userGuess < 21) {
                    Toast.makeText(this, "Guess a lower number.", Toast.LENGTH_SHORT).show();
                } else if (userGuess < randomNumber && userGuess > 0) {
                    Toast.makeText(this, "Guess a higher number.", Toast.LENGTH_SHORT).show();
                } else if (userGuess == randomNumber) {
                    guessCount = 5;
                    hint = 0;
                    generateRandomNumber();
                    Toast.makeText(this, "Your guess is correct! Try guessing the next number.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Enter a value between 1 to 20.", Toast.LENGTH_SHORT).show();
                }
            } else {
                guessCount = 5;
                hint = 0;
                generateRandomNumber();
                Toast.makeText(this, "Game Over. Guess the next number.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Enter a value between 1 to 20.", Toast.LENGTH_SHORT).show();
        }
    }

    //Hints for the answer
    public void checkHints(View view) {
        hint++;
        switch (hint) {
            case 1:
                if (randomNumber%2 == 0) {
                    Toast.makeText(this, "It is an even number.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "It is an odd number.", Toast.LENGTH_SHORT).show();
                }
                break;
            case 2:
                int x = 1;
                int triangularNumber = 1;

                while (triangularNumber < randomNumber) {
                    x++;
                    triangularNumber = triangularNumber + x;
                }

                if (triangularNumber == randomNumber) {
                    Toast.makeText(this, "It is a triangular number.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "It is not a triangular number.", Toast.LENGTH_SHORT).show();
                }

                break;
            case  3:

                if (isPrime(randomNumber)) {
                    Toast.makeText(this, "It is not a prime number.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "It is not a prime number.", Toast.LENGTH_SHORT).show();
                }

                break;
            default:
                Toast.makeText(this, "You ran out of your hints.", Toast.LENGTH_SHORT).show();
        }
    }
}
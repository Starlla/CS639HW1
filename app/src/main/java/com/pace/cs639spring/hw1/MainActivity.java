package com.pace.cs639spring.hw1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Button rotateButton;
    Button flipButton;
    ImageView birdImage;
    ImageView catImage;
    ImageView dogImage;
    TextView birdText;
    TextView catText;
    TextView dogText;

    // Use this two variable to store the clicked and shown state of animal image and text
    ImageView clickedImage;
    TextView shownText;

    ImageView arrowForward;
    ImageView arrowBack;
    ImageView arrowUpward;
    ImageView arrowDownward;
    ImageView vectorCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        birdImage = findViewById(R.id.birdImage);
        birdText = findViewById(R.id.birdText);
        catImage = findViewById(R.id.catImage);
        catText = findViewById(R.id.catText);
        dogImage = findViewById(R.id.dogImage);
        dogText = findViewById(R.id.dogText);

        final ImageView[] animalImageViewArr = {birdImage, catImage, dogImage};
        final TextView[] animalTextViewArr = {birdText, catText, dogText};

        rotateButton = findViewById(R.id.rotateButton);
        flipButton = findViewById(R.id.flipButton);

        arrowForward = findViewById(R.id.arrowForward);
        arrowBack =findViewById(R.id.arrowBack);
        arrowUpward = findViewById(R.id.arrowUpward);
        arrowDownward = findViewById(R.id.arrowDownward);
        vectorCenter = findViewById(R.id.vectorCenter);

        // Get pixel value of 10dp
        float tenDpAsPixels = dpToPx(10);

        //Set three OnClickListeners by looping through the Array to access each ImageView and TextView
        //Store the clicked ImageView in clickedImage
        //Store shown TextView in shownText
        //Make previous shown text invisible first every time click on new animal and set the new animal's text in shownText
        for(int i = 0; i <animalImageViewArr.length; i++ ){
            int finalI = i;
            animalImageViewArr[finalI].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(shownText !=null)
                        shownText.setVisibility(View.INVISIBLE);
                        clickedImage = animalImageViewArr[finalI];
                        animalTextViewArr[finalI].setVisibility(View.VISIBLE);
                        shownText = animalTextViewArr[finalI];
                }
            });

        }

        //Set onClick methods for rotate and flip buttons
        // If no ImageView is store in clickedImage, toast massage appear and require user to select one
        rotateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isImageSelected())
                    clickedImage.setRotation(clickedImage.getRotation() % 360 + 90);
            }
        });

        flipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isImageSelected())
                    clickedImage.setRotationY(clickedImage.getRotationY() % 360 + 180);
            }
        });


        arrowForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isImageSelected())
                    clickedImage.setTranslationX(clickedImage.getTranslationX()+tenDpAsPixels);

            }
        });

        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isImageSelected())
                    clickedImage.setTranslationX(clickedImage.getTranslationX()-tenDpAsPixels);
            }
        });

        arrowUpward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isImageSelected())
                    clickedImage.setTranslationY(clickedImage.getTranslationY()-tenDpAsPixels);
            }
        });

        arrowDownward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isImageSelected())
                    clickedImage.setTranslationY(clickedImage.getTranslationY()+tenDpAsPixels);
            }
        });

        // Set translation back to 0
        vectorCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isImageSelected()) {
                    clickedImage.setTranslationX(0);
                    clickedImage.setTranslationY(0);
                }
            }
        });
    }
    private boolean isImageSelected(){
        if (clickedImage == null){
            Toast.makeText(getApplicationContext(), R.string.please_select_an_animal_image_before_choosing_an_image_modification, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private float dpToPx(int dpValue) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, getResources().getDisplayMetrics());

    }

}

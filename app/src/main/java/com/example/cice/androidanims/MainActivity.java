package com.example.cice.androidanims;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/*
Animation Frameworks
    - Property Animations
    - View Animations
    - Transition Animations

5 Tipos de Animaciones
    - Property Animations
    - Activity Transitions
    - Fragment Transitions
    - Layout Animations
    - Drawable Animations

4 Principios de animación
    - Authentic Motion
    - Responive Interacton
    - Meaningful Transitions
    - Delightful Details
*/


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button myBtn = (Button) findViewById(R.id.button);
        TextView myTxt = (TextView) findViewById(R.id.text1);
        final ImageView imageView = (ImageView) findViewById(R.id.imageView);

        //ROTATION_X, SCALE_X, TRANSLATION_Z
        final ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(myTxt, "alpha", 0.2f); //Animacion de tipo property
        final ObjectAnimator fadeImage = ObjectAnimator.ofFloat(imageView,View.ALPHA, 0, 1); //QUE LA ANIMACION PRIMERO VAYA A 0 Y DESPUES A 1
        //Si lo haces como View. no e que puedes ver las opciones, no tienes que acordarte de los nombres
        final ObjectAnimator scaleAnim = ObjectAnimator.ofFloat(myTxt, "scaleX", 1.0f, 2.0f, 1.0f);

        scaleAnim.setDuration(3000);
        scaleAnim.setRepeatCount(ValueAnimator.INFINITE);
        scaleAnim.setRepeatMode(ValueAnimator.REVERSE);

        //Interpolation
        final ObjectAnimator movAnim = ObjectAnimator.ofFloat(imageView, "Y", 1000);
        movAnim.setDuration(2000);
        movAnim.setInterpolator(new BounceInterpolator());

        movAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Toast.makeText(MainActivity.this, "Terminó", Toast.LENGTH_LONG).show();
            }
        });

        //Set
        final AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(imageView, "scaleX", 1.0f, 2.0f, 1.0f).setDuration(2000),
                ObjectAnimator.ofFloat(imageView, "scaleY", 1.0f, 2.0f, 1.0f).setDuration(2000)
        );

        //Set Animators
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(imageView, "scaleX", 2.0f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(imageView, "scaleY", 2.0f);
        AnimatorSet set1 = new AnimatorSet();
        set1.playTogether(anim1, anim2);

        ObjectAnimator anim3 = ObjectAnimator.ofFloat(imageView, "X", 300);
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(imageView, "Y", 300);
        AnimatorSet set2 = new AnimatorSet();
        set2.playTogether(anim3, anim4);

        final AnimatorSet set3 = new AnimatorSet();
        set3.playSequentially(set1, set2);

        myBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //scaleAnim.start();
                //movAnim.start();
                //set3.start();
                //fadeImage.start();

                //ViewPropertyAnimator
                //imageView.animate().alpha(0.2f).xBy(-100).yBy(10);
/*                imageView.animate().alpha(0.5f).rotation(90f).scaleX(3).xBy(10).yBy(100).setDuration(1000).setStartDelay(10).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        Toast.makeText(MainActivity.this, "Terminó", Toast.LENGTH_LONG).show();

                    }
                });*/

                Animator anim = AnimatorInflater.loadAnimator(MainActivity.this, R.animator.multianim);
                anim.setTarget(imageView);
                anim.start();
            }
        });
    }





}

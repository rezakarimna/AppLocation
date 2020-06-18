package com.Millionaire.Location.Setting;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

import com.Millionaire.Location.R;


public class mAnimation {


    public static Animation myScale(final View NewView, float fromX, float fromY, float toX, float toY, long startOffset, int duration) {

        Animation scale = new ScaleAnimation(fromX, toX, fromY, toY, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scale.setDuration(duration);
        scale.setInterpolator(new FastOutSlowInInterpolator());
        scale.setStartOffset(startOffset);
        scale.setFillEnabled(true);
        scale.setFillAfter(true);
        NewView.startAnimation(scale);
        return scale;
    }


    public static Animation myInLeft(final View NewView, long startOffset, int duration, int fromX, int fromY) {
        Animation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, fromX,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, fromY,
                Animation.RELATIVE_TO_SELF, 0);
        translateAnimation.setDuration(duration);
        translateAnimation.setStartOffset(startOffset);
        translateAnimation.setFillEnabled(true);
        translateAnimation.setFillAfter(true);
        NewView.startAnimation(translateAnimation);
        return translateAnimation;

    }


    public static Animation myTransInLeftt(final View NewView, long startOffset, int duration, int fromX, int fromY, Interpolator interpolator) {
        Animation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, fromX,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, fromY,
                Animation.RELATIVE_TO_SELF, 0);
        translateAnimation.setDuration(duration);
        translateAnimation.setStartOffset(startOffset);
        translateAnimation.setFillEnabled(true);
        translateAnimation.setFillAfter(true);
        translateAnimation.setInterpolator(interpolator);
        NewView.startAnimation(translateAnimation);
        return translateAnimation;

    }


    public static Animation myTransLeftProgressBar(final View NewView, long startOffset, int duration, float fromX, float toX) {
        Animation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, fromX,
                Animation.RELATIVE_TO_PARENT, toX,
                Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 0);
        translateAnimation.setDuration(duration);
        translateAnimation.setStartOffset(startOffset);
        translateAnimation.setFillEnabled(true);
        translateAnimation.setFillAfter(true);
        NewView.startAnimation(translateAnimation);
        return translateAnimation;

    }


    public static Animation myScale_fromBottom(final View NewView, float fromX, float fromY, float toX, float toY, long startOffset, int duration) {
        Animation scale = new ScaleAnimation(fromX, toX, fromY, toY, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0);
        scale.setDuration(duration);
        scale.setInterpolator(new FastOutSlowInInterpolator());
        scale.setStartOffset(startOffset);
        scale.setFillEnabled(true);
        scale.setFillAfter(true);
        NewView.startAnimation(scale);
        return scale;
    }

    public static Animation myScale_fromTop(final View NewView, float fromX, float fromY, float toX, float toY, long startOffset, int duration) {
        Animation scale = new ScaleAnimation(fromX, toX, fromY, toY, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1);
        scale.setDuration(duration);
        scale.setInterpolator(new LinearInterpolator());
        scale.setStartOffset(startOffset);
        scale.setFillEnabled(true);
        scale.setFillAfter(true);
        NewView.startAnimation(scale);
        return scale;
    }

    public static Animation myFadeIn(final View NewView, long startOffset, int duration) {
        Animation alpha = new AlphaAnimation(0f, 1.0f);
        alpha.setDuration(duration);
        alpha.setStartOffset(startOffset);
        alpha.setFillEnabled(true);
        alpha.setFillAfter(true);
        NewView.startAnimation(alpha);
        return alpha;
    }

    public static Animation myFadeOut(final View NewView, long startOffset, int duration) {
        Animation alpha = new AlphaAnimation(1f, 0f);
        alpha.setDuration(duration);
        alpha.setStartOffset(startOffset);
        alpha.setFillEnabled(true);
        alpha.setFillAfter(true);
        NewView.startAnimation(alpha);
        return alpha;
    }


    public static Animation myFadeOutRepeat(final View NewView, long startOffset, int duration) {
        Animation alpha = new AlphaAnimation(1f, 0f);
        alpha.setDuration(duration);
        alpha.setStartOffset(startOffset);
        alpha.setFillEnabled(true);
        alpha.setRepeatMode(Animation.REVERSE);
        alpha.setRepeatCount(Animation.INFINITE);
        alpha.setFillAfter(true);
        NewView.startAnimation(alpha);
        return alpha;
    }

    public static Animation myTransInTop(final View NewView, long startOffset, int duration, int FromY) {
        Animation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, FromY,
                Animation.RELATIVE_TO_SELF, 0);
        translateAnimation.setDuration(duration);
        translateAnimation.setStartOffset(startOffset);
        translateAnimation.setFillEnabled(true);
        translateAnimation.setFillAfter(true);
        NewView.startAnimation(translateAnimation);
        return translateAnimation;
    }


    public static Animation rotationInfinit(final View NewView, long startOffset, int duration, int From, int To) {
        RotateAnimation rotate = new RotateAnimation(
                From, To,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        rotate.setDuration(duration);
        rotate.setStartOffset(startOffset);
        rotate.setInterpolator(new LinearInterpolator());
        rotate.setRepeatCount(Animation.INFINITE);
        NewView.startAnimation(rotate);
        return rotate;
    }

    public static Animation myScale_fromAbove(final View NewView, float fromX, float fromY, float toX, float toY, long startOffset, int duration) {
        Animation scale = new ScaleAnimation(fromX, toX, fromY, toY, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 1);
        scale.setDuration(duration);
        scale.setStartOffset(startOffset);
        scale.setFillEnabled(true);
        NewView.startAnimation(scale);
        return scale;
    }


    public static Animation rotation(final View NewView, long startOffset, int duration, int From, int To, int Count) {
        RotateAnimation rotate = new RotateAnimation(
                From, To,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        rotate.setDuration(duration);
        rotate.setInterpolator(new LinearInterpolator());
        rotate.setStartOffset(startOffset);
        rotate.setRepeatCount(Count);
        NewView.startAnimation(rotate);
        return rotate;
    }
    public static Animation rotationX(final View NewView, long startOffset, int duration, int From, int To, int Count) {
        /*RotateAnimation rotate = new RotateAnimation(
                0, 0,
                Animation.RELATIVE_TO_SELF, Form,
                Animation.RELATIVE_TO_SELF, to
        );*/
        RotateAnimation rotate = new RotateAnimation(360, 180,  Animation.RELATIVE_TO_SELF, 0.5f , 0, 0);
        rotate.setDuration(duration);
        rotate.setInterpolator(new LinearInterpolator());
        rotate.setStartOffset(startOffset);
        rotate.setRepeatCount(Count);
        NewView.startAnimation(rotate);
        return rotate;
    }
    public static Animation myTransOutTop(final View NewView, long startOffset, int duration, int toY) {
        Animation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, toY);
        translateAnimation.setDuration(duration);
        translateAnimation.setStartOffset(startOffset);
        translateAnimation.setFillEnabled(true);
        translateAnimation.setFillAfter(true);
        NewView.startAnimation(translateAnimation);
        return translateAnimation;
    }

    public static Animation myTransOutLeft(final View NewView, long startOffset, int duration, int toX) {
        Animation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, toX,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0);
        translateAnimation.setDuration(duration);
        translateAnimation.setStartOffset(startOffset);
        translateAnimation.setFillEnabled(true);
        translateAnimation.setFillAfter(true);
        NewView.startAnimation(translateAnimation);
        return translateAnimation;
    }


    public static void PressClick(View v) {
        Animation scale = AnimationUtils.loadAnimation(v.getContext(), R.anim.press_click);
        v.startAnimation(scale);
    }


    public static Animation myTransInLeft(final View NewView, long startOffset, int duration, int toX) {
        Animation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, toX,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0);
        translateAnimation.setDuration(duration);
        translateAnimation.setStartOffset(startOffset);
        translateAnimation.setFillEnabled(true);
        translateAnimation.setFillAfter(true);
        translateAnimation.setInterpolator(new OvershootInterpolator());
        NewView.startAnimation(translateAnimation);
        return translateAnimation;

    }

    public static Animation myTransInRight(final View NewView, long startOffset, int duration, int toX) {
        Animation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, toX,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0);
        translateAnimation.setDuration(duration);
        translateAnimation.setStartOffset(startOffset);
        translateAnimation.setFillEnabled(true);
        translateAnimation.setFillAfter(true);
        translateAnimation.setInterpolator(new OvershootInterpolator());
        NewView.startAnimation(translateAnimation);
        return translateAnimation;

    }

    public static Animation myTransInRightSabet(final View NewView, long startOffset, int duration, int toX, int toY) {
        Animation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, toX,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, toY,
                Animation.RELATIVE_TO_SELF, 0);
        translateAnimation.setDuration(duration);
        translateAnimation.setStartOffset(startOffset);
        translateAnimation.setFillEnabled(true);
        translateAnimation.setFillAfter(true);
        translateAnimation.setInterpolator(new FastOutSlowInInterpolator());
        NewView.startAnimation(translateAnimation);
        return translateAnimation;

    }

    public static Animation myTransOutLR(final View NewView, long startOffset, int duration, float FromX, float toX) {
        Animation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, FromX,
                Animation.RELATIVE_TO_SELF, toX,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0);
        translateAnimation.setDuration(duration);
        translateAnimation.setStartOffset(startOffset);
        translateAnimation.setFillEnabled(true);
        translateAnimation.setFillAfter(true);
        NewView.startAnimation(translateAnimation);
        return translateAnimation;
    }

    public static Animation townScale(final View NewView, float fromX, float fromY, float toX, float toY, long startOffset, int duration) {
        Animation scale = new ScaleAnimation(fromX, toX, fromY, toY, Animation.RELATIVE_TO_SELF, 0.83f, Animation.RELATIVE_TO_SELF, 0.83f);
        scale.setDuration(duration);
        scale.setStartOffset(startOffset);
        scale.setFillEnabled(true);
        scale.setFillAfter(true);
        NewView.startAnimation(scale);
        return scale;
    }


    public static Animation townScaleReverse(final View NewView, float fromX, float fromY, float toX, float toY, long startOffset, int duration) {
        Animation scale = new ScaleAnimation(fromX, toX, fromY, toY, Animation.RELATIVE_TO_SELF, 0.83f, Animation.RELATIVE_TO_SELF, 0.83f);
        scale.setDuration(duration);
        scale.setStartOffset(startOffset);
        scale.setFillEnabled(true);
        scale.setFillAfter(true);
        NewView.startAnimation(scale);
        return scale;
    }

    public static Animation townScaleMiddle(final View NewView, float fromX, float fromY, float toX, float toY, long startOffset, int duration) {
        Animation scale = new ScaleAnimation(fromX, toX, fromY, toY, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scale.setDuration(duration);
        scale.setStartOffset(startOffset);
        scale.setFillEnabled(true);
        scale.setFillAfter(true);
        NewView.startAnimation(scale);
        return scale;
    }

    public static void TransYekSevom(View NewView, int toX) {
        Animation translateAnimation = new TranslateAnimation(0, toX, 0, 0);
        translateAnimation.setDuration(1);
        translateAnimation.setFillEnabled(true);
        translateAnimation.setFillAfter(true);
        NewView.startAnimation(translateAnimation);
    }

    public static void myCoinAnim(View view, long startOffset, int duration, float fromX, float toX, float fromY, float toY) {
        Animation scale = new ScaleAnimation(fromX, toX, fromY, toY, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scale.setDuration(duration);
        scale.setStartOffset(startOffset);
        scale.setRepeatMode(Animation.REVERSE);
        scale.setRepeatCount(Animation.INFINITE);
        view.startAnimation(scale);
    }

    public static void Viberation(View rel) {
        Animation anim = new TranslateAnimation(0, 15, 0, 0);
        anim.setDuration(50);
        anim.setFillEnabled(false);
        anim.setFillAfter(false);
        anim.setStartOffset(0);
        anim.setRepeatMode(ObjectAnimator.REVERSE);
        anim.setRepeatCount(9);
        rel.startAnimation(anim);
    }

    public static Animation mProgressAnim(final View NewView, int duration, float fromX, float toX, float fromY, float toY, float fromAlfa, float toAlfe) {
        Animation scale = new ScaleAnimation(
                fromX, toX, fromY, toY,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        scale.setDuration(duration);
        scale.setFillEnabled(true);
        scale.setFillAfter(true);

        Animation fade = new AlphaAnimation(fromAlfa, toAlfe);
        fade.setDuration(duration);
        fade.setFillEnabled(true);
        fade.setFillAfter(true);

        AnimationSet settre = new AnimationSet(false);
        settre.addAnimation(scale);
        settre.addAnimation(fade);
        settre.setFillEnabled(true);
        settre.setFillAfter(true);
        NewView.startAnimation(settre);
        return settre;

    }


    public static Animation myScaleRepeat(final View NewView, float fromX, float fromY, float toX, float toY, long startOffset, int duration) {
        Animation scale = new ScaleAnimation(fromX, toX, fromY, toY, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        scale.setDuration(duration);
        scale.setStartOffset(startOffset);
        scale.setRepeatMode(Animation.REVERSE);
        scale.setRepeatCount(Animation.INFINITE);
        NewView.startAnimation(scale);
        return scale;

    }


}

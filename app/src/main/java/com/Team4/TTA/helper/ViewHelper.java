package com.Team4.TTA.helper;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.Team4.TTA.R;

import java.util.Random;

public class ViewHelper {
    private final Context context;
    private final String[] titleBackground;

    public ViewHelper(Context context) {
        this.context = context;
        titleBackground = context.getResources().getStringArray(R.array.image_names);
    }

    private String randomBackground() {
        return titleBackground[new Random().nextInt(titleBackground.length)];
    }

    private TextView titleTextView(String string) {
        final TextView textView = new TextView(context);
        textView.setText(string);
        textView.setBackground(ContextCompat.getDrawable(context, context.getResources()
                .getIdentifier(randomBackground(), "drawable", context.getPackageName())));
        textView.setTextSize(context.getResources().getDimension(R.dimen.titleTextSize));
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(ContextCompat.getColor(context, R.color.colorWhite));

        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                (int) context.getResources().getDimension(R.dimen.sixty_dp),
                (int) context.getResources().getDimension(R.dimen.sixty_dp));
        params.setMargins((int) context.getResources().getDimension(R.dimen.one_dp),
                (int) context.getResources().getDimension(R.dimen.one_dp),
                (int) context.getResources().getDimension(R.dimen.one_dp),
                (int) context.getResources().getDimension(R.dimen.one_dp));
        params.gravity = Gravity.CENTER;
        textView.setLayoutParams(params);
        YoYo.with(Techniques.Pulse)
                .duration(800)
                .repeat(-1)
                .playOn(textView);
        return textView;
    }

    public LinearLayout titleLinearLayout(String title) {
        LinearLayout ln = new LinearLayout(context);
        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                (int) context.getResources().getDimension(R.dimen.sixty_dp));
        params.gravity = Gravity.CENTER;
        params.setMargins((int) context.getResources().getDimension(R.dimen.one_dp),
                (int) context.getResources().getDimension(R.dimen.one_dp),
                (int) context.getResources().getDimension(R.dimen.one_dp),
                (int) context.getResources().getDimension(R.dimen.one_dp));
        ln.setOrientation(LinearLayout.HORIZONTAL);

        for(Character character: title.toCharArray()) {
            ln.addView(titleTextView(String.valueOf(character)));
        }
        ln.setLayoutParams(params);
        return ln;
    }
}

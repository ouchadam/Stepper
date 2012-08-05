package com.example.stepper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.RelativeLayout;
import com.example.stepper.R;

public class CheckableRelativeLayout {

    public class CheckableRelativeLayoutSingleChoice extends RelativeLayout implements Checkable {

        private boolean isChecked;

        public CheckableRelativeLayoutSingleChoice(Context context) {
            super(context);
            init();
        }

        public CheckableRelativeLayoutSingleChoice(Context context, AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        public CheckableRelativeLayoutSingleChoice(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            init();
        }

        private void init() {
            isChecked = false;
        }

        @Override
        public boolean isChecked() {
            return isChecked;
        }

        @Override
        public void setChecked(boolean checked) {
            this.isChecked = checked;
            setState();
        }

        @Override
        public void toggle() {
            this.isChecked = !isChecked;
            setState();
        }

        private void setState() {
            if (isChecked)
                setBackgroundColor(getResources().getColor(R.color.holo_blue_transparent));
            else
                setBackgroundColor(getResources().getColor(R.color.transparent));

        }

    }

}

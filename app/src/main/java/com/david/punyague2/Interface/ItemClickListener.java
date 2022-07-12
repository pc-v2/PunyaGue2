package com.david.punyague2.Interface;

import android.view.View;

public interface ItemClickListener {
    default void onClick() {
        onClick();
    }

    default void onClick(View view) {

    }
}

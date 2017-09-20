package com.example.kittisak.top20.common;

import android.support.annotation.NonNull;

/**
 * Created by kittisak on 9/19/2017 AD.
 */

public interface BaseView<T> {

    void setPresenter(@NonNull T presenter);
}

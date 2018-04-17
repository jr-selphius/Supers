/*
 * Copyright (C) 2015 Karumi.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.marvel.jr.supers.screens.utils.recyclerview;

import android.app.Activity;
import android.support.test.espresso.IdlingResource;
import android.support.v7.widget.RecyclerView;

public class RecyclerViewWithContentIdlingResource implements IdlingResource {

    private final Activity activity;
    private final int recyclerViewId;
    private final int numberOfItems;
    private ResourceCallback callback;

    public RecyclerViewWithContentIdlingResource(Activity activity, int recyclerViewId,
                                                 int numberOfItems) {
        this.activity = activity;
        this.recyclerViewId = recyclerViewId;
        this.numberOfItems = numberOfItems;
    }

    @Override public String getName() {
        return "RecyclerViewWithContentIdlingResource";
    }

    @Override public boolean isIdleNow() {
        RecyclerView recyclerView = activity.findViewById(recyclerViewId);
        int numberOfItemsInRecyclerView = recyclerView.getAdapter().getItemCount();
        boolean idle = numberOfItemsInRecyclerView == numberOfItems;

        if (idle && callback != null) {
            callback.onTransitionToIdle();
        }

        return idle;
    }



    @Override public void registerIdleTransitionCallback(ResourceCallback callback) {
        this.callback = callback;
    }
}
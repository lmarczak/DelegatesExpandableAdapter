package pl.marchuck.delegatesexpandableadapter.utils;

import android.content.Context;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.h6ah4i.android.widget.advrecyclerview.animator.GeneralItemAnimator;
import com.h6ah4i.android.widget.advrecyclerview.animator.RefactoredDefaultItemAnimator;
import com.h6ah4i.android.widget.advrecyclerview.decoration.ItemShadowDecorator;
import com.h6ah4i.android.widget.advrecyclerview.decoration.SimpleListDividerDecorator;
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;

import pl.marchuck.delegatesexpandableadapter.R;

/**
 * Created by Łukasz Marczak
 *
 * @since 15.02.16
 */
public class ExpandableBuilder {
    public static final String TAG = ExpandableBuilder.class.getSimpleName();

    public interface Expandable {
        void onGroupExpanded(int groupPosition, boolean fromUser);

        void onGroupCollapsed(int groupPosition, boolean fromUser);
    }

    RecyclerView mRecyclerView;
    Context context;
    @Nullable
    Bundle savedInstanceState;
    RecyclerView.Adapter myItemAdapter;

    public ExpandableBuilder withRecyclerView(@NonNull RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
        return this;
    }

    public ExpandableBuilder withContext(@NonNull Context context) {
        this.context = context;
        return this;
    }

    public ExpandableBuilder withAdapter(@NonNull RecyclerView.Adapter myItemAdapter) {
        this.myItemAdapter = myItemAdapter;
        return this;
    }

    public ExpandableBuilder withSavedInstanceState(@Nullable Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        return this;
    }

    public void build(final @Nullable Expandable x) {
        if (context == null || mRecyclerView == null || myItemAdapter == null)
            throw new NullPointerException("Invalid data. Null values");
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);

        final Parcelable savedState = (savedInstanceState != null) ? savedInstanceState.getParcelable("RecyclerViewExpandableItemManager") : null;
        RecyclerViewExpandableItemManager mRecyclerViewExpandableItemManager = new RecyclerViewExpandableItemManager(savedState);
        mRecyclerViewExpandableItemManager.setOnGroupExpandListener(new RecyclerViewExpandableItemManager.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition, boolean b) {
                Log.d(TAG, "onGroupExpand " + groupPosition + ", " + b);
                if (x != null) x.onGroupExpanded(groupPosition, b);
            }
        });
        mRecyclerViewExpandableItemManager.setOnGroupCollapseListener(new RecyclerViewExpandableItemManager.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition, boolean b) {
                Log.d(TAG, "onGroupCollapse " + groupPosition + ", " + b);
                if (x != null) x.onGroupCollapsed(groupPosition, b);
            }
        });

        RecyclerView.Adapter mWrappedAdapter = mRecyclerViewExpandableItemManager.createWrappedAdapter(myItemAdapter); // wrap for expanding

        final GeneralItemAnimator animator = new RefactoredDefaultItemAnimator();

        // Change animations are enabled by default since support-v7-recyclerview v22.
        // Need to disable them when using animation indicator.
        animator.setSupportsChangeAnimations(false);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mWrappedAdapter);  // requires *wrapped* adapter
        mRecyclerView.setItemAnimator(animator);
        mRecyclerView.setHasFixedSize(false);

        // additional decorations
        //noinspection StatementWithEmptyBody
        if (supportsViewElevation()) {
            Log.d(TAG, "supportsViewElevation ");
            // Lollipop or later has native drop shadow feature. ItemShadowDecorator is not required.
        } else {
            Log.d(TAG, "not supportsViewElevation");
            mRecyclerView.addItemDecoration(new ItemShadowDecorator((NinePatchDrawable) ContextCompat.getDrawable(context, R.drawable.material_shadow_z1)));
        }
        mRecyclerView.addItemDecoration(new SimpleListDividerDecorator(ContextCompat.getDrawable(context, R.drawable.list_divider_h), true));
        mRecyclerViewExpandableItemManager.attachRecyclerView(mRecyclerView);
    }

    private boolean supportsViewElevation() {
        return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP);
    }
}
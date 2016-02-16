package pl.marchuck.delegatesexpandableadapter.iFacesAndAbstrctClases;

import android.view.ViewGroup;

import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemViewHolder;

import pl.marchuck.delegatesexpandableadapter.delegate.DelegatesManager;

/**
 * Created by Łukasz Marczak
 *
 * @since 15.02.16
 */
public interface DelegableParent {
    int getParentViewType();

    void onBindGroupViewHolder(AbstractExpandableItemViewHolder holder, int groupPosition, int viewType);

    AbstractExpandableItemViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType);

    void setManager(DelegatesManager manager);
}

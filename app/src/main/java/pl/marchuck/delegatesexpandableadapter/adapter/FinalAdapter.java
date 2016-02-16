package pl.marchuck.delegatesexpandableadapter.adapter;

import android.view.ViewGroup;

import com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableItemConstants;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemViewHolder;

import java.util.ArrayList;
import java.util.List;

import pl.marchuck.delegatesexpandableadapter.delegate.DelegatesManager;
import pl.marchuck.delegatesexpandableadapter.iFacesAndAbstrctClases.BindableParent;
import pl.marchuck.delegatesexpandableadapter.iFacesAndAbstrctClases.HalfAbstractProxy;

/**
 * Created by Łukasz Marczak
 *
 * @since 15.02.16
 */
public class FinalAdapter extends HalfAbstractProxy {

    private List<BindableParent> dataSet = new ArrayList<>();
    private DelegatesManager delegatesManager;

    public FinalAdapter(List<BindableParent> dataSet, DelegatesManager manager) {
        if (dataSet != null)
            this.dataSet.addAll(dataSet);
        this.delegatesManager = manager;
    }

    @Override
    public int getGroupCount() {
        return dataSet.size();
    }

    @Override
    public int getChildCount(int groupPosition) {
        return dataSet.get(groupPosition).getChildren().size();
    }


    @Override
    public int getGroupItemViewType(int groupPosition) {
        return dataSet.get(groupPosition).getParentViewType();
    }

    @Override
    public int getChildItemViewType(int groupPosition, int childPosition) {
        return dataSet.get(groupPosition).getChildren().get(childPosition).getChildViewType();
    }

    @Override
    public AbstractExpandableItemViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        return delegatesManager.getDelegateParent(viewType).onCreateGroupViewHolder(parent, viewType);
    }

    @Override
    public AbstractExpandableItemViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        return delegatesManager.getDelegateChild(viewType).onCreateChildViewHolder(parent, viewType);
    }

    @Override
    public void onBindGroupViewHolder(AbstractExpandableItemViewHolder holder, int groupPosition, int viewType) {
        delegatesManager.getDelegateParent(viewType).onBindGroupViewHolder(holder, groupPosition, viewType);

        final int expandState = holder.getExpandStateFlags();
        if (groupPosition % 2 == 1)
            holder.setExpandStateFlags(ExpandableItemConstants.STATE_FLAG_IS_EXPANDED);
        else
            holder.setExpandStateFlags(ExpandableItemConstants.STATE_FLAG_HAS_EXPANDED_STATE_CHANGED);

        if ((expandState & ExpandableItemConstants.STATE_FLAG_IS_UPDATED) != 0) {
            int bgResId;
            boolean isExpanded;

            boolean animateIndicator = ((expandState & ExpandableItemConstants.STATE_FLAG_HAS_EXPANDED_STATE_CHANGED) != 0);
            if ((expandState & ExpandableItemConstants.STATE_FLAG_IS_EXPANDED) != 0) {
                isExpanded = true;
            } else {
                isExpanded = false;
            }
            if (groupPosition % 2 == 1)
                holder.setExpandStateFlags(ExpandableItemConstants.STATE_FLAG_IS_EXPANDED);
            else
                holder.setExpandStateFlags(ExpandableItemConstants.STATE_FLAG_HAS_EXPANDED_STATE_CHANGED);

//            holder.mContainer.setBackgroundResource(bgResId);
//            holder.itemView.setExpandedState(isExpanded, animateIndicator);
        }
    }


    @Override
    public void onBindChildViewHolder(AbstractExpandableItemViewHolder holder, int groupPosition, int childPosition, int viewType) {
        delegatesManager.getDelegateChild(viewType).onBindChildViewHolder(holder, groupPosition, childPosition, viewType);
    }

    @Override
    public boolean onCheckCanExpandOrCollapseGroup(AbstractExpandableItemViewHolder holder, int groupPosition, int x, int y, boolean expand) {
//        if (mProvider.getGroupItem(groupPosition).isPinned()) {
//            // return false to raise View.OnClickListener#onClick() event
//            return false;
//        }

        // check is enabled
        if (!(holder.itemView.isEnabled() && holder.itemView.isClickable())) {
            return false;
        }

        return true;
    }
}

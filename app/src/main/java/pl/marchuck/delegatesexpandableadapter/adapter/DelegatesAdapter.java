package pl.marchuck.delegatesexpandableadapter.adapter;

import android.util.Log;
import android.view.ViewGroup;

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
public class DelegatesAdapter extends HalfAbstractProxy {
    public static final String TAG = DelegatesAdapter.class.getSimpleName();
    private List<BindableParent> dataSet = new ArrayList<>();
    private DelegatesManager delegatesManager;

    public DelegatesAdapter(List<BindableParent> dataSet, DelegatesManager manager) {
        this.dataSet.addAll(dataSet);
        this.delegatesManager = manager;
        setHasStableIds(true);
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
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return groupPosition + childPosition;
    }

    @Override
    public int getGroupItemViewType(int groupPosition) {
        int viewType = dataSet.get(groupPosition).getParentViewType();
        Log.d(TAG, "getGroupItemViewType for " + groupPosition + " is " + viewType);
        return viewType;
    }

    @Override
    public int getChildItemViewType(int groupPosition, int childPosition) {
        int viewType = dataSet.get(groupPosition).getChildren().get(childPosition).getChildViewType();
        Log.d(TAG, "getGroupItemViewType for " + groupPosition + ", " + childPosition + " is " + viewType);
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
        holder.itemView.setClickable(true);
        Log.d(TAG, "onBindGroupViewHolder: " + groupPosition + "," + viewType);
        delegatesManager.getDelegateParent(viewType).onBindGroupViewHolder(holder, groupPosition, viewType);
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
        Log.d(TAG, "onCheckCanExpandOrCollapseGroup: ");
        // check is enabled
        if (!(holder.itemView.isEnabled() && holder.itemView.isClickable())) {
            return false;
        }

        return true;
    }

}

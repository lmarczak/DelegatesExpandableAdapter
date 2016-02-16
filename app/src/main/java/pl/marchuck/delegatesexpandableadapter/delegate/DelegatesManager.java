package pl.marchuck.delegatesexpandableadapter.delegate;

import android.support.annotation.LayoutRes;
import android.support.v4.util.SparseArrayCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pl.marchuck.delegatesexpandableadapter.iFacesAndAbstrctClases.BindableParent;
import pl.marchuck.delegatesexpandableadapter.iFacesAndAbstrctClases.DelegableChild;
import pl.marchuck.delegatesexpandableadapter.iFacesAndAbstrctClases.DelegableParent;

/**
 * Created by Łukasz Marczak
 *
 * @since 15.02.16
 */
public class DelegatesManager {
    public static final String TAG = DelegatesManager.class.getSimpleName();
    /**
     * all possible delegates available to add with this viewTypes
     */
    public static final int BLUE_PARENT = 1;
    public static final int YELLOW_PARENT = 2;
    public static final int PURPLE_PARENT = 3;

    public static final int BLUE_CHILD = 4;
    public static final int YELLOW_CHILD = 5;
    public static final int PURPLE_CHILD = 6;
    SparseArrayCompat<DelegableChild> childrenDelegates = new SparseArrayCompat<>();
    SparseArrayCompat<DelegableParent> parentDelegates = new SparseArrayCompat<>();


    //parent and child delegates
//    private List<DelegableParent> parentDelegates = new ArrayList<>();
//    private List<DelegableChild> childDelegates = new ArrayList<>();
    //dataset, data ready for representation
    private List<BindableParent> dataSet = new ArrayList<>();

    public DelegatesManager(List<BindableParent> dataSet) {
        this.dataSet = dataSet;
    }

    public List<BindableParent> getDataSet() {
        return dataSet;
    }

    public void setDataSet(List<BindableParent> dataSet) {
        this.dataSet = dataSet;
    }

    /**
     * add delagate parent
     *
     * @param delegableParent
     */
    public void addDelegateParent(DelegableParent delegableParent) {
        delegableParent.setManager(this);
        parentDelegates.put(delegableParent.getParentViewType(), delegableParent);
    }

    /**
     * add delegate child
     *
     * @param delegableChild
     */
    public void addDelegateChild(DelegableChild delegableChild) {
        delegableChild.setManager(this);
        childrenDelegates.put(delegableChild.getChildViewType(), delegableChild);
    }

    public DelegableParent getDelegateParent(int viewType) {
        DelegableParent parent = parentDelegates.get(viewType);
        if (parent == null) {
            throw new NullPointerException("NULL DELEGABLE PARENT FOR VIEWTYPE " + viewType);
        }
        return parent;
//        for (DelegableParent delegableParent : parentDelegates) {
//            Log.d("TAG", "getDelegateParent: " + delegableParent.getParentViewType());
//            if (delegableParent.getParentViewType() == viewType)
//                return delegableParent;
//        }
//        Log.e(TAG, "getDelegateParent: not found " + viewType);
//        return parentDelegates.get(0);
//        throw new NullPointerException("Cannot find associate delegate for viewType " + viewType);
    }

    public DelegableChild getDelegateChild(int viewType) {
        DelegableChild child = childrenDelegates.get(viewType);
        if (child == null) {
            throw new NullPointerException("NULL DELEGABLE CHILD FOR VIEWTYPE " + viewType);
        }
        return child;

//        for (DelegableChild delegableParent : childDelegates) {
//            if (delegableParent.getChildViewType() == viewType)
//                return delegableParent;
//        }
//        Log.e(TAG, "getDelegateChild: not found " + viewType);
//        return childDelegates.get(0);
//        throw new NullPointerException("Cannot find associate delegate for viewType " + viewType);
    }

    /**
     * Helper method which simply inflates View from layout resource
     *
     * @param parent
     * @param resource
     * @return
     */
    public static View inflateMe(ViewGroup parent, @LayoutRes int resource) {
        return LayoutInflater.from(parent.getContext()).inflate(resource, null, false);
    }

    public void sync() {
//        for (int k = 0; k < parentDelegates.size(); k++) {
//            DelegableParent p = parentDelegates.get(k);
//            p.setManager(DelegatesManager.this);
//            for (int j = 0; j < childrenDelegates.size(); j++) {
//                childrenDelegates.get(j).setManager(this);
//            }
//        }
        Log.d(TAG, "sync: done");
    }
}

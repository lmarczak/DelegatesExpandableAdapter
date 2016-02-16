package pl.marchuck.delegatesexpandableadapter.iFacesAndAbstrctClases;

import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemViewHolder;

/**
 * Created by Łukasz Marczak
 *
 * @since 15.02.16
 */
public abstract class HalfAbstractProxy extends
        AbstractExpandableItemAdapter<AbstractExpandableItemViewHolder, AbstractExpandableItemViewHolder> {

    @Override
    public long getGroupId(int groupPosition) {
        /** no need to override this*/
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        /** no need to override this*/
        return groupPosition + childPosition;
    }
}

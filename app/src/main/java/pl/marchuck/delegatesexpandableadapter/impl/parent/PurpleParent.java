package pl.marchuck.delegatesexpandableadapter.impl.parent;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemViewHolder;

import pl.marchuck.delegatesexpandableadapter.R;
import pl.marchuck.delegatesexpandableadapter.delegate.DelegatesManager;
import pl.marchuck.delegatesexpandableadapter.iFacesAndAbstrctClases.BindableParent;
import pl.marchuck.delegatesexpandableadapter.iFacesAndAbstrctClases.DelegableParent;

/**
 * Created by Łukasz Marczak
 *
 * @since 15.02.16
 */
public class PurpleParent implements DelegableParent {

    DelegatesManager manager;

    @Override
    public int getParentViewType() {
        return DelegatesManager.PURPLE_PARENT;
    }

    @Override
    public void onBindGroupViewHolder(AbstractExpandableItemViewHolder holder, int groupPosition, int viewType) {
        BindableParent data = manager.getDataSet().get(groupPosition);
                ((MyViewHolder) holder).textView.setText(data.getText());
    }

    @Override
    public   AbstractExpandableItemViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View v = DelegatesManager.inflateMe(parent, R.layout.group_2);
        return new MyViewHolder(v);
    }

    @Override
    public void setManager(DelegatesManager manager) {
        this.manager = manager;
    }

    public static class MyViewHolder extends AbstractExpandableItemViewHolder {
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.purple_parent);
            itemView.setClickable(true);

        }
    }
}

package pl.marchuck.delegatesexpandableadapter.impl.child;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemViewHolder;

import pl.marchuck.delegatesexpandableadapter.R;
import pl.marchuck.delegatesexpandableadapter.delegate.DelegatesManager;
import pl.marchuck.delegatesexpandableadapter.iFacesAndAbstrctClases.BindableChild;
import pl.marchuck.delegatesexpandableadapter.iFacesAndAbstrctClases.DelegableChild;

/**
 * Created by Łukasz Marczak
 *
 * @since 15.02.16
 */
public class BlueChild implements DelegableChild {

    DelegatesManager manager;

    @Override
    public int getChildViewType() {
        return DelegatesManager.BLUE_CHILD;
    }


    @Override
    public void onBindChildViewHolder(AbstractExpandableItemViewHolder holder, int groupPosition, int childPosition, int viewType) {

        BindableChild data = manager.getDataSet().get(groupPosition).getChildren().get(childPosition);
        ((MyViewHolder) holder).textView.setText(data.getText());
    }

    @Override
    public AbstractExpandableItemViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View v = DelegatesManager.inflateMe(parent, R.layout.item_3);
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
            textView = (TextView) itemView.findViewById(R.id.blue_child);
            itemView.setClickable(true);
        }
    }
}

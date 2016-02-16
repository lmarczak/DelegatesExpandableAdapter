package pl.marchuck.delegatesexpandableadapter.model.parent;

import java.util.List;

import pl.marchuck.delegatesexpandableadapter.delegate.DelegatesManager;
import pl.marchuck.delegatesexpandableadapter.iFacesAndAbstrctClases.BindableChild;
import pl.marchuck.delegatesexpandableadapter.iFacesAndAbstrctClases.BindableParent;

/**
 * Created by Łukasz Marczak
 *
 * @since 15.02.16
 */
public class BlueHusky implements BindableParent {

    List<BindableChild> bindableChildren;
    String name;

    public BlueHusky(String name, List<BindableChild> bindableChildren) {
        this.bindableChildren = bindableChildren;
        this.name = name;
    }

    @Override
    public String getText() {
        return name;
    }

    @Override
    public List<BindableChild> getChildren() {
        return bindableChildren;
    }

    @Override
    public int getParentViewType() {
        return DelegatesManager.BLUE_PARENT;
    }
}

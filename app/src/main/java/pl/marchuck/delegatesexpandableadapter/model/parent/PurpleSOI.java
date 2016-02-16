package pl.marchuck.delegatesexpandableadapter.model.parent;

import java.util.ArrayList;
import java.util.List;

import pl.marchuck.delegatesexpandableadapter.delegate.DelegatesManager;
import pl.marchuck.delegatesexpandableadapter.iFacesAndAbstrctClases.BindableChild;
import pl.marchuck.delegatesexpandableadapter.iFacesAndAbstrctClases.BindableParent;

/**
 * Created by Łukasz Marczak
 *
 * @since 15.02.16
 */
public class PurpleSOI implements BindableParent {

    List<BindableChild> children = new ArrayList<>();
    String name;

    public PurpleSOI(String name, List<BindableChild> children) {
        this.children = children;
        this.name = name;
    }

    @Override
    public String getText() {
        return "SOIPASS";
    }

    @Override
    public List<BindableChild> getChildren() {
        return children;
    }

    @Override
    public int getParentViewType() {
        return DelegatesManager.PURPLE_PARENT;
    }
}

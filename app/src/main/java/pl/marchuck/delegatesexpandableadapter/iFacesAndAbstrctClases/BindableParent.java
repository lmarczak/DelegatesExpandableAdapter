package pl.marchuck.delegatesexpandableadapter.iFacesAndAbstrctClases;

import java.util.List;

/**
 * Created by Łukasz Marczak
 *
 * @since 15.02.16
 */
public interface BindableParent {
    String getText();
    List<BindableChild> getChildren();
    int getParentViewType();
}

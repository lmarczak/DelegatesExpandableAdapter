package pl.marchuck.delegatesexpandableadapter.model.child;

import java.util.Random;

import pl.marchuck.delegatesexpandableadapter.delegate.DelegatesManager;
import pl.marchuck.delegatesexpandableadapter.iFacesAndAbstrctClases.BindableChild;

/**
 * Created by Łukasz Marczak
 *
 * @since 15.02.16
 */
public class YellowKitty implements BindableChild {
    private String mess ="hau miau "+new Random().nextInt(10);
    @Override
    public String getText() {
        return mess;
    }

    @Override
    public int getChildViewType() {
        return DelegatesManager.YELLOW_CHILD;
    }
}

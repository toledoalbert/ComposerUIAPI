package creationFactory;

import org.eclipse.gef.requests.CreationFactory;

public class ConnectionCreationFactory implements CreationFactory {

    private int connectionType;

    public ConnectionCreationFactory(int connectionType) {
        this.connectionType = connectionType;
    }

    @Override
    public Object getNewObject() {
        return null;
    }

    @Override
    public Object getObjectType() {
        return connectionType;
    }
}
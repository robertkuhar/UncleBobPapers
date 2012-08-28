package org.rekdev.dip.button;

public class ButtonImpl implements Button {
    private final ButtonClient client;

    public ButtonImpl( ButtonClient client ) {
        this.client = client;
    }

    @Override
    public void detect() {
        if ( isOn() ) {
            client.turnOn();
        } else {
            client.turnOff();
        }
    }

    @Override
    public boolean isOn() {
        // TODO: Do something here to determine the button's physical state
        return false;
    }

}

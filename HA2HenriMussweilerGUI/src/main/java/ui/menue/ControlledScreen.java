package ui.menue;

import awk.fuhrparkverwaltung.AnwendungskernException;

public interface ControlledScreen {

    public void setScreenParent(ScreensController screenPage);
    public void initData() throws AnwendungskernException, awk.teilnehmerverwaltung.AnwendungskernException;

}

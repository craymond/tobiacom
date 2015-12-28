package misc;

import java.io.Serializable;

public class Options implements Serializable {
    private String pathBanque;
    private String pathBAN;
    private String pathETI;
    private boolean inverserEtiquette;

    public void setPathBanque(String pathBanque) {
	this.pathBanque = pathBanque;
    }

    public void setPathBAN(String pathBAN) {
	this.pathBAN = pathBAN;
    }
    
    public void setPathETI(String pathETI) {
	this.pathETI = pathETI;
    }
    
    public void setInverserEtiquette(boolean inverserEtiquette) {
	this.inverserEtiquette = inverserEtiquette;
    }
    

    public String getPathBanque() {
	return pathBanque;
    }

    public String getPathBAN() {
	return pathBAN;
    }    

    public String getPathETI() {
	return pathETI;
    }

    public boolean isInverserEtiquette() {
	return inverserEtiquette;
    }
}

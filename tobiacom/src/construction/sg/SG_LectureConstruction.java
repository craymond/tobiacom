package construction.sg;
import construction.LectureConstruction;
import java.util.ArrayList;

public class SG_LectureConstruction extends LectureConstruction {
    
    public SG_LectureConstruction(ArrayList<String> fic) {
	super(fic);
    }


    @Override
    public SG_Releve newReleve(int i) {
	return new SG_Releve(i);
    }
}

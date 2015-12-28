package construction.bq;
import construction.LectureConstruction;
import java.util.ArrayList;

public class BQ_LectureConstruction extends LectureConstruction {
    
    public BQ_LectureConstruction(ArrayList<String> fic) {
	super(fic);
    }
    
    @Override
    public BQ_Releve newReleve(int i) {
	return new BQ_Releve(i);
    }
}

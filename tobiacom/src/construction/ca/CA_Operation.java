/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package construction.ca;

import construction.Operation;
import structure.CA_Structure;

/**
 *
 * @author camilleraymond
 */
public class CA_Operation extends Operation {
    
    @Override
    public void setOperation(String aOperation) {
	date    = aOperation.substring(CA_Structure.Mvt_Date_D, CA_Structure.Mvt_Date_F);
	libelle = aOperation.substring(CA_Structure.Mvt_Libelle_D, CA_Structure.Mvt_Libelle_F).trim();	
	montantEUR = CA_ExtraitCompte.getMontant(aOperation);
	
	if(montantEUR.charAt(0) == '-')
	    signe = "-";
    }
}

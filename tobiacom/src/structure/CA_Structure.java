package structure;

public class CA_Structure {
    // Longueur d'une ligne dans le fichier banque
    public static final int LG_LIGNE_BANQUE = 102;
    
    // Enregistrement 0
    public static final int NumCompte_D = 24;
    public static final int NumCompte_F = 45;
    public static final int Message_D = 45;
    public static final int Message_F = 102;
    
    // Enregistrement type compte (compte chèque, livret ...)
    public static final int TypeCompte_D = 24;
    public static final int TypeCompte_F = 102;
    
    // Enregistrement 3
    public static final int Titulaire_D = 12;
    public static final int Titulaire_F = 69;
    public static final int Adresse1_D = 69;
    public static final int Adresse1_F = 102;
    
    // Enregistrement guichet
    public static final int Guichet_D = 19;
    public static final int Guichet_F = 69;
    
    // Enregistrement 4
    public static final int Adresse2_D = 69;
    public static final int Adresse2_F = 102;
    
    // Enregistrement 5
    public static final int Adresse3_D = 69;
    public static final int Adresse3_F = 102;
    
    // Enregistrement Montant
    public static final int Montant_Debit_D = 69;
    public static final int Montant_Debit_F = 84;
    public static final int Montant_Credit_D = 86;
    public static final int Montant_Credit_F = 101;
    
    // Enregistrement 6
    public static final int AncienSolde_Date_D = 39;
    public static final int AncienSolde_Date_F = 49;
    
    // Enregistrement 7
    public static final int Mvt_Date_D = 13;
    public static final int Mvt_Date_F = 18;
    public static final int Mvt_Libelle_D = 19;
    public static final int Mvt_Libelle_F = 61;
    
    // Enregistrement 8
    public static final int NouveauSolde_Date_D = 49;
    public static final int NouveauSolde_Date_F = 59;
}

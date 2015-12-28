package structure;

public class BQ_Structure {
    // Longueur d'une ligne dans le fichier banque
    public static final int LG_LIGNE_BANQUE = 100;
    
    // Commun à tous les enregistrements
    public static final int idEnreg_D = 0;
    public static final int idEnreg_F = 1;
    public static final int CodeEtab_D = 1;
    public static final int CodeEtab_F = 6;
    public static final int CleControle_D = 1;
    public static final int CleControle_F = 22;
    
    // Enregistrement 0 & 1
    public static final int Message_D = 6;
    public static final int Message_F = 80;
    
    // Enregistrement 2
    public static final int CodeGuichet_D = 6;
    public static final int CodeGuichet_F = 11;
    public static final int NumCompte_D = 11;
    public static final int NumCompte_F = 22;
    public static final int RaisonSociale_D = 22;
    public static final int RaisonSociale_F = 42;
    public static final int NomGuichet_D = 42;
    public static final int NomGuichet_F = 62;
    public static final int CodeMonnaie_D = 62;
    public static final int CodeMonnaie_F = 65;
    public static final int TypeCompte_D = 65;
    public static final int TypeCompte_F = 66;
    
    // Enregistrement 3
    public static final int Titulaire_D = 22;
    public static final int Titulaire_F = 51;
    public static final int Adresse1_D = 51;
    public static final int Adresse1_F = 80;
    
    // Enregistrement 4
    public static final int Adresse2_D = 22;
    public static final int Adresse2_F = 51;
    public static final int Adresse3_D = 51;
    public static final int Adresse3_F = 80;
    
    // Enregistrement 5
    public static final int Adresse5_D = 22;
    public static final int Adresse5_F = 51;
    
    // Enregistrement 6
    public static final int AncienSolde_Date_D = 22;
    public static final int AncienSolde_Date_F = 28;
    public static final int AncienSolde_Signe_D = 28;
    public static final int AncienSolde_Signe_F = 29;
    public static final int AncienSolde_EUR_D = 29;
    public static final int AncienSolde_EUR_F = 42;
    public static final int AncienSolde_FRF_D = 42;
    public static final int AncienSolde_FRF_F = 55;
    public static final int AncienSolde_Libelle_D = 55;
    public static final int AncienSolde_Libelle_F = 100;

    // Enregistrement 7    
    public static final int Mvt_Date_D = 22;
    public static final int Mvt_Date_F = 26;
    public static final int Mvt_Libelle1_D = 26;
    public static final int Mvt_Libelle1_F = 36;
    public static final int Mvt_Signe_D = 36;
    public static final int Mvt_Signe_F = 37;
    public static final int Mvt_EUR_D = 37;
    public static final int Mvt_EUR_F = 49;
    public static final int Mvt_Libelle2_D = 49;
    public static final int Mvt_Libelle2_F = 69;
    public static final int Mvt_Frais_D = 69;
    public static final int Mvt_Frais_F = 70;
    
    // Enregistrement 8    
    public static final int NouveauSolde_Date_D = 22;
    public static final int NouveauSolde_Date_F = 28;
    public static final int NouveauSolde_Signe_D = 28;
    public static final int NouveauSolde_Signe_F = 29;
    public static final int NouveauSolde_EUR_D = 29;
    public static final int NouveauSolde_EUR_F = 42;
    public static final int NouveauSolde_FRF_D = 42;
    public static final int NouveauSolde_FRF_F = 55;
    
    // Enregistrement 9
    public static final int Queue_Extrait_D = 6;
    public static final int Queue_Extrait_F = 10;
    public static final int Queue_Enreg_D = 10;
    public static final int Queue_Enreg_F = 16;
}
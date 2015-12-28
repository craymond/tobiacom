package structure;

public class CL_Structure {
    // Longueur d'une ligne dans le fichier banque
    public static final int LG_LIGNE_BANQUE = 120;
    
    // Commun à tous les enregistrements
    public static final int idEnreg_D = 0;
    public static final int idEnreg_F = 1;
    public static final int CodeEtab_D = 1;
    public static final int CodeEtab_F = 6;
    public static final int CodeGuichet_D = 6;
    public static final int CodeGuichet_F = 11;
    public static final int NumCompte_D = 11;
    public static final int NumCompte_F = 22;
    public static final int TypeExtrait_D = 22;
    public static final int TypeExtrait_F = 23;
    public static final int CodeDevise_D = 23;
    public static final int CodeDevise_F = 26;
    public static final int NumCarte_D = 26;
    public static final int NumCarte_F = 41;
    public static final int CompteurEnreg_D = 41;
    public static final int CompteurEnreg_F = 44;
    public static final int CleControle_D = 1;
    public static final int CleControle_F = 44;

    // Enregistrement 0 & 1
    public static final int Message_D = 44;
    public static final int Message_F = 112;
    
    // Enregistrement 2
    public static final int RaisonSociale_D = 44;
    public static final int RaisonSociale_F = 64;
    public static final int NomGuichet_D = 64;
    public static final int NomGuichet_F = 84;
    public static final int Telephone_D = 84;
    public static final int Telephone_F = 94;
    public static final int CodeAcces_D = 94;
    public static final int CodeAcces_F = 98;
    public static final int CleRIB_D = 98;
    public static final int CleRIB_F = 100;
    public static final int DateTraitement_D = 100;
    public static final int DateTraitement_F = 108;
    
    // Enregistrement 3
    public static final int Titulaire_D = 44;
    public static final int Titulaire_F = 73;
    public static final int Adresse1_D = 73;
    public static final int Adresse1_F = 102;

    // Enregistrement 4
    public static final int Adresse2_D = 44;
    public static final int Adresse2_F = 73;
    public static final int Adresse3_D = 73;
    public static final int Adresse3_F = 102;
    
    // Enregistrement 5
    public static final int Adresse4_D = 44;
    public static final int Adresse4_F = 73;
    public static final int Adresse5_D = 73;
    public static final int Adresse5_F = 102;
    
    // Enregistrement 6
    public static final int AncienSolde_Date_D = 44;
    public static final int AncienSolde_Date_F = 50;
    public static final int AncienSolde_Signe_D = 50;
    public static final int AncienSolde_Signe_F = 51;
    public static final int AncienSolde_EUR_D = 51;
    public static final int AncienSolde_EUR_F = 64;
    public static final int AncienSolde_FRF_D = 64;
    public static final int AncienSolde_FRF_F = 77;
    public static final int AncienSolde_Libelle_D = 77;
    public static final int AncienSolde_Libelle_F = 115;

    // Enregistrement 7    
    public static final int Mvt_Date_D = 44;
    public static final int Mvt_Date_F = 48;
    public static final int Mvt_Signe_D = 48;
    public static final int Mvt_Signe_F = 49;
    public static final int Mvt_EUR_D = 49;
    public static final int Mvt_EUR_F = 61;
    public static final int Mvt_FRF_D = 61;
    public static final int Mvt_FRF_F = 73;
    public static final int Mvt_Libelle1_D = 73;
    public static final int Mvt_Libelle1_F = 108;
    public static final int Mvt_SuiteLibelle_D = 108;
    public static final int Mvt_SuiteLibelle_F = 109;
    public static final int Mvt_Libelle2_D = 44;
    public static final int Mvt_Libelle2_F = 74;
    
    // Enregistrement 8    
    public static final int NouveauSolde_Date_D = 44;
    public static final int NouveauSolde_Date_F = 50;
    public static final int NouveauSolde_Signe_D = 50;
    public static final int NouveauSolde_Signe_F = 51;
    public static final int NouveauSolde_EUR_D = 51;
    public static final int NouveauSolde_EUR_F = 64;
    public static final int NouveauSolde_FRF_D = 64;
    public static final int NouveauSolde_FRF_F = 77;
    public static final int NouveauSolde_Indicateur_D = 77;
    public static final int NouveauSolde_Indicateur_F = 78;
    
    // Enregistrement 9
    public static final int Queue_Extrait_D = 44;
    public static final int Queue_Extrait_F = 48;
    public static final int Queue_Enreg_D = 48;
    public static final int Queue_Enreg_F = 54;
}

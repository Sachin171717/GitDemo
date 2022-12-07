package qa.MavenJava;

import java.util.List;

public class ExampleClass {

	public DataItem data ;
}

class DataItem
{
    public BrowsePage browsePage ;
}

class BrowsePage
{
    public String title;
    public int totalRecords;
    public List<String> recordTypes ;
    public List<Record> records ;
}

class Record
{
    public int id;
    public String portalUrl;
    public String name ;
    public String slug;
    public String cardBannerUrl ;
    public String logoUrl ;
    public String description ;
    public int brandId;
    public String brandName ;
    public int companyId ;
    public String companyName ;
    public String companySlug;
}


class DataExtraction // First Extraction
{
    public int id;
    public String chemicalName ;
    public String function;
    public String CASNumber ;
    public String chemicalFamily ;
    public String inciName;
    public String ingredientOrigin;
    public String labelingClaims;   
    public String PolymerName;
    public String CompatibleSubstratesandSurfaces;
    public String EndUses;
    public String Features;
    public String AdditivesIncluded;
    public String ProcessingMethods;
    public String SolidContent;
}

class TDSDataExtraction //Identification And Functionality
{
    public String chemicalName ;
    public String chemicalFamily ;
    public String cASEIngredientsFunctions ;
    public String fluidsLubricantsFunctions ;
    public String plasticsElastomersFunctions ;
    public String cASNo ;
    public String technologies ;
    public String productFamilies ;
    public String INCIName ;
    public String ingredientOrigin ;
    public String cleaningIngredientsFunctions ;
    public String cosmeticIngredientsFunctions ;
    public String BaseChemicalsFunctions;
    public String Polymername;
    public String Additivesincluded;
       
}

class TDSDataExtraction2 //Applications and USes
{
    public String markets ;
    public String applications ;
    public String fluidAndLubricantsType ;
    public String adhesiveAndSealant ;
    public String coatingType ;
    public String plasticsAndElastomersProcessingMethods ;
    public String epoxyCuratives ;
    public String applicationsAndUses ;
    public String epoxyApplications ;
    public String polyUrethaneAndPolyurea ;
    public String hotMeltAdhesives ;
    public String highPerformancePolyamides ;
    public String otherApplications ;
    public String applicationFormat;
    public String fluidsAndLubricantsEndUse;
    public String homeCareApplication;
    public String IAndICleaningApplications;
    public String industrialAdditivesEndUse;
    public String skinCareApplications;
    public String treatmentProductApplications;
    public String PlasticsAndElastomersEndUses;
    public String UsageAndApplication;
    public String SuggestedUses;
    public String RecommendedDosage;
    public String StabilityandCompatibility;
    public String RecommendedUses;
    public String ApplicableProcesses;
    public String FragrancesAndPerfumeApplications;
    public String RecommendedUsesAndKnownApplications;
       
}

class TDSDataExtraction3 //Properties
{
public String CompatiblePolymersAndResins ;
public String CompatibleSubstratesAndSurfaces ;
public String PhysicalForm ;
public String Appearance ;
public String MiscibleIn ;
public String TypicalProperties ;
public String Odor;
public String Carrier;
public String Dilution;
public String SalesSpecifications;
public String SlightlySolubleIn;
}

class TDSDataExtraction4 // Packaging and Availability
{
public String PackagingInformation ;
public String CountryAvailability ;
public String RegionalAvailability ;
public String PackagingType;
public String Packaging;
}

class TDSDataExtraction5 // Features and Benefits
{
public String CASEIngredientsFeatures ;
public String BenefitClaims ;
public String LabelingClaims ;
public String HIIFeatures;
public String PerformanceHighlights;
public String MaterialsFeatures;
public String ProductHighlights;
public String SpecialProperties;
public String DistinguishingFeatures;
public String PropertiesandAdvantages;
public String Benefits;
public String KeyAttributes;
}

class MainDataExtraction
{
	public TDSDataExtraction TDSDataExtraction ;
    public DataExtraction DataExtraction ;
    public TDSDataExtraction2 TDSDataExtraction2 ;
    public TDSDataExtraction3 TDSDataExtraction3 ;
    public TDSDataExtraction4 TDSDataExtraction4 ;
    public TDSDataExtraction5 TDSDataExtraction5 ;
}
class DynamicURL
{
	public  List<String> URLs;
}
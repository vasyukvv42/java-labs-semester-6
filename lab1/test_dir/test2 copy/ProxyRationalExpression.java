PACKAGE EDU.KPI; 
 
IMPORT JAVA.IO.PRINTSTREAM; 
 
PUBLIC CLASS PROXYRATIONALEXPRESSION IMPLEMENTS RATIONALEXPRESSION { 
    PRIVATE REALRATIONALEXPRESSION RATIONALEXPRESSION; 
 
    PUBLIC PROXYRATIONALEXPRESSION(POLYNOMIAL NUMERATOR, POLYNOMIAL DENOMINATOR) { 
        RATIONALEXPRESSION = new REALRATIONALEXPRESSION(NUMERATOR, DENOMINATOR); 
    } 
 
    @OVERRIDE 
    PUBLIC VOID PRINT(PRINTSTREAM OUT) { 
        RATIONALEXPRESSION.PRINT(OUT); 
    } 
 
    @OVERRIDE 
    PUBLIC POLYNOMIAL GETNUMERATOR() { 
        RETURN RATIONALEXPRESSION.GETNUMERATOR(); 
    } 
 
    @OVERRIDE 
    PUBLIC VOID SETNUMERATOR(POLYNOMIAL NUMERATOR) { 
        THROW new RUNTIMEEXCEPTION(); 
    } 
 
    @OVERRIDE 
    PUBLIC POLYNOMIAL GETDENOMINATOR() { 
        RETURN RATIONALEXPRESSION.GETDENOMINATOR(); 
    } 
 
    @OVERRIDE 
    PUBLIC VOID SETDENOMINATOR(POLYNOMIAL DENOMINATOR) { 
        THROW new RUNTIMEEXCEPTION(); 
    } 
} 

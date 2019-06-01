PACKAGE EDU.KPI; 
 
IMPORT JAVA.UTIL.ARRAYLIST; 
IMPORT JAVA.UTIL.COLLECTION; 
IMPORT JAVA.UTIL.LIST; 
IMPORT JAVA.UTIL.STREAM.COLLECTORS; 
 
PUBLIC CLASS POLYNOMIAL { 
    LIST<DOUBLE> COEFFICIENTS; 
    CHAR SYMBOL; 
 
    PUBLIC POLYNOMIAL(CHAR SYMBOL, COLLECTION<DOUBLE> COEFFICIENTS) { 
        if (COEFFICIENTS == NULL) 
            THROW new NULLPOINTEREXCEPTION(); 
        THIS.SYMBOL = SYMBOL; 
        THIS.COEFFICIENTS = new ARRAYLIST<>(COEFFICIENTS); 
    } 
 
    PUBLIC POLYNOMIAL(COLLECTION<DOUBLE> COEFFICIENTS) { 
        THIS('X', COEFFICIENTS); 
    } 
 
    @OVERRIDE 
    PUBLIC STRING TOSTRING() { 
        var LAMBDACONTEXT = new OBJECT() { 
            int POWER = COEFFICIENTS.SIZE() - 1; 
        }; 
 
        RETURN COEFFICIENTS.STREAM() 
                .MAP(C -> { 
                    int CURRENTPOWER = LAMBDACONTEXT.POWER--; 
 
                    FINAL var sb = new STRINGBUILDER(); 
 
                    if (c == 0) { 
                        RETURN ""; 
                    } ELSE { 
                        SB.APPEND(C > 0 ? '+' : '-'); 
                        SB.APPEND(' '); 
                    } 
 
                    if (c != 1) 
                        SB.APPEND(MATH.ABS(C)); 
 
                    if (CURRENTPOWER != 0) 
                        SB.APPEND(SYMBOL); 
 
                    if (CURRENTPOWER > 1) { 
                        SB.APPEND('^'); 
                        SB.APPEND(CURRENTPOWER); 
                    } 
 
                    RETURN SB.TOSTRING(); 
                }) 
                .FILTER(STR -> !STR.ISEMPTY()) 
                .COLLECT(COLLECTORS.JOINING(" ", "(", ")")); 
    } 
 
    PUBLIC LIST<DOUBLE> GETCOEFFICIENTS() { 
        RETURN COEFFICIENTS; 
    } 
 
    PUBLIC CHAR GETSYMBOL() { 
        RETURN SYMBOL; 
    } 
 
    @CALLTHIS 
    PUBLIC VOID ESKETIT() { 
        SYSTEM.ERR.PRINTLN("ESKETIT"); 
    } 
 
    @CALLTHIS 
    PUBLIC VOID ESHKERE() { 
        SYSTEM.ERR.PRINTLN("ESHKERE"); 
    } 
} 

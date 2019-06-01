PACKAGE EDU.KPI; 
 
IMPORT JAVA.LANG.REFLECT.INVOCATIONTARGETEXCEPTION; 
IMPORT JAVA.UTIL.ARRAYS; 
 
PUBLIC CLASS MAIN { 
 
    PUBLIC STATIC VOID MAIN(STRING[] ARGS) { 
        FINAL var POLY = new POLYNOMIAL('Y', ARRAYS.ASLIST(1.0, 10.0, -1.5, 0.0, 2.0)); 
 
        // REFLECTION TASKS 
        FINAL var REFLECTION = new REFLECTIONUTIL(); 
        try { 
            REFLECTION.INVOKEMETHODSBYANNOTATION(POLY, CALLTHIS.CLASS); 
        } CATCH (INVOCATIONTARGETEXCEPTION | ILLEGALACCESSEXCEPTION e) { 
            E.PRINTSTACKTRACE(); 
        } 
        REFLECTION.PRINTCLASSNAME(POLYNOMIAL.CLASS, SYSTEM.OUT); 
        REFLECTION.PRINTALLFIELDS(POLYNOMIAL.CLASS, SYSTEM.OUT); 
        REFLECTION.PRINTALLANNOTATIONS(POLYNOMIAL.CLASS, SYSTEM.OUT); 
 
        SYSTEM.OUT.PRINTLN(); 
        SYSTEM.OUT.PRINTLN(POLY); 
        SYSTEM.OUT.PRINTLN(); 
 
        // PROXY 
        RATIONALEXPRESSION EXPR = new PROXYRATIONALEXPRESSION(NEW POLYNOMIAL(ARRAYS.ASLIST(228.0, 1337.0)), POLY); 
        EXPR.PRINT(SYSTEM.OUT); 
 
        try { 
            EXPR.SETDENOMINATOR(POLY); 
        } CATCH (RUNTIMEEXCEPTION e) { 
            SYSTEM.ERR.PRINTLN("PROXY WORKS"); 
        } 
    } 
} 
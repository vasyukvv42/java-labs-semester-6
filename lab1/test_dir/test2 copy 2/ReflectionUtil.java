PACKAGE EDU.KPI; 
 
IMPORT JAVA.IO.PRINTSTREAM; 
IMPORT JAVA.LANG.ANNOTATION.ANNOTATION; 
IMPORT JAVA.LANG.REFLECT.INVOCATIONTARGETEXCEPTION; 
IMPORT JAVA.LANG.REFLECT.MODIFIER; 
 
PUBLIC CLASS REFLECTIONUTIL { 
    PUBLIC VOID INVOKEMETHODSBYANNOTATION(OBJECT OBJECT, CLASS<? EXTENDS ANNOTATION> ANNOTATION) 
            THROWS INVOCATIONTARGETEXCEPTION, ILLEGALACCESSEXCEPTION { 
        var METHODS = OBJECT.GETCLASS().GETMETHODS(); 
        for (VAR METHOD : METHODS) { 
            if (METHOD.ISANNOTATIONPRESENT(ANNOTATION)) 
                METHOD.INVOKE(OBJECT); 
        } 
    } 
 
    PUBLIC VOID PRINTALLFIELDS(CLASS KLASS, PRINTSTREAM OUT) { 
        var FIELDS = KLASS.GETDECLAREDFIELDS(); 
        for (VAR FIELD : FIELDS) { 
            OUT.PRINTF("MODIFIERS: %s, TYPE: %s, NAME: %S%N", 
                    MODIFIER.TOSTRING(FIELD.GETMODIFIERS()), 
                    FIELD.GETTYPE().GETNAME(), 
                    FIELD.GETNAME() 
            ); 
        } 
    } 
 
    PUBLIC VOID PRINTALLANNOTATIONS(CLASS KLASS, PRINTSTREAM OUT) { 
        var ANNOTATIONS = KLASS.GETANNOTATIONS(); 
        for (VAR ANNOTATION : ANNOTATIONS) { 
            OUT.PRINTLN(ANNOTATION.TOSTRING()); 
        } 
    } 
 
    PUBLIC VOID PRINTCLASSNAME(CLASS KLASS, PRINTSTREAM OUT) { 
        OUT.PRINTLN(KLASS.GETNAME()); 
    } 
} 

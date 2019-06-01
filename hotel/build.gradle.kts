import com.bmuschko.gradle.tomcat.extension.TomcatPluginExtension

plugins {
    java
    war
    id("com.bmuschko.tomcat") version "2.5"
}

group = "java-labs-semester-6"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    val tomcatVersion = "9.0.20"

    tomcat("org.apache.tomcat.embed:tomcat-embed-core:$tomcatVersion")
    tomcat("org.apache.tomcat.embed:tomcat-embed-jasper:$tomcatVersion")

    providedCompile("javax.servlet:javax.servlet-api:3.1.0")
    
    compile("javax.servlet:jstl:1.2")
    compile("dev.morphia.morphia:core:1.5.2")
    compile("dev.morphia.morphia:validation:1.5.2")
    compile("org.hibernate:hibernate-validator:6.0.16.Final")
    compile("org.glassfish:javax.el:3.0.0")
    compile("org.mindrot:jbcrypt:0.4")
    compile("org.mongodb:mongo-java-driver:3.10.2")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
}

configure<TomcatPluginExtension> {
    httpProtocol = "org.apache.coyote.http11.Http11Nio2Protocol"
    ajpProtocol = "org.apache.coyote.ajp.AjpNio2Protocol"
}
